# Flowing Fluid Implementation Guide

## Current Status: NOT IMPLEMENTED

Lucid Water currently exists as a static block (no flowing physics). This guide explains how to implement proper flowing fluids.

---

## ⚠️ Complexity Warning

Implementing flowing fluids in Minecraft 1.21.1 is **significantly more complex** than other features because it requires:

1. **Multiple interconnected classes** (Fluid, FlowableFluid, FluidBlock, BucketItem)
2. **Rendering system integration** (fluid textures, translucency, tinting)
3. **Physics simulation** (spreading, source blocks, flowing states)
4. **Bucket interaction** (fill/empty mechanics)
5. **Particle effects** (drips, splashes)
6. **Sound effects** (ambient, filling, emptying)

**Estimated Time: 4-6 hours** for full implementation

---

## 📋 Required Components

### 1. Fluid Classes (Core Logic)

**BaseFluid.java** - Abstract base for fluid behavior
```java
public abstract class LucidWaterFluid extends FlowableFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_LUCID_WATER;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_LUCID_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.LUCID_WATER_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.LUCID_WATER.getDefaultState()
            .with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_LUCID_WATER ||
               fluid == ModFluids.FLOWING_LUCID_WATER;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        // Handle block interactions (e.g., concrete powder -> concrete)
    }

    @Override
    protected boolean isInfinite(World world) {
        return false; // Set to true for infinite source blocks (like water)
    }

    @Override
    protected void flow(WorldAccess world, BlockPos pos, BlockState state,
                       Direction direction, FluidState fluidState) {
        // Custom flow behavior
        if (world.getFluidState(pos).isStill()) {
            // Source block behavior
        }
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4; // Blocks per tick (water is 4, lava is 2)
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1; // How much level decreases when flowing horizontally
    }

    // Still fluid variant
    public static class Still extends LucidWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8; // Maximum level
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }

    // Flowing fluid variant
    public static class Flowing extends LucidWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }
}
```

### 2. FluidBlock (Placeable Block)

**LucidWaterBlock.java**
```java
public class LucidWaterBlock extends FluidBlock {
    public LucidWaterBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        // Custom entity interactions
        // E.g., slow movement, apply effects, damage/heal
        if (entity instanceof LivingEntity living) {
            // Apply slow falling or levitation
            living.addStatusEffect(new StatusEffectInstance(
                StatusEffects.SLOW_FALLING, 20, 0));
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Particle effects
        if (random.nextInt(10) == 0) {
            world.addParticle(
                ParticleTypes.DRIPPING_WATER,
                pos.getX() + random.nextDouble(),
                pos.getY() - 0.1,
                pos.getZ() + random.nextDouble(),
                0, 0, 0
            );
        }
    }
}
```

### 3. Bucket Item

**LucidWaterBucketItem.java**
```java
public class LucidWaterBucketItem extends BucketItem {
    public LucidWaterBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Handle bucket placement
        ActionResult result = super.useOnBlock(context);

        if (result.isAccepted()) {
            // Play sound
            World world = context.getWorld();
            BlockPos pos = context.getBlockPos();
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY,
                SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

        return result;
    }
}
```

### 4. Fluid Registration

**ModFluids.java**
```java
public class ModFluids {
    // Still variant (source block)
    public static final FlowableFluid STILL_LUCID_WATER = register("still_lucid_water",
        new LucidWaterFluid.Still());

    // Flowing variant (non-source)
    public static final FlowableFluid FLOWING_LUCID_WATER = register("flowing_lucid_water",
        new LucidWaterFluid.Flowing());

    private static FlowableFluid register(String name, FlowableFluid fluid) {
        return Registry.register(Registries.FLUID,
            Identifier.of(ElementalDimensions.MOD_ID, name), fluid);
    }

    public static void initialize() {
        ElementalDimensions.LOGGER.info("Registering fluids");
    }
}
```

### 5. Update ModBlocks

Replace static LUCID_WATER block with FluidBlock:
```java
public static final FluidBlock LUCID_WATER = (FluidBlock) registerBlock("lucid_water",
    new LucidWaterBlock(ModFluids.STILL_LUCID_WATER,
        AbstractBlock.Settings.create()
            .mapColor(MapColor.WATER_BLUE)
            .noCollision()
            .strength(100.0f)
            .dropsNothing()
            .liquid()
            .replaceable()));
```

### 6. Update ModItems

