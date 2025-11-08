package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import com.nikhil.elementaldimensions.item.ModToolMaterials;
import com.nikhil.elementaldimensions.item.ModArmorMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item FIRE_ESSENCE;
    public static Item WATER_ESSENCE;
    public static Item EARTH_ESSENCE;
    public static Item AIR_ESSENCE;

    public static Item INFERNO_HEART;
    public static Item AQUATIC_HEART;
    public static Item TERRAN_HEART;
    public static Item SKYBOUND_HEART;

    // Dreaming Depths - Reverie Tools & Armor
    public static Item REVERIE_SWORD;
    public static Item REVERIE_PICKAXE;
    public static Item REVERIE_AXE;
    public static Item REVERIE_SHOVEL;
    public static Item REVERIE_HOE;
    public static Item DREAMBOUND_HELMET;
    public static Item DREAMBOUND_CHESTPLATE;
    public static Item DREAMBOUND_LEGGINGS;
    public static Item DREAMBOUND_BOOTS;

    // Celestine Expanse - Lumicore Tools & Armor
    public static Item LUMICORE_SWORD;
    public static Item LUMICORE_PICKAXE;
    public static Item LUMICORE_AXE;
    public static Item LUMICORE_SHOVEL;
    public static Item LUMICORE_HOE;
    public static Item SKYBORN_HELMET;
    public static Item SKYBORN_CHESTPLATE;
    public static Item SKYBORN_LEGGINGS;
    public static Item SKYBORN_BOOTS;

    // Fungal Dominion - Mycelite Tools & Armor
    public static Item MYCELITE_SWORD;
    public static Item MYCELITE_PICKAXE;
    public static Item MYCELITE_AXE;
    public static Item MYCELITE_SHOVEL;
    public static Item MYCELITE_HOE;
    public static Item SPOREBOUND_HELMET;
    public static Item SPOREBOUND_CHESTPLATE;
    public static Item SPOREBOUND_LEGGINGS;
    public static Item SPOREBOUND_BOOTS;

    // Forgotten Archive - Glyph Tools & Armor
    public static Item GLYPH_SWORD;
    public static Item GLYPH_PICKAXE;
    public static Item GLYPH_AXE;
    public static Item GLYPH_SHOVEL;
    public static Item GLYPH_HOE;
    public static Item RUNIC_HELMET;
    public static Item RUNIC_CHESTPLATE;
    public static Item RUNIC_LEGGINGS;
    public static Item RUNIC_BOOTS;

    // Astral Frontier - Celestite Tools & Armor
    public static Item CELESTITE_SWORD;
    public static Item CELESTITE_PICKAXE;
    public static Item CELESTITE_AXE;
    public static Item CELESTITE_SHOVEL;
    public static Item CELESTITE_HOE;
    public static Item STARFORGED_HELMET;
    public static Item STARFORGED_CHESTPLATE;
    public static Item STARFORGED_LEGGINGS;
    public static Item STARFORGED_BOOTS;

    // Gloomy Caverns - Gloom Tools & Armor
    public static Item GLOOM_SWORD;
    public static Item GLOOM_PICKAXE;
    public static Item GLOOM_AXE;
    public static Item GLOOM_SHOVEL;
    public static Item GLOOM_HOE;
    public static Item WITHERED_HELMET;
    public static Item WITHERED_CHESTPLATE;
    public static Item WITHERED_LEGGINGS;
    public static Item WITHERED_BOOTS;

    public static Item PHANTASM_SPAWN_EGG;
    public static Item LULLER_SPAWN_EGG;
    public static Item NIGHTMARE_SHADE_SPAWN_EGG;
    public static Item SOMNIAN_BUTTERFLY_SPAWN_EGG;
    public static Item SKYWHALE_SPAWN_EGG;
    public static Item LUMIFIN_SPAWN_EGG;
    public static Item STORM_HAWK_SPAWN_EGG;
    public static Item SKY_PIRATE_SPAWN_EGG;
    public static Item SPORE_BAT_SPAWN_EGG;
    public static Item MYCELIUM_WALKER_SPAWN_EGG;
    public static Item MYCELITE_CREEPER_SPAWN_EGG;
    public static Item FUNGAL_ZOMBIE_SPAWN_EGG;
    public static Item SCRIBE_SPIRIT_SPAWN_EGG;
    public static Item LIVING_TOME_SPAWN_EGG;
    public static Item SENTINEL_SPAWN_EGG;
    public static Item PAGE_HORROR_SPAWN_EGG;
    public static Item STAR_ELEMENTAL_SPAWN_EGG;
    public static Item NEBULA_WISP_SPAWN_EGG;
    public static Item VOIDLING_SPAWN_EGG;
    public static Item COSMIC_HORROR_SPAWN_EGG;
    public static Item ECHO_WISP_SPAWN_EGG;
    public static Item CAVE_SNAIL_SPAWN_EGG;
    public static Item GLOOM_STALKER_SPAWN_EGG;
    public static Item WITHERED_SKELETON_SPAWN_EGG;

    // Boss spawn eggs
    public static Item PYROS_SPAWN_EGG;
    public static Item LEVIATHAN_SPAWN_EGG;
    public static Item GOLIATH_SPAWN_EGG;
    public static Item ZEPHYROS_SPAWN_EGG;
    public static Item PRIMARCH_SPAWN_EGG;
    public static Item THE_DREAMER_SPAWN_EGG;
    public static Item THE_RADIANT_MONARCH_SPAWN_EGG;
    public static Item THE_OVERMIND_SPAWN_EGG;
    public static Item THE_LIBRARIAN_SPAWN_EGG;
    public static Item THE_VOID_ARCHON_SPAWN_EGG;
    public static Item THE_ECHO_KING_SPAWN_EGG;

    // Portal Igniters
    public static Item DREAMING_DEPTHS_IGNITER;
    public static Item INFERNO_REALM_IGNITER;
    public static Item RADIANT_PLATEAU_IGNITER;
    public static Item MYCELIAL_NEXUS_IGNITER;
    public static Item GOLDEN_CITADEL_IGNITER;
    public static Item ANCIENT_GROVES_IGNITER;
    public static Item COSMIC_VOID_IGNITER;
    public static Item CELESTIAL_SANCTUM_IGNITER;
    public static Item GLYPHIC_ARCHIVES_IGNITER;
    public static Item ECHOING_EXPANSE_IGNITER;
    public static Item UMBRAL_WASTELAND_IGNITER;

    public static final RegistryKey<ItemGroup> ELEMENTAL_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(ElementalDimensions.MOD_ID, "elemental_group"));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ElementalDimensions.MOD_ID, name), item);
    }

    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering items");

        // Initialize armor materials
        ModArmorMaterials.initialize();

        // Register essences
        FIRE_ESSENCE = registerItem("fire_essence", new Item(new Item.Settings()));
        WATER_ESSENCE = registerItem("water_essence", new Item(new Item.Settings()));
        EARTH_ESSENCE = registerItem("earth_essence", new Item(new Item.Settings()));
        AIR_ESSENCE = registerItem("air_essence", new Item(new Item.Settings()));

        INFERNO_HEART = registerItem("inferno_heart", new Item(new Item.Settings().maxCount(1)));
        AQUATIC_HEART = registerItem("aquatic_heart", new Item(new Item.Settings().maxCount(1)));
        TERRAN_HEART = registerItem("terran_heart", new Item(new Item.Settings().maxCount(1)));
        SKYBOUND_HEART = registerItem("skybound_heart", new Item(new Item.Settings().maxCount(1)));

        // Dreaming Depths - Reverie Tools & Armor
        REVERIE_SWORD = registerItem("reverie_sword", new SwordItem(ModToolMaterials.REVERIE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.REVERIE, 3, -2.4f))));
        REVERIE_PICKAXE = registerItem("reverie_pickaxe", new PickaxeItem(ModToolMaterials.REVERIE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.REVERIE, 1.0f, -2.8f))));
        REVERIE_AXE = registerItem("reverie_axe", new AxeItem(ModToolMaterials.REVERIE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.REVERIE, 5.0f, -3.0f))));
        REVERIE_SHOVEL = registerItem("reverie_shovel", new ShovelItem(ModToolMaterials.REVERIE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.REVERIE, 1.5f, -3.0f))));
        REVERIE_HOE = registerItem("reverie_hoe", new HoeItem(ModToolMaterials.REVERIE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.REVERIE, -4.0f, 0.0f))));
        DREAMBOUND_HELMET = registerItem("dreambound_helmet", new ArmorItem(ModArmorMaterials.DREAMBOUND, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33))));
        DREAMBOUND_CHESTPLATE = registerItem("dreambound_chestplate", new ArmorItem(ModArmorMaterials.DREAMBOUND, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(33))));
        DREAMBOUND_LEGGINGS = registerItem("dreambound_leggings", new ArmorItem(ModArmorMaterials.DREAMBOUND, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(33))));
        DREAMBOUND_BOOTS = registerItem("dreambound_boots", new ArmorItem(ModArmorMaterials.DREAMBOUND, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(33))));

        // Celestine Expanse - Lumicore Tools & Armor
        LUMICORE_SWORD = registerItem("lumicore_sword", new SwordItem(ModToolMaterials.LUMICORE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.LUMICORE, 3, -2.4f))));
        LUMICORE_PICKAXE = registerItem("lumicore_pickaxe", new PickaxeItem(ModToolMaterials.LUMICORE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.LUMICORE, 1.0f, -2.8f))));
        LUMICORE_AXE = registerItem("lumicore_axe", new AxeItem(ModToolMaterials.LUMICORE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.LUMICORE, 5.0f, -3.0f))));
        LUMICORE_SHOVEL = registerItem("lumicore_shovel", new ShovelItem(ModToolMaterials.LUMICORE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.LUMICORE, 1.5f, -3.0f))));
        LUMICORE_HOE = registerItem("lumicore_hoe", new HoeItem(ModToolMaterials.LUMICORE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.LUMICORE, -3.0f, 0.0f))));
        SKYBORN_HELMET = registerItem("skyborn_helmet", new ArmorItem(ModArmorMaterials.SKYBORN, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(30))));
        SKYBORN_CHESTPLATE = registerItem("skyborn_chestplate", new ArmorItem(ModArmorMaterials.SKYBORN, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(30))));
        SKYBORN_LEGGINGS = registerItem("skyborn_leggings", new ArmorItem(ModArmorMaterials.SKYBORN, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(30))));
        SKYBORN_BOOTS = registerItem("skyborn_boots", new ArmorItem(ModArmorMaterials.SKYBORN, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(30))));

        // Fungal Dominion - Mycelite Tools & Armor
        MYCELITE_SWORD = registerItem("mycelite_sword", new SwordItem(ModToolMaterials.MYCELITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.MYCELITE, 3, -2.4f))));
        MYCELITE_PICKAXE = registerItem("mycelite_pickaxe", new PickaxeItem(ModToolMaterials.MYCELITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.MYCELITE, 1.0f, -2.8f))));
        MYCELITE_AXE = registerItem("mycelite_axe", new AxeItem(ModToolMaterials.MYCELITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.MYCELITE, 6.0f, -3.1f))));
        MYCELITE_SHOVEL = registerItem("mycelite_shovel", new ShovelItem(ModToolMaterials.MYCELITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.MYCELITE, 1.5f, -3.0f))));
        MYCELITE_HOE = registerItem("mycelite_hoe", new HoeItem(ModToolMaterials.MYCELITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.MYCELITE, -2.0f, -1.0f))));
        SPOREBOUND_HELMET = registerItem("sporebound_helmet", new ArmorItem(ModArmorMaterials.SPOREBOUND, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(25))));
        SPOREBOUND_CHESTPLATE = registerItem("sporebound_chestplate", new ArmorItem(ModArmorMaterials.SPOREBOUND, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(25))));
        SPOREBOUND_LEGGINGS = registerItem("sporebound_leggings", new ArmorItem(ModArmorMaterials.SPOREBOUND, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(25))));
        SPOREBOUND_BOOTS = registerItem("sporebound_boots", new ArmorItem(ModArmorMaterials.SPOREBOUND, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(25))));

        // Forgotten Archive - Glyph Tools & Armor
        GLYPH_SWORD = registerItem("glyph_sword", new SwordItem(ModToolMaterials.GLYPH, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.GLYPH, 3, -2.4f))));
        GLYPH_PICKAXE = registerItem("glyph_pickaxe", new PickaxeItem(ModToolMaterials.GLYPH, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.GLYPH, 1.0f, -2.8f))));
        GLYPH_AXE = registerItem("glyph_axe", new AxeItem(ModToolMaterials.GLYPH, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.GLYPH, 5.0f, -3.0f))));
        GLYPH_SHOVEL = registerItem("glyph_shovel", new ShovelItem(ModToolMaterials.GLYPH, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.GLYPH, 1.5f, -3.0f))));
        GLYPH_HOE = registerItem("glyph_hoe", new HoeItem(ModToolMaterials.GLYPH, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.GLYPH, -4.0f, 0.0f))));
        RUNIC_HELMET = registerItem("runic_helmet", new ArmorItem(ModArmorMaterials.RUNIC, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(35))));
        RUNIC_CHESTPLATE = registerItem("runic_chestplate", new ArmorItem(ModArmorMaterials.RUNIC, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(35))));
        RUNIC_LEGGINGS = registerItem("runic_leggings", new ArmorItem(ModArmorMaterials.RUNIC, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(35))));
        RUNIC_BOOTS = registerItem("runic_boots", new ArmorItem(ModArmorMaterials.RUNIC, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(35))));

        // Astral Frontier - Celestite Tools & Armor
        CELESTITE_SWORD = registerItem("celestite_sword", new SwordItem(ModToolMaterials.CELESTITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.CELESTITE, 3, -2.4f))));
        CELESTITE_PICKAXE = registerItem("celestite_pickaxe", new PickaxeItem(ModToolMaterials.CELESTITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.CELESTITE, 1.0f, -2.8f))));
        CELESTITE_AXE = registerItem("celestite_axe", new AxeItem(ModToolMaterials.CELESTITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.CELESTITE, 5.0f, -3.0f))));
        CELESTITE_SHOVEL = registerItem("celestite_shovel", new ShovelItem(ModToolMaterials.CELESTITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.CELESTITE, 1.5f, -3.0f))));
        CELESTITE_HOE = registerItem("celestite_hoe", new HoeItem(ModToolMaterials.CELESTITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.CELESTITE, -5.0f, 0.0f))));
        STARFORGED_HELMET = registerItem("starforged_helmet", new ArmorItem(ModArmorMaterials.STARFORGED, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))));
        STARFORGED_CHESTPLATE = registerItem("starforged_chestplate", new ArmorItem(ModArmorMaterials.STARFORGED, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))));
        STARFORGED_LEGGINGS = registerItem("starforged_leggings", new ArmorItem(ModArmorMaterials.STARFORGED, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))));
        STARFORGED_BOOTS = registerItem("starforged_boots", new ArmorItem(ModArmorMaterials.STARFORGED, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))));

        // Gloomy Caverns - Gloom Tools & Armor
        GLOOM_SWORD = registerItem("gloom_sword", new SwordItem(ModToolMaterials.GLOOM, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.GLOOM, 3, -2.4f))));
        GLOOM_PICKAXE = registerItem("gloom_pickaxe", new PickaxeItem(ModToolMaterials.GLOOM, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.GLOOM, 1.0f, -2.8f))));
        GLOOM_AXE = registerItem("gloom_axe", new AxeItem(ModToolMaterials.GLOOM, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.GLOOM, 6.0f, -3.2f))));
        GLOOM_SHOVEL = registerItem("gloom_shovel", new ShovelItem(ModToolMaterials.GLOOM, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.GLOOM, 1.5f, -3.0f))));
        GLOOM_HOE = registerItem("gloom_hoe", new HoeItem(ModToolMaterials.GLOOM, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.GLOOM, -1.0f, -2.0f))));
        WITHERED_HELMET = registerItem("withered_helmet", new ArmorItem(ModArmorMaterials.WITHERED, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(27))));
        WITHERED_CHESTPLATE = registerItem("withered_chestplate", new ArmorItem(ModArmorMaterials.WITHERED, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(27))));
        WITHERED_LEGGINGS = registerItem("withered_leggings", new ArmorItem(ModArmorMaterials.WITHERED, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(27))));
        WITHERED_BOOTS = registerItem("withered_boots", new ArmorItem(ModArmorMaterials.WITHERED, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(27))));

        PHANTASM_SPAWN_EGG = registerItem("phantasm_spawn_egg", new SpawnEggItem(ModEntities.PHANTASM, 0x4B0082, 0x8A2BE2, new Item.Settings()));
        LULLER_SPAWN_EGG = registerItem("luller_spawn_egg", new SpawnEggItem(ModEntities.LULLER, 0xE6E6FA, 0xD8BFD8, new Item.Settings()));
        NIGHTMARE_SHADE_SPAWN_EGG = registerItem("nightmare_shade_spawn_egg", new SpawnEggItem(ModEntities.NIGHTMARE_SHADE, 0x1C1C1C, 0x8B0000, new Item.Settings()));
        SOMNIAN_BUTTERFLY_SPAWN_EGG = registerItem("somnian_butterfly_spawn_egg", new SpawnEggItem(ModEntities.SOMNIAN_BUTTERFLY, 0xFFB6C1, 0xFFC0CB, new Item.Settings()));
        SKYWHALE_SPAWN_EGG = registerItem("skywhale_spawn_egg", new SpawnEggItem(ModEntities.SKYWHALE, 0x87CEEB, 0xB0E0E6, new Item.Settings()));
        LUMIFIN_SPAWN_EGG = registerItem("lumifin_spawn_egg", new SpawnEggItem(ModEntities.LUMIFIN, 0xFFFF00, 0xFFD700, new Item.Settings()));
        STORM_HAWK_SPAWN_EGG = registerItem("storm_hawk_spawn_egg", new SpawnEggItem(ModEntities.STORM_HAWK, 0x708090, 0x2F4F4F, new Item.Settings()));
        SKY_PIRATE_SPAWN_EGG = registerItem("sky_pirate_spawn_egg", new SpawnEggItem(ModEntities.SKY_PIRATE, 0x8B4513, 0x696969, new Item.Settings()));
        SPORE_BAT_SPAWN_EGG = registerItem("spore_bat_spawn_egg", new SpawnEggItem(ModEntities.SPORE_BAT, 0x9ACD32, 0x6B8E23, new Item.Settings()));
        MYCELIUM_WALKER_SPAWN_EGG = registerItem("mycelium_walker_spawn_egg", new SpawnEggItem(ModEntities.MYCELIUM_WALKER, 0x8FBC8F, 0x556B2F, new Item.Settings()));
        MYCELITE_CREEPER_SPAWN_EGG = registerItem("mycelite_creeper_spawn_egg", new SpawnEggItem(ModEntities.MYCELITE_CREEPER, 0x228B22, 0x008000, new Item.Settings()));
        FUNGAL_ZOMBIE_SPAWN_EGG = registerItem("fungal_zombie_spawn_egg", new SpawnEggItem(ModEntities.FUNGAL_ZOMBIE, 0x556B2F, 0x3CB371, new Item.Settings()));
        SCRIBE_SPIRIT_SPAWN_EGG = registerItem("scribe_spirit_spawn_egg", new SpawnEggItem(ModEntities.SCRIBE_SPIRIT, 0xF0F8FF, 0xE0FFFF, new Item.Settings()));
        LIVING_TOME_SPAWN_EGG = registerItem("living_tome_spawn_egg", new SpawnEggItem(ModEntities.LIVING_TOME, 0x8B4513, 0xF5DEB3, new Item.Settings()));
        SENTINEL_SPAWN_EGG = registerItem("sentinel_spawn_egg", new SpawnEggItem(ModEntities.SENTINEL, 0x708090, 0xC0C0C0, new Item.Settings()));
        PAGE_HORROR_SPAWN_EGG = registerItem("page_horror_spawn_egg", new SpawnEggItem(ModEntities.PAGE_HORROR, 0x2F4F4F, 0xFFFFE0, new Item.Settings()));
        STAR_ELEMENTAL_SPAWN_EGG = registerItem("star_elemental_spawn_egg", new SpawnEggItem(ModEntities.STAR_ELEMENTAL, 0xFFFFFF, 0xFFD700, new Item.Settings()));
        NEBULA_WISP_SPAWN_EGG = registerItem("nebula_wisp_spawn_egg", new SpawnEggItem(ModEntities.NEBULA_WISP, 0x9370DB, 0xBA55D3, new Item.Settings()));
        VOIDLING_SPAWN_EGG = registerItem("voidling_spawn_egg", new SpawnEggItem(ModEntities.VOIDLING, 0x191970, 0x4B0082, new Item.Settings()));
        COSMIC_HORROR_SPAWN_EGG = registerItem("cosmic_horror_spawn_egg", new SpawnEggItem(ModEntities.COSMIC_HORROR, 0x000000, 0x8A2BE2, new Item.Settings()));
        ECHO_WISP_SPAWN_EGG = registerItem("echo_wisp_spawn_egg", new SpawnEggItem(ModEntities.ECHO_WISP, 0x36454F, 0x708090, new Item.Settings()));
        CAVE_SNAIL_SPAWN_EGG = registerItem("cave_snail_spawn_egg", new SpawnEggItem(ModEntities.CAVE_SNAIL, 0x8B7355, 0xD2B48C, new Item.Settings()));
        GLOOM_STALKER_SPAWN_EGG = registerItem("gloom_stalker_spawn_egg", new SpawnEggItem(ModEntities.GLOOM_STALKER, 0x2F4F4F, 0x000000, new Item.Settings()));
        WITHERED_SKELETON_SPAWN_EGG = registerItem("withered_skeleton_spawn_egg", new SpawnEggItem(ModEntities.WITHERED_SKELETON, 0x696969, 0x2F4F4F, new Item.Settings()));

        // Boss spawn eggs
        PYROS_SPAWN_EGG = registerItem("pyros_spawn_egg", new SpawnEggItem(ModEntities.PYROS, 0xFF4500, 0xB22222, new Item.Settings()));
        LEVIATHAN_SPAWN_EGG = registerItem("leviathan_spawn_egg", new SpawnEggItem(ModEntities.LEVIATHAN, 0x1E90FF, 0x000080, new Item.Settings()));
        GOLIATH_SPAWN_EGG = registerItem("goliath_spawn_egg", new SpawnEggItem(ModEntities.GOLIATH, 0x8B4513, 0x654321, new Item.Settings()));
        ZEPHYROS_SPAWN_EGG = registerItem("zephyros_spawn_egg", new SpawnEggItem(ModEntities.ZEPHYROS, 0xF0F8FF, 0xB0C4DE, new Item.Settings()));
        PRIMARCH_SPAWN_EGG = registerItem("primarch_spawn_egg", new SpawnEggItem(ModEntities.PRIMARCH, 0x4B0082, 0x8B008B, new Item.Settings()));
        THE_DREAMER_SPAWN_EGG = registerItem("the_dreamer_spawn_egg", new SpawnEggItem(ModEntities.THE_DREAMER, 0x9370DB, 0xDA70D6, new Item.Settings()));
        THE_RADIANT_MONARCH_SPAWN_EGG = registerItem("the_radiant_monarch_spawn_egg", new SpawnEggItem(ModEntities.THE_RADIANT_MONARCH, 0xFFD700, 0xFFA500, new Item.Settings()));
        THE_OVERMIND_SPAWN_EGG = registerItem("the_overmind_spawn_egg", new SpawnEggItem(ModEntities.THE_OVERMIND, 0x2E8B57, 0x006400, new Item.Settings()));
        THE_LIBRARIAN_SPAWN_EGG = registerItem("the_librarian_spawn_egg", new SpawnEggItem(ModEntities.THE_LIBRARIAN, 0xDEB887, 0x8B4513, new Item.Settings()));
        THE_VOID_ARCHON_SPAWN_EGG = registerItem("the_void_archon_spawn_egg", new SpawnEggItem(ModEntities.THE_VOID_ARCHON, 0x000000, 0x483D8B, new Item.Settings()));
        THE_ECHO_KING_SPAWN_EGG = registerItem("the_echo_king_spawn_egg", new SpawnEggItem(ModEntities.THE_ECHO_KING, 0x696969, 0x2F4F4F, new Item.Settings()));

        // Portal Igniters
        DREAMING_DEPTHS_IGNITER = registerItem("dreaming_depths_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.DREAMING_DEPTHS_PORTAL, ModBlocks.DREAMSTONE, new Item.Settings().maxCount(1)));
        INFERNO_REALM_IGNITER = registerItem("inferno_realm_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.INFERNO_REALM_PORTAL, ModBlocks.INFERNO_STONE, new Item.Settings().maxCount(1)));
        RADIANT_PLATEAU_IGNITER = registerItem("radiant_plateau_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.RADIANT_PLATEAU_PORTAL, ModBlocks.RADIANT_BLOCK, new Item.Settings().maxCount(1)));
        MYCELIAL_NEXUS_IGNITER = registerItem("mycelial_nexus_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.MYCELIAL_NEXUS_PORTAL, ModBlocks.FUNGAL_BARK, new Item.Settings().maxCount(1)));
        GOLDEN_CITADEL_IGNITER = registerItem("golden_citadel_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.GOLDEN_CITADEL_PORTAL, ModBlocks.GOLDEN_PILLAR, new Item.Settings().maxCount(1)));
        ANCIENT_GROVES_IGNITER = registerItem("ancient_groves_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.ANCIENT_GROVES_PORTAL, ModBlocks.REVERIE_BLOCK, new Item.Settings().maxCount(1)));
        COSMIC_VOID_IGNITER = registerItem("cosmic_void_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.COSMIC_VOID_PORTAL, ModBlocks.VOID_STONE, new Item.Settings().maxCount(1)));
        CELESTIAL_SANCTUM_IGNITER = registerItem("celestial_sanctum_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.CELESTIAL_SANCTUM_PORTAL, ModBlocks.CELESTIAL_STONE, new Item.Settings().maxCount(1)));
        GLYPHIC_ARCHIVES_IGNITER = registerItem("glyphic_archives_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.GLYPHIC_ARCHIVES_PORTAL, ModBlocks.GLYPH_BLOCK, new Item.Settings().maxCount(1)));
        ECHOING_EXPANSE_IGNITER = registerItem("echoing_expanse_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.ECHOING_EXPANSE_PORTAL, ModBlocks.ECHO_STONE, new Item.Settings().maxCount(1)));
        UMBRAL_WASTELAND_IGNITER = registerItem("umbral_wasteland_igniter",
            new com.nikhil.elementaldimensions.item.PortalIgniterItem(ModBlocks.UMBRAL_WASTELAND_PORTAL, ModBlocks.SHADESTONE, new Item.Settings().maxCount(1)));

        // Register item group
        Registry.register(Registries.ITEM_GROUP, ELEMENTAL_GROUP,
            FabricItemGroup.builder()
                .icon(() -> new ItemStack(FIRE_ESSENCE))
                .displayName(Text.translatable("itemGroup.elementaldimensions.elemental_group"))
                .entries((context, entries) -> {
                    entries.add(FIRE_ESSENCE);
                    entries.add(WATER_ESSENCE);
                    entries.add(EARTH_ESSENCE);
                    entries.add(AIR_ESSENCE);
                    entries.add(INFERNO_HEART);
                    entries.add(AQUATIC_HEART);
                    entries.add(TERRAN_HEART);
                    entries.add(SKYBOUND_HEART);

                    // Reverie tools and Dreambound armor
                    entries.add(REVERIE_SWORD);
                    entries.add(REVERIE_PICKAXE);
                    entries.add(REVERIE_AXE);
                    entries.add(REVERIE_SHOVEL);
                    entries.add(REVERIE_HOE);
                    entries.add(DREAMBOUND_HELMET);
                    entries.add(DREAMBOUND_CHESTPLATE);
                    entries.add(DREAMBOUND_LEGGINGS);
                    entries.add(DREAMBOUND_BOOTS);

                    // Lumicore tools and Skyborn armor
                    entries.add(LUMICORE_SWORD);
                    entries.add(LUMICORE_PICKAXE);
                    entries.add(LUMICORE_AXE);
                    entries.add(LUMICORE_SHOVEL);
                    entries.add(LUMICORE_HOE);
                    entries.add(SKYBORN_HELMET);
                    entries.add(SKYBORN_CHESTPLATE);
                    entries.add(SKYBORN_LEGGINGS);
                    entries.add(SKYBORN_BOOTS);

                    // Mycelite tools and Sporebound armor
                    entries.add(MYCELITE_SWORD);
                    entries.add(MYCELITE_PICKAXE);
                    entries.add(MYCELITE_AXE);
                    entries.add(MYCELITE_SHOVEL);
                    entries.add(MYCELITE_HOE);
                    entries.add(SPOREBOUND_HELMET);
                    entries.add(SPOREBOUND_CHESTPLATE);
                    entries.add(SPOREBOUND_LEGGINGS);
                    entries.add(SPOREBOUND_BOOTS);

                    // Glyph tools and Runic armor
                    entries.add(GLYPH_SWORD);
                    entries.add(GLYPH_PICKAXE);
                    entries.add(GLYPH_AXE);
                    entries.add(GLYPH_SHOVEL);
                    entries.add(GLYPH_HOE);
                    entries.add(RUNIC_HELMET);
                    entries.add(RUNIC_CHESTPLATE);
                    entries.add(RUNIC_LEGGINGS);
                    entries.add(RUNIC_BOOTS);

                    // Celestite tools and Starforged armor
                    entries.add(CELESTITE_SWORD);
                    entries.add(CELESTITE_PICKAXE);
                    entries.add(CELESTITE_AXE);
                    entries.add(CELESTITE_SHOVEL);
                    entries.add(CELESTITE_HOE);
                    entries.add(STARFORGED_HELMET);
                    entries.add(STARFORGED_CHESTPLATE);
                    entries.add(STARFORGED_LEGGINGS);
                    entries.add(STARFORGED_BOOTS);

                    // Gloom tools and Withered armor
                    entries.add(GLOOM_SWORD);
                    entries.add(GLOOM_PICKAXE);
                    entries.add(GLOOM_AXE);
                    entries.add(GLOOM_SHOVEL);
                    entries.add(GLOOM_HOE);
                    entries.add(WITHERED_HELMET);
                    entries.add(WITHERED_CHESTPLATE);
                    entries.add(WITHERED_LEGGINGS);
                    entries.add(WITHERED_BOOTS);

                    entries.add(PHANTASM_SPAWN_EGG);
                    entries.add(LULLER_SPAWN_EGG);
                    entries.add(NIGHTMARE_SHADE_SPAWN_EGG);
                    entries.add(SOMNIAN_BUTTERFLY_SPAWN_EGG);
                    entries.add(SKYWHALE_SPAWN_EGG);
                    entries.add(LUMIFIN_SPAWN_EGG);
                    entries.add(STORM_HAWK_SPAWN_EGG);
                    entries.add(SKY_PIRATE_SPAWN_EGG);
                    entries.add(SPORE_BAT_SPAWN_EGG);
                    entries.add(MYCELIUM_WALKER_SPAWN_EGG);
                    entries.add(MYCELITE_CREEPER_SPAWN_EGG);
                    entries.add(FUNGAL_ZOMBIE_SPAWN_EGG);
                    entries.add(SCRIBE_SPIRIT_SPAWN_EGG);
                    entries.add(LIVING_TOME_SPAWN_EGG);
                    entries.add(SENTINEL_SPAWN_EGG);
                    entries.add(PAGE_HORROR_SPAWN_EGG);
                    entries.add(STAR_ELEMENTAL_SPAWN_EGG);
                    entries.add(NEBULA_WISP_SPAWN_EGG);
                    entries.add(VOIDLING_SPAWN_EGG);
                    entries.add(COSMIC_HORROR_SPAWN_EGG);
                    entries.add(ECHO_WISP_SPAWN_EGG);
                    entries.add(CAVE_SNAIL_SPAWN_EGG);
                    entries.add(GLOOM_STALKER_SPAWN_EGG);
                    entries.add(WITHERED_SKELETON_SPAWN_EGG);

                    // Boss spawn eggs
                    entries.add(PYROS_SPAWN_EGG);
                    entries.add(LEVIATHAN_SPAWN_EGG);
                    entries.add(GOLIATH_SPAWN_EGG);
                    entries.add(ZEPHYROS_SPAWN_EGG);
                    entries.add(PRIMARCH_SPAWN_EGG);
                    entries.add(THE_DREAMER_SPAWN_EGG);
                    entries.add(THE_RADIANT_MONARCH_SPAWN_EGG);
                    entries.add(THE_OVERMIND_SPAWN_EGG);
                    entries.add(THE_LIBRARIAN_SPAWN_EGG);
                    entries.add(THE_VOID_ARCHON_SPAWN_EGG);
                    entries.add(THE_ECHO_KING_SPAWN_EGG);

                    // Portal Igniters
                    entries.add(DREAMING_DEPTHS_IGNITER);
                    entries.add(INFERNO_REALM_IGNITER);
                    entries.add(RADIANT_PLATEAU_IGNITER);
                    entries.add(MYCELIAL_NEXUS_IGNITER);
                    entries.add(GOLDEN_CITADEL_IGNITER);
                    entries.add(ANCIENT_GROVES_IGNITER);
                    entries.add(COSMIC_VOID_IGNITER);
                    entries.add(CELESTIAL_SANCTUM_IGNITER);
                    entries.add(GLYPHIC_ARCHIVES_IGNITER);
                    entries.add(ECHOING_EXPANSE_IGNITER);
                    entries.add(UMBRAL_WASTELAND_IGNITER);
                })
                .build()
        );
    }
}
