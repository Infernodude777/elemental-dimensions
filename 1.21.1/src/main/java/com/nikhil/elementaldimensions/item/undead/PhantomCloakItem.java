package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * Phantom Cloak - Grants invisibility and speed
 */
public class PhantomCloakItem extends Item {

    public PhantomCloakItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(300));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 400, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 1));

            stack.damage(1, user, user.getPreferredEquipmentSlot(stack));
        }

        user.getItemCooldownManager().set(this, 800);
        return TypedActionResult.success(stack, world.isClient());
    }
}
