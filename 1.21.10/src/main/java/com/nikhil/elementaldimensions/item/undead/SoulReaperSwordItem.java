package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

/**
 * Soul Reaper Sword - Steals life from enemies and heals the user
 */
public class SoulReaperSwordItem extends SwordItem {

    private static final ToolMaterial SOUL_REAPER_MATERIAL = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 1800;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 8.0F;
        }

        @Override
        public float getAttackDamage() {
            return 7.0F;
        }

        @Override
        public TagKey<net.minecraft.block.Block> getInverseTag() {
            return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
        }

        @Override
        public int getEnchantability() {
            return 18;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    };

    public SoulReaperSwordItem(Settings settings) {
        super(SOUL_REAPER_MATERIAL, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Heal attacker when hitting an enemy
        if (attacker instanceof PlayerEntity player) {
            player.heal(2.0F);
            // Apply wither effect to target
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 1));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Grant soul power buff when used
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0));
            stack.damage(5, user, user.getPreferredEquipmentSlot(stack));
        }

        user.getItemCooldownManager().set(this, 400);
        return TypedActionResult.success(stack, world.isClient());
    }
}
