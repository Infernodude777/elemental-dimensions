#!/usr/bin/env python3
"""
Generate Block Assets - Elemental Dimensions
Creates blockstates, block models, item models, and loot tables for all registered blocks
"""

import os
import json

MOD_ID = "elementaldimensions"

# Output directories
BLOCKSTATES_DIR = r"ResourcePack_ElementalDimensions\assets\elementaldimensions\blockstates"
BLOCK_MODELS_DIR = r"ResourcePack_ElementalDimensions\assets\elementaldimensions\models\block"
ITEM_MODELS_DIR = r"ResourcePack_ElementalDimensions\assets\elementaldimensions\models\item"
LOOT_TABLES_DIR = r"ResourcePack_ElementalDimensions\data\elementaldimensions\loot_tables\blocks"

# Create directories
for directory in [BLOCKSTATES_DIR, BLOCK_MODELS_DIR, ITEM_MODELS_DIR, LOOT_TABLES_DIR]:
    os.makedirs(directory, exist_ok=True)

# All registered blocks from ModBlocks.java (128 blocks)
BLOCKS = [
    # Celestial Dimension
    "celestial_stone", "celestial_ore", "starlight_moss", "void_rift_block",
    # Inferno Realm
    "inferno_stone", "blazing_ore",
    # Aquatic Realm
    "aqua_stone", "tidal_crystal",
    # Terran Realm
    "terran_bedrock", "emerald_cluster",
    # Skybound Realm
    "cloud_stone", "wind_crystal",
    # Dreaming Depths (20 blocks)
    "dreamstone", "lullglass", "softsoil", "dreamgrass", "echo_crystal",
    "dreamwood_log", "lucid_water", "phantasm_dust", "etherstone", "mirage_leaves",
    "dreamwood_planks", "reverie_ore", "reverie_block", "somnia_brick",
    "dreamstone_stairs", "dreamstone_slab", "lulled_vines", "dream_lantern",
    "phantasm_fungus", "dream_sand",
    # Celestine Expanse (19 blocks)
    "celestine_grass", "starlit_stone", "lumicore_block", "astral_glass",
    "radiant_crystal", "sky_reef", "lumicore_ore", "celestine_dirt",
    "radiant_pillar", "skybound_stone_brick", "astral_vine", "twilight_grass",
    "luminous_coral", "starlit_cobblestone", "celestine_cloud", "astral_lantern",
    "lumicore_pillar", "radiant_block", "skybound_moss",
    # Fungal Dominion (19 blocks)
    "mycelite_soil", "root_flesh", "biolume_cap", "fungal_bark", "mycelite_brick",
    "spore_block", "mycelite_ore", "mycelite_block", "giant_mushroom_stem",
    "luminous_fungus", "fungal_tendril", "molded_stone", "mycelite_stone",
    "spore_lamp", "glowing_moss_block", "fungal_planks", "root_network",
    "biolume_cluster", "spore_crystal",
    # Forgotten Archive (19 blocks)
    "runestone", "runestone_brick", "archive_sandstone", "ink_marble",
    "glyph_glass", "ancient_shelf", "dusty_tome_block", "glyph_ore",
    "glyph_block", "polished_runestone", "chiseled_runestone", "golden_pillar",
    "ink_well_block", "archived_scroll", "knowledge_crystal", "runic_lantern",
    "archive_sandstone_brick", "smooth_ink_marble", "ancient_tablet",
    # Astral Frontier (19 blocks)
    "starlit_grass", "stardust_stone", "celestite_block", "celestite_crystal",
    "nebular_dust", "cosmic_stone", "celestite_ore", "star_fragment_block",
    "nebular_glass", "void_stone", "cosmic_pillar", "astral_brick",
    "starlight_torch", "celestite_pillar", "nebular_stone", "cosmic_lantern",
    "stardust_block", "void_crystal", "astral_moss",
    # Gloomy Caverns (19 blocks)
    "withered_basalt", "echo_stone", "drip_moss", "gloom_crystal", "bone_brick",
    "shadestone", "gloom_ore", "gloom_block", "withered_brick", "echo_pillar",
    "despair_vine", "mournstone", "mournstone_brick", "gloom_lantern",
    "withered_roots", "drip_stone", "echo_brick", "gloom_glass", "shadestone_pillar",
    # Portal
    "celestial_portal_frame"
]

# Block types that need special handling
GLASS_BLOCKS = [
    "lullglass", "tidal_crystal", "wind_crystal", "echo_crystal",
    "astral_glass", "radiant_crystal", "glyph_glass", "nebular_glass",
    "void_crystal", "gloom_crystal", "gloom_glass", "lucid_water",
    "ink_well_block"
]

ORE_BLOCKS = [
    "celestial_ore", "blazing_ore", "emerald_cluster", "reverie_ore",
    "lumicore_ore", "mycelite_ore", "glyph_ore", "celestite_ore", "gloom_ore"
]

