# Script to generate all missing entity textures for Elemental Dimensions mod
Add-Type -AssemblyName System.Drawing
$outputPath = "src\main\resources\assets\elementaldimensions\textures\entity"
# Create output directory if it doesn't exist
if (-not (Test-Path $outputPath)) {
    New-Item -ItemType Directory -Path $outputPath -Force
}
# Helper function to create entity texture (64x64 for mobs, 128x128 for bosses)
function New-EntityTexture {
    param(
        [string]$fileName,
        [int]$width = 64,
        [int]$height = 64,
        [System.Drawing.Color]$primaryColor,
        [System.Drawing.Color]$secondaryColor,
        [System.Drawing.Color]$accentColor,
        [string]$pattern = "humanoid"
    )
    $bitmap = New-Object System.Drawing.Bitmap($width, $height)
    $graphics = [System.Drawing.Graphics]::FromImage($bitmap)
    $graphics.Clear([System.Drawing.Color]::Transparent)
    $random = New-Object System.Random($fileName.GetHashCode())
    if ($pattern -eq "humanoid") {
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 8, 0, 16, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 10, 4, 4, 4)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 18, 4, 4, 4)
        # Body
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 10, 16, 12, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 12, 18, 8, 12)
        # Arms
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 4, 16, 6, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 22, 16, 6, 16)
        # Legs
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 10, 32, 6, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 16, 32, 6, 16)
    }
    elseif ($pattern -eq "quadruped") {
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 8, 4, 16, 12)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 10, 6, 4, 4)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 18, 6, 4, 4)
        # Body
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 6, 16, 20, 16)
        # Legs (4 legs)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 8, 32, 4, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 14, 32, 4, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 20, 32, 4, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 26, 32, 4, 16)
    }
    elseif ($pattern -eq "floating") {
        # Spherical wisp body
        $graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush($primaryColor)), 12, 12, 24, 24)
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush($secondaryColor)), 16, 16, 16, 16)
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush($accentColor)), 20, 20, 8, 8)
        # Add glow effect particles around it
        for ($i = 0; $i -lt 12; $i++) {
            $angle = ($i * 30) * [Math]::PI / 180
            $x = 24 + [int](20 * [Math]::Cos($angle))
            $y = 24 + [int](20 * [Math]::Sin($angle))
            $graphics.FillEllipse((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(150, $accentColor.R, $accentColor.G, $accentColor.B))), $x, $y, 3, 3)
        }
    }
    elseif ($pattern -eq "serpent") {
        # Snake-like body segments
        for ($i = 0; $i -lt 8; $i++) {
            $y = 4 + ($i * 6)
            $xOffset = [int](4 * [Math]::Sin($i))
            $color = if ($i % 2 -eq 0) { $primaryColor } else { $secondaryColor }
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($color)), 16 + $xOffset, $y, 12, 6)
        }
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 18, 0, 8, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::Red)), 20, 2, 2, 2)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::Red)), 24, 2, 2, 2)
    }
    elseif ($pattern -eq "flying") {
        # Bird-like body
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 14, 12, 12, 12)
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 18, 8, 8, 6)
        # Wings
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 2, 14, 12, 8)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 26, 14, 12, 8)
        # Tail
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 16, 24, 8, 12)
    }
    elseif ($pattern -eq "aquatic") {
        # Fish-like body
        $graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
        # Body
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush($primaryColor)), 10, 16, 24, 16)
        # Fins
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 6, 20, 4, 8)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 34, 20, 4, 8)
        # Tail
        $points = @(
            (New-Object System.Drawing.Point(34, 24)),
            (New-Object System.Drawing.Point(44, 18)),
            (New-Object System.Drawing.Point(44, 30))
        )
        $graphics.FillPolygon((New-Object System.Drawing.SolidBrush($accentColor)), $points)
        # Eyes
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::White)), 14, 20, 4, 4)
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::Black)), 15, 21, 2, 2)
    }
    elseif ($pattern -eq "boss") {
        # Large imposing figure (humanoid boss)
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 12, 4, 24, 24)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 16, 12, 6, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 26, 12, 6, 6)
        # Body - larger
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 10, 28, 28, 36)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 14, 32, 20, 28)
        # Arms - thicker
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 2, 28, 8, 32)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 38, 28, 8, 32)
        # Legs - thicker
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 12, 64, 10, 24)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 26, 64, 10, 24)
        # Crown/horns
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 10, 0, 4, 8)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 34, 0, 4, 8)
    }
    elseif ($pattern -eq "boss_large") {
        # Massive creature (colossus/leviathan)
        # Central body
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 8, 20, 48, 48)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 12, 24, 40, 40)
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 20, 8, 24, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::Red)), 24, 12, 6, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::Red)), 34, 12, 6, 6)
        # Limbs
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 0, 32, 8, 32)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 56, 32, 8, 32)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 16, 68, 12, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 36, 68, 12, 16)
        # Spikes/details
        for ($i = 0; $i -lt 6; $i++) {
            $x = 12 + ($i * 8)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), $x, 16, 4, 8)
        }
    }
    elseif ($pattern -eq "spider") {
        # Spider body
        # Abdomen
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 12, 20, 24, 16)
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 16, 12, 16, 10)
        # Eyes
        for ($i = 0; $i -lt 4; $i++) {
            $x = 18 + ($i * 4)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::Red)), $x, 14, 2, 2)
        }
        # Legs (8 legs)
        for ($i = 0; $i -lt 4; $i++) {
            $y = 22 + ($i * 4)
            # Left legs
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 4, $y, 8, 2)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 0, $y + 2, 4, 8)
            # Right legs
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 36, $y, 8, 2)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 44, $y + 2, 4, 8)
        }
    }
    elseif ($pattern -eq "crab") {
        # Crab body
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 10, 20, 28, 16)
        # Shell details
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 12, 22, 24, 12)
        # Eyes on stalks
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 14, 16, 3, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 31, 16, 3, 6)
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::White)), 13, 14, 5, 4)
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::White)), 30, 14, 5, 4)
        # Claws
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 2, 24, 8, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 38, 24, 8, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 0, 22, 4, 4)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 44, 22, 4, 4)
        # Legs
        for ($i = 0; $i -lt 3; $i++) {
            $y = 28 + ($i * 4)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 6, $y, 4, 2)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 38, $y, 4, 2)
        }
    }
    elseif ($pattern -eq "golem") {
        # Large stone-like humanoid
        # Head
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 10, 2, 20, 16)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 14, 8, 4, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), 22, 8, 4, 6)
        # Broad shoulders and body
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 6, 18, 28, 24)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 10, 22, 20, 18)
        # Thick arms
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 0, 18, 6, 24)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 34, 18, 6, 24)
        # Legs
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 10, 42, 8, 22)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 22, 42, 8, 22)
        # Add stone texture
        for ($i = 0; $i -lt 30; $i++) {
            $x = $random.Next(6, 34)
            $y = $random.Next(18, 62)
            $darkness = $random.Next(-20, 20)
            $r = [Math]::Max(0, [Math]::Min(255, $primaryColor.R + $darkness))
            $g = [Math]::Max(0, [Math]::Min(255, $primaryColor.G + $darkness))
            $b = [Math]::Max(0, [Math]::Min(255, $primaryColor.B + $darkness))
            $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($r, $g, $b))
        }
    }
    $graphics.Dispose()
    $fullPath = Join-Path $outputPath $fileName
    $bitmap.Save($fullPath, [System.Drawing.Imaging.ImageFormat]::Png)
    $bitmap.Dispose()
    Write-Output "Created: $fileName"
}
Write-Output "`n=== Generating Firelands Mob Textures ===" -ForegroundColor Yellow
New-EntityTexture "flame_sprite.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(255, 150, 0)) `
    ([System.Drawing.Color]::FromArgb(255, 200, 50)) `
    ([System.Drawing.Color]::FromArgb(255, 100, 0)) `
    "floating"
