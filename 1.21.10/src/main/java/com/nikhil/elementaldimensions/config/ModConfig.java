package com.nikhil.elementaldimensions.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nikhil.elementaldimensions.ElementalDimensions;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration system for Elemental Dimensions
 * Handles all customizable gameplay values
 */
public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(
        FabricLoader.getInstance().getConfigDir().toFile(),
        "elementaldimensions.json"
    );
    
    // Weapon ability cooldowns (seconds)
    public static Map<String, Integer> weaponAbilityCooldowns = new HashMap<>();
    
    // Tool settings
    public static boolean toolDebuffsEnabled = true;
    
    // Celestial dimension settings
    public static double celestialOreSpawnChance = 0.03;
    public static Map<String, Double> celestialMobSpawnMultipliers = new HashMap<>();
    
    // Boss settings
    public static int primarchHealth = 600;
    public static int phaseDurationSeconds = 15;
    public static int respawnCooldownMinutes = 60;
    public static int healthScalingPerPlayer = 100;
    
    // Grappling hook pull strength
    public static Map<String, Double> grapplingPullStrength = new HashMap<>();
    
    static {
        // Default weapon cooldowns
        weaponAbilityCooldowns.put("fire_wave", 8);
        weaponAbilityCooldowns.put("frost_nova", 10);
        weaponAbilityCooldowns.put("earthquake", 12);
        weaponAbilityCooldowns.put("wind_burst", 10);
        
        // Default mob spawn rates
        celestialMobSpawnMultipliers.put("celestial_enderman", 0.20);
        celestialMobSpawnMultipliers.put("void_shulker", 0.15);
        
        // Default grappling pull strengths
        grapplingPullStrength.put("flame", 2.8);
        grapplingPullStrength.put("tidal", 2.2);
        grapplingPullStrength.put("stone", 2.0);
        grapplingPullStrength.put("wind", 3.2);
    }
    
    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                if (data != null) {
                    applyConfig(data);
                }
                ElementalDimensions.LOGGER.info("Configuration loaded from file");
            } catch (IOException e) {
                ElementalDimensions.LOGGER.error("Failed to load config", e);
                save(); // Create default config
            }
        } else {
            save(); // Create default config
        }
    }
    
    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            ConfigData data = new ConfigData();
            data.weapon_ability_cooldowns = weaponAbilityCooldowns;
            data.tool_debuffs_enabled = toolDebuffsEnabled;
            data.celestial_dimension = new CelestialDimensionConfig();
            data.celestial_dimension.ore_spawn_chance = celestialOreSpawnChance;
            data.celestial_dimension.mob_spawn_multipliers = celestialMobSpawnMultipliers;
            data.boss_settings = new BossSettings();
            data.boss_settings.primarch_health = primarchHealth;
            data.boss_settings.phase_duration_seconds = phaseDurationSeconds;
            data.boss_settings.respawn_cooldown_minutes = respawnCooldownMinutes;
            data.boss_settings.health_scaling_per_player = healthScalingPerPlayer;
            data.grappling_pull_strength = grapplingPullStrength;
            
            GSON.toJson(data, writer);
            ElementalDimensions.LOGGER.info("Configuration saved to file");
        } catch (IOException e) {
            ElementalDimensions.LOGGER.error("Failed to save config", e);
        }
    }
    
    private static void applyConfig(ConfigData data) {
        if (data.weapon_ability_cooldowns != null) {
            weaponAbilityCooldowns = data.weapon_ability_cooldowns;
        }
        toolDebuffsEnabled = data.tool_debuffs_enabled;
        if (data.celestial_dimension != null) {
            celestialOreSpawnChance = data.celestial_dimension.ore_spawn_chance;
            if (data.celestial_dimension.mob_spawn_multipliers != null) {
                celestialMobSpawnMultipliers = data.celestial_dimension.mob_spawn_multipliers;
            }
        }
        if (data.boss_settings != null) {
            primarchHealth = data.boss_settings.primarch_health;
            phaseDurationSeconds = data.boss_settings.phase_duration_seconds;
            respawnCooldownMinutes = data.boss_settings.respawn_cooldown_minutes;
            healthScalingPerPlayer = data.boss_settings.health_scaling_per_player;
        }
        if (data.grappling_pull_strength != null) {
            grapplingPullStrength = data.grappling_pull_strength;
        }
    }
    
    // Inner classes for JSON structure
    private static class ConfigData {
        Map<String, Integer> weapon_ability_cooldowns;
        boolean tool_debuffs_enabled;
        CelestialDimensionConfig celestial_dimension;
        BossSettings boss_settings;
        Map<String, Double> grappling_pull_strength;
    }
    
    private static class CelestialDimensionConfig {
        double ore_spawn_chance;
        Map<String, Double> mob_spawn_multipliers;
    }
    
    private static class BossSettings {
        int primarch_health;
        int phase_duration_seconds;
        int respawn_cooldown_minutes;
        int health_scaling_per_player;
    }
}
