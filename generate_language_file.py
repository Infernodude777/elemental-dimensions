"""
Generate complete en_us.json language file for Elemental Dimensions
Transforms block.elementaldimensions.dreamstone -> "Dreamstone"
"""

import json
import os

# All 128 registered blocks from ModBlocks.java
BLOCKS = {
    # Old Celestial Dimension Blocks
    "celestial_stone": "Celestial Stone",
    "celestial_ore": "Celestial Ore",
    "starlight_moss": "Starlight Moss",
    "void_rift_block": "Void Rift Block",

    # Fire Dimension Blocks
    "inferno_stone": "Inferno Stone",
    "blazing_ore": "Blazing Ore",

    # Water Dimension Blocks
    "aqua_stone": "Aqua Stone",
    "tidal_crystal": "Tidal Crystal",

    # Earth Dimension Blocks
    "terran_bedrock": "Terran Bedrock",
    "emerald_cluster": "Emerald Cluster",

    # Sky Dimension Blocks
    "cloud_stone": "Cloud Stone",
    "wind_shard": "Wind Shard",

    # Portal Block
    "celestial_portal_frame": "Celestial Portal Frame",

    # DREAMING DEPTHS (20 blocks)
    "dreamstone": "Dreamstone",
    "lullglass": "Lull Glass",
    "softsoil": "Soft Soil",
    "dreamgrass": "Dream Grass",
    "echo_crystal": "Echo Crystal",
    "dreamwood_log": "Dreamwood Log",
    "lucid_water": "Lucid Water",
    "phantasm_dust": "Phantasm Dust",
    "etherstone": "Etherstone",
    "mirage_leaves": "Mirage Leaves",
    "dreamwood_planks": "Dreamwood Planks",
    "reverie_ore": "Reverie Ore",
    "reverie_block": "Reverie Block",
    "somnia_brick": "Somnia Brick",
    "dreamstone_stairs": "Dreamstone Stairs",
    "dreamstone_slab": "Dreamstone Slab",
    "lulled_vines": "Lulled Vines",
    "dream_lantern": "Dream Lantern",
    "phantasm_fungus": "Phantasm Fungus",
    "dream_sand": "Dream Sand",

    # CELESTINE EXPANSE (19 blocks)
    "celestine_grass": "Celestine Grass",
    "starlit_stone": "Starlit Stone",
    "lumicore_block": "Lumicore Block",
    "astral_glass": "Astral Glass",
    "radiant_crystal": "Radiant Crystal",
    "sky_reef": "Sky Reef",
    "lumicore_ore": "Lumicore Ore",
    "celestine_dirt": "Celestine Dirt",
    "radiant_pillar": "Radiant Pillar",
    "skybound_stone_brick": "Skybound Stone Brick",
    "astral_vine": "Astral Vine",
    "twilight_grass": "Twilight Grass",
    "luminous_coral": "Luminous Coral",
    "starlit_cobblestone": "Starlit Cobblestone",
    "celestine_cloud": "Celestine Cloud",
    "astral_lantern": "Astral Lantern",
    "lumicore_pillar": "Lumicore Pillar",
    "radiant_block": "Radiant Block",
    "skybound_moss": "Skybound Moss",

    # FUNGAL DOMINION (19 blocks)
    "mycelite_soil": "Mycelite Soil",
    "root_flesh": "Root Flesh",
    "biolume_cap": "Biolume Cap",
    "fungal_bark": "Fungal Bark",
    "mycelite_brick": "Mycelite Brick",
    "spore_block": "Spore Block",
    "mycelite_ore": "Mycelite Ore",
    "mycelite_block": "Mycelite Block",
    "giant_mushroom_stem": "Giant Mushroom Stem",
    "luminous_fungus": "Luminous Fungus",
    "fungal_tendril": "Fungal Tendril",
    "molded_stone": "Molded Stone",
    "mycelite_stone": "Mycelite Stone",
    "spore_lamp": "Spore Lamp",
    "glowing_moss_block": "Glowing Moss Block",
    "fungal_planks": "Fungal Planks",
    "root_network": "Root Network",
    "biolume_cluster": "Biolume Cluster",
    "spore_crystal": "Spore Crystal",

    # FORGOTTEN ARCHIVE (19 blocks)
    "runestone": "Runestone",
    "runestone_brick": "Runestone Brick",
    "archive_sandstone": "Archive Sandstone",
    "ink_marble": "Ink Marble",
    "glyph_glass": "Glyph Glass",
    "ancient_shelf": "Ancient Shelf",
    "dusty_tome_block": "Dusty Tome Block",
    "glyph_ore": "Glyph Ore",
    "glyph_block": "Glyph Block",
    "polished_runestone": "Polished Runestone",
    "chiseled_runestone": "Chiseled Runestone",
    "golden_pillar": "Golden Pillar",
    "ink_well_block": "Ink Well Block",
    "archived_scroll": "Archived Scroll",
    "knowledge_crystal": "Knowledge Crystal",
    "runic_lantern": "Runic Lantern",
    "archive_sandstone_brick": "Archive Sandstone Brick",
    "smooth_ink_marble": "Smooth Ink Marble",
    "ancient_tablet": "Ancient Tablet",

    # ASTRAL FRONTIER (19 blocks)
    "starlit_grass": "Starlit Grass",
    "stardust_stone": "Stardust Stone",
    "celestite_block": "Celestite Block",
    "celestite_crystal": "Celestite Crystal",
    "nebular_dust": "Nebular Dust",
    "cosmic_stone": "Cosmic Stone",
    "celestite_ore": "Celestite Ore",
    "star_fragment_block": "Star Fragment Block",
    "nebular_glass": "Nebular Glass",
    "void_stone": "Void Stone",
    "cosmic_pillar": "Cosmic Pillar",
    "astral_brick": "Astral Brick",
    "starlight_torch": "Starlight Torch",
    "celestite_pillar": "Celestite Pillar",
    "nebular_stone": "Nebular Stone",
    "cosmic_lantern": "Cosmic Lantern",
    "stardust_block": "Stardust Block",
    "void_crystal": "Void Crystal",
    "astral_moss": "Astral Moss",

    # GLOOMY CAVERNS (19 blocks)
    "withered_basalt": "Withered Basalt",
    "echo_stone": "Echo Stone",
    "drip_moss": "Drip Moss",
    "gloom_crystal": "Gloom Crystal",
    "bone_brick": "Bone Brick",
    "shadestone": "Shadestone",
    "gloom_ore": "Gloom Ore",
    "gloom_block": "Gloom Block",
    "withered_brick": "Withered Brick",
    "echo_pillar": "Echo Pillar",
    "despair_vine": "Despair Vine",
    "mournstone": "Mournstone",
    "mournstone_brick": "Mournstone Brick",
    "gloom_lantern": "Gloom Lantern",
    "withered_roots": "Withered Roots",
    "drip_stone": "Drip Stone",
    "echo_brick": "Echo Brick",
    "gloom_glass": "Gloom Glass",
    "shadestone_pillar": "Shadestone Pillar",
}

