# Structure System Implementation Guide

## Current Status: PARTIAL IMPLEMENTATION

The structure system has basic foundation code but requires significant additional work for full implementation. This document explains what exists, what's needed, and how to complete it.

---

## ✅ What's Already Done

### 1. Structure Registry Keys (ModStructures.java)
Located: `src/main/java/com/elementaldimensions/world/gen/ModStructures.java`

**Registered Structures (18 total):**

**Firelands (3):**
- `lava_shrine` - Small lava-themed shrine
- `ember_fortress` - Large fortress structure
- `volcanic_temple` - Temple with boss arena

**Aquatica (3):**
- `coral_palace` - Underwater palace
- `underwater_ruins` - Ruined structure
- `abyssal_shrine` - Deep ocean shrine

**Terra Depths (3):**
- `crystal_cavern` - Crystal-filled cave
- `moss_sanctuary` - Moss-covered sanctuary
- `ancient_gaia_temple` - Earth temple with boss

**Skyreach Peaks (3):**
- `sky_castle` - Floating castle
- `floating_island_ruin` - Ruined floating island
- `wind_altar` - Windswept altar

**Celestial (3):**
- `star_temple` - Star-themed temple
- `nebula_sanctum` - Nebula sanctuary
- `cosmic_observatory` - Observatory structure

**Void Realm (3):**
- `void_citadel` - Dark citadel
- `nullstone_tower` - Tall tower
- `shadow_portal` - Portal structure

### 2. Simple Structure Feature Classes
- `SimpleStructureFeature.java` - Basic procedural structure generator
- `SimpleStructureConfig.java` - Configuration for simple structures

**What They Do:**
- Generate simple 5x5x5 hollow shrine structures
- Use dimension-specific blocks
- Place a chest in the center
- Placeholder for full NBT-based structures

---

## ⚠️ What's Missing (8-12 Hours of Work)

### 1. NBT Template Files (Most Time-Consuming)

**What Are NBT Files?**
- Binary files containing block-by-block structure data
- Created in-game using Structure Blocks
- Allow complex, hand-designed structures

**How to Create Them:**
1. **In Minecraft:**
   - Place Structure Block (command: `/give @s structure_block`)
   - Build your structure nearby
   - Set Structure Block to "Save" mode
   - Enter structure name (e.g., "lava_shrine")
   - Set the size to encompass your structure
   - Click "Save"

2. **File Location:**
   - Saves to: `world/generated/minecraft/structures/`
   - Copy to: `src/main/resources/data/elementaldimensions/structures/`

3. **Required Files:**
   - 18 structure NBT files (one per structure)
   - Estimated time per structure: 20-40 minutes
   - **Total time: 6-12 hours**

**Recommended Structure Sizes:**
```
Small (Shrines/Altars):  15x15x15 blocks
Medium (Temples/Ruins):  25x25x30 blocks
Large (Fortresses/Castles): 40x40x50 blocks
```

### 2. Structure Feature Classes

Need to create proper structure features that:
- Load NBT templates using `StructureTemplate`
- Handle rotation and mirroring
- Adapt to terrain (ground level detection)
- Place loot chests with correct loot tables
- Add boss spawners where appropriate

**Example Implementation:**
```java
public class TempleStructureFeature extends Feature<DefaultFeatureConfig> {
    private final Identifier templateId;

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();

        // Get structure template manager
        StructureTemplateManager templateManager =
            world.toServerWorld().getStructureTemplateManager();

        // Load template
        Optional<StructureTemplate> template =
            templateManager.getTemplate(templateId);

        if (template.isPresent()) {
            // Place structure with rotation
            PlacementData placementData = new PlacementData()
                .setRotation(BlockRotation.random(context.getRandom()))
                .setMirror(BlockMirror.NONE);

            template.get().place(world, pos, pos, placementData,
                context.getRandom(), Block.NOTIFY_ALL);

            return true;
        }

        return false;
    }
}
```

### 3. Structure Placement Configuration

Create JSON files in: `src/main/resources/data/elementaldimensions/worldgen/structure/`

