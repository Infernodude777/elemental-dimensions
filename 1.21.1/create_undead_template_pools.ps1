# Create template pools for Undead Realm structures
$structures = @(
    "crypt", "tomb", "graveyard", "bone_castle", "mausoleum",
    "haunted_house", "necromancer_tower", "soul_prison", "phantom_fortress",
    "lich_citadel", "cursed_well", "bone_garden", "spirit_shrine",
    "undead_catacombs", "wraith_monument"
)
$basePath = "src/main/resources/data/elementaldimensions/worldgen/template_pool"
foreach ($struct in $structures) {
    $content = @"
{
  "name": "elementaldimensions:undead_realm/$struct/start_pool",
  "fallback": "minecraft:empty",
  "elements": [
    {
      "weight": 1,
      "element": {
        "location": "elementaldimensions:undead_realm/$struct/base",
        "processors": "minecraft:empty",
        "projection": "rigid",
        "element_type": "minecraft:single_pool_element"
      }
    }
  ]
}
"@
    $filePath = Join-Path $basePath "undead_${struct}_start.json"
    [System.IO.File]::WriteAllText($filePath, $content, [System.Text.UTF8Encoding]::new($false))
    Write-Output "Created template pool for $struct"
}
Write-Output "All 15 Undead Realm template pools created!"