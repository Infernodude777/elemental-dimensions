package com.nikhil.elementaldimensions.registry;

import com.nikhil.elementaldimensions.ElementalDimensions;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
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
    
    // Portal Frame Block
    public static final Block CELESTIAL_PORTAL_FRAME = registerBlock("celestial_portal_frame",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .strength(50.0f, 1200.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.BLOCK)));
    
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
     * Initialize all blocks - called from main mod class
     */
    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering blocks for " + ElementalDimensions.MOD_ID);
    }
}
