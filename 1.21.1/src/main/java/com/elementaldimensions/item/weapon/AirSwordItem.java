package com.elementaldimensions.item.weapon;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.block.Block;
import com.elementaldimensions.item.ModItems;

public class AirSwordItem extends SwordItem {
	
	public AirSwordItem(Settings settings) {
		super(AIR_MATERIAL, settings);
	}

	public static final ToolMaterial AIR_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 900;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 9.0f;
		}

		@Override
		public float getAttackDamage() {
			return 4.0f;
		}

		@Override
		public TagKey<Block> getInverseTag() {
			return BlockTags.INCORRECT_FOR_IRON_TOOL;
		}

		@Override
		public int getEnchantability() {
			return 22;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(ModItems.AIR_INGOT);
		}
	};
}
