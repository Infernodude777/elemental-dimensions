package com.elementaldimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

/**
 * Elemental Forge - Functional crafting station
 * Used for creating advanced elemental items
 */
public class ElementalForgeBlock extends Block {
	
	public ElementalForgeBlock(Settings settings) {
		super(settings
			.luminance(state -> 11)
			.sounds(BlockSoundGroup.ANVIL)
			.requiresTool()
			.strength(5.0f, 1200.0f)
		);
	}
	
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		
		// Flame particles
		if (random.nextInt(4) == 0 && world instanceof ServerWorld serverWorld) {
			double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
			double y = pos.getY() + 0.8;
			double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.6;
			
			serverWorld.spawnParticles(
				ParticleTypes.FLAME, x, y, z, 1, 0.0, 0.1, 0.0
			, 0.0);
		}
		
		// Occasional lava drip effect
		if (random.nextInt(10) == 0 && world instanceof ServerWorld serverWorld) {
			double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.4;
			double y = pos.getY() + 0.1;
			double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.4;
			
			serverWorld.spawnParticles(
				ParticleTypes.LAVA, x, y, z, 1, 0.0, 0.0, 0.0
			, 0.0);
		}
	}
}
