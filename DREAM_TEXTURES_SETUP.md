# Dream Textures Setup for Minecraft Resource Pack Generation

## Complete Setup Guide (15 minutes)

### Step 1: Install Blender (Free)

1. Download Blender: https://www.blender.org/download/
2. Install it (default options are fine)

### Step 2: Install Dream Textures Add-on

1. **Download Dream Textures:**
   ```powershell
   cd C:\Users\Nikhil\Desktop\elemental_dimensions
   git clone https://github.com/carson-katri/dream-textures.git
   ```

2. **Install in Blender:**
   - Open Blender
   - Go to: Edit → Preferences → Add-ons
   - Click "Install..." button
   - Navigate to: `C:\Users\Nikhil\Desktop\elemental_dimensions\dream-textures`
   - Select the `.zip` or folder
   - Enable the checkbox for "Dream Textures"

3. **Download Stable Diffusion Model:**
   - In Blender, open Dream Textures panel (N key → Dream)
   - Click "Download Model" (it will download ~4GB)
   - Select model: `stable-diffusion-v1-5` (best for textures)

### Step 3: Generate Your First Texture

1. In Blender, press N to open side panel
2. Go to "Dream" tab
3. Enter prompt: `dark charcoal stone block, glowing orange-red lava cracks, Minecraft texture, pixel art, seamless, tileable`
4. Enable "Seamless" option
5. Click "Generate"
6. Texture appears in Shader Editor!

### Step 4: Automate the Process

I'll create a Python script that:
- Runs Blender in headless mode (no UI, faster)
- Generates all 115 textures automatically
- Exports as PNG with correct names
- Resizes to 16x16/32x32
- Saves to ResourcePack folders

---

## Alternative: Use the Simple Script Approach

If you don't want to use Blender, I can modify **texture_from_prompt.py** to:

1. Use your own local Stable Diffusion instance
2. Generate all textures in batch
3. Auto-resize and save

This is **simpler** but requires setting up Stable Diffusion first.

---

## Fastest Solution Right Now: Stable Diffusion WebUI

Actually, the **absolute easiest** is still Automatic1111 WebUI because:

✅ No Blender needed
✅ One-command install
✅ I create a script that auto-generates everything
✅ Works with or without GPU

Let me set this up for you now - which do you prefer?

**Option A:** Dream Textures (Blender, more features)
**Option B:** Automatic1111 WebUI (simplest, recommended)
**Option C:** Simple Python script with local Stable Diffusion
