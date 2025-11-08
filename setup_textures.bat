@echo off
REM Elemental Dimensions - Texture Organization Helper
REM This script creates the folder structure for textures

echo ========================================
echo Elemental Dimensions Texture Setup
echo ========================================
echo.

REM Create main texture directories
set BLOCK_DIR=ResourcePack_ElementalDimensions\assets\elementaldimensions\textures\block
set ITEM_DIR=ResourcePack_ElementalDimensions\assets\elementaldimensions\textures\item

echo Creating texture directories...

if not exist "%BLOCK_DIR%" (
    mkdir "%BLOCK_DIR%"
    echo Created: %BLOCK_DIR%
) else (
    echo Already exists: %BLOCK_DIR%
)

if not exist "%ITEM_DIR%" (
    mkdir "%ITEM_DIR%"
    echo Created: %ITEM_DIR%
) else (
    echo Already exists: %ITEM_DIR%
)

echo.
echo ========================================
echo Folder structure ready!
echo ========================================
echo.
echo Next steps:
echo 1. Generate textures using AI (see AI_TEXTURE_GENERATION_GUIDE.md)
echo 2. Resize all textures to 16x16 pixels
echo 3. Save block textures to: %BLOCK_DIR%
echo 4. Save item textures to: %ITEM_DIR%
echo 5. Use F3+T in-game to reload resource pack
echo.
echo Texture Checklist:
echo [ ] 11 Dimension Stones (inferno_stone.png, aqua_stone.png, etc.)
echo [ ] 11 Dimension Ores (blazing_ore.png, tidal_crystal.png, etc.)
echo [ ] 50+ Custom Blocks (atlantis_pillar.png, cloud_block.png, etc.)
echo [ ] 4 Essences (fire_essence.png, water_essence.png, etc.)
echo.
echo See TEXTURE_GUIDE.md for complete list!
echo ========================================
pause
