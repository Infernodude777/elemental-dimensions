package com.nikhil.elementaldimensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * /guide command - Gives players a comprehensive guidebook about the mod
 */
public class GuideCommand {
    
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("guide")
                        .executes(GuideCommand::executeGuide)
        );
    }
    
    private static int executeGuide(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        
        try {
            ServerPlayerEntity player = source.getPlayer();
            
            // Give book item with custom name
            ItemStack guidebook = new ItemStack(Items.BOOK);
            guidebook.set(net.minecraft.component.DataComponentTypes.CUSTOM_NAME, 
                    Text.literal("Elemental Dimensions Guide").formatted(Formatting.GOLD, Formatting.BOLD));
            
            // Add lore with guide info
            java.util.List<Text> lore = new java.util.ArrayList<>();
            lore.add(Text.literal("Your complete guide to").formatted(Formatting.GRAY));
            lore.add(Text.literal("conquering the 5 realms").formatted(Formatting.GRAY));
            lore.add(Text.literal(""));
            lore.add(Text.literal("✦ Inferno Realm - Pyros").formatted(Formatting.RED));
            lore.add(Text.literal("✦ Aquatic Realm - Leviathan").formatted(Formatting.BLUE));
            lore.add(Text.literal("✦ Terran Realm - Goliath").formatted(Formatting.GREEN));
            lore.add(Text.literal("✦ Skybound Realm - Zephyros").formatted(Formatting.WHITE));
            lore.add(Text.literal("✦ Celestial Realm - Primarch").formatted(Formatting.LIGHT_PURPLE));
            
            guidebook.set(net.minecraft.component.DataComponentTypes.LORE, 
                    new net.minecraft.component.type.LoreComponent(lore));
            
            // Give to player
            boolean success = player.giveItemStack(guidebook);
            
            if (success) {
                source.sendFeedback(() -> Text.literal("✦ You have received the Elemental Dimensions Guide!")
                        .formatted(Formatting.GOLD), false);
                source.sendFeedback(() -> Text.literal("Check the book's lore for boss information!")
                        .formatted(Formatting.GRAY), false);
            } else {
                source.sendFeedback(() -> Text.literal("Your inventory is full! Clear a slot and try again.")
                        .formatted(Formatting.RED), false);
            }
            
            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: Command must be run by a player!")
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }
    
    // Removed complex book creation - simplified to use lore instead
}
