package com.nikhil.elementaldimensions.block.portal;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

/**
 * Base portal block for dimensional travel
 */
public abstract class DimensionalPortalBlock extends Block {

    public DimensionalPortalBlock(Settings settings) {
        super(settings);
    }

    /**
     * Get the dimension this portal leads to
     */
    protected abstract RegistryKey<World> getTargetDimension();

    /**
     * Get the frame block for this portal
     */
    protected abstract Block getFrameBlock();

    /**
     * Get portal width (default 4)
     */
    public int getPortalWidth() {
        return 4;
    }

    /**
     * Get portal height (default 5)
     */
    public int getPortalHeight() {
        return 5;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity instanceof ServerPlayerEntity player) {
            ServerWorld serverWorld = (ServerWorld) world;
            RegistryKey<World> targetDimKey = getTargetDimension();
            ServerWorld targetWorld = serverWorld.getServer().getWorld(targetDimKey);

            if (targetWorld != null && targetWorld != serverWorld) {
                // Teleport the player
                teleportEntity(player, serverWorld, targetWorld, pos);
            } else if (world.random.nextInt(100) == 0) {
                // Play ambient sound if in same dimension (shouldn't happen for portals)
                world.playSound(null, pos, SoundEvents.BLOCK_PORTAL_AMBIENT,
                    SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.4F + 0.8F);
            }
        }
    }

    /**
     * Teleport entity to target dimension using new 1.21.x TeleportTarget API
     */
    protected void teleportEntity(ServerPlayerEntity player, ServerWorld fromWorld, ServerWorld toWorld, BlockPos portalPos) {
        // Play teleport sound in origin dimension
        fromWorld.playSound(null, portalPos, SoundEvents.BLOCK_PORTAL_TRAVEL,
            SoundCategory.BLOCKS, 1.0F, 1.0F);

        // Find safe spawn location in target dimension
        BlockPos targetPos = findSafeSpawnLocation(toWorld, portalPos);

        // Calculate destination position (center of block + slight elevation)
        Vec3d destPos = new Vec3d(targetPos.getX() + 0.5, targetPos.getY(), targetPos.getZ() + 0.5);

        // Keep player's velocity (or zero it for clean teleport)
        Vec3d velocity = Vec3d.ZERO; // player.getVelocity() for momentum preservation

        // Preserve player's rotation
        float yaw = player.getYaw();
        float pitch = player.getPitch();

        // Create dimension transition using TeleportTarget
        TeleportTarget target = new TeleportTarget(
            toWorld,
            destPos,
            velocity,
            yaw,
            pitch,
            TeleportTarget.NO_OP // Post-dimension transition callback (none needed)
        );

        // Perform the teleportation
        player.teleportTo(target);

        // Play arrival sound in destination dimension
        toWorld.playSound(null, targetPos, SoundEvents.BLOCK_PORTAL_TRAVEL,
            SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    protected BlockPos findSafeSpawnLocation(ServerWorld targetWorld, BlockPos originalPos) {
        // Try to spawn at same coordinates
        BlockPos targetPos = originalPos;

        // If not safe, find a safe spot nearby
        if (!isValidSpawnLocation(targetWorld, targetPos)) {
            // Search in expanding circle for safe location
            for (int radius = 1; radius <= 16; radius++) {
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos searchPos = originalPos.add(x, 0, z);
                        if (isValidSpawnLocation(targetWorld, searchPos)) {
                            return searchPos;
                        }
                    }
                }
            }
        }

        return targetPos;
    }

    protected boolean isValidSpawnLocation(World world, BlockPos pos) {
        // Check if there's ground below and air above
        return world.getBlockState(pos.down()).isSolidBlock(world, pos.down()) &&
               world.getBlockState(pos).isAir() &&
               world.getBlockState(pos.up()).isAir();
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Add portal particles
        if (random.nextInt(100) == 0) {
            world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS,
                0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        // Spawn particles
        for (int i = 0; i < 4; i++) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();
            double velocityX = (random.nextDouble() - 0.5) * 0.5;
            double velocityY = (random.nextDouble() - 0.5) * 0.5;
            double velocityZ = (random.nextDouble() - 0.5) * 0.5;

            world.addParticle(ParticleTypes.PORTAL, x, y, z, velocityX, velocityY, velocityZ);
        }
    }

    /**
     * Check if portal frame is valid
     */
    public boolean isValidFrame(WorldAccess world, BlockPos pos) {
        int width = getPortalWidth();
        int height = getPortalHeight();
        Block frame = getFrameBlock();

        // Check bottom frame
        for (int x = 0; x < width; x++) {
            if (!world.getBlockState(pos.add(x, 0, 0)).isOf(frame)) {
                return false;
            }
        }

        // Check top frame
        for (int x = 0; x < width; x++) {
            if (!world.getBlockState(pos.add(x, height - 1, 0)).isOf(frame)) {
                return false;
            }
        }

        // Check left side
        for (int y = 1; y < height - 1; y++) {
            if (!world.getBlockState(pos.add(0, y, 0)).isOf(frame)) {
                return false;
            }
        }

        // Check right side
        for (int y = 1; y < height - 1; y++) {
            if (!world.getBlockState(pos.add(width - 1, y, 0)).isOf(frame)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Try to create portal at position
     */
    public boolean tryCreatePortal(WorldAccess world, BlockPos pos) {
        if (!isValidFrame(world, pos)) {
            return false;
        }

        int width = getPortalWidth();
        int height = getPortalHeight();

        // Fill inside with portal blocks
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                world.setBlockState(pos.add(x, y, 0), this.getDefaultState(), 3);
            }
        }

        // Play activation sound
        world.playSound(null, pos, SoundEvents.BLOCK_PORTAL_TRIGGER,
            SoundCategory.BLOCKS, 1.0F, 1.0F);

        return true;
    }
}
