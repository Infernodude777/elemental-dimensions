package com.elementaldimensions.command;

import net.minecraft.util.math.Vec3d;
import com.elementaldimensions.config.ModConfig;
import com.elementaldimensions.entity.ModEntities;
import com.elementaldimensions.world.dimension.ModDimensions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class ModCommands {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		// /edtp <dimension>
		dispatcher.register(literal("edtp")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("dimension", StringArgumentType.word())
						.suggests((context, builder) -> {
							builder.suggest("firelands");
							builder.suggest("aquatica");
							builder.suggest("terra_depths");
							builder.suggest("skyreach_peaks");
							builder.suggest("celestial");
							builder.suggest("void_realm");
							return builder.buildFuture();
						})
						.executes(ModCommands::teleportToDimension)));

		// /edspawn <mob>
		dispatcher.register(literal("edspawn")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("mob", StringArgumentType.word())
						.suggests((context, builder) -> {
							builder.suggest("flame_sprite");
							builder.suggest("magma_brute");
							builder.suggest("fire_titan");
							builder.suggest("abyss_crab");
							builder.suggest("abyss_leviathan");
							builder.suggest("stone_golem");
							builder.suggest("stone_colossus");
							builder.suggest("wind_hawk");
							builder.suggest("wind_seraph");
							builder.suggest("celestial_enderman");
							builder.suggest("elemental_primarch");
							return builder.buildFuture();
						})
						.executes(ModCommands::spawnMob)));

		// /edgive <item>
		dispatcher.register(literal("edgive")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("item", StringArgumentType.word())
						.executes(ModCommands::giveItem)));

		// /edreload
		dispatcher.register(literal("edreload")
				.requires(source -> source.hasPermissionLevel(3))
				.executes(ModCommands::reloadConfig));

		// /eddebug <on/off>
		dispatcher.register(literal("eddebug")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("mode", StringArgumentType.word())
						.suggests((context, builder) -> {
							builder.suggest("on");
							builder.suggest("off");
							return builder.buildFuture();
						})
						.executes(ModCommands::toggleDebug)));

		// /edhelp
		dispatcher.register(literal("edhelp")
				.executes(ModCommands::showHelp));

		// /edlistblocks <dimension>
		dispatcher.register(literal("edlistblocks")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("dimension", StringArgumentType.word())
						.executes(ModCommands::listBlocks)));

		// /edlistmobs <dimension>
		dispatcher.register(literal("edlistmobs")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("dimension", StringArgumentType.word())
						.executes(ModCommands::listMobs)));
	}

	private static int teleportToDimension(CommandContext<ServerCommandSource> context) {
		String dimensionName = StringArgumentType.getString(context, "dimension");
		ServerPlayerEntity player = context.getSource().getPlayer();

		if (player == null) {
			context.getSource().sendError(Text.literal("This command can only be used by players"));
			return 0;
		}

		RegistryKey<World> targetDimension = switch (dimensionName.toLowerCase()) {
			case "firelands" -> ModDimensions.FIRELANDS;
			case "aquatica" -> ModDimensions.AQUATICA;
			case "terra_depths" -> ModDimensions.TERRA_DEPTHS;
			case "skyreach_peaks" -> ModDimensions.SKYREACH_PEAKS;
			case "celestial" -> ModDimensions.CELESTIAL;
			case "void_realm" -> ModDimensions.VOID_REALM;
			default -> null;
		};

		if (targetDimension == null) {
			context.getSource().sendError(Text.literal("Unknown dimension: " + dimensionName));
			return 0;
		}

		ServerWorld targetWorld = context.getSource().getServer().getWorld(targetDimension);
		if (targetWorld == null) {
			context.getSource().sendError(Text.literal("Dimension not loaded: " + dimensionName));
			return 0;
		}

		// Use default spawn position
		BlockPos spawnPos = new BlockPos(0, 64, 0);
		player.teleportTo(new TeleportTarget(targetWorld,
				new Vec3d(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5),
				Vec3d.ZERO, player.getYaw(), player.getPitch(), TeleportTarget.NO_OP));

		context.getSource().sendFeedback(() -> Text.literal("Teleported to " + dimensionName), true);
		return 1;
	}

	private static int spawnMob(CommandContext<ServerCommandSource> context) {
		String mobName = StringArgumentType.getString(context, "mob");
		ServerPlayerEntity player = context.getSource().getPlayer();

		if (player == null) {
			context.getSource().sendError(Text.literal("This command can only be used by players"));
			return 0;
		}

		EntityType<?> entityType = switch (mobName.toLowerCase()) {
			case "flame_sprite" -> ModEntities.FLAME_SPRITE;
			case "magma_brute" -> ModEntities.MAGMA_BRUTE;
			case "fire_titan" -> ModEntities.FIRE_TITAN;
			case "abyss_crab" -> ModEntities.ABYSS_CRAB;
			case "abyss_leviathan" -> ModEntities.ABYSS_LEVIATHAN;
			case "stone_golem" -> ModEntities.STONE_GOLEM;
			case "stone_colossus" -> ModEntities.STONE_COLOSSUS;
			case "wind_hawk" -> ModEntities.WIND_HAWK;
			case "wind_seraph" -> ModEntities.WIND_SERAPH;
			case "celestial_enderman" -> ModEntities.CELESTIAL_ENDERMAN;
			case "elemental_primarch" -> ModEntities.ELEMENTAL_PRIMARCH;
			default -> null;
		};

		if (entityType == null) {
			context.getSource().sendError(Text.literal("Unknown mob: " + mobName));
			return 0;
		}

		BlockPos spawnPos = player.getBlockPos().offset(player.getHorizontalFacing(), 3);
		ServerWorld world = context.getSource().getWorld();
		entityType.spawn(world, spawnPos, SpawnReason.COMMAND);

		context.getSource().sendFeedback(() -> Text.literal("Spawned " + mobName), true);
		return 1;
	}

	private static int giveItem(CommandContext<ServerCommandSource> context) {
		String itemName = StringArgumentType.getString(context, "item");
		ServerPlayerEntity player = context.getSource().getPlayer();

		if (player == null) {
			context.getSource().sendError(Text.literal("This command can only be used by players"));
			return 0;
		}


		context.getSource().sendFeedback(() -> Text.literal("Item giving not yet implemented for: " + itemName), false);
		return 1;
	}

	private static int reloadConfig(CommandContext<ServerCommandSource> context) {
		ModConfig.init();
		context.getSource().sendFeedback(() -> Text.literal("Configuration reloaded"), true);
		return 1;
	}

	private static int toggleDebug(CommandContext<ServerCommandSource> context) {
		String mode = StringArgumentType.getString(context, "mode");
		boolean enable = mode.equalsIgnoreCase("on");

		ModConfig.get().debugMode = enable;
		ModConfig.save();

		context.getSource().sendFeedback(() ->
				Text.literal("Debug mode " + (enable ? "enabled" : "disabled")), true);
		return 1;
	}

	private static int showHelp(CommandContext<ServerCommandSource> context) {
		context.getSource().sendFeedback(() -> Text.literal("""
				=== Elemental Dimensions Commands ===
				/edtp <dimension> - Teleport to dimension
				/edspawn <mob> - Spawn a mob
				/edgive <item> - Give an item
				/edreload - Reload config
				/eddebug <on/off> - Toggle debug mode
				/edlistblocks <dimension> - List blocks
				/edlistmobs <dimension> - List mobs
				"""), false);
		return 1;
	}

	private static int listBlocks(CommandContext<ServerCommandSource> context) {
		String dimension = StringArgumentType.getString(context, "dimension");
		context.getSource().sendFeedback(() ->
				Text.literal("Listing blocks for " + dimension + "..."), false);

		return 1;
	}

	private static int listMobs(CommandContext<ServerCommandSource> context) {
		String dimension = StringArgumentType.getString(context, "dimension");
		context.getSource().sendFeedback(() ->
				Text.literal("Listing mobs for " + dimension + "..."), false);

		return 1;
	}
}
