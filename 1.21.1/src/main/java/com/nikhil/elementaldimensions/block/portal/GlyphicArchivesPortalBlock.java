package com.nikhil.elementaldimensions.block.portal;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import com.nikhil.elementaldimensions.world.dimension.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public class GlyphicArchivesPortalBlock extends DimensionalPortalBlock {

    public GlyphicArchivesPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected RegistryKey<World> getTargetDimension() {
        return ModDimensions.FORGOTTEN_ARCHIVE_WORLD;
    }

    @Override
    protected Block getFrameBlock() {
        return ModBlocks.GLYPH_BLOCK;
    }
}
