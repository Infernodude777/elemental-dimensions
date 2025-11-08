import os
import re

# Dimension mapping
DIMENSION_FIX = {
    "DreamingDepthsPortalBlock": ("DREAMING_DEPTHS_WORLD_KEY", "DREAMING_DEPTHS_WORLD"),
    "InfernoRealmPortalBlock": ("INFERNO_REALM_WORLD_KEY", "FIRE_WORLD"),
    "RadiantPlateauPortalBlock": ("RADIANT_PLATEAU_WORLD_KEY", "CELESTINE_EXPANSE_WORLD"),
    "MycelialNexusPortalBlock": ("MYCELIAL_NEXUS_WORLD_KEY", "FUNGAL_DOMINION_WORLD"),
    "GoldenCitadelPortalBlock": ("GOLDEN_CITADEL_WORLD_KEY", "ASTRAL_FRONTIER_WORLD"),
    "AncientGrovesPortalBlock": ("ANCIENT_GROVES_WORLD_KEY", "GLOOMY_CAVERNS_WORLD"),
    "CosmicVoidPortalBlock": ("COSMIC_VOID_WORLD_KEY", "VOID_WORLD"),
    "CelestialSanctumPortalBlock": ("CELESTIAL_SANCTUM_WORLD_KEY", "CELESTIAL_WORLD"),
    "GlyphicArchivesPortalBlock": ("GLYPHIC_ARCHIVES_WORLD_KEY", "FORGOTTEN_ARCHIVE_WORLD"),
    "EchoingExpansePortalBlock": ("ECHOING_EXPANSE_WORLD_KEY", "WATER_WORLD"),
    "UmbralWastelandPortalBlock": ("UMBRAL_WASTELAND_WORLD_KEY", "EARTH_WORLD"),
}

# Block mapping
BLOCK_FIX = {
    "InfernoRealmPortalBlock": ("INFERNAL_STONE", "INFERNO_STONE"),
    "RadiantPlateauPortalBlock": ("RADIANT_STONE", "RADIANT_BLOCK"),
    "MycelialNexusPortalBlock": ("FUNGAL_STONE", "FUNGAL_STONE"),
    "GoldenCitadelPortalBlock": ("GOLDEN_BRICKS", "GOLDEN_BRICKS"),
}

portal_dir = "1.21.1/src/main/java/com/nikhil/elementaldimensions/block/portal"

for filename, (old_dim, new_dim) in DIMENSION_FIX.items():
    filepath = os.path.join(portal_dir, f"{filename}.java")

    if not os.path.exists(filepath):
        print(f"❌ File not found: {filepath}")
        continue

    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    # Fix dimension constant
    content = content.replace(f"ModDimensions.{old_dim}", f"ModDimensions.{new_dim}")

    # Fix block constant if needed
    if filename in BLOCK_FIX:
        old_block, new_block = BLOCK_FIX[filename]
        content = content.replace(f"ModBlocks.{old_block}", f"ModBlocks.{new_block}")

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

    print(f"✅ Fixed {filename}")

print("\n✅ All portal blocks fixed!")
