package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Cursed Bow - Arrows apply wither and weakness effects
 */
public class CursedBowItem extends BowItem {

    public CursedBowItem(Settings settings) {
        super(settings.maxDamage(600));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player && !world.isClient) {
            // Apply curse buff to player when firing
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 100, 0));
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}
