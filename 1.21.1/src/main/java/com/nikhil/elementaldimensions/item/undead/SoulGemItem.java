package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * Soul Gem - Stores and releases soul energy
 */
public class SoulGemItem extends Item {

    public SoulGemItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Heal player significantly
            user.heal(8.0F);

            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        user.getItemCooldownManager().set(this, 100);
        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
