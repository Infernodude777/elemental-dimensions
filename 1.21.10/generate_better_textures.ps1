# Advanced Texture Generator for Elemental Dimensions
# Creates more detailed, Minecraft-style textures with patterns

Add-Type -AssemblyName System.Drawing

$blockPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\src\main\resources\assets\elementaldimensions\textures\block"
$itemPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\src\main\resources\assets\elementaldimensions\textures\item"

# Helper function to add noise
function Add-Noise {
    param($color, $variance)
    $r = [Math]::Max(0, [Math]::Min(255, $color.R + (Get-Random -Minimum (-$variance) -Maximum $variance)))
    $g = [Math]::Max(0, [Math]::Min(255, $color.G + (Get-Random -Minimum (-$variance) -Maximum $variance)))
    $b = [Math]::Max(0, [Math]::Min(255, $color.B + (Get-Random -Minimum (-$variance) -Maximum $variance)))
    return [System.Drawing.Color]::FromArgb(255, $r, $g, $b)
}

# Create improved Voidstone texture with cracks
Write-Host "Creating Voidstone texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
$baseColor = [System.Drawing.Color]::FromArgb(255, 40, 40, 45)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Add cracks pattern
        if (($x + $y) % 7 -eq 0 -or ($x - $y) % 9 -eq 0) {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 25, 25, 30))
        } else {
            $bmp.SetPixel($x, $y, (Add-Noise $baseColor 15))
        }
    }
}
$bmp.Save("$blockPath\voidstone.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Void Crystal with glow effect
Write-Host "Creating Void Crystal texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $centerX = 8; $centerY = 8
        $dist = [Math]::Sqrt(($x - $centerX) * ($x - $centerX) + ($y - $centerY) * ($y - $centerY))
        
        if ($dist -lt 4) {
            # Bright core
            $brightness = 200 - ($dist * 30)
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 100 + $brightness/4, 80 + $brightness/5, 150 + $brightness/3))
        } elseif ($dist -lt 6) {
            # Glow ring
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 90, 70, 130))
        } else {
            # Outer crystal
            $noise = Get-Random -Minimum -10 -Maximum 10
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 60 + $noise, 50 + $noise, 100 + $noise))
        }
    }
}
$bmp.Save("$blockPath\void_crystal.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Nullrock with subtle pattern
Write-Host "Creating Nullrock texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Checker pattern for depth
        if (($x / 4 + $y / 4) % 2 -eq 0) {
            $base = 30
        } else {
            $base = 35
        }
        $noise = Get-Random -Minimum -8 -Maximum 8
        $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $base + $noise, $base + $noise, $base + $noise + 5))
    }
}
$bmp.Save("$blockPath\nullrock.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Dark Matter Block with swirl pattern
Write-Host "Creating Dark Matter Block texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $angle = [Math]::Atan2($y - 8, $x - 8)
        $swirl = [Math]::Sin($angle * 3) * 10
        
        $r = 15 + $swirl
        $g = 10 + $swirl/2
        $b = 35 + $swirl
        
        $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, [Math]::Max(0, $r), [Math]::Max(0, $g), [Math]::Max(0, $b)))
    }
}
$bmp.Save("$blockPath\dark_matter_block.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Void Glass with grid pattern
Write-Host "Creating Void Glass texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        if ($x % 8 -eq 0 -or $y % 8 -eq 0) {
            # Grid lines
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(120, 50, 50, 90))
        } else {
            # Transparent panes
            $alpha = 80 + (Get-Random -Minimum -20 -Maximum 20)
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($alpha, 35, 35, 70))
        }
    }
}
$bmp.Save("$blockPath\void_glass.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Ethereal Grass with detailed grass blades
Write-Host "Creating Ethereal Grass texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        if ($y -lt 8) {
            # Grass top - purple with variation
            if ((Get-Random -Minimum 0 -Maximum 10) -lt 2) {
                # Grass blade highlights
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 100, 70, 120))
            } else {
                $noise = Get-Random -Minimum -15 -Maximum 15
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 75 + $noise, 55 + $noise, 95 + $noise))
            }
        } else {
            # Dirt bottom
            $noise = Get-Random -Minimum -10 -Maximum 10
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 38 + $noise, 38 + $noise, 42 + $noise))
        }
    }
}
$bmp.Save("$blockPath\ethereal_grass.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

Write-Host "`n=== Creating Item Textures ===`n"

# Create Void Essence with energy effect
Write-Host "Creating Void Essence texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $centerX = 8; $centerY = 8
        $dist = [Math]::Sqrt(($x - $centerX) * ($x - $centerX) + ($y - $centerY) * ($y - $centerY))
        
        if ($dist -lt 5 -and $dist -gt 2) {
            # Energy ring
            $pulse = [Math]::Sin($dist * 1.5) * 30
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 90 + $pulse, 70 + $pulse, 140 + $pulse))
        } elseif ($dist -le 2) {
            # Core
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 140, 110, 180))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$itemPath\void_essence.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Dark Matter with singularity effect
Write-Host "Creating Dark Matter texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $centerX = 8; $centerY = 8
        $dist = [Math]::Sqrt(($x - $centerX) * ($x - $centerX) + ($y - $centerY) * ($y - $centerY))
        
        if ($dist -lt 6) {
            $intensity = 50 - ($dist * 8)
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $intensity/3, $intensity/5, $intensity))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$itemPath\dark_matter.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Void Crystal Shard with facets
Write-Host "Creating Void Crystal Shard texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($bmp)
$graphics.Clear([System.Drawing.Color]::Transparent)

