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
                })
                .build()
        );
    }
}
