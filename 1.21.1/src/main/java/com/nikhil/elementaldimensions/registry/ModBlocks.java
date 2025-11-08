package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

/**
 * Registry for all custom blocks in Elemental Dimensions
 */
public class ModBlocks {

    // Celestial Dimension Blocks
    public static final Block CELESTIAL_STONE = registerBlock("celestial_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(3.0f, 9.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 7)));

    public static final Block CELESTIAL_ORE = registerBlock("celestial_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(5.0f, 15.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 10)));

    public static final Block STARLIGHT_MOSS = registerBlock("starlight_moss",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(0.1f)
                    .sounds(BlockSoundGroup.MOSS_BLOCK)
                    .luminance(state -> 8)
                    .nonOpaque()));

    public static final Block VOID_RIFT_BLOCK = registerBlock("void_rift_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(50.0f, 1200.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 5)));

    // Fire Dimension Blocks
    public static final Block INFERNO_STONE = registerBlock("inferno_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED)
                    .strength(3.0f, 9.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.NETHERRACK)
                    .luminance(state -> 12)));

    public static final Block BLAZING_ORE = registerBlock("blazing_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .strength(5.0f, 15.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.NETHER_ORE)
                    .luminance(state -> 14)));

    // Water Dimension Blocks
    public static final Block AQUA_STONE = registerBlock("aqua_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLUE)
                    .strength(3.0f, 9.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 8)));

    public static final Block TIDAL_CRYSTAL = registerBlock("tidal_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(5.0f, 15.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 12)
                    .nonOpaque()));

    // Earth Dimension Blocks
    public static final Block TERRAN_BEDROCK = registerBlock("terran_bedrock",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(4.0f, 12.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block EMERALD_CLUSTER = registerBlock("emerald_cluster",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GREEN)
                    .strength(5.0f, 15.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                    .luminance(state -> 10)
                    .nonOpaque()));

    // Air Dimension Blocks
    public static final Block CLOUD_STONE = registerBlock("cloud_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(2.0f, 6.0f)
                    .sounds(BlockSoundGroup.WOOL)
                    .luminance(state -> 6)));

    public static final Block WIND_CRYSTAL = registerBlock("wind_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIGHT_GRAY)
                    .strength(3.0f, 9.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 10)
                    .nonOpaque()));

    // Dreaming Depths Blocks
    public static final Block DREAMSTONE = registerBlock("dreamstone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.8f, 4.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 7)));

    public static final Block LULLGLASS = registerBlock("lullglass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .strength(0.3f)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 12)
                    .nonOpaque()));

    public static final Block SOFTSOIL = registerBlock("softsoil",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.SAND)));

    public static final Block DREAMGRASS = registerBlock("dreamgrass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.GRASS)
                    .luminance(state -> 6)));

    public static final Block ECHO_CRYSTAL = registerBlock("echo_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(1.2f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 14)
                    .nonOpaque()));

    public static final Block DREAMWOOD_LOG = registerBlock("dreamwood_log",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.MAGENTA)
                    .strength(2.2f)
                    .sounds(BlockSoundGroup.WOOD)));

    // Celestine Expanse Blocks
    public static final Block CELESTINE_GRASS = registerBlock("celestine_grass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.GRASS)
                    .luminance(state -> 3)));

    public static final Block STARLIT_STONE = registerBlock("starlit_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIGHT_BLUE)
                    .strength(1.5f, 4.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block LUMICORE_BLOCK = registerBlock("lumicore_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .strength(3.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 12)));

    public static final Block ASTRAL_GLASS = registerBlock("astral_glass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.2f)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 7)
                    .nonOpaque()));

    public static final Block RADIANT_CRYSTAL = registerBlock("radiant_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(5.0f, 10.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 15)
                    .nonOpaque()));

    // Fungal Dominion Blocks
    public static final Block MYCELITE_SOIL = registerBlock("mycelite_soil",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.MOSS_BLOCK)));

    public static final Block ROOT_FLESH = registerBlock("root_flesh",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(1.2f, 3.0f)
                    .sounds(BlockSoundGroup.SLIME)
                    .luminance(state -> 2)));

    public static final Block BIOLUME_CAP = registerBlock("biolume_cap",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.FUNGUS)
                    .luminance(state -> 10)));

    public static final Block FUNGAL_BARK = registerBlock("fungal_bark",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(1.2f, 4.0f)
                    .sounds(BlockSoundGroup.WOOD)));

    public static final Block MYCELITE_BRICK = registerBlock("mycelite_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(2.2f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    // Forgotten Archive Blocks
    public static final Block RUNESTONE = registerBlock("runestone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(1.8f, 4.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block RUNESTONE_BRICK = registerBlock("runestone_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(2.4f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block ARCHIVE_SANDSTONE = registerBlock("archive_sandstone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_YELLOW)
                    .strength(1.2f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block INK_MARBLE = registerBlock("ink_marble",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(1.0f, 2.5f)
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 3)
                    .nonOpaque()));

    public static final Block GLYPH_GLASS = registerBlock("glyph_glass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .strength(0.3f)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 8)
                    .nonOpaque()));

    // Astral Frontier Blocks
    public static final Block STARLIT_GRASS = registerBlock("starlit_grass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.GRASS)
                    .luminance(state -> 5)));

    public static final Block STARDUST_STONE = registerBlock("stardust_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.4f, 3.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block CELESTITE_BLOCK = registerBlock("celestite_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIGHT_BLUE)
                    .strength(3.0f, 7.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 12)));

    public static final Block CELESTITE_CRYSTAL = registerBlock("celestite_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(2.0f, 5.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 15)
                    .nonOpaque()));

    public static final Block NEBULAR_DUST = registerBlock("nebular_dust",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.2f)
                    .sounds(BlockSoundGroup.SAND)));

    // Gloomy Caverns Blocks
    public static final Block WITHERED_BASALT = registerBlock("withered_basalt",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(2.8f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.BASALT)));

    public static final Block ECHO_STONE = registerBlock("echo_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(1.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block DRIP_MOSS = registerBlock("drip_moss",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(0.2f)
                    .sounds(BlockSoundGroup.MOSS_BLOCK)
                    .luminance(state -> 4)));

    public static final Block GLOOM_CRYSTAL = registerBlock("gloom_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(2.0f, 5.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 9)
                    .nonOpaque()));

    public static final Block BONE_BRICK = registerBlock("bone_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(2.5f, 7.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.BONE)));

    // Additional Dreaming Depths Blocks
    // OLD: Static lucid water block (kept for compatibility)
    public static final Block LUCID_WATER_OLD = registerBlock("lucid_water_old",
            new com.nikhil.elementaldimensions.block.custom.EnhancedLucidWaterBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WATER_BLUE)
                    .strength(100.0f)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 8)
                    .nonOpaque()
                    .allowsSpawning((state, world, pos, type) -> false)
                    .solidBlock((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)
                    .blockVision((state, world, pos) -> false)));

    // NEW: Flowing fluid blocks
    public static Block LUCID_WATER;
    public static Block RADIANT_FLUID;
    public static Block MYCELIAL_OOZE;
    public static Block VOID_ESSENCE;
    public static Block INK_FLUID;

    public static final Block PHANTASM_DUST = registerBlock("phantasm_dust",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.3f)
                    .sounds(BlockSoundGroup.SAND)));

    public static final Block ETHERSTONE = registerBlock("etherstone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIGHT_BLUE)
                    .strength(2.0f, 5.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 5)));

    public static final Block MIRAGE_LEAVES = registerBlock("mirage_leaves",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .strength(0.2f)
                    .sounds(BlockSoundGroup.GRASS)
                    .luminance(state -> 6)
                    .nonOpaque()));

    public static final Block DREAMWOOD_PLANKS = registerBlock("dreamwood_planks",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(2.0f, 3.0f)
                    .sounds(BlockSoundGroup.WOOD)));

    public static final Block REVERIE_ORE = registerBlock("reverie_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(3.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 10)));

    public static final Block REVERIE_BLOCK = registerBlock("reverie_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(5.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 14)));

    public static final Block SOMNIA_BRICK = registerBlock("somnia_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(2.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    public static final Block DREAMSTONE_STAIRS = registerBlock("dreamstone_stairs",
            new StairsBlock(DREAMSTONE.getDefaultState(), AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.8f, 4.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block DREAMSTONE_SLAB = registerBlock("dreamstone_slab",
            new SlabBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.8f, 4.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block LULLED_VINES = registerBlock("lulled_vines",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.VINE)
                    .luminance(state -> 4)
                    .nonOpaque()));

    public static final Block DREAM_LANTERN = registerBlock("dream_lantern",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(3.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 15)
                    .nonOpaque()));

    public static final Block PHANTASM_FUNGUS = registerBlock("phantasm_fungus",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.FUNGUS)
                    .luminance(state -> 8)
                    .nonOpaque()));

    public static final Block DREAM_SAND = registerBlock("dream_sand",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.SAND)));

    // Additional Celestine Expanse Blocks
    public static final Block SKY_REEF = registerBlock("sky_reef",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CORAL)));

    public static final Block LUMICORE_ORE = registerBlock("lumicore_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .strength(3.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 9)));

    public static final Block CELESTINE_DIRT = registerBlock("celestine_dirt",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DIRT_BROWN)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.GRAVEL)));

    public static final Block RADIANT_PILLAR = registerBlock("radiant_pillar",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(2.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 12)));

    public static final Block SKYBOUND_STONE_BRICK = registerBlock("skybound_stone_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIGHT_BLUE)
                    .strength(2.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    public static final Block ASTRAL_VINE = registerBlock("astral_vine",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.VINE)
                    .luminance(state -> 6)
                    .nonOpaque()));

    public static final Block TWILIGHT_GRASS = registerBlock("twilight_grass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.GRASS)
                    .luminance(state -> 5)
                    .nonOpaque()));

    public static final Block LUMINOUS_CORAL = registerBlock("luminous_coral",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.CORAL)
                    .luminance(state -> 10)
                    .nonOpaque()));

    public static final Block STARLIT_COBBLESTONE = registerBlock("starlit_cobblestone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIGHT_BLUE)
                    .strength(2.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block CELESTINE_CLOUD = registerBlock("celestine_cloud",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(0.2f)
                    .sounds(BlockSoundGroup.WOOL)));

    public static final Block ASTRAL_LANTERN = registerBlock("astral_lantern",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .strength(3.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 15)
                    .nonOpaque()));

    public static final Block LUMICORE_PILLAR = registerBlock("lumicore_pillar",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .strength(3.0f, 8.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 10)));

    public static final Block RADIANT_BLOCK = registerBlock("radiant_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(5.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 15)));

    public static final Block SKYBOUND_MOSS = registerBlock("skybound_moss",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(0.1f)
                    .sounds(BlockSoundGroup.MOSS_CARPET)
                    .luminance(state -> 3)));

    // Additional Fungal Dominion Blocks
    public static final Block SPORE_BLOCK = registerBlock("spore_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.MOSS_BLOCK)
                    .luminance(state -> 5)));

    public static final Block MYCELITE_ORE = registerBlock("mycelite_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(3.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 6)));

    public static final Block MYCELITE_BLOCK = registerBlock("mycelite_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(5.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 10)));

    public static final Block GIANT_MUSHROOM_STEM = registerBlock("giant_mushroom_stem",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(2.0f)
                    .sounds(BlockSoundGroup.STEM)));

    public static final Block LUMINOUS_FUNGUS = registerBlock("luminous_fungus",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.FUNGUS)
                    .luminance(state -> 12)
                    .nonOpaque()));

    public static final Block FUNGAL_TENDRIL = registerBlock("fungal_tendril",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.VINE)
                    .luminance(state -> 7)
                    .nonOpaque()));

    public static final Block MOLDED_STONE = registerBlock("molded_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block MYCELITE_STONE = registerBlock("mycelite_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(1.8f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 4)));

    public static final Block SPORE_LAMP = registerBlock("spore_lamp",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(1.0f)
                    .sounds(BlockSoundGroup.SHROOMLIGHT)
                    .luminance(state -> 15)));

    public static final Block GLOWING_MOSS_BLOCK = registerBlock("glowing_moss_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(0.1f)
                    .sounds(BlockSoundGroup.MOSS_BLOCK)
                    .luminance(state -> 8)));

    public static final Block FUNGAL_PLANKS = registerBlock("fungal_planks",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(2.0f, 3.0f)
                    .sounds(BlockSoundGroup.WOOD)));

    public static final Block ROOT_NETWORK = registerBlock("root_network",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(0.8f, 1.5f)
                    .sounds(BlockSoundGroup.ROOTS)
                    .luminance(state -> 3)));

    public static final Block BIOLUME_CLUSTER = registerBlock("biolume_cluster",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(1.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                    .luminance(state -> 12)
                    .nonOpaque()));

    public static final Block SPORE_CRYSTAL = registerBlock("spore_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(3.0f, 5.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 14)
                    .nonOpaque()));

    // Additional Forgotten Archive Blocks
    public static final Block ANCIENT_SHELF = registerBlock("ancient_shelf",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(2.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.WOOD)));

    public static final Block DUSTY_TOME_BLOCK = registerBlock("dusty_tome_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(1.5f)
                    .sounds(BlockSoundGroup.WOOD)));

    public static final Block GLYPH_ORE = registerBlock("glyph_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(3.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 7)));

    public static final Block GLYPH_BLOCK = registerBlock("glyph_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(5.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 12)));

    public static final Block POLISHED_RUNESTONE = registerBlock("polished_runestone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(2.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block CHISELED_RUNESTONE = registerBlock("chiseled_runestone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(2.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 5)));

    public static final Block GOLDEN_PILLAR = registerBlock("golden_pillar",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(3.0f, 8.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 10)));

    public static final Block INK_WELL_BLOCK = registerBlock("ink_well_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(1.0f)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block ARCHIVED_SCROLL = registerBlock("archived_scroll",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL)));

    public static final Block KNOWLEDGE_CRYSTAL = registerBlock("knowledge_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(2.5f, 5.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 11)
                    .nonOpaque()));

    public static final Block RUNIC_LANTERN = registerBlock("runic_lantern",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(3.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 15)
                    .nonOpaque()));

    public static final Block ARCHIVE_SANDSTONE_BRICK = registerBlock("archive_sandstone_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .strength(1.0f, 1.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block SMOOTH_INK_MARBLE = registerBlock("smooth_ink_marble",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CALCITE)));

    public static final Block ANCIENT_TABLET = registerBlock("ancient_tablet",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(2.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 6)));

    // Additional Astral Frontier Blocks
    public static final Block COSMIC_STONE = registerBlock("cosmic_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 7)));

    public static final Block CELESTITE_ORE = registerBlock("celestite_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(3.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 11)));

    public static final Block STAR_FRAGMENT_BLOCK = registerBlock("star_fragment_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(5.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 15)));

    public static final Block NEBULAR_GLASS = registerBlock("nebular_glass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.3f)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 10)
                    .nonOpaque()));

    public static final Block VOID_STONE = registerBlock("void_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(2.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block COSMIC_PILLAR = registerBlock("cosmic_pillar",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(3.0f, 8.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 12)));

    public static final Block ASTRAL_BRICK = registerBlock("astral_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(2.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    public static final Block STARLIGHT_TORCH = registerBlock("starlight_torch",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.WOOD)
                    .luminance(state -> 14)
                    .nonOpaque()));

    public static final Block CELESTITE_PILLAR = registerBlock("celestite_pillar",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(3.0f, 8.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 10)));

    public static final Block NEBULAR_STONE = registerBlock("nebular_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 8)));

    public static final Block COSMIC_LANTERN = registerBlock("cosmic_lantern",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(3.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 15)
                    .nonOpaque()));

    public static final Block STARDUST_BLOCK = registerBlock("stardust_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.SAND)
                    .luminance(state -> 6)));

    public static final Block VOID_CRYSTAL = registerBlock("void_crystal",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(3.0f, 5.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 5)
                    .nonOpaque()));

    public static final Block ASTRAL_MOSS = registerBlock("astral_moss",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(0.1f)
                    .sounds(BlockSoundGroup.MOSS_CARPET)
                    .luminance(state -> 4)));

    // Additional Gloomy Caverns Blocks
    public static final Block SHADESTONE = registerBlock("shadestone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block GLOOM_ORE = registerBlock("gloom_ore",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(3.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .luminance(state -> 3)));

    public static final Block GLOOM_BLOCK = registerBlock("gloom_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(5.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)
                    .luminance(state -> 7)));

    public static final Block WITHERED_BRICK = registerBlock("withered_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(3.0f, 6.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    public static final Block ECHO_PILLAR = registerBlock("echo_pillar",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(2.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
                    .luminance(state -> 5)));

    public static final Block DESPAIR_VINE = registerBlock("despair_vine",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.VINE)
                    .luminance(state -> 2)
                    .nonOpaque()));

    public static final Block MOURNSTONE = registerBlock("mournstone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(1.8f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block MOURNSTONE_BRICK = registerBlock("mournstone_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(2.2f, 6.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    public static final Block GLOOM_LANTERN = registerBlock("gloom_lantern",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(3.5f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 10)
                    .nonOpaque()));

    public static final Block WITHERED_ROOTS = registerBlock("withered_roots",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .strength(0.0f)
                    .sounds(BlockSoundGroup.ROOTS)
                    .nonOpaque()));

    public static final Block DRIP_STONE = registerBlock("drip_stone",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(1.0f, 3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DRIPSTONE_BLOCK)));

    public static final Block ECHO_BRICK = registerBlock("echo_brick",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .strength(1.5f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    public static final Block GLOOM_GLASS = registerBlock("gloom_glass",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(0.3f)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 5)
                    .nonOpaque()));

    public static final Block SHADESTONE_PILLAR = registerBlock("shadestone_pillar",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(2.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)));

    // Portal Frame Block
    public static final Block CELESTIAL_PORTAL_FRAME = registerBlock("celestial_portal_frame",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(50.0f, 1200.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.BLOCK)));

    // Portal Blocks - Import the portal package at top of file
    public static final Block DREAMING_DEPTHS_PORTAL = registerBlockNoItem("dreaming_depths_portal",
            new com.nikhil.elementaldimensions.block.portal.DreamingDepthsPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 11)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block INFERNO_REALM_PORTAL = registerBlockNoItem("inferno_realm_portal",
            new com.nikhil.elementaldimensions.block.portal.InfernoRealmPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.ORANGE)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 15)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block RADIANT_PLATEAU_PORTAL = registerBlockNoItem("radiant_plateau_portal",
            new com.nikhil.elementaldimensions.block.portal.RadiantPlateauPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 14)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block MYCELIAL_NEXUS_PORTAL = registerBlockNoItem("mycelial_nexus_portal",
            new com.nikhil.elementaldimensions.block.portal.MycelialNexusPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 8)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block GOLDEN_CITADEL_PORTAL = registerBlockNoItem("golden_citadel_portal",
            new com.nikhil.elementaldimensions.block.portal.GoldenCitadelPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 13)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block ANCIENT_GROVES_PORTAL = registerBlockNoItem("ancient_groves_portal",
            new com.nikhil.elementaldimensions.block.portal.AncientGrovesPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GREEN)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 9)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block COSMIC_VOID_PORTAL = registerBlockNoItem("cosmic_void_portal",
            new com.nikhil.elementaldimensions.block.portal.CosmicVoidPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 6)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block CELESTIAL_SANCTUM_PORTAL = registerBlockNoItem("celestial_sanctum_portal",
            new com.nikhil.elementaldimensions.block.portal.CelestialSanctumPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 12)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block GLYPHIC_ARCHIVES_PORTAL = registerBlockNoItem("glyphic_archives_portal",
            new com.nikhil.elementaldimensions.block.portal.GlyphicArchivesPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLUE)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 10)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block ECHOING_EXPANSE_PORTAL = registerBlockNoItem("echoing_expanse_portal",
            new com.nikhil.elementaldimensions.block.portal.EchoingExpansePortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 9)
                    .sounds(BlockSoundGroup.GLASS)));

    public static final Block UMBRAL_WASTELAND_PORTAL = registerBlockNoItem("umbral_wasteland_portal",
            new com.nikhil.elementaldimensions.block.portal.UmbralWastelandPortalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .strength(-1.0f)
                    .noCollision()
                    .luminance(state -> 4)
                    .sounds(BlockSoundGroup.GLASS)));

    /**
     * Register a block with automatic BlockItem registration
     */
    private static Block registerBlock(String name, Block block) {
        Identifier id = Identifier.of(ElementalDimensions.MOD_ID, name);

        // Register block
        Block registeredBlock = Registry.register(Registries.BLOCK, id, block);

        // Register corresponding BlockItem
        Registry.register(Registries.ITEM, id,
                new BlockItem(registeredBlock, new Item.Settings()));

        return registeredBlock;
    }

    /**
     * Register a block without a BlockItem (e.g., for portal blocks)
     */
    private static Block registerBlockNoItem(String name, Block block) {
        Identifier id = Identifier.of(ElementalDimensions.MOD_ID, name);
        return Registry.register(Registries.BLOCK, id, block);
    }

    /**
     * Initialize all blocks - called from main mod class
     */
    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering blocks for " + ElementalDimensions.MOD_ID);

        // Register fluid blocks (after fluids are initialized)
        registerFluidBlocks();
    }

    /**
     * Register fluid blocks - must be called after ModFluids.initialize()
     */
    private static void registerFluidBlocks() {
        LUCID_WATER = registerBlockNoItem("lucid_water",
            new com.nikhil.elementaldimensions.block.DimensionalFluidBlock(
                (net.minecraft.fluid.FlowableFluid) com.nikhil.elementaldimensions.fluid.ModFluids.STILL_LUCID_WATER,
                AbstractBlock.Settings.create()
                    .mapColor(MapColor.WATER_BLUE)
                    .noCollision()
                    .strength(100.0f)
                    .dropsNothing()
                    .liquid()
                    .replaceable(),
                net.minecraft.particle.ParticleTypes.GLOW,
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.NIGHT_VISION, 100, 0),
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.SLOW_FALLING, 40, 0)
            ));

        RADIANT_FLUID = registerBlockNoItem("radiant_fluid",
            new com.nikhil.elementaldimensions.block.DimensionalFluidBlock(
                (net.minecraft.fluid.FlowableFluid) com.nikhil.elementaldimensions.fluid.ModFluids.STILL_RADIANT_FLUID,
                AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .noCollision()
                    .strength(100.0f)
                    .dropsNothing()
                    .liquid()
                    .luminance(state -> 15)
                    .replaceable(),
                net.minecraft.particle.ParticleTypes.END_ROD,
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.GLOWING, 100, 0),
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.REGENERATION, 40, 0)
            ));

        MYCELIAL_OOZE = registerBlockNoItem("mycelial_ooze",
            new com.nikhil.elementaldimensions.block.DimensionalFluidBlock(
                (net.minecraft.fluid.FlowableFluid) com.nikhil.elementaldimensions.fluid.ModFluids.STILL_MYCELIAL_OOZE,
                AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .strength(100.0f)
                    .dropsNothing()
                    .liquid()
                    .replaceable(),
                net.minecraft.particle.ParticleTypes.SPORE_BLOSSOM_AIR,
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.POISON, 60, 0),
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.SLOWNESS, 60, 0)
            ));

        VOID_ESSENCE = registerBlockNoItem("void_essence",
            new com.nikhil.elementaldimensions.block.DimensionalFluidBlock(
                (net.minecraft.fluid.FlowableFluid) com.nikhil.elementaldimensions.fluid.ModFluids.STILL_VOID_ESSENCE,
                AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .noCollision()
                    .strength(100.0f)
                    .dropsNothing()
                    .liquid()
                    .luminance(state -> 2)
                    .replaceable(),
                net.minecraft.particle.ParticleTypes.PORTAL,
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.LEVITATION, 40, 0),
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.DARKNESS, 100, 0)
            ));

        INK_FLUID = registerBlockNoItem("ink_fluid",
            new com.nikhil.elementaldimensions.block.DimensionalFluidBlock(
                (net.minecraft.fluid.FlowableFluid) com.nikhil.elementaldimensions.fluid.ModFluids.STILL_INK_FLUID,
                AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .noCollision()
                    .strength(100.0f)
                    .dropsNothing()
                    .liquid()
                    .replaceable(),
                net.minecraft.particle.ParticleTypes.SQUID_INK,
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.BLINDNESS, 100, 0),
                new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.WEAKNESS, 60, 0)
            ));
    }
}
