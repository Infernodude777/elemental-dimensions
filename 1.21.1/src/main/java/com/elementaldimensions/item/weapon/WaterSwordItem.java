package com.elementaldimensions.item.weapon;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.block.Block;
import com.elementaldimensions.item.ModItems;

public class WaterSwordItem extends SwordItem {
	
	public WaterSwordItem(Settings settings) {
		super(WATER_MATERIAL, settings);
	}

	public static final ToolMaterial WATER_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 1100;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 6.5f;
		}

		@Override
		public float getAttackDamage() {
			return 4.5f;
		}

		@Override
		public TagKey<Block> getInverseTag() {
			return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
		}

		@Override
		public int getEnchantability() {
			return 20;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(ModItems.WATER_INGOT);
		}
	};
}
