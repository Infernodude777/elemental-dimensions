@echo off
echo ========================================
echo Starting Elemental Dimensions Texture Generation
echo ========================================
echo.

cd /d "%~dp0"

echo Checking dependencies...
python -c "import craiyon" >nul 2>&1
if errorlevel 1 (
    echo ERROR: craiyon.py not installed
    echo Installing dependencies...
    pip install craiyon.py pillow requests
    if errorlevel 1 (
        echo Failed to install dependencies
        pause
        exit /b 1
    )
)

echo.
echo Starting texture generation...
echo This will take approximately 1-2 hours.
echo.

python generate_all_textures.py

echo.
echo ========================================
echo Generation script finished!
echo ========================================
pause
