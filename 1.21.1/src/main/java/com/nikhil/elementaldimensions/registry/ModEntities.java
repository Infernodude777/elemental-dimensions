package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import com.nikhil.elementaldimensions.entity.boss.*;
import com.nikhil.elementaldimensions.entity.dreamingdepths.*;
import com.nikhil.elementaldimensions.entity.celestineexpanse.*;
import com.nikhil.elementaldimensions.entity.fungaldominion.*;
import com.nikhil.elementaldimensions.entity.forgottenarchive.*;
import com.nikhil.elementaldimensions.entity.astralfrontier.*;
import com.nikhil.elementaldimensions.entity.gloomycaverns.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static EntityType<PyrosEntity> PYROS;
    public static EntityType<LeviathanEntity> LEVIATHAN;
    public static EntityType<GoliathEntity> GOLIATH;
    public static EntityType<ZephyrosEntity> ZEPHYROS;
    public static EntityType<PrimarchEntity> PRIMARCH;

    public static EntityType<PhantasmEntity> PHANTASM;
    public static EntityType<LullerEntity> LULLER;
    public static EntityType<NightmareShadeEntity> NIGHTMARE_SHADE;
    public static EntityType<SomnianButterflyEntity> SOMNIAN_BUTTERFLY;
    public static EntityType<TheDreamerEntity> THE_DREAMER;

    public static EntityType<SkywhaleEntity> SKYWHALE;
    public static EntityType<LumifinEntity> LUMIFIN;
    public static EntityType<StormHawkEntity> STORM_HAWK;
    public static EntityType<SkyPirateEntity> SKY_PIRATE;
    public static EntityType<TheRadiantMonarchEntity> THE_RADIANT_MONARCH;

    public static EntityType<SporeBatEntity> SPORE_BAT;
    public static EntityType<MyceliumWalkerEntity> MYCELIUM_WALKER;
    public static EntityType<MyceliteCreeperEntity> MYCELITE_CREEPER;
    public static EntityType<FungalZombieEntity> FUNGAL_ZOMBIE;
    public static EntityType<TheOvermindEntity> THE_OVERMIND;

    public static EntityType<ScribeSpiritEntity> SCRIBE_SPIRIT;
    public static EntityType<LivingTomeEntity> LIVING_TOME;
    public static EntityType<SentinelEntity> SENTINEL;
    public static EntityType<PageHorrorEntity> PAGE_HORROR;
    public static EntityType<TheLibrarianEntity> THE_LIBRARIAN;

    public static EntityType<StarElementalEntity> STAR_ELEMENTAL;
    public static EntityType<NebulaWispEntity> NEBULA_WISP;
    public static EntityType<VoidlingEntity> VOIDLING;
    public static EntityType<CosmicHorrorEntity> COSMIC_HORROR;
    public static EntityType<TheVoidArchonEntity> THE_VOID_ARCHON;

    public static EntityType<EchoWispEntity> ECHO_WISP;
    public static EntityType<CaveSnailEntity> CAVE_SNAIL;
    public static EntityType<GloomStalkerEntity> GLOOM_STALKER;
    public static EntityType<WitheredSkeletonEntity> WITHERED_SKELETON;
    public static EntityType<TheEchoKingEntity> THE_ECHO_KING;

    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering entities for " + ElementalDimensions.MOD_ID);

        PYROS = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "pyros"),
                EntityType.Builder.create(PyrosEntity::new, SpawnGroup.MONSTER).dimensions(2.0f, 3.0f).maxTrackingRange(64).trackingTickInterval(3).build("pyros"));
        LEVIATHAN = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "leviathan"),
                EntityType.Builder.create(LeviathanEntity::new, SpawnGroup.MONSTER).dimensions(2.5f, 2.0f).maxTrackingRange(64).trackingTickInterval(3).build("leviathan"));
        GOLIATH = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "goliath"),
                EntityType.Builder.create(GoliathEntity::new, SpawnGroup.MONSTER).dimensions(3.0f, 4.0f).maxTrackingRange(64).trackingTickInterval(3).build("goliath"));
        ZEPHYROS = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "zephyros"),
                EntityType.Builder.create(ZephyrosEntity::new, SpawnGroup.MONSTER).dimensions(1.5f, 2.5f).maxTrackingRange(64).trackingTickInterval(3).build("zephyros"));
        PRIMARCH = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "primarch"),
                EntityType.Builder.create(PrimarchEntity::new, SpawnGroup.MONSTER).dimensions(3.5f, 5.0f).maxTrackingRange(96).trackingTickInterval(2).build("primarch"));

        PHANTASM = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "phantasm"),
                EntityType.Builder.create(PhantasmEntity::new, SpawnGroup.MONSTER).dimensions(0.9f, 1.8f).maxTrackingRange(48).trackingTickInterval(3).build("phantasm"));
        LULLER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "luller"),
                EntityType.Builder.create(LullerEntity::new, SpawnGroup.CREATURE).dimensions(0.8f, 1.2f).maxTrackingRange(32).trackingTickInterval(3).build("luller"));
        NIGHTMARE_SHADE = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "nightmare_shade"),
                EntityType.Builder.create(NightmareShadeEntity::new, SpawnGroup.MONSTER).dimensions(0.85f, 1.9f).maxTrackingRange(48).trackingTickInterval(3).build("nightmare_shade"));
        SOMNIAN_BUTTERFLY = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "somnian_butterfly"),
                EntityType.Builder.create(SomnianButterflyEntity::new, SpawnGroup.AMBIENT).dimensions(0.4f, 0.4f).maxTrackingRange(32).trackingTickInterval(3).build("somnian_butterfly"));
        THE_DREAMER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "the_dreamer"),
                EntityType.Builder.create(TheDreamerEntity::new, SpawnGroup.MONSTER).dimensions(2.2f, 3.5f).maxTrackingRange(80).trackingTickInterval(2).build("the_dreamer"));

        SKYWHALE = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "skywhale"),
                EntityType.Builder.create(SkywhaleEntity::new, SpawnGroup.CREATURE).dimensions(2.5f, 1.5f).maxTrackingRange(48).trackingTickInterval(3).build("skywhale"));
        LUMIFIN = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "lumifin"),
                EntityType.Builder.create(LumifinEntity::new, SpawnGroup.AMBIENT).dimensions(0.5f, 0.5f).maxTrackingRange(32).trackingTickInterval(3).build("lumifin"));
        STORM_HAWK = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "storm_hawk"),
                EntityType.Builder.create(StormHawkEntity::new, SpawnGroup.MONSTER).dimensions(0.8f, 1.4f).maxTrackingRange(48).trackingTickInterval(3).build("storm_hawk"));
        SKY_PIRATE = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "sky_pirate"),
                EntityType.Builder.create(SkyPirateEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 1.95f).maxTrackingRange(48).trackingTickInterval(3).build("sky_pirate"));
        THE_RADIANT_MONARCH = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "the_radiant_monarch"),
                EntityType.Builder.create(TheRadiantMonarchEntity::new, SpawnGroup.MONSTER).dimensions(2.4f, 3.8f).maxTrackingRange(80).trackingTickInterval(2).build("the_radiant_monarch"));

        SPORE_BAT = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "spore_bat"),
                EntityType.Builder.create(SporeBatEntity::new, SpawnGroup.AMBIENT).dimensions(0.5f, 0.5f).maxTrackingRange(32).trackingTickInterval(3).build("spore_bat"));
        MYCELIUM_WALKER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "mycelium_walker"),
                EntityType.Builder.create(MyceliumWalkerEntity::new, SpawnGroup.CREATURE).dimensions(0.9f, 1.3f).maxTrackingRange(32).trackingTickInterval(3).build("mycelium_walker"));
        MYCELITE_CREEPER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "mycelite_creeper"),
                EntityType.Builder.create(MyceliteCreeperEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 1.7f).maxTrackingRange(48).trackingTickInterval(3).build("mycelite_creeper"));
        FUNGAL_ZOMBIE = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "fungal_zombie"),
                EntityType.Builder.create(FungalZombieEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 1.95f).maxTrackingRange(48).trackingTickInterval(3).build("fungal_zombie"));
        THE_OVERMIND = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "the_overmind"),
                EntityType.Builder.create(TheOvermindEntity::new, SpawnGroup.MONSTER).dimensions(2.6f, 3.2f).maxTrackingRange(80).trackingTickInterval(2).build("the_overmind"));

        SCRIBE_SPIRIT = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "scribe_spirit"),
                EntityType.Builder.create(ScribeSpiritEntity::new, SpawnGroup.CREATURE).dimensions(0.7f, 1.4f).maxTrackingRange(32).trackingTickInterval(3).build("scribe_spirit"));
        LIVING_TOME = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "living_tome"),
                EntityType.Builder.create(LivingTomeEntity::new, SpawnGroup.AMBIENT).dimensions(0.6f, 0.6f).maxTrackingRange(32).trackingTickInterval(3).build("living_tome"));
        SENTINEL = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "sentinel"),
                EntityType.Builder.create(SentinelEntity::new, SpawnGroup.MONSTER).dimensions(1.2f, 2.8f).maxTrackingRange(48).trackingTickInterval(3).build("sentinel"));
        PAGE_HORROR = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "page_horror"),
                EntityType.Builder.create(PageHorrorEntity::new, SpawnGroup.MONSTER).dimensions(0.8f, 2.2f).maxTrackingRange(48).trackingTickInterval(3).build("page_horror"));
        THE_LIBRARIAN = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "the_librarian"),
                EntityType.Builder.create(TheLibrarianEntity::new, SpawnGroup.MONSTER).dimensions(2.3f, 3.6f).maxTrackingRange(80).trackingTickInterval(2).build("the_librarian"));

        STAR_ELEMENTAL = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "star_elemental"),
                EntityType.Builder.create(StarElementalEntity::new, SpawnGroup.CREATURE).dimensions(0.8f, 1.6f).maxTrackingRange(32).trackingTickInterval(3).build("star_elemental"));
        NEBULA_WISP = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "nebula_wisp"),
                EntityType.Builder.create(NebulaWispEntity::new, SpawnGroup.AMBIENT).dimensions(0.5f, 0.5f).maxTrackingRange(32).trackingTickInterval(3).build("nebula_wisp"));
        VOIDLING = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "voidling"),
                EntityType.Builder.create(VoidlingEntity::new, SpawnGroup.MONSTER).dimensions(0.9f, 2.0f).maxTrackingRange(48).trackingTickInterval(3).build("voidling"));
        COSMIC_HORROR = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "cosmic_horror"),
                EntityType.Builder.create(CosmicHorrorEntity::new, SpawnGroup.MONSTER).dimensions(1.4f, 2.6f).maxTrackingRange(48).trackingTickInterval(3).build("cosmic_horror"));
        THE_VOID_ARCHON = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "the_void_archon"),
                EntityType.Builder.create(TheVoidArchonEntity::new, SpawnGroup.MONSTER).dimensions(2.8f, 4.2f).maxTrackingRange(96).trackingTickInterval(2).build("the_void_archon"));

        ECHO_WISP = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "echo_wisp"),
                EntityType.Builder.create(EchoWispEntity::new, SpawnGroup.AMBIENT).dimensions(0.4f, 0.4f).maxTrackingRange(32).trackingTickInterval(3).build("echo_wisp"));
        CAVE_SNAIL = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "cave_snail"),
                EntityType.Builder.create(CaveSnailEntity::new, SpawnGroup.CREATURE).dimensions(0.7f, 0.6f).maxTrackingRange(32).trackingTickInterval(3).build("cave_snail"));
        GLOOM_STALKER = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "gloom_stalker"),
                EntityType.Builder.create(GloomStalkerEntity::new, SpawnGroup.MONSTER).dimensions(1.0f, 2.2f).maxTrackingRange(48).trackingTickInterval(3).build("gloom_stalker"));
        WITHERED_SKELETON = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "withered_skeleton"),
                EntityType.Builder.create(WitheredSkeletonEntity::new, SpawnGroup.MONSTER).dimensions(0.6f, 1.99f).maxTrackingRange(48).trackingTickInterval(3).build("withered_skeleton"));
        THE_ECHO_KING = Registry.register(Registries.ENTITY_TYPE, Identifier.of(ElementalDimensions.MOD_ID, "the_echo_king"),
                EntityType.Builder.create(TheEchoKingEntity::new, SpawnGroup.MONSTER).dimensions(2.5f, 3.9f).maxTrackingRange(80).trackingTickInterval(2).build("the_echo_king"));

        registerAttributes();
        ElementalDimensions.LOGGER.info("Registered 41 entities (5 old bosses + 36 new entities)");
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(PYROS, PyrosEntity.createPyrosAttributes());
        FabricDefaultAttributeRegistry.register(LEVIATHAN, LeviathanEntity.createLeviathanAttributes());
        FabricDefaultAttributeRegistry.register(GOLIATH, GoliathEntity.createGoliathAttributes());
        FabricDefaultAttributeRegistry.register(ZEPHYROS, ZephyrosEntity.createZephyrosAttributes());
        FabricDefaultAttributeRegistry.register(PRIMARCH, PrimarchEntity.createPrimarchAttributes());

        FabricDefaultAttributeRegistry.register(PHANTASM, PhantasmEntity.createPhantasmAttributes());
        FabricDefaultAttributeRegistry.register(LULLER, LullerEntity.createLullerAttributes());
        FabricDefaultAttributeRegistry.register(NIGHTMARE_SHADE, NightmareShadeEntity.createNightmareShadeAttributes());
        FabricDefaultAttributeRegistry.register(SOMNIAN_BUTTERFLY, SomnianButterflyEntity.createSomnianButterflyAttributes());
        FabricDefaultAttributeRegistry.register(THE_DREAMER, TheDreamerEntity.createTheDreamerAttributes());

        FabricDefaultAttributeRegistry.register(SKYWHALE, SkywhaleEntity.createSkywhaleAttributes());
        FabricDefaultAttributeRegistry.register(LUMIFIN, LumifinEntity.createLumifinAttributes());
        FabricDefaultAttributeRegistry.register(STORM_HAWK, StormHawkEntity.createStormHawkAttributes());
        FabricDefaultAttributeRegistry.register(SKY_PIRATE, SkyPirateEntity.createSkyPirateAttributes());
        FabricDefaultAttributeRegistry.register(THE_RADIANT_MONARCH, TheRadiantMonarchEntity.createTheRadiantMonarchAttributes());

        FabricDefaultAttributeRegistry.register(SPORE_BAT, SporeBatEntity.createSporeBatAttributes());
        FabricDefaultAttributeRegistry.register(MYCELIUM_WALKER, MyceliumWalkerEntity.createMyceliumWalkerAttributes());
        FabricDefaultAttributeRegistry.register(MYCELITE_CREEPER, MyceliteCreeperEntity.createMyceliteCreeperAttributes());
        FabricDefaultAttributeRegistry.register(FUNGAL_ZOMBIE, FungalZombieEntity.createFungalZombieAttributes());
        FabricDefaultAttributeRegistry.register(THE_OVERMIND, TheOvermindEntity.createTheOvermindAttributes());

        FabricDefaultAttributeRegistry.register(SCRIBE_SPIRIT, ScribeSpiritEntity.createScribeSpiritAttributes());
        FabricDefaultAttributeRegistry.register(LIVING_TOME, LivingTomeEntity.createLivingTomeAttributes());
        FabricDefaultAttributeRegistry.register(SENTINEL, SentinelEntity.createSentinelAttributes());
        FabricDefaultAttributeRegistry.register(PAGE_HORROR, PageHorrorEntity.createPageHorrorAttributes());
        FabricDefaultAttributeRegistry.register(THE_LIBRARIAN, TheLibrarianEntity.createTheLibrarianAttributes());

        FabricDefaultAttributeRegistry.register(STAR_ELEMENTAL, StarElementalEntity.createStarElementalAttributes());
        FabricDefaultAttributeRegistry.register(NEBULA_WISP, NebulaWispEntity.createNebulaWispAttributes());
        FabricDefaultAttributeRegistry.register(VOIDLING, VoidlingEntity.createVoidlingAttributes());
        FabricDefaultAttributeRegistry.register(COSMIC_HORROR, CosmicHorrorEntity.createCosmicHorrorAttributes());
        FabricDefaultAttributeRegistry.register(THE_VOID_ARCHON, TheVoidArchonEntity.createTheVoidArchonAttributes());

        FabricDefaultAttributeRegistry.register(ECHO_WISP, EchoWispEntity.createEchoWispAttributes());
        FabricDefaultAttributeRegistry.register(CAVE_SNAIL, CaveSnailEntity.createCaveSnailAttributes());
        FabricDefaultAttributeRegistry.register(GLOOM_STALKER, GloomStalkerEntity.createGloomStalkerAttributes());
        FabricDefaultAttributeRegistry.register(WITHERED_SKELETON, WitheredSkeletonEntity.createWitheredSkeletonAttributes());
        FabricDefaultAttributeRegistry.register(THE_ECHO_KING, TheEchoKingEntity.createTheEchoKingAttributes());
    }
}
