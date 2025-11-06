package com.nikhil.elementaldimensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * Admin commands for spawning item chests
 */
public class ItemSpawnCommands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        // /getallitems - Spawns chest with ALL mod items
        dispatcher.register(
                CommandManager.literal("getallitems")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ItemSpawnCommands::giveAllItems)
        );

        // /getallweapons - Spawns chest with all weapons
        dispatcher.register(
                CommandManager.literal("getallweapons")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ItemSpawnCommands::giveAllWeapons)
        );

        // /getalltools - Spawns chest with all tools
        dispatcher.register(
                CommandManager.literal("getalltools")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ItemSpawnCommands::giveAllTools)
        );

        // /getallarmor - Spawns chest with all armor sets
        dispatcher.register(
                CommandManager.literal("getallarmor")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ItemSpawnCommands::giveAllArmor)
        );

        // /getessences - Spawns chest with all essences
        dispatcher.register(
                CommandManager.literal("getessences")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ItemSpawnCommands::giveAllEssences)
        );
    }

    private static int giveAllItems(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();
            ServerWorld world = source.getWorld();
            BlockPos chestPos = getChestPosition(player);

            if (!createChest(world, chestPos)) {
                source.sendFeedback(() -> Text.literal("Error: Could not place chest!")
                        .formatted(Formatting.RED), false);
                return 0;
            }

            ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(chestPos);
            if (chest == null) return 0;

            int slot = 0;

            // Add all essences
            slot = addItem(chest, slot, "fire_essence", 64);
            slot = addItem(chest, slot, "water_essence", 64);
            slot = addItem(chest, slot, "earth_essence", 64);
            slot = addItem(chest, slot, "air_essence", 64);

            // Add boss hearts
            slot = addItem(chest, slot, "inferno_heart", 1);
            slot = addItem(chest, slot, "aquatic_heart", 1);
            slot = addItem(chest, slot, "terran_heart", 1);
            slot = addItem(chest, slot, "skybound_heart", 1);
            slot = addItem(chest, slot, "elemental_heart", 1);

            // Add celestial items
            slot = addItem(chest, slot, "celestial_crystal", 64);
            slot = addItem(chest, slot, "celestial_dust", 64);
            slot = addItem(chest, slot, "starlight_igniter", 1);

            // Add weapons
            slot = addItem(chest, slot, "flame_reaver", 1);
            slot = addItem(chest, slot, "tidal_piercer", 1);
            slot = addItem(chest, slot, "terran_crusher", 1);
            slot = addItem(chest, slot, "zephyr_whisper", 1);

            // Add tools
            slot = addItem(chest, slot, "inferno_pick", 1);
            slot = addItem(chest, slot, "aqua_breaker", 1);
            slot = addItem(chest, slot, "terran_drill", 1);
            slot = addItem(chest, slot, "zephyr_pick", 1);

            source.sendFeedback(() -> Text.literal("âœ¦ Spawned chest with ALL items in front of you!")
                    .formatted(Formatting.GOLD), true);

            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: " + e.getMessage())
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int giveAllWeapons(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();
            ServerWorld world = source.getWorld();
            BlockPos chestPos = getChestPosition(player);

            if (!createChest(world, chestPos)) return 0;

            ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(chestPos);
            if (chest == null) return 0;

            int slot = 0;
            slot = addItem(chest, slot, "flame_reaver", 1);
            slot = addItem(chest, slot, "tidal_piercer", 1);
            slot = addItem(chest, slot, "terran_crusher", 1);
            slot = addItem(chest, slot, "zephyr_whisper", 1);

            source.sendFeedback(() -> Text.literal("âš” Spawned chest with ALL weapons!")
                    .formatted(Formatting.RED), true);

            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: " + e.getMessage())
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int giveAllTools(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();
            ServerWorld world = source.getWorld();
            BlockPos chestPos = getChestPosition(player);

            if (!createChest(world, chestPos)) return 0;

            ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(chestPos);
            if (chest == null) return 0;

            int slot = 0;
            slot = addItem(chest, slot, "inferno_pick", 1);
            slot = addItem(chest, slot, "aqua_breaker", 1);
            slot = addItem(chest, slot, "terran_drill", 1);
            slot = addItem(chest, slot, "zephyr_pick", 1);

            source.sendFeedback(() -> Text.literal("â› Spawned chest with ALL tools!")
                    .formatted(Formatting.AQUA), true);

            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: " + e.getMessage())
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int giveAllArmor(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();
            ServerWorld world = source.getWorld();
            BlockPos chestPos = getChestPosition(player);

            if (!createChest(world, chestPos)) return 0;

            ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(chestPos);
            if (chest == null) return 0;

            int slot = 0;

            // Fire armor
            slot = addItem(chest, slot, "fire_helmet", 1);
            slot = addItem(chest, slot, "fire_chestplate", 1);
            slot = addItem(chest, slot, "fire_leggings", 1);
            slot = addItem(chest, slot, "fire_boots", 1);

            // Water armor
            slot = addItem(chest, slot, "water_helmet", 1);
            slot = addItem(chest, slot, "water_chestplate", 1);
            slot = addItem(chest, slot, "water_leggings", 1);
            slot = addItem(chest, slot, "water_boots", 1);

            // Earth armor
            slot = addItem(chest, slot, "earth_helmet", 1);
            slot = addItem(chest, slot, "earth_chestplate", 1);
            slot = addItem(chest, slot, "earth_leggings", 1);
            slot = addItem(chest, slot, "earth_boots", 1);

            // Air armor
            slot = addItem(chest, slot, "air_helmet", 1);
            slot = addItem(chest, slot, "air_chestplate", 1);
            slot = addItem(chest, slot, "air_leggings", 1);
            slot = addItem(chest, slot, "air_boots", 1);

            source.sendFeedback(() -> Text.literal("ðŸ›¡ Spawned chest with ALL armor sets!")
                    .formatted(Formatting.LIGHT_PURPLE), true);

            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: " + e.getMessage())
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int giveAllEssences(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();
            ServerWorld world = source.getWorld();
            BlockPos chestPos = getChestPosition(player);

            if (!createChest(world, chestPos)) return 0;

            ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(chestPos);
            if (chest == null) return 0;

            int slot = 0;
            slot = addItem(chest, slot, "fire_essence", 64);
            slot = addItem(chest, slot, "water_essence", 64);
            slot = addItem(chest, slot, "earth_essence", 64);
            slot = addItem(chest, slot, "air_essence", 64);
            slot = addItem(chest, slot, "celestial_crystal", 64);
            slot = addItem(chest, slot, "celestial_dust", 64);

            source.sendFeedback(() -> Text.literal("âœ¨ Spawned chest with ALL essences!")
                    .formatted(Formatting.YELLOW), true);

            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: " + e.getMessage())
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static BlockPos getChestPosition(ServerPlayerEntity player) {
        // Get position 2 blocks in front of player
        Direction facing = player.getHorizontalFacing();
        BlockPos playerPos = player.getBlockPos();
        return playerPos.offset(facing, 2);
    }

    private static boolean createChest(ServerWorld world, BlockPos pos) {
        // Clear the position first
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        // Place chest
        return world.setBlockState(pos, Blocks.CHEST.getDefaultState());
    }

    private static int addItem(ChestBlockEntity chest, int slot, String itemName, int count) {
        if (slot >= 27) return slot; // Chest is full

        try {
            ItemStack stack = new ItemStack(
                    Registries.ITEM.get(Identifier.of("elementaldimensions", itemName)),
                    count
            );

            if (!stack.isEmpty()) {
                chest.setStack(slot, stack);
                return slot + 1;
            }
        } catch (Exception e) {
            // Item doesn't exist, skip it
        }

        return slot;
    }
}
