#!/usr/bin/env python3
"""
Update Worldgen Configuration - Elemental Dimensions
Updates noise_settings surface rules to use new blocks
Updates biome spawners to use registered entities
"""

import os
import json

MOD_ID = "elementaldimensions"

# Base paths for 1.21.1 version
BASE_DIR = r"1.21.1\src\main\resources\data\elementaldimensions"
NOISE_SETTINGS_DIR = os.path.join(BASE_DIR, r"worldgen\noise_settings")
BIOME_DIR = os.path.join(BASE_DIR, r"worldgen\biome")
PLACED_FEATURES_DIR = os.path.join(BASE_DIR, r"worldgen\placed_feature")
CONFIGURED_FEATURES_DIR = os.path.join(BASE_DIR, r"worldgen\configured_feature")

# Create directories
for directory in [PLACED_FEATURES_DIR, CONFIGURED_FEATURES_DIR]:
    os.makedirs(directory, exist_ok=True)

# Dimension configurations (11 dimensions)
DIMENSIONS = {
    "inferno_realm": {
        "default_block": "elementaldimensions:inferno_stone",
        "surface_block": "minecraft:magma_block",
        "subsurface_block": "elementaldimensions:inferno_stone",
        "default_fluid": "minecraft:lava",
        "sea_level": 32,
        "biomes": ["firelands_biome"],
        "mobs": []  # No registered mobs for this dimension yet
    },
    "aquatic_realm": {
        "default_block": "elementaldimensions:aqua_stone",
        "surface_block": "elementaldimensions:aqua_stone",
        "subsurface_block": "elementaldimensions:aqua_stone",
        "default_fluid": "minecraft:water",
        "sea_level": 200,
        "biomes": ["aquatica_biome"],
        "mobs": []
    },
    "terran_realm": {
        "default_block": "elementaldimensions:terran_bedrock",
        "surface_block": "minecraft:grass_block",
        "subsurface_block": "minecraft:dirt",
        "default_fluid": "minecraft:water",
        "sea_level": 63,
        "biomes": ["terra_depths_biome"],
        "mobs": []
    },
    "skybound_realm": {
        "default_block": "elementaldimensions:cloud_stone",
        "surface_block": "elementaldimensions:cloud_stone",
        "subsurface_block": "elementaldimensions:cloud_stone",
        "default_fluid": "minecraft:air",
        "sea_level": 0,
        "biomes": ["skyreach_peaks_biome"],
        "mobs": ["skywhale", "lumifin", "storm_hawk", "sky_pirate"]
    },
    "celestial_realm": {
        "default_block": "elementaldimensions:celestial_stone",
        "surface_block": "elementaldimensions:starlight_moss",
        "subsurface_block": "elementaldimensions:celestial_stone",
        "default_fluid": "minecraft:air",
        "sea_level": 64,
        "biomes": ["celestial_biome"],
        "mobs": []
    },
    "void_realm": {
        "default_block": "elementaldimensions:void_rift_block",
        "surface_block": "elementaldimensions:void_rift_block",
        "subsurface_block": "elementaldimensions:void_rift_block",
        "default_fluid": "minecraft:air",
        "sea_level": 0,
        "biomes": ["void_wastes"],
        "mobs": []
    },
    "dreaming_depths": {
        "default_block": "elementaldimensions:dreamstone",
        "surface_block": "elementaldimensions:dreamgrass",
        "subsurface_block": "elementaldimensions:softsoil",
        "default_fluid": "elementaldimensions:lucid_water",
        "sea_level": 63,
        "biomes": ["somnia_fields"],
        "mobs": ["phantasm", "luller", "nightmare_shade", "somnian_butterfly"]
    },
    "forgotten_archive": {
        "default_block": "elementaldimensions:runestone",
        "surface_block": "elementaldimensions:archive_sandstone",
        "subsurface_block": "elementaldimensions:runestone",
        "default_fluid": "minecraft:air",
        "sea_level": 64,
        "biomes": ["golden_stacks"],
        "mobs": ["scribe_spirit", "living_tome", "sentinel", "page_horror"]
    },
    "fungal_dominion": {
        "default_block": "elementaldimensions:mycelite_stone",
        "surface_block": "elementaldimensions:mycelite_soil",
        "subsurface_block": "elementaldimensions:root_flesh",
        "default_fluid": "minecraft:water",
        "sea_level": 63,
        "biomes": ["molded_forest"],
        "mobs": ["spore_bat", "mycelium_walker", "mycelite_creeper", "fungal_zombie"]
    },
    "astral_frontier": {
        "default_block": "elementaldimensions:cosmic_stone",
        "surface_block": "elementaldimensions:starlit_grass",
        "subsurface_block": "elementaldimensions:stardust_stone",
        "default_fluid": "minecraft:air",
        "sea_level": 64,
        "biomes": ["astral_plateau"],
        "mobs": ["star_elemental", "nebula_wisp", "voidling", "cosmic_horror"]
    },
    "gloomy_caverns": {
        "default_block": "elementaldimensions:shadestone",
        "surface_block": "elementaldimensions:echo_stone",
        "subsurface_block": "elementaldimensions:withered_basalt",
        "default_fluid": "minecraft:water",
        "sea_level": 32,
        "biomes": ["mournstone_depths"],
        "mobs": ["echo_wisp", "cave_snail", "gloom_stalker", "withered_skeleton"]
    }
}

