# QUICK START GUIDE
**Last Updated**: November 5, 2025 (MC 1.21.10 Update Attempt)

## ✅ CURRENT STATUS

**Main Version (MC 1.21.1): FULLY WORKING**
- Build Status: ✅ BUILD SUCCESSFUL in 10s
- Compilation Errors: **0**
- All Features: **WORKING**
- GeckoLib: **Fully Compatible**

**1.21.10 Folder: PARTIAL COMPATIBILITY**
- Build Status: ⚠️ API migration in progress
- Basic Features: Working
- GeckoLib Rendering: Waiting for library update
- Status: Items/entities need MC 1.21.10 API migration

## ⚠️ IMPORTANT: MC 1.21.10 CHANGES

Minecraft 1.21.10 (released October 7, 2025) introduced significant API changes:
- `EntityAttributes`: Removed `GENERIC_` prefix (✅ FIXED)
- Particle rendering: Now uses `ServerWorld.spawnParticles()` (✅ FIXED)
- `Item.use()`: Method signature completely changed (⚠️ IN PROGRESS)
- Entity damage/teleport: API methods changed (⚠️ IN PROGRESS)
- GeckoLib 4.8.2: Renderer registration incompatible (⏳ Waiting for update)

---

## 🚀 BUILD COMMANDS

### Recommended: Use Main Version (MC 1.21.1)
.\gradlew build

# Build 1.21.10 variant
cd 1.21.10
.\gradlew build

# Clean build (if needed)
.\gradlew clean build

# Compile only (faster)
.\gradlew compileJava
```

---

## 📦 OUTPUT JARS

- **Main**: `build/libs/elemental-dimensions-1.0.0.jar`
- **1.21.10**: `1.21.10/build/libs/elemental-dimensions-1.0.0.jar`

---

## 🆕 NEW TONIGHT

1. **Grappling Hook** - Advanced mobility item with physics
2. **21 New Blocks** - Decorative & functional with particles
3. **3 New Entities** - Lava Golem, Deep Anglerfish, Earth Elemental

---

## 📚 DOCUMENTATION

- **NIGHT_WORK_SUMMARY.md** - Complete session report
- **home.txt** - Welcome message with stats
- **README.md** - General mod information

---

## 🎮 PLAY THE MOD

1. Build using command above
2. Copy JAR to `.minecraft/mods/`
3. Ensure dependencies installed:
   - Fabric Loader 0.16.5+
   - Fabric API 0.102.0+
   - GeckoLib 4.8.2+
4. Launch Minecraft 1.21.1
5. Have fun!

---

## 🔍 TROUBLESHOOTING

**Build fails?**
- Run `.\gradlew clean build --refresh-dependencies`
- Delete `.gradle` folder and rebuild
- Check Java version (needs 21+)

**Can't find JARs?**
- Check `build/libs/` folder
- Ensure build completed successfully

**Mod won't load?**
- Verify Fabric Loader installed
- Check Fabric API version matches
- Ensure GeckoLib mod installed

---

**Need Help?** See NIGHT_WORK_SUMMARY.md for detailed information!
