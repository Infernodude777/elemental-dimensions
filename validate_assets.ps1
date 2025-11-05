# Asset Validator Script
# Checks downloaded assets for correct format, size, and naming

param(
    [switch]$Detailed,
    [switch]$FixNames
)

$AssetsRoot = Join-Path $PSScriptRoot "src\main\resources\assets\elementaldimensions"

function Test-ImageFile {
    param($Path)
    
    if (!(Test-Path $Path)) {
        return $null
    }
    
    try {
        Add-Type -AssemblyName System.Drawing
        $img = [System.Drawing.Image]::FromFile($Path)
        $result = @{
            Width = $img.Width
            Height = $img.Height
            Format = $img.RawFormat.ToString()
            Valid = $true
        }
        $img.Dispose()
        return $result
    }
    catch {
        return @{ Valid = $false; Error = $_.Exception.Message }
    }
}

function Test-AudioFile {
    param($Path)
    
    if (!(Test-Path $Path)) {
        return $null
    }
    
    $file = Get-Item $Path
    $result = @{
        Size = $file.Length
        SizeKB = [math]::Round($file.Length / 1KB, 2)
        SizeMB = [math]::Round($file.Length / 1MB, 2)
        Extension = $file.Extension
        Valid = $file.Extension -eq ".ogg"
    }
    
    return $result
}

