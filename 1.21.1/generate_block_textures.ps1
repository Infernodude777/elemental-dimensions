# Script to generate missing block textures for Elemental Dimensions mod
Add-Type -AssemblyName System.Drawing

$outputPath = "src\main\resources\assets\elementaldimensions\textures\block"

# Create output directory if it doesn't exist
if (-not (Test-Path $outputPath)) {
    New-Item -ItemType Directory -Path $outputPath -Force
}

# Helper function to create block texture with pattern
function New-BlockTexture {
    param(
        [string]$fileName,
        [System.Drawing.Color]$baseColor,
        [System.Drawing.Color]$accentColor,
        [string]$pattern = "stone"
    )
    
    $bitmap = New-Object System.Drawing.Bitmap(16, 16)
    $graphics = [System.Drawing.Graphics]::FromImage($bitmap)
    $graphics.Clear($baseColor)
    
    $random = New-Object System.Random($fileName.GetHashCode())
    
    if ($pattern -eq "stone") {
        # Stone texture with noise
        for ($x = 0; $x -lt 16; $x++) {
            for ($y = 0; $y -lt 16; $y++) {
                if ($random.Next(0, 4) -eq 0) {
                    $darkness = $random.Next(-20, 20)
                    $r = [Math]::Max(0, [Math]::Min(255, $baseColor.R + $darkness))
                    $g = [Math]::Max(0, [Math]::Min(255, $baseColor.G + $darkness))
                    $b = [Math]::Max(0, [Math]::Min(255, $baseColor.B + $darkness))
                    $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($r, $g, $b))
                }
            }
        }
    }
    elseif ($pattern -eq "ore") {
        # Base stone with ore spots
        for ($x = 0; $x -lt 16; $x++) {
            for ($y = 0; $y -lt 16; $y++) {
                if ($random.Next(0, 4) -eq 0) {
                    $darkness = $random.Next(-20, 20)
                    $r = [Math]::Max(0, [Math]::Min(255, $baseColor.R + $darkness))
                    $g = [Math]::Max(0, [Math]::Min(255, $baseColor.G + $darkness))
                    $b = [Math]::Max(0, [Math]::Min(255, $baseColor.B + $darkness))
                    $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($r, $g, $b))
                }
            }
        }
        # Add ore veins
        for ($i = 0; $i -lt 8; $i++) {
            $x = $random.Next(0, 16)
            $y = $random.Next(0, 16)
            $bitmap.SetPixel($x, $y, $accentColor)
            if ($x + 1 -lt 16) { $bitmap.SetPixel($x + 1, $y, $accentColor) }
            if ($y + 1 -lt 16) { $bitmap.SetPixel($x, $y + 1, $accentColor) }
        }
    }
    elseif ($pattern -eq "glass") {
        # Transparent glass with subtle pattern
        $graphics.Clear([System.Drawing.Color]::FromArgb(180, $baseColor.R, $baseColor.G, $baseColor.B))
        for ($i = 0; $i -lt 4; $i++) {
            $x = $random.Next(2, 14)
            $y = $random.Next(2, 14)
            $graphics.FillRectangle(
                (New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(100, $accentColor.R, $accentColor.G, $accentColor.B))),
                $x, $y, 2, 2
            )
        }
    }
    elseif ($pattern -eq "sand") {
        # Sandy texture
        for ($x = 0; $x -lt 16; $x++) {
            for ($y = 0; $y -lt 16; $y++) {
                $variance = $random.Next(-15, 15)
                $r = [Math]::Max(0, [Math]::Min(255, $baseColor.R + $variance))
                $g = [Math]::Max(0, [Math]::Min(255, $baseColor.G + $variance))
                $b = [Math]::Max(0, [Math]::Min(255, $baseColor.B + $variance))
                $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($r, $g, $b))
            }
        }
    }
    elseif ($pattern -eq "grass") {
        # Grass-like texture with variation
        for ($x = 0; $x -lt 16; $x++) {
            for ($y = 0; $y -lt 16; $y++) {
                $variance = $random.Next(-25, 25)
                $r = [Math]::Max(0, [Math]::Min(255, $baseColor.R + $variance))
                $g = [Math]::Max(0, [Math]::Min(255, $baseColor.G + $variance))
                $b = [Math]::Max(0, [Math]::Min(255, $baseColor.B + $variance))
                $bitmap.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($r, $g, $b))
            }
        }
        # Add some accent spots
        for ($i = 0; $i -lt 12; $i++) {
            $x = $random.Next(0, 16)
            $y = $random.Next(0, 16)
            $bitmap.SetPixel($x, $y, $accentColor)
        }
    }
    elseif ($pattern -eq "brick") {
        # Brick pattern
        for ($y = 0; $y -lt 16; $y += 4) {
            for ($x = 0; $x -lt 16; $x += 8) {
                $offset = if (($y / 4) % 2 -eq 0) { 0 } else { 4 }
                $graphics.FillRectangle(
                    (New-Object System.Drawing.SolidBrush($baseColor)),
                    ($x + $offset) % 16, $y, 7, 3
                )
                $graphics.DrawRectangle(
                    (New-Object System.Drawing.Pen($accentColor, 1)),
                    ($x + $offset) % 16, $y, 7, 3
                )
            }
        }
    }
    elseif ($pattern -eq "log") {
        # Wood log texture (side)
        # Vertical grain lines
        for ($x = 0; $x -lt 16; $x += 2) {
            $variance = $random.Next(-10, 10)
            $r = [Math]::Max(0, [Math]::Min(255, $baseColor.R + $variance))
            $g = [Math]::Max(0, [Math]::Min(255, $baseColor.G + $variance))
            $b = [Math]::Max(0, [Math]::Min(255, $baseColor.B + $variance))
            $brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb($r, $g, $b))
            $graphics.FillRectangle($brush, $x, 0, 2, 16)
            $brush.Dispose()
        }
    }
    elseif ($pattern -eq "crystal_cluster") {
        # Crystal cluster
        $graphics.Clear([System.Drawing.Color]::FromArgb(80, 80, 90))
        for ($i = 0; $i -lt 6; $i++) {
            $x = $random.Next(2, 12)
            $y = $random.Next(10, 14)
            $height = $random.Next(4, 8)
            # Draw crystal
            $points = @(
                (New-Object System.Drawing.Point($x, $y)),
                (New-Object System.Drawing.Point($x + 2, $y)),
                (New-Object System.Drawing.Point($x + 1, $y - $height))
            )
            $graphics.FillPolygon((New-Object System.Drawing.SolidBrush($baseColor)), $points)
            $graphics.FillRectangle((New-Object System.Drawing.SolidBrush($accentColor)), $x, $y - 2, 2, 2)
        }
    }
    
    $graphics.Dispose()
    
    $fullPath = Join-Path $outputPath $fileName
    $bitmap.Save($fullPath, [System.Drawing.Imaging.ImageFormat]::Png)
    $bitmap.Dispose()
    
    Write-Host "Created: $fileName"
}