# Draw crystal facets
$pen1 = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(255, 80, 60, 130), 2)
$pen2 = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(255, 100, 80, 150), 1)

$points = @(
    (New-Object System.Drawing.Point(8, 2)),
    (New-Object System.Drawing.Point(12, 8)),
    (New-Object System.Drawing.Point(8, 14)),
    (New-Object System.Drawing.Point(4, 8))
)

$graphics.FillPolygon((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 70, 50, 110))), $points)
$graphics.DrawPolygon($pen1, $points)
$graphics.DrawLine($pen2, 8, 2, 8, 14)
$graphics.DrawLine($pen2, 4, 8, 12, 8)

$graphics.Dispose()
$pen1.Dispose()
$pen2.Dispose()
$bmp.Save("$itemPath\void_crystal_shard.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Nullstone Ingot
Write-Host "Creating Nullstone Ingot texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        if ($x -ge 4 -and $x -le 11 -and $y -ge 5 -and $y -le 10) {
            # Ingot shape with metallic sheen
            if ($x -le 7) {
                $brightness = 50 + ($x - 4) * 10
            } else {
                $brightness = 80 - ($x - 8) * 10
            }
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $brightness/2, $brightness/2, $brightness/2 + 10))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$itemPath\nullstone_ingot.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Ethereal Thread with weave pattern
Write-Host "Creating Ethereal Thread texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($bmp)
$graphics.Clear([System.Drawing.Color]::Transparent)

$pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(255, 90, 70, 110), 2)
# Draw wavy thread
for ($i = 0; $i -lt 12; $i++) {
    $y = 4 + $i
    $x = 8 + [Math]::Sin($i / 2.0) * 3
    $graphics.FillEllipse((New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(200, 85, 65, 105))), $x, $y, 2, 2)
}

$pen.Dispose()
$graphics.Dispose()
$bmp.Save("$itemPath\ethereal_thread.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Void Lord Heart with pulsing effect
Write-Host "Creating Void Lord Heart texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Heart shape
        $dx1 = $x - 5; $dy1 = $y - 6
        $dx2 = $x - 11; $dy2 = $y - 6
        $dist1 = [Math]::Sqrt($dx1 * $dx1 + $dy1 * $dy1)
        $dist2 = [Math]::Sqrt($dx2 * $dx2 + $dy2 * $dy2)
        
        if (($dist1 -lt 4 -or $dist2 -lt 4 -or ($y -gt 8 -and $y -lt 14 -and $x -gt 6 -and $x -lt 10))) {
            if ($dist1 -lt 2 -or $dist2 -lt 2) {
                # Bright core
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 160, 40, 140))
            } else {
                # Dark purple
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 90, 20, 80))
            }
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$itemPath\void_lord_heart.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

# Create Void Altar Rune with mystical symbols
Write-Host "Creating Void Altar Rune texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($bmp)
$graphics.Clear([System.Drawing.Color]::Transparent)

# Background circle
$brush = New-Object System.Drawing.SolidBrush([System.Drawing.Color]::FromArgb(255, 50, 40, 70))
$graphics.FillEllipse($brush, 3, 3, 10, 10)

# Rune symbols (simple lines)
$pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(255, 120, 90, 150), 1)
$graphics.DrawLine($pen, 8, 5, 8, 11)
$graphics.DrawLine($pen, 5, 8, 11, 8)
$graphics.DrawLine($pen, 6, 6, 10, 10)
$graphics.DrawLine($pen, 10, 6, 6, 10)

$brush.Dispose()
$pen.Dispose()
$graphics.Dispose()
$bmp.Save("$itemPath\void_altar_rune.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()

Write-Host "`n=== All improved textures created! ===`n"
Write-Host "Block textures: 6 files in $blockPath"
Write-Host "Item textures: 7 files in $itemPath"
