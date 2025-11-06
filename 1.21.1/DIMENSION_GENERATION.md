# Custom Dimension Generation - Technical Details

## Overview
Each of the 6 new dimensions now has completely custom noise settings, making them visually and structurally distinct from vanilla Minecraft dimensions. No dimension uses `minecraft:overworld`, `minecraft:nether`, or any other vanilla noise settings.

---

## Dimension Characteristics

### 1. Dreaming Depths
**Noise Settings**: `elementaldimensions:dreaming_depths_settings`

**Unique Features**:
- **Sea Level**: 80 (higher than overworld)
- **Terrain Style**: Gentle, rolling hills with smooth transitions
- **Size Horizontal**: 2 (wider features)
- **Size Vertical**: 1 (standard height variation)
- **Y Range**: -64 to 320 (full height)
- **Default Block**: Dreamstone
- **Surface**: Dreamgrass
- **Subsurface**: Softsoil
- **Water**: Enabled with aquifers

**Generation Character**: Soft, dreamy landscapes with gentle slopes. Higher sea level creates more lake-filled valleys. Terrain is smoother and more ethereal than vanilla.

**Visual Identity**: Purple/lavender stone with softer terrain shapes, creating a surreal dreamscape feel.

---

### 2. Celestine Expanse
**Noise Settings**: `elementaldimensions:celestine_expanse_settings`

**Unique Features**:
- **Sea Level**: 200 (extremely high - sky dimension)
- **Terrain Style**: Floating islands with vast air gaps
- **Size Horizontal**: 4 (very wide features)
- **Size Vertical**: 1 (standard height)
- **Y Range**: 0 to 384 (no negative Y)
- **Default Block**: Air
- **Surface**: Celestine Grass
- **Subsurface**: Aether Sand
- **Water**: Disabled (no aquifers)

**Generation Character**: Sparse floating islands with huge empty voids between them. Low density values create dramatic air gaps. Perfect for sky dimension aesthetic.

**Visual Identity**: Cyan/blue floating landmasses suspended in air, creating a true sky realm experience completely different from the End.

---

### 3. Fungal Dominion
**Noise Settings**: `elementaldimensions:fungal_dominion_settings`

**Unique Features**:
- **Sea Level**: 63 (standard)
- **Terrain Style**: Dense, organic, bumpy terrain
- **Size Horizontal**: 1 (standard)
- **Size Vertical**: 3 (very tall features)
- **Y Range**: -64 to 320
- **Default Block**: Root Flesh
- **Surface**: Mycelite Soil
- **Subsurface**: Fungal Bark (5 blocks deep)
- **Water**: Enabled
- **Ore Veins**: Enabled

**Generation Character**: Very vertical terrain with tall mushroom-like structures. Higher vertical size creates towering fungal pillars. Dense continents with high multiplication factor (1.2).

**Visual Identity**: Green/brown organic terrain with extreme vertical variation, creating massive fungal structures unlike anything in vanilla.

---

### 4. Forgotten Archive
**Noise Settings**: `elementaldimensions:forgotten_archive_settings`

**Unique Features**:
- **Sea Level**: 32 (low)
- **Terrain Style**: Enclosed library halls with ceiling
- **Size Horizontal**: 1 (standard)
- **Size Vertical**: 1 (standard)
- **Y Range**: 0 to 256 (limited height, enclosed)
- **Default Block**: Runestone
- **Surface**: Dust Sand
- **Subsurface**: Archive Sandstone
- **Water**: Disabled
- **Bedrock Ceiling**: Yes (at Y 256)

**Generation Character**: Creates an enclosed library dimension with both floor and ceiling bedrock. Smaller scale noise creates more room-like spaces. Flat character for indoor feel.

**Visual Identity**: Gold/amber enclosed spaces like an ancient library. The bedrock ceiling creates a true "indoor dimension" feel never seen in vanilla.

---

### 5. Astral Frontier
**Noise Settings**: `elementaldimensions:astral_frontier_settings`

**Unique Features**:
- **Sea Level**: 0 (no sea)
- **Terrain Style**: Sparse asteroid fields
- **Size Horizontal**: 3 (wide features)
- **Size Vertical**: 1 (standard)
- **Y Range**: -64 to 320
- **Default Block**: Stardust Stone
- **Surface**: Starlit Grass
- **Subsurface**: Stardust Sand
- **Water**: Disabled
- **Ore Veins**: Enabled

**Generation Character**: Very low density (0.25 multiplier) creates sparse, asteroid-like floating rocks. Extreme Y gradients (3.0 to -3.0) create dramatic vertical cliffs. Space-like emptiness.

**Visual Identity**: Purple/magenta scattered asteroids floating in void, creating a true space dimension that's completely different from the End's solid islands.

---

