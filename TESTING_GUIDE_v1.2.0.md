# v1.2.0-dev Testing Guide

## 🎯 Quick Testing Checklist

This guide helps you verify all v1.2.0 features work correctly.

---

## 🌊 Fluid System Testing

### 1. Bucket Functionality
**Steps:**
1. Get a bucket: `/give @s minecraft:bucket`
2. Get fluid buckets: `/give @s elementaldimensions:lucid_water_bucket`
3. Test fill: Right-click on fluid source → Bucket fills
4. Test empty: Right-click on ground → Fluid places
5. Test pickup: Right-click placed fluid → Bucket fills again

**Expected Results:**
- ✅ Buckets fill from sources
- ✅ Buckets place fluid blocks
- ✅ Fluid sources can be picked up
- ✅ Bucket item texture shows correctly

### 2. Fluid Physics
**Steps:**
1. Place fluid source at height
2. Observe flowing behavior
3. Test horizontal spread
4. Create 2×2 source pool
5. Test infinite source generation

**Expected Results:**
- ✅ Fluid flows downward
- ✅ Spreads 3-4 blocks horizontally
- ✅ 2×2 sources create infinite water
- ✅ Flowing textures animate correctly

### 3. Swimming & Underwater
**Steps:**
1. Dive into fluid
2. Test swimming movement
3. Check drowning mechanics
4. Test underwater visibility

**Expected Results:**
- ✅ Player can swim
- ✅ Oxygen bubbles appear
- ✅ Drowning damage works
- ✅ Underwater view looks correct

### 4. Crafting Recipes
**Test Each Recipe:**
```
/give @s minecraft:bucket
/give @s elementaldimensions:dreamstone 4
```
Then craft in 3×3 grid:
```
  D
D B D
  D
```

**All Recipes:**
- [ ] Lucid Water Bucket (dreamstone)
- [ ] Radiant Fluid Bucket (luminite)
- [ ] Mycelial Ooze Bucket (mycelium_block)
- [ ] Void Essence Bucket (void_crystal)
- [ ] Ink Fluid Bucket (ancient_glyph)

### 5. Natural Generation
**Test Each Dimension:**
```bash
# Teleport to dimension
/execute in elementaldimensions:dreaming_depths run tp @s ~ 100 ~

# Fly around and look for lakes/pools
/gamemode spectator
```

**Find These Features:**
- [ ] Lucid Water Lakes (Dreaming Depths)
- [ ] Radiant Fluid Lakes (Celestine Expanse)
- [ ] Mycelial Ooze Pools (Fungal Dominion)
- [ ] Void Essence Pools (Astral Frontier)
- [ ] Ink Fluid Pools (Forgotten Archive)

---

## 🏆 Achievement System Testing

### 1. Root Advancement
**Test:**
```bash
/advancement grant @s only elementaldimensions:root
```
**Expected:**
- ✅ Toast notification appears
- ✅ Shows in Advancements UI
- ✅ Correct icon and description

### 2. Dimension Advancements
**Test Each:**
```bash
# Inferno Realm
/execute in elementaldimensions:inferno_realm run tp @s ~ 100 ~

# Aquatic Realm
/execute in elementaldimensions:aquatic_realm run tp @s ~ 100 ~

# ... repeat for all 11 dimensions
```

**Verify:**
- [ ] Enter Inferno Realm
- [ ] Enter Aquatic Realm
- [ ] Enter Skybound Realm
- [ ] Enter Terran Realm
- [ ] Enter Celestial Realm
- [ ] Enter Void Realm
- [ ] Enter Dreaming Depths
- [ ] Enter Celestine Expanse
- [ ] Enter Fungal Dominion
- [ ] Enter Forgotten Archive
- [ ] Enter Astral Frontier
- [ ] Enter Gloomy Caverns

**Expected:**
- ✅ Auto-unlocks on dimension change
- ✅ Toast notification shows
- ✅ Parent-child progression works

### 3. Boss Advancements
**Test Each Boss:**
```bash
# Summon and kill boss
/summon elementaldimensions:pyros ~ ~ ~
/kill @e[type=elementaldimensions:pyros]
```

