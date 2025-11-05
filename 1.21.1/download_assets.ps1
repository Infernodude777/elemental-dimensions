# Asset Download and Organization Script for Elemental Dimensions
# This script helps organize downloaded assets into the correct directory structure

param(
    [switch]$CreateDirectories,
    [switch]$Help,
    [string]$AssetType = "all"
)

$ModRoot = $PSScriptRoot
$AssetsRoot = Join-Path $ModRoot "src\main\resources\assets\elementaldimensions"

function Show-Help {
    Write-Host @"
Elemental Dimensions Asset Management Script
============================================

Usage: .\download_assets.ps1 [-CreateDirectories] [-AssetType <type>] [-Help]

Parameters:
  -CreateDirectories    Create all necessary asset directories
  -AssetType <type>     Process specific asset type: textures, sounds, music, all (default: all)
  -Help                 Show this help message

Examples:
  .\download_assets.ps1 -CreateDirectories
  .\download_assets.ps1 -AssetType sounds

Manual Download Instructions:
1. Visit the sites listed below
2. Download assets matching the descriptions
3. Place them in the Downloads folder with the correct names
4. Run this script to organize them

Recommended Sites:
- Freesound.org (sounds/music - CC0, CC-BY)
- OpenGameArt.org (textures/sprites - various free licenses)
- Pixabay.com (textures/music - CC0)
- Incompetech.com (music - CC-BY)

"@
}

function New-AssetDirectories {
    Write-Host "Creating asset directory structure..." -ForegroundColor Cyan
    
    # Texture directories
    $textureDirs = @(
        "textures\block",
        "textures\item",
        "textures\entity"
    )
    
    # Sound directories
    $soundDirs = @(
        "sounds\music\firelands",
        "sounds\music\aquatica",
        "sounds\music\terra",
        "sounds\music\skyreach",
        "sounds\music\celestial",
        "sounds\music\void",
        "sounds\ambient",
        "sounds\mob",
        "sounds\boss"
    )
    
    $allDirs = $textureDirs + $soundDirs
    
    foreach ($dir in $allDirs) {
        $fullPath = Join-Path $AssetsRoot $dir
        if (!(Test-Path $fullPath)) {
            New-Item -ItemType Directory -Path $fullPath -Force | Out-Null
            Write-Host "  Created: $dir" -ForegroundColor Green
        } else {
            Write-Host "  Exists: $dir" -ForegroundColor Gray
        }
    }
    
    Write-Host "`nDirectory structure created successfully!" -ForegroundColor Green
}

