package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WraithEssenceItem extends Item {
    public WraithEssenceItem(Settings settings) {
        super(settings.maxCount(64));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2));
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        user.getItemCooldownManager().set(this, 200);
        return TypedActionResult.success(stack, world.isClient());
    }
}
