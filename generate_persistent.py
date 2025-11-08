"""
Elemental Dimensions - PERSISTENT Texture Generation
Will retry indefinitely until all textures are generated successfully
Handles API failures gracefully and keeps trying
"""

from craiyon import Craiyon
from PIL import Image
import base64
from pathlib import Path
from io import BytesIO
import time
import random

print("="*70)
print(" ELEMENTAL DIMENSIONS - PERSISTENT TEXTURE GENERATOR")
print(" Will retry until ALL textures are generated successfully!")
print("="*70)
print("\nInitializing...")

gen = Craiyon()
BASE_DIR = Path("C:/Users/Nikhil/Desktop/elemental_dimensions/ResourcePack_ElementalDimensions/assets/elementaldimensions/textures")

# Complete texture list
TEXTURES = [
    # DIMENSION STONES (11)
    ("block", "inferno_stone", "dark charcoal stone block, glowing orange-red lava cracks, Minecraft texture, pixel art"),
    ("block", "aqua_stone", "deep ocean blue stone block, cyan coral patterns, Minecraft texture, pixel art"),
    ("block", "terran_stone", "brown stone block, emerald green crystal flecks, Minecraft texture, pixel art"),
    ("block", "skybound_stone", "light sky blue stone block, white cloud streaks, Minecraft texture, pixel art"),
    ("block", "celestial_stone", "deep purple stone block, white star dots, Minecraft texture, pixel art"),
    ("block", "void_stone", "black stone block, dark purple veins, Minecraft texture, pixel art"),
    ("block", "dream_stone", "pastel purple stone block, pink lavender swirls, Minecraft texture, pixel art"),
    ("block", "fungal_stone", "yellow-green stone block, dark moss patches, Minecraft texture, pixel art"),
    ("block", "archive_stone", "tan stone block, blue runic symbols, Minecraft texture, pixel art"),
    ("block", "astral_stone", "dark purple stone block, pink-blue nebula wisps, Minecraft texture, pixel art"),
    ("block", "gloom_stone", "very dark gray stone block, black shadow veins, Minecraft texture, pixel art"),

    # DIMENSION ORES (11)
    ("block", "inferno_ore", "dark stone, bright orange flame crystals, Minecraft ore texture, pixel art"),
    ("block", "aqua_ore", "blue stone, cyan water droplet crystals, Minecraft ore texture, pixel art"),
    ("block", "terran_ore", "brown stone, emerald green crystal clusters, Minecraft ore texture, pixel art"),
    ("block", "skybound_ore", "light blue stone, white-yellow wind spiral crystals, Minecraft ore texture, pixel art"),
    ("block", "celestial_ore", "purple stone, pink cosmic crystal shards, Minecraft ore texture, pixel art"),
    ("block", "void_ore", "black stone, dark purple shadow crystals, Minecraft ore texture, pixel art"),
    ("block", "dream_ore", "pastel stone, rainbow prismatic crystals, Minecraft ore texture, pixel art"),
    ("block", "fungal_ore", "green stone, lime glowing mushroom clusters, Minecraft ore texture, pixel art"),
    ("block", "archive_ore", "tan stone, blue glowing runic glyphs, Minecraft ore texture, pixel art"),
    ("block", "astral_ore", "dark stone, white-blue star crystals, Minecraft ore texture, pixel art"),
    ("block", "gloom_ore", "gray stone, translucent blue echo crystals, Minecraft ore texture, pixel art"),
]

def check_if_generated(category, name):
    """Check if texture already exists"""
    output_path = BASE_DIR / category / f"{name}.png"
    return output_path.exists()

