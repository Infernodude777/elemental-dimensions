package com.nikhil.elementaldimensions.entity.gloomycaverns;

import net.minecraft.entity.EntityType;
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
import net.minecraft.world.World;

public class TheEchoKingEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int summonCooldown = 0;
    private int echoAttackCooldown = 0;

    public TheEchoKingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 110;
        this.bossBar = new ServerBossBar(
                Text.translatable("entity.elementaldimensions.the_echo_king"),
                BossBar.Color.BLUE,
                BossBar.Style.PROGRESS
        );
        this.bossBar.setDarkenSky(true);
    }

    public static DefaultAttributeContainer.Builder createTheEchoKingAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 310.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 16.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.33)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 72.0)
                .add(EntityAttributes.GENERIC_ARMOR, 10.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.85);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.85));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 22.0f));
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
                this.bossBar.setColor(BossBar.Color.PURPLE);
                this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.38);
            } else if (healthPercent <= 0.33f && phase == 2) {
                phase = 3;
                this.bossBar.setColor(BossBar.Color.RED);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0);
            }

            if (summonCooldown > 0) {
                summonCooldown--;
            }

            if (echoAttackCooldown > 0) {
                echoAttackCooldown--;
            }

            if (phase >= 2 && summonCooldown == 0 && this.random.nextInt(260) == 0) {
                summonMinions();
                summonCooldown = 480;
            }

            if (phase >= 3 && echoAttackCooldown == 0 && this.getTarget() instanceof PlayerEntity player) {
                echoAttack(player);
                echoAttackCooldown = 220;
            }
        }
    }

    private void summonMinions() {
        if (this.getWorld().isClient) return;

        for (int i = 0; i < phase + 1; i++) {
            double angle = (Math.PI * 2 * i) / (phase + 1);
            double x = this.getX() + Math.cos(angle) * 3.5;
            double z = this.getZ() + Math.sin(angle) * 3.5;

            if (this.random.nextBoolean()) {
                GloomStalkerEntity stalker = new GloomStalkerEntity(null, this.getWorld());
                stalker.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(stalker);
            } else {
                WitheredSkeletonEntity skeleton = new WitheredSkeletonEntity(null, this.getWorld());
                skeleton.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(skeleton);
            }
        }

        this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 2.0F, 0.9F);
    }

    private void echoAttack(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 140, 0));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 1));
        this.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 2.0F, 0.7F);
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
