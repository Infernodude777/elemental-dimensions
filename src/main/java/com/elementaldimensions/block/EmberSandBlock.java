package com.elementaldimensions.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.BlockView;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class EmberSandBlock extends FallingBlock {

	public static final MapCodec<EmberSandBlock> CODEC = createCodec(EmberSandBlock::new);

	public EmberSandBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public MapCodec<EmberSandBlock> getCodec() {
		return CODEC;
	}

	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		super.onSteppedOn(world, pos, state, entity);
		if (world instanceof ServerWorld serverWorld) {
			serverWorld.spawnParticles(ParticleTypes.FLAME,
					pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
					3, 0.25, 0.1, 0.25, 0.01);
		}
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		// Particle effects handled by client-side rendering
	}

	@Override
	public int getColor(BlockState state, BlockView world, BlockPos pos) {
		return 0xDB7B3B; // Orange/ember color
	}
}
