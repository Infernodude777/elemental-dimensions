package com.elementaldimensions.world.gen.feature;

import com.elementaldimensions.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates star temples in Celestial dimension
 */
public class StarTempleFeature extends Feature<DefaultFeatureConfig> {

	public StarTempleFeature() {
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();

		// Build floating temple platform
		int platformRadius = 10;
		for (int x = -platformRadius; x <= platformRadius; x++) {
			for (int z = -platformRadius; z <= platformRadius; z++) {
				if (x*x + z*z <= platformRadius*platformRadius) {
					world.setBlockState(pos.add(x, 0, z), ModBlocks.CELESTIAL_STONE.getDefaultState(), 3);
					if ((x*x + z*z) % 10 == 0) {
						world.setBlockState(pos.add(x, 0, z), ModBlocks.COSMIC_SHARD.getDefaultState(), 3);
					}
				}
			}
		}

		// Build temple walls
		int templeSize = 7;
		int templeHeight = 10;

		for (int y = 1; y < templeHeight; y++) {
			for (int x = -templeSize; x <= templeSize; x++) {
				for (int z = -templeSize; z <= templeSize; z++) {
					BlockPos buildPos = pos.add(x, y, z);

					// Outer walls with windows
					if (Math.abs(x) == templeSize || Math.abs(z) == templeSize) {
						if (y > 2 && y < 8 && (x + z) % 3 == 0) {
							world.setBlockState(buildPos, Blocks.AIR.getDefaultState(), 3);
						} else {
							world.setBlockState(buildPos, ModBlocks.ASTRAL_TILE.getDefaultState(), 3);
						}
					}
					// Roof
					else if (y == templeHeight - 1) {
						world.setBlockState(buildPos, ModBlocks.CELESTIAL_STONE.getDefaultState(), 3);
					}
				}
			}
		}

		// Add cosmic shard pillars
		for (int i = -4; i <= 4; i += 4) {
			for (int j = -4; j <= 4; j += 4) {
				for (int y = 1; y < templeHeight; y++) {
					world.setBlockState(pos.add(i, y, j), ModBlocks.COSMIC_SHARD.getDefaultState(), 3);
				}
			}
		}

		// Central altar
		for (int y = 1; y <= 3; y++) {
			world.setBlockState(pos.add(0, y, 0), ModBlocks.STARLIGHT_MOSS.getDefaultState(), 3);
		}

		return true;
	}
}
