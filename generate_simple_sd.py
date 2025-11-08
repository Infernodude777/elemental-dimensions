"""
Elemental Dimensions - Simple Local Stable Diffusion Generator
Uses Hugging Face Diffusers (works with Python 3.13)
UNLIMITED, FREE, LOCAL GENERATION!
"""

from diffusers import StableDiffusionPipeline
from PIL import Image
from pathlib import Path
import torch
import time

print("="*70)
print(" ELEMENTAL DIMENSIONS - LOCAL AI TEXTURE GENERATOR")
print(" Using Stable Diffusion via Hugging Face (Unlimited & Free!)")
print("="*70)

BASE_DIR = Path("C:/Users/Nikhil/Desktop/elemental_dimensions/ResourcePack_ElementalDimensions/assets/elementaldimensions/textures")

# ALL 115 TEXTURES - Complete list (51 blocks + 53 items + 11 mobs)
TEXTURES = [
    # DIMENSION STONES (11)
    ("block", "inferno_stone", "minecraft pixel art, dark charcoal stone block, glowing orange-red lava cracks, seamless tileable, 16x16"),
    ("block", "aqua_stone", "minecraft pixel art, deep ocean blue stone block, cyan coral patterns, seamless tileable, 16x16"),
    ("block", "terran_stone", "minecraft pixel art, brown stone block, emerald green crystal flecks, seamless tileable, 16x16"),
    ("block", "skybound_stone", "minecraft pixel art, light sky blue stone block, white cloud streaks, seamless tileable, 16x16"),
    ("block", "celestial_stone", "minecraft pixel art, deep purple stone block, white star dots, seamless tileable, 16x16"),
    ("block", "void_stone", "minecraft pixel art, black stone block, dark purple veins, seamless tileable, 16x16"),
    ("block", "dream_stone", "minecraft pixel art, pastel purple stone block, pink lavender swirls, seamless tileable, 16x16"),
    ("block", "fungal_stone", "minecraft pixel art, yellow-green stone block, dark moss patches, seamless tileable, 16x16"),
    ("block", "archive_stone", "minecraft pixel art, tan stone block, blue runic symbols, seamless tileable, 16x16"),
    ("block", "astral_stone", "minecraft pixel art, dark purple stone block, pink-blue nebula wisps, seamless tileable, 16x16"),
    ("block", "gloom_stone", "minecraft pixel art, very dark gray stone block, black shadow veins, seamless tileable, 16x16"),

    # DIMENSION ORES (11)
    ("block", "inferno_ore", "minecraft pixel art ore, dark stone, bright orange flame crystals, seamless tileable, 16x16"),
    ("block", "aqua_ore", "minecraft pixel art ore, blue stone, cyan water droplet crystals, seamless tileable, 16x16"),
    ("block", "terran_ore", "minecraft pixel art ore, brown stone, emerald green crystal clusters, seamless tileable, 16x16"),
    ("block", "skybound_ore", "minecraft pixel art ore, light blue stone, white-yellow wind spiral crystals, seamless tileable, 16x16"),
    ("block", "celestial_ore", "minecraft pixel art ore, purple stone, pink cosmic crystal shards, seamless tileable, 16x16"),
    ("block", "void_ore", "minecraft pixel art ore, black stone, dark purple shadow crystals, seamless tileable, 16x16"),
    ("block", "dream_ore", "minecraft pixel art ore, pastel stone, rainbow prismatic crystals, seamless tileable, 16x16"),
    ("block", "fungal_ore", "minecraft pixel art ore, green stone, lime glowing mushroom clusters, seamless tileable, 16x16"),
    ("block", "archive_ore", "minecraft pixel art ore, tan stone, blue glowing runic glyphs, seamless tileable, 16x16"),
    ("block", "astral_ore", "minecraft pixel art ore, dark stone, white-blue star crystals, seamless tileable, 16x16"),
    ("block", "gloom_ore", "minecraft pixel art ore, gray stone, translucent blue echo crystals, seamless tileable, 16x16"),

    # SPECIAL BLOCKS (29)
    ("block", "atlantis_pillar", "minecraft pixel art, weathered gray stone pillar, coral growth, seamless tileable, 16x16"),
    ("block", "atlantis_brick", "minecraft pixel art, cracked underwater brick, barnacles, seamless tileable, 16x16"),
    ("block", "red_coral", "minecraft pixel art, vibrant red coral cluster, branching organic, seamless tileable, 16x16"),
    ("block", "blue_coral", "minecraft pixel art, electric blue bioluminescent coral, glowing, seamless tileable, 16x16"),
    ("block", "amethyst_cluster", "minecraft pixel art, purple-pink translucent amethyst crystals, seamless tileable, 16x16"),
    ("block", "cloud_block", "minecraft pixel art, pure white fluffy cloud texture, soft cottony, seamless tileable, 16x16"),
    ("block", "aether_grass", "minecraft pixel art, cyan-turquoise magical grass, ethereal shimmer, seamless tileable, 16x16"),
    ("block", "enchanted_oak_log", "minecraft pixel art, golden oak log, magical yellow energy patterns, seamless tileable, 16x16"),
    ("block", "radiant_star", "minecraft pixel art, bright yellow-white glowing star block, light rays, seamless tileable, 16x16"),
    ("block", "nebula_gas", "minecraft pixel art, purple-magenta-blue cosmic nebula clouds, seamless tileable, 16x16"),
    ("block", "void_crystal", "minecraft pixel art, dark purple-black void crystal cluster, seamless tileable, 16x16"),
    ("block", "emerald_cluster", "minecraft pixel art, bright emerald green crystal formation, seamless tileable, 16x16"),
    ("block", "bioluminescent_moss", "minecraft pixel art, vibrant lime-green glowing cave moss, seamless tileable, 16x16"),
    ("block", "red_mushroom_block", "minecraft pixel art, giant red mushroom cap, white circular spots, seamless tileable, 16x16"),
    ("block", "toxic_mushroom", "minecraft pixel art, neon green glowing toxic mushroom cap, seamless tileable, 16x16"),
    ("block", "mycelium_network", "minecraft pixel art, white-gray mycelium root web pattern, seamless tileable, 16x16"),
    ("block", "spore_cluster", "minecraft pixel art, yellow-green glowing spore pods, seamless tileable, 16x16"),
    ("block", "ancient_bookshelf", "minecraft pixel art, dark oak bookshelf, colorful book spines, seamless tileable, 16x16"),
    ("block", "runic_stone", "minecraft pixel art, dark gray stone, glowing blue runic carvings, seamless tileable, 16x16"),
    ("block", "compressed_pages", "minecraft pixel art, yellowed paper and parchment layers, seamless tileable, 16x16"),
    ("block", "sapphire_cluster", "minecraft pixel art, brilliant blue sapphire crystal formation, seamless tileable, 16x16"),
    ("block", "star_cluster", "minecraft pixel art, bright white glowing stars constellation, seamless tileable, 16x16"),
    ("block", "cosmic_nebula", "minecraft pixel art, purple-pink-blue swirling cosmic gas, seamless tileable, 16x16"),
    ("block", "prismatic_crystal", "minecraft pixel art, rainbow color-shifting crystal, seamless tileable, 16x16"),
    ("block", "shadow_stone", "minecraft pixel art, nearly black shadow stone block, seamless tileable, 16x16"),
    ("block", "echo_crystal", "minecraft pixel art, dark blue crystal, concentric ripple waves, seamless tileable, 16x16"),
    ("block", "withered_stone", "minecraft pixel art, decayed gray stone, brown-black decay spots, seamless tileable, 16x16"),
    ("block", "dream_cloud", "minecraft pixel art, soft pink-purple ethereal cloud, seamless tileable, 16x16"),
    ("block", "reality_crystal", "minecraft pixel art, brilliant rainbow prismatic faceted crystal, seamless tileable, 16x16"),

    # ESSENCES (4)
    ("item", "flame_essence", "minecraft pixel art item icon, glowing flame droplet, orange-red gradient, yellow core, 16x16"),
    ("item", "water_essence", "minecraft pixel art item icon, cyan-blue water droplet, air bubble inside, 16x16"),
    ("item", "earth_essence", "minecraft pixel art item icon, emerald green crystal shard, faceted, 16x16"),
    ("item", "wind_essence", "minecraft pixel art item icon, white-blue tornado vortex spiral, 16x16"),

    # HEARTS (4)
    ("item", "inferno_heart", "minecraft pixel art item icon, red glowing heart, orange flame patterns, 16x16"),
    ("item", "aquatic_heart", "minecraft pixel art item icon, cyan-blue glowing heart, water wave patterns, 16x16"),
    ("item", "terran_heart", "minecraft pixel art item icon, green glowing heart, rocky earthen texture, 16x16"),
    ("item", "skybound_heart", "minecraft pixel art item icon, white glowing heart, ethereal wispy edges, 16x16"),

    # DREAM EQUIPMENT (9)
    ("item", "dream_sword", "minecraft pixel art item, ethereal purple sword, glowing lavender blade, transparent edges, 16x16"),
    ("item", "dream_pickaxe", "minecraft pixel art item, purple pickaxe, semi-transparent, ethereal glow, 16x16"),
    ("item", "dream_axe", "minecraft pixel art item, purple axe, glowing blade, transparent handle, 16x16"),
    ("item", "dream_shovel", "minecraft pixel art item, purple shovel, ethereal semi-transparent, 16x16"),
    ("item", "dream_hoe", "minecraft pixel art item, purple hoe, translucent, magical shimmer, 16x16"),
    ("item", "dream_helmet", "minecraft pixel art armor, purple helmet, ethereal translucent, glowing edges, 16x16"),
    ("item", "dream_chestplate", "minecraft pixel art armor, purple chestplate, semi-transparent, glowing patterns, 16x16"),
    ("item", "dream_leggings", "minecraft pixel art armor, purple leggings, translucent, magical patterns, 16x16"),
    ("item", "dream_boots", "minecraft pixel art armor, purple boots, ethereal, glowing soles, 16x16"),

    # SKY EQUIPMENT (9)
    ("item", "sky_sword", "minecraft pixel art item, radiant golden sword, bright white glowing edge, 16x16"),
    ("item", "sky_pickaxe", "minecraft pixel art item, brilliant gold pickaxe, radiant glow, 16x16"),
    ("item", "sky_axe", "minecraft pixel art item, shining golden axe, bright yellow blade, 16x16"),
    ("item", "sky_shovel", "minecraft pixel art item, radiant golden shovel, glowing yellow, 16x16"),
    ("item", "sky_hoe", "minecraft pixel art item, golden-yellow hoe, bright radiant glow, 16x16"),
    ("item", "sky_helmet", "minecraft pixel art armor, radiant golden helmet, divine light, 16x16"),
    ("item", "sky_chestplate", "minecraft pixel art armor, brilliant gold chestplate, holy light, 16x16"),
    ("item", "sky_leggings", "minecraft pixel art armor, shining golden leggings, celestial glow, 16x16"),
    ("item", "sky_boots", "minecraft pixel art armor, radiant golden boots, divine shimmer, 16x16"),

    # FUNGAL EQUIPMENT (9)
    ("item", "fungal_sword", "minecraft pixel art item, toxic green glowing sword, mushroom growth, 16x16"),
    ("item", "fungal_pickaxe", "minecraft pixel art item, lime-green glowing pickaxe, mushroom cap, 16x16"),
    ("item", "fungal_axe", "minecraft pixel art item, bright green glowing axe, fungal blade, 16x16"),
    ("item", "fungal_shovel", "minecraft pixel art item, neon green shovel, spore-covered, 16x16"),
    ("item", "fungal_hoe", "minecraft pixel art item, toxic green hoe, glowing mushroom, 16x16"),
    ("item", "fungal_helmet", "minecraft pixel art armor, green fungal helmet, mushroom cap design, 16x16"),
    ("item", "fungal_chestplate", "minecraft pixel art armor, toxic green chestplate, mushroom growths, 16x16"),
    ("item", "fungal_leggings", "minecraft pixel art armor, neon green leggings, glowing spores, 16x16"),
    ("item", "fungal_boots", "minecraft pixel art armor, bright green boots, glowing mushroom, 16x16"),

    # ARCHIVE EQUIPMENT (9)
    ("item", "archive_sword", "minecraft pixel art item, brilliant blue sword, glowing cyan runic glyphs, 16x16"),
    ("item", "archive_pickaxe", "minecraft pixel art item, bright blue pickaxe, glowing runic inscriptions, 16x16"),
    ("item", "archive_axe", "minecraft pixel art item, blue axe, glowing magical glyphs, 16x16"),
    ("item", "archive_shovel", "minecraft pixel art item, blue shovel, bright cyan glowing symbols, 16x16"),
    ("item", "archive_hoe", "minecraft pixel art item, blue hoe, glowing runic patterns, 16x16"),
    ("item", "archive_helmet", "minecraft pixel art armor, brilliant blue helmet, cyan magical glyphs, 16x16"),
    ("item", "archive_chestplate", "minecraft pixel art armor, bright blue chestplate, glowing symbols, 16x16"),
    ("item", "archive_leggings", "minecraft pixel art armor, blue leggings, cyan runic inscriptions, 16x16"),
    ("item", "archive_boots", "minecraft pixel art armor, blue boots, bright cyan glowing glyphs, 16x16"),

    # ASTRAL TOOLS (5)
    ("item", "astral_sword", "minecraft pixel art item, cosmic purple-white sword, swirling starlight, 16x16"),
    ("item", "astral_pickaxe", "minecraft pixel art item, purple cosmic pickaxe, white star sparkles, 16x16"),
    ("item", "astral_axe", "minecraft pixel art item, cosmic purple axe, bright starlight shimmer, 16x16"),
    ("item", "astral_shovel", "minecraft pixel art item, purple cosmic shovel, white star particles, 16x16"),
    ("item", "astral_hoe", "minecraft pixel art item, cosmic purple hoe, starlight energy, 16x16"),

    # GLOOM TOOLS (4)
    ("item", "gloom_sword", "minecraft pixel art item, dark gray shadowy sword, black smoky wisps, 16x16"),
    ("item", "gloom_pickaxe", "minecraft pixel art item, dark gray pickaxe, black mist trailing, 16x16"),
    ("item", "gloom_axe", "minecraft pixel art item, shadowy gray axe, dark smoke effects, 16x16"),
    ("item", "gloom_shovel", "minecraft pixel art item, dark gray shovel, black wispy trails, 16x16"),
]

