package com.elementaldimensions.sound;

import com.elementaldimensions.ElementalDimensions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

	// ===== FIRELANDS MUSIC =====
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_FIRELANDS_INFERNAL_ECHOES =
			registerMusic("music.firelands.infernal_echoes");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_FIRELANDS_CRIMSON_PULSE =
			registerMusic("music.firelands.crimson_pulse");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_FIRELANDS_ASHEN_DRUMS =
			registerMusic("music.firelands.ashen_drums");

	// ===== AQUATICA MUSIC =====
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_AQUATICA_ECHOING_CURRENTS =
			registerMusic("music.aquatica.echoing_currents");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_AQUATICA_DEEP_LAMENT =
			registerMusic("music.aquatica.deep_lament");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_AQUATICA_CORAL_LULLABY =
			registerMusic("music.aquatica.coral_lullaby");

	// ===== TERRA DEPTHS MUSIC =====
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_TERRA_HEART_OF_EARTH =
			registerMusic("music.terra.heart_of_earth");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_TERRA_ROOTED_HYMN =
			registerMusic("music.terra.rooted_hymn");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_TERRA_STONE_CHORUS =
			registerMusic("music.terra.stone_chorus");

	// ===== SKYREACH PEAKS MUSIC =====
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_SKYREACH_WHISPERS_ABOVE =
			registerMusic("music.skyreach.whispers_above");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_SKYREACH_GALE_CANTICLE =
			registerMusic("music.skyreach.gale_canticle");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_SKYREACH_SKYBORNE_CHOIR =
			registerMusic("music.skyreach.skyborne_choir");

	// ===== CELESTIAL DIMENSION MUSIC =====
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_CELESTIAL_ETERNAL_ORBIT =
			registerMusic("music.celestial.eternal_orbit");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_CELESTIAL_NEBULA_LULL =
			registerMusic("music.celestial.nebula_lull");
	public static final RegistryEntry.Reference<SoundEvent> MUSIC_CELESTIAL_STARLIT_CHOIR =
			registerMusic("music.celestial.starlit_choir");

	// ===== AMBIENT SOUNDS =====
	public static final RegistryEntry.Reference<SoundEvent> AMBIENT_FIRELANDS =
			registerSound("ambient.firelands");
	public static final RegistryEntry.Reference<SoundEvent> AMBIENT_AQUATICA =
			registerSound("ambient.aquatica");
	public static final RegistryEntry.Reference<SoundEvent> AMBIENT_TERRA =
			registerSound("ambient.terra");
	public static final RegistryEntry.Reference<SoundEvent> AMBIENT_SKYREACH =
			registerSound("ambient.skyreach");
	public static final RegistryEntry.Reference<SoundEvent> AMBIENT_CELESTIAL =
			registerSound("ambient.celestial");

	// ===== BOSS SOUNDS =====
	public static final RegistryEntry.Reference<SoundEvent> BOSS_FIRE_TITAN_ROAR =
			registerSound("boss.fire_titan.roar");
	public static final RegistryEntry.Reference<SoundEvent> BOSS_ABYSS_LEVIATHAN_CRY =
			registerSound("boss.abyss_leviathan.cry");
	public static final RegistryEntry.Reference<SoundEvent> BOSS_STONE_COLOSSUS_RUMBLE =
			registerSound("boss.stone_colossus.rumble");
	public static final RegistryEntry.Reference<SoundEvent> BOSS_WIND_SERAPH_SHRIEK =
			registerSound("boss.wind_seraph.shriek");
	public static final RegistryEntry.Reference<SoundEvent> BOSS_PRIMARCH_WAIL =
			registerSound("boss.elemental_primarch.wail");

	private static RegistryEntry.Reference<SoundEvent> registerSound(String name) {
		Identifier id = Identifier.of(ElementalDimensions.MOD_ID, name);
		return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}

	private static RegistryEntry.Reference<SoundEvent> registerMusic(String name) {
		return registerSound(name);
	}

	public static void register() {
		ElementalDimensions.LOGGER.info("Registering sounds for " + ElementalDimensions.MOD_ID);
	}
}
