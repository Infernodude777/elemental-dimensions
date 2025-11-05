# ELEMENTAL DIMENSIONS - ERROR FIX GUIDE

## 🔥 CRITICAL API FIXES REQUIRED FOR MINECRAFT 1.21.10

### Issue Summary
The mod was built for an earlier Minecraft version. Minecraft 1.21.10 with Fabric has breaking API changes that need to be addressed across ~100+ error instances.

---

## 🛠️ BULK FIX REQUIRED

### Fix #1: TypedActionResult Import
**Error**: `The import net.minecraft.util.TypedActionResult cannot be resolved`

**FILES AFFECTED**:
- All Essence items (FireEssenceItem, WaterEssenceItem, etc.)

**OLD CODE**:
```java
import net.minecraft.util.TypedActionResult;
public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
return TypedActionResult.success(stack, world.isClient());
```

**NEW CODE**:
```java
import net.minecraft.util.ActionResult;
public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
return ActionResult.success(stack);
```

**OR USE Item.use() REPLACEMENT**:
```java
@Override
public void useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
    // Implementation
}
```

---

###Fix #2: World.isClient Field Visibility
**Error**: `The field World.isClient is not visible`

**OLD CODE**:
```java
if (!world.isClient && world instanceof ServerWorld serverWorld)
```

**NEW CODE**:
```java
if (world instanceof ServerWorld serverWorld)
// OR
if (!world.isClient()) // Use method instead of field
```

---

### Fix #3: TooltipContext Method Override
**Error**: `The method appendTooltip(...) must override or implement a supertype method`

**OLD CODE**:
```java
public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
```

**NEW CODE**:
```java
public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
```

---

### Fix #4: PickaxeItem Import
**Error**: `The import net.minecraft.item.PickaxeItem cannot be resolved`

**DIAGNOSIS**: In Minecraft 1.21, tool items were restructured.

**NEW APPROACH - Use MiningToolItem**:
```java
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.BlockTags;

public class InfernoPickItem extends MiningToolItem {
    public InfernoPickItem(Settings settings) {
        super(ModToolMaterial.FIRE, BlockTags.PICKAXE_MINEABLE, settings);
    }
}
```

---

### Fix #5: ArmorItem Import & Constructor
**Error**: `The import net.minecraft.item.ArmorItem cannot be resolved`

**NEW APPROACH**:
```java
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;

// Use AnimalArmorItem or create custom armor class extending Item
public class ElementalArmorItem extends Item {
    public ElementalArmorItem(EquipmentType type, Settings settings) {
        super(settings.equipmentSlot(type));
    }
}
```

---

### Fix #6: LivingEntity.getSlotForHand()
**Error**: `The method getSlotForHand(Hand) is undefined`

**OLD CODE**:
```java
stack.damage(1, miner, LivingEntity.getSlotForHand(miner.getActiveHand()));
```

**NEW CODE**:
```java
stack.damage(1, serverWorld, miner, miner.getActiveEquipmentSlot());
// OR
stack.damage(1, serverWorld, miner, EquipmentSlot.MAINHAND);
```

---

### Fix #7: ParticleTypes.BLOCK Usage
**Error**: `The method spawnParticles(...) is not applicable`

**OLD CODE**:
```java
serverWorld.spawnParticles(ParticleTypes.BLOCK, x, y, z, count, ...)
```

**NEW CODE**:
```java
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.block.Blocks;

serverWorld.spawnParticles(
    new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()),
    x, y, z, count, ...
)
```

---

### Fix #8: Entity Method Changes
**Error**: Various entity method signature changes

**Pyros Entity Fixes**:
```java
// OLD: this.setFireImmune();
// NEW: Constructor parameter
public PyrosEntity(EntityType<? extends HostileEntity> entityType, World world) {
    super(entityType, world);
    this.fireImmune = true; // Set directly
}

// OLD: this.getWorld()
// NEW: this.getEntityWorld() OR just this.getWorld() should work

// OLD: player.damage(source, amount)
// NEW: player.damage(serverWorld, source, amount)

// OLD: EntityPredicates.EXCEPT_SPECTATOR
// NEW: EntityPredicates.VALID_LIVING_ENTITY
```

---

## 📋 STEP-BY-STEP FIX PROCESS

### Step 1: Fix All Essence Items
Files: `FireEssenceItem.java`, `WaterEssenceItem.java`, `EarthEssenceItem.java`, `AirEssenceItem.java`

1. Remove `TypedActionResult` imports
2. Change `use()` method to use `ActionResult`
3. Fix `world.isClient` to `world.isClient()`  
4. Fix `appendTooltip` signature

### Step 2: Fix All Tool Items  
Files: `InfernoPickItem.java`, `AquaBreakerItem.java`, `TerranDrillItem.java`, `ZephyrPickItem.java`

1. Change `extends PickaxeItem` to `extends MiningToolItem`
2. Add `BlockTags.PICKAXE_MINEABLE` parameter
3. Fix `postMine()` method signature
4. Fix `stack.damage()` calls with ServerWorld parameter

### Step 3: Fix All Axe Items
Files: `BlazeAxeItem.java`, `TidalAxeItem.java`, `TerranAxeItem.java`, `GaleAxeItem.java`

1. Change `extends AxeItem` to `extends MiningToolItem` with `BlockTags.AXE_MINEABLE`
2. Fix constructor to not use attribute modifiers (handled by material)

### Step 4: Fix Armor System
File: `ElementalArmorItem.java`

1. Completely rewrite to use new 1.21 equipment system
2. Use `EquipmentType` instead of `ArmorItem.Type`
3. Implement equipment slot handling

### Step 5: Recreate ModItems Registry
Create minimal working version that only registers placeholders

### Step 6: Fix PyrosEntity
1. Update all entity method calls
2. Fix damage method signatures
3. Update predicates

---

## 🚀 QUICK FIX SOLUTION

**OPTION A: DOWNGRADE MINECRAFT**
Change `gradle.properties`:
```properties
minecraft_version=1.20.1
yarn_mappings=1.20.1+build.10
fabric_version=0.92.0+1.20.1
```

**OPTION B: STUB OUT BROKEN ITEMS** (Fastest)
Replace all broken item classes with simple `Item` instances:
```java
public static final Item FIRE_ESSENCE = registerItem("fire_essence", new Item(new Item.Settings()));
```

**OPTION C: INCREMENTAL API UPDATE** (Recommended)
Fix one category at a time:
1. Essences → Complete
2. Tools → Complete  
3. Armor → Complete
4. Weapons (already working)
5. Entities → Complete

---

## ⚡ IMMEDIATE ACTION REQUIRED

Due to the 273 errors and cascading dependency issues, I recommend:

1. **Create a clean ModItems.java with only placeholders**
2. **Comment out broken imports**
3. **Get the mod to compile** 
4. **Then incrementally fix each item type**

Would you like me to:
- A) Create placeholder ModItems to get compilation working
- B) Fix essence items first (smallest scope)
- C) Provide a complete rewrite of one item category
- D) Create a Python script to bulk-replace API calls

The errors are systematic and fixable, but require methodical updates across all affected files.
