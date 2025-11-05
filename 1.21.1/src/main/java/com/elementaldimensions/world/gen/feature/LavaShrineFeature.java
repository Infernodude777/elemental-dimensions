package com.elementaldimensions.world.gen.feature;

import com.elementaldimensions.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates a lava shrine structure procedurally in the Firelands dimension
 */
public class LavaShrineFeature extends Feature<DefaultFeatureConfig> {
	
	public LavaShrineFeature() {
		super(DefaultFeatureConfig.CODEC);
	}
	
	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		
		// Build a simple pyramid shrine
		int height = 12;
		int baseSize = 9;
		
		for (int y = 0; y < height; y++) {
			int size = baseSize - (y / 2);
			if (size < 3) size = 3;
			
			for (int x = -size; x <= size; x++) {
				for (int z = -size; z <= size; z++) {
					BlockPos buildPos = pos.add(x, y, z);
					
					// Outer walls
					if (Math.abs(x) == size || Math.abs(z) == size) {
						if (y < height - 3) {
							world.setBlockState(buildPos, ModBlocks.MAGMATIC_BRICK.getDefaultState(), 3);
						} else {
							world.setBlockState(buildPos, ModBlocks.SCORCHED_STONE.getDefaultState(), 3);
						}
					}
					// Inner hollow space with lava pool at bottom
					else if (y == 0) {
						world.setBlockState(buildPos, Blocks.LAVA.getDefaultState(), 3);
					}
					// Place fire furnace core at center top
					else if (y == height - 1 && x == 0 && z == 0) {
						world.setBlockState(buildPos, ModBlocks.FIRE_FURNACE_CORE.getDefaultState(), 3);
					}
				}
			}
		}
		
		// Add lava crystal ore pillars
		for (int i = 0; i < 4; i++) {
			int xOffset = (i % 2 == 0) ? 4 : -4;
			int zOffset = (i / 2 == 0) ? 4 : -4;
			
			for (int y = 0; y < 6; y++) {
				BlockPos pillarPos = pos.add(xOffset, y, zOffset);
				world.setBlockState(pillarPos, ModBlocks.LAVA_CRYSTAL_ORE.getDefaultState(), 3);
			}
		}
		
		return true;
	}
}
