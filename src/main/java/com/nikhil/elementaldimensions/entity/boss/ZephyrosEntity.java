package com.nikhil.elementaldimensions.entity.boss;

import net.minecraft.entity.*;
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
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Zephyros - Boss of the Skybound Realm
 * 
 * Phase 1 (100%-70% HP): Wind gusts and air blasts
 * Phase 2 (70%-30% HP): Lightning strikes, tornado
 * Phase 3 (30%-0% HP): Sky rage - devastating wind storm
 */
public class ZephyrosEntity extends HostileEntity {
    
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int attackCooldown = 0;
    
    public ZephyrosEntity(EntityType<? extends ZephyrosEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 500;
        
        this.bossBar = new ServerBossBar(
                Text.literal("Zephyros the Storm Lord"),
                BossBar.Color.WHITE,
                BossBar.Style.NOTCHED_10
        );
        this.bossBar.setDarkenSky(true);
    }
    
    public static DefaultAttributeContainer.Builder createZephyrosAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 450.0)
                .add(EntityAttributes.ATTACK_DAMAGE, 16.0)
                .add(EntityAttributes.ARMOR, 8.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.FOLLOW_RANGE, 64.0)
                .add(EntityAttributes.FLYING_SPEED, 0.4);
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 32.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));
        
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }
    
    @Override
    public void tick() {
        super.tick();
        
        if (!this.getEntityWorld().isClient()) {
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            updatePhase();
            
            // Levitation effect
            if (!this.isOnGround() && this.getVelocity().y < 0) {
                this.setVelocity(this.getVelocity().x, this.getVelocity().y * 0.6, this.getVelocity().z);
            }
            
            if (this.age % 5 == 0) {
                spawnWindParticles();
            }
            
            if (attackCooldown > 0) {
                attackCooldown--;
            } else {
                performPhaseAbility();
                attackCooldown = 45;
            }
        }
    }
    
    private void updatePhase() {
        float healthPercent = this.getHealth() / this.getMaxHealth();
        int newPhase = healthPercent > 0.7f ? 1 : (healthPercent > 0.3f ? 2 : 3);
        
        if (newPhase != this.phase) {
            this.phase = newPhase;
            onPhaseChange();
        }
    }
    
    private void onPhaseChange() {
        if (!this.getEntityWorld().isClient()) {
            ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
            Vec3d pos = this.getBlockPos().toCenterPos();
            
            // Wind burst effect
            for (int i = 0; i < 60; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 5;
                double offsetY = this.random.nextDouble() * 3;
                double offsetZ = (this.random.nextDouble() - 0.5) * 5;
                
                serverWorld.spawnParticles(ParticleTypes.CLOUD,
                        pos.x + offsetX, pos.y + offsetY, pos.z + offsetZ,
                        1, 0, 0, 0, 0.1);
            }
            
            this.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 2.0f, 1.2f);
            this.heal(60.0f);
        }
    }
    
    private void performPhaseAbility() {
        if (this.getTarget() == null) return;
        
        switch (this.phase) {
            case 1 -> windGust();
            case 2 -> lightningStrike();
            case 3 -> skyRage();
        }
    }
    
    private void windGust() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        Vec3d targetPos = this.getTarget().getBlockPos().toCenterPos();
        
        // Direction from boss to target
        Vec3d direction = targetPos.subtract(pos).normalize();
        
        // Create wind stream
        for (int i = 0; i < 10; i++) {
            double distance = i * 1.5;
            Vec3d particlePos = pos.add(direction.multiply(distance));
            
            serverWorld.spawnParticles(ParticleTypes.CLOUD,
                    particlePos.x, particlePos.y + 1, particlePos.z,
                    5, 0.5, 0.5, 0.5, 0.1);
        }
        
        // Knockback entities in path
        Box damageBox = new Box(pos, targetPos).expand(2);
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().mobAttack(this), 10.0f);
            
            // Strong knockback
            entity.setVelocity(direction.x * 2.0, 0.8, direction.z * 2.0);
            entity.velocityModified = true;
        }
        
        this.playSound(SoundEvents.ITEM_ELYTRA_FLYING, 2.0f, 0.8f);
    }
    
    private void lightningStrike() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d targetPos = this.getTarget().getBlockPos().toCenterPos();
        
        // Strike 3 lightning bolts around target
        for (int i = 0; i < 3; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 6;
            double offsetZ = (this.random.nextDouble() - 0.5) * 6;
            
            BlockPos strikePos = BlockPos.ofFloored(
                    targetPos.x + offsetX,
                    targetPos.y,
                    targetPos.z + offsetZ
            );
            
            // Lightning effect particles
            serverWorld.spawnParticles(ParticleTypes.ELECTRIC_SPARK,
                    strikePos.getX(), strikePos.getY() + 10, strikePos.getZ(),
                    50, 0.2, 5, 0.2, 0.1);
            
            // Damage at strike location
            Box damageBox = new Box(strikePos).expand(2);
            List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                    LivingEntity.class, damageBox, entity -> entity != this);
            
            for (LivingEntity entity : entities) {
                entity.damage(serverWorld, this.getDamageSources().lightningBolt(), 15.0f);
                entity.setOnFireFor(3);
            }
        }
        
        this.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 2.0f, 1.0f);
    }
    
    private void skyRage() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        // Tornado effect - spiral upward knockback
        Box damageBox = new Box(pos.add(-7, -3, -7), pos.add(7, 6, 7));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().flyIntoWall(), 20.0f);
            
            // Spiral knockback
            Vec3d toEntity = entity.getBlockPos().toCenterPos().subtract(pos);
            double angle = Math.atan2(toEntity.z, toEntity.x) + Math.PI / 4;
            double spiralX = Math.cos(angle) * 1.5;
            double spiralZ = Math.sin(angle) * 1.5;
            
            entity.setVelocity(spiralX, 1.2, spiralZ);
            entity.velocityModified = true;
            
            // Levitation effect
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 60, 1));
        }
        
        // Massive cloud particles
        serverWorld.spawnParticles(ParticleTypes.CLOUD,
                pos.x, pos.y + 2, pos.z,
                150, 3, 3, 3, 0.3);
        
        this.playSound(SoundEvents.ENTITY_WITHER_SHOOT, 2.0f, 1.5f);
    }
    
    private void spawnWindParticles() {
        if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.CLOUD,
                    this.getX() + (this.random.nextDouble() - 0.5) * 3,
                    this.getY() + this.random.nextDouble() * 2,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 3,
                    1, 0, 0, 0, 0.05);
        }
    }
    
    @Override
    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(world, damageSource, causedByPlayer);
        
        ItemStack heart = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "skybound_heart"))
        );
        this.dropStack(world, heart);
        
        ItemStack essence = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "air_essence")),
                5 + this.random.nextInt(5)
        );
        this.dropStack(world, essence);
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
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }
    
    @Override
    public boolean cannotDespawn() {
        return true;
    }
}
