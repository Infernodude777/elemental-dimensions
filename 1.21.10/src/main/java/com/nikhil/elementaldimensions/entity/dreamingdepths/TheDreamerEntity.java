package com.nikhil.elementaldimensions.entity.dreamingdepths;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class TheDreamerEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int summonCooldown = 0;

    public TheDreamerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 100;
        this.bossBar = new ServerBossBar(
                Text.translatable("entity.elementaldimensions.the_dreamer"),
                BossBar.Color.PURPLE,
                BossBar.Style.PROGRESS
        );
        this.bossBar.setDarkenSky(true);
    }

    public static DefaultAttributeContainer.Builder createTheDreamerAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0)
                .add(EntityAttributes.GENERIC_ARMOR, 8.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.8);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.3, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.9));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
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
                this.bossBar.setColor(BossBar.Color.RED);
                this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
            } else if (healthPercent <= 0.33f && phase == 2) {
                phase = 3;
                this.bossBar.setColor(BossBar.Color.PINK);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(16.0);
            }
            
            if (summonCooldown > 0) {
                summonCooldown--;
            }
            
            if (phase >= 2 && summonCooldown == 0 && this.random.nextInt(200) == 0) {
                summonMinions();
                summonCooldown = 400;
            }
        }
    }

    private void summonMinions() {
        if (this.getWorld().isClient) return;
        
        for (int i = 0; i < phase; i++) {
            double angle = (Math.PI * 2 * i) / phase;
            double x = this.getX() + Math.cos(angle) * 3.0;
            double z = this.getZ() + Math.sin(angle) * 3.0;
            
            if (this.random.nextBoolean()) {
                PhantasmEntity phantasm = new PhantasmEntity(null, this.getWorld());
                phantasm.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(phantasm);
            } else {
                NightmareShadeEntity shade = new NightmareShadeEntity(null, this.getWorld());
                shade.setPosition(x, this.getY(), z);
                this.getWorld().spawnEntity(shade);
            }
        }
        
        this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 2.0F, 1.0F);
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
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_DEATH;
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
