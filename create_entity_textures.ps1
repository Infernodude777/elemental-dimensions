# Script to create entity textures for Undead Realm mobs
Add-Type -AssemblyName System.Drawing
$texturePath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\resources\assets\elementaldimensions\textures\entity"
if (-not (Test-Path $texturePath)) {
    New-Item -ItemType Directory -Force -Path $texturePath | Out-Null
}
function Create-EntityTexture {
    param (
        [string]$path,
        [System.Drawing.Color]$color
    )
    $bitmap = New-Object System.Drawing.Bitmap(64, 64)
    $graphics = [System.Drawing.Graphics]::FromImage($bitmap)
    $brush = New-Object System.Drawing.SolidBrush($color)
    $graphics.FillRectangle($brush, 0, 0, 64, 64)
    $graphics.Dispose()
    $brush.Dispose()
    $bitmap.Save($path, [System.Drawing.Imaging.ImageFormat]::Png)
    $bitmap.Dispose()
}
# Entity textures with colors
$entityTextures = @{
    "zombie_miner" = [System.Drawing.Color]::FromArgb(255, 100, 150, 100)
    "skeleton_archer" = [System.Drawing.Color]::FromArgb(255, 220, 220, 210)
    "ghost" = [System.Drawing.Color]::FromArgb(200, 230, 230, 250)
    "wraith" = [System.Drawing.Color]::FromArgb(220, 180, 200, 240)
    "banshee" = [System.Drawing.Color]::FromArgb(230, 200, 220, 250)
    "necromantic_skeleton" = [System.Drawing.Color]::FromArgb(255, 80, 60, 100)
    "phantom_knight" = [System.Drawing.Color]::FromArgb(255, 150, 150, 200)
    "graveyard_guardian" = [System.Drawing.Color]::FromArgb(255, 100, 100, 120)
    "soul_stealer" = [System.Drawing.Color]::FromArgb(240, 100, 180, 220)
    "bone_dragon" = [System.Drawing.Color]::FromArgb(255, 240, 240, 230)
    "lich_king" = [System.Drawing.Color]::FromArgb(255, 100, 50, 150)
}
foreach ($texture in $entityTextures.GetEnumerator()) {
    $path = Join-Path $texturePath "$($texture.Key).png"
    Create-EntityTexture -path $path -color $texture.Value
    Write-Output "Created entity texture: $($texture.Key)"
}
Write-Output "`nAll entity textures created successfully!"
Write-Output "Total textures: $($entityTextures.Count)"