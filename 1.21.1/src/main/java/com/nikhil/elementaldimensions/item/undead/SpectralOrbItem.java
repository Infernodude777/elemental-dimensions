package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SpectralOrbItem extends Item {
    public SpectralOrbItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(400));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 0));
            stack.damage(1, user, user.getPreferredEquipmentSlot(stack));
        }
        user.getItemCooldownManager().set(this, 600);
        return TypedActionResult.success(stack, world.isClient());
    }
}
