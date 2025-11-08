package com.nikhil.elementaldimensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.nikhil.elementaldimensions.registry.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * /getall command - Gives the player all mod items and blocks
 */
public class GetAllCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("getall")
                        .requires(source -> source.hasPermissionLevel(2)) // Requires operator permission
                        .executes(GetAllCommand::executeGetAll)
        );
    }

    private static int executeGetAll(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();

            // Give essences and hearts
            player.giveItemStack(new ItemStack(ModItems.FIRE_ESSENCE, 64));
            player.giveItemStack(new ItemStack(ModItems.WATER_ESSENCE, 64));
            player.giveItemStack(new ItemStack(ModItems.EARTH_ESSENCE, 64));
            player.giveItemStack(new ItemStack(ModItems.AIR_ESSENCE, 64));
            player.giveItemStack(new ItemStack(ModItems.INFERNO_HEART, 1));
            player.giveItemStack(new ItemStack(ModItems.AQUATIC_HEART, 1));
            player.giveItemStack(new ItemStack(ModItems.TERRAN_HEART, 1));
            player.giveItemStack(new ItemStack(ModItems.SKYBOUND_HEART, 1));

            // Give all boss spawn eggs
            player.giveItemStack(new ItemStack(ModItems.PYROS_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.LEVIATHAN_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.GOLIATH_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.ZEPHYROS_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.PRIMARCH_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.THE_DREAMER_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.THE_RADIANT_MONARCH_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.THE_OVERMIND_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.THE_LIBRARIAN_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.THE_VOID_ARCHON_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.THE_ECHO_KING_SPAWN_EGG, 1));

            // Give all mob spawn eggs
            player.giveItemStack(new ItemStack(ModItems.PHANTASM_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.LULLER_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.NIGHTMARE_SHADE_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.SOMNIAN_BUTTERFLY_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.SKYWHALE_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.LUMIFIN_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.STORM_HAWK_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.SKY_PIRATE_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.SPORE_BAT_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.MYCELIUM_WALKER_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.MYCELITE_CREEPER_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.FUNGAL_ZOMBIE_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.SCRIBE_SPIRIT_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.LIVING_TOME_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.SENTINEL_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.PAGE_HORROR_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.STAR_ELEMENTAL_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.NEBULA_WISP_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.VOIDLING_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.COSMIC_HORROR_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.ECHO_WISP_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.CAVE_SNAIL_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.GLOOM_STALKER_SPAWN_EGG, 1));
            player.giveItemStack(new ItemStack(ModItems.WITHERED_SKELETON_SPAWN_EGG, 1));

            final int totalItems = 43;
            source.sendFeedback(() -> Text.literal("§6✦ Given " + totalItems + " different items to " + player.getName().getString() + "!")
                    .formatted(Formatting.GOLD), true);

            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: Command must be run by a player!")
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }
}