New-EntityTexture "magma_brute.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(150, 50, 30)) `
    ([System.Drawing.Color]::FromArgb(200, 80, 40)) `
    ([System.Drawing.Color]::FromArgb(255, 120, 0)) `
    "humanoid"
New-EntityTexture "ember_serpent.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(200, 80, 0)) `
    ([System.Drawing.Color]::FromArgb(255, 150, 50)) `
    ([System.Drawing.Color]::FromArgb(255, 200, 100)) `
    "serpent"
New-EntityTexture "pyre_howler.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(180, 60, 40)) `
    ([System.Drawing.Color]::FromArgb(220, 100, 60)) `
    ([System.Drawing.Color]::FromArgb(255, 180, 100)) `
    "quadruped"
Write-Output "`n=== Generating Aquatica Mob Textures ===" -ForegroundColor Cyan
New-EntityTexture "abyss_crab.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(80, 60, 100)) `
    ([System.Drawing.Color]::FromArgb(120, 100, 140)) `
    ([System.Drawing.Color]::FromArgb(160, 140, 180)) `
    "crab"
New-EntityTexture "tide_siren.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(60, 150, 200)) `
    ([System.Drawing.Color]::FromArgb(100, 180, 220)) `
    ([System.Drawing.Color]::FromArgb(140, 210, 240)) `
    "humanoid"