**Boss List:**
- [ ] Defeat Pyros (Inferno)
- [ ] Defeat Leviathan (Aquatic)
- [ ] Defeat Goliath (Terran)
- [ ] Defeat Zephyros (Skybound)
- [ ] Defeat Primarch (Celestial)
- [ ] Defeat The Dreamer (Dreaming Depths)
- [ ] Defeat Radiant Monarch (Celestine)
- [ ] Defeat The Overmind (Fungal)
- [ ] Defeat The Librarian (Archive)
- [ ] Defeat Void Archon (Astral)
- [ ] Defeat Echo King (Gloomy)

**Expected:**
- ✅ Unlocks on boss death
- ✅ Shows 100 XP reward
- ✅ Challenge frame (fancy border)
- ✅ Correct parent advancement

### 4. Gameplay Advancements
**Test:**

**A. Craft First Tool**
```bash
# Give materials and craft reverie pickaxe
/give @s elementaldimensions:dreamstone 3
/give @s minecraft:stick 2
# Craft in crafting table
```

**B. Full Armor Set**
```bash
# Give any full armor set
/give @s elementaldimensions:reverie_helmet
/give @s elementaldimensions:reverie_chestplate
/give @s elementaldimensions:reverie_leggings
/give @s elementaldimensions:reverie_boots
# Equip all pieces
```

**C. Obtain Essence**
```bash
/give @s elementaldimensions:fire_essence
```

**D. Obtain All Essences** (Challenge!)
```bash
/give @s elementaldimensions:fire_essence
/give @s elementaldimensions:water_essence
/give @s elementaldimensions:earth_essence
/give @s elementaldimensions:air_essence
```

**E. Obtain Heart**
```bash
/give @s elementaldimensions:inferno_heart
```

**Verify All:**
- [ ] Craft First Tool (task frame)
- [ ] Full Armor Set (goal frame)
- [ ] Obtain Essence (task frame)
- [ ] Obtain All Essences (challenge frame, 50 XP)
- [ ] Obtain Heart (goal frame)

---

## 🗺️ Worldgen Testing

### 1. Feature Placement
**Find Each Feature:**
```bash
# Enable spectator mode and fly around
/gamemode spectator

# Dimensions to check:
/execute in elementaldimensions:dreaming_depths run tp @s 0 100 0
/execute in elementaldimensions:celestine_expanse run tp @s 0 100 0
/execute in elementaldimensions:fungal_dominion run tp @s 0 100 0
/execute in elementaldimensions:astral_frontier run tp @s 0 100 0
/execute in elementaldimensions:forgotten_archive run tp @s 0 100 0
```

**What to Look For:**
- [ ] Lakes spawn on world surface
- [ ] Correct fluid type in correct dimension
- [ ] Barrier blocks around edges (stone)
- [ ] Reasonable spawn rate (not too common/rare)

### 2. Chunk Generation
**Test:**
1. Generate new world
2. Teleport to each dimension
3. Fly around and generate chunks
4. Watch console for errors

**Expected:**
- ✅ No console errors
- ✅ Chunks generate smoothly
- ✅ No crash during generation
- ✅ Features placed correctly

---

## 🎨 Visual Testing

### 1. Textures
**Check Each Fluid:**
- [ ] Still Lucid Water texture loads
- [ ] Flowing Lucid Water animates
- [ ] Still Radiant Fluid texture loads
- [ ] Flowing Radiant Fluid animates
- [ ] Still Mycelial Ooze texture loads
- [ ] Flowing Mycelial Ooze animates
- [ ] Still Void Essence texture loads
- [ ] Flowing Void Essence animates
- [ ] Still Ink Fluid texture loads
- [ ] Flowing Ink Fluid animates

### 2. Bucket Items
**Check Inventory Icons:**
- [ ] Lucid Water Bucket icon
- [ ] Radiant Fluid Bucket icon
- [ ] Mycelial Ooze Bucket icon
- [ ] Void Essence Bucket icon
- [ ] Ink Fluid Bucket icon

### 3. UI Elements
**Test:**
- [ ] Advancement icons show correctly
- [ ] Toast notifications look good
- [ ] Language translations work
- [ ] Recipe book shows bucket recipes

---

## 🔊 Audio Testing (When Implemented)

### Music System
**Test (when audio files added):**
- [ ] Dimension music plays on entry
- [ ] Music loops smoothly
- [ ] Volume levels appropriate
- [ ] Music stops when leaving dimension

### Sound Effects
**Test (when audio files added):**
- [ ] Portal ambient sounds
- [ ] Boss roars/attacks
- [ ] Mob ambient sounds
- [ ] Item use sounds

