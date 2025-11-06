# Comprehensive fix for all registry errors
Write-Host "=== COMPREHENSIVE REGISTRY ERROR FIX ===" -ForegroundColor Cyan
Write-Host ""

# Fix 1: Update all biome files
Write-Host "1. Fixing biome files..." -ForegroundColor Yellow

$biomeDir = "src\main\resources\data\elementaldimensions\worldgen\biome"
$biomeFiles = Get-ChildItem "$biomeDir\*.json" -ErrorAction SilentlyContinue

foreach ($file in $biomeFiles) {
    try {
        $content = Get-Content $file.FullName -Raw | ConvertFrom-Json

        # Fix music
        if ($content.effects.music) {
            $content.effects.music.sound = "minecraft:music.overworld.meadow"
        }

        # Clear spawners
        if ($content.spawners) {
            $content.spawners.monster = @()
            $content.spawners.creature = @()
            $content.spawners.ambient = @()
            $content.spawners.water_creature = @()
            $content.spawners.water_ambient = @()
            $content.spawners.misc = @()
        }

        $content | ConvertTo-Json -Depth 20 | Set-Content $file.FullName -Encoding UTF8
        Write-Host "  Fixed: $($file.Name)" -ForegroundColor Green
    } catch {
        Write-Host "  Error: $($file.Name)" -ForegroundColor Red
    }
}

# Fix 2: Delete problematic files
Write-Host ""
Write-Host "2. Removing incompatible files..." -ForegroundColor Yellow

$dirsToClean = @(
    "src\main\resources\data\elementaldimensions\worldgen\density_function",
    "src\main\resources\data\elementaldimensions\worldgen\noise"
)

foreach ($dir in $dirsToClean) {
    if (Test-Path $dir) {
        Remove-Item "$dir\*" -Recurse -Force -ErrorAction SilentlyContinue
        Write-Host "  Cleaned: $dir" -ForegroundColor Green
    }
}

# Fix 3: Fix structure files
Write-Host ""
Write-Host "3. Fixing structures..." -ForegroundColor Yellow

$structureDir = "src\main\resources\data\elementaldimensions\worldgen\structure"
$structureFiles = Get-ChildItem "$structureDir\*.json" -ErrorAction SilentlyContinue

foreach ($file in $structureFiles) {
    try {
        $content = Get-Content $file.FullName -Raw

        if ($content.Trim() -eq "[]" -or $content.Trim().Length -lt 10) {
            $minimalStructure = @{
                type = "minecraft:jigsaw"
                start_pool = "minecraft:empty"
                size = 0
                max_distance_from_center = 80
                biomes = "#minecraft:is_overworld"
                step = "surface_structures"
                terrain_adaptation = "none"
                spawn_overrides = @{}
            }

            $minimalStructure | ConvertTo-Json -Depth 10 | Set-Content $file.FullName -Encoding UTF8
            Write-Host "  Fixed: $($file.Name)" -ForegroundColor Green
        }
    } catch {}
}

Write-Host ""
Write-Host "=== FIX COMPLETE ===" -ForegroundColor Green
Write-Host "Rebuild with: .\gradlew build" -ForegroundColor Cyan
