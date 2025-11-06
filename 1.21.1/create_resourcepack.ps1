# Create resource pack with all textures
$resourcePackPath = "resourcepack"
$packName = "Elemental Dimensions Textures"
# Create resource pack directory structure
New-Item -ItemType Directory -Force -Path "$resourcePackPath\assets\elementaldimensions\textures\block" | Out-Null
New-Item -ItemType Directory -Force -Path "$resourcePackPath\assets\elementaldimensions\textures\item" | Out-Null
New-Item -ItemType Directory -Force -Path "$resourcePackPath\assets\elementaldimensions\textures\entity" | Out-Null
# Copy all textures
Write-Output "Creating resource pack: $packName"
# Copy block textures
$blockTextures = Get-ChildItem -Path "src\main\resources\assets\elementaldimensions\textures\block" -Filter "*.png" -ErrorAction SilentlyContinue
if ($blockTextures) {
    Copy-Item -Path "src\main\resources\assets\elementaldimensions\textures\block\*" -Destination "$resourcePackPath\assets\elementaldimensions\textures\block\" -Force
    Write-Output "  Copied $($blockTextures.Count) block textures"
}
# Copy item textures
$itemTextures = Get-ChildItem -Path "src\main\resources\assets\elementaldimensions\textures\item" -Filter "*.png" -ErrorAction SilentlyContinue
if ($itemTextures) {
    Copy-Item -Path "src\main\resources\assets\elementaldimensions\textures\item\*" -Destination "$resourcePackPath\assets\elementaldimensions\textures\item\" -Force
    Write-Output "  Copied $($itemTextures.Count) item textures"
}
# Copy entity textures
$entityTextures = Get-ChildItem -Path "src\main\resources\assets\elementaldimensions\textures\entity" -Filter "*.png" -ErrorAction SilentlyContinue -Recurse
if ($entityTextures) {
    # Create subdirectories
    $entityDirs = Get-ChildItem -Path "src\main\resources\assets\elementaldimensions\textures\entity" -Directory -Recurse
    foreach ($dir in $entityDirs) {
        $relativePath = $dir.FullName.Substring((Get-Item "src\main\resources\assets\elementaldimensions\textures\entity").FullName.Length)
        New-Item -ItemType Directory -Force -Path "$resourcePackPath\assets\elementaldimensions\textures\entity$relativePath" | Out-Null
    }
    # Copy entity textures with subdirectories
    Copy-Item -Path "src\main\resources\assets\elementaldimensions\textures\entity\*" -Destination "$resourcePackPath\assets\elementaldimensions\textures\entity\" -Recurse -Force
    Write-Output "  Copied $($entityTextures.Count) entity textures"
}
# Create pack.mcmeta
$packMcmeta = @{
    pack = @{
        pack_format = 34
        description = "Elemental Dimensions - Custom textures for all dimensions, blocks, items, and entities"
    }
} | ConvertTo-Json -Depth 10
$packMcmeta | Set-Content -Path "$resourcePackPath\pack.mcmeta" -Encoding UTF8
# Create pack.png (icon)
Add-Type -AssemblyName System.Drawing
$packIcon = New-Object System.Drawing.Bitmap(128, 128)
$graphics = [System.Drawing.Graphics]::FromImage($packIcon)
# Create gradient background
$brush = New-Object System.Drawing.Drawing2D.LinearGradientBrush(
    (New-Object System.Drawing.Point(0, 0)),
    (New-Object System.Drawing.Point(128, 128)),
    [System.Drawing.Color]::FromArgb(138, 43, 226),  # BlueViolet
    [System.Drawing.Color]::FromArgb(75, 0, 130)     # Indigo
)
$graphics.FillRectangle($brush, 0, 0, 128, 128)
# Draw border
$pen = New-Object System.Drawing.Pen([System.Drawing.Color]::Gold, 4)
$graphics.DrawRectangle($pen, 2, 2, 124, 124)
$packIcon.Save("$resourcePackPath\pack.png", [System.Drawing.Imaging.ImageFormat]::Png)
$graphics.Dispose()
$packIcon.Dispose()
$brush.Dispose()
$pen.Dispose()
Write-Output "`nResource pack created successfully at: $resourcePackPath"
Write-Output "Pack format: 34 (Minecraft 1.21.x compatible)"
# Create README
$readme = @"
# Elemental Dimensions Resource Pack
This resource pack contains all custom textures for the Elemental Dimensions mod.
## Contents:
- Block textures for all 7 dimensions
- Item textures for all custom items
- Entity textures for all custom mobs
## Installation:
1. Copy this folder to your Minecraft resourcepacks directory
2. Enable the resource pack in Minecraft settings
3. Enjoy enhanced textures!
## Dimensions Included:
- Undead Realm
- Dreaming Depths
- Celestine Expanse
- Fungal Dominion
- Forgotten Archive
- Astral Frontier
- Gloomy Caverns
Generated: $(Get-Date -Format "yyyy-MM-dd HH:mm:ss")
"@
$readme | Set-Content -Path "$resourcePackPath\README.md" -Encoding UTF8
Write-Output "`nREADME.md created"