# Ore configurations for each dimension
ORE_CONFIGS = {
    "celestial_ore": {
        "dimension": "celestial_realm",
        "min_y": 0,
        "max_y": 128,
        "vein_size": 8,
        "veins_per_chunk": 10
    },
    "blazing_ore": {
        "dimension": "inferno_realm",
        "min_y": 0,
        "max_y": 128,
        "vein_size": 6,
        "veins_per_chunk": 12
    },
    "lumicore_ore": {
        "dimension": "skybound_realm",
        "min_y": 32,
        "max_y": 196,
        "vein_size": 7,
        "veins_per_chunk": 8
    },
    "reverie_ore": {
        "dimension": "dreaming_depths",
        "min_y": 0,
        "max_y": 80,
        "vein_size": 5,
        "veins_per_chunk": 9
    },
    "mycelite_ore": {
        "dimension": "fungal_dominion",
        "min_y": 0,
        "max_y": 96,
        "vein_size": 6,
        "veins_per_chunk": 10
    },
    "glyph_ore": {
        "dimension": "forgotten_archive",
        "min_y": 16,
        "max_y": 112,
        "vein_size": 4,
        "veins_per_chunk": 7
    },
    "celestite_ore": {
        "dimension": "astral_frontier",
        "min_y": 0,
        "max_y": 128,
        "vein_size": 8,
        "veins_per_chunk": 11
    },
    "gloom_ore": {
        "dimension": "gloomy_caverns",
        "min_y": 0,
        "max_y": 64,
        "vein_size": 5,
        "veins_per_chunk": 8
    }
}

print("=" * 80)
print("UPDATING WORLDGEN CONFIGURATION - ELEMENTAL DIMENSIONS")
print("=" * 80)

# === UPDATE NOISE SETTINGS ===
print(f"\n📐 Updating noise settings for {len(DIMENSIONS)} dimensions...")
noise_updated = 0

for dim_name, config in DIMENSIONS.items():
    noise_file = os.path.join(NOISE_SETTINGS_DIR, f"{dim_name}_settings.json")

    if os.path.exists(noise_file):
        with open(noise_file, 'r') as f:
            noise_data = json.load(f)

        # Update default blocks
        noise_data["default_block"] = {"Name": config["default_block"]}
        noise_data["default_fluid"] = {"Name": config["default_fluid"]}
        if config["default_fluid"] != "minecraft:air":
            noise_data["default_fluid"]["Properties"] = {"level": "0"}
        noise_data["sea_level"] = config["sea_level"]

        # Update surface rules to use new blocks
        if "surface_rule" in noise_data:
            # Find biome-specific surface rules
            if "sequence" in noise_data["surface_rule"]:
                for rule in noise_data["surface_rule"]["sequence"]:
                    if "if_true" in rule and "biome_is" in rule["if_true"]:
                        # Update surface blocks
                        if "then_run" in rule and "sequence" in rule["then_run"]:
                            for sub_rule in rule["then_run"]["sequence"]:
                                if "then_run" in sub_rule and "result_state" in sub_rule["then_run"]:
                                    # First rule is usually surface
                                    if rule["then_run"]["sequence"].index(sub_rule) == 0:
                                        sub_rule["then_run"]["result_state"]["Name"] = config["surface_block"]
                                    # Second rule is usually subsurface
                                    elif rule["then_run"]["sequence"].index(sub_rule) == 1:
                                        sub_rule["then_run"]["result_state"]["Name"] = config["subsurface_block"]

        # Save updated noise settings
        with open(noise_file, 'w') as f:
            json.dump(noise_data, f, indent=2)
        noise_updated += 1
    else:
        print(f"  ⚠️  Missing: {noise_file}")

