package com.elementaldimensions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Reinforced Aqua Glass - Decorative aquatica block
 * Transparent with bubble particles
 */
public class ReinforcedAquaGlassBlock extends TransparentBlock {

	public ReinforcedAquaGlassBlock(Settings settings) {
		super(settings
			.luminance(state -> 8)
			.sounds(BlockSoundGroup.GLASS)
			.nonOpaque()
		);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);

		if (random.nextInt(5) == 0) {
			double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.5;
			double y = pos.getY() + random.nextDouble();
			double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.5;

			world.addParticle(
				ParticleTypes.BUBBLE,
				x, y, z,
				0.0, 0.1, 0.0
			);
		}
	}
}
