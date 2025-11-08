package com.nikhil.elementaldimensions.block.portal;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import com.nikhil.elementaldimensions.world.dimension.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public class CosmicVoidPortalBlock extends DimensionalPortalBlock {

    public CosmicVoidPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected RegistryKey<World> getTargetDimension() {
        return ModDimensions.VOID_WORLD;
    }

    @Override
    protected Block getFrameBlock() {
        return ModBlocks.VOID_STONE;
    }
}
