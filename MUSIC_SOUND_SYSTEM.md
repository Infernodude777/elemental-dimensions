# Elemental Dimensions - Music & Sound System

## Overview
This document describes the music and sound system for the Elemental Dimensions mod. Custom music tracks and ambient sounds enhance immersion in each dimension.

## Sound Structure

### Sound Event Registration
Sound events should be registered in a `ModSounds.java` class:
```java
public class ModSounds {
    // Dimension ambient music
    public static final Identifier INFERNO_MUSIC = new Identifier("elementaldimensions", "music.inferno");
    public static final Identifier AQUATIC_MUSIC = new Identifier("elementaldimensions", "music.aquatic");
    public static final Identifier SKYBOUND_MUSIC = new Identifier("elementaldimensions", "music.skybound");
    // ... etc for all dimensions
    
    // Boss music
    public static final Identifier BOSS_MUSIC = new Identifier("elementaldimensions", "music.boss");
    
    // Ambient sounds
    public static final Identifier PORTAL_AMBIENT = new Identifier("elementaldimensions", "ambient.portal");
    
    public static void initialize() {
        // Register sound events with Fabric API
        Registry.register(Registries.SOUND_EVENT, INFERNO_MUSIC, 
            SoundEvent.of(INFERNO_MUSIC));
        // ... register all sounds
    }
}
```

## Sound File Structure

### Directory Layout
```
src/main/resources/assets/elementaldimensions/sounds/
├── music/
│   ├── dimension/
│   │   ├── inferno.ogg
│   │   ├── aquatic.ogg
│   │   ├── skybound.ogg
│   │   ├── terran.ogg
│   │   ├── celestial.ogg
│   │   ├── void.ogg
│   │   ├── dreaming_depths.ogg
│   │   ├── celestine_expanse.ogg
│   │   ├── fungal_dominion.ogg
│   │   ├── forgotten_archive.ogg
│   │   ├── astral_frontier.ogg
│   │   └── gloomy_caverns.ogg
│   └── boss/
│       └── boss_battle.ogg
├── ambient/
│   ├── portal_hum.ogg
│   ├── dimension_whispers.ogg
│   └── crystal_chime.ogg
├── entity/
│   ├── boss/
│   │   ├── pyros_roar.ogg
│   │   ├── leviathan_screech.ogg
│   │   └── ... (other boss sounds)
│   └── mob/
│       └── ... (mob sounds)
└── block/
    └── ... (block interaction sounds)
```

## Sound Definitions File

### sounds.json
Create `src/main/resources/assets/elementaldimensions/sounds.json`:
```json
{
  "music.inferno": {
    "category": "music",
    "sounds": [
      {
        "name": "elementaldimensions:music/dimension/inferno",
        "stream": true,
        "volume": 0.4
      }
    ]
  },
  "music.aquatic": {
    "category": "music",
    "sounds": [
      {
        "name": "elementaldimensions:music/dimension/aquatic",
        "stream": true,
        "volume": 0.4
      }
    ]
  },
  "music.boss": {
    "category": "music",
    "sounds": [
      {
        "name": "elementaldimensions:music/boss/boss_battle",
        "stream": true,
        "volume": 0.6
      }
    ]
  },
  "ambient.portal": {
    "category": "ambient",
    "sounds": [
      {
        "name": "elementaldimensions:ambient/portal_hum",
        "stream": false,
        "volume": 0.3
      }
    ],
    "subtitle": "sound.elementaldimensions.portal.ambient"
  }
}
```

## Dimension-Specific Music

### Playing Music on Dimension Entry
Use a mixin or dimension tick event to start music:
```java
@Mixin(ClientPlayNetworkHandler.class)
public class DimensionMusicMixin {
    @Inject(method = "onPlayerRespawn", at = @At("RETURN"))
    private void onDimensionChange(PlayerRespawnS2CPacket packet, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null) {
            RegistryKey<World> dimension = client.world.getRegistryKey();
            
            if (dimension.getValue().equals(new Identifier("elementaldimensions", "inferno_realm"))) {
                client.getMusicManager().play(ModSounds.INFERNO_MUSIC);
            } else if (dimension.getValue().equals(new Identifier("elementaldimensions", "aquatic_realm"))) {
                client.getMusicManager().play(ModSounds.AQUATIC_MUSIC);
            }
            // ... etc for all dimensions
        }
    }
}
```

## Boss Music System

### Triggering Boss Music
In boss entity classes, trigger music when player enters range:
```java
public class PyrosEntity extends HostileEntity {
    @Override
    public void tick() {
        super.tick();
        
        if (world.isClient) {
            PlayerEntity nearestPlayer = world.getClosestPlayer(this, 32.0);
            if (nearestPlayer != null) {
                MinecraftClient.getInstance().getMusicManager()
                    .play(ModSounds.BOSS_MUSIC);
            }
        }
    }
}
```

## Ambient Sounds

