package com.elementaldimensions.world.gen.feature;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates a simple lava shrine structure in the Firelands dimension
 */
public class LavaShrineFeature extends Feature<DefaultFeatureConfig> {

	public LavaShrineFeature() {
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();

		// Generate a simple 7x7x7 shrine
		int size = 3;

		// Build floor
		for (int x = -size; x <= size; x++) {
			for (int z = -size; z <= size; z++) {
				world.setBlockState(pos.add(x, 0, z), ModBlocks.INFERNO_STONE.getDefaultState(), 3);
			}
		}

		// Build hollow walls
		for (int y = 1; y <= size + 2; y++) {
			for (int x = -size; x <= size; x++) {
				for (int z = -size; z <= size; z++) {
					// Only place blocks on the edges
					if (x == -size || x == size || z == -size || z == size) {
						if (y < size + 2 || (x % 2 == 0 && z % 2 == 0)) { // Leave gaps in roof
							world.setBlockState(pos.add(x, y, z), ModBlocks.INFERNO_STONE.getDefaultState(), 3);
						}
					}
				}
			}
		}

		// Add lava pool in center
		world.setBlockState(pos.add(0, 1, 0), Blocks.LAVA.getDefaultState(), 3);

		// Add chest in center
		world.setBlockState(pos.add(0, 2, 0), Blocks.CHEST.getDefaultState(), 3);

		// Add glowing ore accents
		world.setBlockState(pos.add(-size, 2, 0), ModBlocks.BLAZING_ORE.getDefaultState(), 3);
		world.setBlockState(pos.add(size, 2, 0), ModBlocks.BLAZING_ORE.getDefaultState(), 3);
		world.setBlockState(pos.add(0, 2, -size), ModBlocks.BLAZING_ORE.getDefaultState(), 3);
		world.setBlockState(pos.add(0, 2, size), ModBlocks.BLAZING_ORE.getDefaultState(), 3);

		return true;
	}
}