**Example: `lava_shrine.json`**
```json
{
  "type": "minecraft:jigsaw",
  "biomes": "#elementaldimensions:has_structure/lava_shrine",
  "spawn_overrides": {},
  "step": "surface_structures",
  "terrain_adaptation": "beard_thin",
  "start_pool": "elementaldimensions:lava_shrine/start_pool",
  "size": 1,
  "max_distance_from_center": 80,
  "start_height": {
    "absolute": 0
  }
}
```

### 4. Structure Sets

Define how often structures spawn in: `src/main/resources/data/elementaldimensions/worldgen/structure_set/`

**Example: `firelands_structures.json`**
```json
{
  "structures": [
    {
      "structure": "elementaldimensions:lava_shrine",
      "weight": 3
    },
    {
      "structure": "elementaldimensions:ember_fortress",
      "weight": 1
    },
    {
      "structure": "elementaldimensions:volcanic_temple",
      "weight": 1
    }
  ],
  "placement": {
    "type": "minecraft:random_spread",
    "spacing": 32,
    "separation": 8,
    "salt": 1234567
  }
}
```

### 5. Biome Tags

Create biome tags to control where structures spawn:
`src/main/resources/data/elementaldimensions/tags/worldgen/biome/`

**Example: `has_structure/lava_shrine.json`**
```json
{
  "values": [
    "elementaldimensions:firelands_biome",
    "elementaldimensions:volcanic_plains"
  ]
}
```

### 6. Loot Tables

Create loot tables for structure chests:
`src/main/resources/data/elementaldimensions/loot_table/chests/`

**Example: `lava_shrine.json`**
```json
{
  "type": "minecraft:chest",
  "pools": [
    {
      "rolls": {
        "min": 3,
        "max": 7
      },
      "entries": [
        {
          "type": "minecraft:item",
          "name": "elementaldimensions:blazing_ore",
          "weight": 10,
          "functions": [
            {
              "function": "minecraft:set_count",
              "count": {
                "min": 2,
                "max": 8
              }
            }
          ]
        },
        {
          "type": "minecraft:item",
          "name": "elementaldimensions:fire_essence",
          "weight": 5
        }
      ]
    }
  ]
}
```

### 7. Structure Pool Templates (Jigsaw)

For complex structures with random variation:
`src/main/resources/data/elementaldimensions/worldgen/template_pool/`

This allows structures to have randomized components (e.g., different room layouts).

---

## 🚀 Quick Start: Simplified Approach (2-3 Hours)

If you want structures working quickly without NBT files:

### Option A: Use SimpleStructureFeature (Already Created)

The `SimpleStructureFeature` class generates basic procedural structures. To use it:

1. **Register Feature in ModFeatures.java:**
```java
public static final Feature<SimpleStructureConfig> SIMPLE_STRUCTURE =
    Registry.register(Registries.FEATURE,
        Identifier.of(ElementalDimensions.MOD_ID, "simple_structure"),
        new SimpleStructureFeature(SimpleStructureConfig.CODEC));
```

2. **Create Configured Features:**
```java
// Firelands shrine
public static final RegistryKey<ConfiguredFeature<?, ?>> LAVA_SHRINE =
    registerKey("lava_shrine");

public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
    register(context, LAVA_SHRINE, ModFeatures.SIMPLE_STRUCTURE,
        new SimpleStructureConfig(
            ModBlocks.INFERNO_STONE.getDefaultState(),
            ModBlocks.BLAZING_ORE.getDefaultState(),
            4  // Size
        ));
}
```

3. **Add to Dimension Biome Generation:**
This is simpler but structures will be basic geometric shapes.

### Option B: Use Vanilla Structure Templates

Copy and modify vanilla structure code:
- Look at `net.minecraft.world.gen.structure` package
- Extend existing structure types (e.g., `DesertPyramidStructure`)
- Customize blocks and loot

---

## 📊 Time Estimates