print(f"✅ Updated {noise_updated} noise settings files")

# === UPDATE BIOMES ===
print(f"\n🌍 Updating biomes with registered mob spawns...")
biome_updated = 0

for dim_name, config in DIMENSIONS.items():
    for biome_name in config["biomes"]:
        biome_file = os.path.join(BIOME_DIR, f"{biome_name}.json")

        if os.path.exists(biome_file):
            try:
                with open(biome_file, 'r', encoding='utf-8-sig') as f:
                    content = f.read()
                    if not content.strip():
                        print(f"  ⚠️  Empty file: {biome_file}")
                        continue
                    biome_data = json.loads(content)

                # Update spawners with registered mobs
                if config["mobs"]:
                    # Clear old spawners
                    biome_data["spawners"]["monster"] = []
                    biome_data["spawners"]["creature"] = []
                    biome_data["spawners"]["ambient"] = []

                    # Add registered mobs
                    for mob in config["mobs"]:
                        spawn_entry = {
                            "type": f"elementaldimensions:{mob}",
                            "weight": 10,
                            "minCount": 1,
                            "maxCount": 3
                        }

                        # Classify mobs
                        if mob in ["luller", "somnian_butterfly", "skywhale", "lumifin", "cave_snail"]:
                            biome_data["spawners"]["creature"].append(spawn_entry)
                        elif mob in ["storm_hawk", "spore_bat"]:
                            biome_data["spawners"]["ambient"].append(spawn_entry)
                        else:
                            biome_data["spawners"]["monster"].append(spawn_entry)

                # Save updated biome
                with open(biome_file, 'w', encoding='utf-8') as f:
                    json.dump(biome_data, f, indent=2)
                biome_updated += 1
            except json.JSONDecodeError as e:
                print(f"  ⚠️  JSON error in {biome_file}: {e}")
            except Exception as e:
                print(f"  ⚠️  Error processing {biome_file}: {e}")
        else:
            print(f"  ⚠️  Missing: {biome_file}")

print(f"✅ Updated {biome_updated} biome files")

# === CREATE ORE GENERATION ===
print(f"\n⛏️  Creating ore generation for {len(ORE_CONFIGS)} ores...")
ore_features = 0

for ore_name, ore_config in ORE_CONFIGS.items():
    # Create configured feature
    configured_feature = {
        "type": "minecraft:ore",
        "config": {
            "size": ore_config["vein_size"],
            "discard_chance_on_air_exposure": 0.0,
            "targets": [
                {
                    "target": {
                        "predicate_type": "minecraft:tag_match",
                        "tag": "minecraft:stone_ore_replaceables"
                    },
                    "state": {
                        "Name": f"elementaldimensions:{ore_name}"
                    }
                }
            ]
        }
    }

    configured_path = os.path.join(CONFIGURED_FEATURES_DIR, f"{ore_name}.json")
    with open(configured_path, 'w') as f:
        json.dump(configured_feature, f, indent=2)

    # Create placed feature
    placed_feature = {
        "feature": f"elementaldimensions:{ore_name}",
        "placement": [
            {
                "type": "minecraft:count",
                "count": ore_config["veins_per_chunk"]
            },
            {
                "type": "minecraft:in_square"
            },
            {
                "type": "minecraft:height_range",
                "height": {
                    "type": "minecraft:uniform",
                    "min_inclusive": {
                        "absolute": ore_config["min_y"]
                    },
                    "max_inclusive": {
                        "absolute": ore_config["max_y"]
                    }
                }
            },
            {
                "type": "minecraft:biome"
            }
        ]
    }

    placed_path = os.path.join(PLACED_FEATURES_DIR, f"{ore_name}.json")
    with open(placed_path, 'w') as f:
        json.dump(placed_feature, f, indent=2)

    ore_features += 1

print(f"✅ Created {ore_features} ore configured features")
print(f"✅ Created {ore_features} ore placed features")

print("\n" + "=" * 80)
print("🎉 WORLDGEN CONFIGURATION UPDATE COMPLETE!")
print("=" * 80)
print(f"\n📊 Summary:")
print(f"   - {noise_updated} noise_settings updated with new blocks")
print(f"   - {biome_updated} biomes updated with registered mobs")
print(f"   - {ore_features * 2} ore generation files created")
print(f"\n✅ Dimensions ready for testing:")
for dim_name in DIMENSIONS.keys():
    print(f"   - {dim_name}")
print("=" * 80 + "\n")
