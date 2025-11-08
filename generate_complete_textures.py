"""
Elemental Dimensions - Complete Texture Generation
Generates all 146 textures using Craiyon API
This will run for approximately 1-2 hours
"""

from craiyon import Craiyon
from PIL import Image
import base64
from pathlib import Path
from io import BytesIO
import time

print("="*70)
print(" ELEMENTAL DIMENSIONS - COMPLETE TEXTURE GENERATION")
print(" Powered by Craiyon AI")
print("="*70)
print("\nInitializing...")

gen = Craiyon()
BASE_DIR = Path("C:/Users/Nikhil/Desktop/elemental_dimensions/ResourcePack_ElementalDimensions/assets/elementaldimensions/textures")

# Complete texture list with optimized prompts for Craiyon
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

    # SPECIAL BLOCKS (29)
    ("block", "atlantis_pillar", "weathered gray stone pillar, coral growth, Minecraft block texture, pixel art"),
    ("block", "atlantis_brick", "cracked underwater brick, barnacles, Minecraft block texture, pixel art"),
    ("block", "red_coral", "vibrant red coral cluster, branching organic, Minecraft block texture, pixel art"),
    ("block", "blue_coral", "electric blue bioluminescent coral, glowing, Minecraft block texture, pixel art"),
    ("block", "amethyst_cluster", "purple-pink translucent amethyst crystals, Minecraft block texture, pixel art"),
    ("block", "cloud_block", "pure white fluffy cloud texture, soft cottony, Minecraft block texture, pixel art"),
    ("block", "aether_grass", "cyan-turquoise magical grass, ethereal shimmer, Minecraft block texture, pixel art"),
    ("block", "enchanted_oak_log", "golden oak log, magical yellow energy patterns, Minecraft block texture, pixel art"),
    ("block", "radiant_star", "bright yellow-white glowing star block, light rays, Minecraft block texture, pixel art"),
    ("block", "nebula_gas", "purple-magenta-blue cosmic nebula clouds, Minecraft block texture, pixel art"),
    ("block", "void_crystal", "dark purple-black void crystal cluster, Minecraft block texture, pixel art"),
    ("block", "emerald_cluster", "bright emerald green crystal formation, Minecraft block texture, pixel art"),
    ("block", "bioluminescent_moss", "vibrant lime-green glowing cave moss, Minecraft block texture, pixel art"),
    ("block", "red_mushroom_block", "giant red mushroom cap, white circular spots, Minecraft block texture, pixel art"),
    ("block", "toxic_mushroom", "neon green glowing toxic mushroom cap, Minecraft block texture, pixel art"),
    ("block", "mycelium_network", "white-gray mycelium root web pattern, Minecraft block texture, pixel art"),
    ("block", "spore_cluster", "yellow-green glowing spore pods, Minecraft block texture, pixel art"),
    ("block", "ancient_bookshelf", "dark oak bookshelf, colorful book spines, Minecraft block texture, pixel art"),
    ("block", "runic_stone", "dark gray stone, glowing blue runic carvings, Minecraft block texture, pixel art"),
    ("block", "compressed_pages", "yellowed paper and parchment layers, Minecraft block texture, pixel art"),
    ("block", "sapphire_cluster", "brilliant blue sapphire crystal formation, Minecraft block texture, pixel art"),
    ("block", "star_cluster", "bright white glowing stars constellation, Minecraft block texture, pixel art"),
    ("block", "cosmic_nebula", "purple-pink-blue swirling cosmic gas, Minecraft block texture, pixel art"),
    ("block", "prismatic_crystal", "rainbow color-shifting crystal, Minecraft block texture, pixel art"),
    ("block", "shadow_stone", "nearly black shadow stone block, Minecraft block texture, pixel art"),
    ("block", "echo_crystal", "dark blue crystal, concentric ripple waves, Minecraft block texture, pixel art"),
    ("block", "withered_stone", "decayed gray stone, brown-black decay spots, Minecraft block texture, pixel art"),
    ("block", "dream_cloud", "soft pink-purple ethereal cloud, Minecraft block texture, pixel art"),
    ("block", "reality_crystal", "brilliant rainbow prismatic faceted crystal, Minecraft block texture, pixel art"),

    # ESSENCES (4)
    ("item", "flame_essence", "glowing flame droplet, orange-red gradient, yellow core, Minecraft item icon, pixel art"),
    ("item", "water_essence", "cyan-blue water droplet, air bubble inside, Minecraft item icon, pixel art"),
    ("item", "earth_essence", "emerald green crystal shard, faceted, Minecraft item icon, pixel art"),
    ("item", "wind_essence", "white-blue tornado vortex spiral, Minecraft item icon, pixel art"),

    # HEARTS (4)
    ("item", "inferno_heart", "red glowing heart, orange flame patterns, Minecraft item icon, pixel art"),
    ("item", "aquatic_heart", "cyan-blue glowing heart, water wave patterns, Minecraft item icon, pixel art"),
    ("item", "terran_heart", "green glowing heart, rocky earthen texture, Minecraft item icon, pixel art"),
    ("item", "skybound_heart", "white glowing heart, ethereal wispy edges, Minecraft item icon, pixel art"),

    # DREAM EQUIPMENT SET (9)
    ("item", "dream_sword", "ethereal purple sword, glowing lavender blade, transparent edges, Minecraft item, pixel art"),
    ("item", "dream_pickaxe", "purple pickaxe, semi-transparent, ethereal glow, Minecraft item, pixel art"),
    ("item", "dream_axe", "purple axe, glowing blade, transparent handle, Minecraft item, pixel art"),
    ("item", "dream_shovel", "purple shovel, ethereal semi-transparent, Minecraft item, pixel art"),
    ("item", "dream_hoe", "purple hoe, translucent, magical shimmer, Minecraft item, pixel art"),
    ("item", "dream_helmet", "purple helmet, ethereal translucent, glowing edges, Minecraft armor, pixel art"),
    ("item", "dream_chestplate", "purple chestplate, semi-transparent, glowing patterns, Minecraft armor, pixel art"),
    ("item", "dream_leggings", "purple leggings, translucent, magical patterns, Minecraft armor, pixel art"),
    ("item", "dream_boots", "purple boots, ethereal, glowing soles, Minecraft armor, pixel art"),

    # SKY EQUIPMENT SET (9)
    ("item", "sky_sword", "radiant golden sword, bright white glowing edge, Minecraft item, pixel art"),
    ("item", "sky_pickaxe", "brilliant gold pickaxe, radiant glow, Minecraft item, pixel art"),
    ("item", "sky_axe", "shining golden axe, bright yellow blade, Minecraft item, pixel art"),
    ("item", "sky_shovel", "radiant golden shovel, glowing yellow, Minecraft item, pixel art"),
    ("item", "sky_hoe", "golden-yellow hoe, bright radiant glow, Minecraft item, pixel art"),
    ("item", "sky_helmet", "radiant golden helmet, divine light, Minecraft armor, pixel art"),
    ("item", "sky_chestplate", "brilliant gold chestplate, holy light, Minecraft armor, pixel art"),
    ("item", "sky_leggings", "shining golden leggings, celestial glow, Minecraft armor, pixel art"),
    ("item", "sky_boots", "radiant golden boots, divine shimmer, Minecraft armor, pixel art"),

    # FUNGAL EQUIPMENT SET (9)
    ("item", "fungal_sword", "toxic green glowing sword, mushroom growth, Minecraft item, pixel art"),
    ("item", "fungal_pickaxe", "lime-green glowing pickaxe, mushroom cap, Minecraft item, pixel art"),
    ("item", "fungal_axe", "bright green glowing axe, fungal blade, Minecraft item, pixel art"),
    ("item", "fungal_shovel", "neon green shovel, spore-covered, Minecraft item, pixel art"),
    ("item", "fungal_hoe", "toxic green hoe, glowing mushroom, Minecraft item, pixel art"),
    ("item", "fungal_helmet", "green fungal helmet, mushroom cap design, Minecraft armor, pixel art"),
    ("item", "fungal_chestplate", "toxic green chestplate, mushroom growths, Minecraft armor, pixel art"),
    ("item", "fungal_leggings", "neon green leggings, glowing spores, Minecraft armor, pixel art"),
    ("item", "fungal_boots", "bright green boots, glowing mushroom, Minecraft armor, pixel art"),

    # ARCHIVE EQUIPMENT SET (9)
    ("item", "archive_sword", "brilliant blue sword, glowing cyan runic glyphs, Minecraft item, pixel art"),
    ("item", "archive_pickaxe", "bright blue pickaxe, glowing runic inscriptions, Minecraft item, pixel art"),
    ("item", "archive_axe", "blue axe, glowing magical glyphs, Minecraft item, pixel art"),
    ("item", "archive_shovel", "blue shovel, bright cyan glowing symbols, Minecraft item, pixel art"),
    ("item", "archive_hoe", "blue hoe, glowing runic patterns, Minecraft item, pixel art"),
    ("item", "archive_helmet", "brilliant blue helmet, cyan magical glyphs, Minecraft armor, pixel art"),
    ("item", "archive_chestplate", "bright blue chestplate, glowing symbols, Minecraft armor, pixel art"),
    ("item", "archive_leggings", "blue leggings, cyan runic inscriptions, Minecraft armor, pixel art"),
    ("item", "archive_boots", "blue boots, bright cyan glowing glyphs, Minecraft armor, pixel art"),

    # ASTRAL TOOLS (5)
    ("item", "astral_sword", "cosmic purple-white sword, swirling starlight, Minecraft item, pixel art"),
    ("item", "astral_pickaxe", "purple cosmic pickaxe, white star sparkles, Minecraft item, pixel art"),
    ("item", "astral_axe", "cosmic purple axe, bright starlight shimmer, Minecraft item, pixel art"),
    ("item", "astral_shovel", "purple cosmic shovel, white star particles, Minecraft item, pixel art"),
    ("item", "astral_hoe", "cosmic purple hoe, starlight energy, Minecraft item, pixel art"),

    # GLOOM TOOLS (4)
    ("item", "gloom_sword", "dark gray shadowy sword, black smoky wisps, Minecraft item, pixel art"),
    ("item", "gloom_pickaxe", "dark gray pickaxe, black mist trailing, Minecraft item, pixel art"),
    ("item", "gloom_axe", "shadowy gray axe, dark smoke effects, Minecraft item, pixel art"),
    ("item", "gloom_shovel", "dark gray shovel, black wispy trails, Minecraft item, pixel art"),
]

