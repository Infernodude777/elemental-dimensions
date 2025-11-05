package com.elementaldimensions.item;

public class ModArmorMaterials {
	
	/*
	public static final RegistryEntry<ArmorMaterial> CELESTIAL = register("celestial",
			Util.make(new EnumMap<>(EquipmentType.class), map -> {
				map.put(EquipmentType.BOOTS, 4);
				map.put(EquipmentType.LEGGINGS, 7);
				map.put(EquipmentType.CHESTPLATE, 9);
				map.put(EquipmentType.HELMET, 4);
				map.put(EquipmentType.BODY, 11);
			}),
			15,
			SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
			3.0f,
			0.1f,
			() -> Ingredient.ofItems(ModItems.CELESTIAL_CRYSTAL));
	
	private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<EquipmentType, Integer> defense,
	                                                       int enchantability, RegistryEntry<SoundEvent> equipSound,
	                                                       float toughness, float knockbackResistance,
	                                                       Supplier<Ingredient> repairIngredient) {
		List<ArmorMaterial.Layer> layers = List.of(
				new ArmorMaterial.Layer(Identifier.of("elementaldimensions", id))
		);
		
		EnumMap<EquipmentType, Integer> defenseMap = new EnumMap<>(EquipmentType.class);
		for (EquipmentType type : EquipmentType.values()) {
			if (defense.containsKey(type)) {
				defenseMap.put(type, defense.get(type));
			}
		}
		
		return Registry.registerReference(Registries.ARMOR_MATERIAL, 
				Identifier.of("elementaldimensions", id),
				new ArmorMaterial(defenseMap, enchantability, equipSound, repairIngredient, 
						layers, toughness, knockbackResistance));
	}
	*/
}
