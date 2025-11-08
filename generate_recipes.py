"""
Generate ALL crafting recipes for Elemental Dimensions
- Tool crafting (30 tools: 6 sets × 5 tools)
- Armor crafting (24 armor pieces: 6 sets × 4 pieces)
- Ore smelting (8 ores)
- Block crafting (planks, bricks, stairs, slabs, etc.)
"""

import json
import os

# Material ingredients for each dimension's tools/armor
MATERIAL_INGREDIENTS = {
    "reverie": "elementaldimensions:reverie_block",
    "lumicore": "elementaldimensions:lumicore_block",
    "mycelite": "elementaldimensions:mycelite_block",
    "glyph": "elementaldimensions:glyph_block",
    "celestite": "elementaldimensions:celestite_block",
    "gloom": "elementaldimensions:gloom_block",
}

# Ore -> Ingot/Material block mappings
ORE_SMELTING = {
    "reverie_ore": "reverie_block",
    "lumicore_ore": "lumicore_block",
    "mycelite_ore": "mycelite_block",
    "glyph_ore": "glyph_block",
    "celestite_ore": "celestite_block",
    "gloom_ore": "gloom_block",
    "blazing_ore": "inferno_stone",  # Smelts to dimension stone
    "celestial_ore": "celestial_stone",
}

# Block crafting recipes
BLOCK_RECIPES = {
    "dreamwood_planks": {
        "type": "shapeless",
        "ingredients": ["elementaldimensions:dreamwood_log"],
        "result": {"item": "elementaldimensions:dreamwood_planks", "count": 4}
    },
    "fungal_planks": {
        "type": "shapeless",
        "ingredients": ["elementaldimensions:fungal_bark"],
        "result": {"item": "elementaldimensions:fungal_planks", "count": 4}
    },
    "somnia_brick": {
        "type": "shaped",
        "pattern": ["DD", "DD"],
        "key": {"D": {"item": "elementaldimensions:dreamstone"}},
        "result": {"item": "elementaldimensions:somnia_brick", "count": 4}
    },
    "runestone_brick": {
        "type": "shaped",
        "pattern": ["RR", "RR"],
        "key": {"R": {"item": "elementaldimensions:runestone"}},
        "result": {"item": "elementaldimensions:runestone_brick", "count": 4}
    },
    "mycelite_brick": {
        "type": "shaped",
        "pattern": ["MM", "MM"],
        "key": {"M": {"item": "elementaldimensions:mycelite_stone"}},
        "result": {"item": "elementaldimensions:mycelite_brick", "count": 4}
    },
    "bone_brick": {
        "type": "shaped",
        "pattern": ["BB", "BB"],
        "key": {"B": {"item": "minecraft:bone_block"}},
        "result": {"item": "elementaldimensions:bone_brick", "count": 4}
    },
    "withered_brick": {
        "type": "shaped",
        "pattern": ["WW", "WW"],
        "key": {"W": {"item": "elementaldimensions:withered_basalt"}},
        "result": {"item": "elementaldimensions:withered_brick", "count": 4}
    },
    "astral_brick": {
        "type": "shaped",
        "pattern": ["SS", "SS"],
        "key": {"S": {"item": "elementaldimensions:cosmic_stone"}},
        "result": {"item": "elementaldimensions:astral_brick", "count": 4}
    },
    "skybound_stone_brick": {
        "type": "shaped",
        "pattern": ["SS", "SS"],
        "key": {"S": {"item": "elementaldimensions:starlit_stone"}},
        "result": {"item": "elementaldimensions:skybound_stone_brick", "count": 4}
    },
}

# Stairs/slabs recipes
STAIR_SLAB_RECIPES = {
    "dreamstone_stairs": {
        "type": "shaped",
        "pattern": ["D  ", "DD ", "DDD"],
        "key": {"D": {"item": "elementaldimensions:dreamstone"}},
        "result": {"item": "elementaldimensions:dreamstone_stairs", "count": 4}
    },
    "dreamstone_slab": {
        "type": "shaped",
        "pattern": ["DDD"],
        "key": {"D": {"item": "elementaldimensions:dreamstone"}},
        "result": {"item": "elementaldimensions:dreamstone_slab", "count": 6}
    },
}

