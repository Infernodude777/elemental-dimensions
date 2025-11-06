package com.nikhil.elementaldimensions.entity.celestineexpanse;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TheRadiantMonarchEntity extends HostileEntity {
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int attackCooldown = 0;

    public TheRadiantMonarchEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 100;
        this.bossBar = new ServerBossBar(
                Text.translatable("entity.elementaldimensions.the_radiant_monarch"),
                BossBar.Color.YELLOW,
                BossBar.Style.PROGRESS
        );
        this.bossBar.setDarkenSky(false);
    }

    public static DefaultAttributeContainer.Builder createTheRadiantMonarchAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 350.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 14.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 70.0)
                .add(EntityAttributes.GENERIC_ARMOR, 10.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.9);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
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
                this.bossBar.setColor(BossBar.Color.WHITE);
                this.getAttributeInstance(EntityAttributes.GENERIC_FLYING_SPEED).setBaseValue(0.6);
            } else if (healthPercent <= 0.33f && phase == 2) {
                phase = 3;
                this.bossBar.setColor(BossBar.Color.BLUE);
                this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(18.0);
            }
            
            if (attackCooldown > 0) {
                attackCooldown--;
            }
            
            if (phase >= 2 && attackCooldown == 0 && this.getTarget() != null && this.random.nextInt(60) == 0) {
                shootFireball(this.getTarget());
                attackCooldown = 100;
            }
        }
    }

    private void shootFireball(LivingEntity target) {
        if (this.getWorld().isClient) return;
        
        Vec3d vec3d = this.getRotationVec(1.0F);
        double d = target.getX() - (this.getX() + vec3d.x * 4.0);
        double e = target.getBodyY(0.5) - (0.5 + this.getBodyY(0.5));
        double f = target.getZ() - (this.getZ() + vec3d.z * 4.0);
        
        Vec3d velocity = new Vec3d(
                d + this.getRandom().nextGaussian() * 0.3,
                e,
                f + this.getRandom().nextGaussian() * 0.3
        );
        
        SmallFireballEntity fireball = new SmallFireballEntity(
                this.getWorld(),
                this,
                velocity
        );
        
        fireball.setPosition(
                this.getX() + vec3d.x * 4.0,
                this.getBodyY(0.5) + 0.5,
                this.getZ() + vec3d.z * 4.0
        );
        
        this.getWorld().spawnEntity(fireball);
        this.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 2.0F, 1.0F);
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
        return SoundEvents.ENTITY_ALLAY_AMBIENT_WITH_ITEM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BLAZE_HURT;
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