function Show-MissingAssets {
    Write-Host "`n=== MISSING ASSETS CHECKLIST ===" -ForegroundColor Yellow
    
    # Block textures
    $blockTextures = @(
        "void_stone.png",
        "void_crystal_ore.png",
        "void_glass.png",
        "void_pillar.png",
        "void_crystal_block.png",
        "void_portal_frame.png"
    )
    
    # Item textures
    $itemTextures = @(
        "fire_essence.png",
        "water_essence.png",
        "earth_essence.png",
        "air_essence.png",
        "celestial_essence.png",
        "void_essence.png",
        "void_sword.png",
        "dimensional_compass.png",
        "void_heart.png",
        "void_scale.png",
        "dimensional_key.png"
    )
    
    # Entity textures
    $entityTextures = @(
        "void_wraith.png",
        "void_stalker.png",
        "void_guardian.png",
        "cinder_elemental.png",
        "coral_guardian.png",
        "cave_lurker.png",
        "thunder_hawk.png",
        "nebula_wraith.png"
    )
    
    # Music tracks
    $musicTracks = @{
        "firelands" = @("infernal_echoes.ogg", "molten_fury.ogg", "blazing_heart.ogg", "flames_of_chaos.ogg")
        "aquatica" = @("echoing_currents.ogg", "abyssal_waves.ogg", "deep_tide.ogg", "ocean_whispers.ogg")
        "terra" = @("stone_heartbeat.ogg", "earthen_depths.ogg", "cavern_resonance.ogg", "ancient_roots.ogg")
        "skyreach" = @("winds_of_eternity.ogg", "sky_symphony.ogg", "cloud_dancer.ogg")
        "celestial" = @("stellar_drift.ogg", "cosmic_horizon.ogg")
        "void" = @("void_silence.ogg")
    }
    
    # Ambient sounds
    $ambientSounds = @(
        "firelands_ambient.ogg",
        "aquatica_ambient.ogg",
        "void_ambient.ogg"
    )
    
    Write-Host "`nBLOCK TEXTURES (16x16 PNG):" -ForegroundColor Cyan
    foreach ($texture in $blockTextures) {
        $path = Join-Path $AssetsRoot "textures\block\$texture"
        if (Test-Path $path) {
            Write-Host "  [✓] $texture" -ForegroundColor Green
        } else {
            Write-Host "  [ ] $texture" -ForegroundColor Red
        }
    }
    
    Write-Host "`nITEM TEXTURES (16x16 PNG):" -ForegroundColor Cyan
    foreach ($texture in $itemTextures) {
        $path = Join-Path $AssetsRoot "textures\item\$texture"
        if (Test-Path $path) {
            Write-Host "  [✓] $texture" -ForegroundColor Green
        } else {
            Write-Host "  [ ] $texture" -ForegroundColor Red
        }
    }
    
    Write-Host "`nENTITY TEXTURES (64x64+ PNG):" -ForegroundColor Cyan
    foreach ($texture in $entityTextures) {
        $path = Join-Path $AssetsRoot "textures\entity\$texture"
        if (Test-Path $path) {
            Write-Host "  [✓] $texture" -ForegroundColor Green
        } else {
            Write-Host "  [ ] $texture" -ForegroundColor Red
        }
    }
    
    Write-Host "`nMUSIC TRACKS (OGG format):" -ForegroundColor Cyan
    foreach ($dimension in $musicTracks.Keys) {
        Write-Host "  $dimension/:" -ForegroundColor Yellow
        foreach ($track in $musicTracks[$dimension]) {
            $path = Join-Path $AssetsRoot "sounds\music\$dimension\$track"
            if (Test-Path $path) {
                Write-Host "    [✓] $track" -ForegroundColor Green
            } else {
                Write-Host "    [ ] $track" -ForegroundColor Red
            }
        }
    }
    
    Write-Host "`nAMBIENT SOUNDS (OGG format):" -ForegroundColor Cyan
    foreach ($sound in $ambientSounds) {
        $path = Join-Path $AssetsRoot "sounds\ambient\$sound"
        if (Test-Path $path) {
            Write-Host "  [✓] $sound" -ForegroundColor Green
        } else {
            Write-Host "  [ ] $sound" -ForegroundColor Red
        }
    }
}

function Show-FreesoundSearches {
    Write-Host "`n=== FREESOUND.ORG SEARCH SUGGESTIONS ===" -ForegroundColor Yellow
    Write-Host "Visit https://freesound.org and search for these terms:" -ForegroundColor Cyan
    Write-Host ""
    
    $searches = @{
        "FIRE/LAVA SOUNDS" = @(
            "fire crackling loop",
            "lava bubbling",
            "volcanic rumble",
            "fire whoosh",
            "molten ambience"
        )
        "WATER SOUNDS" = @(
            "underwater ambience lake",
            "water flowing stream",
            "ocean waves constant",
            "bubble underwater",
            "deep water drone"
        )
        "EARTH/CAVE SOUNDS" = @(
            "cave ambience",
            "stone rumble",
            "rock slide",
            "deep cave drip",
            "cavern echo"
        )
        "AIR/WIND SOUNDS" = @(
            "wind howling",
            "air whoosh",
            "wind chimes",
            "gentle breeze",
            "sky ambience"
        )
        "SPACE/CELESTIAL SOUNDS" = @(
            "space ambience",
            "cosmic drone",
            "ethereal pad",
            "stellar atmosphere",
            "deep space hum"
        )
        "DARK/VOID SOUNDS" = @(
            "dark ambience deep bass",
            "horror ambience",
            "void drone",
            "ominous rumble",
            "deep tension"
        )
    }
    
    foreach ($category in $searches.Keys) {
        Write-Host "`n$category" -ForegroundColor Green
        foreach ($search in $searches[$category]) {
            Write-Host "  • $search" -ForegroundColor White
        }
    }
    
    Write-Host "`n"
    Write-Host "TIP: Filter by 'Creative Commons 0' license for easiest usage!" -ForegroundColor Yellow
    Write-Host "TIP: Download WAV or OGG format (use Audacity to convert if needed)" -ForegroundColor Yellow
}

