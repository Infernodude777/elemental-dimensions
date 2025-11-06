# Generate blockstates, models, loot tables, and textures for all 6 new dimensions
Add-Type -AssemblyName System.Drawing
$basePath = "c:/Users/Nikhil/Desktop/elemental_dimensions/1.21.1/src/main/resources"
# All blocks for all 6 dimensions
$allBlocks = @(
    # Dreaming Depths
    "dreamstone", "lullglass", "softsoil", "dreamgrass", "nightbloom_vine",
    "echo_crystal", "phantasm_dust", "etherstone", "mirage_leaf", "dreamwood_log",
    "lucid_flower", "glowsand", "glowglass", "memory_stone", "levistone",
    "dreamroot", "reverie_crystal", "whisper_wool", "dreamlight_lantern",
    # Celestine Expanse
    "celestine_grass", "starlit_stone", "lumicore_block", "aurora_root", "astral_glass",
    "nimbus_dust", "aether_stone", "void_coral", "radiant_crystal", "feather_reed",
    "starfall_ore", "luminite_lantern", "aural_moss", "celestine_brick", "celestine_tile",
    "starlight_pane", "aether_sand", "lumicore_lamp", "crystal_vine", "celestine_ore",
    # Fungal Dominion
    "mycelite_soil", "root_flesh", "biolume_cap", "spore_pod", "fungal_vein",
    "decay_clay", "calcimoss_block", "lumispore_cluster", "fungal_bark", "mycelite_brick",
    "fungal_glass", "symbiont_root", "fungal_vine", "mold_cap_carpet", "root_heart_block",
    "fossil_bark", "sporelight", "fungal_moss", "spore_web",
    # Forgotten Archive
    "runestone", "runestone_brick", "archive_sandstone", "mirror_tile", "ink_marble",
    "memory_ink_block", "glyph_glass", "runic_pedestal", "ink_carpet", "engraved_gold",
    "archive_plank", "knowledge_crystal", "dust_sand", "runic_lantern", "inscribed_bookcase",
    "runestone_slab", "runic_gate", "fossil_tablet", "script_light",
    # Astral Frontier
    "starlit_grass_af", "stardust_stone", "celestite_block", "celestite_crystal", "stardust_ore",
    "nebular_dust", "aether_core", "singularity_rock", "astral_glass_af", "starlight_vine",
    "voidroot", "astral_lantern", "stardust_sand", "astral_ice", "celestial_alloy_block",
    "astral_conduit",
    # Gloomy Caverns
    "withered_basalt", "echo_stone", "drip_moss", "decay_soil", "gloom_crystal",
    "fungal_bark_gloomy", "bone_brick", "echo_lantern", "mourn_vine"
)
Write-Output "Creating blockstates, models, loot tables, and textures for $($allBlocks.Count) blocks..."
# Color palette for different dimensions
$colorMap = @{
    "dream" = @(0xEA, 0xD7, 0xFF)   # Lavender
    "celest" = @(0x88, 0xC2, 0xFF)  # Sky blue
    "fungal" = @(0x40, 0x80, 0x40)  # Green
    "archive" = @(0xD1, 0xC2, 0x8F) # Golden
    "astral" = @(0x4C, 0x2A, 0x86)  # Purple
    "gloomy" = @(0x3B, 0x3B, 0x3B)  # Gray
}
function Get-BlockColor($blockName) {
    if ($blockName -like "*dream*" -or $blockName -like "*lucid*" -or $blockName -like "*phantasm*" -or $blockName -like "*ether*" -or $blockName -like "*mirage*" -or $blockName -like "*reverie*" -or $blockName -like "*whisper*" -or $blockName -like "*lullglass*" -or $blockName -like "*glowsand*" -or $blockName -like "*glowglass*" -or $blockName -like "*memory*" -or $blockName -like "*levi*" -or $blockName -like "*nightbloom*" -or $blockName -like "*echo_crystal*" -or $blockName -like "*softsoil*") {
        return $colorMap["dream"]
    } elseif ($blockName -like "*celest*" -or $blockName -like "*star*" -or $blockName -like "*lumi*" -or $blockName -like "*aurora*" -or $blockName -like "*astral_glass" -or $blockName -like "*nimbus*" -or $blockName -like "*aether_stone" -or $blockName -like "*void_coral" -or $blockName -like "*radiant*" -or $blockName -like "*feather*" -or $blockName -like "*aural*" -or $blockName -like "*aether_sand" -or $blockName -like "*crystal_vine") {
        return $colorMap["celest"]
    } elseif ($blockName -like "*mycel*" -or $blockName -like "*root*" -or $blockName -like "*biolume*" -or $blockName -like "*spore*" -or $blockName -like "*fungal*" -or $blockName -like "*decay*" -or $blockName -like "*calcimoss*" -or $blockName -like "*lumispore*" -or $blockName -like "*mold*" -or $blockName -like "*symbiont*" -or $blockName -like "*fossil_bark") {
        return $colorMap["fungal"]
    } elseif ($blockName -like "*rune*" -or $blockName -like "*archive*" -or $blockName -like "*mirror*" -or $blockName -like "*ink*" -or $blockName -like "*glyph*" -or $blockName -like "*engraved*" -or $blockName -like "*knowledge*" -or $blockName -like "*dust_sand" -or $blockName -like "*inscribed*" -or $blockName -like "*fossil_tablet" -or $blockName -like "*script*") {
        return $colorMap["archive"]
    } elseif ($blockName -like "*stardust*" -or $blockName -like "*celestite*" -or $blockName -like "*nebular*" -or $blockName -like "*aether_core" -or $blockName -like "*singularity*" -or $blockName -like "*astral_glass_af" -or $blockName -like "*starlight_vine" -or $blockName -like "*voidroot" -or $blockName -like "*astral_lantern" -or $blockName -like "*astral_ice" -or $blockName -like "*celestial_alloy" -or $blockName -like "*astral_conduit" -or $blockName -like "*starlit_grass_af") {
        return $colorMap["astral"]
    } elseif ($blockName -like "*withered*" -or $blockName -like "*echo_stone" -or $blockName -like "*drip*" -or $blockName -like "*decay_soil" -or $blockName -like "*gloom*" -or $blockName -like "*fungal_bark_gloomy" -or $blockName -like "*bone_brick" -or $blockName -like "*echo_lantern" -or $blockName -like "*mourn*") {
        return $colorMap["gloomy"]
    }
    return @(0x80, 0x80, 0x80)  # Default gray
}
$count = 0
foreach ($block in $allBlocks) {
    $count++
    if ($count % 10 -eq 0) {
        Write-Output "  Processed $count/$($allBlocks.Count) blocks..."
    }
    # Blockstate
    $blockstatePath = "$basePath/assets/elementaldimensions/blockstates/$block.json"
    $blockstateContent = @"
{
  "variants": {
    "": {
      "model": "elementaldimensions:block/$block"
    }
  }
}
"@
    [System.IO.File]::WriteAllText($blockstatePath, $blockstateContent, [System.Text.UTF8Encoding]::new($false))
    # Block model
    $blockModelPath = "$basePath/assets/elementaldimensions/models/block/$block.json"
    $blockModelContent = @"
{
  "parent": "block/cube_all",
  "textures": {
    "all": "elementaldimensions:block/$block"
  }
}
"@
    [System.IO.File]::WriteAllText($blockModelPath, $blockModelContent, [System.Text.UTF8Encoding]::new($false))
    # Item model
    $itemModelPath = "$basePath/assets/elementaldimensions/models/item/$block.json"
    $itemModelContent = @"
{
  "parent": "elementaldimensions:block/$block"
}
"@
    [System.IO.File]::WriteAllText($itemModelPath, $itemModelContent, [System.Text.UTF8Encoding]::new($false))
    # Loot table
    $lootTablePath = "$basePath/data/elementaldimensions/loot_table/blocks/$block.json"
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
    [System.IO.File]::WriteAllText($lootTablePath, $lootTableContent, [System.Text.UTF8Encoding]::new($false))
    # Texture (16x16 placeholder)
    $texturePath = "$basePath/assets/elementaldimensions/textures/block/$block.png"
    $bmp = New-Object System.Drawing.Bitmap 16, 16
    $graphics = [System.Drawing.Graphics]::FromImage($bmp)
    $color = Get-BlockColor $block
    $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, $color[0], $color[1], $color[2]))
    $graphics.FillRectangle($brush, 0, 0, 16, 16)
    # Add some variation
    $darkerBrush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, [Math]::Max(0, $color[0]-30), [Math]::Max(0, $color[1]-30), [Math]::Max(0, $color[2]-30)))
    for ($i = 0; $i -lt 4; $i++) {
        $x = Get-Random -Minimum 0 -Maximum 16
        $y = Get-Random -Minimum 0 -Maximum 16
        $graphics.FillRectangle($darkerBrush, $x, $y, 2, 2)
    }
    $bmp.Save($texturePath, [System.Drawing.Imaging.ImageFormat]::Png)
    $graphics.Dispose()
    $bmp.Dispose()
    $brush.Dispose()
    $darkerBrush.Dispose()
}
Write-Output ""
Write-Output "Successfully created $($allBlocks.Count) blockstates"
Write-Output "Successfully created $($allBlocks.Count) block models"
Write-Output "Successfully created $($allBlocks.Count) item models"
Write-Output "Successfully created $($allBlocks.Count) loot tables"
Write-Output "Successfully created $($allBlocks.Count) textures"
Write-Output ""
Write-Output "All block resources generated successfully"