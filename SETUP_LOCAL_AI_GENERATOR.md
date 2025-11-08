# Local AI Texture Generator Setup Guide

## Problem: Free online AIs are unreliable/overloaded

The GitHub tools you showed me use these services:
- **AIPackGenerator** → Uses Craiyon (currently down)
- **TextureMaker** → Uses Stable Diffusion (requires GPU setup)
- **MC-TextureGen** → Not AI-based (just modifies existing textures)

## Best Solution: Use Stable Diffusion Locally

Since you need **unlimited, free, offline generation**, here's the best approach:

---

## Option 1: Use Automatic1111 WebUI (Recommended - Unlimited Free)

This runs Stable Diffusion on your computer with NO API limits!

### Step 1: Install Automatic1111

1. **Download Stable Diffusion WebUI:**
   ```powershell
   cd C:\Users\Nikhil\Desktop\elemental_dimensions
   git clone https://github.com/AUTOMATIC1111/stable-diffusion-webui.git
   ```

2. **Run the installer:**
   ```powershell
   cd stable-diffusion-webui
   .\webui-user.bat
   ```

   This will:
   - Download Python dependencies
   - Download the Stable Diffusion model (~4GB)
   - Launch a web interface at http://localhost:7860

3. **Open your browser to:** http://localhost:7860

### Step 2: Generate Textures

1. In the web UI, enter prompt:
   ```
   dark charcoal stone block, glowing orange-red lava cracks, Minecraft texture, pixel art, simple, flat, 16x16
   ```

2. Settings to use:
   - **Width:** 512
   - **Height:** 512
   - **Steps:** 20
   - **CFG Scale:** 7
   - **Sampling:** DPM++ 2M Karras

3. Click **Generate**

4. Right-click image → Save as `inferno_stone.png`

5. Resize to 16x16 (we'll automate this)

---

## Option 2: Automate with Python Script

I can create a script that:
1. Connects to your local Stable Diffusion server
2. Generates all 115 textures automatically
3. Resizes them to 16x16/32x32
4. Saves to correct folders

**Requirements:**
- Stable Diffusion WebUI running at http://localhost:7860
- API enabled (add `--api` to `webui-user.bat`)

Would you like me to create this automated script?

---

## Option 3: Use ComfyUI (More Advanced)

ComfyUI is faster but more complex:
```powershell
git clone https://github.com/comfyanonymous/ComfyUI.git
cd ComfyUI
python -m pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cu121
python -m pip install -r requirements.txt
python main.py
```

---

## Quick Comparison

| Method | Speed | Quality | GPU Needed? | Unlimited? |
|--------|-------|---------|-------------|------------|
| Craiyon API | Slow | Medium | ❌ No | ⚠️ Rate limited |
| Bing Creator | Fast | High | ❌ No | ❌ 15/day |
| Leonardo.ai | Fast | High | ❌ No | ⚠️ 150/day |
| Stable Diffusion Local | **Fast** | **High** | ✅ Yes* | ✅ **UNLIMITED** |

*Can run on CPU but very slow (5+ min per image). GPU recommended (30 sec per image).

---

## What's Your GPU?

Check with:
```powershell
nvidia-smi
```

If you have an NVIDIA GPU with 4GB+ VRAM, Stable Diffusion will work great!
If not, we can use CPU mode (slower but still works).

---

## My Recommendation

**For unlimited free generation:**
1. Install Automatic1111 WebUI (easiest)
2. Let me create an automated Python script
3. Generate all 115 textures overnight
4. Wake up to complete texture pack!

Want me to set this up for you?
