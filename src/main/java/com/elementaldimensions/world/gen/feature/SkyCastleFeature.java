package com.elementaldimensions.world.gen.feature;

import com.elementaldimensions.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates a floating sky castle in Skyreach Peaks
 */
public class SkyCastleFeature extends Feature<DefaultFeatureConfig> {
	
	public SkyCastleFeature() {
		super(DefaultFeatureConfig.CODEC);
	}
	
	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		
		// Build floating island base
		int radius = 12;
		for (int y = -3; y <= 0; y++) {
			int currentRadius = radius - Math.abs(y);
			for (int x = -currentRadius; x <= currentRadius; x++) {
				for (int z = -currentRadius; z <= currentRadius; z++) {
					if (x*x + z*z <= currentRadius*currentRadius) {
						BlockPos buildPos = pos.add(x, y, z);
						if (y == -3) {
							world.setBlockState(buildPos, ModBlocks.SKYROCK.getDefaultState(), 3);
						} else {
							world.setBlockState(buildPos, ModBlocks.CLOUD_SOIL.getDefaultState(), 3);
						}
					}
				}
			}
		}
		
		// Build castle structure
		int castleSize = 8;
		int castleHeight = 12;
		
		for (int y = 1; y < castleHeight; y++) {
			for (int x = -castleSize; x <= castleSize; x++) {
				for (int z = -castleSize; z <= castleSize; z++) {
					BlockPos buildPos = pos.add(x, y, z);
					
					// Walls
					if (Math.abs(x) == castleSize || Math.abs(z) == castleSize) {
						if (y % 3 == 0) {
							world.setBlockState(buildPos, ModBlocks.WIND_CRYSTAL.getDefaultState(), 3);
						} else {
							world.setBlockState(buildPos, ModBlocks.PETAL_TILE.getDefaultState(), 3);
						}
					}
					// Battlements
					else if (y == castleHeight - 1 && (x % 2 == 0 || z % 2 == 0)) {
						world.setBlockState(buildPos, ModBlocks.SKYROCK.getDefaultState(), 3);
					}
				}
			}
		}
		
		// Add towers
		int[] towerOffsets = {-castleSize, castleSize};
		for (int xOff : towerOffsets) {
			for (int zOff : towerOffsets) {
				for (int y = 1; y < castleHeight + 6; y++) {
					BlockPos towerPos = pos.add(xOff, y, zOff);
					world.setBlockState(towerPos, ModBlocks.STRATUS_SHARD.getDefaultState(), 3);
				}
				// Tower tops
				world.setBlockState(pos.add(xOff, castleHeight + 6, zOff), ModBlocks.WIND_CRYSTAL.getDefaultState(), 3);
			}
		}
		
		return true;
	}
}
