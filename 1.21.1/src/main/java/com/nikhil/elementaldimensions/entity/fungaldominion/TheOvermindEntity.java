package com.nikhil.elementaldimensions.entity.fungaldominion;

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

public class TheOvermindEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int summonCooldown = 0;
    private int poisonCooldown = 0;

    public TheOvermindEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 100;
        this.bossBar = new ServerBossBar(
                Text.translatable("entity.elementaldimensions.the_overmind"),
                BossBar.Color.GREEN,
                BossBar.Style.PROGRESS
        );
        this.bossBar.setDarkenSky(true);
    }

    public static DefaultAttributeContainer.Builder createTheOvermindAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 320.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 13.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 60.0)
                .add(EntityAttributes.GENERIC_ARMOR, 9.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.85);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.1, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.7));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 18.0f));
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
                this.bossBar.setColor(BossBar.Color.YELLOW);
                this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
            } else if (healthPercent <= 0.33f && phase == 2) {
                phase = 3;
                this.bossBar.setColor(BossBar.Color.RED);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(17.0);
            }

            if (summonCooldown > 0) {
                summonCooldown--;
            }

            if (poisonCooldown > 0) {
                poisonCooldown--;
            }

            if (phase >= 2 && summonCooldown == 0 && this.random.nextInt(250) == 0) {
                summonMinions();
                summonCooldown = 500;
            }

            if (phase >= 3 && poisonCooldown == 0 && this.getTarget() instanceof PlayerEntity player) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));
                poisonCooldown = 200;
            }
        }
    }

    private void summonMinions() {
        if (this.getWorld().isClient) return;

        for (int i = 0; i < phase + 1; i++) {
            double angle = (Math.PI * 2 * i) / (phase + 1);
            double x = this.getX() + Math.cos(angle) * 4.0;
            double z = this.getZ() + Math.sin(angle) * 4.0;

            if (this.random.nextBoolean()) {
                MyceliteCreeperEntity creeper = new MyceliteCreeperEntity(null, this.getWorld());
                creeper.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(creeper);
            } else {
                FungalZombieEntity zombie = new FungalZombieEntity(null, this.getWorld());
                zombie.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(zombie);
            }
        }

        this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 2.0F, 0.8F);
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
        return SoundEvents.ENTITY_RAVAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_RAVAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_RAVAGER_DEATH;
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
