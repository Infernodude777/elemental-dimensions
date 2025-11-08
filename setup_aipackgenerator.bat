@echo off
echo ========================================
echo AIPackGenerator Setup for Elemental Dimensions
echo ========================================
echo.

cd /d "%~dp0"

echo [1/4] Checking Python installation...
python --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Python is not installed or not in PATH
    echo Please download Python from https://www.python.org/downloads/
    pause
    exit /b 1
)
python --version

echo.
echo [2/4] Cloning AIPackGenerator repository...
if exist AIPackGenerator (
    echo AIPackGenerator folder already exists, skipping clone...
) else (
    git clone https://github.com/DWF967/AIPackGenerator.git
    if errorlevel 1 (
        echo ERROR: Failed to clone repository. Is Git installed?
        pause
        exit /b 1
    )
)

echo.
echo [3/4] Installing dependencies...
cd AIPackGenerator
pip install craiyon.py pillow requests
if errorlevel 1 (
    echo ERROR: Failed to install dependencies
    pause
    exit /b 1
)

echo.
echo [4/4] Setup complete!
echo.
echo ========================================
echo NEXT STEPS:
echo ========================================
echo 1. Review the prompts in TEXTURE_GENERATION_SETUP.md
echo 2. Run generate_all_textures.bat to start generation
echo 3. Wait for textures to generate (this may take 1-2 hours)
echo 4. Textures will be saved to ResourcePack_ElementalDimensions folder
echo ========================================
echo.

pause
