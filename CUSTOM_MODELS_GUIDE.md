# Elemental Dimensions - Custom 3D Models Guide

## Overview
This guide explains how to create and implement custom 3D models for Elemental Dimensions blocks, items, and entities using JSON model format and Blockbench.

---

## Table of Contents
1. [Model Format Overview](#model-format-overview)
2. [Block Models](#block-models)
3. [Item Models](#item-models)
4. [Entity Models (GeckoLib)](#entity-models-geckolib)
5. [Blockbench Integration](#blockbench-integration)
6. [Example Models](#example-models)
7. [Best Practices](#best-practices)

---

## Model Format Overview

### JSON Model Structure
Minecraft models use JSON format with the following structure:
```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "elementaldimensions:block/texture_name"
  },
  "elements": [
    {
      "from": [x1, y1, z1],
      "to": [x2, y2, z2],
      "faces": {
        "north": {"texture": "#all", "uv": [u1, v1, u2, v2]},
        "south": {"texture": "#all"},
        ...
      }
    }
  ]
}
```

### Coordinate System
- **X axis**: West (-) to East (+)
- **Y axis**: Down (-) to Up (+)
- **Z axis**: North (-) to South (+)
- **Range**: 0 to 16 units (1 block = 16 units)

---

## Block Models

### Example 1: Portal Frame (Custom Shape)
File: `src/main/resources/assets/elementaldimensions/models/block/portal_frame.json`

```json
{
  "parent": "minecraft:block/block",
  "textures": {
    "top": "elementaldimensions:block/portal_frame_top",
    "side": "elementaldimensions:block/portal_frame_side",
    "inner": "elementaldimensions:block/portal_frame_inner",
    "particle": "elementaldimensions:block/portal_frame_side"
  },
  "elements": [
    {
      "_comment": "Bottom frame piece",
      "from": [0, 0, 0],
      "to": [16, 4, 16],
      "faces": {
        "north": {"texture": "#side", "uv": [0, 12, 16, 16]},
        "south": {"texture": "#side", "uv": [0, 12, 16, 16]},
        "east": {"texture": "#side", "uv": [0, 12, 16, 16]},
        "west": {"texture": "#side", "uv": [0, 12, 16, 16]},
        "up": {"texture": "#top", "uv": [0, 0, 16, 16]},
        "down": {"texture": "#top", "uv": [0, 0, 16, 16]}
      }
    },
    {
      "_comment": "Inner column - north west",
      "from": [2, 4, 2],
      "to": [4, 14, 4],
      "faces": {
        "north": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "south": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "east": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "west": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "up": {"texture": "#inner", "uv": [0, 0, 2, 2]},
        "down": {"texture": "#inner", "uv": [0, 0, 2, 2]}
      }
    },
    {
      "_comment": "Inner column - north east",
      "from": [12, 4, 2],
      "to": [14, 14, 4],
      "faces": {
        "north": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "south": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "east": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "west": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "up": {"texture": "#inner", "uv": [0, 0, 2, 2]},
        "down": {"texture": "#inner", "uv": [0, 0, 2, 2]}
      }
    },
    {
      "_comment": "Inner column - south west",
      "from": [2, 4, 12],
      "to": [4, 14, 14],
      "faces": {
        "north": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "south": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "east": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "west": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "up": {"texture": "#inner", "uv": [0, 0, 2, 2]},
        "down": {"texture": "#inner", "uv": [0, 0, 2, 2]}
      }
    },
    {
      "_comment": "Inner column - south east",
      "from": [12, 4, 12],
      "to": [14, 14, 14],
      "faces": {
        "north": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "south": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "east": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "west": {"texture": "#inner", "uv": [0, 2, 2, 12]},
        "up": {"texture": "#inner", "uv": [0, 0, 2, 2]},
        "down": {"texture": "#inner", "uv": [0, 0, 2, 2]}
      }
    },
    {
      "_comment": "Top frame piece",
      "from": [0, 14, 0],
      "to": [16, 16, 16],
      "faces": {
        "north": {"texture": "#side", "uv": [0, 0, 16, 2]},
        "south": {"texture": "#side", "uv": [0, 0, 16, 2]},
        "east": {"texture": "#side", "uv": [0, 0, 16, 2]},
        "west": {"texture": "#side", "uv": [0, 0, 16, 2]},
        "up": {"texture": "#top", "uv": [0, 0, 16, 16]},
        "down": {"texture": "#top", "uv": [0, 0, 16, 16]}
      }
    }
  ],
  "display": {
    "thirdperson_righthand": {
      "rotation": [75, 45, 0],
      "translation": [0, 2.5, 0],
      "scale": [0.375, 0.375, 0.375]
    },
    "thirdperson_lefthand": {
      "rotation": [75, 45, 0],
      "translation": [0, 2.5, 0],
      "scale": [0.375, 0.375, 0.375]
    },
    "firstperson_righthand": {
      "rotation": [0, 45, 0],
      "scale": [0.4, 0.4, 0.4]
    },
    "firstperson_lefthand": {
      "rotation": [0, 225, 0],
      "scale": [0.4, 0.4, 0.4]
    },
    "gui": {
      "rotation": [30, 225, 0],
      "scale": [0.625, 0.625, 0.625]
    },
    "fixed": {
      "scale": [0.5, 0.5, 0.5]
    },
    "ground": {
      "translation": [0, 3, 0],
      "scale": [0.25, 0.25, 0.25]
    }
  }
}
```

### Example 2: Crystal Block (Emissive Texture)
File: `src/main/resources/assets/elementaldimensions/models/block/dreamstone_crystal.json`

```json
{
  "parent": "minecraft:block/block",
  "textures": {
    "crystal": "elementaldimensions:block/dreamstone_crystal",
    "crystal_glow": "elementaldimensions:block/dreamstone_crystal_glow",
    "particle": "elementaldimensions:block/dreamstone_crystal"
  },
  "elements": [
    {
      "_comment": "Central crystal core",
      "from": [6, 0, 6],
      "to": [10, 12, 10],
      "rotation": {"angle": 0, "axis": "y", "origin": [8, 6, 8]},
      "faces": {
        "north": {"texture": "#crystal", "uv": [0, 4, 4, 16]},
        "south": {"texture": "#crystal", "uv": [0, 4, 4, 16]},
        "east": {"texture": "#crystal", "uv": [0, 4, 4, 16]},
        "west": {"texture": "#crystal", "uv": [0, 4, 4, 16]},
        "up": {"texture": "#crystal", "uv": [6, 6, 10, 10]},
        "down": {"texture": "#crystal", "uv": [6, 6, 10, 10]}
      }
    },
    {
      "_comment": "Crystal tip",
      "from": [7, 12, 7],
      "to": [9, 16, 9],
      "faces": {
        "north": {"texture": "#crystal", "uv": [0, 0, 2, 4]},
        "south": {"texture": "#crystal", "uv": [0, 0, 2, 4]},
        "east": {"texture": "#crystal", "uv": [0, 0, 2, 4]},
        "west": {"texture": "#crystal", "uv": [0, 0, 2, 4]},
        "up": {"texture": "#crystal", "uv": [7, 7, 9, 9]}
      }
    },
    {
      "_comment": "Glowing overlay - renders with emissive lighting",
      "from": [6.01, 0, 6.01],
      "to": [9.99, 12, 9.99],
      "faces": {
        "north": {"texture": "#crystal_glow", "uv": [0, 4, 4, 16]},
        "south": {"texture": "#crystal_glow", "uv": [0, 4, 4, 16]},
        "east": {"texture": "#crystal_glow", "uv": [0, 4, 4, 16]},
        "west": {"texture": "#crystal_glow", "uv": [0, 4, 4, 16]},
        "up": {"texture": "#crystal_glow", "uv": [6, 6, 10, 10]}
      }
    }
  ]
}
```

### Blockstate File for Custom Models
File: `src/main/resources/assets/elementaldimensions/blockstates/portal_frame.json`

```json
{
  "variants": {
    "": {
      "model": "elementaldimensions:block/portal_frame"
    }
  }
}
```

---

## Item Models

### Example 1: Custom Sword Model
File: `src/main/resources/assets/elementaldimensions/models/item/inferno_sword.json`

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "elementaldimensions:item/inferno_sword",
    "layer1": "elementaldimensions:item/inferno_sword_glow"
  },
  "display": {
    "thirdperson_righthand": {
      "rotation": [0, -90, 55],
      "translation": [0, 4, 0.5],
      "scale": [0.85, 0.85, 0.85]
    },
    "thirdperson_lefthand": {
      "rotation": [0, 90, -55],
      "translation": [0, 4, 0.5],
      "scale": [0.85, 0.85, 0.85]
    },
    "firstperson_righthand": {
      "rotation": [0, -90, 25],
      "translation": [1.13, 3.2, 1.13],
      "scale": [0.68, 0.68, 0.68]
    },
    "firstperson_lefthand": {
      "rotation": [0, 90, -25],
      "translation": [1.13, 3.2, 1.13],
      "scale": [0.68, 0.68, 0.68]
    }
  }
}
```

### Example 2: 3D Item Model (Portal Key)
File: `src/main/resources/assets/elementaldimensions/models/item/portal_key.json`

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "elementaldimensions:item/portal_key_base"
  },
  "elements": [
    {
      "_comment": "Key shaft",
      "from": [7, 0, 7.5],
      "to": [9, 10, 8.5],
      "faces": {
        "north": {"texture": "#layer0", "uv": [0, 0, 2, 10]},
        "south": {"texture": "#layer0", "uv": [0, 0, 2, 10]},
        "east": {"texture": "#layer0", "uv": [0, 0, 1, 10]},
        "west": {"texture": "#layer0", "uv": [0, 0, 1, 10]},
        "up": {"texture": "#layer0", "uv": [0, 0, 2, 1]}
      }
    },
    {
      "_comment": "Key head (circular)",
      "from": [5, 10, 6.5],
      "to": [11, 14, 9.5],
      "faces": {
        "north": {"texture": "#layer0", "uv": [2, 0, 8, 4]},
        "south": {"texture": "#layer0", "uv": [2, 0, 8, 4]},
        "east": {"texture": "#layer0", "uv": [8, 0, 11, 4]},
        "west": {"texture": "#layer0", "uv": [8, 0, 11, 4]},
        "up": {"texture": "#layer0", "uv": [2, 0, 8, 3]},
        "down": {"texture": "#layer0", "uv": [2, 0, 8, 3]}
      }
    },
    {
      "_comment": "Key teeth",
      "from": [7, 1, 6.5],
      "to": [9, 4, 7.5],
      "faces": {
        "north": {"texture": "#layer0", "uv": [10, 0, 12, 3]},
        "east": {"texture": "#layer0", "uv": [10, 0, 11, 3]},
        "west": {"texture": "#layer0", "uv": [10, 0, 11, 3]}
      }
    }
  ],
  "display": {
    "gui": {
      "rotation": [30, 45, 0],
      "translation": [0, 0, 0],
      "scale": [1, 1, 1]
    },
    "ground": {
      "rotation": [0, 0, 0],
      "translation": [0, 2, 0],
      "scale": [0.5, 0.5, 0.5]
    },
    "fixed": {
      "rotation": [0, 180, 0],
      "scale": [1, 1, 1]
    },
    "thirdperson_righthand": {
      "rotation": [0, 90, 55],
      "translation": [0, 1, 0.5],
      "scale": [0.85, 0.85, 0.85]
    },
    "firstperson_righthand": {
      "rotation": [0, -90, 25],
      "translation": [1.13, 3.2, 1.13],
      "scale": [0.68, 0.68, 0.68]
    }
  }
}
```

---

## Entity Models (GeckoLib)

### GeckoLib Model Structure
GeckoLib entities use `.geo.json` models created in Blockbench.

**File Structure:**
```
src/main/resources/assets/elementaldimensions/
├── geo/
│   ├── pyros.geo.json
│   ├── leviathan.geo.json
│   └── ...
├── textures/entity/
│   ├── pyros.png
│   ├── leviathan.png
│   └── ...
└── animations/
    ├── pyros.animation.json
    ├── leviathan.animation.json
    └── ...
```

### Example: Boss Entity Model Registration
**Java Code** (`PyrosEntity.java`):
```java
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PyrosEntity extends HostileEntity implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }
    
    private PlayState predicate(AnimationState<PyrosEntity> state) {
        if(state.isMoving()) {
            state.getController().setAnimation(RawAnimation.begin().then("animation.pyros.walk", Animation.LoopType.LOOP));
        } else {
            state.getController().setAnimation(RawAnimation.begin().then("animation.pyros.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
```

**Model File** (`PyrosModel.java`):
```java
import software.bernie.geckolib.model.GeoModel;
import net.minecraft.util.Identifier;

public class PyrosModel extends GeoModel<PyrosEntity> {
    @Override
    public Identifier getModelResource(PyrosEntity animatable) {
        return new Identifier("elementaldimensions", "geo/pyros.geo.json");
    }
    
    @Override
    public Identifier getTextureResource(PyrosEntity animatable) {
        return new Identifier("elementaldimensions", "textures/entity/pyros.png");
    }
    
    @Override
    public Identifier getAnimationResource(PyrosEntity animatable) {
        return new Identifier("elementaldimensions", "animations/pyros.animation.json");
    }
}
```

**Renderer** (`PyrosRenderer.java`):
```java
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class PyrosRenderer extends GeoEntityRenderer<PyrosEntity> {
    public PyrosRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PyrosModel());
        this.shadowRadius = 1.5f;
    }
}
```

---

## Blockbench Integration

### Creating Models in Blockbench

**1. Install Blockbench:**
- Download from: https://www.blockbench.net/
- Free, open-source 3D modeling tool

**2. For Block/Item Models:**
- File → New → Java Block/Item
- Create model using cubes
- Export → Save Model (JSON)
- Place in `/models/block/` or `/models/item/`

**3. For Entity Models (GeckoLib):**
- File → New → GeckoLib Animated Model
- Create model with bones and animations
- Export → Export GeckoLib Model
- Place `.geo.json` in `/geo/`
- Export textures to `/textures/entity/`
- Export animations to `/animations/`

### Blockbench Tips

**UV Mapping:**
- Use UV Editor to map textures precisely
- Enable "Auto UV" for quick mapping
- Use "Per-face UV" for complex models

**Animations (GeckoLib):**
- Create bone structure (parent-child hierarchy)
- Animate bones with keyframes
- Use easing for smooth transitions
- Export all animations to single `.animation.json`

**Testing:**
- Use Blockbench preview to test animations
- Check model from all angles
- Verify texture alignment

---

## Best Practices

### Performance Optimization
1. **Minimize faces**: Remove unseen faces (inside faces)
2. **Reduce elements**: Combine cubes where possible
3. **Texture atlas**: Use texture atlases instead of multiple files
4. **LOD models**: Create simplified models for distant rendering

### Model Guidelines
- **Maximum complexity**: 500-1000 polygons per entity
- **Block models**: Keep under 100 elements for performance
- **Texture resolution**: 16×16 for blocks, 64×64 for entities
- **Transparency**: Use carefully (performance impact)

### File Naming Conventions
```
Block models:      block_name.json
Item models:       item_name.json
Entity geo:        entity_name.geo.json
Entity texture:    entity_name.png
Entity animation:  entity_name.animation.json
```

### Display Transform Values
Common display positions:
- **GUI**: `rotation: [30, 225, 0], scale: [0.625, 0.625, 0.625]`
- **Ground**: `translation: [0, 3, 0], scale: [0.25, 0.25, 0.25]`
- **First person**: `rotation: [0, -90, 25], translation: [1.13, 3.2, 1.13]`
- **Third person**: `rotation: [0, -90, 55], translation: [0, 4, 0.5]`

---

## Testing Your Models

### In-Game Testing Commands
```bash
# Give yourself the item to test
/give @s elementaldimensions:portal_frame

# Summon entity to test model
/summon elementaldimensions:pyros ~ ~ ~

# Set block to test model
/setblock ~ ~ ~ elementaldimensions:dreamstone_crystal
```

### Debug Mode
Enable F3+B to see hitboxes and verify model bounds.

---

## Additional Resources

- **Blockbench Documentation**: https://www.blockbench.net/wiki/
- **GeckoLib Wiki**: https://github.com/bernie-g/geckolib/wiki
- **Minecraft Model Format**: https://minecraft.wiki/w/Model
- **Example Models**: See `/models/` directory for existing examples

---

## Troubleshooting

### Model Not Showing
1. Check file path matches registration
2. Verify JSON syntax (use JSON validator)
3. Ensure textures exist at specified paths
4. Check console for model loading errors

### Texture Issues
1. Verify texture path in model JSON
2. Check texture file exists
3. Ensure texture format is PNG
4. Verify UV coordinates are correct

### Animation Not Playing
1. Check animation controller registration
2. Verify animation name matches JSON file
3. Ensure AnimationController is properly set up
4. Check for animation state conditions

---

**Last Updated**: 2024-01-XX
**Minecraft Version**: 1.21.1
**GeckoLib Version**: 4.5+
