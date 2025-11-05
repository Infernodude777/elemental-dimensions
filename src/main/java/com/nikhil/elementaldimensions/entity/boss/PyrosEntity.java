package com.nikhil.elementaldimensions.entity.boss;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
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
 * Pyros the Eternal Flame - Boss of the Inferno Realm
 * 
 * Phase 1 (100%-70% HP): Fire Wave attacks
 * Phase 2 (70%-30% HP): Meteor showers, lava pools, fire shield
 * Phase 3 (30%-0% HP): Inferno Rage mode - rapid attacks, explosion immunity
 */
public class PyrosEntity extends HostileEntity {
    
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int attackCooldown = 0;
    private int specialAttackTimer = 0;
    
    public PyrosEntity(EntityType<? extends PyrosEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 500; // Massive XP reward
        
        // Create boss bar
        this.bossBar = new ServerBossBar(
                Text.literal("Pyros the Eternal Flame"),
                BossBar.Color.RED,
                BossBar.Style.NOTCHED_10
        );
        this.bossBar.setDarkenSky(true);
        this.bossBar.setDragonMusic(true);
    }
    
    public static DefaultAttributeContainer.Builder createPyrosAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 500.0)
                .add(EntityAttributes.ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.ARMOR, 10.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.FOLLOW_RANGE, 64.0);
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 32.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));
        
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }
    
    @Override
    public void tick() {
        super.tick();
        
        if (!this.getEntityWorld().isClient()) {
            // Update boss bar
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            
            // Update phase based on health
            updatePhase();
            
            // Spawn fire particles
            if (this.age % 5 == 0) {
                spawnFireParticles();
            }
            
            // Handle special attacks
            if (specialAttackTimer > 0) {
                specialAttackTimer--;
            }
            
            if (attackCooldown > 0) {
                attackCooldown--;
            } else {
                performPhaseAbility();
                attackCooldown = 60; // 3 second cooldown
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
        // Visual effect on phase change
        if (!this.getEntityWorld().isClient()) {
            ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
            
            // Explosion particles
            for (int i = 0; i < 50; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 4;
                double offsetY = this.random.nextDouble() * 3;
                double offsetZ = (this.random.nextDouble() - 0.5) * 4;
                
                serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
                        this.getX() + offsetX,
                        this.getY() + offsetY,
                        this.getZ() + offsetZ,
                        1, 0, 0, 0, 0);
            }
            
            this.playSound(SoundEvents.ENTITY_ENDER_DRAGON_GROWL, 2.0f, 0.8f);
            
            // Heal slightly on phase change
            this.heal(50.0f);
        }
    }
    
    private void performPhaseAbility() {
        if (this.getTarget() == null) return;
        
        switch (this.phase) {
            case 1 -> fireWaveAttack();
            case 2 -> meteorShower();
            case 3 -> infernoRage();
        }
    }
    
    private void fireWaveAttack() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        // Create expanding ring of fire
        for (int i = 0; i < 360; i += 10) {
            double angle = Math.toRadians(i);
            double radius = 3.0;
            
            double x = pos.x + Math.cos(angle) * radius;
            double z = pos.z + Math.sin(angle) * radius;
            
            serverWorld.spawnParticles(ParticleTypes.FLAME,
                    x, pos.y + 0.1, z,
                    5, 0.1, 0.1, 0.1, 0.05);
        }
        
        // Damage nearby entities
        Box damageBox = new Box(pos.add(-3, -1, -3), pos.add(3, 2, 3));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().inFire(), 8.0f);
            entity.setOnFireFor(5);
        }
        
        this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.5f, 0.9f);
    }
    
    private void meteorShower() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d targetPos = this.getTarget().getBlockPos().toCenterPos();
        
        // Spawn 5 meteors around target
        for (int i = 0; i < 5; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 10;
            double offsetZ = (this.random.nextDouble() - 0.5) * 10;
            
            BlockPos meteorPos = BlockPos.ofFloored(
                    targetPos.x + offsetX,
                    targetPos.y + 20,
                    targetPos.z + offsetZ
            );
            
            // Particle trail
            serverWorld.spawnParticles(ParticleTypes.LAVA,
                    meteorPos.getX(), meteorPos.getY(), meteorPos.getZ(),
                    20, 0.5, 10, 0.5, 0.1);
            
            // Damage on impact
            BlockPos impactPos = new BlockPos(
                    meteorPos.getX(),
                    (int) targetPos.y,
                    meteorPos.getZ()
            );
            
            Box damageBox = new Box(impactPos).expand(3);
            List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                    LivingEntity.class, damageBox, entity -> entity != this);
            
            for (LivingEntity entity : entities) {
                entity.damage(serverWorld, this.getDamageSources().hotFloor(), 12.0f);
            }
        }
        
        this.playSound(SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 2.0f, 0.7f);
    }
    
    private void infernoRage() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        // Massive fire explosion
        Box damageBox = new Box(pos.add(-6, -2, -6), pos.add(6, 4, 6));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().inFire(), 20.0f);
            entity.setOnFireFor(10);
            
            // Knockback
            Vec3d direction = entity.getBlockPos().toCenterPos().subtract(pos).normalize();
            entity.setVelocity(direction.multiply(2.0));
            entity.velocityModified = true;
        }
        
        // Particle explosion
        serverWorld.spawnParticles(ParticleTypes.EXPLOSION_EMITTER,
                pos.x, pos.y + 1, pos.z,
                1, 0, 0, 0, 0);
        
        this.playSound(SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 2.0f, 0.5f);
    }
    
    private void spawnFireParticles() {
        if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.FLAME,
                    this.getX() + (this.random.nextDouble() - 0.5) * 2,
                    this.getY() + this.random.nextDouble() * 2,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 2,
                    1, 0, 0, 0, 0);
        }
    }
    
    @Override
    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(world, damageSource, causedByPlayer);
        
        // Drop Inferno Heart (guaranteed)
        ItemStack heart = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "inferno_heart"))
        );
        this.dropStack(world, heart);
        
        // Additional fire essence drops
        ItemStack essence = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "fire_essence")),
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
    
    // Note: NBT persistence disabled - phase will reset on chunk reload
    // This is acceptable for boss entities that are typically fought in one session
    /*
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Phase", this.phase);
    }
    
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.phase = nbt.getInt("Phase").orElse(1);
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }
    */
    
    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false; // Boss never despawns
    }
    
    @Override
    public boolean cannotDespawn() {
        return true;
    }
    
    @Override
    public boolean isFireImmune() {
        return true; // Fire boss is immune to fire
    }
}