function Show-ValidationReport {
    Write-Host "`n╔═══════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
    Write-Host "║        ELEMENTAL DIMENSIONS ASSET VALIDATION           ║" -ForegroundColor Cyan
    Write-Host "╚═══════════════════════════════════════════════════════════╝`n" -ForegroundColor Cyan
    
    $totalAssets = 0
    $validAssets = 0
    $issues = @()
    
    # Check Block Textures
    Write-Host "BLOCK TEXTURES (Expected: 16x16 PNG)" -ForegroundColor Yellow
    $blockTextures = @(
        "void_stone.png",
        "void_crystal_ore.png",
        "void_glass.png",
        "void_pillar.png",
        "void_crystal_block.png",
        "void_portal_frame.png"
    )
    
    foreach ($texture in $blockTextures) {
        $totalAssets++
        $path = Join-Path $AssetsRoot "textures\block\$texture"
        $info = Test-ImageFile $path
        
        if ($null -eq $info) {
            Write-Host "  [ ] $texture - MISSING" -ForegroundColor Red
            $issues += "Missing block texture: $texture"
        }
        elseif (!$info.Valid) {
            Write-Host "  [!] $texture - ERROR: $($info.Error)" -ForegroundColor Red
            $issues += "Invalid block texture: $texture - $($info.Error)"
        }
        elseif ($info.Width -ne 16 -or $info.Height -ne 16) {
            Write-Host "  [!] $texture - WRONG SIZE: $($info.Width)x$($info.Height) (expected 16x16)" -ForegroundColor Yellow
            $issues += "Wrong size block texture: $texture is $($info.Width)x$($info.Height)"
            $validAssets++
        }
        else {
            Write-Host "  [✓] $texture - ${$info.Width}x$($info.Height) PNG" -ForegroundColor Green
            $validAssets++
        }
    }
    
    # Check Item Textures
    Write-Host "`nITEM TEXTURES (Expected: 16x16 PNG)" -ForegroundColor Yellow
    $itemTextures = @(
        "fire_essence.png",
        "water_essence.png",
        "earth_essence.png",
        "air_essence.png",
        "celestial_essence.png",
        "void_essence.png",
        "void_sword.png",
        "dimensional_compass.png"
    )
    
    foreach ($texture in $itemTextures) {
        $totalAssets++
        $path = Join-Path $AssetsRoot "textures\item\$texture"
        $info = Test-ImageFile $path
        
        if ($null -eq $info) {
            Write-Host "  [ ] $texture - MISSING" -ForegroundColor Red
            $issues += "Missing item texture: $texture"
        }
        elseif (!$info.Valid) {
            Write-Host "  [!] $texture - ERROR: $($info.Error)" -ForegroundColor Red
            $issues += "Invalid item texture: $texture"
        }
        elseif ($info.Width -ne 16 -or $info.Height -ne 16) {
            Write-Host "  [!] $texture - WRONG SIZE: $($info.Width)x$($info.Height) (expected 16x16)" -ForegroundColor Yellow
            $issues += "Wrong size item texture: $texture"
            $validAssets++
        }
        else {
            Write-Host "  [✓] $texture - $($info.Width)x$($info.Height) PNG" -ForegroundColor Green
            $validAssets++
        }
    }
    
    # Check Entity Textures
    Write-Host "`nENTITY TEXTURES (Expected: 64x64+ PNG)" -ForegroundColor Yellow
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
    
    foreach ($texture in $entityTextures) {
        $totalAssets++
        $path = Join-Path $AssetsRoot "textures\entity\$texture"
        $info = Test-ImageFile $path
        
        if ($null -eq $info) {
            Write-Host "  [ ] $texture - MISSING" -ForegroundColor Red
            $issues += "Missing entity texture: $texture"
        }
        elseif (!$info.Valid) {
            Write-Host "  [!] $texture - ERROR: $($info.Error)" -ForegroundColor Red
            $issues += "Invalid entity texture: $texture"
        }
        elseif ($info.Width -lt 64 -or $info.Height -lt 32) {
            Write-Host "  [!] $texture - TOO SMALL: $($info.Width)x$($info.Height) (expected 64x64 or larger)" -ForegroundColor Yellow
            $issues += "Too small entity texture: $texture"
            $validAssets++
        }
        else {
            Write-Host "  [✓] $texture - $($info.Width)x$($info.Height) PNG" -ForegroundColor Green
            $validAssets++
        }
    }
    
    # Check Music Files
    Write-Host "`nMUSIC TRACKS (Expected: OGG format)" -ForegroundColor Yellow
    $musicDimensions = @{
        "firelands" = @("infernal_echoes.ogg", "molten_fury.ogg", "blazing_heart.ogg", "flames_of_chaos.ogg")
        "aquatica" = @("echoing_currents.ogg", "abyssal_waves.ogg", "deep_tide.ogg", "ocean_whispers.ogg")
        "terra" = @("stone_heartbeat.ogg", "earthen_depths.ogg", "cavern_resonance.ogg", "ancient_roots.ogg")
        "skyreach" = @("winds_of_eternity.ogg", "sky_symphony.ogg", "cloud_dancer.ogg")
        "celestial" = @("stellar_drift.ogg", "cosmic_horizon.ogg")
        "void" = @("void_silence.ogg")
    }
    
    foreach ($dimension in $musicDimensions.Keys) {
        Write-Host "  $dimension/:" -ForegroundColor Cyan
        foreach ($track in $musicDimensions[$dimension]) {
            $totalAssets++
            $path = Join-Path $AssetsRoot "sounds\music\$dimension\$track"
            $info = Test-AudioFile $path
            
            if ($null -eq $info) {
                Write-Host "    [ ] $track - MISSING" -ForegroundColor Red
                $issues += "Missing music: $dimension/$track"
            }
            elseif (!$info.Valid) {
                Write-Host "    [!] $track - WRONG FORMAT: $($info.Extension) (expected .ogg)" -ForegroundColor Yellow
                $issues += "Wrong format music: $dimension/$track"
                $validAssets++
            }
            else {
                $sizeStr = if ($info.SizeMB -gt 1) { "$($info.SizeMB) MB" } else { "$($info.SizeKB) KB" }
                Write-Host "    [✓] $track - $sizeStr" -ForegroundColor Green
                $validAssets++
            }
        }
    }
    
    # Check Ambient Sounds
    Write-Host "`nAMBIENT SOUNDS (Expected: OGG format)" -ForegroundColor Yellow
    $ambientSounds = @(
        "firelands_ambient.ogg",
        "aquatica_ambient.ogg",
        "void_ambient.ogg"
    )
    
    foreach ($sound in $ambientSounds) {
        $totalAssets++
        $path = Join-Path $AssetsRoot "sounds\ambient\$sound"
        $info = Test-AudioFile $path
        
        if ($null -eq $info) {
            Write-Host "  [ ] $sound - MISSING" -ForegroundColor Red
            $issues += "Missing ambient sound: $sound"
        }
        elseif (!$info.Valid) {
            Write-Host "  [!] $sound - WRONG FORMAT: $($info.Extension) (expected .ogg)" -ForegroundColor Yellow
            $issues += "Wrong format ambient: $sound"
            $validAssets++
        }
        else {
            $sizeStr = if ($info.SizeMB -gt 1) { "$($info.SizeMB) MB" } else { "$($info.SizeKB) KB" }
            Write-Host "  [✓] $sound - $sizeStr" -ForegroundColor Green
            $validAssets++
        }
    }
    
    # Summary
    Write-Host "`n╔═══════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
    Write-Host "║                    VALIDATION SUMMARY                  ║" -ForegroundColor Cyan
    Write-Host "╚═══════════════════════════════════════════════════════════╝`n" -ForegroundColor Cyan
    
    $completionPercent = [math]::Round(($validAssets / $totalAssets) * 100, 1)
    
    Write-Host "Total Assets Expected: $totalAssets" -ForegroundColor White
    Write-Host "Valid Assets Found: $validAssets" -ForegroundColor Green
    Write-Host "Completion: $completionPercent%" -ForegroundColor $(if ($completionPercent -eq 100) { "Green" } elseif ($completionPercent -gt 50) { "Yellow" } else { "Red" })
    
    if ($issues.Count -gt 0) {
        Write-Host "`nISSUES FOUND ($($issues.Count)):" -ForegroundColor Yellow
        foreach ($issue in $issues) {
            Write-Host "  • $issue" -ForegroundColor Red
        }
    }
    else {
        Write-Host "`n✅ ALL ASSETS VALID! Ready to build and test!" -ForegroundColor Green
    }
    
    # Recommendations
    if ($completionPercent -lt 100) {
        Write-Host "`nRECOMMENDATIONS:" -ForegroundColor Yellow
        Write-Host "1. Run: .\download_assets.ps1 to see what's missing" -ForegroundColor White
        Write-Host "2. Check: QUICK_ASSET_LINKS.md for download sources" -ForegroundColor White
        Write-Host "3. Convert audio to OGG using Audacity or online converter" -ForegroundColor White
        Write-Host "4. Ensure textures are correct size (16x16 for blocks/items)" -ForegroundColor White
    }
}

# Main execution
Show-ValidationReport

Write-Host "`n"
