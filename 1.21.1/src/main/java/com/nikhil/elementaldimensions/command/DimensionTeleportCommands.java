package com.nikhil.elementaldimensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Admin commands for teleporting to dimensions
 */
public class DimensionTeleportCommands {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        // /celestial - Teleport to Celestial Realm
        dispatcher.register(
                CommandManager.literal("celestial")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "celestial_realm"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "celestial_realm")))
        );

        // /fire - Teleport to Inferno Realm
        dispatcher.register(
                CommandManager.literal("fire")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "inferno_realm"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "inferno_realm")))
        );

        // /water - Teleport to Aquatic Realm
        dispatcher.register(
                CommandManager.literal("water")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "aquatic_realm"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "aquatic_realm")))
        );

        // /earth - Teleport to Terran Realm
        dispatcher.register(
                CommandManager.literal("earth")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "terran_realm"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "terran_realm")))
        );

        // /air - Teleport to Skybound Realm
        dispatcher.register(
                CommandManager.literal("air")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "skybound_realm"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "skybound_realm")))
        );

        // /void - Teleport to Void Realm
        dispatcher.register(
                CommandManager.literal("void")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "void_realm"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "void_realm")))
        );

        // /dream - Teleport to Dreaming Depths
        dispatcher.register(
                CommandManager.literal("dream")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "dreaming_depths"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "dreaming_depths")))
        );

        // /celestine - Teleport to Celestine Expanse
        dispatcher.register(
                CommandManager.literal("celestine")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "celestine_expanse"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "celestine_expanse")))
        );

        // /fungal - Teleport to Fungal Dominion
        dispatcher.register(
                CommandManager.literal("fungal")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "fungal_dominion"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "fungal_dominion")))
        );

        // /archive - Teleport to Forgotten Archive
        dispatcher.register(
                CommandManager.literal("archive")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "forgotten_archive"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "forgotten_archive")))
        );

        // /astral - Teleport to Astral Frontier
        dispatcher.register(
                CommandManager.literal("astral")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "astral_frontier"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "astral_frontier")))
        );

        // /gloom - Teleport to Gloomy Caverns
        dispatcher.register(
                CommandManager.literal("gloom")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ctx -> teleportToDimension(ctx, "gloomy_caverns"))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> teleportPlayerToDimension(ctx, "gloomy_caverns")))
        );
    }

    private static int teleportToDimension(CommandContext<ServerCommandSource> context, String dimensionName) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();
            return performTeleport(source, player, dimensionName);
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: Command must be run by a player!")
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int teleportPlayerToDimension(CommandContext<ServerCommandSource> context, String dimensionName) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity targetPlayer = EntityArgumentType.getPlayer(context, "player");
            return performTeleport(source, targetPlayer, dimensionName);
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: Could not find player!")
                    .formatted(Formatting.RED), false);
            return 0;
        }
    }

    private static int performTeleport(ServerCommandSource source, ServerPlayerEntity player, String dimensionName) {
        // Get dimension registry key
        RegistryKey<World> dimensionKey = RegistryKey.of(
                RegistryKeys.WORLD,
                Identifier.of("elementaldimensions", dimensionName)
        );

        // Get target world
        ServerWorld targetWorld = source.getServer().getWorld(dimensionKey);

        if (targetWorld == null) {
            source.sendFeedback(() -> Text.literal("Error: Dimension not found! Make sure dimension files are configured.")
                    .formatted(Formatting.RED), false);
            return 0;
        }

        // Teleport to spawn point (0, 100, 0)
        BlockPos spawnPos = new BlockPos(0, 100, 0);
        Vec3d spawnVec = Vec3d.ofCenter(spawnPos);

        player.teleport(targetWorld, spawnVec.x, spawnVec.y, spawnVec.z,
                player.getYaw(), player.getPitch());

        String dimensionDisplayName = switch(dimensionName) {
            case "celestial_realm" -> "Celestial Realm";
            case "inferno_realm" -> "Inferno Realm";
            case "aquatic_realm" -> "Aquatic Realm";
            case "terran_realm" -> "Terran Realm";
            case "skybound_realm" -> "Skybound Realm";
            case "void_realm" -> "Void Realm";
            case "dreaming_depths" -> "Dreaming Depths";
            case "celestine_expanse" -> "Celestine Expanse";
            case "fungal_dominion" -> "Fungal Dominion";
            case "forgotten_archive" -> "Forgotten Archive";
            case "astral_frontier" -> "Astral Frontier";
            case "gloomy_caverns" -> "Gloomy Caverns";
            default -> dimensionName;
        };

        Formatting color = switch(dimensionName) {
            case "celestial_realm" -> Formatting.LIGHT_PURPLE;
            case "inferno_realm" -> Formatting.RED;
            case "aquatic_realm" -> Formatting.BLUE;
            case "terran_realm" -> Formatting.GREEN;
            case "skybound_realm" -> Formatting.WHITE;
            case "void_realm" -> Formatting.DARK_GRAY;
            case "dreaming_depths" -> Formatting.AQUA;
            case "celestine_expanse" -> Formatting.YELLOW;
            case "fungal_dominion" -> Formatting.DARK_GREEN;
            case "forgotten_archive" -> Formatting.GOLD;
            case "astral_frontier" -> Formatting.DARK_PURPLE;
            case "gloomy_caverns" -> Formatting.DARK_GRAY;
            default -> Formatting.GRAY;
        };

        source.sendFeedback(() -> Text.literal("âœ¦ Teleported to " + dimensionDisplayName + "!")
                .formatted(color), true);

        return 1;
    }
}
