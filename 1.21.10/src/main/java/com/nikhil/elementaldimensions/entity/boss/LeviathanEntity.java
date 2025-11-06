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
 * Leviathan - Boss of the Aquatic Realm
 *
 * Phase 1 (100%-70% HP): Tidal waves and water jets
 * Phase 2 (70%-30% HP): Ice spikes, freezing aura, water prison
 * Phase 3 (30%-0% HP): Tsunami rage - massive water attacks
 */
public class LeviathanEntity extends HostileEntity {

    private final ServerBossBar bossBar;
    private int phase = 1;
    private int attackCooldown = 0;

    public LeviathanEntity(EntityType<? extends LeviathanEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 500;

        this.bossBar = new ServerBossBar(
                Text.literal("Leviathan the Tidal Terror"),
                BossBar.Color.BLUE,
                BossBar.Style.NOTCHED_10
        );
        this.bossBar.setDarkenSky(true);
    }

    public static DefaultAttributeContainer.Builder createLeviathanAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 500.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 18.0)
                .add(EntityAttributes.GENERIC_ARMOR, 12.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
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
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            updatePhase();

            if (this.age % 10 == 0) {
                spawnWaterParticles();
            }

            if (attackCooldown > 0) {
                attackCooldown--;
            } else {
                performPhaseAbility();
                attackCooldown = 50;
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

            // Water explosion effect
            for (int i = 0; i < 100; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 6;
                double offsetY = this.random.nextDouble() * 4;
                double offsetZ = (this.random.nextDouble() - 0.5) * 6;

                serverWorld.spawnParticles(ParticleTypes.SPLASH,
                        pos.x + offsetX, pos.y + offsetY, pos.z + offsetZ,
                        1, 0, 0, 0, 0);
            }

            this.playSound(SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, 2.0f, 0.8f);
            this.heal(75.0f);
        }
    }

    private void performPhaseAbility() {
        if (this.getTarget() == null) return;

        switch (this.phase) {
            case 1 -> tidalWave();
            case 2 -> iceSpikes();
            case 3 -> tsunami();
        }
    }

    private void tidalWave() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();

        // Create wave effect
        for (int radius = 1; radius <= 5; radius++) {
            final int r = radius;
            for (int i = 0; i < 360; i += 15) {
                double angle = Math.toRadians(i);
                double x = pos.x + Math.cos(angle) * r;
                double z = pos.z + Math.sin(angle) * r;

                serverWorld.spawnParticles(ParticleTypes.SPLASH,
                        x, pos.y + 1, z,
                        10, 0.2, 0.5, 0.2, 0.1);
            }
        }

        // Damage and knockback
        Box damageBox = new Box(pos.add(-5, -1, -5), pos.add(5, 3, 5));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);

        for (LivingEntity entity : entities) {
            entity.damage(this.getDamageSources().mobAttack(this), 10.0f);

            // Knockback away from boss
            Vec3d direction = entity.getBlockPos().toCenterPos().subtract(pos).normalize();
            entity.setVelocity(direction.x * 1.5, 0.5, direction.z * 1.5);
            entity.velocityModified = true;
        }

        this.playSound(SoundEvents.ITEM_BUCKET_EMPTY, 2.0f, 0.7f);
    }

    private void iceSpikes() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d targetPos = this.getTarget().getBlockPos().toCenterPos();

        // Create ice spike pattern around target
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            double distance = 3.0;
            double x = targetPos.x + Math.cos(angle) * distance;
            double z = targetPos.z + Math.sin(angle) * distance;

            // Ice particles
            serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE,
                    x, targetPos.y, z,
                    20, 0.2, 2, 0.2, 0.05);

            // Damage at spike location
            BlockPos spikePos = BlockPos.ofFloored(x, targetPos.y, z);
            Box damageBox = new Box(spikePos).expand(1.5);
            List<LivingEntity> entities = serverWorld.getEntitiesByClass(
                    LivingEntity.class, damageBox, entity -> entity != this);

            for (LivingEntity entity : entities) {
                entity.damage(this.getDamageSources().magic(), 12.0f);
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
            }
        }

        this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 2.0f, 0.5f);
    }

    private void tsunami() {
        ServerWorld serverWorld = (ServerWorld) this.getEntityWorld();
        Vec3d pos = this.getBlockPos().toCenterPos();

        // Massive water explosion
        Box damageBox = new Box(pos.add(-8, -2, -8), pos.add(8, 5, 8));
        List<LivingEntity> entities = this.getEntityWorld().getEntitiesByClass(
                LivingEntity.class, damageBox, entity -> entity != this);

        for (LivingEntity entity : entities) {
            entity.damage(this.getDamageSources().drown(), 25.0f);

            // Strong upward knockback (simulating being caught in tsunami)
            Vec3d direction = entity.getBlockPos().toCenterPos().subtract(pos).normalize();
            entity.setVelocity(direction.x * 2.5, 1.5, direction.z * 2.5);
            entity.velocityModified = true;

            // Apply drowning effect
            entity.setAir(0);
        }

        // Massive particle effect
        serverWorld.spawnParticles(ParticleTypes.SPLASH,
                pos.x, pos.y + 2, pos.z,
                200, 4, 2, 4, 0.3);

        this.playSound(SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 2.0f, 0.5f);
    }

    private void spawnWaterParticles() {
        if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.DRIPPING_WATER,
                    this.getX() + (this.random.nextDouble() - 0.5) * 2,
                    this.getY() + this.random.nextDouble() * 2,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 2,
                    1, 0, 0, 0, 0);
        }
    }

    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(damageSource, causedByPlayer);

        // Drop Aquatic Heart (guaranteed)
        ItemStack heart = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "aquatic_heart"))
        );
        this.dropStack(heart);

        // Additional water essence drops
        ItemStack essence = new ItemStack(
                Registries.ITEM.get(Identifier.of("elementaldimensions", "water_essence")),
                5 + this.random.nextInt(5)
        );
        this.dropStack(essence);
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

    // canBreatheInWater is final in 1.21 - use canBreatheUnderwater() instead if needed
}
