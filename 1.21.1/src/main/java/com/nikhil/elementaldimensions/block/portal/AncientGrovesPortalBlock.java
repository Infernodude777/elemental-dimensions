package com.nikhil.elementaldimensions.block.portal;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import com.nikhil.elementaldimensions.world.dimension.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public class AncientGrovesPortalBlock extends DimensionalPortalBlock {

    public AncientGrovesPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected RegistryKey<World> getTargetDimension() {
        return ModDimensions.GLOOMY_CAVERNS_WORLD;
    }

    @Override
    protected Block getFrameBlock() {
        return ModBlocks.REVERIE_BLOCK;
    }
}
