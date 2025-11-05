# GeckoLib Migration Guide

## Project Status

### Backup Configuration (MC 1.21.10 + GeckoLib 5.3-alpha-3)
- **Files**: `gradle.properties.1.21.10-geckolib5`, `build.gradle.1.21.10-geckolib5`
- **Status**: GeckoLib 5.x alpha has undocumented/incomplete API
- **Issues**: AnimationController constructor undefined, GeoEntityRenderer type parameter mismatches

### Current Configuration (MC 1.21.1 + GeckoLib 4.8.2)
- **Minecraft Version**: 1.21.1
- **GeckoLib Version**: 4.8.2 (STABLE)
- **Repository**: Cloudsmith (https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/)
- **Status**: Stable, documented API

## GeckoLib 4.x API (MC 1.21.1)

### Package Structure
```java
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.Animation.LoopType;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
```

### Entity Implementation
```java
public class ExampleEntity extends HostileEntity implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().then("walk", LoopType.LOOP));
            }
            return state.setAndContinue(RawAnimation.begin().then("idle", LoopType.LOOP));
        }));
    }
    
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
```

### Renderer Implementation
```java
public class ExampleRenderer extends GeoEntityRenderer<ExampleEntity> {
    public ExampleRenderer(EntityRendererFactory.Context context) {
        super(context, new ExampleModel());
    }
    
    public static class ExampleModel extends GeoModel<ExampleEntity> {
        @Override
        public Identifier getModelResource(ExampleEntity entity) {
            return Identifier.of("modid", "geo/entity/example.geo.json");
        }
        
        @Override
        public Identifier getTextureResource(ExampleEntity entity) {
            return Identifier.of("modid", "textures/entity/example.png");
        }
        
        @Override
        public Identifier getAnimationResource(ExampleEntity entity) {
            return Identifier.of("modid", "animations/entity/example.animation.json");
        }
    }
}
```

## GeckoLib 5.x API (MC 1.21.10) - ALPHA/UNDOCUMENTED

### Package Structure (Discovered)
```java
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animatable.processing.AnimationState;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
```

### Known Issues
1. **AnimationController Constructor**: No working constructor signature found
   - Tried: `new AnimationController<>(entity, lambda)`
   - Tried: `new AnimationController<>(entity, name, lambda)`
   - Tried: `new AnimationController<>(entity, name, tickOffset, lambda)`
   - All result in "constructor undefined" errors

2. **GeoEntityRenderer Type Parameters**: Requires `<T, R extends EntityRenderState & GeoRenderState>`
   - Java doesn't support intersection types in generic instantiation
   - Mixin-based interface at runtime, compilation issue

3. **No Documentation**: Wiki only covers GeckoLib 3 and 4

## Migration Steps (GeckoLib 5.x → 4.x)

### Configuration Files ✅
- [x] gradle.properties: MC 1.21.10 → 1.21.1, GeckoLib 5.3-alpha-3 → 4.8.2
- [x] build.gradle: Modrinth Maven → Cloudsmith repository
- [x] fabric.mod.json: minecraft ~1.21.10 → ~1.21.1, geckolib >=5.3.0 → >=4.8.0

### Code Changes Needed
- [ ] Entity imports: Update package paths (geckolib.animatable.* → geckolib.core.*)
- [ ] Renderer imports: Update package paths
- [ ] AnimationController: Update to working GeckoLib 4.x constructor
- [ ] GeoModel: Verify method signatures
- [ ] Test compilation

## Files Modified
1. c:\Users\Nikhil\Desktop\elemental_dimensions\gradle.properties
2. c:\Users\Nikhil\Desktop\elemental_dimensions\build.gradle
3. c:\Users\Nikhil\Desktop\elemental_dimensions\src\main\resources\fabric.mod.json
4. All 32 entity files (imports + AnimationController)
5. All 32 renderer files (imports)

## Next Steps
1. Wait for Gradle dependency refresh
2. Update entity imports for GeckoLib 4.x
3. Update renderer imports for GeckoLib 4.x
4. Fix AnimationController constructors
5. Test compilation
6. Build final JAR