Write-Host "Generating Firelands block textures..."
New-BlockTexture "ember_sand.png" ([System.Drawing.Color]::FromArgb(180, 80, 40)) ([System.Drawing.Color]::FromArgb(255, 120, 60)) "sand"
New-BlockTexture "charcoal_rock.png" ([System.Drawing.Color]::FromArgb(40, 40, 45)) ([System.Drawing.Color]::FromArgb(60, 60, 70)) "stone"
New-BlockTexture "obsidian_grass.png" ([System.Drawing.Color]::FromArgb(60, 50, 80)) ([System.Drawing.Color]::FromArgb(100, 80, 120)) "grass"
New-BlockTexture "magmatic_brick.png" ([System.Drawing.Color]::FromArgb(150, 60, 40)) ([System.Drawing.Color]::FromArgb(100, 40, 30)) "brick"
New-BlockTexture "ash_layer.png" ([System.Drawing.Color]::FromArgb(120, 120, 125)) ([System.Drawing.Color]::FromArgb(140, 140, 145)) "sand"
New-BlockTexture "sulfur_vein.png" ([System.Drawing.Color]::FromArgb(100, 50, 50)) ([System.Drawing.Color]::FromArgb(200, 200, 60)) "ore"
New-BlockTexture "ember_root_log.png" ([System.Drawing.Color]::FromArgb(120, 50, 40)) ([System.Drawing.Color]::FromArgb(150, 70, 50)) "log"
New-BlockTexture "lava_glass_tile.png" ([System.Drawing.Color]::FromArgb(255, 100, 50)) ([System.Drawing.Color]::FromArgb(255, 180, 100)) "glass"
New-BlockTexture "fire_furnace_core.png" ([System.Drawing.Color]::FromArgb(200, 80, 40)) ([System.Drawing.Color]::FromArgb(255, 150, 80)) "stone"
New-BlockTexture "smoldering_coal_block.png" ([System.Drawing.Color]::FromArgb(50, 50, 55)) ([System.Drawing.Color]::FromArgb(255, 120, 60)) "stone"
New-BlockTexture "lava_crystal_ore.png" ([System.Drawing.Color]::FromArgb(120, 60, 50)) ([System.Drawing.Color]::FromArgb(255, 150, 80)) "ore"

