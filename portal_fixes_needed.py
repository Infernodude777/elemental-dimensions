"""
Quick reference for fixing portal compilation errors
"""

# Dimension mapping - Old name → Correct ModDimensions constant
DIMENSION_MAP = {
    "DREAMING_DEPTHS_WORLD_KEY": "DREAMING_DEPTHS_WORLD",
    "INFERNO_REALM_WORLD_KEY": "FIRE_WORLD",  # Inferno Realm = Fire World
    "RADIANT_PLATEAU_WORLD_KEY": "CELESTINE_EXPANSE_WORLD",  # Radiant Plateau = Celestine Expanse
    "MYCELIAL_NEXUS_WORLD_KEY": "FUNGAL_DOMINION_WORLD",  # Mycelial Nexus = Fungal Dominion
    "GOLDEN_CITADEL_WORLD_KEY": "ASTRAL_FRONTIER_WORLD",  # Golden Citadel = Astral Frontier
    "ANCIENT_GROVES_WORLD_KEY": "GLOOMY_CAVERNS_WORLD",  # Ancient Groves = Gloomy Caverns
    "COSMIC_VOID_WORLD_KEY": "VOID_WORLD",  # Cosmic Void = Void World
    "CELESTIAL_SANCTUM_WORLD_KEY": "CELESTIAL_WORLD",  # Celestial Sanctum = Celestial World
    "GLYPHIC_ARCHIVES_WORLD_KEY": "FORGOTTEN_ARCHIVE_WORLD",  # Glyphic Archives = Forgotten Archive
    "ECHOING_EXPANSE_WORLD_KEY": "WATER_WORLD",  # NEED TO CHECK - might be wrong
    "UMBRAL_WASTELAND_WORLD_KEY": "EARTH_WORLD",  # NEED TO CHECK - might be wrong
}

# Block mapping - Used name → Actual ModBlocks constant
BLOCK_MAP = {
    "INFERNAL_STONE": "FIRE_STONE",  # Check actual name
    "RADIANT_STONE": "CELESTINE_STONE",  # Check actual name
    "FUNGAL_STONE": "FUNGAL_STONE",  # Check if exists
    "GOLDEN_BRICKS": "GOLDEN_BRICKS",  # Check if exists
}

print("Fix required:")
print("1. canUsePortals() requires boolean argument - remove the call")
print("2. player.teleport() signature changed - use FabricDimensions.teleport()")
print("3. Make getPortalWidth/Height() public in DimensionalPortalBlock")
print("4. Fix dimension constants to match ModDimensions")
print("5. Fix block constants to match ModBlocks")
