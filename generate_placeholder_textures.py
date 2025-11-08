"""
Quick Placeholder Texture Generator
Creates simple colored placeholder textures so you can test the mod NOW
Then replace with AI-generated ones later when your computer isn't overloaded
"""

from PIL import Image, ImageDraw, ImageFont
from pathlib import Path

BASE_DIR = Path("C:/Users/Nikhil/Desktop/elemental_dimensions/ResourcePack_ElementalDimensions/assets/elementaldimensions/textures")

# Color schemes for each dimension
COLORS = {
    "inferno": (40, 20, 20, 255),  # Dark red-black
    "aqua": (20, 60, 80, 255),     # Deep blue
    "terran": (60, 40, 20, 255),   # Brown
    "skybound": (120, 180, 220, 255),  # Light blue
    "celestial": (80, 40, 120, 255),   # Purple
    "void": (20, 10, 30, 255),     # Nearly black
    "dream": (160, 120, 180, 255), # Pastel purple
    "fungal": (100, 140, 40, 255), # Yellow-green
    "archive": (140, 120, 90, 255), # Tan
    "astral": (60, 20, 80, 255),   # Dark purple
    "gloom": (40, 40, 50, 255),    # Dark gray
}

ACCENT_COLORS = {
    "inferno": (255, 100, 0, 255),  # Orange
    "aqua": (0, 200, 255, 255),     # Cyan
    "terran": (0, 255, 100, 255),   # Green
    "skybound": (255, 255, 255, 255),  # White
    "celestial": (255, 200, 255, 255), # Pink
    "void": (80, 0, 120, 255),      # Purple
    "dream": (255, 150, 255, 255),  # Pink
    "fungal": (150, 255, 0, 255),   # Lime
    "archive": (0, 150, 255, 255),  # Blue
    "astral": (200, 150, 255, 255), # Light purple
    "gloom": (100, 150, 200, 255),  # Blue-gray
}

