# Texture Generation Setup Guide - TextureMaker (Stable Diffusion)

## 🎯 Recommended Tool: TextureMaker

**Repository:** https://github.com/LyFl0w/TextureMaker

**Why this one?**
- Uses Stable Diffusion (highest quality AI image generation)
- Best for custom pixel art textures
- Open source and free
- Runs locally (unlimited generations)
- Most control over output quality

---

## 📦 Installation Steps

### Prerequisites

1. **Python 3.10 or 3.11** (required for Stable Diffusion)
   - Download: https://www.python.org/downloads/
   - ⚠️ Check "Add Python to PATH" during installation

2. **Git** (to clone repository)
   - Download: https://git-scm.com/downloads

3. **GPU Recommended** (NVIDIA with CUDA for faster generation)
   - CPU works but is much slower (5-10 min per texture vs 30 seconds)

### Setup Instructions

```powershell
# 1. Clone the TextureMaker repository
cd C:\Users\Nikhil\Desktop\elemental_dimensions
git clone https://github.com/LyFl0w/TextureMaker.git
cd TextureMaker

# 2. Create virtual environment
python -m venv venv

# 3. Activate virtual environment
.\venv\Scripts\Activate.ps1

# 4. Install dependencies
pip install --upgrade pip
pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cu118
pip install diffusers transformers accelerate pillow

# 5. Test installation
python -c "import torch; print('CUDA available:', torch.cuda.is_available())"
```

---

## 🚀 Quick Start Usage

### Basic Generation Command

```powershell
# Activate environment first
cd C:\Users\Nikhil\Desktop\elemental_dimensions\TextureMaker
.\venv\Scripts\Activate.ps1

# Generate a texture
python generate_texture.py --prompt "dark charcoal stone with glowing orange lava cracks, Minecraft block texture, 16x16 pixel art" --output "../ResourcePack_ElementalDimensions/assets/elementaldimensions/textures/block/inferno_stone.png" --size 512 --downscale 16
```

### Important Parameters

- `--prompt` - Your texture description
- `--output` - Where to save the PNG file
- `--size` - Generation resolution (512 or 1024 recommended, then downscale)
- `--downscale` - Final size (16 for blocks/items, 32 for mobs)
- `--negative-prompt` - What to avoid (blur, gradient, realistic, 3D)
- `--steps` - Quality iterations (20-50, higher = better but slower)
- `--seed` - Reproducibility (same seed = same output)

---

## 📝 Optimized Prompts for Stable Diffusion

### Prompt Template Structure

```
[MAIN SUBJECT], Minecraft texture, pixel art, 16x16, [STYLE KEYWORDS]

Negative: photorealistic, blurry, gradient, smooth, 3D render, watermark, text
```

### Style Keywords for Best Results

**For Blocks:**
- `seamless tileable pattern`
- `flat top-down view`
- `retro 16-bit pixel art`
- `game asset`
- `sharp pixels no blur`

**For Items:**
- `item icon`
- `centered composition`
- `transparent background`
- `clear silhouette`
- `game inventory icon`

**For Mobs:**
- `sprite sheet`
- `front facing view`
- `32x32 pixels`
- `character sprite`
- `retro RPG style`

---

## 🎨 Generation Scripts

I'll create automated batch scripts for generating all textures systematically.

### Method 1: Individual Generation (Highest Quality Control)

Create `generate_blocks.py`:

