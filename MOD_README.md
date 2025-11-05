# Elemental Dimensions - Minecraft Fabric Mod

A comprehensive dimension mod for Minecraft 1.21.10 featuring 5 unique elemental realms, each guarded by powerful bosses.

## 🌟 Features

### Five Elemental Dimensions
1. **Inferno Realm** - A fiery dimension of eternal flames
2. **Aquatic Realm** - An underwater world of tidal forces
3. **Terran Realm** - A rocky domain of earth and stone
4. **Skybound Realm** - A floating realm among the clouds
5. **Celestial Realm** - The ultimate realm combining all elements

### Five Epic Boss Fights

#### 🔥 Pyros the Eternal Flame (Inferno Realm)
- **Health:** 500 HP
- **Element:** Fire
- **Abilities:**
  - Phase 1: Fire Wave - 360° ring of fire
  - Phase 2: Meteor Shower - Raining fireballs  
  - Phase 3: Inferno Rage - Massive explosions
- **Drops:** Inferno Heart, Fire Essence (5-10)
- **XP:** 500

#### 🌊 Leviathan the Tidal Terror (Aquatic Realm)
- **Health:** 500 HP
- **Element:** Water
- **Abilities:**
  - Phase 1: Tidal Wave - Knockback water blasts
  - Phase 2: Ice Spikes - Freezing damage & slowness
  - Phase 3: Tsunami - Drowning attack
- **Drops:** Aquatic Heart, Water Essence (5-10)
- **XP:** 500

#### 🌍 Goliath the Stone Titan (Terran Realm)
- **Health:** 600 HP (Highest armor)
- **Element:** Earth
- **Abilities:**
  - Phase 1: Ground Slam - Shockwave damage
  - Phase 2: Earthquake - Area shake with nausea
  - Phase 3: Earth Fissures - Lava cracks
- **Drops:** Terran Heart, Earth Essence (5-10)
- **XP:** 500

#### 💨 Zephyros the Storm Lord (Skybound Realm)
- **Health:** 450 HP (Fastest)
- **Element:** Air
- **Abilities:**
  - Phase 1: Wind Gust - Strong knockback
  - Phase 2: Lightning Strike - Electric damage
  - Phase 3: Sky Rage - Tornado with levitation
- **Drops:** Skybound Heart, Air Essence (5-10)
- **XP:** 500

#### ✨ Primarch Astraeus (Celestial Realm)
- **Health:** 1000 HP (Ultimate Boss)
- **Element:** ALL FOUR COMBINED
- **Abilities:**
  - Phase 1 (100-75%): FIRE attacks (Pyros abilities)
  - Phase 2 (75-50%): WATER attacks (Leviathan abilities)
  - Phase 3 (50-25%): EARTH attacks (Goliath abilities)
  - Phase 4 (25-0%): AIR + ALL ELEMENTS (Ultimate storm)
- **Drops:** Elemental Heart, ALL Essences (10-20 each)
- **XP:** 2000

## 🎮 Commands

### `/guide`
Gives you the Elemental Dimensions Guide book containing:
- Boss information and stats
- Phase abilities and strategies
- Progression tips
- Survival recommendations

## 📦 Installation

1. Download the mod JAR from the `build/libs` folder
2. Install Fabric Loader for Minecraft 1.21.10
3. Install Fabric API 0.134.1+1.21.10 or later
4. Place the mod JAR in your `.minecraft/mods` folder
5. Launch Minecraft with the Fabric profile

## 🛠️ Development Setup

### Prerequisites
- Java 21
- Gradle (included via wrapper)

### Building from Source

```powershell
# Windows PowerShell
cd "path\to\Elemental Dimensions"
.\gradlew build
```

```bash
# Linux/Mac
cd "path/to/Elemental Dimensions"
./gradlew build
```

The compiled mod will be in `build/libs/elemental-dimensions-1.0.0.jar`

## 📋 Progression Guide

1. **Gather Resources**
   - Mine Celestial Ore to get Celestial Crystals
   - Craft Celestial Portal Frames
   - Create a Starlight Igniter

2. **Conquer the Elemental Realms**
   - Defeat Pyros in the Inferno Realm
   - Defeat Leviathan in the Aquatic Realm
   - Defeat Goliath in the Terran Realm
   - Defeat Zephyros in the Skybound Realm

3. **Collect Boss Hearts**
   - Each boss drops their unique heart
   - Collect all 4 elemental hearts

4. **Challenge the Primarch**
   - Use the hearts to access the Celestial Realm
   - Face Primarch Astraeus in the ultimate battle

## 🎯 Boss Mechanics

### Phase System
All bosses have a 3-phase system (Primarch has 4):
- Bosses become more aggressive as health decreases
- Each phase unlock unlocks new abilities
- Phase transitions heal the boss slightly
- Boss bars track health and current phase

### Combat Tips
- **Fire Resistance** for Pyros (lava/fire attacks)
- **Water Breathing** for Leviathan (drowning attack)
- **High Mobility** vs Goliath (earthquake/knockback)
- **Lightning Rod** placement vs Zephyros (lightning strikes)
- **ALL Protections** for Primarch (uses everything!)

## 📁 Project Structure

```
Elemental Dimensions/
├── src/main/
│   ├── java/com/elementaldev/elementaldimensions/
│   │   ├── ElementalDimensions.java (Main mod class)
│   │   ├── command/
│   │   │   └── GuideCommand.java (/guide command)
│   │   ├── config/
│   │   │   └── ModConfig.java
│   │   ├── entity/boss/
│   │   │   ├── PyrosEntity.java
│   │   │   ├── LeviathanEntity.java
│   │   │   ├── GoliathEntity.java
│   │   │   ├── ZephyrosEntity.java
│   │   │   └── PrimarchEntity.java
│   │   ├── item/ (essences, tools, armor)
│   │   ├── registry/
│   │   │   ├── ModBlocks.java
│   │   │   ├── ModItems.java
│   │   │   └── ModEntities.java
│   │   └── world/dimension/
│   └── resources/
│       ├── fabric.mod.json
│       ├── assets/elementaldimensions/
│       └── data/elementaldimensions/
├── build.gradle
├── gradle.properties
└── README.md (this file)
```

## 🔧 Technical Details

- **Minecraft Version:** 1.21.10
- **Mod Loader:** Fabric
- **Fabric API:** 0.134.1+1.21.10
- **Java Version:** 21
- **Mod ID:** elementaldimensions

## ✅ No Hardcoded Paths
This mod uses only relative paths and is fully portable. It will work on any system without modification.

## 🐛 Known Issues

- NBT serialization for boss phase persistence is disabled (bosses reset phase on chunk reload)
- This is intentional as boss fights typically happen in one session

## 📜 License

This mod is provided as-is for educational and entertainment purposes.

## 🙏 Credits

Created for the Elemental Dimensions modding project.
All boss entities feature custom AI, particle effects, and multi-phase combat systems.

---

**May the elements guide your journey!**