### Portal Ambient Sound
Add looping sound to portal blocks:
```java
public class PortalBlock extends Block {
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(100) == 0) {
            world.playSound(
                pos.getX(), pos.getY(), pos.getZ(),
                SoundEvent.of(ModSounds.PORTAL_AMBIENT),
                SoundCategory.BLOCKS,
                0.3F, 1.0F,
                false
            );
        }
    }
}
```

## Music Specifications

### Dimension Music Themes
| Dimension | Music Style | Tempo | Instrumentation |
|-----------|-------------|-------|-----------------|
| **Inferno Realm** | Aggressive, intense | Fast | Drums, distorted strings, brass |
| **Aquatic Realm** | Ethereal, flowing | Slow | Strings, harp, chimes |
| **Skybound Realm** | Light, airy | Medium | Flutes, bells, soft pads |
| **Terran Realm** | Heavy, grounded | Slow | Bass, percussion, low strings |
| **Celestial Realm** | Mysterious, cosmic | Medium | Choir, synthesizers, celesta |
| **Void Realm** | Dark, ominous | Very slow | Deep drones, dissonant tones |
| **Dreaming Depths** | Surreal, dreamlike | Variable | Music box, reversed sounds |
| **Celestine Expanse** | Uplifting, radiant | Medium-fast | Orchestra, bright brass |
| **Fungal Dominion** | Organic, strange | Slow | Woodwinds, nature samples |
| **Forgotten Archive** | Scholarly, ancient | Slow | Harpsichord, strings, whispers |
| **Astral Frontier** | Vast, expansive | Slow | Synth pads, ambient textures |
| **Gloomy Caverns** | Melancholic, echoing | Very slow | Echoed piano, reverb strings |

### Boss Music
- **Tempo**: Fast, intense
- **Style**: Orchestral with heavy percussion
- **Duration**: 3-4 minutes looping
- **Dynamics**: Build-up intro, intense main section

## Sound Format Requirements

### File Specifications
- **Format**: OGG Vorbis
- **Sample Rate**: 44100 Hz (44.1 kHz)
- **Bit Rate**: 128-192 kbps for music, 64-96 kbps for effects
- **Channels**: Stereo for music, mono for most sound effects
- **Volume**: Normalized to -6 dB peak to prevent clipping

### Music Track Lengths
- **Dimension Ambient**: 2-4 minutes (looping seamlessly)
- **Boss Battle**: 3-4 minutes
- **Sound Effects**: 0.5-3 seconds

## Implementation Checklist

### Phase 1: Core Setup
- [ ] Create ModSounds class
- [ ] Create sounds.json file
- [ ] Add sound event registration
- [ ] Test basic sound playback

### Phase 2: Dimension Music
- [ ] Compose/acquire 11 dimension music tracks
- [ ] Convert to OGG format
- [ ] Add to sounds folder
- [ ] Implement dimension detection
- [ ] Test music playback on dimension entry

### Phase 3: Boss Music
- [ ] Compose/acquire boss battle music
- [ ] Implement boss music trigger
- [ ] Add music stop condition (boss defeated/player dies)
- [ ] Test with all boss entities

### Phase 4: Ambient Sounds
- [ ] Create portal ambient sounds
- [ ] Add block interaction sounds
- [ ] Implement environmental ambience
- [ ] Balance volume levels

### Phase 5: Entity Sounds
- [ ] Add boss roars/attack sounds
- [ ] Add mob ambient sounds
- [ ] Add entity death sounds
- [ ] Balance sound mix

## Placeholder Sound Sources

Until custom music is composed, you can use:
1. **Free Music Archive** (freemusicarchive.org) - CC-licensed music
2. **Incompetech** (incompetech.com) - Royalty-free tracks
3. **OpenGameArt** (opengameart.org) - Game music and sounds
4. **Freesound** (freesound.org) - Sound effects library

**Important**: Ensure all sounds have appropriate licenses for distribution!

## Testing Commands

```bash
# Test sound playback in-game
/playsound elementaldimensions:music.inferno music @a ~ ~ ~ 0.4 1.0

# Stop all music
/stopsound @a music

# Test ambient sound
/playsound elementaldimensions:ambient.portal ambient @a ~ ~ ~ 0.3 1.0
```

## Volume Balance Guidelines

- **Dimension Music**: 0.3-0.4 volume (background)
- **Boss Music**: 0.5-0.6 volume (prominent but not overwhelming)
- **Ambient Sounds**: 0.2-0.3 volume (subtle atmosphere)
- **Entity Sounds**: 0.4-0.7 volume (noticeable)
- **Block Sounds**: 0.5-0.8 volume (player interaction feedback)

## Future Enhancements

- [ ] Dynamic music system (intensity changes with danger)
- [ ] Music layers that add/remove based on context
- [ ] Positional audio for distant sounds
- [ ] Weather-specific ambient sounds
- [ ] Time-of-day music variations
- [ ] Combat intensity music transitions

---

**Note**: This is a framework document. Actual implementation requires composing or acquiring music tracks and coding the sound system integration.