Write-Host "`nGenerating Aquatica block textures..."
New-BlockTexture "abyssal_glass.png" ([System.Drawing.Color]::FromArgb(40, 80, 120)) ([System.Drawing.Color]::FromArgb(80, 140, 200)) "glass"
New-BlockTexture "pearlstone.png" ([System.Drawing.Color]::FromArgb(180, 200, 220)) ([System.Drawing.Color]::FromArgb(220, 230, 240)) "stone"
New-BlockTexture "luminescent_kelp.png" ([System.Drawing.Color]::FromArgb(60, 150, 100)) ([System.Drawing.Color]::FromArgb(100, 255, 180)) "grass"
New-BlockTexture "pressure_sand.png" ([System.Drawing.Color]::FromArgb(100, 120, 140)) ([System.Drawing.Color]::FromArgb(120, 140, 160)) "sand"
New-BlockTexture "bubble_column_block.png" ([System.Drawing.Color]::FromArgb(80, 160, 220)) ([System.Drawing.Color]::FromArgb(150, 200, 255)) "glass"
New-BlockTexture "siren_shell.png" ([System.Drawing.Color]::FromArgb(200, 180, 160)) ([System.Drawing.Color]::FromArgb(240, 220, 200)) "stone"
New-BlockTexture "brine_rock.png" ([System.Drawing.Color]::FromArgb(100, 120, 130)) ([System.Drawing.Color]::FromArgb(130, 150, 160)) "stone"
New-BlockTexture "oceanic_crystal_ore.png" ([System.Drawing.Color]::FromArgb(80, 100, 120)) ([System.Drawing.Color]::FromArgb(100, 200, 255)) "ore"
New-BlockTexture "tidewood_log.png" ([System.Drawing.Color]::FromArgb(80, 120, 140)) ([System.Drawing.Color]::FromArgb(100, 150, 170)) "log"
New-BlockTexture "salt_stone.png" ([System.Drawing.Color]::FromArgb(220, 220, 230)) ([System.Drawing.Color]::FromArgb(240, 240, 245)) "stone"

