package com.nikhil.elementaldimensions.item;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import com.nikhil.elementaldimensions.registry.ModBlocks;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    // Dreaming Depths - Reverie tools
    REVERIE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15, () -> Ingredient.ofItems(ModBlocks.REVERIE_BLOCK)),
    
    // Celestine Expanse - Lumicore tools
    LUMICORE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1861, 8.5F, 3.5F, 18, () -> Ingredient.ofItems(ModBlocks.LUMICORE_BLOCK)),
    
    // Fungal Dominion - Mycelite tools
    MYCELITE(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 14, () -> Ingredient.ofItems(ModBlocks.MYCELITE_BLOCK)),
    
    // Forgotten Archive - Glyph tools
    GLYPH(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1961, 9.0F, 4.0F, 22, () -> Ingredient.ofItems(ModBlocks.GLYPH_BLOCK)),
    
    // Astral Frontier - Celestite tools
    CELESTITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2131, 9.5F, 4.5F, 20, () -> Ingredient.ofItems(ModBlocks.CELESTITE_BLOCK)),
    
    // Gloomy Caverns - Gloom tools
    GLOOM(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1661, 7.5F, 3.0F, 12, () -> Ingredient.ofItems(ModBlocks.GLOOM_BLOCK));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(
            final TagKey<Block> inverseTag,
            final int itemDurability,
            final float miningSpeed,
            final float attackDamage,
            final int enchantability,
            final Supplier<Ingredient> repairIngredient
    ) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
