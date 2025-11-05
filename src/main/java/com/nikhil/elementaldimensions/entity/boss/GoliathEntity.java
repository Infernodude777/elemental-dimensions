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
 * Goliath - Boss of the Terran Realm
 * 
 * Phase 1 (100%-70% HP): Ground slams and rock throws
 * Phase 2 (70%-30% HP): Earthquake, gravity manipulation
 * Phase 3 (30%-0% HP): Earth rage - massive fissures
 */
public class GoliathEntity extends HostileEntity {
    
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int attackCooldown = 0;
    
    public GoliathEntity(EntityType<? extends GoliathEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 500;
        
        this.bossBar = new ServerBossBar(
                Text.literal("Goliath the Stone Titan"),
                BossBar.Color.GREEN,
                BossBar.Style.NOTCHED_10
        );
        this.bossBar.setDarkenSky(true);
    }
    
    public static DefaultAttributeContainer.Builder createGoliathAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 600.0)
                .add(EntityAttributes.ATTACK_DAMAGE, 20.0)
                .add(EntityAttributes.ARMOR, 15.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.FOLLOW_RANGE, 64.0);
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.7));
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
            
            if (this.age % 20 == 0) {
                spawnEarthParticles();
            }
            
            if (attackCooldown > 0) {
                attackCooldown--;
            } else {
                performPhaseAbility();
                attackCooldown = 60;
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
            
            // Ground shatter effect
            for (int i = 0; i < 80; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 8;
                double offsetY = this.random.nextDouble() * 2;
                double offsetZ = (this.random.nextDouble() - 0.5) * 8;
                
                serverWorld.spawnParticles(ParticleTypes.POOF,
                        pos.x + offsetX, pos.y + offsetY, pos.z + offsetZ,
                        1, 0, 0, 0, 0);
            }
            
            this.playSound(SoundEvents.ENTITY_RAVAGER_ROAR, 2.0f, 0.6f);
            this.heal(100.0f);
        }
    }
    
    private void performPhaseAbility() {
        if (this.getTarget() == null) return;
        
        switch (this.phase) {
            case 1 -> groundSlam();
            case 2 -> earthquake();
            case 3 -> earthRage();
        }
    }
    
    private void groundSlam() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        // Create shockwave
        for (int radius = 1; radius <= 6; radius++) {
            final int r = radius;
            for (int i = 0; i < 360; i += 10) {
                double angle = Math.toRadians(i);
                double x = pos.x + Math.cos(angle) * r;
                double z = pos.z + Math.sin(angle) * r;
                
                serverWorld.spawnParticles(ParticleTypes.POOF,
                        x, pos.y + 0.1, z,
                        5, 0.2, 0.1, 0.2, 0.05);
            }
        }
        
        // Damage and slow
        Box damageBox = new Box(pos.add(-6, -1, -6), pos.add(6, 2, 6));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().mobAttack(this), 12.0f);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 2));
            
            // Small upward knockback
            entity.setVelocity(entity.getVelocity().x, 0.5, entity.getVelocity().z);
            entity.velocityModified = true;
        }
        
        this.playSound(SoundEvents.ENTITY_RAVAGER_ATTACK, 2.0f, 0.7f);
    }
    
    private void earthquake() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        // Continuous shaking effect
        Box damageBox = new Box(pos.add(-10, -2, -10), pos.add(10, 3, 10));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            // Multiple smaller hits over time
            entity.damage(serverWorld, this.getDamageSources().magic(), 8.0f);
            
            // Random horizontal knockback (earthquake shaking)
            double randX = (this.random.nextDouble() - 0.5) * 0.8;
            double randZ = (this.random.nextDouble() - 0.5) * 0.8;
            entity.setVelocity(randX, entity.getVelocity().y, randZ);
            entity.velocityModified = true;
            
            // Nausea effect
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
        }
        
        // Massive particle effect
        serverWorld.spawnParticles(ParticleTypes.POOF,
                pos.x, pos.y, pos.z,
                100, 5.0, 1.0, 5.0, 0.2);
        
        this.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 2.0f, 0.5f);
    }
    
    private void earthRage() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        // Create fissures in all directions
        for (int dir = 0; dir < 8; dir++) {
            double angle = Math.toRadians(dir * 45);
            
            for (int dist = 1; dist <= 8; dist++) {
                double x = pos.x + Math.cos(angle) * dist;
                double z = pos.z + Math.sin(angle) * dist;
                
                serverWorld.spawnParticles(ParticleTypes.LAVA,
                        x, pos.y, z,
                        10, 0.2, 0.5, 0.2, 0.1);
                
                // Damage along fissure line
                BlockPos fissurePos = BlockPos.ofFloored(x, pos.y, z);
                Box damageBox = new Box(fissurePos).expand(1);
                List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                        LivingEntity.class, damageBox, entity -> entity != this);
                
                for (LivingEntity entity : entities) {
                    entity.damage(serverWorld, this.getDamageSources().lava(), 15.0f);
                    entity.setOnFireFor(5);
                }
            }
        }
        
        this.playSound(SoundEvents.BLOCK_BASALT_BREAK, 2.0f, 0.4f);
    }
    
    private void spawnEarthParticles() {
        if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.POOF,
                    this.getX() + (this.random.nextDouble() - 0.5) * 2,
                    this.getY() + 0.5,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 2,
                    1, 0, 0, 0, 0);
        }
    }
    
    @Override
    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(world, damageSource, causedByPlayer);
        
        ItemStack heart = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "terran_heart"))
        );
        this.dropStack(world, heart);
        
        ItemStack essence = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "earth_essence")),
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
