# Custom Dimension Uniqueness - Configuration Guide

## ✅ **What I've Changed**

### **Custom Infiniburn Tags (6 new tags created)**

Each dimension now has its own custom infiniburn tag that determines which blocks burn infinitely:

1. **Astral Frontier** → `#elementaldimensions:infiniburn_astral`
   - Burns: cosmic_stone, void_stone, nebular_stone, stardust_block

2. **Celestine Expanse** → `#elementaldimensions:infiniburn_celestine`
   - Burns: celestine_dirt, skybound_stone_brick, sky_reef, celestine_cloud

3. **Dreaming Depths** → `#elementaldimensions:infiniburn_dreaming`
   - Burns: dreamwood_planks, starlit_grass, somnia_brick, dream_sand

4. **Fungal Dominion** → `#elementaldimensions:infiniburn_fungal`
   - Burns: fungal_planks, spore_block, mycelite_stone, giant_mushroom_stem

5. **Forgotten Archive** → `#elementaldimensions:infiniburn_archive`
   - Burns: ancient_shelf, dusty_tome_block, archive_sandstone_brick, smooth_ink_marble

6. **Gloomy Caverns** → `#elementaldimensions:infiniburn_gloomy`
   - Burns: shadestone, mournstone, withered_brick, echo_brick

### **Effect Types Chosen (Strategic Selection)**

Since Minecraft only allows 3 effect types, I strategically assigned them:

- **`minecraft:the_end`** → Space/mystical dimensions (purple/void particles, End-like fog)
  - Astral Frontier (starry void)
  - Celestine Expanse (sky realm)
  - Dreaming Depths (dream realm)
  - Forgotten Archive (ancient knowledge)

- **`minecraft:the_nether`** → Dark/underground dimensions (red fog, ash particles)
  - Fungal Dominion (spore-filled caves)
  - Gloomy Caverns (dark underground)

---

## 🎨 **To Make Dimensions 80%+ Custom Blocks**

### **What Makes a Dimension Look Unique:**

Your dimensions are already configured with custom noise settings and will use different terrain generation. However, to achieve **80% custom block coverage**, you need to:

### **1. Surface Rules (Most Important!)**

Update your noise settings files to use **custom blocks as default blocks and surface blocks**. Currently they use vanilla blocks like:

**Current Problems:**
```json
// In astral_frontier_settings.json
"default_block": {
  "Name": "minecraft:blackstone"  // ❌ VANILLA
}

// In celestine_expanse_settings.json
"default_block": {
  "Name": "minecraft:end_stone"  // ❌ VANILLA
}
```

**Fix Required:**
```json
// Astral Frontier should use:
"default_block": {
  "Name": "elementaldimensions:cosmic_stone"  // ✅ CUSTOM
}

// Celestine Expanse should use:
"default_block": {
  "Name": "elementaldimensions:celestine_dirt"  // ✅ CUSTOM
}
```

### **2. Biome Block Replacements**

In your biome JSON files (which I haven't seen yet), make sure surface builders use custom blocks:

**Example for a custom biome:**
```json
{
  "surface_builder": "elementaldimensions:astral_surface",
  "carvers": {},
  "features": [],
  "starts": [],
  "spawners": {},
  "spawn_costs": {},
  "player_spawn_friendly": false,
  "precipitation": "none",
  "temperature": 0.5,
  "downfall": 0.0
}
```

### **3. Structure Blocks**

Your structure NBT files should be built entirely from custom blocks (which you'll create in-game).

---

## 📋 **Complete Checklist for 80% Custom Appearance**

### ✅ **Already Done:**
- [x] Custom infiniburn tags per dimension
- [x] Unique effect types assigned strategically
- [x] Unique ambient light levels per dimension
- [x] Unique skylight/ceiling configurations
- [x] Custom time settings (some dimensions)

### ⚠️ **Still Needs Fixing:**

#### **High Priority (Makes the biggest visual difference):**
1. **Update noise_settings files:**
   - [ ] Change `default_block` from vanilla to custom blocks
   - [ ] Change `default_fluid` if needed (custom fluids?)
   - [ ] Update surface_rule sequences to place custom blocks

2. **Update biome files:**
   - [ ] Use custom blocks for surface/underground
   - [ ] Add custom vegetation (trees, plants) made from custom blocks
   - [ ] Add custom ore generation with custom ores

#### **Medium Priority:**
3. **Structure Templates:**
   - [ ] Build structures in-game using 80%+ custom blocks
   - [ ] Export as NBT files
   - [ ] Place in `data/elementaldimensions/structures/`

4. **Custom Features:**
   - [ ] Create custom placed_feature JSON files
   - [ ] Use custom blocks for geode interiors, ore veins, etc.

#### **Low Priority (Polish):**
5. **Particles & Sounds:**
   - Custom ambient sounds per dimension (requires code)
   - Custom particle effects (requires code)

6. **Sky Colors:**
   - Custom fog colors, sky colors, water colors (requires code in biome builders)

---

## 🎯 **Quick Win: Update Default Blocks Now**

Let me know if you want me to update all your noise_settings files to use custom default blocks. I can do that immediately!

**Dimension → Recommended Default Block:**
- **Astral Frontier** → `cosmic_stone` or `void_stone`
- **Celestine Expanse** → `celestine_dirt` or `sky_reef`
- **Dreaming Depths** → `starlit_grass` or `dreamwood_planks`
- **Fungal Dominion** → `mycelite_stone` or `spore_block`
- **Forgotten Archive** → `archive_sandstone_brick` or `ancient_shelf`
- **Gloomy Caverns** → `shadestone` or `mournstone`

---

## 💡 **Note About Effects**

Minecraft 1.21.1 **only** supports 3 effect types:
- `minecraft:overworld` (blue sky, clouds, day/night cycle)
- `minecraft:the_nether` (red fog, no sky)
- `minecraft:the_end` (void sky, End particles)

**We cannot create custom effect namespaces** like `elementaldimensions:astral_effect`. However, the visual appearance is primarily controlled by:
1. **Block composition** (80%+ custom blocks)
2. **Fog/sky colors** (set in Java code for custom biomes)
3. **Ambient light** (already customized per dimension)
4. **Custom particles** (requires Java code)

The `effects` field mainly controls:
- Particle types that spawn
- Fog density/color (can override in biome code)
- Whether clouds render
- Sky rendering behavior

Would you like me to update the noise_settings files now to use custom default blocks?
