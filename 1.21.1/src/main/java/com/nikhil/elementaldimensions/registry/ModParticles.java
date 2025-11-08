package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Custom particle types for dimensional effects
 *
 * Particles add visual ambiance to dimensions and special events
 */
public class ModParticles {

    // Dreaming Depths particles
    public static final SimpleParticleType DREAM_SPARKLE = register("dream_sparkle");
    public static final SimpleParticleType NIGHTMARE_FOG = register("nightmare_fog");
    public static final SimpleParticleType LUCID_BUBBLE = register("lucid_bubble");

    // Celestine Expanse particles
    public static final SimpleParticleType SKY_SHIMMER = register("sky_shimmer");
    public static final SimpleParticleType RADIANT_BEAM = register("radiant_beam");
    public static final SimpleParticleType CLOUD_PUFF = register("cloud_puff");

    // Fungal Dominion particles
    public static final SimpleParticleType SPORE_CLOUD = register("spore_cloud");
    public static final SimpleParticleType MYCELIUM_DUST = register("mycelium_dust");
    public static final SimpleParticleType BIOLUME_GLOW = register("biolume_glow");

    // Forgotten Archive particles
    public static final SimpleParticleType INK_DROPLET = register("ink_droplet");
    public static final SimpleParticleType PAGE_FLUTTER = register("page_flutter");
    public static final SimpleParticleType KNOWLEDGE_WISP = register("knowledge_wisp");

    // Astral Frontier particles
    public static final SimpleParticleType STAR_TRAIL = register("star_trail");
    public static final SimpleParticleType VOID_RIFT = register("void_rift");
    public static final SimpleParticleType COSMIC_DUST = register("cosmic_dust");

    // Gloomy Caverns particles
    public static final SimpleParticleType ECHO_WAVE = register("echo_wave");
    public static final SimpleParticleType GLOOM_FOG = register("gloom_fog");
    public static final SimpleParticleType DESPAIR_EMBER = register("despair_ember");

    // Boss particles
    public static final SimpleParticleType FIRE_EXPLOSION = register("fire_explosion");
    public static final SimpleParticleType WATER_SURGE = register("water_surge");
    public static final SimpleParticleType EARTH_CRACK = register("earth_crack");
    public static final SimpleParticleType WIND_SPIRAL = register("wind_spiral");

    // Special effect particles
    public static final SimpleParticleType PORTAL_SWIRL = register("portal_swirl");
    public static final SimpleParticleType ESSENCE_SHIMMER = register("essence_shimmer");
    public static final SimpleParticleType DIMENSIONAL_RIFT = register("dimensional_rift");

    private static SimpleParticleType register(String name) {
        return Registry.register(
            Registries.PARTICLE_TYPE,
            Identifier.of(ElementalDimensions.MOD_ID, name),
            FabricParticleTypes.simple()
        );
    }

    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering particle types for Elemental Dimensions");
    }
}