# Mob sprites (32x32) - will be generated after blocks/items
MOBS = [
    ("entity", "pyros", "minecraft pixel art mob sprite, massive red-orange lava demon, curved horns, glowing yellow eyes, front view, 32x32"),
    ("entity", "leviathan", "minecraft pixel art mob sprite, gigantic blue sea dragon, cyan scales, fierce eyes, front view, 32x32"),
    ("entity", "goliath", "minecraft pixel art mob sprite, colossal brown stone golem, rocky body, glowing orange eyes, front view, 32x32"),
    ("entity", "zephyros", "minecraft pixel art mob sprite, majestic white-blue cloud humanoid, glowing cyan eyes, front view, 32x32"),
    ("entity", "the_primarch", "minecraft pixel art mob sprite, divine purple cosmic entity, glowing starlight, constellation patterns, front view, 32x32"),
    ("entity", "the_dreamer", "minecraft pixel art mob sprite, ethereal purple floating entity, translucent wispy, pink aura, front view, 32x32"),
    ("entity", "the_overmind", "minecraft pixel art mob sprite, colossal green mushroom, massive cap, glowing eyes, front view, 32x32"),
    ("entity", "the_librarian", "minecraft pixel art mob sprite, tan-brown robed keeper, flowing robes, blue eyes, ancient tome, front view, 32x32"),
    ("entity", "the_void_archon", "minecraft pixel art mob sprite, terrifying black-purple cosmic entity, reality-warping, front view, 32x32"),
    ("entity", "the_echo_king", "minecraft pixel art mob sprite, ominous dark gray shadowy ruler, black cloak, shadow crown, front view, 32x32"),
    ("entity", "the_radiant_monarch", "minecraft pixel art mob sprite, magnificent golden-orange radiant ruler, divine armor, bright crown, front view, 32x32"),
]

