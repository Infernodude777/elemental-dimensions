package com.nikhil.elementaldimensions.entity.astralfrontier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TheVoidArchonEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int teleportCooldown = 0;
    private int voidCooldown = 0;

    public TheVoidArchonEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 150;
        this.bossBar = new ServerBossBar(
                Text.translatable("entity.elementaldimensions.the_void_archon"),
                BossBar.Color.PURPLE,
                BossBar.Style.PROGRESS
        );
        this.bossBar.setDarkenSky(true);
    }

    public static DefaultAttributeContainer.Builder createTheVoidArchonAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 18.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 80.0)
                .add(EntityAttributes.GENERIC_ARMOR, 12.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.3, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.9));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 25.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient) {
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

            float healthPercent = this.getHealth() / this.getMaxHealth();
            if (healthPercent <= 0.66f && phase == 1) {
                phase = 2;
                this.bossBar.setColor(BossBar.Color.PINK);
                this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(22.0);
            } else if (healthPercent <= 0.33f && phase == 2) {
                phase = 3;
                this.bossBar.setColor(BossBar.Color.RED);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(26.0);
            }

            if (teleportCooldown > 0) {
                teleportCooldown--;
            }

            if (voidCooldown > 0) {
                voidCooldown--;
            }

            if (phase >= 2 && teleportCooldown == 0 && this.random.nextInt(120) == 0) {
                teleportRandomly();
                teleportCooldown = 200;
            }

            if (phase >= 3 && voidCooldown == 0 && this.getTarget() instanceof PlayerEntity player) {
                applyVoidEffects(player);
                voidCooldown = 180;
            }
        }
    }

    private void teleportRandomly() {
        if (this.getWorld().isClient) return;

        for (int i = 0; i < 16; i++) {
            double d = this.getX() + (this.random.nextDouble() - 0.5) * 16.0;
            double e = this.getY() + (double) (this.random.nextInt(16) - 8);
            double f = this.getZ() + (this.random.nextDouble() - 0.5) * 16.0;

            if (this.teleport(d, e, f, true)) {
                this.getWorld().sendEntityStatus(this, (byte) 46);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                break;
            }
        }
    }

    private void applyVoidEffects(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 2));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 80, 0));
        this.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 1.5F, 0.8F);
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WARDEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_WARDEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }

    @Override
    protected boolean isDisallowedInPeaceful() {
        return true;
    }
}
