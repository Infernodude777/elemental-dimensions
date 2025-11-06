package com.elementaldimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Crystal Pillar - Decorative terra depths block
 * Glowing crystal structure
 */
public class CrystalPillarBlock extends Block {

	public CrystalPillarBlock(Settings settings) {
		super(settings
			.luminance(state -> 12)
			.sounds(BlockSoundGroup.AMETHYST_BLOCK)
			.requiresTool()
		);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);

		if (random.nextInt(10) == 0) {
			double x = pos.getX() + random.nextDouble();
			double y = pos.getY() + random.nextDouble();
			double z = pos.getZ() + random.nextDouble();

			world.addParticle(
				ParticleTypes.END_ROD,
				x, y, z,
				0.0, 0.0, 0.0
			);
		}
	}
}
