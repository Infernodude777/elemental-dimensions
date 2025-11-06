# PowerShell script to generate GUI textures for Elemental Dimensions mod
# Creates custom interface elements for each dimension
Add-Type -AssemblyName System.Drawing
function Add-Noise {
    param($color, $amount)
    $r = [Math]::Max(0, [Math]::Min(255, $color.R + (Get-Random -Minimum (-$amount) -Maximum $amount)))
    $g = [Math]::Max(0, [Math]::Min(255, $color.G + (Get-Random -Minimum (-$amount) -Maximum $amount)))
    $b = [Math]::Max(0, [Math]::Min(255, $color.B + (Get-Random -Minimum (-$amount) -Maximum $amount)))
    return [System.Drawing.Color]::FromArgb($color.A, $r, $g, $b)
}
$guiPath = "src\main\resources\assets\elementaldimensions\textures\gui"
New-Item -ItemType Directory -Force -Path $guiPath | Out-Null
Write-Output "=== Creating GUI Textures ==="
# Dimensional Compass GUI Background (256x256)
Write-Output "Creating Dimensional Compass GUI..."
$compassGui = New-Object System.Drawing.Bitmap(256, 256)
$graphics = [System.Drawing.Graphics]::FromImage($compassGui)
# Dark background with gradient
for ($y = 0; $y -lt 256; $y++) {
    $darkness = [Math]::Max(0, 40 - ($y / 8))
    $color = [System.Drawing.Color]::FromArgb(255, $darkness, $darkness, $darkness + 10)
    for ($x = 0; $x -lt 256; $x++) {
        $compassGui.SetPixel($x, $y, $color)
    }
}
# Border frame
$borderPen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(255, 100, 80, 120), 2)
$graphics.DrawRectangle($borderPen, 5, 5, 245, 245)
$graphics.DrawRectangle($borderPen, 8, 8, 239, 239)
# Title bar
for ($y = 10; $y -lt 30; $y++) {
    for ($x = 10; $x -lt 246; $x++) {
        $color = [System.Drawing.Color]::FromArgb(255, 60, 50, 80)
        $compassGui.SetPixel($x, $y, $color)
    }
}
$graphics.Dispose()
$compassGui.Save("$guiPath\compass_gui.png")
$compassGui.Dispose()
Write-Output "  ✓ compass_gui.png"
# Void Altar GUI Background (176x166 - standard inventory size)
Write-Output "Creating Void Altar GUI..."
$altarGui = New-Object System.Drawing.Bitmap(176, 166)
$graphics = [System.Drawing.Graphics]::FromImage($altarGui)
# Dark purple background
for ($y = 0; $y -lt 166; $y++) {
    for ($x = 0; $x -lt 176; $x++) {
        $noise = Get-Random -Minimum -5 -Maximum 5
        $color = [System.Drawing.Color]::FromArgb(255, 40 + $noise, 30 + $noise, 60 + $noise)
        $altarGui.SetPixel($x, $y, $color)
    }
}
# Void energy effect (purple glow in center)
$centerX = 88
$centerY = 40
for ($y = 20; $y -lt 60; $y++) {
    for ($x = 68; $x -lt 108; $x++) {
        $dist = [Math]::Sqrt([Math]::Pow($x - $centerX, 2) + [Math]::Pow($y - $centerY, 2))
        if ($dist -lt 20) {
            $intensity = [Math]::Max(0, 255 - ($dist * 10))
            $current = $altarGui.GetPixel($x, $y)
            $newColor = [System.Drawing.Color]::FromArgb(255,
                [Math]::Min(255, $current.R + $intensity/3),
                [Math]::Min(255, $current.G + $intensity/5),
                [Math]::Min(255, $current.B + $intensity/2))
            $altarGui.SetPixel($x, $y, $newColor)
        }
    }
}
# Border
$borderPen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(255, 80, 60, 100), 1)
$graphics.DrawRectangle($borderPen, 0, 0, 175, 165)
$graphics.Dispose()
$altarGui.Save("$guiPath\void_altar_gui.png")
$altarGui.Dispose()
Write-Output "  ✓ void_altar_gui.png"
Write-Output "`n=== Creating Effect Icons ==="
$effectsPath = "src\main\resources\assets\elementaldimensions\textures\mob_effect"
New-Item -ItemType Directory -Force -Path $effectsPath | Out-Null
# Void Corruption Effect Icon (18x18)
Write-Output "Creating Void Corruption effect icon..."
$voidEffect = New-Object System.Drawing.Bitmap(18, 18)
for ($y = 0; $y -lt 18; $y++) {
    for ($x = 0; $x -lt 18; $x++) {
        $dist = [Math]::Sqrt([Math]::Pow($x - 9, 2) + [Math]::Pow($y - 9, 2))
        if ($dist -lt 8) {
            # Dark purple core
            $intensity = [Math]::Max(0, 255 - ($dist * 30))
            $color = [System.Drawing.Color]::FromArgb(255, 80 + $intensity/4, 40 + $intensity/6, 120 + $intensity/3)
            $voidEffect.SetPixel($x, $y, $color)
        } else {
            # Transparent outside
            $voidEffect.SetPixel($x, $y, [System.Drawing.Color]::Transparent)
        }
    }
}
$voidEffect.Save("$effectsPath\void_corruption.png")
$voidEffect.Dispose()
Write-Output "  ✓ void_corruption.png"
# Flame Aura Effect Icon (18x18)
Write-Output "Creating Flame Aura effect icon..."
$flameEffect = New-Object System.Drawing.Bitmap(18, 18)
for ($y = 0; $y -lt 18; $y++) {
    for ($x = 0; $x -lt 18; $x++) {
        $dist = [Math]::Sqrt([Math]::Pow($x - 9, 2) + [Math]::Pow($y - 9, 2))
        if ($dist -lt 8) {
            # Fire colors - red/orange core
            $intensity = [Math]::Max(0, 255 - ($dist * 30))
            if ($dist -lt 4) {
                $color = [System.Drawing.Color]::FromArgb(255, 255, 180 + $intensity/3, 40)
            } else {
                $color = [System.Drawing.Color]::FromArgb(255, 220 + $intensity/6, 80 + $intensity/4, 20)
            }
            $flameEffect.SetPixel($x, $y, $color)
        } else {
            $flameEffect.SetPixel($x, $y, [System.Drawing.Color]::Transparent)
        }
    }
}
$flameEffect.Save("$effectsPath\flame_aura.png")
$flameEffect.Dispose()
Write-Output "  ✓ flame_aura.png"
# Water Breathing Enhanced Effect Icon (18x18)
Write-Output "Creating Aquatic Blessing effect icon..."
$waterEffect = New-Object System.Drawing.Bitmap(18, 18)
for ($y = 0; $y -lt 18; $y++) {
    for ($x = 0; $x -lt 18; $x++) {
        $dist = [Math]::Sqrt([Math]::Pow($x - 9, 2) + [Math]::Pow($y - 9, 2))
        if ($dist -lt 8) {
            # Cyan/blue water colors
            $intensity = [Math]::Max(0, 255 - ($dist * 30))
            $color = [System.Drawing.Color]::FromArgb(255, 60 + $intensity/6, 180 + $intensity/4, 220 + $intensity/3)
            $waterEffect.SetPixel($x, $y, $color)
        } else {
            $waterEffect.SetPixel($x, $y, [System.Drawing.Color]::Transparent)
        }
    }
}
$waterEffect.Save("$effectsPath\aquatic_blessing.png")
$waterEffect.Dispose()
Write-Output "  ✓ aquatic_blessing.png"
# Earth Resilience Effect Icon (18x18)
Write-Output "Creating Earth Resilience effect icon..."
$earthEffect = New-Object System.Drawing.Bitmap(18, 18)
for ($y = 0; $y -lt 18; $y++) {
    for ($x = 0; $x -lt 18; $x++) {
        $dist = [Math]::Sqrt([Math]::Pow($x - 9, 2) + [Math]::Pow($y - 9, 2))
        if ($dist -lt 8) {
            # Green/brown earth colors
            $intensity = [Math]::Max(0, 255 - ($dist * 30))
            $color = [System.Drawing.Color]::FromArgb(255, 100 + $intensity/4, 160 + $intensity/3, 80 + $intensity/5)
            $earthEffect.SetPixel($x, $y, $color)
        } else {
            $earthEffect.SetPixel($x, $y, [System.Drawing.Color]::Transparent)
        }
    }
}
$earthEffect.Save("$effectsPath\earth_resilience.png")
$earthEffect.Dispose()
Write-Output "  ✓ earth_resilience.png"
# Sky Grace Effect Icon (18x18)
Write-Output "Creating Sky Grace effect icon..."
$skyEffect = New-Object System.Drawing.Bitmap(18, 18)
for ($y = 0; $y -lt 18; $y++) {
    for ($x = 0; $x -lt 18; $x++) {
        $dist = [Math]::Sqrt([Math]::Pow($x - 9, 2) + [Math]::Pow($y - 9, 2))
        if ($dist -lt 8) {
            # Light blue/white sky colors
            $intensity = [Math]::Max(0, 255 - ($dist * 30))
            $color = [System.Drawing.Color]::FromArgb(255, 200 + $intensity/5, 220 + $intensity/4, 240 + $intensity/6)
            $skyEffect.SetPixel($x, $y, $color)
        } else {
            $skyEffect.SetPixel($x, $y, [System.Drawing.Color]::Transparent)
        }
    }
}
$skyEffect.Save("$effectsPath\sky_grace.png")
$skyEffect.Dispose()
Write-Output "  ✓ sky_grace.png"
# Celestial Power Effect Icon (18x18)
Write-Output "Creating Celestial Power effect icon..."
$celestialEffect = New-Object System.Drawing.Bitmap(18, 18)
$graphics = [System.Drawing.Graphics]::FromImage($celestialEffect)
# Dark space background
for ($y = 0; $y -lt 18; $y++) {
    for ($x = 0; $x -lt 18; $x++) {
        $dist = [Math]::Sqrt([Math]::Pow($x - 9, 2) + [Math]::Pow($y - 9, 2))
        if ($dist -lt 8) {
            $celestialEffect.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 30, 20, 60))
        } else {
            $celestialEffect.SetPixel($x, $y, [System.Drawing.Color]::Transparent)
        }
    }
}
# Add stars
for ($i = 0; $i -lt 15; $i++) {
    $sx = Get-Random -Minimum 3 -Maximum 15
    $sy = Get-Random -Minimum 3 -Maximum 15
    $dist = [Math]::Sqrt([Math]::Pow($sx - 9, 2) + [Math]::Pow($sy - 9, 2))
    if ($dist -lt 7) {
        $celestialEffect.SetPixel($sx, $sy, [System.Drawing.Color]::FromArgb(255, 255, 255, 200))
    }
}
$graphics.Dispose()
$celestialEffect.Save("$effectsPath\celestial_power.png")
$celestialEffect.Dispose()
Write-Output "  ✓ celestial_power.png"
Write-Output "`n=== Creating Additional Item Textures ==="
$itemsPath = "src\main\resources\assets\elementaldimensions\textures\item"
# Dimension Keys (fancy versions)
Write-Output "Creating dimension key textures..."
# Void Dimension Key (16x16)
$voidKey = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($voidKey)
# Key shaft (vertical)
for ($y = 2; $y -lt 12; $y++) {
    for ($x = 7; $x -lt 9; $x++) {
        $voidKey.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 80, 60, 120))
    }
}
# Key head (circle at top)
$headColor = [System.Drawing.Color]::FromArgb(255, 100, 70, 140)
$voidKey.SetPixel(7, 2, $headColor)
$voidKey.SetPixel(8, 2, $headColor)
$voidKey.SetPixel(6, 3, $headColor)
$voidKey.SetPixel(9, 3, $headColor)
$voidKey.SetPixel(6, 4, $headColor)
$voidKey.SetPixel(9, 4, $headColor)
# Key teeth (at bottom)
$voidKey.SetPixel(9, 10, [System.Drawing.Color]::FromArgb(255, 80, 60, 120))
$voidKey.SetPixel(9, 11, [System.Drawing.Color]::FromArgb(255, 80, 60, 120))
$voidKey.SetPixel(9, 12, [System.Drawing.Color]::FromArgb(255, 80, 60, 120))
$graphics.Dispose()
$voidKey.Save("$itemsPath\void_dimension_key.png")
$voidKey.Dispose()
Write-Output "  ✓ void_dimension_key.png"
# Elemental Catalyst (crafting ingredient - 16x16)
Write-Output "Creating Elemental Catalyst..."
$catalyst = New-Object System.Drawing.Bitmap(16, 16)
# Diamond shape with multicolor gradient
$colors = @(
    [System.Drawing.Color]::FromArgb(255, 255, 100, 100),  # Red
    [System.Drawing.Color]::FromArgb(255, 100, 100, 255),  # Blue
    [System.Drawing.Color]::FromArgb(255, 100, 255, 100),  # Green
    [System.Drawing.Color]::FromArgb(255, 255, 255, 100)   # Yellow
)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $dist = [Math]::Abs($x - 8) + [Math]::Abs($y - 8)
        if ($dist -lt 6) {
            $colorIndex = ($x + $y) % 4
            $intensity = [Math]::Max(0, 255 - ($dist * 40))
            $baseColor = $colors[$colorIndex]
            $color = [System.Drawing.Color]::FromArgb(255,
                [Math]::Min(255, $baseColor.R * $intensity / 255),
                [Math]::Min(255, $baseColor.G * $intensity / 255),
                [Math]::Min(255, $baseColor.B * $intensity / 255))
            $catalyst.SetPixel($x, $y, $color)
        }
    }
}
$catalyst.Save("$itemsPath\elemental_catalyst.png")
$catalyst.Dispose()
Write-Output "  ✓ elemental_catalyst.png"
Write-Output "`n=== All GUI and effect textures created! ==="
Write-Output "Created:"
Write-Output "  - 2 GUI backgrounds (compass, void altar)"
Write-Output "  - 6 status effect icons"
Write-Output "  - 2 additional item textures"
Write-Output "Total: 10 new textures"