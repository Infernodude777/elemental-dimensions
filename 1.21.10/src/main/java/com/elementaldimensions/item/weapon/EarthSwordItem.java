package com.elementaldimensions.item.weapon;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.block.Block;
import com.elementaldimensions.item.ModItems;

public class EarthSwordItem extends SwordItem {
	
	public EarthSwordItem(Settings settings) {
		super(EARTH_MATERIAL, settings);
	}

	public static final ToolMaterial EARTH_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 1500;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 6.0f;
		}

		@Override
		public float getAttackDamage() {
			return 6.0f;
		}

		@Override
		public TagKey<Block> getInverseTag() {
			return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
		}

		@Override
		public int getEnchantability() {
			return 15;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(ModItems.EARTH_INGOT);
		}
	};
}