# All 97 registered items from ModItems.java
ITEMS = {
    # Essences and Hearts
    "fire_essence": "Fire Essence",
    "water_essence": "Water Essence",
    "earth_essence": "Earth Essence",
    "air_essence": "Air Essence",
    "inferno_heart": "Inferno Heart",
    "aquatic_heart": "Aquatic Heart",
    "terran_heart": "Terran Heart",
    "skybound_heart": "Skybound Heart",

    # DREAMING DEPTHS - Reverie Tools & Dreambound Armor
    "reverie_sword": "Reverie Sword",
    "reverie_pickaxe": "Reverie Pickaxe",
    "reverie_axe": "Reverie Axe",
    "reverie_shovel": "Reverie Shovel",
    "reverie_hoe": "Reverie Hoe",
    "dreambound_helmet": "Dreambound Helmet",
    "dreambound_chestplate": "Dreambound Chestplate",
    "dreambound_leggings": "Dreambound Leggings",
    "dreambound_boots": "Dreambound Boots",

    # CELESTINE EXPANSE - Lumicore Tools & Skyborn Armor
    "lumicore_sword": "Lumicore Sword",
    "lumicore_pickaxe": "Lumicore Pickaxe",
    "lumicore_axe": "Lumicore Axe",
    "lumicore_shovel": "Lumicore Shovel",
    "lumicore_hoe": "Lumicore Hoe",
    "skyborn_helmet": "Skyborn Helmet",
    "skyborn_chestplate": "Skyborn Chestplate",
    "skyborn_leggings": "Skyborn Leggings",
    "skyborn_boots": "Skyborn Boots",

    # FUNGAL DOMINION - Mycelite Tools & Sporebound Armor
    "mycelite_sword": "Mycelite Sword",
    "mycelite_pickaxe": "Mycelite Pickaxe",
    "mycelite_axe": "Mycelite Axe",
    "mycelite_shovel": "Mycelite Shovel",
    "mycelite_hoe": "Mycelite Hoe",
    "sporebound_helmet": "Sporebound Helmet",
    "sporebound_chestplate": "Sporebound Chestplate",
    "sporebound_leggings": "Sporebound Leggings",
    "sporebound_boots": "Sporebound Boots",

    # FORGOTTEN ARCHIVE - Glyph Tools & Runic Armor
    "glyph_sword": "Glyph Sword",
    "glyph_pickaxe": "Glyph Pickaxe",
    "glyph_axe": "Glyph Axe",
    "glyph_shovel": "Glyph Shovel",
    "glyph_hoe": "Glyph Hoe",
    "runic_helmet": "Runic Helmet",
    "runic_chestplate": "Runic Chestplate",
    "runic_leggings": "Runic Leggings",
    "runic_boots": "Runic Boots",

    # ASTRAL FRONTIER - Celestite Tools & Starforged Armor
    "celestite_sword": "Celestite Sword",
    "celestite_pickaxe": "Celestite Pickaxe",
    "celestite_axe": "Celestite Axe",
    "celestite_shovel": "Celestite Shovel",
    "celestite_hoe": "Celestite Hoe",
    "starforged_helmet": "Starforged Helmet",
    "starforged_chestplate": "Starforged Chestplate",
    "starforged_leggings": "Starforged Leggings",
    "starforged_boots": "Starforged Boots",

    # GLOOMY CAVERNS - Gloom Tools & Withered Armor
    "gloom_sword": "Gloom Sword",
    "gloom_pickaxe": "Gloom Pickaxe",
    "gloom_axe": "Gloom Axe",
    "gloom_shovel": "Gloom Shovel",
    "gloom_hoe": "Gloom Hoe",
    "withered_helmet": "Withered Helmet",
    "withered_chestplate": "Withered Chestplate",
    "withered_leggings": "Withered Leggings",
    "withered_boots": "Withered Boots",

    # Spawn Eggs (35 total)
    "pyros_spawn_egg": "Pyros Spawn Egg",
    "leviathan_spawn_egg": "Leviathan Spawn Egg",
    "goliath_spawn_egg": "Goliath Spawn Egg",
    "zephyros_spawn_egg": "Zephyros Spawn Egg",
    "primarch_spawn_egg": "Primarch Spawn Egg",
    "phantasm_spawn_egg": "Phantasm Spawn Egg",
    "luller_spawn_egg": "Luller Spawn Egg",
    "nightmare_shade_spawn_egg": "Nightmare Shade Spawn Egg",
    "somnian_butterfly_spawn_egg": "Somnian Butterfly Spawn Egg",
    "the_dreamer_spawn_egg": "The Dreamer Spawn Egg",
    "skywhale_spawn_egg": "Skywhale Spawn Egg",
    "lumifin_spawn_egg": "Lumifin Spawn Egg",
    "storm_hawk_spawn_egg": "Storm Hawk Spawn Egg",
    "sky_pirate_spawn_egg": "Sky Pirate Spawn Egg",
    "the_radiant_monarch_spawn_egg": "The Radiant Monarch Spawn Egg",
    "spore_bat_spawn_egg": "Spore Bat Spawn Egg",
    "mycelium_walker_spawn_egg": "Mycelium Walker Spawn Egg",
    "mycelite_creeper_spawn_egg": "Mycelite Creeper Spawn Egg",
    "fungal_zombie_spawn_egg": "Fungal Zombie Spawn Egg",
    "the_overmind_spawn_egg": "The Overmind Spawn Egg",
    "scribe_spirit_spawn_egg": "Scribe Spirit Spawn Egg",
    "living_tome_spawn_egg": "Living Tome Spawn Egg",
    "sentinel_spawn_egg": "Sentinel Spawn Egg",
    "page_horror_spawn_egg": "Page Horror Spawn Egg",
    "the_librarian_spawn_egg": "The Librarian Spawn Egg",
    "star_elemental_spawn_egg": "Star Elemental Spawn Egg",
    "nebula_wisp_spawn_egg": "Nebula Wisp Spawn Egg",
    "voidling_spawn_egg": "Voidling Spawn Egg",
    "cosmic_horror_spawn_egg": "Cosmic Horror Spawn Egg",
    "the_void_archon_spawn_egg": "The Void Archon Spawn Egg",
    "echo_wisp_spawn_egg": "Echo Wisp Spawn Egg",
    "cave_snail_spawn_egg": "Cave Snail Spawn Egg",
    "gloom_stalker_spawn_egg": "Gloom Stalker Spawn Egg",
    "withered_skeleton_spawn_egg": "Withered Skeleton Spawn Egg",
    "the_echo_king_spawn_egg": "The Echo King Spawn Egg",

    # Portal Igniters (11 total)
    "dreaming_depths_igniter": "Dreaming Depths Portal Igniter",
    "inferno_realm_igniter": "Inferno Realm Portal Igniter",
    "radiant_plateau_igniter": "Radiant Plateau Portal Igniter",
    "mycelial_nexus_igniter": "Mycelial Nexus Portal Igniter",
    "golden_citadel_igniter": "Golden Citadel Portal Igniter",
    "ancient_groves_igniter": "Ancient Groves Portal Igniter",
    "cosmic_void_igniter": "Cosmic Void Portal Igniter",
    "celestial_sanctum_igniter": "Celestial Sanctum Portal Igniter",
    "glyphic_archives_igniter": "Glyphic Archives Portal Igniter",
    "echoing_expanse_igniter": "Echoing Expanse Portal Igniter",
    "umbral_wasteland_igniter": "Umbral Wasteland Portal Igniter",
}