### 6. Gloomy Caverns
**Noise Settings**: `elementaldimensions:gloomy_caverns_settings`

**Unique Features**:
- **Sea Level**: 32 (low)
- **Terrain Style**: Dense underground caverns
- **Size Horizontal**: 1 (standard)
- **Size Vertical**: 2 (taller features)
- **Y Range**: -64 to 192 (shorter than overworld)
- **Default Block**: Withered Basalt
- **Surface**: Drip Moss
- **Subsurface**: Decay Soil (4 blocks deep)
- **Water**: Enabled with aquifers

**Generation Character**: Dense, oppressive cavern systems. Higher density multiplier (1.1) creates more solid terrain. Lower height limit makes it feel more confined than overworld caves.

**Visual Identity**: Gray/dark claustrophobic caverns filled with dripping water and decay, creating an oppressive underground feel distinct from vanilla caves.

---

## Key Differences from Vanilla

### Not Using Vanilla Settings
- **No `minecraft:overworld`**: Each dimension has custom terrain generation
- **No `minecraft:nether`**: No reuse of Nether's generation
- **No `minecraft:end`**: No reuse of End's floating island pattern
- **No `minecraft:caves`**: Custom cave generation through noise settings

### Unique Noise Parameters
Each dimension uses different combinations of:
- **Continentalness Scale**: 0.05 to 0.4 (vanilla: 0.25)
- **Erosion Scale**: 0.05 to 0.5 (vanilla: 0.25)
- **Density Multipliers**: 0.2 to 1.2 (vanilla: 0.64)
- **Y Gradients**: 0.9 to 3.0 (vanilla: 1.5)
- **Horizontal Size**: 1 to 4 (vanilla: 1)
- **Vertical Size**: 1 to 3 (vanilla: 2)

### Custom Features
1. **Dreaming Depths**: Smoother terrain, higher sea level
2. **Celestine Expanse**: True floating islands with massive voids
3. **Fungal Dominion**: Extreme vertical structures
4. **Forgotten Archive**: Enclosed dimension with ceiling
5. **Astral Frontier**: Sparse asteroids in void
6. **Gloomy Caverns**: Dense, oppressive caverns

---

## Surface Rules

Each dimension has custom surface rules that place dimension-specific blocks:

1. **Bedrock Placement**: Custom bedrock layers (some dimensions have ceiling bedrock)
2. **Surface Blocks**: Dimension-specific grass/soil variants
3. **Subsurface Layers**: Unique subsurface materials at different depths
4. **No Stone**: Many dimensions don't use stone at all

---

## Technical Implementation

### File Locations
```
data/elementaldimensions/worldgen/noise_settings/
├── dreaming_depths_settings.json
├── celestine_expanse_settings.json
├── fungal_dominion_settings.json
├── forgotten_archive_settings.json
├── astral_frontier_settings.json
└── gloomy_caverns_settings.json
```

### Dimension Files Updated
```
data/elementaldimensions/dimension/
├── dreaming_depths.json → uses dreaming_depths_settings
├── celestine_expanse.json → uses celestine_expanse_settings
├── fungal_dominion.json → uses fungal_dominion_settings
├── forgotten_archive.json → uses forgotten_archive_settings
├── astral_frontier.json → uses astral_frontier_settings
└── gloomy_caverns.json → uses gloomy_caverns_settings
```

---

## Visual Comparison to Vanilla

| Dimension | Vanilla Equivalent | Key Difference |
|-----------|-------------------|----------------|
| Dreaming Depths | None | Softer, dreamier terrain than overworld |
| Celestine Expanse | End | Larger islands, more dramatic voids, no center island |
| Fungal Dominion | Mushroom Biome | Extreme vertical structures, organic material |
| Forgotten Archive | None | Enclosed library with ceiling bedrock |
| Astral Frontier | End | Sparse asteroids vs solid islands |
| Gloomy Caverns | Deep Dark | Denser, more oppressive, different materials |

---

## World Generation Performance

All noise settings are optimized for performance:
- Using `flat_cache` for continents and ridges (reduces recalculation)
- Appropriate noise scales to avoid excessive detail
- Efficient density functions with minimal nested operations
- Custom ore vein settings only where needed

---

## Player Experience

When players teleport to these dimensions, they will immediately notice:

1. **Different Terrain Shapes**: No dimension looks like vanilla
2. **Unique Block Palettes**: Each uses dimension-specific blocks
3. **Distinct Atmospheres**: From dreamy to oppressive
4. **Varied Challenges**: Some have void risks, others are enclosed
5. **Exploration Variety**: Each dimension rewards different exploration styles

---

**Result**: 6 completely unique dimensions that feel nothing like vanilla Minecraft, each with its own identity and gameplay experience!

---

*Generated: November 5, 2025*
*Minecraft Version: 1.21.1*
*Mod Version: 1.0.0*
