package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

/**
 * Custom sound events for Elemental Dimensions
 *
 * Sound file naming convention:
 * - Ambient: sounds/ambient/[dimension_name].ogg
 * - Boss: sounds/boss/[boss_name]_[action].ogg
 * - Block: sounds/block/[block_name]_[action].ogg
 */
public class ModSounds {

    // Ambient dimension sounds
    public static final SoundEvent AMBIENT_DREAMING_DEPTHS = registerSound("ambient.dreaming_depths");
    public static final SoundEvent AMBIENT_CELESTINE_EXPANSE = registerSound("ambient.celestine_expanse");
    public static final SoundEvent AMBIENT_FUNGAL_DOMINION = registerSound("ambient.fungal_dominion");
    public static final SoundEvent AMBIENT_FORGOTTEN_ARCHIVE = registerSound("ambient.forgotten_archive");
    public static final SoundEvent AMBIENT_ASTRAL_FRONTIER = registerSound("ambient.astral_frontier");
    public static final SoundEvent AMBIENT_GLOOMY_CAVERNS = registerSound("ambient.gloomy_caverns");

    // Boss music
    public static final SoundEvent MUSIC_BOSS_PYROS = registerSound("music.boss.pyros");
    public static final SoundEvent MUSIC_BOSS_LEVIATHAN = registerSound("music.boss.leviathan");
    public static final SoundEvent MUSIC_BOSS_GOLIATH = registerSound("music.boss.goliath");
    public static final SoundEvent MUSIC_BOSS_ZEPHYROS = registerSound("music.boss.zephyros");
    public static final SoundEvent MUSIC_BOSS_PRIMARCH = registerSound("music.boss.primarch");
    public static final SoundEvent MUSIC_BOSS_THE_DREAMER = registerSound("music.boss.the_dreamer");
    public static final SoundEvent MUSIC_BOSS_RADIANT_MONARCH = registerSound("music.boss.radiant_monarch");
    public static final SoundEvent MUSIC_BOSS_OVERMIND = registerSound("music.boss.overmind");
    public static final SoundEvent MUSIC_BOSS_LIBRARIAN = registerSound("music.boss.librarian");
    public static final SoundEvent MUSIC_BOSS_VOID_ARCHON = registerSound("music.boss.void_archon");
    public static final SoundEvent MUSIC_BOSS_ECHO_KING = registerSound("music.boss.echo_king");

    // Boss sound effects
    public static final SoundEvent PYROS_ROAR = registerSound("entity.pyros.roar");
    public static final SoundEvent PYROS_FIRE_WAVE = registerSound("entity.pyros.fire_wave");
    public static final SoundEvent LEVIATHAN_SPLASH = registerSound("entity.leviathan.splash");
    public static final SoundEvent GOLIATH_STOMP = registerSound("entity.goliath.stomp");
    public static final SoundEvent ZEPHYROS_WIND = registerSound("entity.zephyros.wind");

    // Portal sounds
    public static final SoundEvent PORTAL_ACTIVATE = registerSound("block.portal.activate");
    public static final SoundEvent PORTAL_TRAVEL = registerSound("block.portal.travel");
    public static final SoundEvent PORTAL_AMBIENT = registerSound("block.portal.ambient");

    // Fluid sounds
    public static final SoundEvent LUCID_WATER_AMBIENT = registerSound("block.lucid_water.ambient");
    public static final SoundEvent VOID_ESSENCE_AMBIENT = registerSound("block.void_essence.ambient");

    // Block sounds
    public static final SoundEvent CRYSTAL_BREAK = registerSound("block.crystal.break");
    public static final SoundEvent CRYSTAL_PLACE = registerSound("block.crystal.place");
    public static final SoundEvent DREAMSTONE_BREAK = registerSound("block.dreamstone.break");

    private static SoundEvent registerSound(String name) {
        Identifier id = Identifier.of(ElementalDimensions.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering sound events for Elemental Dimensions");
    }
}
