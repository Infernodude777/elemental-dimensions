# Elemental Dimensions - Multi-Version Mod

**Version:** 1.0.0  
**Repository:** https://github.com/Infernodude777/elemental-dimensions  
**Last Updated:** November 5, 2025

---

## 📁 PROJECT STRUCTURE

This repository contains **two independent versions**:

```
elemental_dimensions/
├── 1.21.1/          ← Minecraft 1.21.1 (FULLY WORKING) ✅
├── 1.21.10/         ← Minecraft 1.21.10 (PARTIAL - 60% migrated) ⚠️
├── MC_1.21.10_STATUS.md
├── QUICK_START.md
└── README.md
```

**Each version folder is a complete standalone Gradle project** with its own `src/`, `build/`, `gradle/`, etc.

---

## 🚀 QUICK START

### MC 1.21.1 (Recommended) ✅

```powershell
cd 1.21.1
.\gradlew build
# JAR: 1.21.1/build/libs/elemental-dimensions-1.0.0.jar
```

### MC 1.21.10 (Experimental) ⚠️

```powershell
cd 1.21.10
.\gradlew build
# See MC_1.21.10_STATUS.md for migration details
```

---

## Overview

Elemental Dimensions is a comprehensive Fabric mod that adds five fully unique elemental dimensions to Minecraft, each with custom blocks, mobs, bosses, structures, and progression systems.

## Features

### Five Unique Dimensions

1. **Firelands** - Volcanic realm with crimson skies, ember plains, and molten glass fields
2. **Aquatica** - Bioluminescent twilight ocean world with pressurized caverns
3. **Terra Depths** - Lush subterranean jungle with crystalline veins and living roots
4. **Skyreach Peaks** - High floating islands with dynamic windflows and petal fields
5. **Celestial Dimension** - Endgame nebula realm with floating star-islands and cosmic crystals

### Content

- **50+ Custom Blocks** across all dimensions
- **20+ Unique Mobs** with custom AI and GeckoLib animations
- **5 Epic Boss Fights** with multi-phase combat mechanics
- **Legendary Weapons** including Infernal Edge, Abyssal Spear, Gaia Cleaver, and Tempest Bow
- **Celestial Endgame Gear** including Starforged Blade, Voidheart Bow, and Celestial Armor
- **Progressive Unlocking System** using Elemental Essences and Rune Altars
- **Dimensional Compass** for navigation between realms
- **Custom Music Tracks** for each dimension (15 total)
- **50+ Particle Effects** for ambient and combat visuals
- **30+ Structures** including fortresses, temples, dungeons, and boss arenas

### Commands

Admin/debug commands for testing and configuration:

- `/edtp <dimension>` - Teleport to a dimension
- `/edspawn <mob>` - Spawn a mob
- `/edgive <item>` - Give an item
- `/edreload` - Reload configuration
- `/eddebug <on/off>` - Toggle debug mode
- `/edhelp` - Show help
- `/edlistblocks <dimension>` - List blocks for dimension
- `/edlistmobs <dimension>` - List mobs for dimension

### Dependencies

Required:
- Fabric API
- GeckoLib 4.5.8+
- Cloth Config 15.0+

## Installation

1. Install Fabric Loader for Minecraft 1.21.1
2. Download and install Fabric API, GeckoLib, and Cloth Config
3. Place the Elemental Dimensions JAR in your mods folder
4. Launch Minecraft with Fabric profile

## Progression

1. Craft the **Dimensional Compass** to begin your journey
2. Collect **Elemental Essences** from each dimension's mobs and bosses
3. Craft **Rune Altars** to unlock new dimensions:
   - Fire Altar: Obsidian + Fire Essence + Lava Crystal
   - Water Altar: Prismarine + Water Essence + Pearlstone
   - Earth Altar: Mossy Cobblestone + Earth Essence + Gaia Stone
   - Air Altar: Quartz + Air Essence + Wind Crystal
   - Celestial Altar: All Essences + Void Crystal Ore + Nether Star
4. Defeat each realm's boss to progress
5. Challenge the **Elemental Primarch** in the Celestial Dimension for ultimate rewards

## Configuration

Edit `config/elementaldimensions.json` to customize:
- Performance settings (particle limits, tick optimizations)
- Multiplayer options (boss scaling, PvP toggles)
- Audio volumes (music and ambient sounds)
- Debug features

## Performance

Optimized for multiplayer and client performance:
- Armor checks throttled to every 100 ticks
- Particle throttles (max 10 per source per second)
- Asynchronous asset loading
- Boss health scales with player count
- Compatible with Lunar Client 1.21.10

## Credits

Created for Minecraft 1.21.1 using Fabric  
Powered by GeckoLib for entity animations  
Configuration system by Cloth Config

## License

MIT License - See LICENSE file for details

## Support

For issues, suggestions, or support, please visit our GitHub repository.

---

**Note:** This mod is designed to be Lunar Client compatible and uses only verified dependencies (Fabric API, GeckoLib, Cloth Config) for maximum compatibility.
