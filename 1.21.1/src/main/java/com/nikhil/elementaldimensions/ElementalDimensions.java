package com.nikhil.elementaldimensions;

import com.nikhil.elementaldimensions.command.DimensionTeleportCommands;
import com.nikhil.elementaldimensions.command.GuideCommand;
import com.nikhil.elementaldimensions.command.ItemSpawnCommands;
import com.nikhil.elementaldimensions.config.ModConfig;
import com.nikhil.elementaldimensions.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main mod class for Elemental Dimensions
 * Initializes all elemental systems, items, blocks, entities, and dimensions
 */
public class ElementalDimensions implements ModInitializer {
    public static final String MOD_ID = "elementaldimensions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        @Override
    public void onInitialize() {
        LOGGER.info("Initializing Elemental Dimensions");

        // Register entities FIRST (before items that reference them)
        ModEntities.initialize();

        // Then register blocks and items
        ModBlocks.initialize();
        ModItems.initialize();

        // Register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            GuideCommand.register(dispatcher);
            DimensionTeleportCommands.register(dispatcher);
            ItemSpawnCommands.register(dispatcher);
        });

        // Initialize configuration
        ModConfig.load();

        LOGGER.info("Elemental Dimensions initialized successfully");
    }
}
