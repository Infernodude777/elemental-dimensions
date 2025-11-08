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
 * Necromancer Staff - Summons undead helpers and grants necromantic powers
 */
public class NecromancerStaffItem extends Item {

    public NecromancerStaffItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(500));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Grant necromantic powers
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 1));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 0));

            stack.damage(1, user, user.getPreferredEquipmentSlot(stack));
        }

        user.getItemCooldownManager().set(this, 600);
        return TypedActionResult.success(stack, world.isClient());
    }
}
