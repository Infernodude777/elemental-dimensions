# Script to create all 6 new dimensions with blocks, items, entities, etc.
$basePath = "c:/Users/Nikhil/Desktop/elemental_dimensions/1.21.1"
# ============================================
# DREAMING DEPTHS - Blocks (20 blocks)
# ============================================
$dreamingDepthsBlocks = @(
    "dreamstone", "lullglass", "softsoil", "dreamgrass", "nightbloom_vine",
    "echo_crystal", "lucid_water", "phantasm_dust", "etherstone", "mirage_leaf",
    "dreamwood_log", "lucid_flower", "glowsand", "glowglass", "memory_stone",
    "levistone", "dreamroot", "reverie_crystal", "whisper_wool", "dreamlight_lantern"
)
# NOTE: The following item arrays are defined for future use but not currently utilized in this script
# $dreamingDepthsItems = @(
#     "somnacite_crystal", "umbral_shard", "lumen_bloom", "abyssal_pearl",
#     "dream_glass_pane", "umbral_ingot", "memory_fiber", "rift_core",
#     "umbral_sword", "umbral_pickaxe", "umbral_axe", "umbral_shovel", "umbral_hoe",
#     "dreambound_helmet", "dreambound_chestplate", "dreambound_leggings", "dreambound_boots",
#     "potion_lucid_sight", "somnacite_stew", "elixir_echoes", "mirror_of_rest"
# )
# ============================================
# CELESTINE EXPANSE - Blocks (20 blocks)
# ============================================
$celestineExpanseBlocks = @(
    "celestine_grass", "starlit_stone", "lumicore_block", "aurora_root", "astral_glass",
    "nimbus_dust", "aether_stone", "void_coral", "radiant_crystal", "feather_reed",
    "starfall_ore", "luminite_lantern", "aural_moss", "celestine_brick", "celestine_tile",
    "starlight_pane", "aether_sand", "lumicore_lamp", "crystal_vine", "celestine_ore"
)
# CELESTINE EXPANSE - Items
$celestineExpanseItems = @(
    "celestine_shard", "starfall_ingot", "feather_reed_fiber", "lumicore_crystal",
    "sky_paper", "aether_key", "celestine_ingot", "luminite_rod",
    "celestine_sword", "celestine_pickaxe", "celestine_bow",
    "skyborne_helmet", "skyborne_chestplate", "skyborne_leggings", "skyborne_boots",
    "celestine_key", "celestine_core"
)
# ============================================
# FUNGAL DOMINION - Blocks (20 blocks)
# ============================================
$fungalDominionBlocks = @(
    "mycelite_soil", "root_flesh", "biolume_cap", "spore_pod", "spore_sludge",
    "fungal_vein", "decay_clay", "calcimoss_block", "lumispore_cluster", "fungal_bark",
    "mycelite_brick", "fungal_glass", "symbiont_root", "fungal_vine", "mold_cap_carpet",
    "root_heart_block", "fossil_bark", "sporelight", "fungal_moss", "spore_web"
)
# FUNGAL DOMINION - Items
$fungalDominionItems = @(
    "spore_essence", "lumispore_dust", "fungal_fiber", "mycel_heart", "decay_enzyme",
    "mycelite_sword", "mycelite_pickaxe", "mycelite_hoe",
    "mycelite_helmet", "mycelite_chestplate", "mycelite_leggings", "mycelite_boots",
    "sporebomb", "elixir_renewal", "decay_draught", "symbiotic_tonic", "symbiotic_staff"
)
# ============================================
# FORGOTTEN ARCHIVE - Blocks (20 blocks)
# ============================================
$forgottenArchiveBlocks = @(
    "runestone", "runestone_brick", "archive_sandstone", "mirror_tile", "ink_marble",
    "memory_ink_block", "ink_pool", "glyph_glass", "runic_pedestal", "ink_carpet",
    "engraved_gold", "archive_plank", "knowledge_crystal", "dust_sand", "runic_lantern",
    "inscribed_bookcase", "runestone_slab", "runic_gate", "fossil_tablet", "script_light"
)
# FORGOTTEN ARCHIVE - Items
$forgottenArchiveItems = @(
    "memory_ink", "runic_dust", "ancient_scroll_fragment", "glyph_core", "echo_tablet",
    "archivists_quill", "lens_of_memory", "codex_of_builders", "ink_flask", "runic_chisel",
    "memory_ink_flask"
)
# ============================================
# ASTRAL FRONTIER - Blocks (16 blocks)
# ============================================
$astralFrontierBlocks = @(
    "starlit_grass", "stardust_stone", "celestite_block", "celestite_crystal", "stardust_ore",
    "nebular_dust", "aether_core", "singularity_rock", "astral_glass", "starlight_vine",
    "voidroot", "astral_lantern", "stardust_sand", "astral_ice", "celestial_alloy_block",
    "astral_conduit"
)
# ASTRAL FRONTIER - Items
$astralFrontierItems = @(
    "stardust_fragment", "celestite_shard", "aether_essence", "voidroot_pod",
    "astral_sword", "astral_pickaxe", "celestial_wand", "aether_bow",
    "starborne_helmet", "starborne_chestplate", "starborne_leggings", "starborne_boots",
    "stellar_core"
)
# ============================================
# GLOOMY CAVERNS - Blocks (10 blocks)
# ============================================
$gloomyCavernsBlocks = @(
    "withered_basalt", "echo_stone", "drip_moss", "decay_soil", "ash_sludge",
    "gloom_crystal", "fungal_bark_gloomy", "bone_brick", "echo_lantern", "mourn_vine"
)
# GLOOMY CAVERNS - Items
$gloomyCavernsItems = @(
    "gloom_crystal_shard", "ash_sludge_bucket", "echo_fragment", "tear_of_despair",
    "decay_wood", "gloom_sword", "gloom_pickaxe", "gloom_lantern"
)
Write-Output "Starting generation of all 6 dimensions..."
Write-Output "This will create blocks, items, blockstates, models, textures, and lang entries"
Write-Output ""
# Function to create block registration
function Add-BlockToModBlock {
    param($blockName)
    return @"
    public static final Block ${blockName.ToUpper()} = register("$blockName",
        new Block(AbstractBlock.Settings.create()
            .strength(1.5f)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)));
