@echo off
echo ╔═══════════════════════════════════════════════════════════╗
echo ║   Elemental Dimensions - Asset Setup Tool                ║
echo ╚═══════════════════════════════════════════════════════════╝
echo.

echo Creating asset directory structure...
echo.

set "ASSETS=src\main\resources\assets\elementaldimensions"

if not exist "%ASSETS%\textures\block" mkdir "%ASSETS%\textures\block"
if not exist "%ASSETS%\textures\item" mkdir "%ASSETS%\textures\item"
if not exist "%ASSETS%\textures\entity" mkdir "%ASSETS%\textures\entity"
if not exist "%ASSETS%\sounds\music\firelands" mkdir "%ASSETS%\sounds\music\firelands"
if not exist "%ASSETS%\sounds\music\aquatica" mkdir "%ASSETS%\sounds\music\aquatica"
if not exist "%ASSETS%\sounds\music\terra" mkdir "%ASSETS%\sounds\music\terra"
if not exist "%ASSETS%\sounds\music\skyreach" mkdir "%ASSETS%\sounds\music\skyreach"
if not exist "%ASSETS%\sounds\music\celestial" mkdir "%ASSETS%\sounds\music\celestial"
if not exist "%ASSETS%\sounds\music\void" mkdir "%ASSETS%\sounds\music\void"
if not exist "%ASSETS%\sounds\ambient" mkdir "%ASSETS%\sounds\ambient"

echo [+] Directories created!
echo.
echo ═══════════════════════════════════════════════════════════
echo ASSET CHECKLIST
echo ═══════════════════════════════════════════════════════════
echo.
echo TEXTURES (16x16 PNG):
echo   Block: 6 files needed
echo   Items: 8 files needed  
echo   Entities: 8 files needed (64x64)
echo.
echo MUSIC (OGG format):
echo   18 tracks total across 6 dimensions
echo.
echo SOUNDS (OGG format):
echo   3 ambient sounds
echo.
echo ═══════════════════════════════════════════════════════════
echo DOCUMENTATION
echo ═══════════════════════════════════════════════════════════
echo.
echo Read these files for complete instructions:
echo.
echo   1. STEP_BY_STEP_TUTORIAL.md - Beginner-friendly walkthrough
echo   2. QUICK_ASSET_LINKS.md - Direct download links
echo   3. ASSET_ACQUISITION_GUIDE.md - Complete reference guide
echo.
echo ═══════════════════════════════════════════════════════════
echo WHERE TO GET ASSETS
echo ═══════════════════════════════════════════════════════════
echo.
echo MUSIC: https://incompetech.com
echo SOUNDS: https://freesound.org  
echo TEXTURES: https://opengameart.org
echo.
echo TIP: Start with STEP_BY_STEP_TUTORIAL.md!
echo.
echo [✓] Setup complete!
echo.
pause
