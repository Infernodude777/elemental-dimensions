# Elemental Dimensions

A comprehensive Minecraft Fabric mod adding six unique elemental dimensions with custom entities, bosses, blocks, and progression systems.

[![Discord](https://img.shields.io/discord/YOUR_DISCORD_ID?style=for-the-badge&logo=discord)](https://discord.gg/YOUR_DISCORD)
[![License](https://img.shields.io/github/license/Infernodude777/elemental-dimensions?style=for-the-badge)](LICENSE)

## 🎮 Features

### Six Unique Dimensions
- **🔥 Firelands** - A scorching realm of lava and eternal flame
- **🌊 Aquatica** - Deep underwater world with coral structures
- **⛰️ Terra Depths** - Underground caverns filled with crystals
- **☁️ Skyreach Peaks** - Floating islands high in the clouds
- **✨ Celestial** - Starry cosmic realm with nebulas
- **🌌 Void Realm** - Dark mysterious dimension

### 32 Animated Entities
All entities use **GeckoLib** for smooth, professional animations:
- 26 unique mobs across all dimensions
- 6 epic boss fights
- Custom AI behaviors
- Unique drops and loot tables

### Full Progression System
- 13 advancement achievements
- Dimensional portal system
- Legendary weapons and tools
- Dimension-specific resources

## 📦 Download

### Multiple Minecraft Versions Available

| Minecraft Version | GeckoLib Version | Status | Download |
|-------------------|------------------|--------|----------|
| **1.21.1** | GeckoLib 4.8.2 | ✅ Stable | [Download](../../releases/tag/1.21.1-latest) |
| **1.21.10** | GeckoLib 5.3-alpha | ⚠️ Alpha | [Download](../../releases/tag/1.21.10-latest) |

## 🔧 Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for your Minecraft version
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download [GeckoLib](https://modrinth.com/mod/geckolib) (version matching your MC version)
4. Download [Cloth Config](https://modrinth.com/mod/cloth-config)
5. Download **Elemental Dimensions** from the releases page
6. Place all JARs in your `.minecraft/mods` folder

## 🛠️ For Developers

### Version Folders
- **[`1.21.1/`](./1.21.1/)** - Stable build using GeckoLib 4.8.2
- **[`1.21.10/`](./1.21.10/)** - Latest build using GeckoLib 5.3-alpha-3

Each folder contains a complete, buildable project for its respective Minecraft version.

### Building from Source

#### 1.21.1 Version
```bash
cd 1.21.1
.\gradlew build
```

#### 1.21.10 Version
```bash
cd 1.21.10
.\gradlew build
```

### Key Technical Differences

| Feature | 1.21.1 (GeckoLib 4.x) | 1.21.10 (GeckoLib 5.x) |
|---------|----------------------|------------------------|
| Entity Interface | `GeoAnimatable` | `GeoEntity` |
| Renderer Type | `GeoEntityRenderer<T>` | `GeoEntityRenderer<T, R extends EntityRenderState & GeoRenderState>` |
| Animation Controller | Simple callback | `AnimationStateHandler` with state checking |
| Package Structure | Same for both | Same for both |

See version-specific READMEs for detailed API differences.

## 📋 Complete Feature List

### Entities by Dimension

**Firelands (5)**
- Flame Sprite
- Ember Serpent
- Pyre Howler
- Magma Brute
- Cinder Elemental
- **Boss:** Fire Titan

**Aquatica (5)**
- Bubble Wisp
- Abyss Crab
- Tide Siren
- Pressure Ray
- Coral Guardian
- **Boss:** Abyss Leviathan

**Terra Depths (5)**
- Stone Golem
- Root Spider
- Moss Beast
- Crystal Mite
- Cave Lurker
- **Boss:** Stone Colossus

**Skyreach Peaks (5)**
- Wind Hawk
- Sky Wisp
- Cloud Sentinel
- Thunder Hawk
- Petal Sprite
- **Boss:** Wind Seraph

**Celestial (4)**
- Celestial Enderman
- Starlight Phantom
- Void Shulker
- Nebula Wraith
- **Boss:** Elemental Primarch

**Void Realm (3)**
- Shadow Wraith
- Void Stalker
- **Boss:** Void Lord

### Blocks & Items
- 30+ unique blocks per dimension
- 40+ custom items
- Legendary weapons (Infernal Edge, Abyssal Spear, etc.)
- Dimension-specific materials
- Portal frame blocks

### Structures
- Coral Palace (Aquatica)
- Sky temples (Skyreach)
- Void altars (Void Realm)
- Natural formations per dimension

## 🎯 Progression Guide

1. **Craft Dimensional Compass** - Navigate and identify dimensions
2. **Build dimension portals** - Each requires specific blocks
3. **Explore dimensions** - Gather resources and fight mobs
4. **Defeat dimension bosses** - Unlock crafting recipes
5. **Craft legendary weapons** - Use boss drops
6. **Unlock final dimension** - Void Realm (requires all 5 elemental hearts)

## 🔊 Audio & Textures

### Textures
- 39 custom PNG textures (16x16)
- Procedurally generated with noise, gradients, and effects
- Entity skins for custom mobs

### Sounds
- 60+ registered sound events
- Music tracks for each dimension
- Ambient sounds
- Mob sounds (ambient, hurt, death)
- Boss battle music

See [`SOUND_ASSET_GUIDE.md`](SOUND_ASSET_GUIDE.md) for asset acquisition instructions.

## 📚 Documentation

- **[CHANGELOG.md](CHANGELOG.md)** - Version history and updates
- **[GECKOLIB_MIGRATION.md](GECKOLIB_MIGRATION.md)** - GeckoLib 4.x vs 5.x comparison
- **[GECKOLIB_COMPLETE.md](GECKOLIB_COMPLETE.md)** - Complete GeckoLib implementation details
- **[SOUND_ASSET_GUIDE.md](SOUND_ASSET_GUIDE.md)** - Audio asset acquisition guide
- **[BUILD.md](BUILD.md)** - Build and compilation instructions

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

### Development Setup
1. Clone the repository
2. Choose your version folder (`1.21.1` or `1.21.10`)
3. Open in your IDE (IntelliJ IDEA recommended)
4. Run `.\gradlew genSources` to generate Minecraft sources
5. Make your changes
6. Test with `.\gradlew runClient`

## 📜 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 🙏 Credits

### Libraries & Dependencies
- **[GeckoLib](https://github.com/bernie-g/geckolib)** - Entity animation library
- **[Fabric](https://fabricmc.net/)** - Modding toolchain
- **[Cloth Config](https://github.com/shedaniel/cloth-config)** - Configuration GUI

### Special Thanks
- GeckoLib Discord community for help with the 5.x migration
- Fabric community for documentation and support
- [BisectHosting](https://bisecthosting.com/gecko) - Server hosting partner

## 🐛 Bug Reports

Found a bug? Please open an issue on the [Issues](https://github.com/Infernodude777/elemental-dimensions/issues) page with:
- Minecraft version
- Mod version
- Steps to reproduce
- Crash report (if applicable)

## 💬 Discord

Join our Discord server for support, updates, and community: [Discord Invite](https://discord.gg/YOUR_DISCORD)

## 🎮 Compatible Clients

✅ Vanilla Minecraft  
✅ Lunar Client  
✅ Badlion Client  
✅ Other Fabric-compatible clients

---

**Made with ❤️ for the Minecraft community**

*Elemental Dimensions* - Explore new worlds, conquer epic bosses, and master the elements!