Add bucket item:
```java
public static final Item LUCID_WATER_BUCKET = registerItem("lucid_water_bucket",
    new LucidWaterBucketItem(ModFluids.STILL_LUCID_WATER,
        new Item.Settings()
            .maxCount(1)
            .recipeRemainder(Items.BUCKET)));
```

---

## 🎨 Rendering Setup

### 1. Fluid Textures

Create 2 textures in: `src/main/resources/assets/elementaldimensions/textures/block/`

- `lucid_water_still.png` - Static water texture (16x16, animated optional)
- `lucid_water_flow.png` - Flowing water texture (16x16, animated optional)

**Tip:** Use Minecraft's water textures as a base and tint them purple/pink.

### 2. Fluid Rendering JSON

Create: `src/main/resources/assets/elementaldimensions/models/fluid/lucid_water.json`

```json
{
  "textures": {
    "still": "elementaldimensions:block/lucid_water_still",
    "flowing": "elementaldimensions:block/lucid_water_flow"
  }
}
```

### 3. Blockstate JSON

Update: `src/main/resources/assets/elementaldimensions/blockstates/lucid_water.json`

```json
{
  "variants": {
    "": {
      "model": "elementaldimensions:block/lucid_water"
    }
  }
}
```

### 4. Fluid Rendering Registration

In `ElementalDimensionsClient.java` (client-side initialization):

```java
@Override
public void onInitializeClient() {
    FluidRenderHandlerRegistry.INSTANCE.register(
        ModFluids.STILL_LUCID_WATER,
        ModFluids.FLOWING_LUCID_WATER,
        new SimpleFluidRenderHandler(
            Identifier.of("elementaldimensions", "block/lucid_water_still"),
            Identifier.of("elementaldimensions", "block/lucid_water_flow")
        )
    );

    BlockRenderLayerMap.INSTANCE.putFluids(
        RenderLayer.getTranslucent(),
        ModFluids.STILL_LUCID_WATER,
        ModFluids.FLOWING_LUCID_WATER
    );
}
```

---

## 🔊 Sound Integration

### Fluid Sounds JSON

Create: `src/main/resources/assets/elementaldimensions/sounds.json`

```json
{
  "block.lucid_water.ambient": {
    "sounds": [
      "elementaldimensions:block/lucid_water/ambient1",
      "elementaldimensions:block/lucid_water/ambient2"
    ],
    "subtitle": "subtitles.block.lucid_water.ambient"
  },
  "item.bucket.fill_lucid_water": {
    "sounds": [
      "item/bucket/fill_water1",
      "item/bucket/fill_water2"
    ]
  },
  "item.bucket.empty_lucid_water": {
    "sounds": [
      "item/bucket/empty_water1",
      "item/bucket/empty_water2"
    ]
  }
}
```

---

## 🎮 Bucket Recipe

Create: `src/main/resources/data/elementaldimensions/recipe/lucid_water_bucket.json`

**Note:** Buckets are typically filled by right-clicking the fluid, not crafted.
But you can add a crafting recipe if desired:

```json
{
  "type": "minecraft:crafting_shapeless",
  "category": "misc",
  "ingredients": [
    {
      "item": "minecraft:water_bucket"
    },
    {
      "item": "elementaldimensions:dreamstone"
    }
  ],
  "result": {
    "id": "elementaldimensions:lucid_water_bucket",
    "count": 1
  }
}
```

---

## 📝 Language Entries

Add to `en_us.json`:

```json
{
  "block.elementaldimensions.lucid_water": "Lucid Water",
  "item.elementaldimensions.lucid_water_bucket": "Lucid Water Bucket",
  "subtitles.block.lucid_water.ambient": "Lucid Water flows"
}
```

---

## 🧪 Testing Checklist

- [ ] Fluid spawns in world (in Dreaming Depths dimension)
- [ ] Fluid spreads horizontally when placed
- [ ] Fluid flows downward
- [ ] Fluid creates source blocks (if infinite enabled)
- [ ] Bucket can pick up fluid
- [ ] Bucket can place fluid
- [ ] Empty bucket remains after placing
- [ ] Fluid is transparent/translucent
- [ ] Fluid has correct color tint
- [ ] Particles appear (drips, splashes)
- [ ] Sounds play (ambient, filling, emptying)
- [ ] Entity collision works (swimming, effects)
- [ ] Fluid interacts with blocks correctly

---

## ⚙️ Configuration Options

