Add-Type -AssemblyName System.Drawing
$blockPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\src\main\resources\assets\elementaldimensions\textures\block"
$itemPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\src\main\resources\assets\elementaldimensions\textures\item"
# Create voidstone texture (dark gray stone)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $noise = Get-Random -Minimum 30 -Maximum 60
        $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $noise, $noise, $noise + 10))
    }
}
$bmp.Save("$blockPath\voidstone.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created voidstone.png"
# Create void_crystal texture (purple/blue glowing)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $r = Get-Random -Minimum 60 -Maximum 100
        $b = Get-Random -Minimum 100 -Maximum 160
        $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $r + 20, $b))
    }
}
$bmp.Save("$blockPath\void_crystal.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created void_crystal.png"
# Create nullrock texture (very dark stone)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $noise = Get-Random -Minimum 20 -Maximum 45
        $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $noise, $noise, $noise))
    }
}
$bmp.Save("$blockPath\nullrock.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created nullrock.png"
# Create dark_matter_block texture (almost black with blue tint)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        $r = Get-Random -Minimum 10 -Maximum 30
        $g = Get-Random -Minimum 5 -Maximum 15
        $b = Get-Random -Minimum 25 -Maximum 45
        $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
    }
}
$bmp.Save("$blockPath\dark_matter_block.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created dark_matter_block.png"
# Create void_glass texture (translucent dark glass)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        if (($x + $y) % 4 -eq 0) {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(180, 40, 40, 80))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(120, 30, 30, 60))
        }
    }
}
$bmp.Save("$blockPath\void_glass.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created void_glass.png"
# Create ethereal_grass texture (purple grass top with dark bottom)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        if ($y -lt 8) {
            $r = Get-Random -Minimum 70 -Maximum 90
            $g = Get-Random -Minimum 50 -Maximum 70
            $b = Get-Random -Minimum 90 -Maximum 110
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
        } else {
            $noise = Get-Random -Minimum 30 -Maximum 50
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $noise, $noise, $noise + 5))
        }
    }
}
$bmp.Save("$blockPath\ethereal_grass.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created ethereal_grass.png"
# Create void_essence item texture (purple orb)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        if (($x -ge 4 -and $x -le 11) -and ($y -ge 4 -and $y -le 11)) {
            $r = Get-Random -Minimum 80 -Maximum 120
            $g = Get-Random -Minimum 60 -Maximum 100
            $b = Get-Random -Minimum 120 -Maximum 160
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$itemPath\void_essence.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created void_essence.png"
# Create dark_matter item texture (very dark orb)
$bmp = New-Object System.Drawing.Bitmap(16, 16)
for ($y = 0; $y -lt 16; $y++) {
    for ($x = 0; $x -lt 16; $x++) {
        if (($x -ge 3 -and $x -le 12) -and ($y -ge 3 -and $y -le 12)) {
            $r = Get-Random -Minimum 10 -Maximum 25
            $g = Get-Random -Minimum 5 -Maximum 15
            $b = Get-Random -Minimum 30 -Maximum 50
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
        } else {
            $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
        }
    }
}
$bmp.Save("$itemPath\dark_matter.png", [System.Drawing.Imaging.ImageFormat]::Png)
$bmp.Dispose()
Write-Output "Created dark_matter.png"
# Create remaining void items
$items = @('void_crystal_shard', 'nullstone_ingot', 'ethereal_thread', 'void_lord_heart', 'void_altar_rune')
foreach ($item in $items) {
    $bmp = New-Object System.Drawing.Bitmap(16, 16)
    $r = Get-Random -Minimum 60 -Maximum 100
    $g = Get-Random -Minimum 40 -Maximum 80
    $b = Get-Random -Minimum 100 -Maximum 140
    for ($y = 0; $y -lt 16; $y++) {
        for ($x = 0; $x -lt 16; $x++) {
            if (($x -ge 4 -and $x -le 11) -and ($y -ge 4 -and $y -le 11)) {
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(255, $r, $g, $b))
            } else {
                $bmp.SetPixel($x, $y, [System.Drawing.Color]::FromArgb(0, 0, 0, 0))
            }
        }
    }
    $bmp.Save("$itemPath\$item.png", [System.Drawing.Imaging.ImageFormat]::Png)
    $bmp.Dispose()
    Write-Output "Created $item.png"
}
Write-Output "`nAll void textures created successfully!"