package com.elementaldimensions.block;

import com.elementaldimensions.ElementalDimensions;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.TranslucentBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
	
	// ===== FIRELANDS BLOCKS =====
	public static final Block SCORCHED_STONE = registerBlock("scorched_stone",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.TERRACOTTA_RED)
					.instrument(NoteBlockInstrument.BASEDRUM)
					.requiresTool()
					.strength(2.0f, 6.0f)
					.sounds(BlockSoundGroup.STONE)));
	
	public static final Block MOLTEN_GLASS = registerBlock("molten_glass",
			new TranslucentBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.ORANGE)
					.strength(0.3f)
					.sounds(BlockSoundGroup.GLASS)
					.nonOpaque()
					.allowsSpawning((state, world, pos, type) -> false)));
	
	public static final Block LAVA_CRYSTAL_ORE = registerBlock("lava_crystal_ore",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.TERRACOTTA_ORANGE)
					.requiresTool()
					.strength(3.0f, 3.0f)
					.sounds(BlockSoundGroup.STONE)
					.luminance(state -> 7)));
	
	public static final Block EMBER_SAND = registerBlock("ember_sand",
			new EmberSandBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.RED)
					.strength(0.5f)
					.sounds(BlockSoundGroup.SAND)
					.luminance(state -> 2)));
	
	public static final Block CHARCOAL_ROCK = registerBlock("charcoal_rock",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.BLACK)
					.requiresTool()
					.strength(1.5f, 6.0f)
					.sounds(BlockSoundGroup.STONE)));
	
	public static final Block OBSIDIAN_GRASS = registerBlock("obsidian_grass",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.TERRACOTTA_RED)
					.requiresTool()
					.strength(1.8f, 6.0f)
					.sounds(BlockSoundGroup.GRASS)
					.luminance(state -> 1)));
	
	public static final Block MAGMATIC_BRICK = registerBlock("magmatic_brick",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DARK_RED)
					.requiresTool()
					.strength(2.5f, 7.0f)
					.sounds(BlockSoundGroup.NETHER_BRICKS)));
	
	public static final Block ASH_LAYER = registerBlock("ash_layer",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.LIGHT_GRAY)
					.strength(0.1f)
					.sounds(BlockSoundGroup.SAND)
					.burnable()));
	
	public static final Block SULFUR_VEIN = registerBlock("sulfur_vein",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.YELLOW)
					.requiresTool()
					.strength(2.5f, 3.0f)
					.sounds(BlockSoundGroup.STONE)));
	
	public static final Block EMBER_ROOT_LOG = registerBlock("ember_root_log",
			new PillarBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.ORANGE)
					.instrument(NoteBlockInstrument.BASS)
					.strength(2.0f)
					.sounds(BlockSoundGroup.WOOD)
					.burnable()
					.luminance(state -> 3)));
	
	public static final Block LAVA_GLASS_TILE = registerBlock("lava_glass_tile",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.ORANGE)
					.requiresTool()
					.strength(1.5f)
					.sounds(BlockSoundGroup.STONE)
					.luminance(state -> 5)));
	
	public static final Block FIRE_FURNACE_CORE = registerBlock("fire_furnace_core",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DARK_RED)
					.requiresTool()
					.strength(3.5f, 8.0f)
					.sounds(BlockSoundGroup.METAL)
					.luminance(state -> 12)));
	
	public static final Block SMOLDERING_COAL_BLOCK = registerBlock("smoldering_coal_block",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.BLACK)
					.requiresTool()
					.strength(5.0f, 6.0f)
					.sounds(BlockSoundGroup.STONE)
					.luminance(state -> 4)));
	
	// ===== AQUATICA BLOCKS =====
	public static final Block CORALITE = registerBlock("coralite",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.CYAN)
					.requiresTool()
					.strength(2.0f, 6.0f)
					.sounds(BlockSoundGroup.CORAL)));
	
	public static final Block ABYSSAL_GLASS = registerBlock("abyssal_glass",
			new TranslucentBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.DARK_AQUA)
					.strength(0.3f)
					.sounds(BlockSoundGroup.GLASS)
					.nonOpaque()
					.luminance(state -> 5)));
	
	public static final Block PEARLSTONE = registerBlock("pearlstone",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.WHITE)
					.requiresTool()
					.strength(1.5f, 6.0f)
					.sounds(BlockSoundGroup.CALCITE)));
	
	public static final Block LUMINESCENT_KELP = registerBlock("luminescent_kelp",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.TEAL)
					.noCollision()
					.strength(0.0f)
					.sounds(BlockSoundGroup.WET_GRASS)
					.luminance(state -> 10)
					.pistonBehavior(PistonBehavior.DESTROY)));
	
	public static final Block PRESSURE_SAND = registerBlock("pressure_sand",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DARK_AQUA)
					.strength(0.6f)
					.sounds(BlockSoundGroup.SAND)));
	
	public static final Block BUBBLE_COLUMN_BLOCK = registerBlock("bubble_column_block",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.WATER_BLUE)
					.strength(1.0f)
					.sounds(BlockSoundGroup.STONE)));
	
	public static final Block SIREN_SHELL = registerBlock("siren_shell",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PINK)
					.strength(1.0f)
					.sounds(BlockSoundGroup.CALCITE)));
	
	public static final Block BRINE_ROCK = registerBlock("brine_rock",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DARK_DULL_PINK)
					.requiresTool()
					.strength(1.8f, 6.0f)
					.sounds(BlockSoundGroup.STONE)));
	
	public static final Block OCEANIC_CRYSTAL_ORE = registerBlock("oceanic_crystal_ore",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.LIGHT_BLUE)
					.requiresTool()
					.strength(3.0f, 3.0f)
					.sounds(BlockSoundGroup.AMETHYST_BLOCK)
					.luminance(state -> 6)));
	
	public static final Block TIDEWOOD_LOG = registerBlock("tidewood_log",
			new PillarBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.CYAN)
					.instrument(NoteBlockInstrument.BASS)
					.strength(2.0f)
					.sounds(BlockSoundGroup.WOOD)));
	
	public static final Block SALT_STONE = registerBlock("salt_stone",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.WHITE)
					.requiresTool()
					.strength(1.5f, 6.0f)
					.sounds(BlockSoundGroup.CALCITE)));
	
	// ===== TERRA DEPTHS BLOCKS =====
	public static final Block GAIA_STONE = registerBlock("gaia_stone",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DARK_GREEN)
					.requiresTool()
					.strength(2.0f, 6.0f)
					.sounds(BlockSoundGroup.DEEPSLATE)));
	
	public static final Block ROOTED_EARTH = registerBlock("rooted_earth",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.BROWN)
					.strength(0.5f)
					.sounds(BlockSoundGroup.ROOTED_DIRT)));
	
	public static final Block CRYSTAL_CLUSTER = registerBlock("crystal_cluster",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.LIME)
					.solid()
					.nonOpaque()
					.strength(1.5f)
					.sounds(BlockSoundGroup.AMETHYST_CLUSTER)
					.luminance(state -> 8)
					.pistonBehavior(PistonBehavior.DESTROY)));
	
	public static final Block MOSS_TILE = registerBlock("moss_tile",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.GREEN)
					.strength(1.0f)
					.sounds(BlockSoundGroup.MOSS_BLOCK)
					.slipperiness(0.95f)));
	
	public static final Block LUM_SHALE = registerBlock("lum_shale",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PALE_GREEN)
					.requiresTool()
					.strength(1.5f, 6.0f)
					.sounds(BlockSoundGroup.DEEPSLATE)
					.luminance(state -> 4)));
	
	public static final Block FUNGAL_FIBER = registerBlock("fungal_fiber",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.TERRACOTTA_WHITE)
					.strength(0.8f)
					.sounds(BlockSoundGroup.MOSS_CARPET)));
	
	public static final Block MYCELIUM_LOG = registerBlock("mycelium_log",
			new PillarBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.PALE_PURPLE)
					.instrument(NoteBlockInstrument.BASS)
					.strength(2.0f)
					.sounds(BlockSoundGroup.NETHER_STEM)));
	
	public static final Block TERRA_ORE = registerBlock("terra_ore",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.EMERALD_GREEN)
					.requiresTool()
					.strength(3.0f, 3.0f)
					.sounds(BlockSoundGroup.STONE)
					.luminance(state -> 5)));
	
	public static final Block STONE_LATTICE = registerBlock("stone_lattice",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.STONE_GRAY)
					.requiresTool()
					.strength(1.5f)
					.sounds(BlockSoundGroup.STONE)
					.nonOpaque()));
	
	// ===== SKYREACH PEAKS BLOCKS =====
	public static final Block SKYROCK = registerBlock("skyrock",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.LIGHT_GRAY)
					.requiresTool()
					.strength(1.5f, 6.0f)
					.sounds(BlockSoundGroup.CALCITE)
					.luminance(state -> 2)));
	
	public static final Block WIND_CRYSTAL = registerBlock("wind_crystal",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DIAMOND_BLUE)
					.requiresTool()
					.strength(2.0f)
					.sounds(BlockSoundGroup.AMETHYST_BLOCK)
					.luminance(state -> 7)));
	
	public static final Block CLOUD_SOIL = registerBlock("cloud_soil",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.WHITE)
					.strength(0.4f)
					.sounds(BlockSoundGroup.SNOW)
					.slipperiness(0.85f)));
	
	public static final Block PETAL_TILE = registerBlock("petal_tile",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PINK)
					.strength(1.0f)
					.sounds(BlockSoundGroup.MOSS_BLOCK)));
	
	public static final Block AIR_LATTICE = registerBlock("air_lattice",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.WHITE)
					.requiresTool()
					.strength(1.0f)
					.sounds(BlockSoundGroup.METAL)
					.nonOpaque()));
	
	public static final Block STRATUS_SHARD = registerBlock("stratus_shard",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.LIGHT_BLUE)
					.requiresTool()
					.strength(3.0f, 3.0f)
					.sounds(BlockSoundGroup.GLASS)
					.luminance(state -> 6)));
	
	public static final Block GUST_ROCK = registerBlock("gust_rock",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.GRAY)
					.requiresTool()
					.strength(2.0f, 6.0f)
					.sounds(BlockSoundGroup.STONE)));
	
	// ===== CELESTIAL DIMENSION BLOCKS =====
	public static final Block CELESTIAL_STONE = registerBlock("celestial_stone",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PURPLE)
					.requiresTool()
					.strength(3.0f, 9.0f)
					.sounds(BlockSoundGroup.DEEPSLATE)
					.luminance(state -> 8)));
	
	public static final Block VOID_CRYSTAL_ORE = registerBlock("void_crystal_ore",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PURPLE)
					.requiresTool()
					.strength(5.0f, 6.0f)
					.sounds(BlockSoundGroup.AMETHYST_BLOCK)
					.luminance(state -> 10)));
	
	public static final Block STARLIGHT_MOSS = registerBlock("starlight_moss",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.BRIGHT_TEAL)
					.strength(0.6f)
					.sounds(BlockSoundGroup.MOSS_BLOCK)
					.luminance(state -> 12)));
	
	public static final Block NEBULA_SAND = registerBlock("nebula_sand",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PURPLE)
					.strength(0.5f)
					.sounds(BlockSoundGroup.SAND)
					.luminance(state -> 3)));
	
	public static final Block RIFT_BLOCK = registerBlock("rift_block",
			new RiftBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.BLACK)
					.requiresTool()
					.strength(4.0f, 1200.0f)
					.sounds(BlockSoundGroup.ANCIENT_DEBRIS)
					.luminance(state -> 1)));
	
	public static final Block ASTRAL_TILE = registerBlock("astral_tile",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DIAMOND_BLUE)
					.requiresTool()
					.strength(2.0f)
					.sounds(BlockSoundGroup.STONE)));
	
	public static final Block COSMIC_SHARD = registerBlock("cosmic_shard",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PURPLE)
					.requiresTool()
					.strength(1.5f)
					.sounds(BlockSoundGroup.GLASS)
					.luminance(state -> 7)));
	
	public static final Block LUMEN_CAP = registerBlock("lumen_cap",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.WHITE)
					.strength(0.5f)
					.sounds(BlockSoundGroup.LANTERN)
					.luminance(state -> 15)));
	
	// ===== VOID REALM BLOCKS =====
	public static final Block VOIDSTONE = registerBlock("voidstone",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.BLACK)
					.requiresTool()
					.strength(3.0f, 9.0f)
					.sounds(BlockSoundGroup.DEEPSLATE)));
	
	public static final Block VOID_CRYSTAL = registerBlock("void_crystal",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.PURPLE)
					.requiresTool()
					.strength(2.0f)
					.sounds(BlockSoundGroup.AMETHYST_BLOCK)
					.luminance(state -> 8)));
	
	public static final Block NULLROCK = registerBlock("nullrock",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.GRAY)
					.requiresTool()
					.strength(4.0f, 15.0f)
					.sounds(BlockSoundGroup.DEEPSLATE)));
	
	public static final Block DARK_MATTER_BLOCK = registerBlock("dark_matter_block",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.BLACK)
					.requiresTool()
					.strength(50.0f, 1200.0f)
					.sounds(BlockSoundGroup.NETHERITE)
					.luminance(state -> 2)));
	
	public static final Block VOID_GLASS = registerBlock("void_glass",
			new TranslucentBlock(AbstractBlock.Settings.create()
					.mapColor(MapColor.BLACK)
					.strength(0.3f)
					.sounds(BlockSoundGroup.GLASS)
					.nonOpaque()
					.allowsSpawning((state, world, pos, type) -> false)));
	
	public static final Block ETHEREAL_GRASS = registerBlock("ethereal_grass",
			new Block(AbstractBlock.Settings.create()
					.mapColor(MapColor.DARK_DULL_PINK)
					.strength(0.6f)
					.sounds(BlockSoundGroup.GRASS)));
	
	// Helper methods
	private static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, Identifier.of(ElementalDimensions.MOD_ID, name), block);
	}
	
	private static void registerBlockItem(String name, Block block) {
		Registry.register(Registries.ITEM, Identifier.of(ElementalDimensions.MOD_ID, name),
				new BlockItem(block, new Item.Settings()));
	}
	
	public static void register() {
		ElementalDimensions.LOGGER.info("Registering blocks for " + ElementalDimensions.MOD_ID);
	}
}
