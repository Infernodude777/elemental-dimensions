# Script to generate item models for Undead Realm items
$items = @(
    "soul_reaper_sword", "necromancer_staff", "phantom_cloak", "cursed_bow",
    "soul_gem", "lich_crown", "void_totem", "spectral_orb",
    "graveyard_key", "wraith_essence", "bone_shard", "phantom_dust",
    "cursed_ingot", "ectoplasm", "lich_phylactery"
)
$basePath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\resources\assets\elementaldimensions\models\item"
foreach ($item in $items) {
    $itemModelPath = "$basePath\$item.json"
    $itemModelContent = @"
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "elementaldimensions:item/$item"
  }
}
"@
    $itemModelContent | Out-File -FilePath $itemModelPath -Encoding UTF8
    Write-Output "Created model for $item"
}
Write-Output "All item models created successfully!"