package com.nikhil.elementaldimensions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Base class for dimensional fluid blocks with custom effects
 */
public class DimensionalFluidBlock extends FluidBlock {
    private final ParticleEffect particleType;
    private final StatusEffectInstance[] effects;

    public DimensionalFluidBlock(FlowableFluid fluid, Settings settings,
                                ParticleEffect particle, StatusEffectInstance... effects) {
        super(fluid, settings);
        this.particleType = particle;
        this.effects = effects;
    }

    public DimensionalFluidBlock(FlowableFluid fluid, Settings settings) {
        this(fluid, settings, ParticleTypes.DRIPPING_WATER);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (particleType != null && random.nextInt(10) == 0) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();
            world.addParticle(particleType, x, y, z, 0, 0.02, 0);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living && effects != null) {
            for (StatusEffectInstance effect : effects) {
                living.addStatusEffect(new StatusEffectInstance(
                    effect.getEffectType(),
                    effect.getDuration(),
                    effect.getAmplifier(),
                    true,
                    false
                ));
            }
        }
    }
}