```python
import subprocess
import os

# Activate virtual environment and generate textures
textures = [
    {
        "name": "inferno_stone",
        "prompt": "dark charcoal gray stone block with bright orange-red glowing lava cracks forming web pattern, rough volcanic texture, Minecraft block texture, seamless tileable, 16x16 pixel art, flat colors, retro game asset",
        "negative": "photorealistic, blurry, gradient, smooth, 3D render, depth, shadow, watermark",
        "category": "block"
    },
    {
        "name": "aqua_stone",
        "prompt": "deep ocean blue stone block with cyan coral ridge patterns embedded, smooth underwater rock surface, Minecraft block texture, seamless tileable, 16x16 pixel art, flat colors, retro game asset",
        "negative": "photorealistic, blurry, gradient, smooth, 3D render, depth, shadow, watermark",
        "category": "block"
    },
    # Add all 51 blocks here...
]

for texture in textures:
    output_path = f"../ResourcePack_ElementalDimensions/assets/elementaldimensions/textures/{texture['category']}/{texture['name']}.png"

    cmd = [
        "python", "generate_texture.py",
        "--prompt", texture['prompt'],
        "--negative-prompt", texture['negative'],
        "--output", output_path,
        "--size", "512",
        "--downscale", "16",
        "--steps", "30"
    ]

    print(f"Generating {texture['name']}...")
    subprocess.run(cmd)
    print(f"✓ Saved to {output_path}\n")
```

### Method 2: Use Alternative - AIPackGenerator (Easier Setup)

If TextureMaker is too complex to set up, try AIPackGenerator:

```powershell
# Clone AIPackGenerator
cd C:\Users\Nikhil\Desktop\elemental_dimensions
git clone https://github.com/DWF967/AIPackGenerator.git
cd AIPackGenerator

# Install dependencies
pip install craiyon.py pillow

# Generate textures (uses Craiyon API - no GPU needed)
python generate_pack.py --prompt "Elemental Dimensions custom blocks and items" --output ../ResourcePack_ElementalDimensions
```

**Note:** AIPackGenerator uses Craiyon which is lower quality but much easier to set up.

---

## 🔧 Alternative: Use Online Stable Diffusion (No Installation)

If local setup is too difficult, use these free online tools:

### 1. **Stable Diffusion Online** (Best Quality)
- URL: https://stablediffusionweb.com/
- Free tier: 100 images/day
- Instructions:
  1. Paste optimized prompt
  2. Set "Negative Prompt"
  3. Set Image Size to 512x512
  4. Generate
  5. Download and manually resize to 16x16 in Paint or online tool

### 2. **Hugging Face Spaces**
- URL: https://huggingface.co/spaces/stabilityai/stable-diffusion
- Completely free
- No sign-up required
- Same process as above

### 3. **Bing Image Creator** (Fallback)
- URL: https://www.bing.com/images/create
- 15 free generations per day
- Good quality but not pixel-art optimized

---

## 📋 Complete Texture List (146 Total)

### Blocks (51)
1. Dimension Stones (11): inferno_stone, aqua_stone, terran_stone, skybound_stone, celestial_stone, void_stone, dream_stone, fungal_stone, archive_stone, astral_stone, gloom_stone
2. Dimension Ores (11): inferno_ore, aqua_ore, terran_ore, skybound_ore, celestial_ore, void_ore, dream_ore, fungal_ore, archive_ore, astral_ore, gloom_ore
3. Atlantis Blocks (11): atlantis_pillar, atlantis_brick, red_coral, blue_coral, amethyst_cluster, cloud_block, aether_grass, enchanted_oak_log, radiant_star, nebula_gas, void_crystal
4. Cave & Fungal (10): emerald_cluster, bioluminescent_moss, red_mushroom_block, toxic_mushroom, mycelium_network, spore_cluster, ancient_bookshelf, runic_stone, compressed_pages, sapphire_cluster
5. Space & Dream (8): star_cluster, cosmic_nebula, prismatic_crystal, shadow_stone, echo_crystal, withered_stone, dream_cloud, reality_crystal

