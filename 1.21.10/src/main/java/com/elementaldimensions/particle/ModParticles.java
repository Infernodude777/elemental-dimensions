package com.elementaldimensions.particle;

import com.elementaldimensions.ElementalDimensions;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {

	// ===== FIRELANDS PARTICLES =====
	public static final SimpleParticleType EMBER_SPARK = register("ember_spark");
	public static final SimpleParticleType HEAT_HAZE = register("heat_haze");
	public static final SimpleParticleType SMOKE_LINE = register("smoke_line");
	public static final SimpleParticleType LAVA_SPLASH = register("lava_splash");
	public static final SimpleParticleType ASH_DRIFT = register("ash_drift");
	public static final SimpleParticleType GLOW_SMOKE = register("glow_smoke");
	public static final SimpleParticleType CINDER_POP = register("cinder_pop");
	public static final SimpleParticleType STEAM_RIPPLE = register("steam_ripple");
	public static final SimpleParticleType MAGMA_SPIT = register("magma_spit");
	public static final SimpleParticleType EMBER_TRAIL = register("ember_trail");

	// ===== AQUATICA PARTICLES =====
	public static final SimpleParticleType BUBBLE_STREAM = register("bubble_stream");
	public static final SimpleParticleType GLOW_SPORE = register("glow_spore");
	public static final SimpleParticleType MIST_TRAIL = register("mist_trail");
	public static final SimpleParticleType WATER_SHEEN = register("water_sheen");
	public static final SimpleParticleType PEARLESCENT_BURST = register("pearlescent_burst");
	public static final SimpleParticleType TIDAL_SPRAY = register("tidal_spray");
	public static final SimpleParticleType BIOLUME_DRIFT = register("biolume_drift");
	public static final SimpleParticleType WET_SPARKLE = register("wet_sparkle");
	public static final SimpleParticleType FOAM_PUFF = register("foam_puff");
	public static final SimpleParticleType CURRENT_LINE = register("current_line");

	// ===== TERRA DEPTHS PARTICLES =====
	public static final SimpleParticleType GLOW_DUST = register("glow_dust");
	public static final SimpleParticleType CRYSTAL_SHARD = register("crystal_shard");
	public static final SimpleParticleType SPORE_CLOUD = register("spore_cloud");
	public static final SimpleParticleType ROOT_PUFF = register("root_puff");
	public static final SimpleParticleType MOSS_SHEEN = register("moss_sheen");
	public static final SimpleParticleType EARTH_RIPPLE = register("earth_ripple");
	public static final SimpleParticleType STONE_DUST = register("stone_dust");
	public static final SimpleParticleType SAP_DROP = register("sap_drop");
	public static final SimpleParticleType BIOLUME_BURST = register("biolume_burst");
	public static final SimpleParticleType SHARD_SPARK = register("shard_spark");

	// ===== SKYREACH PEAKS PARTICLES =====
	public static final SimpleParticleType AIRSTREAM = register("airstream");
	public static final SimpleParticleType LIGHT_FLAKE = register("light_flake");
	public static final SimpleParticleType PETAL_DRIFT = register("petal_drift");
	public static final SimpleParticleType SPARK_DUST = register("spark_dust");
	public static final SimpleParticleType CLOUD_PUFF = register("cloud_puff");
	public static final SimpleParticleType WIND_RING = register("wind_ring");
	public static final SimpleParticleType FEATHER_DRIFT = register("feather_drift");
	public static final SimpleParticleType GLOW_BUBBLE = register("glow_bubble");
	public static final SimpleParticleType AIRBURST = register("airburst");
	public static final SimpleParticleType LIGHT_STREAK = register("light_streak");

	// ===== CELESTIAL DIMENSION PARTICLES =====
	public static final SimpleParticleType STARLIGHT_DUST = register("starlight_dust");
	public static final SimpleParticleType NEBULA_SWIRL = register("nebula_swirl");
	public static final SimpleParticleType VOID_GLOW = register("void_glow");
	public static final SimpleParticleType COSMIC_SPARK = register("cosmic_spark");
	public static final SimpleParticleType RIPPLE_LIGHT = register("ripple_light");
	public static final SimpleParticleType GRAVITY_DUST = register("gravity_dust");
	public static final SimpleParticleType RIFT_SMOKE = register("rift_smoke");
	public static final SimpleParticleType LUMEN_BURST = register("lumen_burst");
	public static final SimpleParticleType STAR_TRAIL = register("star_trail");
	public static final SimpleParticleType ETHER_WAVE = register("ether_wave");
	public static final SimpleParticleType PULSE_ORB = register("pulse_orb");

	private static SimpleParticleType register(String name) {
		return Registry.register(Registries.PARTICLE_TYPE,
				Identifier.of(ElementalDimensions.MOD_ID, name),
				FabricParticleTypes.simple());
	}

	public static void register() {
		ElementalDimensions.LOGGER.info("Registering particles for " + ElementalDimensions.MOD_ID);
	}

	public static void registerFactories() {
		ElementalDimensions.LOGGER.info("Registering particle factories for " + ElementalDimensions.MOD_ID);
		// Client-side factory registration happens here
	}
}
