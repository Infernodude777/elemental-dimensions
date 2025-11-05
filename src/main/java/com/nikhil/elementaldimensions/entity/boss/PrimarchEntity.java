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
 * Primarch Astraeus - Ultimate Boss of the Celestial Realm
 * Combines the abilities of all 4 elemental bosses
 * 
 * Phase 1 (100%-75% HP): Fire attacks (Pyros abilities)
 * Phase 2 (75%-50% HP): Water attacks (Leviathan abilities)
 * Phase 3 (50%-25% HP): Earth attacks (Goliath abilities)
 * Phase 4 (25%-0% HP): Air attacks + ALL COMBINED (Zephyros + everything)
 */
public class PrimarchEntity extends HostileEntity {
    
    private final ServerBossBar bossBar;
    private int phase = 1;
    private int attackCooldown = 0;
    private int comboCounter = 0;
    
    public PrimarchEntity(EntityType<? extends PrimarchEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 2000; // Massive XP reward
        
        this.bossBar = new ServerBossBar(
                Text.literal("Primarch Astraeus - Master of Elements"),
                BossBar.Color.PURPLE,
                BossBar.Style.NOTCHED_20
        );
        this.bossBar.setDarkenSky(true);
        this.bossBar.setDragonMusic(true);
    }
    
    public static DefaultAttributeContainer.Builder createPrimarchAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 1000.0) // Double health of others
                .add(EntityAttributes.ATTACK_DAMAGE, 25.0)
                .add(EntityAttributes.ARMOR, 20.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.FOLLOW_RANGE, 96.0);
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.9));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 48.0f));
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
            
            // Spawn elemental particles based on phase
            if (this.age % 3 == 0) {
                spawnPhaseParticles();
            }
            
            if (attackCooldown > 0) {
                attackCooldown--;
            } else {
                performPhaseAbility();
                attackCooldown = phase == 4 ? 30 : 40; // Faster in final phase
            }
        }
    }
    
    private void updatePhase() {
        float healthPercent = this.getHealth() / this.getMaxHealth();
        int newPhase = healthPercent > 0.75f ? 1 : 
                       healthPercent > 0.5f ? 2 :
                       healthPercent > 0.25f ? 3 : 4;
        
        if (newPhase != this.phase) {
            this.phase = newPhase;
            onPhaseChange();
        }
    }
    
    private void onPhaseChange() {
        if (!this.getEntityWorld().isClient()) {
            ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
            Vec3d pos = this.getBlockPos().toCenterPos();
            
            // Massive multi-element explosion
            for (int i = 0; i < 150; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 10;
                double offsetY = this.random.nextDouble() * 5;
                double offsetZ = (this.random.nextDouble() - 0.5) * 10;
                
                // Different particle types based on new phase
                net.minecraft.particle.SimpleParticleType particleType = switch(phase) {
                    case 1 -> ParticleTypes.FLAME;
                    case 2 -> ParticleTypes.SPLASH;
                    case 3 -> ParticleTypes.POOF;
                    case 4 -> ParticleTypes.END_ROD;
                    default -> ParticleTypes.EXPLOSION;
                };
                
                serverWorld.spawnParticles(particleType,
                        pos.x + offsetX, pos.y + offsetY, pos.z + offsetZ,
                        1, 0.0, 0.0, 0.0, 0.1);
            }
            
            // Update boss bar color
            BossBar.Color color = switch(phase) {
                case 1 -> BossBar.Color.RED;
                case 2 -> BossBar.Color.BLUE;
                case 3 -> BossBar.Color.GREEN;
                case 4 -> BossBar.Color.PURPLE;
                default -> BossBar.Color.WHITE;
            };
            
            this.bossBar.setColor(color);
            this.playSound(SoundEvents.ENTITY_ENDER_DRAGON_GROWL, 3.0f, 0.7f);
            this.heal(150.0f); // Significant heal on phase change
        }
    }
    
    private void performPhaseAbility() {
        if (this.getTarget() == null) return;
        
        switch (this.phase) {
            case 1 -> firePhase();
            case 2 -> waterPhase();
            case 3 -> earthPhase();
            case 4 -> ultimatePhase();
        }
    }
    
    // PHASE 1: FIRE (Pyros abilities)
    private void firePhase() {
        int attack = this.random.nextInt(2);
        if (attack == 0) {
            fireWave();
        } else {
            meteorShower();
        }
    }
    
    private void fireWave() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        for (int i = 0; i < 360; i += 8) {
            double angle = Math.toRadians(i);
            double radius = 4.0;
            double x = pos.x + Math.cos(angle) * radius;
            double z = pos.z + Math.sin(angle) * radius;
            
            serverWorld.spawnParticles(ParticleTypes.FLAME,
                    x, pos.y + 0.1, z, 8, 0.1, 0.1, 0.1, 0.05);
        }
        
        Box damageBox = new Box(pos.add(-4, -1, -4), pos.add(4, 2, 4));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().inFire(), 12.0f);
            entity.setOnFireFor(8);
        }
        
        this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 2.0f, 0.8f);
    }
    
    private void meteorShower() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d targetPos = this.getTarget().getBlockPos().toCenterPos();
        
        for (int i = 0; i < 7; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 12;
            double offsetZ = (this.random.nextDouble() - 0.5) * 12;
            
            BlockPos meteorPos = BlockPos.ofFloored(
                    targetPos.x + offsetX, targetPos.y + 15, targetPos.z + offsetZ);
            
            serverWorld.spawnParticles(ParticleTypes.LAVA,
                    meteorPos.getX(), meteorPos.getY(), meteorPos.getZ(),
                    15, 0.5, 8, 0.5, 0.1);
            
            Box damageBox = new Box(BlockPos.ofFloored(meteorPos.getX(), targetPos.y, meteorPos.getZ())).expand(3);
            List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                    LivingEntity.class, damageBox, entity -> entity != this);
            
            for (LivingEntity entity : entities) {
                entity.damage(serverWorld, this.getDamageSources().hotFloor(), 14.0f);
            }
        }
        
        this.playSound(SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 2.0f, 0.7f);
    }
    
    // PHASE 2: WATER (Leviathan abilities)
    private void waterPhase() {
        int attack = this.random.nextInt(2);
        if (attack == 0) {
            tidalWave();
        } else {
            iceSpikes();
        }
    }
    
    private void tidalWave() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        for (int radius = 1; radius <= 6; radius++) {
            final int r = radius;
            for (int i = 0; i < 360; i += 12) {
                double angle = Math.toRadians(i);
                double x = pos.x + Math.cos(angle) * r;
                double z = pos.z + Math.sin(angle) * r;
                
                serverWorld.spawnParticles(ParticleTypes.SPLASH,
                        x, pos.y + 1, z, 12, 0.2, 0.5, 0.2, 0.1);
            }
        }
        
        Box damageBox = new Box(pos.add(-6, -1, -6), pos.add(6, 3, 6));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().mobAttack(this), 13.0f);
            Vec3d direction = entity.getBlockPos().toCenterPos().subtract(pos).normalize();
            entity.setVelocity(direction.x * 2.0, 0.7, direction.z * 2.0);
            entity.velocityModified = true;
        }
        
        this.playSound(SoundEvents.ITEM_BUCKET_EMPTY, 2.0f, 0.6f);
    }
    
    private void iceSpikes() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d targetPos = this.getTarget().getBlockPos().toCenterPos();
        
        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(i * 36);
            double distance = 4.0;
            double x = targetPos.x + Math.cos(angle) * distance;
            double z = targetPos.z + Math.sin(angle) * distance;
            
            serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE,
                    x, targetPos.y, z, 25, 0.2, 2, 0.2, 0.05);
            
            Box damageBox = new Box(BlockPos.ofFloored(x, targetPos.y, z)).expand(1.5);
            List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                    LivingEntity.class, damageBox, entity -> entity != this);
            
            for (LivingEntity entity : entities) {
                entity.damage(serverWorld, this.getDamageSources().magic(), 14.0f);
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 120, 3));
            }
        }
        
        this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 2.0f, 0.4f);
    }
    
    // PHASE 3: EARTH (Goliath abilities)
    private void earthPhase() {
        int attack = this.random.nextInt(2);
        if (attack == 0) {
            earthquake();
        } else {
            earthFissures();
        }
    }
    
    private void earthquake() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        Box damageBox = new Box(pos.add(-12, -2, -12), pos.add(12, 3, 12));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            entity.damage(serverWorld, this.getDamageSources().magic(), 10.0f);
            
            double randX = (this.random.nextDouble() - 0.5) * 1.2;
            double randZ = (this.random.nextDouble() - 0.5) * 1.2;
            entity.setVelocity(randX, entity.getVelocity().y, randZ);
            entity.velocityModified = true;
            
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 140, 2));
        }
        
        serverWorld.spawnParticles(ParticleTypes.POOF,
                pos.x, pos.y, pos.z, 120, 6.0, 1.0, 6.0, 0.2);
        
        this.playSound(SoundEvents.ENTITY_WARDEN_SONIC_BOOM, 2.0f, 0.5f);
    }
    
    private void earthFissures() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        for (int dir = 0; dir < 8; dir++) {
            double angle = Math.toRadians(dir * 45);
            
            for (int dist = 1; dist <= 10; dist++) {
                double x = pos.x + Math.cos(angle) * dist;
                double z = pos.z + Math.sin(angle) * dist;
                
                serverWorld.spawnParticles(ParticleTypes.LAVA,
                        x, pos.y, z, 12, 0.2, 0.5, 0.2, 0.1);
                
                Box damageBox = new Box(BlockPos.ofFloored(x, pos.y, z)).expand(1.2);
                List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                        LivingEntity.class, damageBox, entity -> entity != this);
                
                for (LivingEntity entity : entities) {
                    entity.damage(serverWorld, this.getDamageSources().lava(), 16.0f);
                    entity.setOnFireFor(6);
                }
            }
        }
        
        this.playSound(SoundEvents.BLOCK_BASALT_BREAK, 2.0f, 0.3f);
    }
    
    // PHASE 4: ALL ELEMENTS COMBINED
    private void ultimatePhase() {
        comboCounter++;
        
        // Cycle through all elements rapidly
        int attack = comboCounter % 4;
        switch (attack) {
            case 0 -> elementalStorm(); // Custom ultimate attack
            case 1 -> fireWave();
            case 2 -> tidalWave();
            case 3 -> lightningStorm();
        }
    }
    
    private void elementalStorm() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();
        
        // Massive AOE combining all elements
        Box damageBox = new Box(pos.add(-10, -3, -10), pos.add(10, 6, 10));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);
        
        for (LivingEntity entity : entities) {
            // Fire damage
            entity.damage(serverWorld, this.getDamageSources().inFire(), 15.0f);
            entity.setOnFireFor(5);
            
            // Water knockback
            Vec3d direction = entity.getBlockPos().toCenterPos().subtract(pos).normalize();
            entity.setVelocity(direction.x * 1.5, 1.0, direction.z * 1.5);
            entity.velocityModified = true;
            
            // Earth slowness
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
            
            // Air levitation
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 1));
        }
        
        // All elemental particles
        serverWorld.spawnParticles(ParticleTypes.FLAME, pos.x, pos.y + 2, pos.z, 50, 4, 2, 4, 0.2);
        serverWorld.spawnParticles(ParticleTypes.SPLASH, pos.x, pos.y + 2, pos.z, 50, 4, 2, 4, 0.2);
        serverWorld.spawnParticles(ParticleTypes.POOF, pos.x, pos.y + 2, pos.z, 50, 4, 2, 4, 0.2);
        serverWorld.spawnParticles(ParticleTypes.CLOUD, pos.x, pos.y + 2, pos.z, 50, 4, 2, 4, 0.2);
        
        this.playSound(SoundEvents.ENTITY_WITHER_SPAWN, 2.0f, 1.0f);
    }
    
    private void lightningStorm() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d targetPos = this.getTarget().getBlockPos().toCenterPos();
        
        for (int i = 0; i < 5; i++) {
            double offsetX = (this.random.nextDouble() - 0.5) * 8;
            double offsetZ = (this.random.nextDouble() - 0.5) * 8;
            
            BlockPos strikePos = BlockPos.ofFloored(
                    targetPos.x + offsetX, targetPos.y, targetPos.z + offsetZ);
            
            serverWorld.spawnParticles(ParticleTypes.ELECTRIC_SPARK,
                    strikePos.getX(), strikePos.getY() + 12, strikePos.getZ(),
                    60, 0.2, 6, 0.2, 0.1);
            
            Box damageBox = new Box(strikePos).expand(2.5);
            List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                    LivingEntity.class, damageBox, entity -> entity != this);
            
            for (LivingEntity entity : entities) {
                entity.damage(serverWorld, this.getDamageSources().lightningBolt(), 18.0f);
                entity.setOnFireFor(4);
            }
        }
        
        this.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 2.0f, 1.0f);
    }
    
    private void spawnPhaseParticles() {
        if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
            net.minecraft.particle.SimpleParticleType particleType = switch(phase) {
                case 1 -> ParticleTypes.FLAME;
                case 2 -> ParticleTypes.DRIPPING_WATER;
                case 3 -> ParticleTypes.POOF;
                case 4 -> ParticleTypes.END_ROD;
                default -> ParticleTypes.PORTAL;
            };
            
            serverWorld.spawnParticles(particleType,
                    this.getX() + (this.random.nextDouble() - 0.5) * 3,
                    this.getY() + this.random.nextDouble() * 3,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 3,
                    1, 0, 0, 0, 0.05);
        }
    }
    
    @Override
    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(world, damageSource, causedByPlayer);
        
        // Drop Elemental Heart (guaranteed)
        ItemStack heart = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "elemental_heart"))
        );
        this.dropStack(world, heart);
        
        // Drop ALL essence types
        for (String essence : new String[]{"fire_essence", "water_essence", "earth_essence", "air_essence"}) {
            ItemStack essenceStack = new ItemStack(
                    Registries.ITEM.get(Identifier.of("elementaldimensions", essence)),
                    10 + this.random.nextInt(10)
            );
            this.dropStack(world, essenceStack);
        }
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
    
    @Override
    public boolean isFireImmune() {
        return true;
    }
    
    @Override
    public boolean canBreatheInWater() {
        return true;
    }
}
