"""
Elemental Dimensions - Stable Diffusion Local Texture Generator
Uses Automatic1111 WebUI API (unlimited, free, local)

SETUP FIRST:
1. Install Automatic1111 WebUI:
   git clone https://github.com/AUTOMATIC1111/stable-diffusion-webui.git
   cd stable-diffusion-webui

2. Edit webui-user.bat and add --api flag:
   set COMMANDLINE_ARGS=--api

3. Run: webui-user.bat
4. Wait for it to start at http://localhost:7860
5. Then run this script!
"""

import requests
import base64
from PIL import Image
from io import BytesIO
from pathlib import Path
import time
import json

# Configuration
API_URL = "http://localhost:7860"
BASE_DIR = Path("C:/Users/Nikhil/Desktop/elemental_dimensions/ResourcePack_ElementalDimensions/assets/elementaldimensions/textures")

# All textures to generate
TEXTURES = [
    # DIMENSION STONES (11)
    ("block", "inferno_stone", "dark charcoal stone block, glowing orange-red lava cracks, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "aqua_stone", "deep ocean blue stone block, cyan coral patterns, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "terran_stone", "brown stone block, emerald green crystal flecks, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "skybound_stone", "light sky blue stone block, white cloud streaks, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "celestial_stone", "deep purple stone block, white star dots, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "void_stone", "black stone block, dark purple veins, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "dream_stone", "pastel purple stone block, pink lavender swirls, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "fungal_stone", "yellow-green stone block, dark moss patches, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "archive_stone", "tan stone block, blue runic symbols, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "astral_stone", "dark purple stone block, pink-blue nebula wisps, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),
    ("block", "gloom_stone", "very dark gray stone block, black shadow veins, Minecraft pixel art texture, seamless tileable, simple flat design, 16x16 resolution"),

    # DIMENSION ORES (11)
    ("block", "inferno_ore", "dark stone background, bright orange flame crystal ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "aqua_ore", "blue stone background, cyan water droplet crystal ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "terran_ore", "brown stone background, emerald green crystal ore clusters, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "skybound_ore", "light blue stone background, white-yellow wind spiral crystal ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "celestial_ore", "purple stone background, pink cosmic crystal shard ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "void_ore", "black stone background, dark purple shadow crystal ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "dream_ore", "pastel stone background, rainbow prismatic crystal ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "fungal_ore", "green stone background, lime glowing mushroom ore clusters, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "archive_ore", "tan stone background, blue glowing runic glyph ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "astral_ore", "dark stone background, white-blue star crystal ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
    ("block", "gloom_ore", "gray stone background, translucent blue echo crystal ore deposits, Minecraft pixel art ore texture, seamless tileable, 16x16 resolution"),
]

def check_api_available():
    """Check if Stable Diffusion WebUI is running"""
    try:
        response = requests.get(f"{API_URL}/sdapi/v1/options", timeout=5)
        return response.status_code == 200
    except:
        return False

def generate_texture_sd(category, name, prompt, size=16):
    """Generate texture using local Stable Diffusion"""

    # Check if already exists
    output_path = BASE_DIR / category / f"{name}.png"
    if output_path.exists():
        print(f"⏭️  {name} already exists, skipping...")
        return True

    print(f"\n{'='*70}")
    print(f"Generating: {name}")
    print(f"Category: {category} | Size: {size}x{size}")
    print(f"Prompt: {prompt[:60]}...")
    print(f"{'='*70}")

    # Stable Diffusion API payload
    payload = {
        "prompt": prompt,
        "negative_prompt": "blurry, low quality, realistic, photograph, 3d render, complex, detailed, photorealistic",
        "steps": 20,
        "width": 512,
        "height": 512,
        "cfg_scale": 7,
        "sampler_name": "DPM++ 2M Karras",
        "seed": -1,
        "batch_size": 1,
    }

    try:
        print("📡 Sending request to local Stable Diffusion...")
        response = requests.post(f"{API_URL}/sdapi/v1/txt2img", json=payload, timeout=120)

        if response.status_code != 200:
            print(f"❌ API error: {response.status_code}")
            return False

        result = response.json()

        if not result.get('images'):
            print("❌ No images in response")
            return False

        print("✅ Image generated! Processing...")

        # Decode base64 image
        img_data = base64.b64decode(result['images'][0])
        img = Image.open(BytesIO(img_data))

        # Resize to target size using NEAREST for pixel art
        print(f"✓ Resizing to {size}x{size}...")
        img = img.resize((size, size), Image.Resampling.NEAREST)

        # Ensure RGBA
        if img.mode != 'RGBA':
            img = img.convert('RGBA')

        # Save
        output_path.parent.mkdir(parents=True, exist_ok=True)
        img.save(output_path, 'PNG', optimize=True)

        print(f"✅ SUCCESS! Saved {name}.png")
        return True

    except Exception as e:
        print(f"❌ ERROR: {e}")
        return False

def main():
    print("="*70)
    print(" ELEMENTAL DIMENSIONS - LOCAL AI TEXTURE GENERATOR")
    print(" Using Stable Diffusion WebUI (Unlimited & Free!)")
    print("="*70)

    # Check if API is available
    print("\n🔍 Checking if Stable Diffusion WebUI is running...")
    if not check_api_available():
        print("\n❌ ERROR: Cannot connect to Stable Diffusion WebUI!")
        print("\n📋 SETUP INSTRUCTIONS:")
        print("1. Open a NEW terminal")
        print("2. Run these commands:")
        print("   cd C:\\Users\\Nikhil\\Desktop\\elemental_dimensions\\stable-diffusion-webui")
        print("   .\\webui-user.bat")
        print("3. Wait for it to start (you'll see: 'Running on http://127.0.0.1:7860')")
        print("4. Then run this script again!")
        print("\nMake sure you added --api flag to webui-user.bat!")
        return

    print("✅ Stable Diffusion WebUI is running!\n")

    print(f"📊 Textures to generate: {len(TEXTURES)}")
    print(f"⏱️  Estimated time: ~{len(TEXTURES) * 0.5} minutes (30 sec per texture)")
    print(f"📁 Output: {BASE_DIR}\n")

    input("Press ENTER to start generation...")

    start_time = time.time()
    success_count = 0
    failed = []

    for i, (category, name, prompt) in enumerate(TEXTURES, 1):
        print(f"\n{'*'*70}")
        print(f"[{i}/{len(TEXTURES)}] Processing {name}...")
        print(f"{'*'*70}")

        if generate_texture_sd(category, name, prompt, 16):
            success_count += 1
        else:
            failed.append(name)

        print(f"\n✅ Progress: {success_count}/{len(TEXTURES)} complete")

        # Small delay between requests
        if i < len(TEXTURES):
            time.sleep(2)

    # Final report
    elapsed_time = time.time() - start_time
    minutes = int(elapsed_time // 60)
    seconds = int(elapsed_time % 60)

    print(f"\n\n{'='*70}")
    print(" GENERATION COMPLETE!")
    print(f"{'='*70}")
    print(f"✅ Successful: {success_count}/{len(TEXTURES)}")
    if failed:
        print(f"❌ Failed: {len(failed)}")
        print(f"   {', '.join(failed)}")
    print(f"⏱️  Total time: {minutes}m {seconds}s")
    print(f"📁 Output: {BASE_DIR}")
    print(f"{'='*70}\n")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\n⚠️  Stopped by user. Progress saved.\n")
