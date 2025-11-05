# Elemental Dimensions - Minecraft 1.21.10

This version of Elemental Dimensions is built for **Minecraft 1.21.10** using **GeckoLib 5.3-alpha-3**.

## Requirements
- Minecraft 1.21.10
- Fabric Loader 0.16.9+
- Fabric API 0.138.0+1.21.10
- GeckoLib 5.3-alpha-3
- Cloth Config 15.0.0+

## GeckoLib Implementation
This version uses GeckoLib 5.x (alpha) API structure:
- Entities implement `GeoEntity` interface
- Renderers use covariant type parameters: `GeoEntityRenderer<EntityType, R extends EntityRenderState & GeoRenderState>`
- Package structure: `software.bernie.geckolib.animatable.*` and `software.bernie.geckolib.animation.*`
- AnimationController uses new `AnimationStateHandler` syntax

### Key Differences from GeckoLib 4.x
1. **Renderer Type Signature:**
   ```java
   // GeckoLib 5.x (1.21.10)
   public class MyRenderer<R extends EntityRenderState & GeoRenderState> 
       extends GeoEntityRenderer<MyEntity, R>
   
   // GeckoLib 4.x (1.21.1)
   public class MyRenderer extends GeoEntityRenderer<MyEntity>
   ```

2. **Animation Controller:**
   ```java
   // GeckoLib 5.x
   new AnimationController<>(this, "controller", state -> {
       return state.isMoving() ? 
           state.setAndContinue(RawAnimation.begin().thenLoop("walk")) : 
           state.setAndContinue(RawAnimation.begin().thenLoop("idle"));
   })
   
   // GeckoLib 4.x
   new AnimationController<>(this, "controller", 0, state -> {
       return state.setAndContinue(RawAnimation.begin().thenLoop("idle"));
   })
   ```

3. **Entity Interface:**
   - GeckoLib 5.x: `implements GeoEntity`
   - GeckoLib 4.x: `implements GeoAnimatable`

## Build Instructions
```bash
.\gradlew build
```

The built JAR will be in `build/libs/elemental-dimensions-1.0.0.jar`

## Features
- 6 unique dimensions (Firelands, Aquatica, Terra Depths, Skyreach Peaks, Celestial, Void Realm)
- 32 custom animated entities using GeckoLib 5
- 6 epic boss fights
- Custom blocks, items, and structures
- Complete advancement system
- Lunar Client compatible

## Status
✅ GeckoLib 5.x implementation complete  
✅ All renderers updated with covariant type parameters  
✅ All entities use new AnimationStateHandler  
⚠️ Alpha version of GeckoLib (5.3-alpha-3)

## Documentation
See the main repository README for full feature list and gameplay guide.

## Credits
Thanks to the GeckoLib Discord community for help with the GeckoLib 5 migration!
