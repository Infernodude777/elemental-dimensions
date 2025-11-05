# Asset Acquisition Guide for Elemental Dimensions

## Overview
This guide will help you find, download, and implement professional textures, sound effects, and music for the Elemental Dimensions mod.

## 🎨 TEXTURE RESOURCES

### Recommended Sites
1. **OpenGameArt.org** - CC0, CC-BY, and various free licenses
2. **itch.io** - Many free game asset packs
3. **Pixabay** - CC0 textures
4. **TextureCan** - Free seamless textures

### What We Need

#### Block Textures (16x16 PNG)
- **Void Dimension Blocks:**
  - `void_stone.png` - Dark stone with subtle purple veins
  - `void_crystal_ore.png` - Dark ore with glowing purple crystals
  - `void_glass.png` - Semi-transparent dark glass with purple tint
  - `void_pillar.png` - Ornate pillar with void patterns
  - `void_crystal_block.png` - Solid crystalline block
  - `void_portal_frame.png` - Ancient frame with runes

#### Item Textures (16x16 PNG)
- **Essences:**
  - `fire_essence.png` - Glowing red/orange crystal
  - `water_essence.png` - Blue glowing droplet
  - `earth_essence.png` - Green/brown gem
  - `air_essence.png` - White/cyan wisp
  - `celestial_essence.png` - Golden/yellow star
  - `void_essence.png` - Purple/black crystal

- **Tools/Weapons:**
  - `void_sword.png` - Dark blade with purple glow
  - `dimensional_compass.png` - Ornate compass with colors

#### Entity Textures (64x64 or higher PNG)
- **Void Dimension Mobs:**
  - `void_wraith.png` - Ghostly purple creature
  - `void_stalker.png` - Dark shadowy beast
  - `void_guardian.png` - Large armored boss

- **Other Dimension Mobs:**
  - `cinder_elemental.png` - Fire creature
  - `coral_guardian.png` - Water creature
  - `cave_lurker.png` - Earth creature
  - `thunder_hawk.png` - Air creature
  - `nebula_wraith.png` - Celestial creature

### Search Terms for Textures
- "minecraft stone texture"
- "fantasy crystal ore"
- "dark glass texture seamless"
- "purple void texture"
- "pixel art essence icons"
- "rpg item icons 16x16"

## 🎵 MUSIC & AUDIO RESOURCES

### Recommended Sites
1. **Freesound.org** - CC0 and CC-BY sounds (best for SFX)
2. **Incompetech** - Royalty-free music by Kevin MacLeod
3. **OpenGameArt.org** - Game music and SFX
4. **ZapSplat** - Free SFX (registration required)
5. **Pixabay** - Free music and sounds

### What We Need

#### Background Music (18 tracks needed, OGG format)

**Firelands (4 tracks):**
- Search terms: "intense fire music", "volcanic ambient", "molten soundtrack"
- Mood: Intense, hot, aggressive
- Suggested on Freesound: "deep bass rumble", "fire crackling loop"

**Aquatica (4 tracks):**
- Search terms: "underwater ambience", "ocean music", "calm water"
- Mood: Serene, flowing, peaceful
- Suggested on Freesound: "underwater ambience", "water flowing"

**Terra Depths (4 tracks):**
- Search terms: "cave ambience", "deep earth rumble", "cavern music"
- Mood: Deep, echoing, mysterious
- Suggested on Freesound: "cave drips", "stone rumble"

**Skyreach (3 tracks):**
- Search terms: "wind ambient", "sky music", "ethereal winds"
- Mood: Light, airy, ethereal
- Suggested on Freesound: "wind howling", "air whoosh"

**Celestial (2 tracks):**
- Search terms: "space ambient", "cosmic music", "heavenly soundtrack"
- Mood: Vast, cosmic, mysterious
- Suggested on Freesound: "space ambience", "cosmic drone"

**Void Realm (1 track):**
- Search terms: "dark ambient", "void music", "ominous soundtrack"
- Mood: Dark, ominous, unsettling
- Suggested on Freesound: "dark ambience deep low bass humming"

#### Sound Effects (OGG format)

**Ambient Sounds:**
- `firelands_ambient.ogg` - Crackling, rumbling
- `aquatica_ambient.ogg` - Water flowing, bubbles
- `void_ambient.ogg` - Deep hum, whispers

**Boss Sounds:**
- `void_guardian_roar.ogg` - Deep, echoing roar

**Mob Sounds (per entity):**
- Idle, hurt, death sounds for each of the 8 mobs

## 📥 DOWNLOAD INSTRUCTIONS

### For Freesound.org
1. Search for the sound using search terms above
2. Filter by "Creative Commons 0" or "CC-BY" licenses
3. Click on a sound you like
4. Click "Download" button
5. Save to a temporary folder

### For OpenGameArt.org
1. Browse or search for assets
2. Check the license (CC0, CC-BY, CC-BY-SA recommended)
3. Download the pack
4. Extract files

### For Other Sites
1. Follow site-specific download instructions
2. Always check the license terms
3. Note attribution requirements (if any)

## 🔧 IMPLEMENTATION GUIDE

### Converting Audio to OGG

You'll need to convert audio files to OGG format. Use **Audacity** (free):

1. Download and install Audacity
2. Open your audio file (MP3, WAV, etc.)
3. File → Export → Export as OGG Vorbis
4. Quality: 5-7 (good balance of quality/size)
5. For music: Ensure it's stereo, 44100 Hz
6. For SFX: Can be mono, 22050 Hz or 44100 Hz

### File Structure

Place downloaded assets here:

```
src/main/resources/assets/elementaldimensions/
├── textures/
│   ├── block/
│   │   ├── void_stone.png
│   │   ├── void_crystal_ore.png
│   │   ├── void_glass.png
│   │   ├── void_pillar.png
│   │   ├── void_crystal_block.png
│   │   └── void_portal_frame.png
│   ├── item/
│   │   ├── fire_essence.png
│   │   ├── water_essence.png
│   │   ├── earth_essence.png
│   │   ├── air_essence.png
│   │   ├── celestial_essence.png
│   │   ├── void_essence.png
│   │   ├── void_sword.png
│   │   └── dimensional_compass.png
│   └── entity/
│       ├── void_wraith.png
│       ├── void_stalker.png
│       ├── void_guardian.png
│       ├── cinder_elemental.png
│       ├── coral_guardian.png
│       ├── cave_lurker.png
│       ├── thunder_hawk.png
│       └── nebula_wraith.png
└── sounds/
    ├── music/
    │   ├── firelands/
    │   │   ├── infernal_echoes.ogg
    │   │   ├── molten_fury.ogg
    │   │   ├── blazing_heart.ogg
    │   │   └── flames_of_chaos.ogg
    │   ├── aquatica/
    │   │   ├── echoing_currents.ogg
    │   │   ├── abyssal_waves.ogg
    │   │   ├── deep_tide.ogg
    │   │   └── ocean_whispers.ogg
    │   ├── terra/
    │   │   ├── stone_heartbeat.ogg
    │   │   ├── earthen_depths.ogg
    │   │   ├── cavern_resonance.ogg
    │   │   └── ancient_roots.ogg
    │   ├── skyreach/
    │   │   ├── winds_of_eternity.ogg
    │   │   ├── sky_symphony.ogg
    │   │   └── cloud_dancer.ogg
    │   ├── celestial/
    │   │   ├── stellar_drift.ogg
    │   │   └── cosmic_horizon.ogg
    │   └── void/
    │       └── void_silence.ogg
    └── ambient/
        ├── firelands_ambient.ogg
        ├── aquatica_ambient.ogg
        └── void_ambient.ogg
```

## 🔍 SPECIFIC FREESOUND.ORG SEARCHES

Here are direct searches you can do on Freesound:

### Fire Sounds
- "fire crackling loop" → Background for Firelands music
- "lava bubbling" → Ambient sound
- "fire whoosh" → Mob attack sounds

### Water Sounds
- "underwater ambience" → Background for Aquatica
- "water flowing" → Ambient sounds
- "bubble underwater" → SFX

### Earth/Cave Sounds
- "cave ambience" → Terra Depths background
- "stone slide" → Block breaking sounds
- "rock impact" → Collision sounds

### Air/Wind Sounds
- "wind howling" → Skyreach ambient
- "air whoosh" → Flying mob sounds
- "wind chimes" → Musical elements

### Space/Celestial Sounds
- "space ambience" → Celestial dimension
- "cosmic drone" → Background music
- "ethereal pad" → Atmospheric sounds

### Dark/Void Sounds
- "dark ambience" → Void Realm
- "deep bass rumble" → Boss sounds
- "horror ambience" → Creepy atmosphere

## ✅ QUALITY CHECKLIST

Before implementing assets:

### Textures
- [ ] Correct size (16x16 for blocks/items, 64x64+ for entities)
- [ ] PNG format with transparency where needed
- [ ] Consistent art style
- [ ] Readable at small size
- [ ] Matches Minecraft aesthetic

### Audio
- [ ] OGG format (use Audacity to convert)
- [ ] Music: Stereo, 44100 Hz, looping seamlessly
- [ ] SFX: Mono or stereo, 22050-44100 Hz
- [ ] Reasonable file size (music < 5MB, SFX < 500KB)
- [ ] No clipping or distortion

### Licensing
- [ ] License allows commercial use (if applicable)
- [ ] License allows modification
- [ ] Attribution noted (if required)
- [ ] License is compatible with mod distribution

## 📝 ATTRIBUTION TEMPLATE

Create a file called `ASSET_CREDITS.md`:

```markdown
# Asset Credits

## Textures
- [Asset Name] by [Author] - [License] - [URL]

## Music
- [Track Name] by [Artist] - [License] - [URL]

## Sound Effects
- [Sound Name] by [Creator] - [License] - [URL]
```

## 🚀 QUICK START WORKFLOW

1. **Start with sounds** (easier to find):
   - Go to Freesound.org
   - Search "dark ambience deep bass" for void music
   - Download CC0 licensed sounds
   - Convert to OGG using Audacity
   - Place in `/sounds/music/void/void_silence.ogg`

2. **Test in-game**:
   - Build mod with `gradlew build`
   - Run game and enter dimension
   - Verify sound plays correctly

3. **Repeat for textures**:
   - Search OpenGameArt.org for "minecraft stone texture"
   - Download 16x16 textures
   - Save as PNG with correct names
   - Place in `/textures/block/`

4. **Verify everything works**:
   - Rebuild mod
   - Check all assets load correctly
   - Update ASSET_CREDITS.md with attribution

## 💡 TIPS

- **Start small**: Get 1-2 sounds/textures working first
- **Batch process**: Use Audacity's batch export for multiple audio files
- **Test frequently**: Rebuild and test after each asset addition
- **Keep originals**: Store original downloaded files separately
- **Document sources**: Immediately note where each asset came from

## 🔗 USEFUL LINKS

- Freesound.org: https://freesound.org/
- OpenGameArt.org: https://opengameart.org/
- Audacity (audio editor): https://www.audacityteam.org/
- Online OGG Converter: https://cloudconvert.com/ogg-converter
- Pixabay: https://pixabay.com/
- itch.io Game Assets: https://itch.io/game-assets/free

---

**Need help?** Check the specific search examples above or refer to the licensing information on each platform!
