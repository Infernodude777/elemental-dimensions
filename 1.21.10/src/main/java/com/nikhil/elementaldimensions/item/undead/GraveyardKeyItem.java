package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GraveyardKeyItem extends Item {
    public GraveyardKeyItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 6000, 2));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 6000, 0));
        }
        user.getItemCooldownManager().set(this, 12000);
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
