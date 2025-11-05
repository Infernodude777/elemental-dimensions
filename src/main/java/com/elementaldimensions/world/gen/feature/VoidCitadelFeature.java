package com.elementaldimensions.world.gen.feature;

import com.elementaldimensions.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates void citadel in Void Realm
 */
public class VoidCitadelFeature extends Feature<DefaultFeatureConfig> {
	
	public VoidCitadelFeature() {
		super(DefaultFeatureConfig.CODEC);
	}
	
	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		
		// Build massive citadel base
		int baseSize = 20;
		int citadelHeight = 30;
		
		// Foundation
		for (int x = -baseSize; x <= baseSize; x++) {
			for (int z = -baseSize; z <= baseSize; z++) {
				for (int y = -3; y <= 0; y++) {
					world.setBlockState(pos.add(x, y, z), ModBlocks.VOIDSTONE.getDefaultState(), 3);
				}
			}
		}
		
		// Main tower
		int towerSize = 12;
		for (int y = 0; y < citadelHeight; y++) {
			int currentSize = towerSize - (y / 6);
			if (currentSize < 5) currentSize = 5;
			
			for (int x = -currentSize; x <= currentSize; x++) {
				for (int z = -currentSize; z <= currentSize; z++) {
					BlockPos buildPos = pos.add(x, y, z);
					
					// Outer walls
					if (Math.abs(x) == currentSize || Math.abs(z) == currentSize) {
						if (y % 5 == 0) {
							world.setBlockState(buildPos, ModBlocks.VOID_CRYSTAL_ORE.getDefaultState(), 3);
						} else {
							world.setBlockState(buildPos, ModBlocks.NULLROCK.getDefaultState(), 3);
						}
					}
					// Corner pillars with void crystals
					else if (Math.abs(x) == currentSize - 1 && Math.abs(z) == currentSize - 1) {
						world.setBlockState(buildPos, ModBlocks.VOID_CRYSTAL.getDefaultState(), 3);
					}
				}
			}
		}
		
		// Add spires at corners
		int[] spireOffsets = {-8, 8};
		for (int xOff : spireOffsets) {
			for (int zOff : spireOffsets) {
				for (int y = 0; y < citadelHeight + 10; y++) {
					world.setBlockState(pos.add(xOff, y, zOff), ModBlocks.RIFT_BLOCK.getDefaultState(), 3);
					if (y % 4 == 0) {
						world.setBlockState(pos.add(xOff, y, zOff), ModBlocks.VOID_CRYSTAL.getDefaultState(), 3);
					}
				}
			}
		}
		
		// Top portal platform
		for (int x = -3; x <= 3; x++) {
			for (int z = -3; z <= 3; z++) {
				world.setBlockState(pos.add(x, citadelHeight, z), ModBlocks.DARK_MATTER_BLOCK.getDefaultState(), 3);
			}
		}
		
		return true;
	}
}
