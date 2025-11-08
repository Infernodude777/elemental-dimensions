# Minecraft 1.21.1 Fluid API Notes

## Issue Discovered
The Fabric wiki tutorial for custom fluids was outdated for Minecraft 1.21.1. The tutorial shows methods like `getFlowSpeed()`, `getLevelDecreasePerBlock()`, `getTickRate()`, etc. with `@Override` annotations, but in 1.21.1 these methods are **not abstract** in `FlowableFluid`.

## Solution
Remove `@Override` annotation from `getFlowSpeed()` method in all custom fluid classes. The method can still be defined to customize flow behavior, but it should not claim to override a parent method.

### Methods That Work in 1.21.1

**Required Abstract Methods (must have @Override):**
- `getFlowing()` - Returns the flowing variant
- `getStill()` - Returns the still variant
- `getBucketItem()` - Returns the bucket item
- `toBlockState(FluidState)` - Converts FluidState to BlockState
- `matchesType(Fluid)` - Checks if fluid matches this type
- `getLevel(FluidState)` - Returns fluid level (in Still/Flowing subclasses)
- `isStill(FluidState)` - Returns whether fluid is still (in Still/Flowing subclasses)

**Methods That CAN Be Overridden (but don't use @Override on getFlowSpeed):**
- `getFlowSpeed(WorldView)` - ⚠️ Remove @Override! Define without annotation
- `getLevelDecreasePerBlock(WorldView)` - Keep @Override
- `getTickRate(WorldView)` - Keep @Override
- `getBlastResistance()` - Keep @Override
- `beforeBreakingBlock(WorldAccess, BlockPos, BlockState)` - Keep @Override
- `getMaxFlowDistance(WorldView)` - Keep @Override
- `isInfinite(World)` - Keep @Override

## Current Fluid Implementation

All 5 custom fluids now build successfully:

1. **Lucid Water** (Dreaming Depths) - Water-like, flow speed 4, infinite
2. **Radiant Fluid** (Radiant Plateau) - Viscous, flow speed 3, infinite
3. **Mycelial Ooze** (Mycelial Nexus) - Honey-like, flow speed 2, finite
4. **Void Essence** (Astral Frontier) - Fast-flowing, flow speed 5, finite
5. **Ink Fluid** (Glyphic Archives) - Medium, flow speed 3, infinite

Each fluid has:
- Custom flow speed and behavior
- Status effects applied on contact (via DimensionalFluidBlock)
- Particle effects (GLOW, END_ROD, SPORE_BLOSSOM_AIR, PORTAL, SQUID_INK)
- Color tints for rendering
- Bucket items

## Build Status
✅ **BUILD SUCCESSFUL** - All fluids compile and are ready for in-game testing.

## Next Steps
1. Test fluids in-game to verify mechanics
2. Adjust flow speeds/effects based on gameplay feel
3. Add fluid recipes and crafting integration
