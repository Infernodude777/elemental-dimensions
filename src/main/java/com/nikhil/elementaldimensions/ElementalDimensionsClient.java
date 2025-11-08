package com.nikhil.elementaldimensions;

import com.nikhil.elementaldimensions.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

/**
 * Client-side initialization for Elemental Dimensions
 * Handles rendering, particles, and client-only features
 */
public class ElementalDimensionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ElementalDimensions.LOGGER.info("Initializing Elemental Dimensions client");

        // Client-side initialization
        // Particle and renderer registration can be added here if needed

        // Register fluid rendering
        registerFluidRendering();
    }

    private void registerFluidRendering() {
        // Lucid Water - purple/blue ethereal fluid
        FluidRenderHandlerRegistry.INSTANCE.register(
            ModFluids.STILL_LUCID_WATER,
            ModFluids.FLOWING_LUCID_WATER,
            new SimpleFluidRenderHandler(
                Identifier.of("minecraft", "block/water_still"),
                Identifier.of("minecraft", "block/water_flow"),
                0xAA88EEFF // Ethereal purple-blue tint
            )
        );

        BlockRenderLayerMap.INSTANCE.putFluids(
            RenderLayer.getTranslucent(),
            ModFluids.STILL_LUCID_WATER,
            ModFluids.FLOWING_LUCID_WATER
        );

        // Radiant Fluid - golden glowing fluid
        FluidRenderHandlerRegistry.INSTANCE.register(
            ModFluids.STILL_RADIANT_FLUID,
            ModFluids.FLOWING_RADIANT_FLUID,
            new SimpleFluidRenderHandler(
                Identifier.of("minecraft", "block/water_still"),
                Identifier.of("minecraft", "block/water_flow"),
                0xFFFFDD33 // Bright golden tint
            )
        );

        BlockRenderLayerMap.INSTANCE.putFluids(
            RenderLayer.getTranslucent(),
            ModFluids.STILL_RADIANT_FLUID,
            ModFluids.FLOWING_RADIANT_FLUID
        );

        // Mycelial Ooze - green/brown fungal fluid
        FluidRenderHandlerRegistry.INSTANCE.register(
            ModFluids.STILL_MYCELIAL_OOZE,
            ModFluids.FLOWING_MYCELIAL_OOZE,
            new SimpleFluidRenderHandler(
                Identifier.of("minecraft", "block/water_still"),
                Identifier.of("minecraft", "block/water_flow"),
                0xCC558833 // Murky green-brown
            )
        );

        BlockRenderLayerMap.INSTANCE.putFluids(
            RenderLayer.getTranslucent(),
            ModFluids.STILL_MYCELIAL_OOZE,
            ModFluids.FLOWING_MYCELIAL_OOZE
        );

        // Void Essence - dark purple/black cosmic fluid
        FluidRenderHandlerRegistry.INSTANCE.register(
            ModFluids.STILL_VOID_ESSENCE,
            ModFluids.FLOWING_VOID_ESSENCE,
            new SimpleFluidRenderHandler(
                Identifier.of("minecraft", "block/water_still"),
                Identifier.of("minecraft", "block/water_flow"),
                0xDD220044 // Deep purple/black
            )
        );

        BlockRenderLayerMap.INSTANCE.putFluids(
            RenderLayer.getTranslucent(),
            ModFluids.STILL_VOID_ESSENCE,
            ModFluids.FLOWING_VOID_ESSENCE
        );

        // Ink Fluid - dark blue/black ink
        FluidRenderHandlerRegistry.INSTANCE.register(
            ModFluids.STILL_INK_FLUID,
            ModFluids.FLOWING_INK_FLUID,
            new SimpleFluidRenderHandler(
                Identifier.of("minecraft", "block/water_still"),
                Identifier.of("minecraft", "block/water_flow"),
                0xFF001133 // Dark blue/black ink
            )
        );

        BlockRenderLayerMap.INSTANCE.putFluids(
            RenderLayer.getTranslucent(),
            ModFluids.STILL_INK_FLUID,
            ModFluids.FLOWING_INK_FLUID
        );

        ElementalDimensions.LOGGER.info("Registered fluid rendering for 5 dimensional fluids");
    }
}