---

## 🐛 Bug Testing

### Common Issues to Check

**1. Console Errors**
```bash
# Check console for:
- Missing texture warnings
- Failed registration errors
- JSON parsing errors
- ClassNotFoundException
```

**2. Performance**
```bash
# Monitor FPS with F3:
- Normal gameplay: 60+ FPS
- With many fluids: 50+ FPS
- Dimension switching: No lag spikes
- Chunk loading: Smooth
```

**3. Multiplayer (If Applicable)**
```bash
# Test on server:
- Fluid sync between clients
- Advancement sync
- Recipe book sync
- No desync issues
```

**4. Compatibility**
```bash
# Test with other mods:
- No ID conflicts
- No recipe conflicts
- No crash on startup
- Features work correctly
```

---

## 📊 Test Results Template

### Test Session Report
```
Date: ___________
Tester: ___________
Version: 1.2.0-dev
Build: ___________

=== FLUID SYSTEM ===
Buckets:         [ ] Pass  [ ] Fail  Notes: ____________
Physics:         [ ] Pass  [ ] Fail  Notes: ____________
Swimming:        [ ] Pass  [ ] Fail  Notes: ____________
Crafting:        [ ] Pass  [ ] Fail  Notes: ____________
Worldgen:        [ ] Pass  [ ] Fail  Notes: ____________

=== ACHIEVEMENTS ===
Root:            [ ] Pass  [ ] Fail  Notes: ____________
Dimensions:      [ ] Pass  [ ] Fail  Notes: ____________
Bosses:          [ ] Pass  [ ] Fail  Notes: ____________
Gameplay:        [ ] Pass  [ ] Fail  Notes: ____________

=== VISUAL ===
Textures:        [ ] Pass  [ ] Fail  Notes: ____________
Icons:           [ ] Pass  [ ] Fail  Notes: ____________
UI:              [ ] Pass  [ ] Fail  Notes: ____________

=== PERFORMANCE ===
FPS:             [ ] Pass  [ ] Fail  Notes: ____________
Console Errors:  [ ] Pass  [ ] Fail  Notes: ____________
Multiplayer:     [ ] Pass  [ ] Fail  Notes: ____________

=== OVERALL ===
Ready for Release:  [ ] Yes  [ ] No
Critical Bugs:      [ ] None  [ ] List: ____________
Minor Issues:       [ ] None  [ ] List: ____________
```

---

## 🚀 Performance Benchmarks

### Target Metrics
- **Startup Time**: < 30 seconds
- **FPS (Normal)**: 60+ FPS
- **FPS (Stress)**: 45+ FPS
- **Chunk Load**: < 1 second per chunk
- **Dimension Switch**: < 2 seconds

### Stress Test Scenarios

**Scenario 1: Fluid Spam**
1. Place 100 fluid sources
2. Let them all flow
3. Monitor FPS and RAM

**Scenario 2: Dimension Tour**
1. Visit all 11 dimensions rapidly
2. Check for memory leaks
3. Monitor console errors

**Scenario 3: Boss Rush**
1. Summon all 11 bosses at once
2. Check entity rendering
3. Monitor performance

---

## ✅ Final Checklist

Before releasing v1.2.0-final:

### Core Features
- [ ] All 5 fluids work correctly
- [ ] All 29 advancements unlock properly
- [ ] All 5 bucket recipes craft correctly
- [ ] Natural generation works in all dimensions
- [ ] No console errors during normal play

### Polish
- [ ] All textures load correctly
- [ ] All translations are present
- [ ] Recipe book shows all recipes
- [ ] Advancement descriptions are clear

### Testing
- [ ] Single-player tested (all features)
- [ ] Multiplayer tested (if applicable)
- [ ] Performance benchmarks pass
- [ ] No critical bugs found

### Documentation
- [ ] Changelog complete
- [ ] README updated
- [ ] Testing guide finalized
- [ ] Known issues documented

### Release Preparation
- [ ] Version number updated
- [ ] Build successful with no warnings
- [ ] JAR file tested in clean environment
- [ ] GitHub repository updated

---

**For Issues:** Report bugs on GitHub with console logs and reproduction steps.

**For Questions:** Check documentation files or ask in Discord/Issues.

---

*Happy Testing! 🎮*
