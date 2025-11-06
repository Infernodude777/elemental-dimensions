# Script to create placeholder textures for Undead Realm content
# Creates 16x16 colored placeholder PNG files
Add-Type -AssemblyName System.Drawing
$blockTexturePath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\resources\assets\elementaldimensions\textures\block"
$itemTexturePath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\resources\assets\elementaldimensions\textures\item"
# Create directories if they don't exist
if (-not (Test-Path $blockTexturePath)) {
    New-Item -ItemType Directory -Force -Path $blockTexturePath | Out-Null
}
if (-not (Test-Path $itemTexturePath)) {
    New-Item -ItemType Directory -Force -Path $itemTexturePath | Out-Null
}
# Function to create a colored 16x16 PNG
function Create-PlaceholderTexture {
    param (
        [string]$path,
        [System.Drawing.Color]$color
    )
    $bitmap = New-Object System.Drawing.Bitmap(16, 16)
    $graphics = [System.Drawing.Graphics]::FromImage($bitmap)
    $brush = New-Object System.Drawing.SolidBrush($color)
    $graphics.FillRectangle($brush, 0, 0, 16, 16)
    $graphics.Dispose()
    $brush.Dispose()
    $bitmap.Save($path, [System.Drawing.Imaging.ImageFormat]::Png)
    $bitmap.Dispose()
}
# Block textures with colors
$blockTextures = @{
    "cursed_bone" = [System.Drawing.Color]::FromArgb(255, 230, 230, 220)
    "necro_stone" = [System.Drawing.Color]::FromArgb(255, 40, 40, 45)
    "soul_sand_block" = [System.Drawing.Color]::FromArgb(255, 90, 70, 50)
    "corrupted_flesh" = [System.Drawing.Color]::FromArgb(255, 140, 50, 60)
    "grave_dirt" = [System.Drawing.Color]::FromArgb(255, 70, 60, 50)
    "tombstone" = [System.Drawing.Color]::FromArgb(255, 120, 120, 125)
    "spectral_glass" = [System.Drawing.Color]::FromArgb(200, 180, 220, 240)
    "wraith_lantern" = [System.Drawing.Color]::FromArgb(255, 100, 200, 220)
    "bone_bricks" = [System.Drawing.Color]::FromArgb(255, 220, 220, 210)
    "death_weed" = [System.Drawing.Color]::FromArgb(255, 60, 80, 50)
    "ectoplasm_block" = [System.Drawing.Color]::FromArgb(255, 150, 220, 100)
    "phantom_ore" = [System.Drawing.Color]::FromArgb(255, 120, 80, 180)
    "undead_portal_frame" = [System.Drawing.Color]::FromArgb(255, 100, 50, 140)
    "rotten_planks" = [System.Drawing.Color]::FromArgb(255, 100, 80, 60)
    "crypt_bricks" = [System.Drawing.Color]::FromArgb(255, 60, 60, 65)
    "soul_fire_block" = [System.Drawing.Color]::FromArgb(255, 80, 200, 220)
    "ghostwood_log" = [System.Drawing.Color]::FromArgb(255, 180, 160, 190)
    "ghostwood_leaves" = [System.Drawing.Color]::FromArgb(255, 100, 140, 130)
    "blood_crystal" = [System.Drawing.Color]::FromArgb(255, 200, 40, 40)
    "cursed_cobblestone" = [System.Drawing.Color]::FromArgb(255, 50, 50, 55)
    "necrotic_soil" = [System.Drawing.Color]::FromArgb(255, 100, 70, 110)
    "spirit_crystal" = [System.Drawing.Color]::FromArgb(255, 240, 240, 250)
    "dark_altar" = [System.Drawing.Color]::FromArgb(255, 30, 30, 35)
    "coffin_block" = [System.Drawing.Color]::FromArgb(255, 70, 50, 40)
    "chiseled_bone" = [System.Drawing.Color]::FromArgb(255, 230, 225, 215)
    "haunted_chains" = [System.Drawing.Color]::FromArgb(255, 130, 130, 135)
    "mausoleum_stone" = [System.Drawing.Color]::FromArgb(255, 80, 80, 85)
    "spectral_torch" = [System.Drawing.Color]::FromArgb(255, 120, 200, 230)
    "zombie_flesh_block" = [System.Drawing.Color]::FromArgb(255, 130, 180, 110)
    "bone_pillar" = [System.Drawing.Color]::FromArgb(255, 225, 220, 210)
}
# Item textures with colors
$itemTextures = @{
    "soul_reaper_sword" = [System.Drawing.Color]::FromArgb(255, 100, 80, 180)
    "necromancer_staff" = [System.Drawing.Color]::FromArgb(255, 60, 40, 100)
    "phantom_cloak" = [System.Drawing.Color]::FromArgb(255, 180, 200, 240)
    "cursed_bow" = [System.Drawing.Color]::FromArgb(255, 80, 60, 100)
    "soul_gem" = [System.Drawing.Color]::FromArgb(255, 100, 200, 250)
    "lich_crown" = [System.Drawing.Color]::FromArgb(255, 200, 180, 50)
    "void_totem" = [System.Drawing.Color]::FromArgb(255, 50, 30, 80)
    "spectral_orb" = [System.Drawing.Color]::FromArgb(255, 200, 220, 250)
    "graveyard_key" = [System.Drawing.Color]::FromArgb(255, 150, 150, 155)
    "wraith_essence" = [System.Drawing.Color]::FromArgb(255, 140, 180, 220)
    "bone_shard" = [System.Drawing.Color]::FromArgb(255, 240, 235, 225)
    "phantom_dust" = [System.Drawing.Color]::FromArgb(255, 190, 200, 230)
    "cursed_ingot" = [System.Drawing.Color]::FromArgb(255, 90, 70, 120)
    "ectoplasm" = [System.Drawing.Color]::FromArgb(255, 160, 230, 120)
    "lich_phylactery" = [System.Drawing.Color]::FromArgb(255, 120, 40, 140)
}
# Create block textures
foreach ($texture in $blockTextures.GetEnumerator()) {
    $path = Join-Path $blockTexturePath "$($texture.Key).png"
    Create-PlaceholderTexture -path $path -color $texture.Value
    Write-Output "Created block texture: $($texture.Key)"
}
# Create item textures
foreach ($texture in $itemTextures.GetEnumerator()) {
    $path = Join-Path $itemTexturePath "$($texture.Key).png"
    Create-PlaceholderTexture -path $path -color $texture.Value
    Write-Output "Created item texture: $($texture.Key)"
}
Write-Output "`nAll placeholder textures created successfully!"
Write-Output "Block textures: $($blockTextures.Count)"
Write-Output "Item textures: $($itemTextures.Count)"