### Flow Speed
```java
@Override
protected int getFlowSpeed(WorldView world) {
    return 4; // Default: 4 (water), 2 (lava)
}
```

### Infinite Sources
```java
@Override
protected boolean isInfinite(World world) {
    return true; // 2x2 source blocks create infinite water
}
```

### Viscosity
```java
@Override
public int getTickRate(WorldView world) {
    return 5; // Higher = slower flow (water: 5, lava: 30)
}
```

### Entity Movement
```java
@Override
public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    // Slow movement
    entity.setVelocity(entity.getVelocity().multiply(0.5, 1.0, 0.5));

    // Apply status effects
    if (entity instanceof LivingEntity living) {
        living.addStatusEffect(new StatusEffectInstance(
            StatusEffects.NIGHT_VISION, 200, 0, true, false));
    }
}
```

---

## 🚀 Simplified Implementation (1-2 Hours)

If you want fluids working quickly:

### Option A: Copy Water Behavior

Extend Minecraft's water classes:
```java
public class LucidWaterFluid extends WaterFluid {
    // Inherit all water behavior
    // Only customize visuals and effects
}
```

### Option B: Static "Fluid" Block

Keep current implementation (solid block) but add:
- Translucent rendering
- Particle effects
- Entity collision behavior
- Call it "Lucid Crystal" instead of "Lucid Water"

This is what's currently implemented - functionally complete but non-flowing.

---

## 📊 Implementation Time Breakdown

| Component | Time Required | Complexity |
|-----------|---------------|------------|
| Fluid classes (Still + Flowing) | 1-2 hours | HIGH |
| FluidBlock implementation | 30 min | MEDIUM |
| Bucket item | 30 min | LOW |
| Registration | 30 min | MEDIUM |
| Textures | 1 hour | LOW |
| Rendering setup | 1 hour | HIGH |
| Sound integration | 30 min | LOW |
| Testing & debugging | 1-2 hours | MEDIUM |
| **TOTAL** | **5-7 hours** | **HIGH** |

---

## 🎯 Recommendation

Given the complexity and time investment:

**Option 1: Skip for v1.0 (Recommended)**
- Keep lucid_water as decorative block
- Note in changelog: "Flowing fluids planned for v1.1"
- Focus on structures and polish
- Mod is 100% playable without flowing fluids

**Option 2: Simplified "Fluid" Block**
- Add translucency and particles to current block
- Add entity collision effects
- Rename to "Lucid Crystal" for clarity
- Time: 30 minutes
- Result: Visually interesting, thematically appropriate

**Option 3: Full Flowing Fluid (5-7 hours)**
- Complete flowing water mechanics
- Bucket items and filling/emptying
- Proper rendering and physics
- All interactions working

---

## ✅ Quick Win: Enhanced Static Block (30 min)

Update current lucid_water to be more interesting:

```java
public static final Block LUCID_WATER = registerBlock("lucid_water",
    new Block(AbstractBlock.Settings.create()
        .mapColor(MapColor.WATER_BLUE)
        .strength(100.0f)
        .sounds(BlockSoundGroup.GLASS)
        .luminance(state -> 8)
        .nonOpaque()
        .allowsSpawning((state, world, pos, type) -> false)
        .solidBlock((state, world, pos) -> false)
        .suffocates((state, world, pos) -> false)
        .blockVision((state, world, pos) -> false)) {

        @Override
        public VoxelShape getOutlineShape(BlockState state, BlockView world,
                                         BlockPos pos, ShapeContext context) {
            return Block.createCuboidShape(0, 0, 0, 16, 14, 16); // Slightly lower
        }

        @Override
        public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
            // Add particles
            if (random.nextInt(10) == 0) {
                world.addParticle(
                    ParticleTypes.GLOW,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + 0.5,
                    pos.getZ() + random.nextDouble(),
                    0, 0.05, 0
                );
            }
        }

        @Override
        public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
            // Apply effect when walking through
            if (entity instanceof LivingEntity living) {
                living.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NIGHT_VISION, 100, 0, true, false));
            }
        }
    });
```

**Result:**
- Translucent block with particles
- Grants night vision when touched
- Visually similar to fluid
- No complex physics needed
- Time: 30 minutes

---

**Status:** Fluid foundation explained, implementation optional
**Recommended Action:** Enhanced static block (30 min) or skip for v1.0
**Priority:** LOW (mod fully playable without flowing fluids)
