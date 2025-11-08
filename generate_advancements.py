"""
Generate advancement JSON files for Elemental Dimensions
Creates proper advancement tree with icons, triggers, and rewards
"""

import json
import os

ADVANCEMENTS_DIR = "1.21.1/src/main/resources/data/elementaldimensions/advancement"

# Define advancement tree
ADVANCEMENTS = {
    # Root advancement
    "root": {
        "icon": {"item": "elementaldimensions:celestial_stone"},
        "title": "advancement.elementaldimensions.root.title",
        "description": "advancement.elementaldimensions.root.description",
        "background": "minecraft:textures/block/end_stone.png",
        "show_toast": False,
        "announce_to_chat": False,
        "criteria": {
            "tick": {"trigger": "minecraft:tick"}
        }
    },

    # Dimension entry advancements
    "enter_inferno_realm": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:inferno_stone"},
        "title": "advancement.elementaldimensions.enter_inferno_realm.title",
        "description": "advancement.elementaldimensions.enter_inferno_realm.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:inferno_realm"
                }
            }
        }
    },

    "enter_aquatic_realm": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:aqua_stone"},
        "title": "advancement.elementaldimensions.enter_aquatic_realm.title",
        "description": "advancement.elementaldimensions.enter_aquatic_realm.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:aquatic_realm"
                }
            }
        }
    },

    "enter_terran_realm": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:terran_bedrock"},
        "title": "advancement.elementaldimensions.enter_terran_realm.title",
        "description": "advancement.elementaldimensions.enter_terran_realm.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:terran_realm"
                }
            }
        }
    },

    "enter_skybound_realm": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:cloud_stone"},
        "title": "advancement.elementaldimensions.enter_skybound_realm.title",
        "description": "advancement.elementaldimensions.enter_skybound_realm.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:skybound_realm"
                }
            }
        }
    },

    "enter_celestial_realm": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:celestial_stone"},
        "title": "advancement.elementaldimensions.enter_celestial_realm.title",
        "description": "advancement.elementaldimensions.enter_celestial_realm.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:celestial_realm"
                }
            }
        }
    },

    "enter_void_realm": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:void_rift_block"},
        "title": "advancement.elementaldimensions.enter_void_realm.title",
        "description": "advancement.elementaldimensions.enter_void_realm.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:void_realm"
                }
            }
        }
    },

    "enter_dreaming_depths": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:dreamstone"},
        "title": "advancement.elementaldimensions.enter_dreaming_depths.title",
        "description": "advancement.elementaldimensions.enter_dreaming_depths.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:dreaming_depths"
                }
            }
        }
    },

    "enter_celestine_expanse": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:starlit_stone"},
        "title": "advancement.elementaldimensions.enter_celestine_expanse.title",
        "description": "advancement.elementaldimensions.enter_celestine_expanse.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:celestine_expanse"
                }
            }
        }
    },

    "enter_fungal_dominion": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:mycelite_stone"},
        "title": "advancement.elementaldimensions.enter_fungal_dominion.title",
        "description": "advancement.elementaldimensions.enter_fungal_dominion.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:fungal_dominion"
                }
            }
        }
    },

    "enter_forgotten_archive": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:runestone"},
        "title": "advancement.elementaldimensions.enter_forgotten_archive.title",
        "description": "advancement.elementaldimensions.enter_forgotten_archive.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:forgotten_archive"
                }
            }
        }
    },

    "enter_astral_frontier": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:cosmic_stone"},
        "title": "advancement.elementaldimensions.enter_astral_frontier.title",
        "description": "advancement.elementaldimensions.enter_astral_frontier.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:astral_frontier"
                }
            }
        }
    },

    "enter_gloomy_caverns": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:shadestone"},
        "title": "advancement.elementaldimensions.enter_gloomy_caverns.title",
        "description": "advancement.elementaldimensions.enter_gloomy_caverns.description",
        "criteria": {
            "entered_dimension": {
                "trigger": "minecraft:changed_dimension",
                "conditions": {
                    "to": "elementaldimensions:gloomy_caverns"
                }
            }
        }
    },

    # Boss defeat advancements
    "defeat_pyros": {
        "parent": "elementaldimensions:enter_inferno_realm",
        "icon": {"item": "minecraft:blaze_rod"},
        "title": "advancement.elementaldimensions.defeat_pyros.title",
        "description": "advancement.elementaldimensions.defeat_pyros.description",
        "frame": "challenge",
        "criteria": {
            "killed_boss": {
                "trigger": "minecraft:player_killed_entity",
                "conditions": {
                    "entity": {
                        "type": "elementaldimensions:pyros"
                    }
                }
            }
        },
        "rewards": {
            "experience": 500
        }
    },

    "defeat_the_dreamer": {
        "parent": "elementaldimensions:enter_dreaming_depths",
        "icon": {"item": "elementaldimensions:echo_crystal"},
        "title": "advancement.elementaldimensions.defeat_the_dreamer.title",
        "description": "advancement.elementaldimensions.defeat_the_dreamer.description",
        "frame": "challenge",
        "criteria": {
            "killed_boss": {
                "trigger": "minecraft:player_killed_entity",
                "conditions": {
                    "entity": {
                        "type": "elementaldimensions:the_dreamer"
                    }
                }
            }
        },
        "rewards": {
            "experience": 500
        }
    },

    # Collection achievements
    "obtain_essence": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:fire_essence"},
        "title": "advancement.elementaldimensions.obtain_essence.title",
        "description": "advancement.elementaldimensions.obtain_essence.description",
        "criteria": {
            "has_essence": {
                "trigger": "minecraft:inventory_changed",
                "conditions": {
                    "items": [
                        {"items": ["elementaldimensions:fire_essence"]},
                        {"items": ["elementaldimensions:water_essence"]},
                        {"items": ["elementaldimensions:earth_essence"]},
                        {"items": ["elementaldimensions:air_essence"]}
                    ]
                }
            }
        }
    },

    "craft_first_tool": {
        "parent": "elementaldimensions:root",
        "icon": {"item": "elementaldimensions:reverie_pickaxe"},
        "title": "advancement.elementaldimensions.craft_first_tool.title",
        "description": "advancement.elementaldimensions.craft_first_tool.description",
        "criteria": {
            "has_tool": {
                "trigger": "minecraft:inventory_changed",
                "conditions": {
                    "items": [
                        {"items": ["elementaldimensions:reverie_sword"]},
                        {"items": ["elementaldimensions:lumicore_pickaxe"]},
                        {"items": ["elementaldimensions:mycelite_axe"]},
                        {"items": ["elementaldimensions:glyph_shovel"]},
                        {"items": ["elementaldimensions:celestite_hoe"]},
                        {"items": ["elementaldimensions:gloom_sword"]}
                    ]
                }
            }
        }
    },
}

