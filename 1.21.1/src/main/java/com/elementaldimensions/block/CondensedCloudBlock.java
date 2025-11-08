package com.elementaldimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Condensed Cloud Block - Decorative skyreach block
 * Soft, fluffy appearance with sparkles
 */
public class CondensedCloudBlock extends Block {

	public CondensedCloudBlock(Settings settings) {
		super(settings
			.luminance(state -> 6)
			.sounds(BlockSoundGroup.WOOL)
			.strength(0.3f)
		);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);

		if (random.nextInt(8) == 0) {
			double x = pos.getX() + random.nextDouble();
			double y = pos.getY() + random.nextDouble();
			double z = pos.getZ() + random.nextDouble();

			world.addParticle(
				ParticleTypes.SNOWFLAKE,
				x, y, z,
				(random.nextDouble() - 0.5) * 0.02,
				-0.01,
				(random.nextDouble() - 0.5) * 0.02
			);
		}
	}
}
