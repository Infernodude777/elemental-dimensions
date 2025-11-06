# PowerShell script to generate entity/mob textures for Elemental Dimensions
# Creates mob textures following Minecraft style
Add-Type -AssemblyName System.Drawing
$entityPath = "src\main\resources\assets\elementaldimensions\textures\entity"
New-Item -ItemType Directory -Force -Path $entityPath | Out-Null
Write-Output "=== Creating Entity/Mob Textures ==="
# Shadow Wraith (64x32 - standard biped)
Write-Output "Creating Shadow Wraith texture..."
$shadowWraith = New-Object System.Drawing.Bitmap(64, 32)
# Fill with dark shadowy colors
for ($y = 0; $y -lt 32; $y++) {
    for ($x = 0; $x -lt 64; $x++) {
        $noise = Get-Random -Minimum -10 -Maximum 10
        # Very dark purple/black
        $r = [Math]::Max(0, [Math]::Min(255, 30 + $noise))
        $g = [Math]::Max(0, [Math]::Min(255, 20 + $noise))
        $b = [Math]::Max(0, [Math]::Min(255, 50 + $noise))
        $shadowWraith.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
    }
}
# Add glowing eyes (red)
for ($ey = 8; $ey -lt 11; $ey++) {
    for ($ex = 10; $ex -lt 12; $ex++) {
        $shadowWraith.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 200, 20, 20))
    }
    for ($ex = 14; $ex -lt 16; $ex++) {
        $shadowWraith.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 200, 20, 20))
    }
}
$shadowWraith.Save("$entityPath\shadow_wraith.png")
$shadowWraith.Dispose()
Write-Output "  Done: shadow_wraith.png"
# Void Stalker (64x32)
Write-Output "Creating Void Stalker texture..."
$voidStalker = New-Object System.Drawing.Bitmap(64, 32)
# Dark blue/purple base
for ($y = 0; $y -lt 32; $y++) {
    for ($x = 0; $x -lt 64; $x++) {
        $noise = Get-Random -Minimum -8 -Maximum 8
        $r = [Math]::Max(0, [Math]::Min(255, 50 + $noise))
        $g = [Math]::Max(0, [Math]::Min(255, 40 + $noise))
        $b = [Math]::Max(0, [Math]::Min(255, 100 + $noise))
        $voidStalker.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
    }
}
# Glowing purple eyes
for ($ey = 8; $ey -lt 11; $ey++) {
    for ($ex = 10; $ex -lt 12; $ex++) {
        $voidStalker.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 150, 80, 200))
    }
    for ($ex = 14; $ex -lt 16; $ex++) {
        $voidStalker.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 150, 80, 200))
    }
}
$voidStalker.Save("$entityPath\void_stalker.png")
$voidStalker.Dispose()
Write-Output "  Done: void_stalker.png"
# Cinder Elemental (64x32)
Write-Output "Creating Cinder Elemental texture..."
$cinderElemental = New-Object System.Drawing.Bitmap(64, 32)
# Orange/red fiery base
for ($y = 0; $y -lt 32; $y++) {
    for ($x = 0; $x -lt 64; $x++) {
        $noise = Get-Random -Minimum -15 -Maximum 15
        # Fire colors - oranges and reds
        if ((Get-Random -Minimum 0 -Maximum 10) -gt 7) {
            # Bright ember spots
            $cinderElemental.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, 255, 200, 100))
        } else {
            $r = [Math]::Max(0, [Math]::Min(255, 180 + $noise))
            $g = [Math]::Max(0, [Math]::Min(255, 80 + $noise))
            $b = [Math]::Max(0, [Math]::Min(255, 20 + $noise))
            $cinderElemental.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
        }
    }
}
# Bright glowing eyes
for ($ey = 8; $ey -lt 11; $ey++) {
    for ($ex = 10; $ex -lt 12; $ex++) {
        $cinderElemental.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 255, 255, 100))
    }
    for ($ex = 14; $ex -lt 16; $ex++) {
        $cinderElemental.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 255, 255, 100))
    }
}
$cinderElemental.Save("$entityPath\cinder_elemental.png")
$cinderElemental.Dispose()
Write-Output "  Done: cinder_elemental.png"
# Void Lord Boss (128x64 - larger boss texture)
Write-Output "Creating Void Lord texture..."
$voidLord = New-Object System.Drawing.Bitmap(128, 64)
# Very dark purple/black with void energy
for ($y = 0; $y -lt 64; $y++) {
    for ($x = 0; $x -lt 128; $x++) {
        $noise = Get-Random -Minimum -5 -Maximum 5
        # Add swirling void energy pattern
        $angle = [Math]::Atan2($y - 32, $x - 64)
        $dist = [Math]::Sqrt([Math]::Pow($x - 64, 2) + [Math]::Pow($y - 32, 2))
        $intensity = [Math]::Sin($dist / 5 + $angle * 3) * 20
        $r = [Math]::Max(0, [Math]::Min(255, 60 + $intensity + $noise))
        $g = [Math]::Max(0, [Math]::Min(255, 30 + $intensity/2 + $noise))
        $b = [Math]::Max(0, [Math]::Min(255, 100 + $intensity + $noise))
        $voidLord.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
    }
}
# Large glowing purple eyes
for ($ey = 20; $ey -lt 26; $ey++) {
    for ($ex = 54; $ex -lt 60; $ex++) {
        $voidLord.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 200, 100, 255))
    }
    for ($ex = 68; $ex -lt 74; $ex++) {
        $voidLord.SetPixel($ex, $ey, [System.Drawing.Color]::FromArgb(255, 200, 100, 255))
    }
}
$voidLord.Save("$entityPath\void_lord.png")
$voidLord.Dispose()
Write-Output "  Done: void_lord.png (boss)"
Write-Output ""
Write-Output "=== All entity textures created! ==="
Write-Output "Created 4 mob textures"