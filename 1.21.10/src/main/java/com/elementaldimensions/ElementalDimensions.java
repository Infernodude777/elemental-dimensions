package com.elementaldimensions;

import com.elementaldimensions.block.ModBlocks;
import com.elementaldimensions.command.ModCommands;
import com.elementaldimensions.config.ModConfig;
import com.elementaldimensions.entity.ModEntities;
import com.elementaldimensions.item.ModItems;
import com.elementaldimensions.particle.ModParticles;
import com.elementaldimensions.sound.ModSounds;
import com.elementaldimensions.world.dimension.ModDimensions;
import com.elementaldimensions.world.gen.feature.ModFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementalDimensions implements ModInitializer {
	public static final String MOD_ID = "elementaldimensions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Elemental Dimensions mod...");

		// Load configuration
		ModConfig.init();

		// Register mod content
		ModBlocks.register();
		ModItems.register();
		ModEntities.register();
		ModParticles.register();
		ModSounds.register();
		ModDimensions.register();
		ModFeatures.register();

		// Register commands
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			ModCommands.register(dispatcher);
		});

		LOGGER.info("Elemental Dimensions mod initialized successfully!");
	}
}
