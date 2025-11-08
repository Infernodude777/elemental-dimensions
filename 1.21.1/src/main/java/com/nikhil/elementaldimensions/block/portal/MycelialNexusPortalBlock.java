package com.nikhil.elementaldimensions.block.portal;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import com.nikhil.elementaldimensions.world.dimension.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public class MycelialNexusPortalBlock extends DimensionalPortalBlock {

    public MycelialNexusPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected RegistryKey<World> getTargetDimension() {
        return ModDimensions.FUNGAL_DOMINION_WORLD;
    }

    @Override
    protected Block getFrameBlock() {
        return ModBlocks.FUNGAL_BARK;
    }
}
