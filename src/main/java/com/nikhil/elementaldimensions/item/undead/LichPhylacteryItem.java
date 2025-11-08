package com.nikhil.elementaldimensions.item.undead;

import net.minecraft.item.Item;

public class LichPhylacteryItem extends Item {
    public LichPhylacteryItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public boolean hasGlint(net.minecraft.item.ItemStack stack) {
        return true;
    }
}
