package com.elementaldimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Void Crystal Cluster - Decorative void/celestial block
 * Dark, mysterious with portal particles
 */
public class VoidCrystalClusterBlock extends Block {

	public VoidCrystalClusterBlock(Settings settings) {
		super(settings
			.luminance(state -> 14)
			.sounds(BlockSoundGroup.AMETHYST_CLUSTER)
			.requiresTool()
		);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);

		if (random.nextInt(6) == 0) {
			double x = pos.getX() + random.nextDouble();
			double y = pos.getY() + random.nextDouble();
			double z = pos.getZ() + random.nextDouble();

			world.addParticle(
				ParticleTypes.PORTAL,
				x, y, z,
				(random.nextDouble() - 0.5) * 0.5,
				(random.nextDouble() - 0.5) * 0.5,
				(random.nextDouble() - 0.5) * 0.5
			);
		}

		// Rare sparkle effect
		if (random.nextInt(15) == 0) {
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
