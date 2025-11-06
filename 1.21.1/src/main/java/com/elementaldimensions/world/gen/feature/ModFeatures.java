package com.elementaldimensions.world.gen.feature;

import com.elementaldimensions.ElementalDimensions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class ModFeatures {

	public static final Feature<DefaultFeatureConfig> LAVA_SHRINE = register("lava_shrine", new LavaShrineFeature());
	public static final Feature<DefaultFeatureConfig> CORAL_PALACE = register("coral_palace", new CoralPalaceFeature());
	public static final Feature<DefaultFeatureConfig> SKY_CASTLE = register("sky_castle", new SkyCastleFeature());
	public static final Feature<DefaultFeatureConfig> CRYSTAL_CAVERN = register("crystal_cavern", new CrystalCavernFeature());
	public static final Feature<DefaultFeatureConfig> STAR_TEMPLE = register("star_temple", new StarTempleFeature());
	public static final Feature<DefaultFeatureConfig> VOID_CITADEL = register("void_citadel", new VoidCitadelFeature());

	private static <C extends DefaultFeatureConfig, F extends Feature<C>> F register(String name, F feature) {
		return Registry.register(Registries.FEATURE, Identifier.of(ElementalDimensions.MOD_ID, name), feature);
	}

	public static void register() {
		ElementalDimensions.LOGGER.info("Registering custom structure features");
	}
}
