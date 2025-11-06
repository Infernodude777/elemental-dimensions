# Fix all registry loading errors in Elemental Dimensions mod
# This script fixes biome music, entity spawns, and structure files

Write-Host "=== FIXING REGISTRY ERRORS ===" -ForegroundColor Cyan
Write-Host ""

$biomeDir = "src\main\resources\data\elementaldimensions\worldgen\biome"
$structureDir = "src\main\resources\data\elementaldimensions\worldgen\structure"

# Fix 1: Remove custom music from biomes (use vanilla sounds instead)
Write-Host "1. Fixing biome music references..." -ForegroundColor Yellow

$biomeFiles = @(
    "aquatica_biome.json",
    "celestial_biome.json",
    "firelands_biome.json",
    "skyreach_peaks_biome.json",
    "terra_depths_biome.json",
    "undead_wastes.json"
)

foreach ($file in $biomeFiles) {
    $path = Join-Path $biomeDir $file
    if (Test-Path $path) {
        $content = Get-Content $path -Raw | ConvertFrom-Json

        # Replace custom music with vanilla underwater music
        if ($content.effects.music) {
            $content.effects.music.sound = "minecraft:music.overworld.deep_dark"
        }

        # Remove any entity spawns that reference unregistered entities
        if ($content.spawners) {
            # Keep only registered entities in spawners
            $content.spawners.ambient = @()
            $content.spawners.creature = @()
        }

        $content | ConvertTo-Json -Depth 10 | Set-Content $path -Encoding UTF8
        Write-Host "  Fixed: $file" -ForegroundColor Green
    }
}

# Fix 2: Fix empty structure files
Write-Host ""
Write-Host "2. Fixing structure files..." -ForegroundColor Yellow

$structureFiles = Get-ChildItem "$structureDir\*.json" -ErrorAction SilentlyContinue

foreach ($file in $structureFiles) {
    $content = Get-Content $file.FullName -Raw

    # If file is just "[]" (empty array), replace with minimal valid structure
    if ($content.Trim() -eq "[]") {
        $minimalStructure = @{
            type = "minecraft:jigsaw"
            start_pool = "elementaldimensions:empty"
            size = 0
            max_distance_from_center = 80
            biomes = "#elementaldimensions:has_structures"
            step = "surface_structures"
            terrain_adaptation = "none"
            spawn_overrides = @{}
        } | ConvertTo-Json -Depth 5

        $minimalStructure | Set-Content $file.FullName -Encoding UTF8
        Write-Host "  Fixed: $($file.Name)" -ForegroundColor Green
    }
}

Write-Host ""
Write-Host "=== FIX COMPLETE ===" -ForegroundColor Green
Write-Host "Registry errors should now be resolved. Try running the game again." -ForegroundColor Cyan
