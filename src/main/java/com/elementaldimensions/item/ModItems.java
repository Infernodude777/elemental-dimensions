package com.elementaldimensions.item;

import com.elementaldimensions.ElementalDimensions;
import net.minecraft.item.*;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.util.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	
	// ===== ESSENCES =====
	public static final Item FIRE_ESSENCE = registerItem("fire_essence",
			new Item(new Item.Settings().maxCount(64)));
	public static final Item WATER_ESSENCE = registerItem("water_essence",
			new Item(new Item.Settings().maxCount(64)));
	public static final Item EARTH_ESSENCE = registerItem("earth_essence",
			new Item(new Item.Settings().maxCount(64)));
	public static final Item AIR_ESSENCE = registerItem("air_essence",
			new Item(new Item.Settings().maxCount(64)));
	
	// ===== PROGRESSION ITEMS =====
	public static final Item DIMENSIONAL_COMPASS = registerItem("dimensional_compass",
			new DimensionalCompassItem(new Item.Settings().maxCount(1)));
	
	public static final Item GUIDE_BOOK = registerItem("guide_book",
			new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
	
	// ===== FIRELANDS MATERIALS =====
	public static final Item LAVA_CRYSTAL = registerItem("lava_crystal",
			new Item(new Item.Settings()));
	public static final Item EMBER_DUST = registerItem("ember_dust",
			new Item(new Item.Settings()));
	public static final Item MAGMA_CORE = registerItem("magma_core",
			new Item(new Item.Settings().fireproof()));
	public static final Item FIRE_INGOT = registerItem("fire_ingot",
			new Item(new Item.Settings().fireproof()));
	public static final Item EMBER_CRYSTAL = registerItem("ember_crystal",
			new Item(new Item.Settings().fireproof()));
	
	// ===== AQUATICA MATERIALS =====
	public static final Item AQUA_CRYSTAL = registerItem("aqua_crystal",
			new Item(new Item.Settings()));
	public static final Item CRAB_MEAT = registerItem("crab_meat",
			new Item(new Item.Settings().component(DataComponentTypes.FOOD, 
				new FoodComponent.Builder().nutrition(3).saturationModifier(0.3f).build())));
	public static final Item SIREN_SCALE = registerItem("siren_scale",
			new Item(new Item.Settings()));
	public static final Item WATER_INGOT = registerItem("water_ingot",
			new Item(new Item.Settings()));
	public static final Item AQUA_PEARL = registerItem("aqua_pearl",
			new Item(new Item.Settings()));
	
	// ===== TERRA DEPTHS MATERIALS =====
	public static final Item GAIA_SHARD = registerItem("gaia_shard",
			new Item(new Item.Settings()));
	public static final Item CRYSTAL_FRAG = registerItem("crystal_frag",
			new Item(new Item.Settings()));
	public static final Item EARTH_INGOT = registerItem("earth_ingot",
			new Item(new Item.Settings()));
	
	// ===== SKYREACH PEAKS MATERIALS =====
	public static final Item ZEPHYR_SHARD = registerItem("zephyr_shard",
			new Item(new Item.Settings()));
	public static final Item AIR_INGOT = registerItem("air_ingot",
			new Item(new Item.Settings()));
	public static final Item ZEPHYR_FEATHER = registerItem("zephyr_feather",
			new Item(new Item.Settings()));
	
	// ===== CELESTIAL MATERIALS =====
	public static final Item VOID_SHARD = registerItem("void_shard",
			new Item(new Item.Settings().rarity(Rarity.RARE)));
	public static final Item CELESTIAL_CRYSTAL = registerItem("celestial_crystal",
			new Item(new Item.Settings().rarity(Rarity.EPIC)));
	public static final Item ELEMENTAL_HEART = registerItem("elemental_heart",
			new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
	public static final Item PRIMARCH_CROWN = registerItem("primarch_crown",
			new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
	
	//===== LEGENDARY WEAPONS (As Special Items - 1.21.10) =====
	// These legendary items have been created with textures. Full combat functionality requires custom item classes in future update.
	public static final Item INFERNAL_EDGE = registerItem("infernal_edge",
			new Item(new Item.Settings().maxCount(1).fireproof().rarity(Rarity.RARE)));
	
	public static final Item ABYSSAL_SPEAR = registerItem("abyssal_spear",
			new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
	
	public static final Item GAIA_CLEAVER = registerItem("gaia_cleaver",
			new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
	
	public static final Item TEMPEST_BOW = registerItem("tempest_bow",
			new Item(new Item.Settings().maxCount(1).maxDamage(500).rarity(Rarity.RARE)));
	
	public static final Item STARFORGED_BLADE = registerItem("starforged_blade",
			new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).fireproof()));
	
	public static final Item VOIDHEART_BOW = registerItem("voidheart_bow",
			new Item(new Item.Settings().maxCount(1).maxDamage(800).rarity(Rarity.EPIC).fireproof()));
	
	public static final Item ASTRAL_PICKAXE = registerItem("astral_pickaxe",
			new Item(new Item.Settings().maxCount(1).maxDamage(2000).rarity(Rarity.EPIC).fireproof()));
	
	// Celestial Armor (as special items)
	public static final Item CELESTIAL_HELMET = registerItem("celestial_helmet",
			new Item(new Item.Settings().maxCount(1).maxDamage(400).rarity(Rarity.EPIC)));
	
	public static final Item CELESTIAL_CHESTPLATE = registerItem("celestial_chestplate",
			new Item(new Item.Settings().maxCount(1).maxDamage(600).rarity(Rarity.EPIC)));
	
	public static final Item CELESTIAL_LEGGINGS = registerItem("celestial_leggings",
			new Item(new Item.Settings().maxCount(1).maxDamage(550).rarity(Rarity.EPIC)));
	
	public static final Item CELESTIAL_BOOTS = registerItem("celestial_boots",
			new Item(new Item.Settings().maxCount(1).maxDamage(450).rarity(Rarity.EPIC)));
	
	// Boss summoning items
	public static final Item FIRE_ALTAR_RUNE = registerItem("fire_altar_rune",
			new Item(new Item.Settings().maxCount(1)));
	public static final Item WATER_ALTAR_RUNE = registerItem("water_altar_rune",
			new Item(new Item.Settings().maxCount(1)));
	public static final Item EARTH_ALTAR_RUNE = registerItem("earth_altar_rune",
			new Item(new Item.Settings().maxCount(1)));
	public static final Item AIR_ALTAR_RUNE = registerItem("air_altar_rune",
			new Item(new Item.Settings().maxCount(1)));
	public static final Item CELESTIAL_ALTAR_RUNE = registerItem("celestial_altar_rune",
			new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
	
	// ===== VOID REALM MATERIALS =====
	public static final Item VOID_ESSENCE = registerItem("void_essence",
			new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
	public static final Item DARK_MATTER = registerItem("dark_matter",
			new Item(new Item.Settings().rarity(Rarity.RARE)));
	public static final Item VOID_CRYSTAL_SHARD = registerItem("void_crystal_shard",
			new Item(new Item.Settings().rarity(Rarity.RARE)));
	public static final Item NULLSTONE_INGOT = registerItem("nullstone_ingot",
			new Item(new Item.Settings()));
	public static final Item ETHEREAL_THREAD = registerItem("ethereal_thread",
			new Item(new Item.Settings()));
	public static final Item VOID_LORD_HEART = registerItem("void_lord_heart",
			new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
	public static final Item VOID_ALTAR_RUNE = registerItem("void_altar_rune",
			new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
	
	// Helper methods
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(ElementalDimensions.MOD_ID, name), item);
	}
	
	public static void register() {
		ElementalDimensions.LOGGER.info("Registering items for " + ElementalDimensions.MOD_ID);
	}
}
