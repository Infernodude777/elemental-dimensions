# Step-by-Step Asset Implementation Tutorial

This is a beginner-friendly, step-by-step guide to getting real textures and sounds into your mod.

---

## 🎯 GOAL

Replace the basic procedural textures and add actual music/sounds to make Elemental Dimensions look and sound professional.

**Time Required:** 2-4 hours for basic implementation

---

## 📋 STEP 1: SET UP THE FOLDERS

**What you're doing:** Creating the directory structure where assets will go.

### Instructions:

1. Open PowerShell in your mod folder
2. Run this command:
   ```powershell
   .\download_assets.ps1 -CreateDirectories
   ```

**Expected Result:** You'll see green checkmarks showing folders were created.

**What this does:** Creates all the subdirectories like `sounds/music/firelands/`, `textures/block/`, etc.

---

## 🎵 STEP 2: DOWNLOAD YOUR FIRST MUSIC TRACK

**What you're doing:** Getting one atmospheric music track for the Void dimension.

### Instructions:

1. Open your web browser
2. Go to: https://incompetech.com/music/royalty-free/
3. Search for: **"Darker Waves"**
4. Click on the track
5. Click the **"Download MP3"** button
6. Save it to your Downloads folder

**Expected Result:** You have a file called `Darker Waves.mp3` in your Downloads folder.

---

## 🔄 STEP 3: CONVERT TO OGG FORMAT

**What you're doing:** Minecraft needs sounds in OGG format, not MP3.

### Option A: Using Audacity (Recommended - Free Software)

1. Download Audacity: https://www.audacityteam.org/download/
2. Install and open Audacity
3. Click **File → Open** and select your `Darker Waves.mp3`
4. Click **File → Export → Export as OGG Vorbis...**
5. Set Quality to **5** (good balance)
6. Save as: **void_silence.ogg** (this is the exact name needed)

### Option B: Using Online Converter

1. Go to: https://cloudconvert.com/mp3-to-ogg
2. Click **"Select File"** and choose `Darker Waves.mp3`
3. Click **"Convert"**
4. Download the converted file
5. Rename it to: **void_silence.ogg**

**Expected Result:** You have a file called `void_silence.ogg`

---

## 📂 STEP 4: PUT THE FILE IN THE RIGHT PLACE

**What you're doing:** Moving the sound file to where the mod expects it.

### Instructions:

1. Navigate to: `src\main\resources\assets\elementaldimensions\sounds\music\void\`
2. Copy your **void_silence.ogg** file into this folder

**Expected Result:** The file is now at:
`src\main\resources\assets\elementaldimensions\sounds\music\void\void_silence.ogg`

---

## ✅ STEP 5: VERIFY IT WORKED

**What you're doing:** Checking that the file is in the right place with the right format.

### Instructions:

1. Run this command in PowerShell:
   ```powershell
   .\validate_assets.ps1
   ```

**Expected Result:** You should see:
```
MUSIC TRACKS (Expected: OGG format)
  void/:
    [✓] void_silence.ogg - X.XX MB
```

**If you see a red [ ] instead:** The file is missing or misnamed. Double-check the filename and location.

---

## 🏗️ STEP 6: BUILD AND TEST

**What you're doing:** Compiling the mod with your new music.

### Instructions:

1. Run this command:
   ```powershell
   .\gradlew build
   ```

2. Wait for **"BUILD SUCCESSFUL"**

3. Your mod file is now at: `build\libs\elemental_dimensions-1.0.0.jar`

4. Copy this to your Minecraft `mods` folder

5. Launch Minecraft and enter the Void dimension

**Expected Result:** You hear your music playing in the Void dimension!

---

## 🎨 STEP 7: ADD YOUR FIRST TEXTURE

**What you're doing:** Getting a texture for Void Stone blocks.

### Instructions:

1. Go to: https://opengameart.org/
2. Search for: **"stone texture 16x16"** or **"minecraft stone"**
3. Find a dark stone texture you like (check the license - look for CC0 or CC-BY)
4. Download it
5. If it's not 16x16 pixels, resize it using:
   - Paint.NET (Windows): https://www.getpaint.net/
   - GIMP (Cross-platform): https://www.gimp.org/
   - Even MS Paint works!

6. Save it as: **void_stone.png** (exactly this name, PNG format)

7. Put it in: `src\main\resources\assets\elementaldimensions\textures\block\`

**Expected Result:** You have a 16x16 PNG file called `void_stone.png` in the textures/block folder.

---

## 🔍 STEP 8: VERIFY THE TEXTURE

Run the validator again:
```powershell
.\validate_assets.ps1
```

**Expected Result:** 
```
BLOCK TEXTURES (Expected: 16x16 PNG)
  [✓] void_stone.png - 16x16 PNG
