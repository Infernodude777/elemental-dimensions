# Create additional textures for Firelands, Aquatica, Terra Depths, Skyreach, and Celestial blocks
Add-Type -AssemblyName System.Drawing
$blockPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\src\main\resources\assets\elementaldimensions\textures\block"
$particlePath = "c:\Users\Nikhil\Desktop\elemental_dimensions\src\main\resources\assets\elementaldimensions\textures\particle"
# Create particle directory if it doesn't exist
if (!(Test-Path $particlePath)) {
    New-Item -ItemType Directory -Path $particlePath | Out-Null
}
Write-Output "=== Creating Enhanced Block Textures ===`n"
# Enhanced Scorched Stone with lava cracks
Write-Output "Enhancing Scorched Stone texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Create lava crack pattern
        if (($x + $y * 2) % 11 -eq 0 -or ($x * 2 - $y) % 13 -eq 0) {
            # Glowing cracks
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 220, 80, 20))
        } elseif ((Get-Random -Minimum 0 -Maximum 20) -eq 0) {
            # Ember spots
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 180, 60, 10))
        } else {
            # Dark scorched rock
            $noise = Get-Random -Minimum -15 -Maximum 15
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 45 + $noise, 25 + $noise, 20 + $noise))
        }
    }
}
$bmp.Save("$blockPath\scorched_stone.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Enhanced Molten Glass with flowing lava effect
Write-Output "Enhancing Molten Glass texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Flowing lava pattern
        $wave = [Math]::Sin(($y + $x) / 3.0) * 40
        $r = 200 + $wave
        $g = 100 + $wave/2
        $b = 20
        if ($x % 4 -eq 0 -or $y % 4 -eq 0) {
            # Glass frame
            $alpha = 150
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($alpha, [Math]::Max(0, $r), [Math]::Max(0, $g), $b))
        } else {
            # Molten interior
            $alpha = 120
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb($alpha, [Math]::Max(0, $r - 20), [Math]::Max(0, $g - 20), $b))
        }
    }
}
$bmp.Save("$blockPath\molten_glass.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Enhanced Coralite with coral patterns
Write-Output "Enhancing Coralite texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Coral branch pattern
        $dist = [Math]::Abs($x - 8) + [Math]::Abs($y - 8)
        if ($dist -lt 3) {
            # Coral core - bright cyan
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 80, 220, 200))
        } elseif ($dist -lt 6) {
            # Coral branches
            if (($x + $y) % 3 -eq 0) {
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 60, 180, 160))
            } else {
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 50, 160, 140))
            }
        } else {
            # Outer coral
            $noise = Get-Random -Minimum -10 -Maximum 10
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 40 + $noise, 140 + $noise, 120 + $noise))
        }
    }
}
$bmp.Save("$blockPath\coralite.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Enhanced Gaia Stone with moss and crystal veins
Write-Output "Enhancing Gaia Stone texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Crystal vein pattern
        if (($x + $y) % 8 -eq 0 -or ($x - $y + 16) % 8 -eq 0) {
            # Green crystal veins
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 100, 220, 100))
        } elseif ((Get-Random -Minimum 0 -Maximum 15) -eq 0) {
            # Moss spots
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 80, 150, 60))
        } else {
            # Stone base
            $noise = Get-Random -Minimum -12 -Maximum 12
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 90 + $noise, 110 + $noise, 85 + $noise))
        }
    }
}
$bmp.Save("$blockPath\gaia_stone.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Enhanced Skyrock with cloud wisps
Write-Output "Enhancing Skyrock texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Cloud wisp pattern
        $dist = [Math]::Sqrt(($x - 5) * ($x - 5) + ($y - 5) * ($y - 5))
        $dist2 = [Math]::Sqrt(($x - 11) * ($x - 11) + ($y - 11) * ($y - 11))
        if ($dist -lt 3 -or $dist2 -lt 3) {
            # Cloud wisps
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 240, 245, 250))
        } else {
            # Light blue stone
            $noise = Get-Random -Minimum -15 -Maximum 15
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 180 + $noise, 200 + $noise, 220 + $noise))
        }
    }
}
$bmp.Save("$blockPath\skyrock.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Enhanced Celestial Stone with star patterns
Write-Output "Enhancing Celestial Stone texture..."
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        # Star pattern
        if ((($x + $y) % 7 -eq 0 -and ($x - $y) % 5 -eq 0) -or (Get-Random -Minimum 0 -Maximum 40) -eq 0) {
            # Twinkling stars
            $brightness = Get-Random -Minimum 200 -Maximum 255
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $brightness, $brightness, 255))
        } else {
            # Dark space stone
            $noise = Get-Random -Minimum -8 -Maximum 8
            $r = 25 + $noise
            $g = 20 + $noise
            $b = 50 + $noise * 2
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, [Math]::Max(0, $r), [Math]::Max(0, $g), [Math]::Max(0, $b)))
        }
    }
}
$bmp.Save("$blockPath\celestial_stone.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "`n=== Creating Particle Textures ===`n"
# Ember particle
Write-Output "Creating Ember particle..."
$bmp = New-Object System.Drawing.Bitmap(8, 8)
for ($y = 0; $y -lt 8; $y++) {
    for ($x = 0; $x -lt 8; $x++) {
        $centerX = 4; $centerY = 4
        $dist = [Math]::Sqrt(($x - $centerX) * ($x - $centerX) + ($y - $centerY) * ($y - $centerY))
        if ($dist -lt 2) {
            # Bright center
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 255, 180, 40))
        } elseif ($dist -lt 3) {
            # Glow
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(200, 255, 120, 20))
        } elseif ($dist -lt 4) {
            # Fade
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(100, 200, 60, 10))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$particlePath\ember.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Void particle
Write-Output "Creating Void particle..."
$bmp = New-Object System.Drawing.Bitmap(8, 8)
for ($y = 0; $y -lt 8; $y++) {
    for ($x = 0; $x -lt 8; $x++) {
        $centerX = 4; $centerY = 4
        $dist = [Math]::Sqrt(($x - $centerX) * ($x - $centerX) + ($y - $centerY) * ($y - $centerY))
        if ($dist -lt 2) {
            # Dark center
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 80, 60, 120))
        } elseif ($dist -lt 3) {
            # Purple glow
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(200, 60, 40, 90))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$particlePath\void_particle.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Bubble particle
Write-Output "Creating Bubble particle..."
$bmp = New-Object System.Drawing.Bitmap(8, 8)
for ($y = 0; $y -lt 8; $y++) {
    for ($x = 0; $x -lt 8; $x++) {
        $centerX = 4; $centerY = 4
        $dist = [Math]::Sqrt(($x - $centerX) * ($x - $centerX) + ($y - $centerY) * ($y - $centerY))
        if ($dist -gt 2 -and $dist -lt 3) {
            # Bubble edge
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(180, 200, 230, 255))
        } elseif ($dist -le 2 -and ($x -eq 3 -and $y -eq 3)) {
            # Highlight
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 255, 255, 255))
        } elseif ($dist -le 2) {
            # Interior
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(80, 150, 200, 240))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$particlePath\bubble.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
# Crystal sparkle particle
Write-Output "Creating Crystal Sparkle particle..."
$bmp = New-Object System.Drawing.Bitmap(8, 8)
$graphics = [System.Drawing.Graphics]::FromImage($bmp)
$graphics.Clear([System.Drawing.Color]::Transparent)
# Draw sparkle cross
$pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(255, 100, 255, 200), 1)
$graphics.DrawLine($pen, 4, 1, 4, 7)
$graphics.DrawLine($pen, 1, 4, 7, 4)
$graphics.DrawLine($pen, 2, 2, 6, 6)
$graphics.DrawLine($pen, 6, 2, 2, 6)
$pen.Dispose()
$graphics.Dispose()
$bmp.Save("$particlePath\crystal_sparkle.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "`n=== All enhanced textures and particles created! ===`n"