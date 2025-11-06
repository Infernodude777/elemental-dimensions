# Undead Realm structures
$structures = @(
    @{name="crypt"; size=2; distance=60},
    @{name="tomb"; size=2; distance=50},
    @{name="graveyard"; size=3; distance=70},
    @{name="bone_castle"; size=5; distance=100},
    @{name="mausoleum"; size=3; distance=60},
    @{name="haunted_house"; size=2; distance=50},
    @{name="necromancer_tower"; size=4; distance=80},
    @{name="soul_prison"; size=3; distance=70},
    @{name="phantom_fortress"; size=5; distance=100},
    @{name="lich_citadel"; size=6; distance=120},
    @{name="cursed_well"; size=1; distance=30},
    @{name="bone_garden"; size=2; distance=50},
    @{name="spirit_shrine"; size=2; distance=50},
    @{name="undead_catacombs"; size=4; distance=90},
    @{name="wraith_monument"; size=3; distance=60}
)
$basePath = "src/main/resources/data/elementaldimensions/worldgen/structure"
foreach ($struct in $structures) {
    $content = @"
{
  "type": "minecraft:jigsaw",
  "start_pool": "elementaldimensions:undead_realm/$($struct.name)/start_pool",
  "size": $($struct.size),
  "max_distance_from_center": $($struct.distance),
  "biomes": "#elementaldimensions:has_structure/undead_$($struct.name)",
  "step": "surface_structures",
  "terrain_adaptation": "beard_thin",
  "start_height": {
    "absolute": 0
  },
  "project_start_to_heightmap": "WORLD_SURFACE_WG",
  "use_expansion_hack": false,
  "spawn_overrides": []
}
"@
    $filePath = Join-Path $basePath "undead_$($struct.name).json"
    [System.IO.File]::WriteAllText($filePath, $content, [System.Text.UTF8Encoding]::new($false))
    Write-Output "Created $($struct.name)"
}
Write-Output "All 15 Undead Realm structures created!"