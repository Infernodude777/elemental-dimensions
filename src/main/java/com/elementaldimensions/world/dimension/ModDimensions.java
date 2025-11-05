package com.elementaldimensions.world.dimension;

import com.elementaldimensions.ElementalDimensions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
	
	// Dimension World Keys
	public static final RegistryKey<World> FIRELANDS = RegistryKey.of(RegistryKeys.WORLD,
			Identifier.of(ElementalDimensions.MOD_ID, "firelands"));
	
	public static final RegistryKey<World> AQUATICA = RegistryKey.of(RegistryKeys.WORLD,
			Identifier.of(ElementalDimensions.MOD_ID, "aquatica"));
	
	public static final RegistryKey<World> TERRA_DEPTHS = RegistryKey.of(RegistryKeys.WORLD,
			Identifier.of(ElementalDimensions.MOD_ID, "terra_depths"));
	
	public static final RegistryKey<World> SKYREACH_PEAKS = RegistryKey.of(RegistryKeys.WORLD,
			Identifier.of(ElementalDimensions.MOD_ID, "skyreach_peaks"));
	
	public static final RegistryKey<World> CELESTIAL = RegistryKey.of(RegistryKeys.WORLD,
			Identifier.of(ElementalDimensions.MOD_ID, "celestial"));
	
	public static final RegistryKey<World> VOID_REALM = RegistryKey.of(RegistryKeys.WORLD,
			Identifier.of(ElementalDimensions.MOD_ID, "void_realm"));
	
	// Dimension Type Keys
	public static final RegistryKey<DimensionType> FIRELANDS_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "firelands_type"));
	
	public static final RegistryKey<DimensionType> AQUATICA_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "aquatica_type"));
	
	public static final RegistryKey<DimensionType> TERRA_DEPTHS_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "terra_depths_type"));
	
	public static final RegistryKey<DimensionType> SKYREACH_PEAKS_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "skyreach_peaks_type"));
	
	public static final RegistryKey<DimensionType> CELESTIAL_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "celestial_type"));
	
	public static final RegistryKey<DimensionType> VOID_REALM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "void_realm_type"));
	
	// Dimension Options Keys
	public static final RegistryKey<DimensionOptions> FIRELANDS_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION,
			Identifier.of(ElementalDimensions.MOD_ID, "firelands"));
	
	public static final RegistryKey<DimensionOptions> AQUATICA_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION,
			Identifier.of(ElementalDimensions.MOD_ID, "aquatica"));
	
	public static final RegistryKey<DimensionOptions> TERRA_DEPTHS_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION,
			Identifier.of(ElementalDimensions.MOD_ID, "terra_depths"));
	
	public static final RegistryKey<DimensionOptions> SKYREACH_PEAKS_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION,
			Identifier.of(ElementalDimensions.MOD_ID, "skyreach_peaks"));
	
	public static final RegistryKey<DimensionOptions> CELESTIAL_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION,
			Identifier.of(ElementalDimensions.MOD_ID, "celestial"));
	
	public static final RegistryKey<DimensionOptions> VOID_REALM_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION,
			Identifier.of(ElementalDimensions.MOD_ID, "void_realm"));
	
	public static void register() {
		ElementalDimensions.LOGGER.info("Registering dimensions for " + ElementalDimensions.MOD_ID);
		// Dimensions are registered via datapacks/JSON
	}
}
