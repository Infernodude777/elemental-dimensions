# Quick Asset Download Links

This is a curated list of specific, high-quality assets that would work well for Elemental Dimensions.

## 🎵 MUSIC - Ready to Download

### Incompetech (Kevin MacLeod) - CC-BY 4.0
All tracks require attribution: "Music by Kevin MacLeod (incompetech.com) Licensed under Creative Commons: By Attribution 4.0 License"

**For Firelands:**
- "Infernal" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100270
- "Invariant" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100407
- "Volatile Reaction" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1400050

**For Aquatica:**
- "Atlantis" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100163
- "Soaring" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100681
- "Luminous Rain" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1400030

**For Terra Depths:**
- "Cavernous Expanse" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100196
- "Dark Descent" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1400012
- "Mysteries of the Mausoleum" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100527

**For Skyreach:**
- "Dreaming in Clouds" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100240
- "Fluffing a Duck" (lighter) - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100278

**For Celestial:**
- "Ospace" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100551
- "Lightless Dawn" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100413

**For Void:**
- "Darker Waves" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100215
- "Dark Fog" - https://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200076

### OpenGameArt Music

**Atmospheric/Ambient:**
- "Osmotic Memory" - https://opengameart.org/content/osmotic-memory
- "Forbidden Exploration" - https://opengameart.org/content/forbidden-exploration
- "Lanterns in the Hollowed Forest" - https://opengameart.org/content/lanterns-in-the-hollowed-forest

## 🔊 SOUND EFFECTS - Freesound.org (CC0 Best Bets)

### Fire/Lava Sounds
Direct searches on Freesound (filter by CC0):
- Search: "fire crackling loop"
- Search: "lava bubbling"
- Search: "fire burning ambient"

**Recommended specific sounds:**
- Fire Crackling: https://freesound.org/people/InspectorJ/sounds/398707/ (CC-BY 4.0)
- Deep Rumble: Use search "deep bass rumble" and filter CC0

### Water Sounds
- Underwater Ambience: https://freesound.org/people/wjoojoo/sounds/197751/ (CC-BY 4.0)
  "underwater_ambience_lake"
- Ocean Waves: https://freesound.org/people/Profispiesser/sounds/550915/ (CC-BY 4.0)
  "BGSaSc Water Beach Ocean Waves"

### Dark/Void Sounds
- Dark Ambience: https://freesound.org/people/Darklitstudio/sounds/832310/ (Check license)
  "Dark ambience -Deep low bass humming sound"
- Thunder (for drama): https://freesound.org/people/C-V/sounds/830372/ (CC0)
  "Rumblings of Thunder"

### Magic/Crystal Sounds
- Magic SFX: https://freesound.org/people/IvanMK10/sounds/831898/ (Check license)
  "Magic"
- Sparkle: https://freesound.org/people/MLaudio/sounds/511485/ (CC0)
  "cartoon_wink_magic_sparkle"

## 🎨 TEXTURES

### Block Textures (16x16)

**OpenGameArt searches that usually have good results:**
1. Search "minecraft texture" and filter by "2D" art type
2. Search "16x16 tiles" 
3. Search "pixel art stone"

**Specific packs to check:**
- "Tiled Terrains" - https://opengameart.org/content/tiled-terrains
  (Has various stone/ground textures you can adapt)

### Item/Icon Textures

**Good sources:**
- "Fantasy RPG Icons" - https://opengameart.org/content/fantasy-rpg-icons
  (Has essence-like icons you can use/modify)

### For Quick Placeholder Textures

If you need something working NOW while you search for better assets:

**Minecraft Wiki (Java Edition textures):**
- Most vanilla textures are in a gray license area, but you can use them as REFERENCE
- Create similar but distinct versions
- Search: "minecraft texture" on Google Images, filter by license: "Creative Commons licenses"

## 🎮 ENTITY SPRITES

**OpenGameArt good packs:**
- Search "rpg monster sprite"
- Search "fantasy creature"
- Search "pixel art enemy"

**Note:** Entity textures for Minecraft are typically 64x64, 64x32, or similar dimensions.

## ⚡ QUICK START PACKAGE

Here's what to download FIRST to get something working:

### Priority 1: Void Dimension (Main Feature)
1. **1 Music Track**: Download "Darker Waves" from Incompetech
2. **1 Ambient Sound**: Search Freesound for "dark ambience" CC0
3. **6 Block Textures**: Use dark variants from any stone pack on OpenGameArt
4. **3 Item Textures**: Use crystal/gem icons from Fantasy RPG pack

### Priority 2: Other Dimensions
- Download 1 track per dimension from Incompetech list above
- Use texture packs from OpenGameArt for block textures

### Priority 3: Polish
- Add ambient sounds per dimension
- Add mob sounds
- Improve textures with custom artwork

## 🔧 TOOLS YOU'LL NEED

1. **Audacity** (Audio conversion to OGG)
   - Download: https://www.audacityteam.org/download/
   - Free, open-source

2. **GIMP** (Image editing for textures)
   - Download: https://www.gimp.org/downloads/
   - Free, open-source

3. **Paint.NET** (Windows alternative to GIMP)
   - Download: https://www.getpaint.net/download.html
   - Free, Windows only

4. **Online OGG Converter** (if you don't want to install Audacity)
   - https://cloudconvert.com/ogg-converter
   - Free, web-based

## 📋 CHECKLIST WORKFLOW

1. [ ] Download `download_assets.ps1` script
2. [ ] Run with `-CreateDirectories` to create folder structure
3. [ ] Download 1 music track from Incompetech
4. [ ] Convert to OGG using Audacity
5. [ ] Place in correct folder (e.g., `/sounds/music/void/void_silence.ogg`)
6. [ ] Update ASSET_CREDITS.md with attribution
7. [ ] Build mod and test
8. [ ] Repeat for more assets

## 💰 BUDGET-FREE OPTIONS

All assets listed here are either:
- **CC0** (Public Domain) - Use freely, no attribution needed
- **CC-BY** - Use freely, attribution required
- **CC-BY-SA** - Use freely, attribution required, share-alike

**AVOID:**
- CC-BY-NC (Non-Commercial) - May conflict with mod distribution
- CC-BY-ND (No Derivatives) - Can't modify
- All Rights Reserved - Can't use without permission

## 🎯 EXPECTED TIME

- **Quick placeholder setup**: 1-2 hours
  (Download a few tracks, basic textures, get it working)

- **Quality asset hunt**: 4-8 hours
  (Find perfect-fit assets, convert, organize, attribute)

- **Professional polish**: 10-20 hours
  (Custom textures, perfect music selection, mob sounds, testing)

## 📞 NEED HELP?

If you get stuck:
1. Check ASSET_ACQUISITION_GUIDE.md for detailed instructions
2. Run `.\download_assets.ps1 -Help` for script usage
3. Visit the source sites' FAQ/Help pages
4. Ensure files are in correct format (PNG for textures, OGG for audio)

---

**Pro Tip**: Start with Incompetech music and Freesound effects. They're well-organized, high-quality, and clearly licensed. You can have working audio in under an hour!