def create_tool_recipe(material_name, tool_type, ingredient):
    """Create crafting recipe for tools"""
    patterns = {
        "sword": {
            "pattern": [" M ", " M ", " S "],
            "key": {"M": {"item": ingredient}, "S": {"item": "minecraft:stick"}},
        },
        "pickaxe": {
            "pattern": ["MMM", " S ", " S "],
            "key": {"M": {"item": ingredient}, "S": {"item": "minecraft:stick"}},
        },
        "axe": {
            "pattern": ["MM ", "MS ", " S "],
            "key": {"M": {"item": ingredient}, "S": {"item": "minecraft:stick"}},
        },
        "shovel": {
            "pattern": [" M ", " S ", " S "],
            "key": {"M": {"item": ingredient}, "S": {"item": "minecraft:stick"}},
        },
        "hoe": {
            "pattern": ["MM ", " S ", " S "],
            "key": {"M": {"item": ingredient}, "S": {"item": "minecraft:stick"}},
        },
    }

    recipe = {
        "type": "minecraft:crafting_shaped",
        **patterns[tool_type],
        "result": {"item": f"elementaldimensions:{material_name}_{tool_type}", "count": 1}
    }
    return recipe

def create_armor_recipe(armor_set, piece_type, ingredient):
    """Create crafting recipe for armor"""
    patterns = {
        "helmet": {
            "pattern": ["MMM", "M M"],
            "key": {"M": {"item": ingredient}},
        },
        "chestplate": {
            "pattern": ["M M", "MMM", "MMM"],
            "key": {"M": {"item": ingredient}},
        },
        "leggings": {
            "pattern": ["MMM", "M M", "M M"],
            "key": {"M": {"item": ingredient}},
        },
        "boots": {
            "pattern": ["M M", "M M"],
            "key": {"M": {"item": ingredient}},
        },
    }

    recipe = {
        "type": "minecraft:crafting_shaped",
        **patterns[piece_type],
        "result": {"item": f"elementaldimensions:{armor_set}_{piece_type}", "count": 1}
    }
    return recipe

def create_smelting_recipe(ore_name, result_item):
    """Create smelting recipe for ores"""
    return {
        "type": "minecraft:smelting",
        "ingredient": {"item": f"elementaldimensions:{ore_name}"},
        "result": {"item": f"elementaldimensions:{result_item}", "count": 1},
        "experience": 0.7,
        "cookingtime": 200
    }

def create_blasting_recipe(ore_name, result_item):
    """Create blasting recipe for ores (faster)"""
    return {
        "type": "minecraft:blasting",
        "ingredient": {"item": f"elementaldimensions:{ore_name}"},
        "result": {"item": f"elementaldimensions:{result_item}", "count": 1},
        "experience": 0.7,
        "cookingtime": 100
    }

