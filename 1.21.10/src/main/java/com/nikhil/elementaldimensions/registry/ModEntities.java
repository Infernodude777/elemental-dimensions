package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import com.nikhil.elementaldimensions.entity.boss.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Registry for all custom entities in Elemental Dimensions
 */
public class ModEntities {

    // Registry identifiers
    private static final Identifier PYROS_ID = Identifier.of(ElementalDimensions.MOD_ID, "pyros");
    private static final Identifier LEVIATHAN_ID = Identifier.of(ElementalDimensions.MOD_ID, "leviathan");
    private static final Identifier GOLIATH_ID = Identifier.of(ElementalDimensions.MOD_ID, "goliath");
    private static final Identifier ZEPHYROS_ID = Identifier.of(ElementalDimensions.MOD_ID, "zephyros");
    private static final Identifier PRIMARCH_ID = Identifier.of(ElementalDimensions.MOD_ID, "primarch");

    // Boss Entities
    public static EntityType<PyrosEntity> PYROS;
    public static EntityType<LeviathanEntity> LEVIATHAN;
    public static EntityType<GoliathEntity> GOLIATH;
    public static EntityType<ZephyrosEntity> ZEPHYROS;
    public static EntityType<PrimarchEntity> PRIMARCH;

    /**
     * Initialize all entities - called from main mod class
     */
    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering entities for " + ElementalDimensions.MOD_ID);

        PYROS = Registry.register(
                Registries.ENTITY_TYPE,
                PYROS_ID,
                EntityType.Builder.create(PyrosEntity::new, SpawnGroup.MONSTER)
                        .dimensions(2.0f, 3.0f)
                        .maxTrackingRange(64)
                        .trackingTickInterval(3)
                        .build("pyros")
        );

        LEVIATHAN = Registry.register(
                Registries.ENTITY_TYPE,
                LEVIATHAN_ID,
                EntityType.Builder.create(LeviathanEntity::new, SpawnGroup.MONSTER)
                        .dimensions(2.5f, 2.0f)
                        .maxTrackingRange(64)
                        .trackingTickInterval(3)
                        .build("leviathan")
        );

        GOLIATH = Registry.register(
                Registries.ENTITY_TYPE,
                GOLIATH_ID,
                EntityType.Builder.create(GoliathEntity::new, SpawnGroup.MONSTER)
                        .dimensions(3.0f, 4.0f)
                        .maxTrackingRange(64)
                        .trackingTickInterval(3)
                        .build("goliath")
        );

        ZEPHYROS = Registry.register(
                Registries.ENTITY_TYPE,
                ZEPHYROS_ID,
                EntityType.Builder.create(ZephyrosEntity::new, SpawnGroup.MONSTER)
                        .dimensions(1.5f, 2.5f)
                        .maxTrackingRange(64)
                        .trackingTickInterval(3)
                        .build("zephyros")
        );

        PRIMARCH = Registry.register(
                Registries.ENTITY_TYPE,
                PRIMARCH_ID,
                EntityType.Builder.create(PrimarchEntity::new, SpawnGroup.MONSTER)
                        .dimensions(3.5f, 5.0f)
                        .maxTrackingRange(96)
                        .trackingTickInterval(2)
                        .build("primarch")
        );

        ElementalDimensions.LOGGER.info("Registered 5 boss entities: Pyros, Leviathan, Goliath, Zephyros, Primarch");

        // Note: Entity attributes will be registered via Fabric API when available
        // FabricDefaultAttributeRegistry.register(PYROS, PyrosEntity.createPyrosAttributes());
        // etc...
    }
}
