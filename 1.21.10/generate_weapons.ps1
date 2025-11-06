# PowerShell script to generate weapon and tool textures
# Creates legendary weapons with glowing effects
Add-Type -AssemblyName System.Drawing
$itemPath = "src\main\resources\assets\elementaldimensions\textures\item"
Write-Output "=== Creating Legendary Weapon Textures ==="
# Infernal Edge (Fire Sword) - 16x16
Write-Output "Creating Infernal Edge (fire sword)..."
$infernalEdge = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($infernalEdge)
# Blade (red-orange with fire glow)
for ($y = 2; $y -lt 13; $y++) {
    for ($x = 7; $x -lt 9; $x++) {
        # Main blade - gradient from orange to red
        $intensity = 255 - (($y - 2) * 20)
        $r = [Math]::Min(255, 200 + $intensity/5)
        $g = [Math]::Min(255, 80 + $intensity/8)
        $b = 30
        $infernalEdge.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
    }
}
# Fire glow edges
for ($y = 3; $y -lt 12; $y++) {
    $infernalEdge.SetPixel(6, $y, [System.Drawing.Color]::FromArgb(180, 255, 150, 50))
    $infernalEdge.SetPixel(9, $y, [System.Drawing.Color]::FromArgb(180, 255, 150, 50))
}
# Guard (dark with fire hints)
for ($x = 5; $x -lt 11; $x++) {
    $infernalEdge.SetPixel($x, 12, [System.Drawing.Color]::FromArgb(255, 100, 40, 20))
}
# Handle (dark wood/metal)
for ($y = 13; $y -lt 16; $y++) {
    for ($x = 7; $x -lt 9; $x++) {
        $infernalEdge.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 60, 30, 20))
    }
}
$graphics.Dispose()
$infernalEdge.Save("$itemPath\infernal_edge.png")
$infernalEdge.Dispose()
Write-Output "  Done: infernal_edge.png"
# Abyssal Spear (Water/Dark weapon) - 16x16
Write-Output "Creating Abyssal Spear (water spear)..."
$abyssalSpear = New-Object System.Drawing.Bitmap(16, 16)
# Spear tip (cyan/teal)
for ($y = 1; $y -lt 6; $y++) {
    for ($x = 7; $x -lt 9; $x++) {
        $r = 60
        $g = [Math]::Min(255, 180 + ($y * 10))
        $b = [Math]::Min(255, 200 + ($y * 10))
        $abyssalSpear.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
    }
}
# Water glow around tip
$abyssalSpear.SetPixel(6, 2, [System.Drawing.Color]::FromArgb(150, 100, 220, 255))
$abyssalSpear.SetPixel(9, 2, [System.Drawing.Color]::FromArgb(150, 100, 220, 255))
$abyssalSpear.SetPixel(6, 3, [System.Drawing.Color]::FromArgb(150, 100, 220, 255))
$abyssalSpear.SetPixel(9, 3, [System.Drawing.Color]::FromArgb(150, 100, 220, 255))
# Shaft (dark blue)
for ($y = 6; $y -lt 15; $y++) {
    $abyssalSpear.SetPixel(7, $y, [System.Drawing.Color]::FromArgb(255, 30, 50, 80))
    $abyssalSpear.SetPixel(8, $y, [System.Drawing.Color]::FromArgb(255, 30, 50, 80))
}
# Bottom grip (darker)
$abyssalSpear.SetPixel(7, 15, [System.Drawing.Color]::FromArgb(255, 20, 30, 50))
$abyssalSpear.SetPixel(8, 15, [System.Drawing.Color]::FromArgb(255, 20, 30, 50))
$abyssalSpear.Save("$itemPath\abyssal_spear.png")
$abyssalSpear.Dispose()
Write-Output "  Done: abyssal_spear.png"
# Gaia Cleaver (Earth Axe) - 16x16
Write-Output "Creating Gaia Cleaver (earth axe)..."
$gaiaCleaver = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($gaiaCleaver)
# Axe head (green crystal/stone)
$axeColor = [System.Drawing.Color]::FromArgb(255, 80, 160, 80)
$darkGreen = [System.Drawing.Color]::FromArgb(255, 50, 120, 50)
# Top blade
for ($y = 3; $y -lt 7; $y++) {
    for ($x = 4; $x -lt 12; $x++) {
        if ($x -gt 5 -and $x -lt 11) {
            $gaiaCleaver.SetPixel($x, $y, $axeColor)
        } else {
            $gaiaCleaver.SetPixel($x, $y, $darkGreen)
        }
    }
}
# Crystal veins
$gaiaCleaver.SetPixel(6, 4, [System.Drawing.Color]::FromArgb(255, 120, 220, 120))
$gaiaCleaver.SetPixel(8, 5, [System.Drawing.Color]::FromArgb(255, 120, 220, 120))
$gaiaCleaver.SetPixel(10, 4, [System.Drawing.Color]::FromArgb(255, 120, 220, 120))
# Handle (brown wood)
for ($y = 7; $y -lt 15; $y++) {
    $gaiaCleaver.SetPixel(8, $y, [System.Drawing.Color]::FromArgb(255, 100, 60, 30))
    if ($y -lt 14) {
        $gaiaCleaver.SetPixel(7, $y, [System.Drawing.Color]::FromArgb(255, 80, 50, 25))
    }
}
$graphics.Dispose()
$gaiaCleaver.Save("$itemPath\gaia_cleaver.png")
$gaiaCleaver.Dispose()
Write-Output "  Done: gaia_cleaver.png"
# Starforged Blade (Celestial Sword) - 16x16
Write-Output "Creating Starforged Blade (celestial sword)..."
$starforged = New-Object System.Drawing.Bitmap(16, 16)
# Blade (bright silver with star sparkles)
for ($y = 2; $y -lt 13; $y++) {
    for ($x = 7; $x -lt 9; $x++) {
        # Shimmering silver blade
        $brightness = 200 + (Get-Random -Minimum 0 -Maximum 55)
        $starforged.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $brightness, $brightness, $brightness + 20))
    }
}
# Star sparkles on blade
for ($i = 0; $i -lt 5; $i++) {
    $sx = Get-Random -Minimum 6 -Maximum 10
    $sy = Get-Random -Minimum 3 -Maximum 12
    $starforged.SetPixel($sx, $sy, [System.Drawing.Color]::FromArgb(255, 255, 255, 255))
}
# Cosmic energy edges (purple glow)
for ($y = 3; $y -lt 12; $y++) {
    if ((Get-Random -Minimum 0 -Maximum 3) -eq 0) {
        $starforged.SetPixel(6, $y, [System.Drawing.Color]::FromArgb(150, 150, 100, 255))
        $starforged.SetPixel(9, $y, [System.Drawing.Color]::FromArgb(150, 150, 100, 255))
    }
}
# Guard (gold/celestial)
for ($x = 5; $x -lt 11; $x++) {
    $starforged.SetPixel($x, 12, [System.Drawing.Color]::FromArgb(255, 200, 180, 100))
}
# Handle (dark purple cosmic)
for ($y = 13; $y -lt 16; $y++) {
    for ($x = 7; $x -lt 9; $x++) {
        $starforged.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 60, 40, 100))
    }
}
$starforged.Save("$itemPath\starforged_blade.png")
$starforged.Dispose()
Write-Output "  Done: starforged_blade.png"
# Tempest Bow (Wind/Sky bow) - 16x16
Write-Output "Creating Tempest Bow (wind bow)..."
$tempestBow = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($tempestBow)
# Bow shape (light blue with white highlights)
$bowColor = [System.Drawing.Color]::FromArgb(255, 150, 200, 230)
$highlightColor = [System.Drawing.Color]::FromArgb(255, 200, 230, 255)
# Top curve
for ($y = 2; $y -lt 6; $y++) {
    $tempestBow.SetPixel(11, $y, $bowColor)
    if ($y -eq 3) {
        $tempestBow.SetPixel(12, $y, $highlightColor)
    }
}
# Middle
for ($y = 6; $y -lt 10; $y++) {
    $tempestBow.SetPixel(10, $y, $bowColor)
}
# Bottom curve
for ($y = 10; $y -lt 14; $y++) {
    $tempestBow.SetPixel(11, $y, $bowColor)
    if ($y -eq 12) {
        $tempestBow.SetPixel(12, $y, $highlightColor)
    }
}
# String (white/light gray)
$pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(200, 220, 220, 220), 1)
$graphics.DrawLine($pen, 12, 3, 12, 12)
# Wind swirls (light cyan)
for ($i = 0; $i -lt 4; $i++) {
    $wx = Get-Random -Minimum 5 -Maximum 10
    $wy = Get-Random -Minimum 4 -Maximum 12
    $tempestBow.SetPixel($wx, $wy, [System.Drawing.Color]::FromArgb(120, 180, 230, 255))
}
$graphics.Dispose()
$tempestBow.Save("$itemPath\tempest_bow.png")
$tempestBow.Dispose()
Write-Output "  Done: tempest_bow.png"
# Astral Pickaxe (Celestial pickaxe) - 16x16
Write-Output "Creating Astral Pickaxe (celestial pickaxe)..."
$astralPick = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($astralPick)
# Pickaxe head (shimmering silver with purple energy)
for ($y = 4; $y -lt 7; $y++) {
    for ($x = 3; $x -lt 13; $x++) {
        if ($x -lt 7 -or $x -gt 9) {
            # Pick tips - bright silver
            $brightness = 200 + (Get-Random -Minimum 0 -Maximum 55)
            $astralPick.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $brightness, $brightness, $brightness))
        } else {
            # Center - cosmic purple
            $astralPick.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 100, 80, 150))
        }
    }
}
# Star sparkles on head
$astralPick.SetPixel(4, 5, [System.Drawing.Color]::FromArgb(255, 255, 255, 255))
$astralPick.SetPixel(11, 5, [System.Drawing.Color]::FromArgb(255, 255, 255, 255))
$astralPick.SetPixel(7, 4, [System.Drawing.Color]::FromArgb(255, 200, 180, 255))
# Handle (diagonal cosmic wood)
for ($i = 0; $i -lt 9; $i++) {
    $hx = 8 + ($i % 2)
    $hy = 7 + $i
    if ($hy -lt 16) {
        $astralPick.SetPixel($hx, $hy, [System.Drawing.Color]::FromArgb(255, 60, 50, 90))
    }
}
$graphics.Dispose()
$astralPick.Save("$itemPath\astral_pickaxe.png")
$astralPick.Dispose()
Write-Output "  Done: astral_pickaxe.png"
# Voidheart Bow (Void/Dark bow) - 16x16
Write-Output "Creating Voidheart Bow (void bow)..."
$voidBow = New-Object System.Drawing.Bitmap(16, 16)
$graphics = [System.Drawing.Graphics]::FromImage($voidBow)
# Bow shape (dark purple/black)
$bowColor = [System.Drawing.Color]::FromArgb(255, 50, 30, 80)
$glowColor = [System.Drawing.Color]::FromArgb(255, 120, 70, 150)
# Top curve
for ($y = 2; $y -lt 6; $y++) {
    $voidBow.SetPixel(11, $y, $bowColor)
    if ($y -eq 3) {
        $voidBow.SetPixel(12, $y, $glowColor)
    }
}
# Middle
for ($y = 6; $y -lt 10; $y++) {
    $voidBow.SetPixel(10, $y, $bowColor)
    $voidBow.SetPixel(9, $y, $glowColor) # Void energy glow
}
# Bottom curve
for ($y = 10; $y -lt 14; $y++) {
    $voidBow.SetPixel(11, $y, $bowColor)
    if ($y -eq 12) {
        $voidBow.SetPixel(12, $y, $glowColor)
    }
}
# String (purple energy)
$pen = New-Object System.Drawing.Pen([System.Drawing.Color]::FromArgb(200, 150, 100, 200), 1)
$graphics.DrawLine($pen, 12, 3, 12, 12)
# Void particles
for ($i = 0; $i -lt 5; $i++) {
    $vx = Get-Random -Minimum 5 -Maximum 10
    $vy = Get-Random -Minimum 4 -Maximum 12
    $voidBow.SetPixel($vx, $vy, [System.Drawing.Color]::FromArgb(150, 80, 50, 120))
}
$graphics.Dispose()
$voidBow.Save("$itemPath\voidheart_bow.png")
$voidBow.Dispose()
Write-Output "  Done: voidheart_bow.png"
Write-Output ""
Write-Output "=== All legendary weapon textures created! ==="
Write-Output "Created 7 weapon/tool textures"