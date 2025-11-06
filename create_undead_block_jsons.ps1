# Script to generate blockstates, models, and loot tables for Undead Realm blocks
$blocks = @(
    "necro_stone", "soul_sand_block", "corrupted_flesh", "grave_dirt",
    "tombstone", "spectral_glass", "wraith_lantern", "bone_bricks",
    "death_weed", "ectoplasm_block", "phantom_ore", "undead_portal_frame",
    "rotten_planks", "crypt_bricks", "soul_fire_block", "ghostwood_log",
    "ghostwood_leaves", "blood_crystal", "cursed_cobblestone", "necrotic_soil",
    "spirit_crystal", "dark_altar", "coffin_block", "chiseled_bone",
    "haunted_chains", "mausoleum_stone", "spectral_torch", "zombie_flesh_block",
    "bone_pillar"
)
$basePath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\resources"
foreach ($block in $blocks) {
    # Blockstate
    $blockstatePath = "$basePath\assets\elementaldimensions\blockstates\$block.json"
    $blockstateContent = @"
{
  "variants": {
    "": {
      "model": "elementaldimensions:block/$block"
    }
  }
}
"@
    $blockstateContent | Out-File -FilePath $blockstatePath -Encoding UTF8
    # Block Model
    $blockModelPath = "$basePath\assets\elementaldimensions\models\block\$block.json"
    $blockModelContent = @"
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "elementaldimensions:block/$block"
  }
}
"@
    $blockModelContent | Out-File -FilePath $blockModelPath -Encoding UTF8
    # Item Model
    $itemModelPath = "$basePath\assets\elementaldimensions\models\item\$block.json"
    $itemModelContent = @"
{
  "parent": "elementaldimensions:block/$block"
}
"@
    $itemModelContent | Out-File -FilePath $itemModelPath -Encoding UTF8
    # Loot Table
    $lootTablePath = "$basePath\data\elementaldimensions\loot_table\blocks\$block.json"
    $lootTableContent = @"
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "elementaldimensions:$block"
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    }
  ]
}
"@
    $lootTableContent | Out-File -FilePath $lootTablePath -Encoding UTF8
    Write-Output "Created files for $block"
}
Write-Output "All block JSON files created successfully!"