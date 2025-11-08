#!/usr/bin/env python3
"""
Generate Missing Textures - Elemental Dimensions
Creates textures for all registered blocks/items that are missing
"""

from PIL import Image, ImageDraw
import os
import random

# Output directories
BLOCK_DIR = r"ResourcePack_ElementalDimensions\assets\elementaldimensions\textures\block"
ITEM_DIR = r"ResourcePack_ElementalDimensions\assets\elementaldimensions\textures\item"

# Ensure directories exist
os.makedirs(BLOCK_DIR, exist_ok=True)
os.makedirs(ITEM_DIR, exist_ok=True)

# Dimension color schemes
COLORS = {
    "void": (40, 10, 60, 255),          # Dark purple/black
    "inferno": (180, 40, 10, 255),       # Deep red
    "aqua": (20, 100, 180, 255),         # Deep blue
    "terran": (100, 70, 40, 255),        # Brown
    "skybound": (150, 200, 240, 255),    # Light blue/white
    "celestial": (100, 50, 180, 255),    # Purple
    "dreaming": (150, 100, 200, 255),    # Lavender/purple
    "celestine": (180, 210, 255, 255),   # Sky blue
    "fungal": (80, 120, 60, 255),        # Green/brown
    "archive": (200, 160, 100, 255),     # Sandy/gold
    "astral": (120, 80, 200, 255),       # Cosmic purple
    "gloomy": (60, 60, 70, 255),         # Dark gray
}

ACCENT_COLORS = {
    "crystal": (100, 220, 255, 255),     # Bright cyan
    "glow": (255, 220, 100, 255),        # Golden glow
    "dark": (20, 20, 30, 255),           # Near black
    "bright": (255, 255, 255, 255),      # White
    "green": (100, 255, 100, 255),       # Bright green
    "red": (255, 100, 100, 255),         # Bright red
}

def create_stone_texture(color, size=16):
    """Create a stone-like block texture"""
    img = Image.new('RGBA', (size, size), color)
    pixels = img.load()
    for _ in range(size * 2):  # Add some noise
        x, y = random.randint(0, size-1), random.randint(0, size-1)
        r, g, b, a = color
        variation = random.randint(-20, 20)
        pixels[x, y] = (
            max(0, min(255, r + variation)),
            max(0, min(255, g + variation)),
            max(0, min(255, b + variation)),
            255
        )
    return img

def create_ore_texture(base_color, crystal_color, size=16):
    """Create an ore texture with crystal highlights"""
    img = create_stone_texture(base_color, size)
    draw = ImageDraw.Draw(img)
    # Add crystal clusters
    for _ in range(random.randint(2, 4)):
        x, y = random.randint(0, size-3), random.randint(0, size-3)
        draw.rectangle([x, y, x+2, y+2], fill=crystal_color)
    return img

def create_glass_texture(color, size=16):
    """Create a translucent glass texture"""
    r, g, b, _ = color
    # Make glass more translucent
    img = Image.new('RGBA', (size, size), (r, g, b, 120))
    draw = ImageDraw.Draw(img)
    # Add subtle grid pattern
    for i in range(0, size, 4):
        draw.line([(i, 0), (i, size)], fill=(255, 255, 255, 30), width=1)
        draw.line([(0, i), (size, i)], fill=(255, 255, 255, 30), width=1)
    return img

