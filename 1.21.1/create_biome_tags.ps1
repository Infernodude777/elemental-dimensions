# Create biome tags for all Undead Realm structures
$structures = @(
    "crypt", "tomb", "graveyard", "bone_castle", "mausoleum",
    "haunted_house", "necromancer_tower", "soul_prison", "phantom_fortress",
    "lich_citadel", "cursed_well", "bone_garden", "spirit_shrine",
    "undead_catacombs", "wraith_monument"
)
$basePath = "c:/Users/Nikhil/Desktop/elemental_dimensions/1.21.1/src/main/resources/data/elementaldimensions/tags/worldgen/biome/has_structure"
foreach ($struct in $structures) {
    $content = @"
{
  "replace": false,
  "values": [
    "elementaldimensions:undead_realm"
  ]
}
"@
    $filePath = Join-Path $basePath "undead_$struct.json"
    [System.IO.File]::WriteAllText($filePath, $content, [System.Text.UTF8Encoding]::new($false))
    Write-Output "Created biome tag for $struct"
}
Write-Output "All 15 biome tags created!"