function Show-TextureSearches {
    Write-Host "`n=== TEXTURE SEARCH SUGGESTIONS ===" -ForegroundColor Yellow
    Write-Host ""
    
    Write-Host "OpenGameArt.org searches:" -ForegroundColor Cyan
    $ogaSearches = @(
        "minecraft stone texture",
        "16x16 ore texture",
        "pixel crystal",
        "dark glass texture",
        "fantasy essence icons",
        "rpg item icons 16x16",
        "pixel art gems"
    )
    foreach ($search in $ogaSearches) {
        Write-Host "  • $search" -ForegroundColor White
    }
    
    Write-Host "`nPixabay.com searches:" -ForegroundColor Cyan
    $pixabaySearches = @(
        "stone texture",
        "crystal texture",
        "dark rock seamless",
        "purple crystal",
        "void background"
    )
    foreach ($search in $pixabaySearches) {
        Write-Host "  • $search" -ForegroundColor White
    }
    
    Write-Host "`nitch.io searches (game-assets/free):" -ForegroundColor Cyan
    $itchSearches = @(
        "pixel art items",
        "minecraft textures",
        "16x16 tileset",
        "fantasy icons",
        "rpg items"
    )
    foreach ($search in $itchSearches) {
        Write-Host "  • $search" -ForegroundColor White
    }
}

function New-AssetCreditsTemplate {
    $creditsPath = Join-Path $ModRoot "ASSET_CREDITS.md"
    
    if (Test-Path $creditsPath) {
        Write-Host "`nASSET_CREDITS.md already exists. Skipping..." -ForegroundColor Yellow
        return
    }
    
    $template = "# Asset Credits for Elemental Dimensions`n`n"
    $template += "This file lists all third-party assets used in this mod and their respective licenses.`n`n"
    $template += "## Textures`n`n### Block Textures`n`n"
    $template += "### Item Textures`n`n### Entity Textures`n`n"
    $template += "## Music`n`n### Firelands Music`n`n### Aquatica Music`n`n"
    $template += "### Terra Depths Music`n`n### Skyreach Music`n`n"
    $template += "### Celestial Music`n`n### Void Realm Music`n`n"
    $template += "## Sound Effects`n`n### Ambient Sounds`n`n"
    $template += "### Mob Sounds`n`n### Boss Sounds`n`n"
    $template += "---`n`nWhen you download an asset, add it here with:`n"
    $template += "- filename.ext - Author - License - Source URL`n"
    
    Set-Content -Path $creditsPath -Value $template
    Write-Host "`nCreated ASSET_CREDITS.md template" -ForegroundColor Green
}

# Main execution
if ($Help) {
    Show-Help
    exit
}

Write-Host @"
╔═══════════════════════════════════════════════════════════╗
║   Elemental Dimensions - Asset Management Tool           ║
╚═══════════════════════════════════════════════════════════╝
"@ -ForegroundColor Cyan

if ($CreateDirectories) {
    New-AssetDirectories
    New-AssetCreditsTemplate
}

Show-MissingAssets
Show-FreesoundSearches
Show-TextureSearches

Write-Host "`n=== NEXT STEPS ===" -ForegroundColor Yellow
Write-Host @"
1. Run with -CreateDirectories to set up folders:
   .\download_assets.ps1 -CreateDirectories

2. Download assets from the sites listed above

3. For audio conversion to OGG:
   - Download Audacity (free): https://www.audacityteam.org/
   - Or use online converter: https://cloudconvert.com/ogg-converter

4. Place files in the correct directories (shown above)

5. Run this script again to check progress

6. Update ASSET_CREDITS.md with proper attribution

7. Build and test your mod!

For detailed instructions, see: ASSET_ACQUISITION_GUIDE.md

"@ -ForegroundColor White

Write-Host "Script complete!" -ForegroundColor Green
