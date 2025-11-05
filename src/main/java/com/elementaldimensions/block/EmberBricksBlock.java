package com.elementaldimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Glowing Ember Bricks - Decorative firelands block
 * Emits particles and light
 */
public class EmberBricksBlock extends Block {
	
	public EmberBricksBlock(Settings settings) {
		super(settings
			.luminance(state -> 10)
			.sounds(BlockSoundGroup.NETHER_BRICKS)
		);
	}
	
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		
		if (random.nextInt(3) == 0) {
			double x = pos.getX() + random.nextDouble();
			double y = pos.getY() + random.nextDouble();
			double z = pos.getZ() + random.nextDouble();
			
			world.addParticle(
				ParticleTypes.FLAME,
				x, y, z,
				0.0, 0.05, 0.0
			);
		}
	}
}