def check_if_generated(category, name):
    """Check if texture already exists"""
    output_path = BASE_DIR / category / f"{name}.png"
    return output_path.exists()

def generate_texture(pipe, category, name, prompt, size=16):
    """Generate texture using Stable Diffusion"""

    # Skip if already exists
    if check_if_generated(category, name):
        print(f"⏭️  {name} already exists, skipping...")
        return True

    print(f"\n{'='*70}")
    print(f"Generating: {name}")
    print(f"Category: {category} | Size: {size}x{size}")
    print(f"Prompt: {prompt[:60]}...")
    print(f"{'='*70}")

    try:
        print("🎨 Generating with Stable Diffusion...")

        # Generate image
        image = pipe(
            prompt=prompt,
            negative_prompt="blurry, low quality, realistic, photograph, 3d render, complex details, photorealistic, text, watermark",
            num_inference_steps=20,
            guidance_scale=7.5,
            width=512,
            height=512
        ).images[0]

        print("✅ Image generated! Resizing...")

        # Resize to target size with NEAREST for pixel art
        image = image.resize((size, size), Image.Resampling.NEAREST)

        # Convert to RGBA
        if image.mode != 'RGBA':
            image = image.convert('RGBA')

        # Save
        output_path = BASE_DIR / category / f"{name}.png"
        output_path.parent.mkdir(parents=True, exist_ok=True)
        image.save(output_path, 'PNG', optimize=True)

        print(f"✅ SUCCESS! Saved {name}.png")
        return True

    except Exception as e:
        print(f"❌ ERROR: {e}")
        return False