### Items (53)
1. Essences (4): flame_essence, water_essence, earth_essence, wind_essence
2. Hearts (4): inferno_heart, aquatic_heart, terran_heart, skybound_heart
3. Dream Set (9): dream_sword, dream_pickaxe, dream_axe, dream_shovel, dream_hoe, dream_helmet, dream_chestplate, dream_leggings, dream_boots
4. Sky Set (9): sky_sword, sky_pickaxe, sky_axe, sky_shovel, sky_hoe, sky_helmet, sky_chestplate, sky_leggings, sky_boots
5. Fungal Set (9): fungal_sword, fungal_pickaxe, fungal_axe, fungal_shovel, fungal_hoe, fungal_helmet, fungal_chestplate, fungal_leggings, fungal_boots
6. Archive Set (9): archive_sword, archive_pickaxe, archive_axe, archive_shovel, archive_hoe, archive_helmet, archive_chestplate, archive_leggings, archive_boots
7. Astral Set (5): astral_sword, astral_pickaxe, astral_axe, astral_shovel, astral_hoe
8. Gloom Set (4): gloom_sword, gloom_pickaxe, gloom_axe, gloom_shovel

### Mobs (42)
1. Bosses (11): pyros, leviathan, goliath, zephyros, the_primarch, the_dreamer, the_overmind, the_librarian, the_void_archon, the_echo_king, the_radiant_monarch
2. Regular Mobs (31): inferno_zombie, water_elemental, cave_troll, earth_elemental, stone_beast, sky_whale, lumifin, storm_hawk, void_rift, cosmic_elemental, celestial_guardian, phantasm, luller, nightmare_shade, somnian_butterfly, spore_bat, mycelium_walker, mycelite_creeper, scribe_spirit, living_tome, stone_sentinel, page_horror, star_elemental, nebula_wisp, voidling, echo_wisp, cave_snail, gloom_stalker, withered_skeleton, skywhale, sky_pirate

---

## ⚡ Recommended Workflow

### Option A: Local TextureMaker (Best Quality)
1. Set up TextureMaker with Stable Diffusion
2. Create Python script with all 146 prompts
3. Run overnight (each texture = 30 sec with GPU = ~2 hours total)
4. Manual cleanup if needed (pixel perfect editing)
5. Place in resource pack folders

### Option B: Online Stable Diffusion (Easier, Slower)
1. Use https://stablediffusionweb.com/
2. Generate 100 textures per day (free tier)
3. Takes 2-3 days for all 146 textures
4. Download and resize each to 16x16
5. Place in resource pack folders

### Option C: AIPackGenerator (Easiest, Lower Quality)
1. Clone and install AIPackGenerator
2. Run with comprehensive prompt
3. Get rough textures in bulk
4. Manually edit/improve in GIMP or Aseprite
5. Place in resource pack folders

---

## 🎯 Next Steps

1. **Choose your method** (A, B, or C above)
2. **Test with 5 textures first** to verify quality
3. **If satisfied, generate remaining textures**
4. **Place in correct folders** (use setup_textures.bat)
5. **Load in Minecraft and test**

---

## 📞 Troubleshooting

**"CUDA not available":**
- You're using CPU mode (works but slow)
- Install NVIDIA drivers if you have NVIDIA GPU
- Continue anyway, just takes longer

**"Module not found":**
- Ensure virtual environment is activated: `.\venv\Scripts\Activate.ps1`
- Reinstall dependencies: `pip install -r requirements.txt`

**"Textures look wrong":**
- Adjust `--steps` higher (30-50)
- Add more negative prompts: `realistic, 3D, smooth, gradient`
- Try different `--seed` values

**"Out of memory":**
- Reduce `--size` from 1024 to 512
- Close other applications
- Use CPU mode if GPU has low VRAM

---

## 📚 Resources

- **TextureMaker GitHub:** https://github.com/LyFl0w/TextureMaker
- **Stable Diffusion Guide:** https://stable-diffusion-art.com/
- **Minecraft Resource Pack Format:** https://minecraft.wiki/w/Resource_pack
- **Aseprite (Pixel Editor):** https://www.aseprite.org/ (paid) or Piskel (free online)

---

**Ready to start? Let me know which method you choose and I'll create the specific generation scripts!**
