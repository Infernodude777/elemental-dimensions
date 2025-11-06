package com.nikhil.elementaldimensions.entity.forgottenarchive;

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

public class TheLibrarianEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int summonCooldown = 0;
    private int weaknessCooldown = 0;

    public TheLibrarianEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 120;
        this.bossBar = new ServerBossBar(
                Text.translatable("entity.elementaldimensions.the_librarian"),
                BossBar.Color.BLUE,
                BossBar.Style.PROGRESS
        );
        this.bossBar.setDarkenSky(true);
    }

    public static DefaultAttributeContainer.Builder createTheLibrarianAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 340.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.32)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 65.0)
                .add(EntityAttributes.GENERIC_ARMOR, 11.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.9);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.25, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.85));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 20.0f));
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
                this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.37);
            } else if (healthPercent <= 0.33f && phase == 2) {
                phase = 3;
                this.bossBar.setColor(BossBar.Color.RED);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(19.0);
            }

            if (summonCooldown > 0) {
                summonCooldown--;
            }

            if (weaknessCooldown > 0) {
                weaknessCooldown--;
            }

            if (phase >= 2 && summonCooldown == 0 && this.random.nextInt(220) == 0) {
                summonMinions();
                summonCooldown = 450;
            }

            if (phase >= 3 && weaknessCooldown == 0 && this.getTarget() instanceof PlayerEntity player) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
                weaknessCooldown = 250;
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
                SentinelEntity sentinel = new SentinelEntity(null, this.getWorld());
                sentinel.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(sentinel);
            } else {
                PageHorrorEntity pageHorror = new PageHorrorEntity(null, this.getWorld());
                pageHorror.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(pageHorror);
            }
        }

        this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 2.0F, 1.2F);
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
        return SoundEvents.ENTITY_EVOKER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_EVOKER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_EVOKER_DEATH;
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
