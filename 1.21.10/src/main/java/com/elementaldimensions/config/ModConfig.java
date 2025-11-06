package com.elementaldimensions.config;

import com.elementaldimensions.ElementalDimensions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "elementaldimensions.json");

	private static ConfigData config = new ConfigData();

	public static void init() {
		if (CONFIG_FILE.exists()) {
			try (FileReader reader = new FileReader(CONFIG_FILE)) {
				config = GSON.fromJson(reader, ConfigData.class);
				ElementalDimensions.LOGGER.info("Loaded configuration from {}", CONFIG_FILE);
			} catch (IOException e) {
				ElementalDimensions.LOGGER.error("Failed to load config", e);
			}
		} else {
			save();
		}
	}

	public static void save() {
		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(config, writer);
			ElementalDimensions.LOGGER.info("Saved configuration to {}", CONFIG_FILE);
		} catch (IOException e) {
			ElementalDimensions.LOGGER.error("Failed to save config", e);
		}
	}

	public static ConfigData get() {
		return config;
	}

	public static class ConfigData {
		// Performance settings
		public int particleMaxPerSource = 10;
		public int armorCheckInterval = 100;
		public boolean asyncAssetLoading = true;

		// Multiplayer settings
		public boolean bossScalesWithPlayers = true;
		public boolean pvpEnabled = false;
		public boolean sharedCelestialDimension = true;

		// Audio settings
		public float musicVolume = 0.5f;
		public float ambientVolume = 0.7f;

		// Debug settings
		public boolean debugMode = false;
		public boolean showParticleDebug = false;

		// Progression settings
		public boolean requireBossKillsForProgression = true;
		public boolean enableDimensionalCompass = true;
	}
}
