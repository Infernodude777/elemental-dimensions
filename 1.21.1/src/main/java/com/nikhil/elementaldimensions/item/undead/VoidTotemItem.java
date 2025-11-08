package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class VoidTotemItem extends Item {
    public VoidTotemItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.setHealth(user.getMaxHealth());
            user.getHungerManager().setFoodLevel(20);
            if (!user.getAbilities().creativeMode) {
                user.getStackInHand(hand).decrement(1);
            }
        }
        user.getItemCooldownManager().set(this, 2400);
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
