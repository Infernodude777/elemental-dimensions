package com.nikhil.elementaldimensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.WrittenBookContentComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.RawFilteredPair;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

/**
 * /guide command - Gives players a comprehensive guidebook about all dimensions
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

            // Create written book
            ItemStack book = new ItemStack(Items.WRITTEN_BOOK);

            // Create book pages
            List<RawFilteredPair<Text>> pages = new ArrayList<>();

            // Page 1 - Welcome
            pages.add(RawFilteredPair.of(Text.literal(
                    "§6§lElemental Dimensions§r\n\n" +
                    "§0Welcome, traveler! This guide contains everything you need to know about the 11 mystical dimensions.\n\n" +
                    "§8Use /fire, /water, /earth, /air, and more to explore!"
            )));

            // Page 2 - Inferno Realm
            pages.add(RawFilteredPair.of(Text.literal(
                    "§c§lInferno Realm§r\n\n" +
                    "§0A scorching volcanic wasteland of molten lava and burning stone.\n\n" +
                    "§4Boss: Pyros§0\n" +
                    "The flame lord awaits in lakes of fire.\n\n" +
                    "§8Command: /fire"
            )));

            // Page 3 - Aquatic Realm
            pages.add(RawFilteredPair.of(Text.literal(
                    "§9§lAquatic Realm§r\n\n" +
                    "§0The lost city of Atlantis, submerged in endless ocean depths.\n\n" +
                    "§1Boss: Leviathan§0\n" +
                    "Ancient sea serpent of the deep.\n\n" +
                    "§8Command: /water"
            )));

            // Page 4 - Terran Realm
            pages.add(RawFilteredPair.of(Text.literal(
                    "§a§lTerran Realm§r\n\n" +
                    "§0Crystal caverns filled with glowing gems and underground forests.\n\n" +
                    "§2Boss: Goliath§0\n" +
                    "Stone titan of the earth.\n\n" +
                    "§8Command: /earth"
            )));

            // Page 5 - Skybound Realm
            pages.add(RawFilteredPair.of(Text.literal(
                    "§f§lSkybound Realm§r\n\n" +
                    "§0Floating islands high above the clouds, paradise in the sky.\n\n" +
                    "§7Boss: Zephyros§0\n" +
                    "Wind sovereign of the heavens.\n\n" +
                    "§8Command: /air"
            )));

            // Page 6 - Celestial Realm
            pages.add(RawFilteredPair.of(Text.literal(
                    "§d§lCelestial Realm§r\n\n" +
                    "§0A cosmic dimension of stars and mystical energy.\n\n" +
                    "§5Boss: Primarch§0\n" +
                    "The first being, keeper of cosmic balance.\n\n" +
                    "§8Command: /celestial"
            )));

            // Page 7 - Void Realm
            pages.add(RawFilteredPair.of(Text.literal(
                    "§8§lVoid Realm§r\n\n" +
                    "§0Floating obsidian platforms in an endless void.\n\n" +
                    "§0Boss: Void Warden§0\n" +
                    "Guardian of the empty darkness.\n\n" +
                    "§8Command: /void"
            )));

            // Page 8 - Dreaming Depths
            pages.add(RawFilteredPair.of(Text.literal(
                    "§b§lDreaming Depths§r\n\n" +
                    "§0A surreal dreamscape where reality bends and shifts.\n\n" +
                    "§3Boss: Dream Weaver§0\n" +
                    "Master of nightmares and visions.\n\n" +
                    "§8Command: /dream"
            )));

            // Page 9 - Fungal Dominion
            pages.add(RawFilteredPair.of(Text.literal(
                    "§e§lFungal Dominion§r\n\n" +
                    "§0Creepy overgrown realm of giant mushrooms and spores.\n\n" +
                    "§6Boss: Mycelium King§0\n" +
                    "Corrupted fungal overlord.\n\n" +
                    "§8Command: /fungal"
            )));

            // Page 10 - Forgotten Archive
            pages.add(RawFilteredPair.of(Text.literal(
                    "§6§lForgotten Archive§r\n\n" +
                    "§0Ancient library of infinite knowledge and lost secrets.\n\n" +
                    "§cBoss: The Curator§0\n" +
                    "Eternal keeper of forbidden lore.\n\n" +
                    "§8Command: /archive"
            )));

            // Page 11 - Astral Frontier
            pages.add(RawFilteredPair.of(Text.literal(
                    "§5§lAstral Frontier§r\n\n" +
                    "§0Cosmic realm of swirling nebulae and starlight.\n\n" +
                    "§dBoss: Nebula Entity§0\n" +
                    "Living constellation of pure energy.\n\n" +
                    "§8Command: /astral"
            )));

            // Page 12 - Gloomy Caverns
            pages.add(RawFilteredPair.of(Text.literal(
                    "§8§lGloomy Caverns§r\n\n" +
                    "§0Dark echoing caves that swallow all light.\n\n" +
                    "§0Boss: Echo Horror§0\n" +
                    "Terror that lurks in shadows.\n\n" +
                    "§8Command: /gloom"
            )));

            // Page 13 - Tips & Tricks
            pages.add(RawFilteredPair.of(Text.literal(
                    "§6§lTips & Tricks§r\n\n" +
                    "§0• Each dimension has unique resources\n" +
                    "• Bosses drop powerful items\n" +
                    "• Use /getall for all items\n" +
                    "• Use /spawnallmobs for testing"
            )));

            // Page 14 - Credits
            pages.add(RawFilteredPair.of(Text.literal(
                    "§6§lElemental Dimensions§r\n\n" +
                    "§0Created with passion for exploration and adventure.\n\n" +
                    "§8Version: 1.0.0\n" +
                    "11 Dimensions\n" +
                    "11 Epic Bosses\n" +
                    "Infinite Adventure"
            )));

            // Create book content
            WrittenBookContentComponent bookContent = new WrittenBookContentComponent(
                    RawFilteredPair.of("Elemental Dimensions"),
                    "The Architects",
                    0,
                    pages,
                    true
            );

            book.set(DataComponentTypes.WRITTEN_BOOK_CONTENT, bookContent);

            // Give book to player
            boolean success = player.giveItemStack(book);

            if (success) {
                source.sendFeedback(() -> Text.literal("§6✦ You have received the Elemental Dimensions Guide!")
                        .formatted(Formatting.GOLD), false);
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
}
