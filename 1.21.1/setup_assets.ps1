# Asset Download Helper Script - Simplified Version
# Creates directories and shows what assets are needed
$ModRoot = $PSScriptRoot
$AssetsRoot = Join-Path $ModRoot "src\main\resources\assets\elementaldimensions"
Write-Output "╔═══════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Output "║   Elemental Dimensions - Asset Setup Tool                ║" -ForegroundColor Cyan
Write-Output "╚═══════════════════════════════════════════════════════════╝`n" -ForegroundColor Cyan
# Create directories
Write-Output "Creating asset directory structure...`n" -ForegroundColor Yellow
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
        Write-Output "  [+] Created: $dir" -ForegroundColor Green
    } else {
        Write-Output "  [✓] Exists: $dir" -ForegroundColor Gray
    }
}
# Create ASSET_CREDITS.md if it doesn't exist
$creditsPath = Join-Path $ModRoot "ASSET_CREDITS.md"
if (!(Test-Path $creditsPath)) {
    $credits = "# Asset Credits`n`nAdd your asset attributions here.`n`n## Textures`n`n## Music`n`n## Sound Effects`n"
    Set-Content -Path $creditsPath -Value $credits
    Write-Output "`n  [+] Created: ASSET_CREDITS.md" -ForegroundColor Green
}
Write-Output "`n═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Output "WHAT YOU NEED TO DOWNLOAD" -ForegroundColor Yellow
Write-Output "═══════════════════════════════════════════════════════════`n" -ForegroundColor Cyan
Write-Output "TEXTURES (16x16 PNG):" -ForegroundColor Green
Write-Output "  Block Textures (6 files):`n" -ForegroundColor Cyan
Write-Output "    - void_stone.png"
Write-Output "    - void_crystal_ore.png"
Write-Output "    - void_glass.png"
Write-Output "    - void_pillar.png"
Write-Output "    - void_crystal_block.png"
Write-Output "    - void_portal_frame.png"
Write-Output "`n  Item Textures (8 files):`n" -ForegroundColor Cyan
Write-Output "    - fire_essence.png"
Write-Output "    - water_essence.png"
Write-Output "    - earth_essence.png"
Write-Output "    - air_essence.png"
Write-Output "    - celestial_essence.png"
Write-Output "    - void_essence.png"
Write-Output "    - void_sword.png"
Write-Output "    - dimensional_compass.png"
Write-Output "`n  Entity Textures (64x64 PNG, 8 files):`n" -ForegroundColor Cyan
Write-Output "    - void_wraith.png"
Write-Output "    - void_stalker.png"
Write-Output "    - void_guardian.png"
Write-Output "    - cinder_elemental.png"
Write-Output "    - coral_guardian.png"
Write-Output "    - cave_lurker.png"
Write-Output "    - thunder_hawk.png"
Write-Output "    - nebula_wraith.png"
Write-Output "`nMUSIC TRACKS (OGG format, 18 files):" -ForegroundColor Green
Write-Output "  Firelands (4): infernal_echoes.ogg, molten_fury.ogg, blazing_heart.ogg, flames_of_chaos.ogg"
Write-Output "  Aquatica (4): echoing_currents.ogg, abyssal_waves.ogg, deep_tide.ogg, ocean_whispers.ogg"
Write-Output "  Terra (4): stone_heartbeat.ogg, earthen_depths.ogg, cavern_resonance.ogg, ancient_roots.ogg"
Write-Output "  Skyreach (3): winds_of_eternity.ogg, sky_symphony.ogg, cloud_dancer.ogg"
Write-Output "  Celestial (2): stellar_drift.ogg, cosmic_horizon.ogg"
Write-Output "  Void (1): void_silence.ogg"
Write-Output "`nAMBIENT SOUNDS (OGG format, 3 files):" -ForegroundColor Green
Write-Output "  - firelands_ambient.ogg"
Write-Output "  - aquatica_ambient.ogg"
Write-Output "  - void_ambient.ogg"
Write-Output "`n═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Output "WHERE TO FIND ASSETS" -ForegroundColor Yellow
Write-Output "═══════════════════════════════════════════════════════════`n" -ForegroundColor Cyan
Write-Output "MUSIC:" -ForegroundColor Green
Write-Output "  • https://incompetech.com - Search for atmospheric/ambient tracks"
Write-Output "  • https://opengameart.org - Filter by 'Audio' category"
Write-Output "  • License: Look for CC0 or CC-BY"
Write-Output "`nSOUND EFFECTS:" -ForegroundColor Green
Write-Output "  • https://freesound.org - Search 'dark ambience', 'fire crackling', etc."
Write-Output "  • Filter by 'Creative Commons 0' for easiest use"
Write-Output "  • Download as OGG or convert using Audacity"
Write-Output "`nTEXTURES:" -ForegroundColor Green
Write-Output "  • https://opengameart.org - Search 'minecraft texture 16x16'"
Write-Output "  • https://itch.io/game-assets/free - Filter by 'Textures'"
Write-Output "  • https://pixabay.com - Free textures (resize to 16x16)"
Write-Output "`n═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Output "NEXT STEPS" -ForegroundColor Yellow
Write-Output "═══════════════════════════════════════════════════════════`n" -ForegroundColor Cyan
Write-Output "1. Read STEP_BY_STEP_TUTORIAL.md for a complete walkthrough"
Write-Output "2. Read QUICK_ASSET_LINKS.md for specific download links"
Write-Output "3. Download assets from the sites above"
Write-Output "4. Convert audio to OGG using Audacity (free)"
Write-Output "5. Place files in the created directories"
Write-Output "6. Run .\validate_assets.ps1 to check progress"
Write-Output "7. Build with .\gradlew build and test in-game!"
Write-Output "`n✅ Directory setup complete!" -ForegroundColor Green
Write-Output "🎯 Check the documentation files for detailed instructions!`n" -ForegroundColor Cyan