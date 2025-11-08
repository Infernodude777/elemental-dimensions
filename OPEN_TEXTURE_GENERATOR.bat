@echo off
echo ========================================
echo Opening Stable Diffusion Web UI
echo ========================================
echo.
echo This will open the free online texture generator.
echo Follow the instructions in GENERATE_TEXTURES_ONLINE.md
echo.
echo Starting with 11 Dimension Stones (highest priority)
echo.
pause

start https://stablediffusionweb.com/

echo.
echo ========================================
echo Browser opened!
echo ========================================
echo.
echo Next steps:
echo 1. Read GENERATE_TEXTURES_ONLINE.md for all prompts
echo 2. Generate each texture (copy/paste prompts)
echo 3. Resize to 16x16 using online tool
echo 4. Save to ResourcePack folder
echo.
echo Folders created at:
echo %cd%\ResourcePack_ElementalDimensions\assets\elementaldimensions\textures\
echo.
pause