| Task | Time Required | Priority |
|------|---------------|----------|
| Create 18 NBT templates | 6-12 hours | HIGH |
| Structure feature classes | 2-3 hours | HIGH |
| Placement config JSONs | 1-2 hours | MEDIUM |
| Structure sets | 1 hour | MEDIUM |
| Biome tags | 30 min | LOW |
| Loot tables | 1-2 hours | MEDIUM |
| Testing & debugging | 2-3 hours | HIGH |
| **TOTAL (Full Implementation)** | **13-23 hours** | - |
| **TOTAL (Simplified)** | **2-3 hours** | - |

---

## 🎯 Recommended Approach

### Phase 1: Get Something Working (2-3 hours)
1. Use `SimpleStructureFeature` to generate basic structures
2. Create 3-5 simple structures per dimension
3. Add basic loot chests
4. Test in-game

### Phase 2: Create NBT Templates (6-12 hours)
1. Build 2-3 structures per dimension in creative mode
2. Use Structure Blocks to save them
3. Copy NBT files to mod resources
4. Test loading and placement

### Phase 3: Polish (3-5 hours)
1. Add structure sets for controlled spawning
2. Create dimension-specific loot tables
3. Add boss spawners to appropriate structures
4. Fine-tune spawn rates and distances

---

## 🔧 Testing Structures

### In-Game Commands:
```
/locate structure elementaldimensions:lava_shrine
/tp @s ~ ~ ~
/fill ~-50 ~-10 ~-50 ~50 ~30 ~50 air
```

### Debugging:
- Enable debug logging in `ElementalDimensions.java`
- Check server log for structure placement messages
- Use F3+G to see chunk boundaries
- Use `/seed` to reproduce world generation

---

## 📝 Next Steps

**To complete structure system:**

1. **Immediate (30 min):**
   - Register `SimpleStructureFeature` in ModFeatures
   - Create basic configured features for each dimension
   - Test one structure spawning

2. **Short-term (2-3 hours):**
   - Build 1-2 template structures per dimension in-game
   - Save as NBT using Structure Blocks
   - Implement proper structure feature loading

3. **Long-term (8-12 hours):**
   - Create all 18 structures as NBT templates
   - Design unique interiors with loot
   - Add boss arenas with spawners
   - Implement structure sets for controlled placement
   - Create dimension-specific loot tables

---

## 🎨 Structure Design Tips

### Shrines (Small):
- 10x10x15 blocks
- Central altar or pedestal
- 1-2 loot chests
- Decorative elements
- Spawn rate: Common

### Temples (Medium):
- 20x20x25 blocks
- Multiple rooms
- Puzzle elements or parkour
- 3-4 loot chests
- Boss spawner (optional)
- Spawn rate: Uncommon

### Fortresses/Castles (Large):
- 40x40x50+ blocks
- Many interconnected rooms
- Multiple levels
- 6-8 loot chests
- Boss arena
- Spawn rate: Rare

---

## ✅ Current Recommendation

Given the time constraint (8-12 hours estimated for full structures), I recommend:

**Option 1: Skip for v1.0, Add in v1.1**
- Release mod without structures
- Gather player feedback
- Add structures in major update
- Allows time for quality design

**Option 2: Simplified Implementation (2-3 hours)**
- Use `SimpleStructureFeature` for basic structures
- Create 1 simple structure per dimension
- Add basic loot chests
- Note in mod description: "Basic structures, more coming soon"

**Option 3: Full Implementation (13-23 hours)**
- Complete all 18 structures with NBT templates
- Full placement system
- Dimension-specific loot
- Boss arenas
- Polish and testing

---

## 📖 Additional Resources

- [Minecraft Wiki: Structure Blocks](https://minecraft.wiki/w/Structure_Block)
- [Fabric Wiki: World Generation](https://fabricmc.net/wiki/tutorial:biomes)
- [Minecraft Structure Tutorial](https://youtu.be/structure_tutorial)
- Vanilla structure code: `net.minecraft.world.gen.structure` package

---

**Status:** Structure foundation created, full implementation pending
**Estimated Completion:** 2-23 hours depending on approach
**Priority:** MEDIUM (mod playable without structures)
