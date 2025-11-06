# Create biome files for all new dimensions
$biomes = @(
    # Dreaming Depths
    @{id="dreaming_depths_plains"; name="Dreaming Plains"; temp=0.5; downfall=0.5; fogColor=0xD8BFD8; skyColor=0x9370DB; grassColor=0xE6E6FA},
    @{id="dreaming_depths_forest"; name="Lucid Forest"; temp=0.6; downfall=0.7; fogColor=0xDDA0DD; skyColor=0x8B7AB8; grassColor=0xC8B4D8},
    # Celestine Expanse
    @{id="celestine_expanse_plains"; name="Celestine Plains"; temp=0.5; downfall=0.3; fogColor=0x87CEEB; skyColor=0x4682B4; grassColor=0xB0E0E6},
    @{id="celestine_expanse_sky_islands"; name="Sky Islands"; temp=0.4; downfall=0.2; fogColor=0xADD8E6; skyColor=0x6495ED; grassColor=0xAFE9FF},
    # Fungal Dominion
    @{id="fungal_dominion_plains"; name="Fungal Plains"; temp=0.8; downfall=0.9; fogColor=0x556B2F; skyColor=0x3B5323; grassColor=0x8FBC8F},
    @{id="fungal_dominion_forest"; name="Spore Forest"; temp=0.7; downfall=0.95; fogColor=0x6B8E23; skyColor=0x4A6619; grassColor=0x9ACD32},
    # Forgotten Archive
    @{id="forgotten_archive_plains"; name="Archive Plains"; temp=0.5; downfall=0.0; fogColor=0xDAA520; skyColor=0xB8860B; grassColor=0xF0E68C},
    @{id="forgotten_archive_library"; name="Endless Library"; temp=0.5; downfall=0.0; fogColor=0xDEB887; skyColor=0xD2691E; grassColor=0xFFD700},
    # Astral Frontier
    @{id="astral_frontier_plains"; name="Astral Plains"; temp=0.2; downfall=0.0; fogColor=0x9370DB; skyColor=0x4B0082; grassColor=0xBA55D3},
    @{id="astral_frontier_nebula"; name="Nebula Fields"; temp=0.1; downfall=0.0; fogColor=0x8B008B; skyColor=0x663399; grassColor=0xDA70D6},
    # Gloomy Caverns
    @{id="gloomy_caverns_plains"; name="Gloom Plains"; temp=0.3; downfall=0.8; fogColor=0x696969; skyColor=0x2F4F4F; grassColor=0x708090},
    @{id="gloomy_caverns_depths"; name="Dark Depths"; temp=0.2; downfall=0.9; fogColor=0x36454F; skyColor=0x1C1C1C; grassColor=0x778899}
)
$biomePath = "src\main\resources\data\elementaldimensions\worldgen\biome"
# Create directory
New-Item -ItemType Directory -Force -Path $biomePath | Out-Null
Write-Output "Creating biome files for $($biomes.Count) biomes..."
foreach ($biome in $biomes) {
    $biomeJson = @{
        has_precipitation = $true
        temperature = $biome.temp
        downfall = $biome.downfall
        effects = @{
            fog_color = $biome.fogColor
            sky_color = $biome.skyColor
            water_color = 4159204
            water_fog_color = 329011
            grass_color = $biome.grassColor
            foliage_color = $biome.grassColor
            mood_sound = @{
                sound = "minecraft:ambient.cave"
                tick_delay = 6000
                block_search_extent = 8
                offset = 2.0
            }
        }
        spawners = @{
            monster = @()
            creature = @()
            ambient = @()
            water_creature = @()
            water_ambient = @()
            misc = @()
        }
        spawn_costs = @{}
        carvers = @{
            air = @()
        }
        features = @(@(), @(), @(), @(), @(), @(), @(), @(), @(), @(), @())
    } | ConvertTo-Json -Depth 20
    $biomePath_full = Join-Path $biomePath "$($biome.id).json"
    $biomeJson | Set-Content -Path $biomePath_full -Encoding UTF8
    Write-Output "  Created biome: $($biome.name)"
}
Write-Output "`nSuccessfully created $($biomes.Count) biome files"