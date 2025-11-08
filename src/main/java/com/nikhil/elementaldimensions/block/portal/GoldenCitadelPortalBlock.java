package com.nikhil.elementaldimensions.block.portal;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import com.nikhil.elementaldimensions.world.dimension.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public class GoldenCitadelPortalBlock extends DimensionalPortalBlock {

    public GoldenCitadelPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected RegistryKey<World> getTargetDimension() {
        return ModDimensions.ASTRAL_FRONTIER_WORLD;
    }

    @Override
    protected Block getFrameBlock() {
        return ModBlocks.GOLDEN_PILLAR;
    }
}
