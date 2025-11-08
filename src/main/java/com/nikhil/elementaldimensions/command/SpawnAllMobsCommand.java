package com.nikhil.elementaldimensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.nikhil.elementaldimensions.registry.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 * /spawnallmobs command - Spawns all mod bosses around the player
 */
public class SpawnAllMobsCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("spawnallmobs")
                        .requires(source -> source.hasPermissionLevel(2)) // Requires operator permission
                        .executes(SpawnAllMobsCommand::executeSpawnAll)
        );
    }

    private static int executeSpawnAll(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        try {
            ServerPlayerEntity player = source.getPlayer();
            ServerWorld world = player.getServerWorld();
            Vec3d playerPos = player.getPos();
            final int[] spawnedCount = {0};

            // Spawn radius in blocks
            int radius = 10;
            double angleIncrement = (2 * Math.PI) / 11; // 11 bosses in a circle

            // Spawn each boss in a circle around the player
            for (int i = 0; i < 11; i++) {
                double angle = i * angleIncrement;
                double offsetX = Math.cos(angle) * radius;
                double offsetZ = Math.sin(angle) * radius;

                BlockPos spawnPos = new BlockPos(
                        (int) (playerPos.x + offsetX),
                        (int) playerPos.y,
                        (int) (playerPos.z + offsetZ)
                );

                // Find safe spawn position (on ground)
                while (world.getBlockState(spawnPos).isAir() && spawnPos.getY() > world.getBottomY()) {
                    spawnPos = spawnPos.down();
                }
                spawnPos = spawnPos.up(); // Spawn above ground

                Entity boss = null;
                switch (i) {
                    case 0:
                        boss = ModEntities.PYROS.create(world);
                        break;
                    case 1:
                        boss = ModEntities.LEVIATHAN.create(world);
                        break;
                    case 2:
                        boss = ModEntities.GOLIATH.create(world);
                        break;
                    case 3:
                        boss = ModEntities.ZEPHYROS.create(world);
                        break;
                    case 4:
                        boss = ModEntities.PRIMARCH.create(world);
                        break;
                    case 5:
                        boss = ModEntities.THE_DREAMER.create(world);
                        break;
                    case 6:
                        boss = ModEntities.THE_RADIANT_MONARCH.create(world);
                        break;
                    case 7:
                        boss = ModEntities.THE_OVERMIND.create(world);
                        break;
                    case 8:
                        boss = ModEntities.THE_LIBRARIAN.create(world);
                        break;
                    case 9:
                        boss = ModEntities.THE_VOID_ARCHON.create(world);
                        break;
                    case 10:
                        boss = ModEntities.THE_ECHO_KING.create(world);
                        break;
                }

                if (boss != null) {
                    boss.refreshPositionAndAngles(
                            spawnPos.getX() + 0.5,
                            spawnPos.getY(),
                            spawnPos.getZ() + 0.5,
                            world.random.nextFloat() * 360.0F,
                            0.0F
                    );
                    world.spawnEntity(boss);
                    spawnedCount[0]++;
                }
            }

            source.sendFeedback(() -> Text.literal("§6✦ Spawned " + spawnedCount[0] + " bosses around " + player.getName().getString() + "!")
                    .formatted(Formatting.GOLD), true);
            source.sendFeedback(() -> Text.literal("§c⚠ WARNING: This may cause serious lag!")
                    .formatted(Formatting.RED), false);

            return 1;
        } catch (Exception e) {
            source.sendFeedback(() -> Text.literal("Error: Command must be run by a player! " + e.getMessage())
                    .formatted(Formatting.RED), false);
            e.printStackTrace();
            return 0;
        }
    }
}