def generate_all_recipes():
    """Generate all crafting, smelting, and block recipes"""

    recipe_dir = "1.21.1/src/main/resources/data/elementaldimensions/recipe"
    os.makedirs(recipe_dir, exist_ok=True)

    total_recipes = 0

    # 1. TOOL RECIPES (30 total: 6 materials × 5 tools)
    print("🔨 Generating tool recipes...")
    tool_types = ["sword", "pickaxe", "axe", "shovel", "hoe"]
    for material, ingredient in MATERIAL_INGREDIENTS.items():
        for tool in tool_types:
            recipe = create_tool_recipe(material, tool, ingredient)
            filename = f"{material}_{tool}.json"
            with open(os.path.join(recipe_dir, filename), 'w') as f:
                json.dump(recipe, f, indent=2)
            total_recipes += 1
    print(f"   ✅ Created {len(MATERIAL_INGREDIENTS) * len(tool_types)} tool recipes")

    # 2. ARMOR RECIPES (24 total: 6 sets × 4 pieces)
    print("🛡️  Generating armor recipes...")
    armor_mapping = {
        "reverie": "dreambound",
        "lumicore": "skyborn",
        "mycelite": "sporebound",
        "glyph": "runic",
        "celestite": "starforged",
        "gloom": "withered",
    }
    armor_pieces = ["helmet", "chestplate", "leggings", "boots"]
    for material, armor_set in armor_mapping.items():
        ingredient = MATERIAL_INGREDIENTS[material]
        for piece in armor_pieces:
            recipe = create_armor_recipe(armor_set, piece, ingredient)
            filename = f"{armor_set}_{piece}.json"
            with open(os.path.join(recipe_dir, filename), 'w') as f:
                json.dump(recipe, f, indent=2)
            total_recipes += 1
    print(f"   ✅ Created {len(armor_mapping) * len(armor_pieces)} armor recipes")

    # 3. ORE SMELTING RECIPES (16 total: 8 ores × 2 methods)
    print("⛏️  Generating ore smelting recipes...")
    for ore, result in ORE_SMELTING.items():
        # Furnace smelting
        recipe = create_smelting_recipe(ore, result)
        filename = f"{ore}_smelting.json"
        with open(os.path.join(recipe_dir, filename), 'w') as f:
            json.dump(recipe, f, indent=2)
        total_recipes += 1

        # Blast furnace (faster)
        recipe = create_blasting_recipe(ore, result)
        filename = f"{ore}_blasting.json"
        with open(os.path.join(recipe_dir, filename), 'w') as f:
            json.dump(recipe, f, indent=2)
        total_recipes += 1
    print(f"   ✅ Created {len(ORE_SMELTING) * 2} ore processing recipes")

    # 4. BLOCK CRAFTING RECIPES
    print("🧱 Generating block crafting recipes...")
    for recipe_name, recipe_data in BLOCK_RECIPES.items():
        filename = f"{recipe_name}.json"
        with open(os.path.join(recipe_dir, filename), 'w') as f:
            json.dump(recipe_data, f, indent=2)
        total_recipes += 1
    print(f"   ✅ Created {len(BLOCK_RECIPES)} block recipes")

    # 5. STAIRS/SLABS RECIPES
    print("🪜 Generating stairs/slabs recipes...")
    for recipe_name, recipe_data in STAIR_SLAB_RECIPES.items():
        filename = f"{recipe_name}.json"
        with open(os.path.join(recipe_dir, filename), 'w') as f:
            json.dump(recipe_data, f, indent=2)
        total_recipes += 1
    print(f"   ✅ Created {len(STAIR_SLAB_RECIPES)} stairs/slabs recipes")

    print()
    print(f"🎉 RECIPE GENERATION COMPLETE!")
    print(f"📊 Total recipes created: {total_recipes}")
    print(f"   - Tool recipes: 30 (6 materials × 5 tools)")
    print(f"   - Armor recipes: 24 (6 sets × 4 pieces)")
    print(f"   - Ore processing: 16 (8 ores × 2 methods)")
    print(f"   - Block crafting: {len(BLOCK_RECIPES)}")
    print(f"   - Stairs/slabs: {len(STAIR_SLAB_RECIPES)}")
    print()
    print("✅ Players can now craft:")
    print("   - Reverie tools & Dreambound armor (Dreaming Depths)")
    print("   - Lumicore tools & Skyborn armor (Celestine Expanse)")
    print("   - Mycelite tools & Sporebound armor (Fungal Dominion)")
    print("   - Glyph tools & Runic armor (Forgotten Archive)")
    print("   - Celestite tools & Starforged armor (Astral Frontier)")
    print("   - Gloom tools & Withered armor (Gloomy Caverns)")
    print("   - Smelt ores into blocks")
    print("   - Craft decorative blocks")

if __name__ == "__main__":
    generate_all_recipes()