New-EntityTexture "bubble_wisp.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(150, 200, 255)) `
    ([System.Drawing.Color]::FromArgb(180, 220, 255)) `
    ([System.Drawing.Color]::FromArgb(200, 240, 255)) `
    "floating"
New-EntityTexture "pressure_ray.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(80, 120, 160)) `
    ([System.Drawing.Color]::FromArgb(100, 150, 190)) `
    ([System.Drawing.Color]::FromArgb(120, 170, 210)) `
    "aquatic"
New-EntityTexture "coral_guardian.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(200, 100, 140)) `
    ([System.Drawing.Color]::FromArgb(220, 140, 180)) `
    ([System.Drawing.Color]::FromArgb(240, 180, 200)) `
    "humanoid"
Write-Output "`n=== Generating Terra Depths Mob Textures ===" -ForegroundColor Green
New-EntityTexture "stone_golem.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(120, 110, 100)) `
    ([System.Drawing.Color]::FromArgb(140, 130, 120)) `
    ([System.Drawing.Color]::FromArgb(100, 200, 120)) `
    "golem"
New-EntityTexture "moss_beast.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(80, 120, 60)) `
    ([System.Drawing.Color]::FromArgb(100, 150, 80)) `
    ([System.Drawing.Color]::FromArgb(120, 180, 100)) `
    "quadruped"
New-EntityTexture "crystal_mite.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(180, 140, 200)) `
    ([System.Drawing.Color]::FromArgb(200, 160, 220)) `
    ([System.Drawing.Color]::FromArgb(220, 180, 240)) `
    "spider"
New-EntityTexture "root_spider.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(100, 80, 60)) `
    ([System.Drawing.Color]::FromArgb(130, 110, 90)) `
    ([System.Drawing.Color]::FromArgb(160, 140, 120)) `
    "spider"
New-EntityTexture "cave_lurker.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(60, 60, 70)) `
    ([System.Drawing.Color]::FromArgb(80, 80, 90)) `
    ([System.Drawing.Color]::FromArgb(120, 120, 140)) `
    "humanoid"
Write-Output "`n=== Generating Skyreach Peaks Mob Textures ===" -ForegroundColor White
New-EntityTexture "wind_hawk.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(200, 220, 240)) `
    ([System.Drawing.Color]::FromArgb(220, 235, 250)) `
    ([System.Drawing.Color]::FromArgb(180, 200, 230)) `
    "flying"
New-EntityTexture "sky_wisp.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(220, 240, 255)) `
    ([System.Drawing.Color]::FromArgb(240, 250, 255)) `
    ([System.Drawing.Color]::FromArgb(200, 230, 255)) `
    "floating"
