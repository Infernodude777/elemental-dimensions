# Elemental Dimensions - Command Reference

## Player Commands (No Permission Required)

### Dimension Teleportation
- `/fire` - Teleport to Inferno Realm (Pyros boss)
- `/water` - Teleport to Aquatic Realm (Leviathan boss)
- `/earth` - Teleport to Terran Realm (Goliath boss)
- `/air` - Teleport to Skybound Realm (Zephyros boss)
- `/celestial` - Teleport to Celestial Realm (Primarch boss)
- `/void` - Teleport to Void Realm (The Void Archon boss)
- `/dream` - Teleport to Dreaming Depths (The Dreamer boss)
- `/fungal` - Teleport to Fungal Dominion (The Overmind boss)
- `/archive` - Teleport to Forgotten Archive (The Librarian boss)
- `/astral` - Teleport to Astral Frontier (The Void Archon boss)
- `/gloom` - Teleport to Gloomy Caverns (The Echo King boss)

### Information
- `/guide` - Receive a written book with complete mod guide (14 pages)

---

## Operator Commands (Permission Level 2 Required)

### Item Management
- `/getall` - Gives the player all mod items:
  - 4 Essences (Fire, Water, Earth, Air)
  - 4 Hearts (Inferno, Aquatic, Terran, Skybound)
  - 11 Boss spawn eggs
  - 24 Regular mob spawn eggs
  - **Total: 43 items**

- `/spawnallmobs` - Spawns all 11 bosses in a circle around the player:
  - Pyros (Fire)
  - Leviathan (Water)
  - Goliath (Earth)
  - Zephyros (Air)
  - Primarch (Celestial)
  - The Dreamer (Dream)
  - The Radiant Monarch (Sky)
  - The Overmind (Fungal)
  - The Librarian (Archive)
  - The Void Archon (Astral)
  - The Echo King (Gloom)
  - ⚠️ **WARNING**: May cause severe lag!

---

## How to Use Commands

### In-Game:
1. Press `T` or `/` to open chat
2. Type the command (e.g., `/guide`)
3. Press `Enter`

### Setting Yourself as Operator (Single Player):
1. Open the world to LAN
2. Enable cheats
3. Use `/op YourUsername`

### Setting Operator (Server):
1. Open server console
2. Type `op YourUsername`
3. Or edit `ops.json` in server files

---

## Command Feedback

All commands provide feedback messages:

### Success Messages:
- ✦ "You have received the Elemental Dimensions Guide!" (green/gold)
- ✦ "Given 43 different items to PlayerName!" (gold)
- ✦ "Spawned 11 bosses around PlayerName!" (gold)
- "Teleporting to [Dimension Name]..." (dimension-colored)

### Error Messages:
- "Your inventory is full! Clear a slot and try again." (red)
- "Error: Command must be run by a player!" (red)

---

## Dimension Command Colors

Each dimension command shows a unique color:
- `/fire` - Red
- `/water` - Blue
- `/earth` - Green
- `/air` - White
- `/celestial` - Purple
- `/void` - Dark Gray
- `/dream` - Light Purple
- `/fungal` - Yellow
- `/archive` - Gold
- `/astral` - Light Purple
- `/gloom` - Gray

---

## Tips

1. **Use `/guide` first** to learn about all dimensions
2. **Prepare before teleporting** - some dimensions are hostile
3. **Use `/getall` in creative mode** for testing
4. **Don't use `/spawnallmobs`** unless you have a powerful computer
5. **Return home** by dying or using `/spawn` (if available)

---

## Troubleshooting

### "Unknown command" error:
- Make sure the mod is installed correctly
- Check that you're running Minecraft 1.21.1 with Fabric

### "You do not have permission" error:
- You need operator permissions for `/getall` and `/spawnallmobs`
- Contact server admin or enable cheats in single player

### Dimension teleport not working:
- Check that the dimension files are in the correct folder
- Look for errors in the Minecraft log
- Some dimensions may not be fully implemented yet

---

## Planned Future Commands (Not Yet Implemented)

These commands may be added in future versions:
- `/ed help` - Show all commands
- `/ed info <dimension>` - Get dimension information
- `/ed summon <boss>` - Spawn a specific boss
- `/ed give <item> [amount]` - Give specific items

---

Last Updated: Session 9
Total Commands: 15 (11 teleports + 1 guide + 2 admin + 1 item spawn system)