def create_stone_texture(name, base_color, accent_color, size=16):
    """Create a simple stone texture"""
    img = Image.new('RGBA', (size, size), base_color)
    draw = ImageDraw.Draw(img)

    # Add some accent pixels for variation
    import random
    random.seed(hash(name))
    for _ in range(size // 2):
        x = random.randint(0, size-1)
        y = random.randint(0, size-1)
        draw.point((x, y), accent_color)

    return img

def create_ore_texture(name, base_color, accent_color, size=16):
    """Create a simple ore texture"""
    img = Image.new('RGBA', (size, size), base_color)
    draw = ImageDraw.Draw(img)

    # Add bright crystal spots
    import random
    random.seed(hash(name))
    for _ in range(size // 3):
        x = random.randint(1, size-2)
        y = random.randint(1, size-2)
        # Draw small crystal cluster (2x2)
        draw.rectangle([x, y, x+1, y+1], fill=accent_color)

    return img

def create_item_texture(name, color, size=16):
    """Create a simple item icon"""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)

    # Draw centered shape
    margin = size // 4
    draw.ellipse([margin, margin, size-margin, size-margin], fill=color)

    return img

print("="*70)
print(" QUICK PLACEHOLDER TEXTURE GENERATOR")
print(" Creates simple colored placeholders for immediate testing")
print("="*70)

# Create directories
(BASE_DIR / "block").mkdir(parents=True, exist_ok=True)
(BASE_DIR / "item").mkdir(parents=True, exist_ok=True)
(BASE_DIR / "entity").mkdir(parents=True, exist_ok=True)

textures_created = 0

# Dimension stones
for dim_name, color in COLORS.items():
    accent = ACCENT_COLORS[dim_name]

    # Stone
    stone_name = f"{dim_name}_stone"
    img = create_stone_texture(stone_name, color, accent)
    img.save(BASE_DIR / "block" / f"{stone_name}.png")
    print(f"✅ Created {stone_name}.png")
    textures_created += 1

    # Ore
    ore_name = f"{dim_name}_ore"
    img = create_ore_texture(ore_name, color, accent)
    img.save(BASE_DIR / "block" / f"{ore_name}.png")
    print(f"✅ Created {ore_name}.png")
    textures_created += 1

# Special blocks (simple solid colors for now)
special_blocks = [
    ("atlantis_pillar", (100, 120, 130, 255)),
    ("atlantis_brick", (80, 100, 110, 255)),
    ("red_coral", (255, 80, 80, 255)),
    ("blue_coral", (80, 150, 255, 255)),
    ("amethyst_cluster", (180, 120, 200, 255)),
    ("cloud_block", (255, 255, 255, 255)),
    ("aether_grass", (100, 200, 220, 255)),
    ("enchanted_oak_log", (180, 140, 60, 255)),
    ("radiant_star", (255, 255, 200, 255)),
    ("nebula_gas", (140, 80, 180, 255)),
]

for name, color in special_blocks:
    img = Image.new('RGBA', (16, 16), color)
    img.save(BASE_DIR / "block" / f"{name}.png")
    print(f"✅ Created {name}.png")
    textures_created += 1

# Items (essences and hearts)
items = [
    ("flame_essence", (255, 100, 0, 255)),
    ("water_essence", (0, 150, 255, 255)),
    ("earth_essence", (0, 200, 100, 255)),
    ("wind_essence", (200, 220, 255, 255)),
    ("inferno_heart", (255, 0, 0, 255)),
    ("aquatic_heart", (0, 200, 255, 255)),
    ("terran_heart", (0, 255, 0, 255)),
    ("skybound_heart", (255, 255, 255, 255)),
]

for name, color in items:
    img = create_item_texture(name, color)
    img.save(BASE_DIR / "item" / f"{name}.png")
    print(f"✅ Created {name}.png")
    textures_created += 1

# Equipment sets (basic colored items)
equipment_sets = [
    ("dream", (160, 100, 200, 255)),
    ("sky", (255, 220, 100, 255)),
    ("fungal", (100, 255, 50, 255)),
    ("archive", (100, 150, 255, 255)),
    ("astral", (140, 80, 200, 255)),
    ("gloom", (60, 60, 80, 255)),
]

equipment_types = ["sword", "pickaxe", "axe", "shovel", "hoe", "helmet", "chestplate", "leggings", "boots"]

for set_name, color in equipment_sets:
    for equip_type in equipment_types:
        name = f"{set_name}_{equip_type}"
        img = create_item_texture(name, color)
        img.save(BASE_DIR / "item" / f"{name}.png")
        print(f"✅ Created {name}.png")
        textures_created += 1

# Boss sprites (32x32 simple placeholders)
bosses = [
    ("pyros", (255, 80, 0, 255)),
    ("leviathan", (0, 100, 255, 255)),
    ("goliath", (120, 80, 40, 255)),
    ("zephyros", (200, 220, 255, 255)),
    ("the_primarch", (140, 100, 200, 255)),
    ("the_dreamer", (180, 120, 200, 255)),
    ("the_overmind", (100, 200, 50, 255)),
    ("the_librarian", (150, 120, 80, 255)),
    ("the_void_archon", (40, 0, 80, 255)),
    ("the_echo_king", (60, 60, 80, 255)),
    ("the_radiant_monarch", (255, 220, 100, 255)),
]

for name, color in bosses:
    img = Image.new('RGBA', (32, 32), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    # Simple monster shape
    draw.rectangle([8, 4, 24, 28], fill=color)  # Body
    draw.rectangle([12, 0, 20, 4], fill=color)  # Head
    print(f"✅ Created {name}.png (32x32)")
    img.save(BASE_DIR / "entity" / f"{name}.png")
    textures_created += 1

print("\n" + "="*70)
print(f" ✅ COMPLETE! Created {textures_created} placeholder textures")
print("="*70)
print(f"\n📁 Location: {BASE_DIR}")
print("\n✨ Next steps:")
print("1. Copy ResourcePack to %appdata%\\.minecraft\\resourcepacks\\")
print("2. Test mod in Minecraft NOW with placeholders")
print("3. Replace with AI textures later when computer isn't overloaded")
print("\n🎨 Placeholders are simple but functional - mod will work!")
