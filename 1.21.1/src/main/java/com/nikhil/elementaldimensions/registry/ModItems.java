package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
    
    public static final RegistryKey<ItemGroup> ELEMENTAL_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(ElementalDimensions.MOD_ID, "elemental_group"));
    
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ElementalDimensions.MOD_ID, name), item);
    }
    
    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering items");
        
        // Register items
        FIRE_ESSENCE = registerItem("fire_essence", new Item(new Item.Settings()));
        WATER_ESSENCE = registerItem("water_essence", new Item(new Item.Settings()));
        EARTH_ESSENCE = registerItem("earth_essence", new Item(new Item.Settings()));
        AIR_ESSENCE = registerItem("air_essence", new Item(new Item.Settings()));
        
        INFERNO_HEART = registerItem("inferno_heart", new Item(new Item.Settings().maxCount(1)));
        AQUATIC_HEART = registerItem("aquatic_heart", new Item(new Item.Settings().maxCount(1)));
        TERRAN_HEART = registerItem("terran_heart", new Item(new Item.Settings().maxCount(1)));
        SKYBOUND_HEART = registerItem("skybound_heart", new Item(new Item.Settings().maxCount(1)));
        
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
                })
                .build()
        );
    }
}