```

---

## 🔄 STEP 9: REBUILD AND SEE IT IN-GAME

1. Run: `.\gradlew build`
2. Copy the new JAR to your mods folder (overwrite the old one)
3. Launch Minecraft
4. Go to the Void dimension
5. Look at Void Stone blocks

**Expected Result:** You see your custom texture on the blocks!

---

## 📝 STEP 10: ADD ATTRIBUTION

**What you're doing:** Giving credit to the artists (required for CC-BY licenses).

### Instructions:

1. Open: `ASSET_CREDITS.md`
2. Under "### Void Realm Music", add:
   ```markdown
   - **void_silence.ogg** - Kevin MacLeod - License: CC-BY 4.0 - Source: https://incompetech.com
     Attribution: "Darker Waves by Kevin MacLeod (incompetech.com) Licensed under Creative Commons: By Attribution 4.0 License"
   ```

3. Under "### Block Textures", add:
   ```markdown
   - **void_stone.png** - [Author Name from OpenGameArt] - License: [Check the page] - Source: [URL]
   ```

**Expected Result:** Your ASSET_CREDITS.md file now has proper attribution.

---

## 🎉 CONGRATULATIONS!

You've successfully:
- ✅ Added custom music to your mod
- ✅ Added a custom texture
- ✅ Learned the complete workflow

---

## 🔁 REPEAT FOR MORE ASSETS

Now you can repeat this process for:

### More Music (18 tracks total):
- Firelands: 4 tracks (fire/lava themed)
- Aquatica: 4 tracks (water/ocean themed)  
- Terra: 4 tracks (cave/earth themed)
- Skyreach: 3 tracks (wind/sky themed)
- Celestial: 2 tracks (space/cosmic themed)
- Void: 1 track (done! ✅)

### More Textures:
- 5 more block textures (void_crystal_ore, void_glass, etc.)
- 8 item textures (essences, weapons)
- 8 entity textures (mobs)

### Sound Effects:
- 3 ambient sounds (background atmosphere)
- Mob sounds (idle, hurt, death)

---

## 🆘 TROUBLESHOOTING

### "BUILD FAILED" error
- Check that file names are EXACTLY correct (case-sensitive!)
- Make sure textures are PNG format
- Make sure sounds are OGG format

### Music doesn't play in-game
- Verify file is in correct folder
- Check filename matches `sounds.json` exactly
- Ensure OGG format (not MP3)

### Texture doesn't show
- Verify 16x16 size for blocks/items
- Check PNG format
- Ensure filename matches exactly

### Validation script shows errors
- Read the error message carefully
- Check file location
- Verify file format (PNG for textures, OGG for sounds)

---

## 📚 NEXT STEPS

Once you're comfortable with this workflow:

1. Read `QUICK_ASSET_LINKS.md` for direct download links
2. Check `ASSET_ACQUISITION_GUIDE.md` for advanced tips
3. Use `download_assets.ps1` to track your progress
4. Visit the recommended sites to find perfect assets

---

## 💡 PRO TIPS

1. **Start with sounds** - They're easier to find and implement
2. **Use CC0 licenses** - No attribution needed, simpler
3. **Test frequently** - Build and test after each asset
4. **Keep originals** - Save original downloads in a separate folder
5. **Batch convert** - Use Audacity to convert multiple MP3s at once
6. **Document immediately** - Add to ASSET_CREDITS.md right away

---

## 🔗 QUICK REFERENCE

### Essential Commands:
```powershell
# Create folders
.\download_assets.ps1 -CreateDirectories

# Check progress
.\validate_assets.ps1

# Build mod
.\gradlew build

# Clean build (if issues)
.\gradlew clean build
```

### Essential File Locations:
```
Downloads → (your files here)
↓ Convert to OGG (if audio)
↓ Resize to 16x16 (if texture)
↓ Rename correctly
↓ Move to:
src\main\resources\assets\elementaldimensions\
├── sounds\music\[dimension]\[filename].ogg
├── sounds\ambient\[filename].ogg
├── textures\block\[filename].png
├── textures\item\[filename].png
└── textures\entity\[filename].png
```

### Essential Websites:
- **Music:** https://incompetech.com (CC-BY, high quality)
- **Sounds:** https://freesound.org (CC0, CC-BY available)
- **Textures:** https://opengameart.org (various free licenses)

---

**You've got this!** Take it one asset at a time. Before you know it, you'll have a fully customized, professional-looking mod! 🚀