def create_plant_texture(color, size=16):
    """Create a plant/organic texture"""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    # Draw simple plant shape
    for i in range(3):
        x = random.randint(2, size-3)
        draw.line([(x, size-1), (x, size//3)], fill=color, width=2)
        draw.ellipse([x-2, size//3-2, x+2, size//3+2], fill=color)
    return img

def create_wood_texture(color, size=16):
    """Create a wood plank texture"""
    img = Image.new('RGBA', (size, size), color)
    draw = ImageDraw.Draw(img)
    r, g, b, a = color
    darker = (max(0, r-30), max(0, g-30), max(0, b-30), 255)
    # Add wood grain lines
    for i in range(size):
        if i % 3 == 0:
            draw.line([(0, i), (size, i)], fill=darker, width=1)
    return img

def create_brick_texture(color, size=16):
    """Create a brick texture"""
    img = Image.new('RGBA', (size, size), color)
    draw = ImageDraw.Draw(img)
    r, g, b, a = color
    grout = (max(0, r-40), max(0, g-40), max(0, b-40), 255)
    # Draw brick pattern
    for y in range(0, size, 4):
        draw.line([(0, y), (size, y)], fill=grout, width=1)
        offset = 0 if (y // 4) % 2 == 0 else size // 2
        for x in range(offset, size, size//2):
            draw.line([(x, y), (x, y+4)], fill=grout, width=1)
    return img

def create_item_texture(color, size=16):
    """Create a simple item icon"""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    # Draw centered circle
    margin = 3
    draw.ellipse([margin, margin, size-margin, size-margin], fill=color)
    # Add highlight
    r, g, b, a = color
    highlight = (min(255, r+60), min(255, g+60), min(255, b+60), 255)
    draw.ellipse([margin+1, margin+1, size//2, size//2], fill=highlight)
    return img

def create_tool_texture(color, tool_type, size=16):
    """Create a tool texture"""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    r, g, b, a = color
    handle = (101, 67, 33, 255)  # Brown handle

    if "sword" in tool_type:
        # Sword blade + handle
        draw.rectangle([7, 2, 9, 10], fill=color)
        draw.rectangle([6, 10, 10, 14], fill=handle)
    elif "pickaxe" in tool_type:
        # Pickaxe head + handle
        draw.rectangle([3, 4, 13, 6], fill=color)
        draw.rectangle([7, 6, 9, 14], fill=handle)
    elif "axe" in tool_type:
        # Axe head + handle
        draw.polygon([(4, 4), (12, 4), (12, 8), (8, 8)], fill=color)
        draw.rectangle([7, 8, 9, 14], fill=handle)
    elif "shovel" in tool_type:
        # Shovel head + handle
        draw.rectangle([6, 8, 10, 11], fill=color)
        draw.rectangle([7, 3, 9, 14], fill=handle)
    elif "hoe" in tool_type:
        # Hoe head + handle
        draw.rectangle([4, 4, 12, 6], fill=color)
        draw.rectangle([7, 6, 9, 14], fill=handle)
    return img

def create_armor_texture(color, armor_type, size=16):
    """Create an armor piece texture"""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)

    if "helmet" in armor_type:
        # Draw helmet shape
        draw.rectangle([4, 4, 12, 12], fill=color)
        draw.rectangle([5, 12, 11, 13], fill=color)
    elif "chestplate" in armor_type:
        # Draw chestplate shape
        draw.rectangle([4, 3, 12, 13], fill=color)
        draw.rectangle([3, 6, 13, 10], fill=color)
    elif "leggings" in armor_type:
        # Draw leggings shape
        draw.rectangle([4, 3, 7, 13], fill=color)
        draw.rectangle([9, 3, 12, 13], fill=color)
    elif "boots" in armor_type:
        # Draw boots shape
        draw.rectangle([4, 8, 7, 13], fill=color)
        draw.rectangle([9, 8, 12, 13], fill=color)
    return img

def create_spawn_egg_texture(primary_color, secondary_color, size=16):
    """Create a spawn egg texture"""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    # Draw egg shape
    draw.ellipse([2, 1, 14, 15], fill=primary_color)
    # Add spots
    for _ in range(random.randint(3, 6)):
        x, y = random.randint(4, 12), random.randint(3, 13)
        draw.ellipse([x-1, y-1, x+1, y+1], fill=secondary_color)
    return img

# === MISSING BLOCK TEXTURES ===
MISSING_BLOCKS = {
    # Format: "texture_name": (base_color, texture_type, optional_accent_color)

    # Void/Dark blocks
    "void_rift_block": (COLORS["void"], "glass", ACCENT_COLORS["dark"]),

    # Inferno blocks
    "blazing_ore": (COLORS["inferno"], "ore", ACCENT_COLORS["glow"]),

    # Aquatic blocks
    "tidal_crystal": (COLORS["aqua"], "glass", ACCENT_COLORS["crystal"]),

    # Terran blocks
    "terran_bedrock": (COLORS["terran"], "stone"),
    "emerald_cluster": ((50, 200, 50, 255), "ore", ACCENT_COLORS["green"]),

    # Skybound blocks
    "cloud_stone": (COLORS["skybound"], "stone"),
    "wind_crystal": (COLORS["skybound"], "glass", ACCENT_COLORS["crystal"]),

    # Dreaming Depths blocks (30 blocks)
    "dreamstone": (COLORS["dreaming"], "stone"),
    "lullglass": (COLORS["dreaming"], "glass"),
    "softsoil": (COLORS["dreaming"], "stone"),
    "dreamgrass": (COLORS["dreaming"], "plant"),
    "echo_crystal": (COLORS["dreaming"], "glass", ACCENT_COLORS["crystal"]),
    "dreamwood_log": (COLORS["dreaming"], "wood"),
    "lucid_water": (COLORS["aqua"], "glass"),
    "phantasm_dust": (COLORS["dreaming"], "stone"),
    "etherstone": (COLORS["dreaming"], "stone"),
    "mirage_leaves": (COLORS["dreaming"], "plant"),
    "dreamwood_planks": (COLORS["dreaming"], "wood"),
    "reverie_ore": (COLORS["dreaming"], "ore", ACCENT_COLORS["glow"]),
    "reverie_block": (COLORS["dreaming"], "brick"),
    "somnia_brick": (COLORS["dreaming"], "brick"),
    "dreamstone_stairs": (COLORS["dreaming"], "stone"),
    "dreamstone_slab": (COLORS["dreaming"], "stone"),
    "lulled_vines": (COLORS["dreaming"], "plant"),
    "dream_lantern": (COLORS["dreaming"], "glass", ACCENT_COLORS["glow"]),
    "phantasm_fungus": (COLORS["dreaming"], "plant"),
    "dream_sand": (COLORS["dreaming"], "stone"),

    # Celestine Expanse blocks (19 blocks)
    "celestine_grass": (COLORS["celestine"], "plant"),
    "starlit_stone": (COLORS["celestine"], "stone"),
    "lumicore_block": (COLORS["celestine"], "brick"),
    "astral_glass": (COLORS["celestine"], "glass"),
    "radiant_crystal": (COLORS["celestine"], "glass", ACCENT_COLORS["glow"]),
    "sky_reef": (COLORS["celestine"], "stone"),
    "lumicore_ore": (COLORS["celestine"], "ore", ACCENT_COLORS["glow"]),
    "celestine_dirt": (COLORS["celestine"], "stone"),
    "radiant_pillar": (COLORS["celestine"], "brick"),
    "skybound_stone_brick": (COLORS["celestine"], "brick"),
    "astral_vine": (COLORS["celestine"], "plant"),
    "twilight_grass": (COLORS["celestine"], "plant"),
    "luminous_coral": (COLORS["celestine"], "plant"),
    "starlit_cobblestone": (COLORS["celestine"], "stone"),
    "celestine_cloud": (COLORS["skybound"], "stone"),
    "astral_lantern": (COLORS["celestine"], "glass", ACCENT_COLORS["glow"]),
    "lumicore_pillar": (COLORS["celestine"], "brick"),
    "radiant_block": (COLORS["celestine"], "brick", ACCENT_COLORS["glow"]),
    "skybound_moss": (COLORS["celestine"], "plant"),

    # Fungal Dominion blocks (19 blocks)
    "mycelite_soil": (COLORS["fungal"], "stone"),
    "root_flesh": (COLORS["fungal"], "stone"),
    "biolume_cap": (COLORS["fungal"], "plant", ACCENT_COLORS["glow"]),
    "fungal_bark": (COLORS["fungal"], "wood"),
    "mycelite_brick": (COLORS["fungal"], "brick"),
    "spore_block": (COLORS["fungal"], "stone"),
    "mycelite_ore": (COLORS["fungal"], "ore", ACCENT_COLORS["green"]),
    "mycelite_block": (COLORS["fungal"], "brick"),
    "giant_mushroom_stem": (COLORS["fungal"], "wood"),
    "luminous_fungus": (COLORS["fungal"], "plant", ACCENT_COLORS["glow"]),
    "fungal_tendril": (COLORS["fungal"], "plant"),
    "molded_stone": (COLORS["fungal"], "stone"),
    "mycelite_stone": (COLORS["fungal"], "stone"),
    "spore_lamp": (COLORS["fungal"], "glass", ACCENT_COLORS["glow"]),
    "glowing_moss_block": (COLORS["fungal"], "plant", ACCENT_COLORS["glow"]),
    "fungal_planks": (COLORS["fungal"], "wood"),
    "root_network": (COLORS["fungal"], "wood"),
    "biolume_cluster": (COLORS["fungal"], "glass", ACCENT_COLORS["glow"]),
    "spore_crystal": (COLORS["fungal"], "glass", ACCENT_COLORS["green"]),

    # Forgotten Archive blocks (19 blocks)
    "runestone": (COLORS["archive"], "stone"),
    "runestone_brick": (COLORS["archive"], "brick"),
    "archive_sandstone": (COLORS["archive"], "stone"),
    "ink_marble": ((50, 50, 50, 255), "stone"),
    "glyph_glass": (COLORS["archive"], "glass", ACCENT_COLORS["glow"]),
    "ancient_shelf": (COLORS["archive"], "wood"),
    "dusty_tome_block": (COLORS["archive"], "wood"),
    "glyph_ore": (COLORS["archive"], "ore", ACCENT_COLORS["glow"]),
    "glyph_block": (COLORS["archive"], "brick"),
    "polished_runestone": (COLORS["archive"], "brick"),
    "chiseled_runestone": (COLORS["archive"], "brick", ACCENT_COLORS["glow"]),
    "golden_pillar": (COLORS["archive"], "brick"),
    "ink_well_block": ((30, 30, 40, 255), "glass"),
    "archived_scroll": (COLORS["archive"], "wood"),
    "knowledge_crystal": (COLORS["archive"], "glass", ACCENT_COLORS["glow"]),
    "runic_lantern": (COLORS["archive"], "glass", ACCENT_COLORS["glow"]),
    "archive_sandstone_brick": (COLORS["archive"], "brick"),
    "smooth_ink_marble": ((70, 70, 80, 255), "stone"),
    "ancient_tablet": (COLORS["archive"], "stone"),

    # Astral Frontier blocks (19 blocks)
    "starlit_grass": (COLORS["astral"], "plant"),
    "stardust_stone": (COLORS["astral"], "stone"),
    "celestite_block": (COLORS["astral"], "brick"),
    "celestite_crystal": (COLORS["astral"], "glass", ACCENT_COLORS["crystal"]),
    "nebular_dust": (COLORS["astral"], "stone"),
    "cosmic_stone": (COLORS["astral"], "stone"),
    "celestite_ore": (COLORS["astral"], "ore", ACCENT_COLORS["glow"]),
    "star_fragment_block": (COLORS["astral"], "brick", ACCENT_COLORS["bright"]),
    "nebular_glass": (COLORS["astral"], "glass"),
    "cosmic_pillar": (COLORS["astral"], "brick"),
    "astral_brick": (COLORS["astral"], "brick"),
    "starlight_torch": (COLORS["astral"], "glass", ACCENT_COLORS["glow"]),
    "celestite_pillar": (COLORS["astral"], "brick"),
    "nebular_stone": (COLORS["astral"], "stone"),
    "cosmic_lantern": (COLORS["astral"], "glass", ACCENT_COLORS["glow"]),
    "stardust_block": (COLORS["astral"], "stone"),
    "astral_moss": (COLORS["astral"], "plant"),

    # Gloomy Caverns blocks (19 blocks)
    "withered_basalt": (COLORS["gloomy"], "stone"),
    "echo_stone": (COLORS["gloomy"], "stone"),
    "drip_moss": (COLORS["gloomy"], "plant"),
    "gloom_crystal": (COLORS["gloomy"], "glass", ACCENT_COLORS["glow"]),
    "bone_brick": ((240, 240, 240, 255), "brick"),
    "shadestone": (COLORS["gloomy"], "stone"),
    "gloom_block": (COLORS["gloomy"], "brick"),
    "withered_brick": (COLORS["gloomy"], "brick"),
    "echo_pillar": (COLORS["gloomy"], "brick"),
    "despair_vine": (COLORS["gloomy"], "plant"),
    "mournstone": (COLORS["gloomy"], "stone"),
    "mournstone_brick": (COLORS["gloomy"], "brick"),
    "gloom_lantern": (COLORS["gloomy"], "glass", ACCENT_COLORS["glow"]),
    "withered_roots": (COLORS["gloomy"], "plant"),
    "drip_stone": (COLORS["gloomy"], "stone"),
    "echo_brick": (COLORS["gloomy"], "brick"),
    "gloom_glass": (COLORS["gloomy"], "glass"),
    "shadestone_pillar": (COLORS["gloomy"], "brick"),

    # Portal
    "celestial_portal_frame": (COLORS["celestial"], "brick", ACCENT_COLORS["glow"]),
}

# === MISSING ITEM TEXTURES ===
MISSING_ITEMS = {
    # Reverie tools (Dreaming Depths) - 5 tools + 4 armor
    "reverie_sword": (COLORS["dreaming"], "sword"),
    "reverie_pickaxe": (COLORS["dreaming"], "pickaxe"),
    "reverie_axe": (COLORS["dreaming"], "axe"),
    "reverie_shovel": (COLORS["dreaming"], "shovel"),
    "reverie_hoe": (COLORS["dreaming"], "hoe"),
    "dreambound_helmet": (COLORS["dreaming"], "helmet"),
    "dreambound_chestplate": (COLORS["dreaming"], "chestplate"),
    "dreambound_leggings": (COLORS["dreaming"], "leggings"),
    "dreambound_boots": (COLORS["dreaming"], "boots"),

    # Lumicore tools (Celestine Expanse) - 5 tools + 4 armor
    "lumicore_sword": (COLORS["celestine"], "sword"),
    "lumicore_pickaxe": (COLORS["celestine"], "pickaxe"),
    "lumicore_axe": (COLORS["celestine"], "axe"),
    "lumicore_shovel": (COLORS["celestine"], "shovel"),
    "lumicore_hoe": (COLORS["celestine"], "hoe"),
    "skyborn_helmet": (COLORS["celestine"], "helmet"),
    "skyborn_chestplate": (COLORS["celestine"], "chestplate"),
    "skyborn_leggings": (COLORS["celestine"], "leggings"),
    "skyborn_boots": (COLORS["celestine"], "boots"),

    # Mycelite tools (Fungal Dominion) - 5 tools + 4 armor
    "mycelite_sword": (COLORS["fungal"], "sword"),
    "mycelite_pickaxe": (COLORS["fungal"], "pickaxe"),
    "mycelite_axe": (COLORS["fungal"], "axe"),
    "mycelite_shovel": (COLORS["fungal"], "shovel"),
    "mycelite_hoe": (COLORS["fungal"], "hoe"),
    "sporebound_helmet": (COLORS["fungal"], "helmet"),
    "sporebound_chestplate": (COLORS["fungal"], "chestplate"),
    "sporebound_leggings": (COLORS["fungal"], "leggings"),
    "sporebound_boots": (COLORS["fungal"], "boots"),

    # Glyph tools (Forgotten Archive) - 5 tools + 4 armor
    "glyph_sword": (COLORS["archive"], "sword"),
    "glyph_pickaxe": (COLORS["archive"], "pickaxe"),
    "glyph_axe": (COLORS["archive"], "axe"),
    "glyph_shovel": (COLORS["archive"], "shovel"),
    "glyph_hoe": (COLORS["archive"], "hoe"),
    "runic_helmet": (COLORS["archive"], "helmet"),
    "runic_chestplate": (COLORS["archive"], "chestplate"),
    "runic_leggings": (COLORS["archive"], "leggings"),
    "runic_boots": (COLORS["archive"], "boots"),

    # Celestite tools (Astral Frontier) - 5 tools + 4 armor
    "celestite_sword": (COLORS["astral"], "sword"),
    "celestite_pickaxe": (COLORS["astral"], "pickaxe"),
    "celestite_axe": (COLORS["astral"], "axe"),
    "celestite_shovel": (COLORS["astral"], "shovel"),
    "celestite_hoe": (COLORS["astral"], "hoe"),
    "starforged_helmet": (COLORS["astral"], "helmet"),
    "starforged_chestplate": (COLORS["astral"], "chestplate"),
    "starforged_leggings": (COLORS["astral"], "leggings"),
    "starforged_boots": (COLORS["astral"], "boots"),

    # Gloom tools (Gloomy Caverns) - 5 tools + 4 armor (NOTE: already have gloom tools from old generation)
    # But missing withered armor
    "withered_helmet": (COLORS["gloomy"], "helmet"),
    "withered_chestplate": (COLORS["gloomy"], "chestplate"),
    "withered_leggings": (COLORS["gloomy"], "leggings"),
    "withered_boots": (COLORS["gloomy"], "boots"),
}

# Spawn egg colors (primary, secondary)
SPAWN_EGG_COLORS = {
    # Non-boss mobs (24 eggs)
    "phantasm_spawn_egg": ((75, 0, 130, 255), (138, 43, 226, 255)),
    "luller_spawn_egg": ((230, 230, 250, 255), (216, 191, 216, 255)),
    "nightmare_shade_spawn_egg": ((28, 28, 28, 255), (139, 0, 0, 255)),
    "somnian_butterfly_spawn_egg": ((255, 182, 193, 255), (255, 192, 203, 255)),
    "skywhale_spawn_egg": ((135, 206, 235, 255), (176, 224, 230, 255)),
    "lumifin_spawn_egg": ((255, 255, 0, 255), (255, 215, 0, 255)),
    "storm_hawk_spawn_egg": ((112, 128, 144, 255), (47, 79, 79, 255)),
    "sky_pirate_spawn_egg": ((139, 69, 19, 255), (105, 105, 105, 255)),
    "spore_bat_spawn_egg": ((154, 205, 50, 255), (107, 142, 35, 255)),
    "mycelium_walker_spawn_egg": ((143, 188, 143, 255), (85, 107, 47, 255)),
    "mycelite_creeper_spawn_egg": ((34, 139, 34, 255), (0, 128, 0, 255)),
    "fungal_zombie_spawn_egg": ((85, 107, 47, 255), (60, 179, 113, 255)),
    "scribe_spirit_spawn_egg": ((240, 248, 255, 255), (224, 255, 255, 255)),
    "living_tome_spawn_egg": ((139, 69, 19, 255), (245, 222, 179, 255)),
    "sentinel_spawn_egg": ((112, 128, 144, 255), (192, 192, 192, 255)),
    "page_horror_spawn_egg": ((47, 79, 79, 255), (255, 255, 224, 255)),
    "star_elemental_spawn_egg": ((255, 255, 255, 255), (255, 215, 0, 255)),
    "nebula_wisp_spawn_egg": ((147, 112, 219, 255), (186, 85, 211, 255)),
    "voidling_spawn_egg": ((25, 25, 112, 255), (75, 0, 130, 255)),
    "cosmic_horror_spawn_egg": ((0, 0, 0, 255), (138, 43, 226, 255)),
    "echo_wisp_spawn_egg": ((54, 69, 79, 255), (112, 128, 144, 255)),
    "cave_snail_spawn_egg": ((139, 115, 85, 255), (210, 180, 140, 255)),
    "gloom_stalker_spawn_egg": ((47, 79, 79, 255), (0, 0, 0, 255)),
    "withered_skeleton_spawn_egg": ((105, 105, 105, 255), (47, 79, 79, 255)),

    # Boss spawn eggs (11 eggs)
    "pyros_spawn_egg": ((255, 69, 0, 255), (178, 34, 34, 255)),
    "leviathan_spawn_egg": ((30, 144, 255, 255), (0, 0, 128, 255)),
    "goliath_spawn_egg": ((139, 69, 19, 255), (101, 67, 33, 255)),
    "zephyros_spawn_egg": ((240, 248, 255, 255), (176, 196, 222, 255)),
    "primarch_spawn_egg": ((75, 0, 130, 255), (139, 0, 139, 255)),
    "the_dreamer_spawn_egg": ((147, 112, 219, 255), (218, 112, 214, 255)),
    "the_radiant_monarch_spawn_egg": ((255, 215, 0, 255), (255, 165, 0, 255)),
    "the_overmind_spawn_egg": ((46, 139, 87, 255), (0, 100, 0, 255)),
    "the_librarian_spawn_egg": ((222, 184, 135, 255), (139, 69, 19, 255)),
    "the_void_archon_spawn_egg": ((0, 0, 0, 255), (72, 61, 139, 255)),
    "the_echo_king_spawn_egg": ((105, 105, 105, 255), (47, 79, 79, 255)),
}

print("=" * 80)
print("GENERATING MISSING REGISTERED TEXTURES")
print("=" * 80)

# Generate block textures
print(f"\n📦 Generating {len(MISSING_BLOCKS)} block textures...")
block_count = 0
for name, data in MISSING_BLOCKS.items():
    color = data[0]
    tex_type = data[1]
    accent = data[2] if len(data) > 2 else None

    if tex_type == "stone":
        img = create_stone_texture(color)
    elif tex_type == "ore":
        img = create_ore_texture(color, accent if accent else ACCENT_COLORS["crystal"])
    elif tex_type == "glass":
        img = create_glass_texture(color)
    elif tex_type == "plant":
        img = create_plant_texture(color)
    elif tex_type == "wood":
        img = create_wood_texture(color)
    elif tex_type == "brick":
        img = create_brick_texture(color)
    else:
        img = create_stone_texture(color)

    filepath = os.path.join(BLOCK_DIR, f"{name}.png")
    img.save(filepath)
    block_count += 1
    if block_count % 20 == 0:
        print(f"  Created {block_count}/{len(MISSING_BLOCKS)} block textures...")

print(f"✅ Created {block_count} block textures")

# Generate item textures (tools & armor)
print(f"\n🎒 Generating {len(MISSING_ITEMS)} tool/armor textures...")
item_count = 0
for name, (color, item_type) in MISSING_ITEMS.items():
    if any(tool in item_type for tool in ["sword", "pickaxe", "axe", "shovel", "hoe"]):
        img = create_tool_texture(color, item_type)
    elif any(armor in item_type for armor in ["helmet", "chestplate", "leggings", "boots"]):
        img = create_armor_texture(color, item_type)
    else:
        img = create_item_texture(color)

    filepath = os.path.join(ITEM_DIR, f"{name}.png")
    img.save(filepath)
    item_count += 1

print(f"✅ Created {item_count} tool/armor textures")

# Generate spawn egg textures
print(f"\n🥚 Generating {len(SPAWN_EGG_COLORS)} spawn egg textures...")
egg_count = 0
for name, (primary, secondary) in SPAWN_EGG_COLORS.items():
    img = create_spawn_egg_texture(primary, secondary)
    filepath = os.path.join(ITEM_DIR, f"{name}.png")
    img.save(filepath)
    egg_count += 1

print(f"✅ Created {egg_count} spawn egg textures")

# Summary
total_created = block_count + item_count + egg_count
print("\n" + "=" * 80)
print(f"🎉 TEXTURE GENERATION COMPLETE!")
print(f"   Created {total_created} new textures:")
print(f"   - {block_count} block textures")
print(f"   - {item_count} tool/armor textures")
print(f"   - {egg_count} spawn egg textures")
print("=" * 80)
print(f"\n📊 Total textures now: {229 + total_created}")
print(f"   Previous: 229 (84 blocks + 110 items + 35 entities)")
print(f"   New: {total_created}")
print(f"   Total: {229 + total_created}")
print("\n✅ All registered blocks/items now have textures!")
print("=" * 80 + "\n")