PLANT_BLOCKS = [
    "starlight_moss", "dreamgrass", "mirage_leaves", "dreamwood_log",
    "celestine_grass", "astral_vine", "twilight_grass", "luminous_coral",
    "skybound_moss", "biolume_cap", "luminous_fungus", "fungal_tendril",
    "glowing_moss_block", "starlit_grass", "astral_moss", "drip_moss",
    "despair_vine", "withered_roots", "phantasm_fungus", "softsoil"
]

LOG_BLOCKS = [
    "dreamwood_log", "fungal_bark", "giant_mushroom_stem"
]

PILLAR_BLOCKS = [
    "radiant_pillar", "lumicore_pillar", "golden_pillar", "cosmic_pillar",
    "celestite_pillar", "echo_pillar", "shadestone_pillar"
]

LANTERN_BLOCKS = [
    "dream_lantern", "astral_lantern", "runic_lantern", "cosmic_lantern", "gloom_lantern"
]

STAIRS_BLOCKS = ["dreamstone_stairs"]
SLAB_BLOCKS = ["dreamstone_slab"]

def create_blockstate_simple(block_name):
    """Create a simple blockstate with single variant"""
    return {
        "variants": {
            "": {
                "model": f"{MOD_ID}:block/{block_name}"
            }
        }
    }

def create_blockstate_stairs(block_name):
    """Create blockstate for stairs (multiple variants for rotation/shape)"""
    base_model = f"{MOD_ID}:block/{block_name}"
    return {
        "variants": {
            "facing=east,half=bottom,shape=straight": {"model": base_model},
            "facing=west,half=bottom,shape=straight": {"model": base_model, "y": 180, "uvlock": True},
            "facing=south,half=bottom,shape=straight": {"model": base_model, "y": 90, "uvlock": True},
            "facing=north,half=bottom,shape=straight": {"model": base_model, "y": 270, "uvlock": True},
            "facing=east,half=top,shape=straight": {"model": base_model, "x": 180, "uvlock": True},
            "facing=west,half=top,shape=straight": {"model": base_model, "x": 180, "y": 180, "uvlock": True},
            "facing=south,half=top,shape=straight": {"model": base_model, "x": 180, "y": 90, "uvlock": True},
            "facing=north,half=top,shape=straight": {"model": base_model, "x": 180, "y": 270, "uvlock": True}
        }
    }

def create_blockstate_slab(block_name):
    """Create blockstate for slab (bottom/top/double variants)"""
    return {
        "variants": {
            "type=bottom": {"model": f"{MOD_ID}:block/{block_name}"},
            "type=top": {"model": f"{MOD_ID}:block/{block_name}_top"},
            "type=double": {"model": f"{MOD_ID}:block/{block_name.replace('_slab', '')}"}
        }
    }

def create_blockstate_pillar(block_name):
    """Create blockstate for pillar (rotatable on axis)"""
    return {
        "variants": {
            "axis=x": {"model": f"{MOD_ID}:block/{block_name}", "x": 90, "y": 90},
            "axis=y": {"model": f"{MOD_ID}:block/{block_name}"},
            "axis=z": {"model": f"{MOD_ID}:block/{block_name}", "x": 90}
        }
    }

def create_block_model_simple(block_name):
    """Create a simple cube_all block model"""
    return {
        "parent": "minecraft:block/cube_all",
        "textures": {
            "all": f"{MOD_ID}:block/{block_name}"
        }
    }

def create_block_model_pillar(block_name):
    """Create a pillar block model with top/side textures"""
    return {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": f"{MOD_ID}:block/{block_name}_top",
            "side": f"{MOD_ID}:block/{block_name}"
        }
    }

def create_block_model_log(block_name):
    """Create a log block model"""
    return {
        "parent": "minecraft:block/cube_column",
        "textures": {
            "end": f"{MOD_ID}:block/{block_name}_top",
            "side": f"{MOD_ID}:block/{block_name}"
        }
    }

def create_block_model_lantern(block_name):
    """Create a lantern block model (uses cross parent for simplicity)"""
    return {
        "parent": "minecraft:block/cross",
        "textures": {
            "cross": f"{MOD_ID}:block/{block_name}"
        }
    }

def create_block_model_plant(block_name):
    """Create a plant block model (cross pattern)"""
    return {
        "parent": "minecraft:block/cross",
        "textures": {
            "cross": f"{MOD_ID}:block/{block_name}"
        }
    }

def create_item_model_block(block_name):
    """Create an item model that references the block model"""
    return {
        "parent": f"{MOD_ID}:block/{block_name}"
    }

def create_item_model_generated(block_name):
    """Create a generated item model (for non-block items like lanterns)"""
    return {
        "parent": "minecraft:item/generated",
        "textures": {
            "layer0": f"{MOD_ID}:block/{block_name}"
        }
    }

def create_loot_table_simple(block_name):
    """Create a simple loot table that drops the block itself"""
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "rolls": 1,
                "entries": [
                    {
                        "type": "minecraft:item",
                        "name": f"{MOD_ID}:{block_name}"
                    }
                ],
                "conditions": [
                    {
                        "condition": "minecraft:survives_explosion"
                    }
                ]
            }
        ]
    }

