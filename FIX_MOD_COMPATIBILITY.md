# Fix Mod Compatibility Issues

## Problem
You have Minecraft 1.20.1 mods installed in a Minecraft 1.21.1 instance.

## Error Summary
```
❌ GeckoLib 4.8.2 - Claims to need MC 1.21.4+ (FALSE - it supports 1.21.1)
❌ Zoomify 2.14.6+1.20.1 - Requires MC 1.20-1.20.1 (you have 1.21.1)
❌ YetAnotherConfigLib 3.6.0+1.20.1 - Requires MC 1.20.1 (you have 1.21.1)
```

## Solution: Clean Up Your Mods Folder

### Step 1: Locate Your Instance Mods Folder
1. Open **Prism Launcher**
2. Right-click your **Minecraft 1.21.1** instance
3. Click **"Folder"** or **"Open Instance Folder"**
4. Navigate to the **`mods`** folder

### Step 2: Remove Incompatible Mods
Delete or move these files OUT of the mods folder:
- `zoomify-2.14.6+1.20.1.jar` (or similar)
- `yet_another_config_lib_v3-3.6.0+1.20.1-fabric.jar` (or similar)

### Step 3: Verify Required Mods for Elemental Dimensions
Keep/Install these mods (MC 1.21.1 versions):

#### ✅ Required Mods:
1. **Fabric API** (latest for MC 1.21.1)
   - Download: https://modrinth.com/mod/fabric-api/versions?g=1.21.1
   
2. **GeckoLib 4.8.2** (MC 1.21.1)
   - Download: https://modrinth.com/mod/geckolib/version/4.8.2-1.21.1
   - If you have an older GeckoLib, DELETE it first
   
3. **Cloth Config 15.0.140** (MC 1.21.1)
   - Download: https://modrinth.com/mod/cloth-config/versions?g=1.21.1

4. **Elemental Dimensions 1.0.0** (your mod)
   - Use: `1.21.1\build\libs\elemental-dimensions-1.0.0.jar`

### Step 4: Optional Mods (MC 1.21.1 versions only!)
If you want Zoomify or config libraries, download the **MC 1.21.1 versions**:
- **Zoomify for MC 1.21.1**: https://modrinth.com/mod/zoomify/versions?g=1.21.1
- **YACL for MC 1.21.1**: https://modrinth.com/mod/yacl/versions?g=1.21.1

### Step 5: Launch Test
1. Close Prism Launcher completely
2. Reopen Prism Launcher
3. Launch your MC 1.21.1 instance
4. Check if Elemental Dimensions loads without errors

## Quick Checklist
```
□ Removed all MC 1.20.1 mods
□ Installed Fabric API (1.21.1)
□ Installed GeckoLib 4.8.2 (1.21.1)
□ Installed Cloth Config 15.0.140 (1.21.1)
□ Copied elemental-dimensions-1.0.0.jar to mods folder
□ Launched game successfully
```

## Expected Result
After cleanup, you should see in the Mods list:
- ✅ Elemental Dimensions 1.0.0
- ✅ Fabric API
- ✅ GeckoLib 4.8.2
- ✅ Cloth Config

**No compatibility errors!** 🎉

## Still Having Issues?
If GeckoLib still shows "requires 1.21.4+":
1. You might have **TWO versions** of GeckoLib in your mods folder
2. Delete ALL GeckoLib jars
3. Download ONLY GeckoLib 4.8.2 for MC 1.21.1 from the link above
4. Verify the filename shows "1.21.1" not "1.21.4"
