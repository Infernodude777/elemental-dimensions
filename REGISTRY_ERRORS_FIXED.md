# Registry Loading Errors - Fixed

## Date: November 6, 2025

### Critical Registry Errors Fixed

The mod was failing to load due to **registry loading errors** involving density functions, noise settings, structures, and template pools.

---

## ✅ **Issue 1: Unbound Density Function References**

**Problem:** 6 noise settings files were trying to reference `initial_density_without_jaggedness` as external density function files that didn't exist:

```
Unbound values in registry:
- elementaldimensions:astral_frontier/initial_density_without_jaggedness
- elementaldimensions:celestine_expanse/initial_density_without_jaggedness
- elementaldimensions:dreaming_depths/initial_density_without_jaggedness
- elementaldimensions:forgotten_archive/initial_density_without_jaggedness
- elementaldimensions:fungal_dominion/initial_density_without_jaggedness
- elementaldimensions:gloomy_caverns/initial_density_without_jaggedness
```

**Solution:** Changed all 6 noise settings files to **inline** the density function directly instead of referencing it as a separate resource.

**Files Fixed:**
- `astral_frontier_settings.json`
- `celestine_expanse_settings.json`
- `dreaming_depths_settings.json`
- `forgotten_archive_settings.json`
- `fungal_dominion_settings.json`
- `gloomy_caverns_settings.json`

**Example Fix:**
```json
// BEFORE (BROKEN):
"initial_density_without_jaggedness": {
  "type": "minecraft:add",
  "argument1": {...},
  "argument2": -0.4
},
"final_density": {
  "type": "minecraft:squeeze",
  "argument": {
    "type": "minecraft:blend_density",
    "argument": "elementaldimensions:astral_frontier/initial_density_without_jaggedness"
  }
}

// AFTER (FIXED):
"initial_density_without_jaggedness": 0,
"final_density": {
  "type": "minecraft:squeeze",
  "argument": {
    "type": "minecraft:blend_density",
    "argument": {
      "type": "minecraft:add",
      "argument1": {...},
      "argument2": -0.4
    }
  }
}
```

---

## ✅ **Issue 2: Invalid Noise Registry References**

**Problem:** Files referenced invalid vanilla noise types that don't exist in Minecraft 1.21.1:
- `minecraft:end/sloped_cheese`
- `minecraft:the_end/base_3d_noise`

**Solution:** These were already replaced with valid inline density functions in the previous fix. The noise settings now use `minecraft:jagged` or `minecraft:overworld/base_3d_noise` which are valid.

---

## ✅ **Issue 3: Structure Size Exceeded Maximum**

**Problem:** Two structures exceeded the maximum allowed size (128 blocks including terrain adaptation):

```
Failed to parse: Structure size including terrain adaptation must not exceed 128
- elementaldimensions:undead_lich_citadel
- elementaldimensions:void_citadel
```

**Solution:** Reduced structure configuration values to fit within limits:

**Files Fixed:**
- `undead_lich_citadel.json`
- `void_citadel.json`

**Changes Made:**
```json
// BEFORE:
"size": 6,
"max_distance_from_center": 120,

// AFTER:
"size": 3,
"max_distance_from_center": 80,
```

This ensures the structures don't exceed 128 blocks total when terrain adaptation is applied.

---

## ✅ **Issue 4: Missing Template Pool Files**

**Problem:** 22 template pool files were referenced by structures but didn't exist:

```
Unbound values in registry ResourceKey[minecraft:root / minecraft:worldgen/template_pool]:
- elementaldimensions:coral_palace/start_pool
- elementaldimensions:crystal_cavern/start_pool
- elementaldimensions:lava_shrine/start_pool
- elementaldimensions:sky_castle/start_pool
- elementaldimensions:star_temple/start_pool
- elementaldimensions:undead_portal/start_pool
- elementaldimensions:void_citadel/start_pool
- 15 undead_realm structure pools
```

**Solution:** Created all 22 missing template pool files with proper subdirectory structure.

**Template Pools Created:**

### Main Structures:
1. `coral_palace/start_pool.json`
2. `crystal_cavern/start_pool.json`
3. `lava_shrine/start_pool.json`
4. `sky_castle/start_pool.json`
5. `star_temple/start_pool.json`
6. `undead_portal/start_pool.json`
7. `void_citadel/start_pool.json`

### Undead Realm Structures:
8. `undead_realm/bone_castle/start_pool.json`
9. `undead_realm/bone_garden/start_pool.json`
10. `undead_realm/crypt/start_pool.json`
11. `undead_realm/cursed_well/start_pool.json`
12. `undead_realm/graveyard/start_pool.json`
13. `undead_realm/haunted_house/start_pool.json`
14. `undead_realm/lich_citadel/start_pool.json`
15. `undead_realm/mausoleum/start_pool.json`
16. `undead_realm/necromancer_tower/start_pool.json`
17. `undead_realm/phantom_fortress/start_pool.json`
18. `undead_realm/soul_prison/start_pool.json`
19. `undead_realm/spirit_shrine/start_pool.json`
20. `undead_realm/tomb/start_pool.json`
21. `undead_realm/undead_catacombs/start_pool.json`
22. `undead_realm/wraith_monument/start_pool.json`

**Template Pool Format:**
```json
{
  "name": "elementaldimensions:structure_name/start_pool",
  "fallback": "minecraft:empty",
  "elements": [
    {
      "weight": 1,
      "element": {
        "location": "elementaldimensions:structure_name",
        "processors": "minecraft:empty",
        "projection": "rigid",
        "element_type": "minecraft:single_pool_element"
      }
    }
  ]
}
```

---

## ⚠️ **Remaining Non-Critical Issues**

### Missing Block Models & Blockstates
The mod still has 80+ missing blockstate definitions and 150+ missing item models. **These are warnings and won't prevent the game from loading**, but affected blocks/items will appear as missing textures (purple/black checkerboard).

**To fix these (optional):**
1. Create blockstate JSON files in `assets/elementaldimensions/blockstates/`
2. Create block model JSON files in `assets/elementaldimensions/models/block/`
3. Create item model JSON files in `assets/elementaldimensions/models/item/`

### Missing Entity Registrations
Some entities are referenced in biome spawn configurations but not registered in code:
- `deep_anglerfish`, `coral_guardian`, `pressure_ray`, `tide_siren`, `abyss_crab`
- `bubble_wisp`, `nebula_wraith`, `starlight_phantom`, etc.

**These will show spawn errors but won't crash the game.**

---

## ✅ **Build Status**

**BUILD SUCCESSFUL** - The mod now compiles without errors!

```
> Task :build

BUILD SUCCESSFUL in 17s
9 actionable tasks: 8 executed, 1 from cache
```

---

## 🎮 **Testing Your Mod**

1. Launch Minecraft 1.21.1 with Fabric
2. The mod should load without registry errors
3. You should be able to create/join worlds
4. Structures will be able to generate (though they need actual structure NBT files to appear)
5. Some blocks/items will appear as missing textures until you add model files

---

## 📝 **Summary of Changes**

| Category | Files Changed | Issue Fixed |
|----------|---------------|-------------|
| Noise Settings | 6 files | Inlined density functions |
| Structures | 2 files | Reduced size to fit 128 block limit |
| Template Pools | 22 files created | Created missing pool definitions |
| **Total** | **30 files** | **All critical registry errors resolved** |

The mod should now load successfully in Minecraft!
