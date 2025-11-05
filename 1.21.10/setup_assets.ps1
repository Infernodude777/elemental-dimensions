# Asset Download Helper Script - Simplified Version
# Creates directories and shows what assets are needed

$ModRoot = $PSScriptRoot
$AssetsRoot = Join-Path $ModRoot "src\main\resources\assets\elementaldimensions"

Write-Host "╔═══════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║   Elemental Dimensions - Asset Setup Tool                ║" -ForegroundColor Cyan
Write-Host "╚═══════════════════════════════════════════════════════════╝`n" -ForegroundColor Cyan

# Create directories
Write-Host "Creating asset directory structure...`n" -ForegroundColor Yellow

$directories = @(
    "textures\block",
    "textures\item",
    "textures\entity",
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

foreach ($dir in $directories) {
    $fullPath = Join-Path $AssetsRoot $dir
    if (!(Test-Path $fullPath)) {
        New-Item -ItemType Directory -Path $fullPath -Force | Out-Null
        Write-Host "  [+] Created: $dir" -ForegroundColor Green
    } else {
        Write-Host "  [✓] Exists: $dir" -ForegroundColor Gray
    }
}

# Create ASSET_CREDITS.md if it doesn't exist
$creditsPath = Join-Path $ModRoot "ASSET_CREDITS.md"
if (!(Test-Path $creditsPath)) {
    $credits = "# Asset Credits`n`nAdd your asset attributions here.`n`n## Textures`n`n## Music`n`n## Sound Effects`n"
    Set-Content -Path $creditsPath -Value $credits
    Write-Host "`n  [+] Created: ASSET_CREDITS.md" -ForegroundColor Green
}

Write-Host "`n═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "WHAT YOU NEED TO DOWNLOAD" -ForegroundColor Yellow
Write-Host "═══════════════════════════════════════════════════════════`n" -ForegroundColor Cyan

Write-Host "TEXTURES (16x16 PNG):" -ForegroundColor Green
Write-Host "  Block Textures (6 files):`n" -ForegroundColor Cyan
Write-Host "    - void_stone.png"
Write-Host "    - void_crystal_ore.png"
Write-Host "    - void_glass.png"
Write-Host "    - void_pillar.png"
Write-Host "    - void_crystal_block.png"
Write-Host "    - void_portal_frame.png"

Write-Host "`n  Item Textures (8 files):`n" -ForegroundColor Cyan
Write-Host "    - fire_essence.png"
Write-Host "    - water_essence.png"
Write-Host "    - earth_essence.png"
Write-Host "    - air_essence.png"
Write-Host "    - celestial_essence.png"
Write-Host "    - void_essence.png"
Write-Host "    - void_sword.png"
Write-Host "    - dimensional_compass.png"

Write-Host "`n  Entity Textures (64x64 PNG, 8 files):`n" -ForegroundColor Cyan
Write-Host "    - void_wraith.png"
Write-Host "    - void_stalker.png"
Write-Host "    - void_guardian.png"
Write-Host "    - cinder_elemental.png"
Write-Host "    - coral_guardian.png"
Write-Host "    - cave_lurker.png"
Write-Host "    - thunder_hawk.png"
Write-Host "    - nebula_wraith.png"

Write-Host "`nMUSIC TRACKS (OGG format, 18 files):" -ForegroundColor Green
Write-Host "  Firelands (4): infernal_echoes.ogg, molten_fury.ogg, blazing_heart.ogg, flames_of_chaos.ogg"
Write-Host "  Aquatica (4): echoing_currents.ogg, abyssal_waves.ogg, deep_tide.ogg, ocean_whispers.ogg"
Write-Host "  Terra (4): stone_heartbeat.ogg, earthen_depths.ogg, cavern_resonance.ogg, ancient_roots.ogg"
Write-Host "  Skyreach (3): winds_of_eternity.ogg, sky_symphony.ogg, cloud_dancer.ogg"
Write-Host "  Celestial (2): stellar_drift.ogg, cosmic_horizon.ogg"
Write-Host "  Void (1): void_silence.ogg"

Write-Host "`nAMBIENT SOUNDS (OGG format, 3 files):" -ForegroundColor Green
Write-Host "  - firelands_ambient.ogg"
Write-Host "  - aquatica_ambient.ogg"
Write-Host "  - void_ambient.ogg"

Write-Host "`n═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "WHERE TO FIND ASSETS" -ForegroundColor Yellow
Write-Host "═══════════════════════════════════════════════════════════`n" -ForegroundColor Cyan

Write-Host "MUSIC:" -ForegroundColor Green
Write-Host "  • https://incompetech.com - Search for atmospheric/ambient tracks"
Write-Host "  • https://opengameart.org - Filter by 'Audio' category"
Write-Host "  • License: Look for CC0 or CC-BY"

Write-Host "`nSOUND EFFECTS:" -ForegroundColor Green
Write-Host "  • https://freesound.org - Search 'dark ambience', 'fire crackling', etc."
Write-Host "  • Filter by 'Creative Commons 0' for easiest use"
Write-Host "  • Download as OGG or convert using Audacity"

Write-Host "`nTEXTURES:" -ForegroundColor Green
Write-Host "  • https://opengameart.org - Search 'minecraft texture 16x16'"
Write-Host "  • https://itch.io/game-assets/free - Filter by 'Textures'"
Write-Host "  • https://pixabay.com - Free textures (resize to 16x16)"

Write-Host "`n═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "NEXT STEPS" -ForegroundColor Yellow
Write-Host "═══════════════════════════════════════════════════════════`n" -ForegroundColor Cyan

Write-Host "1. Read STEP_BY_STEP_TUTORIAL.md for a complete walkthrough"
Write-Host "2. Read QUICK_ASSET_LINKS.md for specific download links"
Write-Host "3. Download assets from the sites above"
Write-Host "4. Convert audio to OGG using Audacity (free)"
Write-Host "5. Place files in the created directories"
Write-Host "6. Run .\validate_assets.ps1 to check progress"
Write-Host "7. Build with .\gradlew build and test in-game!"

Write-Host "`n✅ Directory setup complete!" -ForegroundColor Green
Write-Host "🎯 Check the documentation files for detailed instructions!`n" -ForegroundColor Cyan