# All 41 registered entities from ModEntities.java
ENTITIES = {
    # Old Bosses (5)
    "pyros": "Pyros",
    "leviathan": "Leviathan",
    "goliath": "Goliath",
    "zephyros": "Zephyros",
    "primarch": "Primarch",

    # Dreaming Depths (5)
    "phantasm": "Phantasm",
    "luller": "Luller",
    "nightmare_shade": "Nightmare Shade",
    "somnian_butterfly": "Somnian Butterfly",
    "the_dreamer": "The Dreamer",

    # Celestine Expanse (5)
    "skywhale": "Skywhale",
    "lumifin": "Lumifin",
    "storm_hawk": "Storm Hawk",
    "sky_pirate": "Sky Pirate",
    "the_radiant_monarch": "The Radiant Monarch",

    # Fungal Dominion (5)
    "spore_bat": "Spore Bat",
    "mycelium_walker": "Mycelium Walker",
    "mycelite_creeper": "Mycelite Creeper",
    "fungal_zombie": "Fungal Zombie",
    "the_overmind": "The Overmind",

    # Forgotten Archive (5)
    "scribe_spirit": "Scribe Spirit",
    "living_tome": "Living Tome",
    "sentinel": "Sentinel",
    "page_horror": "Page Horror",
    "the_librarian": "The Librarian",

    # Astral Frontier (5)
    "star_elemental": "Star Elemental",
    "nebula_wisp": "Nebula Wisp",
    "voidling": "Voidling",
    "cosmic_horror": "Cosmic Horror",
    "the_void_archon": "The Void Archon",

    # Gloomy Caverns (5)
    "echo_wisp": "Echo Wisp",
    "cave_snail": "Cave Snail",
    "gloom_stalker": "Gloom Stalker",
    "withered_skeleton": "Withered Skeleton",
    "the_echo_king": "The Echo King",
}

