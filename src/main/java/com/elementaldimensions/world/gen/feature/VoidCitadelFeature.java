package com.elementaldimensions.world.gen.feature;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates a simple void citadel structure in Void dimension
 */
public class VoidCitadelFeature extends Feature<DefaultFeatureConfig> {

	public VoidCitadelFeature() {
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();

		int size = 3;

		// Build floor with void rift blocks
		for (int x = -size; x <= size; x++) {
			for (int z = -size; z <= size; z++) {
				world.setBlockState(pos.add(x, 0, z), ModBlocks.VOID_RIFT_BLOCK.getDefaultState(), 3);
			}
		}

		// Build hollow walls
		for (int y = 1; y <= size + 2; y++) {
			for (int x = -size; x <= size; x++) {
				for (int z = -size; z <= size; z++) {
					if (x == -size || x == size || z == -size || z == size) {
						if (y < size + 2 || (x % 2 == 0 && z % 2 == 0)) {
							world.setBlockState(pos.add(x, y, z), ModBlocks.VOID_RIFT_BLOCK.getDefaultState(), 3);
						}
					}
				}
			}
		}

		// Add chest
		world.setBlockState(pos.add(0, 1, 0), Blocks.CHEST.getDefaultState(), 3);

		// Add void rift accents at cardinal directions
		world.setBlockState(pos.add(-size, 2, 0), ModBlocks.VOID_RIFT_BLOCK.getDefaultState(), 3);
		world.setBlockState(pos.add(size, 2, 0), ModBlocks.VOID_RIFT_BLOCK.getDefaultState(), 3);
		world.setBlockState(pos.add(0, 2, -size), ModBlocks.VOID_RIFT_BLOCK.getDefaultState(), 3);
		world.setBlockState(pos.add(0, 2, size), ModBlocks.VOID_RIFT_BLOCK.getDefaultState(), 3);

		return true;
	}
}