# Mob sprites will be generated after items since they take longer
MOBS = [
    # BOSSES (11 @ 32x32)
    ("entity", "pyros", "massive red-orange lava demon, curved black horns, glowing yellow eyes, Minecraft mob sprite, pixel art, front view"),
    ("entity", "leviathan", "gigantic blue sea dragon, cyan scales, fierce eyes, Minecraft mob sprite, pixel art, front view"),
    ("entity", "goliath", "colossal brown stone golem, rocky body, glowing orange eyes, Minecraft mob sprite, pixel art, front view"),
    ("entity", "zephyros", "majestic white-blue cloud humanoid, glowing cyan eyes, Minecraft mob sprite, pixel art, front view"),
    ("entity", "the_primarch", "divine purple cosmic entity, glowing starlight, constellation patterns, Minecraft mob sprite, pixel art, front view"),
    ("entity", "the_dreamer", "ethereal purple floating entity, translucent wispy, pink aura, Minecraft mob sprite, pixel art, front view"),
    ("entity", "the_overmind", "colossal green mushroom, massive cap, glowing eyes, Minecraft mob sprite, pixel art, front view"),
    ("entity", "the_librarian", "tan-brown robed keeper, flowing robes, blue eyes, ancient tome, Minecraft mob sprite, pixel art, front view"),
    ("entity", "the_void_archon", "terrifying black-purple cosmic entity, reality-warping, Minecraft mob sprite, pixel art, front view"),
    ("entity", "the_echo_king", "ominous dark gray shadowy ruler, black cloak, shadow crown, Minecraft mob sprite, pixel art, front view"),
    ("entity", "the_radiant_monarch", "magnificent golden-orange radiant ruler, divine armor, bright crown, Minecraft mob sprite, pixel art, front view"),
]

