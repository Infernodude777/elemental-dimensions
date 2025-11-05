package com.elementaldimensions.world.gen.feature;

import com.elementaldimensions.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates a coral palace structure in Aquatica dimension
 */
public class CoralPalaceFeature extends Feature<DefaultFeatureConfig> {
	
	public CoralPalaceFeature() {
		super(DefaultFeatureConfig.CODEC);
	}
	
	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		
		// Build underwater palace with coral blocks
		int width = 15;
		int height = 10;
		int depth = 15;
		
		// Main structure
		for (int y = 0; y < height; y++) {
			for (int x = -width/2; x <= width/2; x++) {
				for (int z = -depth/2; z <= depth/2; z++) {
					BlockPos buildPos = pos.add(x, y, z);
					
					// Outer walls
					if (Math.abs(x) == width/2 || Math.abs(z) == depth/2) {
						if (y == 0 || y == height - 1) {
							world.setBlockState(buildPos, ModBlocks.CORALITE.getDefaultState(), 3);
						} else if (y % 2 == 0) {
							world.setBlockState(buildPos, ModBlocks.PEARLSTONE.getDefaultState(), 3);
						} else {
							world.setBlockState(buildPos, ModBlocks.ABYSSAL_GLASS.getDefaultState(), 3);
						}
					}
					// Floor and ceiling
					else if (y == 0 || y == height - 1) {
						world.setBlockState(buildPos, ModBlocks.CORALITE.getDefaultState(), 3);
					}
				}
			}
		}
		
		// Add towers at corners
		int[] cornerOffsets = {-width/2, width/2};
		for (int xOff : cornerOffsets) {
			for (int zOff : cornerOffsets) {
				for (int y = 0; y < height + 5; y++) {
					BlockPos towerPos = pos.add(xOff, y, zOff);
					if (y < height + 4) {
						world.setBlockState(towerPos, ModBlocks.PEARLSTONE.getDefaultState(), 3);
					} else {
						world.setBlockState(towerPos, ModBlocks.OCEANIC_CRYSTAL_ORE.getDefaultState(), 3);
					}
				}
			}
		}
		
		return true;
	}
}