def main():
    print("\n📦 Loading Stable Diffusion model...")
    print("(This will download ~4GB on first run - be patient!)\n")

    try:
        # Load the model
        model_id = "runwayml/stable-diffusion-v1-5"

        # Check if CUDA is available
        if torch.cuda.is_available():
            print("✅ GPU detected! Using CUDA for fast generation")
            pipe = StableDiffusionPipeline.from_pretrained(model_id, torch_dtype=torch.float16)
            pipe = pipe.to("cuda")
        else:
            print("⚠️  No GPU detected. Using CPU (slower but works!)")
            pipe = StableDiffusionPipeline.from_pretrained(model_id)
            pipe = pipe.to("cpu")

        print("✅ Model loaded successfully!\n")

    except Exception as e:
        print(f"\n❌ ERROR loading model: {e}")
        print("\nThis might be due to:")
        print("1. Network connection issue")
        print("2. Insufficient disk space (~4GB needed)")
        print("3. Memory issue")
        return

    print(f"📊 Total textures: {len(TEXTURES)} blocks/items + {len(MOBS)} mobs = {len(TEXTURES) + len(MOBS)} total")
    print(f"⏱️  Estimated time on CPU: ~{(len(TEXTURES) + len(MOBS)) * 2} minutes")
    print(f"⏱️  Estimated time on GPU: ~{(len(TEXTURES) + len(MOBS)) * 0.5} minutes")
    print(f"📁 Output: {BASE_DIR}\n")

    input("Press ENTER to start generation...")

    start_time = time.time()
    success_count = 0
    failed = []

    # Phase 1: Generate blocks and items (16x16)
    print(f"\n{'#'*70}")
    print(f"# PHASE 1: BLOCKS & ITEMS ({len(TEXTURES)} textures @ 16x16)")
    print(f"{'#'*70}\n")

    for i, (category, name, prompt) in enumerate(TEXTURES, 1):
        print(f"\n{'*'*70}")
        print(f"[{i}/{len(TEXTURES)}] Processing {name}...")
        print(f"{'*'*70}")

        if generate_texture(pipe, category, name, prompt, 16):
            success_count += 1
        else:
            failed.append(name)

        print(f"\n✅ Progress: {success_count}/{len(TEXTURES) + len(MOBS)} complete")

    # Phase 2: Generate mob sprites (32x32)
    print(f"\n\n{'#'*70}")
    print(f"# PHASE 2: MOB SPRITES ({len(MOBS)} mobs @ 32x32)")
    print(f"{'#'*70}\n")

    for i, (category, name, prompt) in enumerate(MOBS, 1):
        print(f"\n{'*'*70}")
        print(f"[{i}/{len(MOBS)}] Processing {name}...")
        print(f"{'*'*70}")

        if generate_texture(pipe, category, name, prompt, 32):
            success_count += 1
        else:
            failed.append(name)

        print(f"\n✅ Progress: {success_count}/{len(TEXTURES) + len(MOBS)} complete")

    # Final report
    elapsed_time = time.time() - start_time
    minutes = int(elapsed_time // 60)
    seconds = int(elapsed_time % 60)

    print(f"\n\n{'='*70}")
    print(" 🎉 GENERATION COMPLETE! 🎉")
    print(f"{'='*70}")
    print(f"✅ Successful: {success_count}/{len(TEXTURES) + len(MOBS)}")
    if failed:
        print(f"❌ Failed: {len(failed)}")
        print(f"   {', '.join(failed)}")
    print(f"⏱️  Total time: {minutes}m {seconds}s")
    print(f"📁 Output: {BASE_DIR}")
    print(f"{'='*70}\n")

    print("\n✨ Next steps:")
    print("1. Review textures in ResourcePack folder")
    print("2. Copy to %appdata%\\.minecraft\\resourcepacks\\")
    print("3. Test in Minecraft!")
    print("\n🎨 Enjoy your AI-generated textures!\n")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\n⚠️  Stopped by user. Progress saved.\n")
    except Exception as e:
        print(f"\n\n❌ CRITICAL ERROR: {e}")
        print("Please report this error if it persists.\n")
