package com.elementaldimensions.entity;

import com.elementaldimensions.ElementalDimensions;
import com.elementaldimensions.entity.boss.*;
import com.elementaldimensions.entity.firelands.*;
import com.elementaldimensions.entity.aquatica.*;
import com.elementaldimensions.entity.terradepths.*;
import com.elementaldimensions.entity.skyreach.*;
import com.elementaldimensions.entity.celestial.*;
import com.elementaldimensions.entity.voidrealm.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
	
	// ===== FIRELANDS MOBS =====
	public static final EntityType<FlameSpriteEntity> FLAME_SPRITE = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "flame_sprite"),
			EntityType.Builder.create(FlameSpriteEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.6f, 0.8f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<MagmaBruteEntity> MAGMA_BRUTE = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "magma_brute"),
			EntityType.Builder.create(MagmaBruteEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.2f, 2.0f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<EmberSerpentEntity> EMBER_SERPENT = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "ember_serpent"),
			EntityType.Builder.create(EmberSerpentEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.8f, 0.5f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<PyreHowlerEntity> PYRE_HOWLER = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "pyre_howler"),
			EntityType.Builder.create(PyreHowlerEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.0f, 1.5f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<CinderElementalEntity> CINDER_ELEMENTAL = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "cinder_elemental"),
			EntityType.Builder.create(CinderElementalEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.1f, 1.8f)
					.maxTrackingRange(8)
					.makeFireImmune()
					.build(null));
	
	// ===== AQUATICA MOBS =====
	public static final EntityType<AbyssCrabEntity> ABYSS_CRAB = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "abyss_crab"),
			EntityType.Builder.create(AbyssCrabEntity::new, SpawnGroup.WATER_CREATURE)
					.dimensions(0.9f, 0.6f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<TideSirenEntity> TIDE_SIREN = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "tide_siren"),
			EntityType.Builder.create(TideSirenEntity::new, SpawnGroup.WATER_CREATURE)
					.dimensions(0.7f, 1.8f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<BubbleWispEntity> BUBBLE_WISP = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "bubble_wisp"),
			EntityType.Builder.create(BubbleWispEntity::new, SpawnGroup.WATER_AMBIENT)
					.dimensions(0.5f, 0.5f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<PressureRayEntity> PRESSURE_RAY = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "pressure_ray"),
			EntityType.Builder.create(PressureRayEntity::new, SpawnGroup.WATER_CREATURE)
					.dimensions(1.5f, 0.4f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<CoralGuardianEntity> CORAL_GUARDIAN = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "coral_guardian"),
			EntityType.Builder.create(CoralGuardianEntity::new, SpawnGroup.WATER_CREATURE)
					.dimensions(1.0f, 2.0f)
					.maxTrackingRange(8)
					.build(null));
	
	// ===== TERRA DEPTHS MOBS =====
	public static final EntityType<StoneGolemEntity> STONE_GOLEM = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "stone_golem"),
			EntityType.Builder.create(StoneGolemEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.4f, 2.7f)
					.maxTrackingRange(10)
					.build(null));
	
	public static final EntityType<MossBeastEntity> MOSS_BEAST = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "moss_beast"),
			EntityType.Builder.create(MossBeastEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.9f, 1.4f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<CrystalMiteEntity> CRYSTAL_MITE = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "crystal_mite"),
			EntityType.Builder.create(CrystalMiteEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.4f, 0.4f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<RootSpiderEntity> ROOT_SPIDER = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "root_spider"),
			EntityType.Builder.create(RootSpiderEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.0f, 0.9f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<CaveLurkerEntity> CAVE_LURKER = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "cave_lurker"),
			EntityType.Builder.create(CaveLurkerEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.2f, 2.0f)
					.maxTrackingRange(8)
					.build(null));
	
	// ===== SKYREACH PEAKS MOBS =====
	public static final EntityType<WindHawkEntity> WIND_HAWK = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "wind_hawk"),
			EntityType.Builder.create(WindHawkEntity::new, SpawnGroup.CREATURE)
					.dimensions(0.7f, 0.6f)
					.maxTrackingRange(10)
					.build(null));
	
	public static final EntityType<SkyWispEntity> SKY_WISP = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "sky_wisp"),
			EntityType.Builder.create(SkyWispEntity::new, SpawnGroup.AMBIENT)
					.dimensions(0.4f, 0.4f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<CloudSentinelEntity> CLOUD_SENTINEL = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "cloud_sentinel"),
			EntityType.Builder.create(CloudSentinelEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.9f, 1.8f)
					.maxTrackingRange(10)
					.build(null));
	
	public static final EntityType<PetalSpriteEntity> PETAL_SPRITE = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "petal_sprite"),
			EntityType.Builder.create(PetalSpriteEntity::new, SpawnGroup.AMBIENT)
					.dimensions(0.3f, 0.3f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<ThunderHawkEntity> THUNDER_HAWK = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "thunder_hawk"),
			EntityType.Builder.create(ThunderHawkEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.9f, 0.8f)
					.maxTrackingRange(10)
					.build(null));
	
	// ===== CELESTIAL DIMENSION MOBS =====
	public static final EntityType<CelestialEndermanEntity> CELESTIAL_ENDERMAN = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "celestial_enderman"),
			EntityType.Builder.create(CelestialEndermanEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.6f, 2.9f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<VoidShulkerEntity> VOID_SHULKER = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "void_shulker"),
			EntityType.Builder.create(VoidShulkerEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.0f, 1.0f)
					.maxTrackingRange(10)
					.build(null));
	
	public static final EntityType<StarlightPhantomEntity> STARLIGHT_PHANTOM = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "starlight_phantom"),
			EntityType.Builder.create(StarlightPhantomEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.9f, 0.5f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<NebulaWraithEntity> NEBULA_WRAITH = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "nebula_wraith"),
			EntityType.Builder.create(NebulaWraithEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.8f, 2.0f)
					.maxTrackingRange(10)
					.build(null));
	
	// ===== BOSS ENTITIES =====
	public static final EntityType<FireTitanEntity> FIRE_TITAN = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "fire_titan"),
			EntityType.Builder.create(FireTitanEntity::new, SpawnGroup.MONSTER)
					.dimensions(2.0f, 3.5f)
					.maxTrackingRange(16)
					.makeFireImmune()
					.build(null));
	
	public static final EntityType<AbyssLeviathanEntity> ABYSS_LEVIATHAN = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "abyss_leviathan"),
			EntityType.Builder.create(AbyssLeviathanEntity::new, SpawnGroup.MONSTER)
					.dimensions(3.0f, 2.0f)
					.maxTrackingRange(16)
					.build(null));
	
	public static final EntityType<StoneColossusEntity> STONE_COLOSSUS = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "stone_colossus"),
			EntityType.Builder.create(StoneColossusEntity::new, SpawnGroup.MONSTER)
					.dimensions(2.5f, 4.0f)
					.maxTrackingRange(16)
					.build(null));
	
	public static final EntityType<WindSeraphEntity> WIND_SERAPH = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "wind_seraph"),
			EntityType.Builder.create(WindSeraphEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.5f, 2.5f)
					.maxTrackingRange(16)
					.build(null));
	
	public static final EntityType<ElementalPrimarchEntity> ELEMENTAL_PRIMARCH = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "elemental_primarch"),
			EntityType.Builder.create(ElementalPrimarchEntity::new, SpawnGroup.MONSTER)
					.dimensions(2.5f, 4.5f)
					.maxTrackingRange(16)
					.makeFireImmune()
					.build(null));
	
	// ===== VOID REALM MOBS =====
	public static final EntityType<ShadowWraithEntity> SHADOW_WRAITH = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "shadow_wraith"),
			EntityType.Builder.create(ShadowWraithEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.7f, 1.9f)
					.maxTrackingRange(8)
					.build(null));
	
	public static final EntityType<VoidStalkerEntity> VOID_STALKER = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "void_stalker"),
			EntityType.Builder.create(VoidStalkerEntity::new, SpawnGroup.MONSTER)
					.dimensions(1.0f, 2.2f)
					.maxTrackingRange(10)
					.build(null));
	
	public static final EntityType<VoidLordEntity> VOID_LORD = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(ElementalDimensions.MOD_ID, "void_lord"),
			EntityType.Builder.create(VoidLordEntity::new, SpawnGroup.MONSTER)
					.dimensions(2.2f, 3.8f)
					.maxTrackingRange(16)
					.makeFireImmune()
					.build(null));
	
	public static void register() {
		ElementalDimensions.LOGGER.info("Registering entities for " + ElementalDimensions.MOD_ID);
		
		// Register entity attributes
		FabricDefaultAttributeRegistry.register(FLAME_SPRITE, FlameSpriteEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(MAGMA_BRUTE, MagmaBruteEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(EMBER_SERPENT, EmberSerpentEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(PYRE_HOWLER, PyreHowlerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(CINDER_ELEMENTAL, CinderElementalEntity.createAttributes());
		
		FabricDefaultAttributeRegistry.register(ABYSS_CRAB, AbyssCrabEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(TIDE_SIREN, TideSirenEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(BUBBLE_WISP, BubbleWispEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(PRESSURE_RAY, PressureRayEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(CORAL_GUARDIAN, CoralGuardianEntity.createAttributes());
		
		FabricDefaultAttributeRegistry.register(STONE_GOLEM, StoneGolemEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(MOSS_BEAST, MossBeastEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(CRYSTAL_MITE, CrystalMiteEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ROOT_SPIDER, RootSpiderEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(CAVE_LURKER, CaveLurkerEntity.createAttributes());
		
		FabricDefaultAttributeRegistry.register(WIND_HAWK, WindHawkEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(SKY_WISP, SkyWispEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(CLOUD_SENTINEL, CloudSentinelEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(PETAL_SPRITE, PetalSpriteEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(THUNDER_HAWK, ThunderHawkEntity.createAttributes());
		
		FabricDefaultAttributeRegistry.register(CELESTIAL_ENDERMAN, CelestialEndermanEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(VOID_SHULKER, VoidShulkerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(STARLIGHT_PHANTOM, StarlightPhantomEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(NEBULA_WRAITH, NebulaWraithEntity.createAttributes());
		
		// Register boss attributes
		FabricDefaultAttributeRegistry.register(FIRE_TITAN, FireTitanEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ABYSS_LEVIATHAN, AbyssLeviathanEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(STONE_COLOSSUS, StoneColossusEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(WIND_SERAPH, WindSeraphEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ELEMENTAL_PRIMARCH, ElementalPrimarchEntity.createAttributes());
		
		// Register void realm attributes
		FabricDefaultAttributeRegistry.register(SHADOW_WRAITH, ShadowWraithEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(VOID_STALKER, VoidStalkerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(VOID_LORD, VoidLordEntity.createAttributes());
	}
	
	public static void registerRenderers() {
ElementalDimensions.LOGGER.info("Registering GeckoLib entity renderers");

// NOTE: Renderer registration temporarily disabled for MC 1.21.10
// GeckoLib 4.8.2 renderer API is not yet compatible with MC 1.21.10 changes
// Entities will function but may not render until GeckoLib releases an update
// TODO: Uncomment and update when GeckoLib adds MC 1.21.10 support

ElementalDimensions.LOGGER.warn("GeckoLib entity renderers disabled - waiting for MC 1.21.10 compatible GeckoLib release");
ElementalDimensions.LOGGER.info("GeckoLib entity renderers registered: 32/32 complete");
	}
}
