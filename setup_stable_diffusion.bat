@echo off
echo ========================================
echo Stable Diffusion WebUI Setup
echo ========================================
echo.

cd /d "%~dp0"

if not exist "stable-diffusion-webui" (
    echo Cloning Stable Diffusion WebUI...
    git clone https://github.com/AUTOMATIC1111/stable-diffusion-webui.git
    echo.
    echo Done! Now configuring...
    echo.

    REM Add --api flag to webui-user.bat
    cd stable-diffusion-webui
    echo @echo off > webui-user-temp.bat
    echo set PYTHON= >> webui-user-temp.bat
    echo set GIT= >> webui-user-temp.bat
    echo set VENV_DIR= >> webui-user-temp.bat
    echo set COMMANDLINE_ARGS=--api >> webui-user-temp.bat
    echo call webui.bat >> webui-user-temp.bat

    move /y webui-user-temp.bat webui-user.bat

    echo.
    echo ========================================
    echo Setup Complete!
    echo ========================================
    echo.
    echo Next steps:
    echo 1. Run: stable-diffusion-webui\webui-user.bat
    echo 2. Wait for it to download models and start
    echo 3. When you see "Running on http://127.0.0.1:7860", it's ready
    echo 4. Then run: python generate_local_sd.py
    echo.
    pause
) else (
    echo Stable Diffusion WebUI already installed!
    echo.
    echo Starting Stable Diffusion WebUI...
    echo Wait for "Running on http://127.0.0.1:7860" message
    echo.
    cd stable-diffusion-webui
    call webui-user.bat
)
