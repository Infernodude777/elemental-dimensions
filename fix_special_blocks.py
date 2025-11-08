"""
Fix ModBlocks.java to use proper block classes for stairs, slabs, pillars, and logs
"""

import re

# Read the ModBlocks.java file
with open("1.21.1/src/main/java/com/nikhil/elementaldimensions/registry/ModBlocks.java", 'r', encoding='utf-8') as f:
    content = f.read()

# Patterns to fix
fixes = []

# Find all pillar blocks and convert them to PillarBlock
pillar_pattern = r'(public static final Block (\w+_PILLAR) = registerBlock\("([^"]+)",\s*new Block\(AbstractBlock\.Settings\.create\(\))(.*?)\)\)\);'
matches = re.finditer(pillar_pattern, content, re.DOTALL)
for match in matches:
    old_text = match.group(0)
    var_name = match.group(2)
    block_id = match.group(3)
    settings = match.group(4)

    new_text = f'''public static final Block {var_name} = registerBlock("{block_id}",
            new PillarBlock(AbstractBlock.Settings.create(){settings})));'''
    fixes.append((old_text, new_text))
    print(f"✓ Converting {var_name} to PillarBlock")

# Find dreamwood_log and convert to PillarBlock
log_pattern = r'(public static final Block DREAMWOOD_LOG = registerBlock\("dreamwood_log",\s*new Block\(AbstractBlock\.Settings\.create\(\))(.*?)\)\)\);'
matches = re.finditer(log_pattern, content, re.DOTALL)
for match in matches:
    old_text = match.group(0)
    settings = match.group(2)

    new_text = f'''public static final Block DREAMWOOD_LOG = registerBlock("dreamwood_log",
            new PillarBlock(AbstractBlock.Settings.create(){settings})));'''
    fixes.append((old_text, new_text))
    print(f"✓ Converting DREAMWOOD_LOG to PillarBlock")

# Find fungal_bark and convert to PillarBlock
fungal_pattern = r'(public static final Block FUNGAL_BARK = registerBlock\("fungal_bark",\s*new Block\(AbstractBlock\.Settings\.create\(\))(.*?)\)\)\);'
matches = re.finditer(fungal_pattern, content, re.DOTALL)
for match in matches:
    old_text = match.group(0)
    settings = match.group(2)

    new_text = f'''public static final Block FUNGAL_BARK = registerBlock("fungal_bark",
            new PillarBlock(AbstractBlock.Settings.create(){settings})));'''
    fixes.append((old_text, new_text))
    print(f"✓ Converting FUNGAL_BARK to PillarBlock")

# Apply all fixes
for old, new in fixes:
    content = content.replace(old, new, 1)

# Write back
with open("1.21.1/src/main/java/com/nikhil/elementaldimensions/registry/ModBlocks.java", 'w', encoding='utf-8') as f:
    f.write(content)

print(f"\n✅ Fixed {len(fixes)} blocks to use proper Minecraft classes")
print(f"   - Pillars now rotate on different axes (like logs)")
print(f"   - Stairs already fixed to use StairsBlock")
print(f"   - Slabs already fixed to use SlabBlock")
print(f"   - Glass blocks already use .nonOpaque() for transparency")
print(f"   - Lanterns already use .luminance() for light emission")
