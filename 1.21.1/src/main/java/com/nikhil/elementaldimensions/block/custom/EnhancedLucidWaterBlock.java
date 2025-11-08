package com.nikhil.elementaldimensions.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * Enhanced Lucid Water block with visual effects and collision behavior.
 * This is a decorative "liquid crystal" block rather than flowing fluid.
 */
public class EnhancedLucidWaterBlock extends Block {
    // Slightly lower height to appear "fluid-like"
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 14, 16);

    public EnhancedLucidWaterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Spawn glowing particles floating upward
        if (random.nextInt(5) == 0) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + 0.3 + random.nextDouble() * 0.5;
            double z = pos.getZ() + random.nextDouble();
            
            // Mix of glow and end rod particles for dreamy effect
            if (random.nextBoolean()) {
                world.addParticle(
                    ParticleTypes.GLOW,
                    x, y, z,
                    0, 0.02, 0
                );
            } else {
                world.addParticle(
                    ParticleTypes.END_ROD,
                    x, y, z,
                    (random.nextDouble() - 0.5) * 0.02,
                    0.05,
                    (random.nextDouble() - 0.5) * 0.02
                );
            }
        }

        // Occasional ambient sound
        if (random.nextInt(200) == 0) {
            world.playSound(
                null,
                pos,
                SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
                SoundCategory.BLOCKS,
                0.3F,
                random.nextFloat() * 0.4F + 0.8F
            );
        }

        // Dripping particles from above
        if (random.nextInt(10) == 0 && world.getBlockState(pos.up()).isAir()) {
            world.addParticle(
                ParticleTypes.DRIPPING_WATER,
                pos.getX() + random.nextDouble(),
                pos.getY() + 1.0,
                pos.getZ() + random.nextDouble(),
                0, 0, 0
            );
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        // Apply dreamy effects when entity touches the lucid water
        if (entity instanceof LivingEntity living) {
            // Night Vision effect for seeing in dreams
            living.addStatusEffect(new StatusEffectInstance(
                StatusEffects.NIGHT_VISION,
                100, // 5 seconds
                0,
                true,  // ambient
                false  // show particles
            ));

            // Slow Falling for dreamy float effect
            living.addStatusEffect(new StatusEffectInstance(
                StatusEffects.SLOW_FALLING,
                40, // 2 seconds
                0,
                true,
                false
            ));

            // Slight speed boost for swimming through dreams
            living.addStatusEffect(new StatusEffectInstance(
                StatusEffects.SPEED,
                20, // 1 second
                0,
                true,
                false
            ));

            // Spawn splash particles
            if (world.random.nextInt(5) == 0) {
                for (int i = 0; i < 3; i++) {
                    world.addParticle(
                        ParticleTypes.SPLASH,
                        entity.getX() + (world.random.nextDouble() - 0.5) * 0.5,
                        entity.getY() + 0.5,
                        entity.getZ() + (world.random.nextDouble() - 0.5) * 0.5,
                        (world.random.nextDouble() - 0.5) * 0.1,
                        0.1,
                        (world.random.nextDouble() - 0.5) * 0.1
                    );
                }
            }
        }

        // Slow down entity slightly (like water)
        entity.setVelocity(entity.getVelocity().multiply(0.9, 1.0, 0.9));
    }
}
