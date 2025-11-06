package com.nikhil.elementaldimensions.item;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    // Dreaming Depths - Dreambound Armor
    public static final RegistryEntry<ArmorMaterial> DREAMBOUND = register("dreambound",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.0f,
            () -> Ingredient.ofItems(ModBlocks.REVERIE_BLOCK));

    // Celestine Expanse - Skyborn Armor
    public static final RegistryEntry<ArmorMaterial> SKYBORN = register("skyborn",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            18,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            1.5f,
            0.0f,
            () -> Ingredient.ofItems(ModBlocks.LUMICORE_BLOCK));

    // Fungal Dominion - Sporebound Armor
    public static final RegistryEntry<ArmorMaterial> SPOREBOUND = register("sporebound",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            14,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            1.0f,
            0.0f,
            () -> Ingredient.ofItems(ModBlocks.MYCELITE_BLOCK));

    // Forgotten Archive - Runic Armor
    public static final RegistryEntry<ArmorMaterial> RUNIC = register("runic",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            22,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0f,
            0.1f,
            () -> Ingredient.ofItems(ModBlocks.GLYPH_BLOCK));

    // Astral Frontier - Starforged Armor
    public static final RegistryEntry<ArmorMaterial> STARFORGED = register("starforged",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 4);
            }),
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            3.0f,
            0.1f,
            () -> Ingredient.ofItems(ModBlocks.CELESTITE_BLOCK));

    // Gloomy Caverns - Withered Armor
    public static final RegistryEntry<ArmorMaterial> WITHERED = register("withered",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            12,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            1.5f,
            0.0f,
            () -> Ingredient.ofItems(ModBlocks.GLOOM_BLOCK));

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense,
                                                          int enchantability, RegistryEntry<SoundEvent> equipSound,
                                                          float toughness, float knockbackResistance,
                                                          Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(Identifier.of("elementaldimensions", id))
        );

        EnumMap<ArmorItem.Type, Integer> defenseMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            if (defense.containsKey(type)) {
                defenseMap.put(type, defense.get(type));
            }
        }

        return Registry.registerReference(Registries.ARMOR_MATERIAL,
                Identifier.of("elementaldimensions", id),
                new ArmorMaterial(defenseMap, enchantability, equipSound, repairIngredient,
                        layers, toughness, knockbackResistance));
    }

    public static void initialize() {
        // This method is called to ensure the static fields are initialized
    }
}
