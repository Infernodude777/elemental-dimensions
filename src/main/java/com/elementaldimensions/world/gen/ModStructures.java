package com.elementaldimensions.world.gen;

import com.elementaldimensions.ElementalDimensions;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
public class ModStructures {

	// Firelands structures
	public static final RegistryKey<Structure> LAVA_SHRINE = registerKey("lava_shrine");
	public static final RegistryKey<Structure> EMBER_FORTRESS = registerKey("ember_fortress");
	public static final RegistryKey<Structure> VOLCANIC_TEMPLE = registerKey("volcanic_temple");

	// Aquatica structures
	public static final RegistryKey<Structure> CORAL_PALACE = registerKey("coral_palace");
	public static final RegistryKey<Structure> UNDERWATER_RUINS = registerKey("underwater_ruins");
	public static final RegistryKey<Structure> ABYSSAL_SHRINE = registerKey("abyssal_shrine");

	// Terra Depths structures
	public static final RegistryKey<Structure> CRYSTAL_CAVERN = registerKey("crystal_cavern");
	public static final RegistryKey<Structure> MOSS_SANCTUARY = registerKey("moss_sanctuary");
	public static final RegistryKey<Structure> ANCIENT_GAIA_TEMPLE = registerKey("ancient_gaia_temple");

	// Skyreach Peaks structures
	public static final RegistryKey<Structure> SKY_CASTLE = registerKey("sky_castle");
	public static final RegistryKey<Structure> FLOATING_ISLAND_RUIN = registerKey("floating_island_ruin");
	public static final RegistryKey<Structure> WIND_ALTAR = registerKey("wind_altar");

	// Celestial structures
	public static final RegistryKey<Structure> STAR_TEMPLE = registerKey("star_temple");
	public static final RegistryKey<Structure> NEBULA_SANCTUM = registerKey("nebula_sanctum");
	public static final RegistryKey<Structure> COSMIC_OBSERVATORY = registerKey("cosmic_observatory");

	// Void Realm structures
	public static final RegistryKey<Structure> VOID_CITADEL = registerKey("void_citadel");
	public static final RegistryKey<Structure> NULLSTONE_TOWER = registerKey("nullstone_tower");
	public static final RegistryKey<Structure> SHADOW_PORTAL = registerKey("shadow_portal");

	private static RegistryKey<Structure> registerKey(String name) {
		return RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(ElementalDimensions.MOD_ID, name));
	}

	public static void bootstrap(Registerable<Structure> context) {
		// Structures will be defined here when NBT files are created
		// For now, this sets up the registry keys
		ElementalDimensions.LOGGER.info("Structure registry keys registered");
	}

	public static void register() {
		ElementalDimensions.LOGGER.info("Registering mod structures");
	}
}
