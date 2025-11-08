package com.elementaldimensions.item.weapon;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.block.Block;
import com.elementaldimensions.item.ModItems;

public class FireSwordItem extends SwordItem {
	
	public FireSwordItem(Settings settings) {
		super(FIRE_MATERIAL, settings);
	}

	public static final ToolMaterial FIRE_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 1200;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 7.0f;
		}

		@Override
		public float getAttackDamage() {
			return 5.0f;
		}

		@Override
		public TagKey<Block> getInverseTag() {
			return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
		}

		@Override
		public int getEnchantability() {
			return 18;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(ModItems.FIRE_INGOT);
		}
	};
}
