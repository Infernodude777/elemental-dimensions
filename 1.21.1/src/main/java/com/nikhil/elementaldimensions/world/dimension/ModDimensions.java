package com.nikhil.elementaldimensions.world.dimension;

import com.nikhil.elementaldimensions.ElementalDimensions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

/**
 * Registry for all custom dimensions
 */
public class ModDimensions {

    // Dimension World Keys
    public static final RegistryKey<World> CELESTIAL_WORLD = RegistryKey.of(
            RegistryKeys.WORLD,
            Identifier.of(ElementalDimensions.MOD_ID, "celestial_realm"));

    public static final RegistryKey<World> FIRE_WORLD = RegistryKey.of(
            RegistryKeys.WORLD,
            Identifier.of(ElementalDimensions.MOD_ID, "inferno_realm"));

    public static final RegistryKey<World> WATER_WORLD = RegistryKey.of(
            RegistryKeys.WORLD,
            Identifier.of(ElementalDimensions.MOD_ID, "aquatic_realm"));

    public static final RegistryKey<World> EARTH_WORLD = RegistryKey.of(
            RegistryKeys.WORLD,
            Identifier.of(ElementalDimensions.MOD_ID, "terran_realm"));

    public static final RegistryKey<World> AIR_WORLD = RegistryKey.of(
            RegistryKeys.WORLD,
            Identifier.of(ElementalDimensions.MOD_ID, "skybound_realm"));

    public static final RegistryKey<World> VOID_WORLD = RegistryKey.of(
            RegistryKeys.WORLD,
            Identifier.of(ElementalDimensions.MOD_ID, "void_realm"));

    // Dimension Type Keys
    public static final RegistryKey<DimensionType> CELESTIAL_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            Identifier.of(ElementalDimensions.MOD_ID, "celestial_realm"));

    public static final RegistryKey<DimensionType> FIRE_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            Identifier.of(ElementalDimensions.MOD_ID, "inferno_realm"));

    public static final RegistryKey<DimensionType> WATER_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            Identifier.of(ElementalDimensions.MOD_ID, "aquatic_realm"));

    public static final RegistryKey<DimensionType> EARTH_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            Identifier.of(ElementalDimensions.MOD_ID, "terran_realm"));

    public static final RegistryKey<DimensionType> AIR_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            Identifier.of(ElementalDimensions.MOD_ID, "skybound_realm"));

    public static final RegistryKey<DimensionType> VOID_TYPE = RegistryKey.of(
            RegistryKeys.DIMENSION_TYPE,
            Identifier.of(ElementalDimensions.MOD_ID, "void_realm"));

    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering dimensions for " + ElementalDimensions.MOD_ID);
    }
}
