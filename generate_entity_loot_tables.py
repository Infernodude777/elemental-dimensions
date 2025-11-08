"""
Generate entity loot table JSON files
Defines what items entities drop when killed
"""

import json
import os

LOOT_TABLES_DIR = "1.21.1/src/main/resources/data/elementaldimensions/loot_table/entities"

# Entity loot configurations
ENTITY_LOOT = {
    # Old Bosses (high-value drops)
    "pyros": [
        ("elementaldimensions:inferno_heart", 1, 1),
        ("elementaldimensions:fire_essence", 5, 10),
        ("minecraft:blaze_rod", 10, 20),
    ],
    "leviathan": [
        ("elementaldimensions:aquatic_heart", 1, 1),
        ("elementaldimensions:water_essence", 5, 10),
        ("minecraft:prismarine_shard", 10, 20),
    ],
    "goliath": [
        ("elementaldimensions:terran_heart", 1, 1),
        ("elementaldimensions:earth_essence", 5, 10),
        ("minecraft:diamond", 5, 10),
    ],
    "zephyros": [
        ("elementaldimensions:skybound_heart", 1, 1),
        ("elementaldimensions:air_essence", 5, 10),
        ("minecraft:feather", 20, 40),
    ],
    "primarch": [
        ("elementaldimensions:inferno_heart", 1, 1),
        ("elementaldimensions:aquatic_heart", 1, 1),
        ("elementaldimensions:terran_heart", 1, 1),
        ("elementaldimensions:skybound_heart", 1, 1),
        ("minecraft:nether_star", 1, 1),
    ],

    # Dreaming Depths
    "phantasm": [
        ("elementaldimensions:phantasm_dust", 1, 3),
        ("elementaldimensions:echo_crystal", 0, 1, 0.3),  # 30% chance
    ],
    "luller": [
        ("elementaldimensions:dreamwood_planks", 1, 2),
        ("minecraft:string", 0, 2),
    ],
    "nightmare_shade": [
        ("elementaldimensions:phantasm_dust", 2, 4),
        ("minecraft:ender_pearl", 0, 1, 0.5),
    ],
    "somnian_butterfly": [
        ("elementaldimensions:mirage_leaves", 0, 2),
    ],
    "the_dreamer": [  # Boss
        ("elementaldimensions:reverie_block", 3, 5),
        ("elementaldimensions:echo_crystal", 5, 10),
        ("elementaldimensions:phantasm_dust", 10, 20),
    ],

    # Celestine Expanse
    "skywhale": [
        ("elementaldimensions:radiant_crystal", 1, 3),
        ("minecraft:phantom_membrane", 1, 2),
    ],
    "lumifin": [
        ("elementaldimensions:sky_reef", 0, 1),
    ],
    "storm_hawk": [
        ("minecraft:feather", 2, 4),
        ("elementaldimensions:radiant_crystal", 0, 1, 0.4),
    ],
    "sky_pirate": [
        ("elementaldimensions:lumicore_block", 0, 2),
        ("minecraft:gold_ingot", 1, 5),
    ],
    "the_radiant_monarch": [  # Boss
        ("elementaldimensions:lumicore_block", 3, 5),
        ("elementaldimensions:radiant_crystal", 5, 10),
        ("minecraft:elytra", 1, 1, 0.5),  # 50% chance elytra
    ],

    # Fungal Dominion
    "spore_bat": [
        ("elementaldimensions:spore_block", 0, 2),
    ],
    "mycelium_walker": [
        ("elementaldimensions:root_flesh", 1, 3),
        ("elementaldimensions:biolume_cap", 0, 1),
    ],
    "mycelite_creeper": [
        ("minecraft:gunpowder", 1, 2),
        ("elementaldimensions:spore_crystal", 0, 1, 0.5),
    ],
    "fungal_zombie": [
        ("minecraft:rotten_flesh", 0, 2),
        ("elementaldimensions:fungal_tendril", 0, 1),
    ],
    "the_overmind": [  # Boss
        ("elementaldimensions:mycelite_block", 3, 5),
        ("elementaldimensions:biolume_cluster", 5, 10),
        ("elementaldimensions:spore_crystal", 10, 20),
    ],

    # Forgotten Archive
    "scribe_spirit": [
        ("minecraft:paper", 1, 3),
        ("elementaldimensions:archived_scroll", 0, 1, 0.3),
    ],
    "living_tome": [
        ("minecraft:book", 1, 1),
        ("elementaldimensions:glyph_block", 0, 1, 0.2),
    ],
    "sentinel": [
        ("elementaldimensions:runestone", 1, 3),
        ("elementaldimensions:glyph_glass", 0, 2),
    ],
    "page_horror": [
        ("minecraft:paper", 3, 5),
        ("elementaldimensions:ancient_tablet", 0, 1, 0.4),
    ],
    "the_librarian": [  # Boss
        ("elementaldimensions:glyph_block", 3, 5),
        ("elementaldimensions:knowledge_crystal", 5, 10),
        ("minecraft:enchanted_book", 1, 3),
    ],

    # Astral Frontier
    "star_elemental": [
        ("elementaldimensions:stardust_stone", 1, 2),
        ("elementaldimensions:star_fragment_block", 0, 1, 0.3),
    ],
    "nebula_wisp": [
        ("elementaldimensions:nebular_dust", 0, 2),
    ],
    "voidling": [
        ("elementaldimensions:void_stone", 1, 3),
        ("elementaldimensions:void_crystal", 0, 1, 0.4),
    ],
    "cosmic_horror": [
        ("elementaldimensions:celestite_block", 0, 2),
        ("elementaldimensions:nebular_glass", 1, 2),
    ],
    "the_void_archon": [  # Boss
        ("elementaldimensions:celestite_block", 3, 5),
        ("elementaldimensions:void_crystal", 5, 10),
        ("elementaldimensions:star_fragment_block", 10, 20),
    ],

    # Gloomy Caverns
    "echo_wisp": [
        ("elementaldimensions:drip_moss", 0, 1),
    ],
    "cave_snail": [
        ("minecraft:slime_ball", 0, 1),
        ("elementaldimensions:drip_stone", 0, 1),
    ],
    "gloom_stalker": [
        ("elementaldimensions:shadestone", 1, 3),
        ("elementaldimensions:gloom_crystal", 0, 1, 0.4),
    ],
    "withered_skeleton": [
        ("minecraft:bone", 1, 3),
        ("elementaldimensions:withered_basalt", 0, 2),
    ],
    "the_echo_king": [  # Boss
        ("elementaldimensions:gloom_block", 3, 5),
        ("elementaldimensions:gloom_crystal", 5, 10),
        ("elementaldimensions:echo_stone", 10, 20),
    ],
}

