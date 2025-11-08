package com.nikhil.elementaldimensions.world.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

/**
 * Configuration for simple structures.
 * Defines the blocks and size for procedurally generated structures.
 */
public record SimpleStructureConfig(BlockState primaryBlock, BlockState secondaryBlock, int size)
        implements FeatureConfig {

    public static final Codec<SimpleStructureConfig> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            BlockState.CODEC.fieldOf("primary_block").forGetter(SimpleStructureConfig::primaryBlock),
            BlockState.CODEC.fieldOf("secondary_block").forGetter(SimpleStructureConfig::secondaryBlock),
            Codec.INT.fieldOf("size").orElse(3).forGetter(SimpleStructureConfig::size)
        ).apply(instance, SimpleStructureConfig::new)
    );
}