def generate_texture(category, name, prompt, size=16, retries=3):
    """Generate a single texture with retry logic"""
    print(f"\n{'='*70}")
    print(f"Generating: {name}")
    print(f"Category: {category} | Size: {size}x{size}")
    print(f"Prompt: {prompt[:60]}...")
    print(f"{'='*70}")

    for attempt in range(retries):
        try:
            if attempt > 0:
                print(f"⚠️  Retry attempt {attempt + 1}/{retries}...")
                time.sleep(10)  # Wait before retry

            print("⏳ Requesting from Craiyon API...")
            result = gen.generate(prompt)

            if not result or not hasattr(result, 'images') or not result.images:
                print(f"❌ No images generated for {name}")
                continue

            print(f"✓ Image generated successfully! Processing...")

            # Get best image (first one is usually highest quality)
            img_data = base64.b64decode(result.images[0])
            img = Image.open(BytesIO(img_data))

            # Resize with NEAREST (perfect for pixel art)
            print(f"✓ Resizing to {size}x{size} with pixel-perfect scaling...")
            img = img.resize((size, size), Image.Resampling.NEAREST)

            # Ensure RGBA for transparency
            if img.mode != 'RGBA':
                img = img.convert('RGBA')

            # Save
            output_path = BASE_DIR / category / f"{name}.png"
            output_path.parent.mkdir(parents=True, exist_ok=True)
            img.save(output_path, 'PNG', optimize=True)

            print(f"✅ SUCCESS: Saved to {output_path.name}")
            return True

        except KeyboardInterrupt:
            print("\n\n⚠️  Generation interrupted by user!")
            raise
        except Exception as e:
            print(f"❌ ERROR (attempt {attempt + 1}/{retries}): {str(e)}")
            if attempt == retries - 1:
                print(f"⛔ Failed after {retries} attempts. Skipping {name}...")
                return False

    return False