Write-Host "`nGenerating Terra Depths block textures..."
New-BlockTexture "rooted_earth.png" ([System.Drawing.Color]::FromArgb(100, 80, 60)) ([System.Drawing.Color]::FromArgb(80, 60, 40)) "grass"
New-BlockTexture "crystal_cluster.png" ([System.Drawing.Color]::FromArgb(180, 120, 220)) ([System.Drawing.Color]::FromArgb(220, 160, 255)) "crystal_cluster"
New-BlockTexture "moss_tile.png" ([System.Drawing.Color]::FromArgb(80, 120, 70)) ([System.Drawing.Color]::FromArgb(100, 150, 90)) "grass"
New-BlockTexture "lum_shale.png" ([System.Drawing.Color]::FromArgb(120, 100, 140)) ([System.Drawing.Color]::FromArgb(180, 160, 200)) "stone"
New-BlockTexture "fungal_fiber.png" ([System.Drawing.Color]::FromArgb(140, 100, 120)) ([System.Drawing.Color]::FromArgb(180, 140, 160)) "grass"
New-BlockTexture "mycelium_log.png" ([System.Drawing.Color]::FromArgb(100, 80, 90)) ([System.Drawing.Color]::FromArgb(130, 110, 120)) "log"
New-BlockTexture "terra_ore.png" ([System.Drawing.Color]::FromArgb(100, 90, 80)) ([System.Drawing.Color]::FromArgb(120, 200, 100)) "ore"
New-BlockTexture "stone_lattice.png" ([System.Drawing.Color]::FromArgb(140, 130, 120)) ([System.Drawing.Color]::FromArgb(180, 170, 160)) "brick"

Write-Host "`nGenerating Skyreach Peaks block textures..."
New-BlockTexture "wind_crystal.png" ([System.Drawing.Color]::FromArgb(200, 230, 255)) ([System.Drawing.Color]::FromArgb(240, 250, 255)) "crystal_cluster"
New-BlockTexture "cloud_soil.png" ([System.Drawing.Color]::FromArgb(240, 240, 250)) ([System.Drawing.Color]::FromArgb(255, 255, 255)) "sand"
New-BlockTexture "petal_tile.png" ([System.Drawing.Color]::FromArgb(255, 200, 220)) ([System.Drawing.Color]::FromArgb(255, 230, 240)) "grass"
New-BlockTexture "air_lattice.png" ([System.Drawing.Color]::FromArgb(210, 230, 250)) ([System.Drawing.Color]::FromArgb(230, 245, 255)) "brick"
New-BlockTexture "stratus_shard.png" ([System.Drawing.Color]::FromArgb(180, 200, 230)) ([System.Drawing.Color]::FromArgb(220, 235, 255)) "crystal_cluster"
New-BlockTexture "gust_rock.png" ([System.Drawing.Color]::FromArgb(180, 190, 210)) ([System.Drawing.Color]::FromArgb(200, 210, 230)) "stone"

Write-Host "`nGenerating Celestial block textures..."
New-BlockTexture "void_crystal_ore.png" ([System.Drawing.Color]::FromArgb(100, 80, 120)) ([System.Drawing.Color]::FromArgb(180, 150, 220)) "ore"
New-BlockTexture "starlight_moss.png" ([System.Drawing.Color]::FromArgb(80, 100, 140)) ([System.Drawing.Color]::FromArgb(150, 200, 255)) "grass"
New-BlockTexture "nebula_sand.png" ([System.Drawing.Color]::FromArgb(120, 100, 160)) ([System.Drawing.Color]::FromArgb(160, 140, 200)) "sand"
New-BlockTexture "rift_block.png" ([System.Drawing.Color]::FromArgb(60, 40, 80)) ([System.Drawing.Color]::FromArgb(120, 100, 160)) "stone"
New-BlockTexture "astral_tile.png" ([System.Drawing.Color]::FromArgb(140, 120, 180)) ([System.Drawing.Color]::FromArgb(180, 160, 220)) "brick"
New-BlockTexture "cosmic_shard.png" ([System.Drawing.Color]::FromArgb(200, 180, 255)) ([System.Drawing.Color]::FromArgb(240, 220, 255)) "crystal_cluster"
New-BlockTexture "lumen_cap.png" ([System.Drawing.Color]::FromArgb(255, 240, 200)) ([System.Drawing.Color]::FromArgb(255, 255, 230)) "grass"

Write-Host "`nAll block textures generated!"
Write-Host "Total textures created in: $outputPath"
