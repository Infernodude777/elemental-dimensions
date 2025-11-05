# API Changes for Minecraft 1.21.1

## Critical Fixes Required

### 1. Fire Immunity
- **Old:** `entity.setFireImmune(true)`
- **New:** Override `isFireImmune()` to return `true`
- **Affected:** All Firelands entities, Fire Titan, Elemental Primarch

### 2. EntityType Builder Fire Immunity
- **Old:** `.fireImmune()`  
- **New:** `.makeFireImmune()`
- **Affected:** ModEntities.java (all fire-based entity types)

### 3. Block Classes Changed
- **TransparentBlock** → Use `Block` with `.notSolid()` or `TranslucentBlock`
- **PillarBlock** → Use `PillarBlock` (should exist, check import)
- **SandBlock** → No longer exists, use `FallingBlock` + override `getCodec()`
- **PlantBlock** → Check if exists or use `Block`
- **AmethystClusterBlock** → Check if exists or use custom block

### 4. MapColor/BlockSoundGroup constants
- **CORAL_BLOCK** → May be `PINK` or check BlockSoundGroup class
- **DARK_PURPLE** → Check MapColor for correct constant name

### 5. FallingBlock requires Codec
- Need to implement `getCodec()` method returning a `Codec<EmberSandBlock>`

## Files to Fix (in order):

1. **EmberSandBlock.java** - Add codec implementation
2. **ModBlocks.java** - Fix all block class references and constants  
3. **ModEntities.java** - Fix `.fireImmune()` to `.makeFireImmune()`
4. **All Firelands entities** - Remove `setFireImmune()`, override `isFireImmune()`
5. **FireTitanEntity.java** - Same as above
6. **ElementalPrimarchEntity.java** - Same as above

## Build Strategy

Since there are many similar fixes across multiple files, I'll:
1. Fix the core registry classes first (ModBlocks, ModEntities)
2. Create template entities that demonstrate correct patterns
3. Apply fixes to all similar entities in batch
4. Test build after each major fix category