New-EntityTexture "cloud_sentinel.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(240, 240, 250)) `
    ([System.Drawing.Color]::FromArgb(220, 230, 245)) `
    ([System.Drawing.Color]::FromArgb(200, 220, 240)) `
    "humanoid"
New-EntityTexture "petal_sprite.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(255, 200, 220)) `
    ([System.Drawing.Color]::FromArgb(255, 220, 235)) `
    ([System.Drawing.Color]::FromArgb(255, 180, 200)) `
    "floating"
New-EntityTexture "thunder_hawk.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(180, 200, 230)) `
    ([System.Drawing.Color]::FromArgb(200, 220, 245)) `
    ([System.Drawing.Color]::FromArgb(255, 255, 100)) `
    "flying"
Write-Output "`n=== Generating Celestial Mob Textures ===" -ForegroundColor Magenta
New-EntityTexture "celestial_enderman.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(100, 80, 140)) `
    ([System.Drawing.Color]::FromArgb(140, 120, 180)) `
    ([System.Drawing.Color]::FromArgb(180, 160, 220)) `
    "humanoid"
New-EntityTexture "void_shulker.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(80, 60, 100)) `
    ([System.Drawing.Color]::FromArgb(120, 100, 140)) `
    ([System.Drawing.Color]::FromArgb(160, 140, 180)) `
    "quadruped"
New-EntityTexture "starlight_phantom.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(150, 180, 255)) `
    ([System.Drawing.Color]::FromArgb(180, 200, 255)) `
    ([System.Drawing.Color]::FromArgb(255, 255, 255)) `
    "flying"
New-EntityTexture "nebula_wraith.png" 64 64 `
    ([System.Drawing.Color]::FromArgb(120, 100, 160)) `
    ([System.Drawing.Color]::FromArgb(160, 140, 200)) `
    ([System.Drawing.Color]::FromArgb(200, 180, 240)) `
    "humanoid"
Write-Output "`n=== Generating Boss Textures (128x128) ===" -ForegroundColor Red
New-EntityTexture "fire_titan.png" 128 128 `
    ([System.Drawing.Color]::FromArgb(200, 60, 0)) `
    ([System.Drawing.Color]::FromArgb(255, 120, 30)) `
    ([System.Drawing.Color]::FromArgb(255, 200, 100)) `
    "boss"
New-EntityTexture "abyss_leviathan.png" 128 128 `
    ([System.Drawing.Color]::FromArgb(40, 60, 100)) `
    ([System.Drawing.Color]::FromArgb(60, 100, 140)) `
    ([System.Drawing.Color]::FromArgb(100, 150, 200)) `
    "boss_large"
New-EntityTexture "stone_colossus.png" 128 128 `
    ([System.Drawing.Color]::FromArgb(100, 90, 80)) `
    ([System.Drawing.Color]::FromArgb(130, 120, 110)) `
    ([System.Drawing.Color]::FromArgb(120, 200, 100)) `
    "boss_large"
New-EntityTexture "wind_seraph.png" 128 128 `
    ([System.Drawing.Color]::FromArgb(220, 240, 255)) `
    ([System.Drawing.Color]::FromArgb(240, 250, 255)) `
    ([System.Drawing.Color]::FromArgb(255, 255, 150)) `
    "boss"
New-EntityTexture "elemental_primarch.png" 128 128 `
    ([System.Drawing.Color]::FromArgb(200, 150, 100)) `
    ([System.Drawing.Color]::FromArgb(255, 200, 150)) `
    ([System.Drawing.Color]::FromArgb(255, 255, 200)) `
    "boss"
Write-Output "`n=== Entity Texture Generation Complete! ===" -ForegroundColor Green
Write-Output "Total textures created: 28" -ForegroundColor White
Write-Output "  - Regular mobs: 23" -ForegroundColor Gray
Write-Output "  - Bosses: 5" -ForegroundColor Gray
Write-Output "Output directory: $outputPath" -ForegroundColor White