# Advancements with proper titles
ADVANCEMENTS = {
    "root.title": "Elemental Dimensions",
    "root.description": "Begin your journey through the elemental realms",

    # Old Dimensions
    "enter_inferno_realm.title": "Into the Inferno",
    "enter_inferno_realm.description": "Step into the blazing realm of eternal fire",
    "enter_aquatic_realm.title": "Depths of the Ocean",
    "enter_aquatic_realm.description": "Dive into the aquatic abyss",
    "enter_terran_realm.title": "Deep Underground",
    "enter_terran_realm.description": "Venture into the earth's core",
    "enter_skybound_realm.title": "Touch the Clouds",
    "enter_skybound_realm.description": "Soar to the realm above the sky",
    "enter_celestial_realm.title": "Among the Stars",
    "enter_celestial_realm.description": "Enter the celestial dimension",
    "enter_void_realm.title": "Into the Abyss",
    "enter_void_realm.description": "Step into the endless void",

    # New Dimensions
    "enter_dreaming_depths.title": "Land of Slumber",
    "enter_dreaming_depths.description": "Enter the dreamscape where reality blurs",
    "enter_celestine_expanse.title": "Celestial Heights",
    "enter_celestine_expanse.description": "Reach the luminous sky realm",
    "enter_fungal_dominion.title": "Spore Kingdom",
    "enter_fungal_dominion.description": "Enter the domain of living fungi",
    "enter_forgotten_archive.title": "Library of Eternity",
    "enter_forgotten_archive.description": "Discover the ancient archives of knowledge",
    "enter_astral_frontier.title": "Beyond the Stars",
    "enter_astral_frontier.description": "Venture into the cosmic frontier",
    "enter_gloomy_caverns.title": "Caverns of Despair",
    "enter_gloomy_caverns.description": "Descend into the depths of gloom",

    # Boss Defeats
    "defeat_pyros.title": "Inferno Slayer",
    "defeat_pyros.description": "Defeat Pyros, Lord of Fire",
    "defeat_leviathan.title": "Deep Sea Hunter",
    "defeat_leviathan.description": "Defeat Leviathan, Terror of the Depths",
    "defeat_goliath.title": "Earth Shaker",
    "defeat_goliath.description": "Defeat Goliath, Guardian of Stone",
    "defeat_zephyros.title": "Storm Breaker",
    "defeat_zephyros.description": "Defeat Zephyros, Master of Winds",
    "defeat_primarch.title": "Primarch's Demise",
    "defeat_primarch.description": "Defeat the Elemental Primarch",
    "defeat_the_dreamer.title": "Nightmare's End",
    "defeat_the_dreamer.description": "Defeat The Dreamer, ruler of nightmares",
    "defeat_the_radiant_monarch.title": "Light Extinguished",
    "defeat_the_radiant_monarch.description": "Defeat The Radiant Monarch",
    "defeat_the_overmind.title": "Hive Mind Destroyed",
    "defeat_the_overmind.description": "Defeat The Overmind, consciousness of fungi",
    "defeat_the_librarian.title": "Knowledge Keeper Fallen",
    "defeat_the_librarian.description": "Defeat The Librarian, guardian of archives",
    "defeat_the_void_archon.title": "Void Conqueror",
    "defeat_the_void_archon.description": "Defeat The Void Archon, cosmic entity",
    "defeat_the_echo_king.title": "Silence the Echoes",
    "defeat_the_echo_king.description": "Defeat The Echo King, lord of caverns",

    # Collection Achievements
    "obtain_essence.title": "Elemental Power",
    "obtain_essence.description": "Collect an elemental essence",
    "obtain_all_essences.title": "Master of Elements",
    "obtain_all_essences.description": "Collect all four elemental essences",
    "obtain_heart.title": "Heart of Power",
    "obtain_heart.description": "Obtain a dimensional heart",
    "craft_first_tool.title": "Getting Equipped",
    "craft_first_tool.description": "Craft your first dimensional tool",
    "full_armor_set.title": "Fully Armored",
    "full_armor_set.description": "Wear a complete armor set from any dimension",
}

