# Script to generate missing item and armor textures for Elemental Dimensions mod
Add-Type -AssemblyName System.Drawing
$outputPath = "src\main\resources\assets\elementaldimensions\textures\item"
# Create output directory if it doesn't exist
if (-not (Test-Path $outputPath)) {
    New-Item -ItemType Directory -Path $outputPath -Force
}
# Helper function to create item texture with gradient and details
function New-ItemTexture {
    param(
        [string]$fileName,
        [System.Drawing.Color]$primaryColor,
        [System.Drawing.Color]$secondaryColor,
        [string]$pattern = "gradient"
    )
    $bitmap = New-Object System.Drawing.Bitmap(16, 16)
    $graphics = [System.Drawing.Graphics]::FromImage($bitmap)
    $graphics.Clear([System.Drawing.Color]::Transparent)
    if ($pattern -eq "gradient") {
        # Create vertical gradient
        $brush = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
            (New-Object System.Drawing.Point(0, 0)),
            (New-Object System.Drawing.Point(0, 16)),
            $primaryColor,
            $secondaryColor
        )
        $graphics.FillRectangle($brush, 4, 2, 8, 12)
        $brush.Dispose()
    }
    elseif ($pattern -eq "crystal") {
        # Diamond crystal shape
        $points = @(
            (New-Object System.Drawing.Point(8, 2)),
            (New-Object System.Drawing.Point(12, 6)),
            (New-Object System.Drawing.Point(8, 14)),
            (New-Object System.Drawing.Point(4, 6))
        )
        $graphics.FillPolygon((New-Object System.Drawing.SolidBrush($primaryColor)), $points)
        # Add highlights
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 7, 4, 2, 3)
    }
    elseif ($pattern -eq "shard") {
        # Jagged shard
        for ($i = 0; $i -lt 4; $i++) {
            $y = 3 + $i * 3
            $width = 8 - $i
            $x = 4 + [int]($i / 2)
            $color = if ($i % 2 -eq 0) { $primaryColor } else { $secondaryColor }
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($color)), $x, $y, $width, 2)
        }
    }
    elseif ($pattern -eq "ingot") {
        # Ingot shape with metallic look
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 3, 5, 10, 6)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 4, 6, 8, 2)
        # Top bevel
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 4, 4, 8, 1)
    }
    elseif ($pattern -eq "dust") {
        # Scattered dust particles
        $random = New-Object System.Random
        for ($i = 0; $i -lt 25; $i++) {
            $x = $random.Next(2, 14)
            $y = $random.Next(2, 14)
            $color = if ($random.Next(0, 2) -eq 0) { $primaryColor } else { $secondaryColor }
            $bitmap.SetPixel($x, $y, $color)
        }
    }
    elseif ($pattern -eq "pearl") {
        # Round pearl
        $graphics.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush($primaryColor)), 5, 5, 6, 6)
        $graphics.FillEllipse((New-Object System.Drawing.SolidBrush($secondaryColor)), 6, 6, 2, 2)
    }
    elseif ($pattern -eq "feather") {
        # Feather shape
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 7, 3, 2, 10)
        for ($i = 0; $i -lt 5; $i++) {
            $y = 4 + $i * 2
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 5, $y, 2, 1)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 9, $y, 2, 1)
        }
    }
    elseif ($pattern -eq "armor") {
        # Helmet/armor piece
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 4, 4, 8, 8)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 5, 5, 6, 3)
        # Eye slots for helmet
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(40, 40, 60))), 6, 7, 1, 1)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(40, 40, 60))), 9, 7, 1, 1)
    }
    elseif ($pattern -eq "rune") {
        # Rune tablet
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($primaryColor)), 4, 3, 8, 10)
        # Rune symbols
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 6, 5, 4, 1)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 7, 6, 2, 3)
        $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($secondaryColor)), 6, 10, 4, 1)
    }
    # Add border/outline
    $pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(50, 0, 0, 0), 1)
    $graphics.DrawRectangle($pen, 0, 0, 15, 15)
    $graphics.Dispose()
    $pen.Dispose()
    $fullPath = Join-Path $outputPath $fileName
    $bitmap.Save($fullPath, [System.Drawing.Imaging.ImageFormat]::Png)
    $bitmap.Dispose()
    Write-Output "Created: $fileName"
}
Write-Output "Generating Firelands item textures..."
# Firelands already has most textures, check for missing ones
Write-Output "`nGenerating Aquatica item textures..."
New-ItemTexture "water_ingot.png" ([System.Drawing.Color]::FromArgb(60, 150, 220)) ([System.Drawing.Color]::FromArgb(140, 200, 255)) "ingot"
Write-Output "`nGenerating Terra Depths item textures..."
New-ItemTexture "earth_ingot.png" ([System.Drawing.Color]::FromArgb(100, 80, 50)) ([System.Drawing.Color]::FromArgb(150, 120, 80)) "ingot"
Write-Output "`nGenerating Skyreach Peaks item textures..."
New-ItemTexture "air_ingot.png" ([System.Drawing.Color]::FromArgb(200, 230, 255)) ([System.Drawing.Color]::FromArgb(240, 250, 255)) "ingot"
Write-Output "`nGenerating essence textures..."
New-ItemTexture "fire_essence.png" ([System.Drawing.Color]::FromArgb(255, 100, 0)) ([System.Drawing.Color]::FromArgb(255, 200, 0)) "crystal"
New-ItemTexture "water_essence.png" ([System.Drawing.Color]::FromArgb(0, 100, 255)) ([System.Drawing.Color]::FromArgb(100, 200, 255)) "crystal"
New-ItemTexture "earth_essence.png" ([System.Drawing.Color]::FromArgb(100, 150, 50)) ([System.Drawing.Color]::FromArgb(150, 200, 100)) "crystal"
New-ItemTexture "air_essence.png" ([System.Drawing.Color]::FromArgb(200, 230, 255)) ([System.Drawing.Color]::FromArgb(255, 255, 255)) "crystal"
Write-Output "`nGenerating remaining Firelands items..."
New-ItemTexture "lava_crystal.png" ([System.Drawing.Color]::FromArgb(255, 80, 0)) ([System.Drawing.Color]::FromArgb(255, 200, 100)) "crystal"
New-ItemTexture "ember_dust.png" ([System.Drawing.Color]::FromArgb(255, 120, 0)) ([System.Drawing.Color]::FromArgb(200, 80, 0)) "dust"
New-ItemTexture "magma_core.png" ([System.Drawing.Color]::FromArgb(200, 50, 0)) ([System.Drawing.Color]::FromArgb(255, 150, 0)) "crystal"
New-ItemTexture "fire_ingot.png" ([System.Drawing.Color]::FromArgb(255, 100, 0)) ([System.Drawing.Color]::FromArgb(255, 200, 100)) "ingot"
New-ItemTexture "ember_crystal.png" ([System.Drawing.Color]::FromArgb(255, 150, 50)) ([System.Drawing.Color]::FromArgb(255, 220, 150)) "crystal"
Write-Output "`nGenerating remaining Aquatica items..."
New-ItemTexture "aqua_crystal.png" ([System.Drawing.Color]::FromArgb(0, 150, 255)) ([System.Drawing.Color]::FromArgb(100, 200, 255)) "crystal"
New-ItemTexture "crab_meat.png" ([System.Drawing.Color]::FromArgb(255, 180, 150)) ([System.Drawing.Color]::FromArgb(255, 200, 180)) "gradient"
New-ItemTexture "siren_scale.png" ([System.Drawing.Color]::FromArgb(80, 200, 220)) ([System.Drawing.Color]::FromArgb(150, 230, 255)) "shard"
New-ItemTexture "aqua_pearl.png" ([System.Drawing.Color]::FromArgb(200, 240, 255)) ([System.Drawing.Color]::FromArgb(255, 255, 255)) "pearl"
Write-Output "`nGenerating remaining Terra Depths items..."
New-ItemTexture "gaia_shard.png" ([System.Drawing.Color]::FromArgb(80, 150, 60)) ([System.Drawing.Color]::FromArgb(120, 200, 100)) "shard"
New-ItemTexture "crystal_frag.png" ([System.Drawing.Color]::FromArgb(150, 100, 200)) ([System.Drawing.Color]::FromArgb(200, 150, 255)) "shard"
Write-Output "`nGenerating remaining Skyreach items..."
New-ItemTexture "zephyr_shard.png" ([System.Drawing.Color]::FromArgb(180, 220, 255)) ([System.Drawing.Color]::FromArgb(220, 240, 255)) "shard"
New-ItemTexture "zephyr_feather.png" ([System.Drawing.Color]::FromArgb(220, 240, 255)) ([System.Drawing.Color]::FromArgb(255, 255, 255)) "feather"
Write-Output "`nGenerating Celestial items..."
New-ItemTexture "void_shard.png" ([System.Drawing.Color]::FromArgb(80, 60, 120)) ([System.Drawing.Color]::FromArgb(150, 120, 200)) "shard"
New-ItemTexture "celestial_crystal.png" ([System.Drawing.Color]::FromArgb(200, 180, 255)) ([System.Drawing.Color]::FromArgb(255, 240, 255)) "crystal"
New-ItemTexture "elemental_heart.png" ([System.Drawing.Color]::FromArgb(255, 100, 150)) ([System.Drawing.Color]::FromArgb(255, 200, 220)) "crystal"
New-ItemTexture "primarch_crown.png" ([System.Drawing.Color]::FromArgb(255, 215, 0)) ([System.Drawing.Color]::FromArgb(255, 255, 150)) "gradient"
Write-Output "`nGenerating Celestial Armor textures..."
New-ItemTexture "celestial_helmet.png" ([System.Drawing.Color]::FromArgb(180, 150, 255)) ([System.Drawing.Color]::FromArgb(220, 200, 255)) "armor"
New-ItemTexture "celestial_chestplate.png" ([System.Drawing.Color]::FromArgb(180, 150, 255)) ([System.Drawing.Color]::FromArgb(220, 200, 255)) "armor"
New-ItemTexture "celestial_leggings.png" ([System.Drawing.Color]::FromArgb(180, 150, 255)) ([System.Drawing.Color]::FromArgb(220, 200, 255)) "armor"
New-ItemTexture "celestial_boots.png" ([System.Drawing.Color]::FromArgb(180, 150, 255)) ([System.Drawing.Color]::FromArgb(220, 200, 255)) "armor"
Write-Output "`nGenerating Altar Rune textures..."
New-ItemTexture "fire_altar_rune.png" ([System.Drawing.Color]::FromArgb(150, 50, 50)) ([System.Drawing.Color]::FromArgb(255, 150, 0)) "rune"
New-ItemTexture "water_altar_rune.png" ([System.Drawing.Color]::FromArgb(50, 100, 150)) ([System.Drawing.Color]::FromArgb(100, 200, 255)) "rune"
New-ItemTexture "earth_altar_rune.png" ([System.Drawing.Color]::FromArgb(80, 100, 50)) ([System.Drawing.Color]::FromArgb(150, 200, 100)) "rune"
New-ItemTexture "air_altar_rune.png" ([System.Drawing.Color]::FromArgb(150, 200, 230)) ([System.Drawing.Color]::FromArgb(220, 240, 255)) "rune"
New-ItemTexture "celestial_altar_rune.png" ([System.Drawing.Color]::FromArgb(100, 80, 150)) ([System.Drawing.Color]::FromArgb(200, 180, 255)) "rune"
Write-Output "`nGenerating Dimensional Compass texture..."
New-ItemTexture "dimensional_compass.png" ([System.Drawing.Color]::FromArgb(180, 140, 80)) ([System.Drawing.Color]::FromArgb(255, 215, 0)) "gradient"
Write-Output "`nAll dimension item textures generated!"
Write-Output "Total textures created in: $outputPath"