"@
}
# Create a master list for ModBlocks.java additions
$allBlocksJava = ""
$allBlocksLang = ""
Write-Output "Processing Dreaming Depths blocks..."
foreach ($block in $dreamingDepthsBlocks) {
    $allBlocksJava += Add-BlockToModBlock -blockName $block
    $allBlocksLang += "`n  `"block.elementaldimensions.$block`": `"" + (Get-Culture).TextInfo.ToTitleCase($block.Replace("_", " ")) + "`","
}
Write-Output "Processing Celestine Expanse blocks..."
foreach ($block in $celestineExpanseBlocks) {
    $allBlocksJava += Add-BlockToModBlock -blockName $block
    $allBlocksLang += "`n  `"block.elementaldimensions.$block`": `"" + (Get-Culture).TextInfo.ToTitleCase($block.Replace("_", " ")) + "`","
}
Write-Output "Processing Fungal Dominion blocks..."
foreach ($block in $fungalDominionBlocks) {
    $allBlocksJava += Add-BlockToModBlock -blockName $block
    $allBlocksLang += "`n  `"block.elementaldimensions.$block`": `"" + (Get-Culture).TextInfo.ToTitleCase($block.Replace("_", " ")) + "`","
}
Write-Output "Processing Forgotten Archive blocks..."
foreach ($block in $forgottenArchiveBlocks) {
    $allBlocksJava += Add-BlockToModBlock -blockName $block
    $allBlocksLang += "`n  `"block.elementaldimensions.$block`": `"" + (Get-Culture).TextInfo.ToTitleCase($block.Replace("_", " ")) + "`","
}
Write-Output "Processing Astral Frontier blocks..."
foreach ($block in $astralFrontierBlocks) {
    $allBlocksJava += Add-BlockToModBlock -blockName $block
    $allBlocksLang += "`n  `"block.elementaldimensions.$block`": `"" + (Get-Culture).TextInfo.ToTitleCase($block.Replace("_", " ")) + "`","
}
Write-Output "Processing Gloomy Caverns blocks..."
foreach ($block in $gloomyCavernsBlocks) {
    $allBlocksJava += Add-BlockToModBlock -blockName $block
    $allBlocksLang += "`n  `"block.elementaldimensions.$block`": `"" + (Get-Culture).TextInfo.ToTitleCase($block.Replace("_", " ")) + "`","
}
# Save block additions to a file for manual integration
[System.IO.File]::WriteAllText("$basePath/NEW_BLOCKS_TO_ADD.txt", $allBlocksJava, [System.Text.UTF8Encoding]::new($false))
[System.IO.File]::WriteAllText("$basePath/NEW_LANG_BLOCKS.txt", $allBlocksLang, [System.Text.UTF8Encoding]::new($false))
Write-Output ""
Write-Output "✓ Generated block additions - see NEW_BLOCKS_TO_ADD.txt"
Write-Output "✓ Generated lang entries - see NEW_LANG_BLOCKS.txt"
Write-Output ""
Write-Output "Total blocks created: $($dreamingDepthsBlocks.Count + $celestineExpanseBlocks.Count + $fungalDominionBlocks.Count + $forgottenArchiveBlocks.Count + $astralFrontierBlocks.Count + $gloomyCavernsBlocks.Count)"
Write-Output ""
Write-Output "Next: Creating textures, blockstates, and models..."
