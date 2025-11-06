# Script to create placeholder GeckoLib resources for Undead Realm entities
$basePath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\resources\assets\elementaldimensions"
$geoPath = "$basePath\geo\entity"
$texturePath = "$basePath\textures\entity"
$animPath = "$basePath\animations\entity"
# Create directories
foreach ($path in @($geoPath, $texturePath, $animPath)) {
    if (-not (Test-Path $path)) {
        New-Item -ItemType Directory -Force -Path $path | Out-Null
    }
}
# Entity resource names
$entities = @(
    "zombie_miner", "skeleton_archer", "ghost", "wraith", "banshee",
    "necromantic_skeleton", "phantom_knight", "graveyard_guardian",
    "soul_stealer", "bone_dragon", "lich_king"
)
# Simple GeckoLib model template
$geoTemplate = @'
{
  "format_version": "1.12.0",
  "minecraft:geometry": [
    {
      "description": {
        "identifier": "geometry.undead",
        "texture_width": 64,
        "texture_height": 64
      },
      "bones": [
        {
          "name": "root",
          "pivot": [0, 0, 0]
        }
      ]
    }
  ]
}
'@
# Simple animation template
$animTemplate = @'
{
  "format_version": "1.8.0",
  "animations": {
    "animation.undead.walk": {
      "loop": true,
      "animation_length": 1.0
    },
    "animation.undead.idle": {
      "loop": true,
      "animation_length": 2.0
    }
  }
}
'@
foreach ($entity in $entities) {
    # Create .geo.json
    $geoFile = "$geoPath\$entity.geo.json"
    $geoTemplate | Out-File -FilePath $geoFile -Encoding UTF8
    # Create .animation.json
    $animFile = "$animPath\$entity.animation.json"
    $animTemplate | Out-File -FilePath $animFile -Encoding UTF8
    Write-Output "Created GeckoLib files for $entity"
}
Write-Output "`nAll GeckoLib resource files created successfully!"
Write-Output "Note: Textures will be created separately using System.Drawing"