def main():
    print(f"\n📊 Total textures to generate: {len(TEXTURES) + len(MOBS)}")
    print(f"   - Blocks: {sum(1 for t in TEXTURES if t[0] == 'block')}")
    print(f"   - Items: {sum(1 for t in TEXTURES if t[0] == 'item')}")
    print(f"   - Mobs: {len(MOBS)}")
    print(f"\n⏱️  Estimated time: 1-2 hours")
    print(f"💡  Tip: You can minimize this window and do other things!\n")

    input("Press ENTER to start generation...")

    start_time = time.time()
    success_count = 0
    fail_count = 0
    failed_textures = []

    # Generate blocks and items first
    print(f"\n\n{'#'*70}")
    print("# PHASE 1: BLOCKS AND ITEMS")
    print(f"{'#'*70}\n")

    for i, (category, name, prompt) in enumerate(TEXTURES, 1):
        print(f"\n[{i}/{len(TEXTURES)}] ", end="")
        if generate_texture(category, name, prompt, 16):
            success_count += 1
        else:
            fail_count += 1
            failed_textures.append((category, name, prompt, 16))

        # Rate limiting - wait between requests
        if i < len(TEXTURES):
            wait_time = 25  # 25 seconds between requests to avoid rate limits
            print(f"⏸️  Waiting {wait_time} seconds before next texture...")
            time.sleep(wait_time)

    # Generate mob sprites (32x32)
    print(f"\n\n{'#'*70}")
    print("# PHASE 2: MOB SPRITES (32x32)")
    print(f"{'#'*70}\n")

    for i, (category, name, prompt) in enumerate(MOBS, 1):
        print(f"\n[{i}/{len(MOBS)}] ", end="")
        if generate_texture(category, name, prompt, 32):
            success_count += 1
        else:
            fail_count += 1
            failed_textures.append((category, name, prompt, 32))

        # Wait between mob generations
        if i < len(MOBS):
            wait_time = 30  # 30 seconds for mobs (larger images)
            print(f"⏸️  Waiting {wait_time} seconds before next mob...")
            time.sleep(wait_time)

    # Retry failed textures
    if failed_textures:
        print(f"\n\n{'#'*70}")
        print(f"# RETRYING {len(failed_textures)} FAILED TEXTURES")
        print(f"{'#'*70}\n")

        retry_success = 0
        for i, (category, name, prompt, size) in enumerate(failed_textures, 1):
            print(f"\n[Retry {i}/{len(failed_textures)}] ", end="")
            if generate_texture(category, name, prompt, size):
                retry_success += 1
                success_count += 1
                fail_count -= 1
            time.sleep(30)  # Longer wait for retries

        print(f"\n✓ Successfully recovered {retry_success}/{len(failed_textures)} textures on retry")

    # Final report
    elapsed_time = time.time() - start_time
    hours = int(elapsed_time // 3600)
    minutes = int((elapsed_time % 3600) // 60)
    seconds = int(elapsed_time % 60)

    print(f"\n\n{'='*70}")
    print(" GENERATION COMPLETE!")
    print(f"{'='*70}")
    print(f"✅ Successful: {success_count}/{len(TEXTURES) + len(MOBS)}")
    print(f"❌ Failed: {fail_count}")
    print(f"⏱️  Total time: {hours}h {minutes}m {seconds}s")
    print(f"📁 Output: {BASE_DIR}")
    print(f"{'='*70}\n")

    if fail_count > 0:
        print(f"⚠️  {fail_count} textures still failed after retries.")
        print("   You can manually generate these using Bing Image Creator")
        print("   or re-run this script later.\n")

    print("\n✨ Next steps:")
    print("1. Review generated textures in ResourcePack folder")
    print("2. Copy ResourcePack_ElementalDimensions to %appdata%\\.minecraft\\resourcepacks\\")
    print("3. Launch Minecraft and enable the resource pack")
    print("4. Test all textures in-game!")
    print("\nEnjoy your AI-generated Elemental Dimensions texture pack! 🎨\n")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\n⚠️  Generation stopped by user. Progress has been saved.")
        print("You can re-run the script to continue from where you left off.\n")