def generate_texture_persistent(category, name, prompt, size=16):
    """Generate texture with INFINITE retries until success"""

    # Skip if already generated
    if check_if_generated(category, name):
        print(f"⏭️  {name} already exists, skipping...")
        return True

    print(f"\n{'='*70}")
    print(f"Generating: {name}")
    print(f"Category: {category} | Size: {size}x{size}")
    print(f"Prompt: {prompt[:60]}...")
    print(f"{'='*70}")

    attempt = 0
    base_wait = 30  # Start with 30 second waits

    while True:  # Loop forever until success
        attempt += 1
        try:
            if attempt > 1:
                # Increase wait time for each retry, max 120 seconds
                wait_time = min(base_wait + (attempt * 10), 120)
                print(f"⏳ Waiting {wait_time} seconds before retry {attempt}...")
                time.sleep(wait_time)

            print(f"📡 Requesting from Craiyon API (attempt {attempt})...")
            result = gen.generate(prompt)

            if not result or not hasattr(result, 'images') or not result.images:
                print(f"⚠️  No images in response (attempt {attempt})")
                continue  # Try again

            print(f"✅ API responded! Processing image...")

            # Get best image
            img_data = base64.b64decode(result.images[0])
            img = Image.open(BytesIO(img_data))

            # Resize with NEAREST for pixel art
            print(f"✓ Resizing to {size}x{size}...")
            img = img.resize((size, size), Image.Resampling.NEAREST)

            # Ensure RGBA
            if img.mode != 'RGBA':
                img = img.convert('RGBA')

            # Save
            output_path = BASE_DIR / category / f"{name}.png"
            output_path.parent.mkdir(parents=True, exist_ok=True)
            img.save(output_path, 'PNG', optimize=True)

            print(f"✅ SUCCESS! Saved {name}.png")
            return True

        except KeyboardInterrupt:
            print("\n\n⚠️  Stopped by user!")
            raise
        except Exception as e:
            error_msg = str(e)
            print(f"❌ ERROR (attempt {attempt}): {error_msg}")
            # Just continue the loop - will retry automatically
            continue

def main():
    print(f"\n📊 Textures to generate: {len(TEXTURES)}")
    print(f"\n⏱️  This will run UNTIL ALL TEXTURES ARE COMPLETE")
    print(f"💡  Script will retry failed textures indefinitely")
    print(f"💡  You can safely minimize this window\n")

    input("Press ENTER to start infinite retry mode...")

    start_time = time.time()
    success_count = 0

    print(f"\n\n{'#'*70}")
    print("# STARTING GENERATION - WILL NOT STOP UNTIL COMPLETE")
    print(f"{'#'*70}\n")

    for i, (category, name, prompt) in enumerate(TEXTURES, 1):
        print(f"\n{'*'*70}")
        print(f"[{i}/{len(TEXTURES)}] Processing {name}...")
        print(f"{'*'*70}")

        # This will retry forever until it works
        if generate_texture_persistent(category, name, prompt, 16):
            success_count += 1
            print(f"\n✅ Progress: {success_count}/{len(TEXTURES)} complete")

        # Add random delay between successful generations (25-35 seconds)
        if i < len(TEXTURES):
            wait_time = random.randint(25, 35)
            print(f"\n⏸️  Waiting {wait_time} seconds before next texture...")
            time.sleep(wait_time)

    # Final report
    elapsed_time = time.time() - start_time
    hours = int(elapsed_time // 3600)
    minutes = int((elapsed_time % 3600) // 60)

    print(f"\n\n{'='*70}")
    print(" 🎉 ALL TEXTURES GENERATED SUCCESSFULLY! 🎉")
    print(f"{'='*70}")
    print(f"✅ Total: {success_count}/{len(TEXTURES)}")
    print(f"⏱️  Total time: {hours}h {minutes}m")
    print(f"📁 Output: {BASE_DIR}")
    print(f"{'='*70}\n")

    print("\n✨ Next steps:")
    print("1. Review textures in ResourcePack folder")
    print("2. Copy to %appdata%\\.minecraft\\resourcepacks\\")
    print("3. Test in Minecraft!")
    print("\n🎨 Enjoy your AI-generated texture pack!\n")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\n⚠️  Generation stopped. Progress saved.")
        print("Re-run to continue from where you left off.\n")