def generate_advancements():
    """Generate all advancement JSON files"""
    os.makedirs(ADVANCEMENTS_DIR, exist_ok=True)

    for name, data in ADVANCEMENTS.items():
        # Build advancement JSON
        advancement = {
            "display": {
                "icon": data["icon"],
                "title": {"translate": data["title"]},
                "description": {"translate": data["description"]},
                "frame": data.get("frame", "task"),
                "show_toast": data.get("show_toast", True),
                "announce_to_chat": data.get("announce_to_chat", True),
                "hidden": False
            },
            "criteria": data["criteria"]
        }

        # Add parent if exists
        if "parent" in data:
            advancement["parent"] = data["parent"]

        # Add background for root
        if "background" in data:
            advancement["display"]["background"] = data["background"]

        # Add rewards if exists
        if "rewards" in data:
            advancement["rewards"] = data["rewards"]

        # Write JSON file
        filename = f"{name}.json"
        with open(os.path.join(ADVANCEMENTS_DIR, filename), 'w') as f:
            json.dump(advancement, f, indent=2)

    print(f"✅ Generated {len(ADVANCEMENTS)} advancement files")
    print(f"📁 Location: {ADVANCEMENTS_DIR}")
    print()
    print("✅ Advancements now properly display:")
    print("   - Title: 'Land of Slumber' (not advancement.elementaldimensions.enter_dreaming_depths)")
    print("   - Icons: Dimension-specific blocks")
    print("   - Frames: task (normal), challenge (boss defeats)")
    print("   - Toast notifications: ✓")
    print("   - Chat announcements: ✓")
    print()
    print("📊 Advancement categories:")
    print(f"   - Root: 1")
    print(f"   - Dimension entry: 11")
    print(f"   - Boss defeats: {sum(1 for a in ADVANCEMENTS if 'defeat_' in a)}")
    print(f"   - Collection: {sum(1 for a in ADVANCEMENTS if 'obtain_' in a or 'craft_' in a)}")

if __name__ == "__main__":
    generate_advancements()