def create_loot_table_silk_touch(block_name):
    """Create a loot table that requires silk touch (for glass/crystal blocks)"""
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "rolls": 1,
                "entries": [
                    {
                        "type": "minecraft:item",
                        "name": f"{MOD_ID}:{block_name}",
                        "conditions": [
                            {
                                "condition": "minecraft:match_tool",
                                "predicate": {
                                    "enchantments": [
                                        {
                                            "enchantment": "minecraft:silk_touch",
                                            "levels": {
                                                "min": 1
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    }
                ],
                "conditions": [
                    {
                        "condition": "minecraft:survives_explosion"
                    }
                ]
            }
        ]
    }

def create_loot_table_slab(block_name):
    """Create a loot table for slabs (drops 2 if double slab)"""
    return {
        "type": "minecraft:block",
        "pools": [
            {
                "rolls": 1,
                "entries": [
                    {
                        "type": "minecraft:item",
                        "name": f"{MOD_ID}:{block_name}",
                        "functions": [
                            {
                                "function": "minecraft:set_count",
                                "count": 2,
                                "conditions": [
                                    {
                                        "condition": "minecraft:block_state_property",
                                        "block": f"{MOD_ID}:{block_name}",
                                        "properties": {
                                            "type": "double"
                                        }
                                    }
                                ]
                            },
                            {
                                "function": "minecraft:explosion_decay"
                            }
                        ]
                    }
                ]
            }
        ]
    }

print("=" * 80)
print("GENERATING BLOCK ASSETS - ELEMENTAL DIMENSIONS")
print("=" * 80)

blockstate_count = 0
block_model_count = 0
item_model_count = 0
loot_table_count = 0

print(f"\n🔷 Processing {len(BLOCKS)} blocks...")

for block_name in BLOCKS:
    # === BLOCKSTATE ===
    if block_name in STAIRS_BLOCKS:
        blockstate = create_blockstate_stairs(block_name)
    elif block_name in SLAB_BLOCKS:
        blockstate = create_blockstate_slab(block_name)
    elif block_name in PILLAR_BLOCKS or block_name in LOG_BLOCKS:
        blockstate = create_blockstate_pillar(block_name)
    else:
        blockstate = create_blockstate_simple(block_name)

    blockstate_path = os.path.join(BLOCKSTATES_DIR, f"{block_name}.json")
    with open(blockstate_path, 'w') as f:
        json.dump(blockstate, f, indent=2)
    blockstate_count += 1

    # === BLOCK MODEL ===
    if block_name in PILLAR_BLOCKS:
        block_model = create_block_model_pillar(block_name)
    elif block_name in LOG_BLOCKS:
        block_model = create_block_model_log(block_name)
    elif block_name in LANTERN_BLOCKS:
        block_model = create_block_model_lantern(block_name)
    elif block_name in PLANT_BLOCKS:
        block_model = create_block_model_plant(block_name)
    else:
        block_model = create_block_model_simple(block_name)

    block_model_path = os.path.join(BLOCK_MODELS_DIR, f"{block_name}.json")
    with open(block_model_path, 'w') as f:
        json.dump(block_model, f, indent=2)
    block_model_count += 1

    # === ITEM MODEL ===
    if block_name in LANTERN_BLOCKS or block_name in PLANT_BLOCKS:
        item_model = create_item_model_generated(block_name)
    else:
        item_model = create_item_model_block(block_name)

    item_model_path = os.path.join(ITEM_MODELS_DIR, f"{block_name}.json")
    with open(item_model_path, 'w') as f:
        json.dump(item_model, f, indent=2)
    item_model_count += 1

    # === LOOT TABLE ===
    if block_name in GLASS_BLOCKS:
        loot_table = create_loot_table_silk_touch(block_name)
    elif block_name in SLAB_BLOCKS:
        loot_table = create_loot_table_slab(block_name)
    else:
        loot_table = create_loot_table_simple(block_name)

    loot_table_path = os.path.join(LOOT_TABLES_DIR, f"{block_name}.json")
    with open(loot_table_path, 'w') as f:
        json.dump(loot_table, f, indent=2)
    loot_table_count += 1

    if (blockstate_count % 20 == 0):
        print(f"  Processed {blockstate_count}/{len(BLOCKS)} blocks...")

print(f"\n✅ Created {blockstate_count} blockstate JSONs")
print(f"✅ Created {block_model_count} block model JSONs")
print(f"✅ Created {item_model_count} item model JSONs")
print(f"✅ Created {loot_table_count} loot table JSONs")

print("\n" + "=" * 80)
print("🎉 BLOCK ASSETS GENERATION COMPLETE!")
print(f"   Total files created: {blockstate_count + block_model_count + item_model_count + loot_table_count}")
print("=" * 80)
print("\n✅ All blocks now have:")
print("   - Blockstate definitions (how they appear in world)")
print("   - Block models (visual representation)")
print("   - Item models (inventory icons)")
print("   - Loot tables (what they drop when mined)")
print("=" * 80 + "\n")
