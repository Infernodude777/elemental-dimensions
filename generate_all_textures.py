"""
Elemental Dimensions - Texture Generation Script
Uses Craiyon API to generate all 146 textures for the mod

Usage:
    python generate_all_textures.py

Requirements:
    pip install craiyon.py pillow requests
"""

import os
import time
from pathlib import Path
try:
    from craiyon import Craiyon
    from PIL import Image
    import requests
    from io import BytesIO
except ImportError:
    print("ERROR: Missing dependencies!")
    print("Please run: pip install craiyon.py pillow requests")
    exit(1)

# Initialize Craiyon API
generator = Craiyon()

# Base output directory
OUTPUT_DIR = Path("ResourcePack_ElementalDimensions/assets/elementaldimensions/textures")

# Texture definitions
TEXTURES = {
    "blocks": [
        # Dimension Stones
        ("inferno_stone", "dark charcoal gray stone with bright orange-red glowing lava web cracks, rough volcanic texture, Minecraft pixel art, 16x16, seamless tileable"),
        ("aqua_stone", "deep ocean blue stone with cyan coral ridge patterns, smooth underwater rock, Minecraft pixel art, 16x16, seamless tileable"),
        ("terran_stone", "rich brown stone with emerald green crystal flecks, earthy cave rock, Minecraft pixel art, 16x16, seamless tileable"),
        ("skybound_stone", "light sky blue stone with white wispy cloud streaks, airy ethereal, Minecraft pixel art, 16x16, seamless tileable"),
        ("celestial_stone", "deep royal purple stone with tiny white constellation stars, Minecraft pixel art, 16x16, seamless tileable"),
        ("void_stone", "nearly black stone with dark purple vein cracks, ominous shadow, Minecraft pixel art, 16x16, seamless tileable"),
        ("dream_stone", "soft pastel purple stone with pink-lavender ethereal swirling mist, surreal, Minecraft pixel art, 16x16, seamless tileable"),
        ("fungal_stone", "sickly yellow-green stone with dark green moss patches and brown decay spots, Minecraft pixel art, 16x16, seamless tileable"),
        ("archive_stone", "aged sandy tan stone with ancient blue-gray carved runic symbols, weathered, Minecraft pixel art, 16x16, seamless tileable"),
        ("astral_stone", "dark purple-black stone with bright pink-blue floating nebula wisps, Minecraft pixel art, 16x16, seamless tileable"),
        ("gloom_stone", "very dark charcoal gray stone with pitch black spreading shadow veins, Minecraft pixel art, 16x16, seamless tileable"),

        # Dimension Ores
        ("inferno_ore", "dark stone with 3-4 bright orange-red flame-shaped crystals, yellow inner glow, Minecraft ore, 16x16 pixel art, seamless"),
        ("aqua_ore", "blue stone with light cyan translucent water droplet crystals and bubbles, Minecraft ore, 16x16 pixel art, seamless"),
        ("terran_ore", "brown stone with sharp faceted emerald green crystal clusters, Minecraft ore, 16x16 pixel art, seamless"),
        ("skybound_ore", "light blue stone with white-yellow swirling wind spiral crystals, Minecraft ore, 16x16 pixel art, seamless"),
        ("celestial_ore", "purple stone with pink-purple shimmering cosmic crystal shards, starlight, Minecraft ore, 16x16 pixel art, seamless"),
        ("void_ore", "black stone with dark purple shadow crystals and smoky wisps, Minecraft ore, 16x16 pixel art, seamless"),
        ("dream_ore", "pastel stone with rainbow prismatic iridescent color-shifting crystals, Minecraft ore, 16x16 pixel art, seamless"),
        ("fungal_ore", "green-brown stone with bright lime-green glowing bioluminescent mushroom clusters, Minecraft ore, 16x16 pixel art, seamless"),
        ("archive_ore", "tan stone with brilliant blue glowing pulsing runic glyphs, Minecraft ore, 16x16 pixel art, seamless"),
        ("astral_ore", "dark stone with white-blue twinkling star-shaped galaxy crystals, Minecraft ore, 16x16 pixel art, seamless"),
        ("gloom_ore", "gray stone with faint translucent gray-blue echo ripple crystals, Minecraft ore, 16x16 pixel art, seamless"),
    ],

    "items": [
        # Essences
        ("flame_essence", "glowing flame droplet, orange-to-red gradient, bright yellow core, ember particles, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("water_essence", "translucent cyan-blue water droplet with trapped air bubble, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("earth_essence", "faceted emerald green crystal shard with sharp geometric angles, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("wind_essence", "swirling white-blue tornado vortex wind spiral, rapid rotation, Minecraft item icon, 16x16 pixel art, transparent background"),

        # Hearts
        ("inferno_heart", "bright red glowing heart shape with orange flickering flame patterns, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("aquatic_heart", "cyan-blue glowing heart with rippling water wave patterns, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("terran_heart", "emerald green glowing heart with solid brown-green rocky texture, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("skybound_heart", "pure white glowing heart with ethereal wispy edges, light blue highlights, Minecraft item icon, 16x16 pixel art, transparent background"),

        # Dream Set Tools
        ("dream_sword", "ethereal purple sword with glowing lavender blade, semi-transparent edges, pink-purple aura, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("dream_pickaxe", "purple pickaxe with semi-transparent head, lavender-pink ethereal glow, wispy mist, Minecraft item icon, 16x16 pixel art, transparent background"),
        ("dream_axe", "purple axe with glowing blade edge, transparent purple handle, pink highlights, Minecraft item icon, 16x16 pixel art, transparent background"),
    ],

    "entity": [
        # Bosses (32x32)
        ("pyros", "massive red-orange lava demon with huge curved black horns, glowing yellow eyes, molten lava flowing down body, Minecraft mob sprite, 32x32 pixel art, front facing, transparent background"),
        ("leviathan", "gigantic serpentine blue sea dragon with detailed cyan scales, fierce eyes, coiled body, Minecraft mob sprite, 32x32 pixel art, front facing, transparent background"),
        ("goliath", "colossal brown stone golem with massive rocky body, glowing orange core eyes, inner light cracks, Minecraft mob sprite, 32x32 pixel art, front facing, transparent background"),
    ]
}

def generate_texture(name, prompt, category, size=16):
    """Generate a single texture using Craiyon API"""
    print(f"\n{'='*60}")
    print(f"Generating: {name}")
    print(f"Category: {category}")
    print(f"Prompt: {prompt[:80]}...")
    print(f"{'='*60}")

    try:
        # Generate image
        print("⏳ Requesting generation from Craiyon API...")
        result = generator.generate(prompt)

        if not result.images:
            print(f"❌ No images generated for {name}")
            return False

        # Get the first (best) image
        image_url = result.images[0]
        print(f"✓ Image generated, downloading...")

        # Download image
        response = requests.get(image_url)
        img = Image.open(BytesIO(response.content))

        # Resize to target size
        print(f"✓ Resizing to {size}x{size}...")
        img = img.resize((size, size), Image.Resampling.NEAREST)

        # Convert to RGBA (for transparency)
        if img.mode != 'RGBA':
            img = img.convert('RGBA')

        # Save
        output_path = OUTPUT_DIR / category / f"{name}.png"
        output_path.parent.mkdir(parents=True, exist_ok=True)
        img.save(output_path, 'PNG')

        print(f"✅ Saved to: {output_path}")
        return True

    except Exception as e:
        print(f"❌ Error generating {name}: {str(e)}")
        return False

def main():
    print("""
    ╔═══════════════════════════════════════════════════════════╗
    ║   ELEMENTAL DIMENSIONS - TEXTURE GENERATOR                ║
    ║   Powered by Craiyon AI                                   ║
    ╚═══════════════════════════════════════════════════════════╝
    """)

    # Count total textures
    total = sum(len(textures) for textures in TEXTURES.values())
    print(f"\n📊 Total textures to generate: {total}")
    print(f"⏱️  Estimated time: {total * 2} minutes ({total * 2 / 60:.1f} hours)")
    print("\n⚠️  Note: Craiyon API is free but may have rate limits.")
    print("    Generation will pause between batches if needed.\n")

    input("Press ENTER to start generation...")

    # Statistics
    success_count = 0
    fail_count = 0
    start_time = time.time()

    # Generate all textures
    for category, texture_list in TEXTURES.items():
        print(f"\n\n{'#'*60}")
        print(f"# CATEGORY: {category.upper()}")
        print(f"# Count: {len(texture_list)}")
        print(f"{'#'*60}\n")

        for name, prompt in texture_list:
            # Determine size based on category
            size = 32 if category == "entity" else 16

            # Generate
            if generate_texture(name, prompt, category, size):
                success_count += 1
            else:
                fail_count += 1

            # Rate limiting - pause between generations
            print("⏸️  Waiting 10 seconds before next generation...")
            time.sleep(10)

    # Final report
    elapsed_time = time.time() - start_time
    print(f"\n\n{'='*60}")
    print(f"GENERATION COMPLETE!")
    print(f"{'='*60}")
    print(f"✅ Successful: {success_count}")
    print(f"❌ Failed: {fail_count}")
    print(f"⏱️  Total time: {elapsed_time / 60:.1f} minutes")
    print(f"📁 Output: {OUTPUT_DIR.absolute()}")
    print(f"{'='*60}\n")

    if fail_count > 0:
        print("⚠️  Some textures failed to generate.")
        print("    You can re-run this script to retry failed textures.")
        print("    Or manually create them using the prompts in MASTER_BATCH_PROMPTS.md\n")

    print("Next steps:")
    print("1. Review generated textures in ResourcePack_ElementalDimensions folder")
    print("2. Edit any textures that need adjustment (use GIMP, Aseprite, or Paint.NET)")
    print("3. Load the resource pack in Minecraft and test")
    print("\n")

if __name__ == "__main__":
    main()