def create_loot_table(entity_name, drops):
    """Create loot table JSON for entity"""
    pools = []

    for drop_data in drops:
        if len(drop_data) == 3:
            item, min_count, max_count = drop_data
            chance = 1.0
        else:
            item, min_count, max_count, chance = drop_data

        # Create pool entry
        pool = {
            "rolls": 1,
            "entries": [
                {
                    "type": "minecraft:item",
                    "name": item,
                    "functions": [
                        {
                            "function": "minecraft:set_count",
                            "count": {
                                "type": "minecraft:uniform",
                                "min": min_count,
                                "max": max_count
                            }
                        },
                        {
                            "function": "minecraft:looting_enchant",
                            "count": {
                                "type": "minecraft:uniform",
                                "min": 0,
                                "max": 1
                            }
                        }
                    ]
                }
            ]
        }

        # Add chance condition if not 100%
        if chance < 1.0:
            pool["conditions"] = [
                {
                    "condition": "minecraft:random_chance",
                    "chance": chance
                }
            ]

        pools.append(pool)

    return {
        "type": "minecraft:entity",
        "pools": pools
    }

def generate_entity_loot_tables():
    """Generate all entity loot table JSON files"""
    os.makedirs(LOOT_TABLES_DIR, exist_ok=True)

    for entity_name, drops in ENTITY_LOOT.items():
        loot_table = create_loot_table(entity_name, drops)

        filename = f"{entity_name}.json"
        with open(os.path.join(LOOT_TABLES_DIR, filename), 'w') as f:
            json.dump(loot_table, f, indent=2)

    print(f"✅ Generated {len(ENTITY_LOOT)} entity loot table files")
    print(f"📁 Location: {LOOT_TABLES_DIR}")
    print()
    print("✅ Entities now drop items:")
    print("   - Bosses drop hearts, essences, and rare items")
    print("   - Regular mobs drop dimension-specific materials")
    print("   - Looting enchantment increases drops")
    print("   - Some items have drop chances (RNG)")
    print()
    print("📊 Loot table breakdown:")
    boss_count = sum(1 for drops in ENTITY_LOOT.values() if any('heart' in str(item).lower() for item, *_ in drops) or len(drops) >= 3)
    print(f"   - Boss loot tables: {boss_count}")
    print(f"   - Regular mob loot tables: {len(ENTITY_LOOT) - boss_count}")
    print()
    print("💎 Notable drops:")
    print("   - Primarch: All 4 hearts + Nether Star")
    print("   - The Radiant Monarch: 50% chance for Elytra")
    print("   - The Librarian: Enchanted books")

if __name__ == "__main__":
    generate_entity_loot_tables()
