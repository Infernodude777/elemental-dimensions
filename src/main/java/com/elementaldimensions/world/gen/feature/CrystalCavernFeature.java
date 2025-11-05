package com.elementaldimensions.world.gen.feature;

import com.elementaldimensions.block.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

/**
 * Generates crystal caverns in Terra Depths
 */
public class CrystalCavernFeature extends Feature<DefaultFeatureConfig> {
	
	public CrystalCavernFeature() {
		super(DefaultFeatureConfig.CODEC);
	}
	
	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		
		// Create hollow cavern
		int radius = 10;
		int height = 15;
		
		for (int y = 0; y < height; y++) {
			int yRadius = radius - Math.abs(y - height/2) / 2;
			for (int x = -yRadius; x <= yRadius; x++) {
				for (int z = -yRadius; z <= yRadius; z++) {
					if (x*x + z*z <= yRadius*yRadius) {
						BlockPos buildPos = pos.add(x, y, z);
						
						// Hollow out center
						world.setBlockState(buildPos, net.minecraft.block.Blocks.AIR.getDefaultState(), 3);
						
						// Add crystal clusters on walls
						if (x*x + z*z >= (yRadius-1)*(yRadius-1) && world.getRandom().nextInt(4) == 0) {
							world.setBlockState(buildPos, ModBlocks.CRYSTAL_CLUSTER.getDefaultState(), 3);
						}
					}
				}
			}
		}
		
		// Floor with moss
		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				if (x*x + z*z <= radius*radius) {
					world.setBlockState(pos.add(x, 0, z), ModBlocks.ROOTED_EARTH.getDefaultState(), 3);
				}
			}
		}
		
		// Central pillar with terra ore
		for (int y = 0; y < height; y++) {
			world.setBlockState(pos.add(0, y, 0), ModBlocks.TERRA_ORE.getDefaultState(), 3);
		}
		
		return true;
	}
}