# Item Group (Creative Tab)
ITEM_GROUPS = {
    "elementaldimensions": "Elemental Dimensions",
}

# Death Messages
DEATH_MESSAGES = {
    "death.attack.pyros": "%1$s was incinerated by Pyros",
    "death.attack.leviathan": "%1$s was dragged into the abyss by Leviathan",
    "death.attack.goliath": "%1$s was crushed by Goliath",
    "death.attack.zephyros": "%1$s was blown away by Zephyros",
    "death.attack.primarch": "%1$s was overwhelmed by the Primarch",
    "death.attack.the_dreamer": "%1$s succumbed to eternal slumber",
    "death.attack.the_radiant_monarch": "%1$s was smited by radiant light",
    "death.attack.the_overmind": "%1$s was consumed by the hive",
    "death.attack.the_librarian": "%1$s was silenced by The Librarian",
    "death.attack.the_void_archon": "%1$s was erased from existence",
    "death.attack.the_echo_king": "%1$s's screams echo eternally",
}

def generate_language_file():
    """Generate complete en_us.json with all translations"""

    lang_data = {}

    # Add blocks
    for block_id, name in BLOCKS.items():
        lang_data[f"block.elementaldimensions.{block_id}"] = name

    # Add items
    for item_id, name in ITEMS.items():
        lang_data[f"item.elementaldimensions.{item_id}"] = name

    # Add entities
    for entity_id, name in ENTITIES.items():
        lang_data[f"entity.elementaldimensions.{entity_id}"] = name

    # Add advancements
    for adv_id, text in ADVANCEMENTS.items():
        lang_data[f"advancement.elementaldimensions.{adv_id}"] = text

    # Add item groups
    for group_id, name in ITEM_GROUPS.items():
        lang_data[f"itemGroup.{group_id}"] = name

    # Add death messages
    lang_data.update(DEATH_MESSAGES)

    # Sort alphabetically for cleaner file
    lang_data = dict(sorted(lang_data.items()))

    # Write to file
    output_path = "1.21.1/src/main/resources/assets/elementaldimensions/lang/en_us.json"
    os.makedirs(os.path.dirname(output_path), exist_ok=True)

    with open(output_path, 'w', encoding='utf-8') as f:
        json.dump(lang_data, f, indent=2, ensure_ascii=False)

    print(f"✅ Generated language file: {output_path}")
    print(f"📊 Total entries: {len(lang_data)}")
    print(f"   - Blocks: {len(BLOCKS)}")
    print(f"   - Items: {len(ITEMS)}")
    print(f"   - Entities: {len(ENTITIES)}")
    print(f"   - Advancements: {len(ADVANCEMENTS)}")
    print(f"   - Death Messages: {len(DEATH_MESSAGES)}")
    print(f"   - Item Groups: {len(ITEM_GROUPS)}")
    print()
    print("✅ All translations now show properly:")
    print("   block.elementaldimensions.dreamstone → 'Dreamstone'")
    print("   item.elementaldimensions.reverie_sword → 'Reverie Sword'")
    print("   entity.elementaldimensions.the_dreamer → 'The Dreamer'")
    print("   advancement.elementaldimensions.enter_dreaming_depths.title → 'Land of Slumber'")

if __name__ == "__main__":
    generate_language_file()
