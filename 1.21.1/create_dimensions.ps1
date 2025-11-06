# Create dimension and dimension_type files for all 6 new dimensions
$dimensions = @(
    @{id="dreaming_depths"; name="Dreaming Depths"; effects="elementaldimensions:dreaming_depths"},
    @{id="celestine_expanse"; name="Celestine Expanse"; effects="elementaldimensions:celestine_expanse"},
    @{id="fungal_dominion"; name="Fungal Dominion"; effects="elementaldimensions:fungal_dominion"},
    @{id="forgotten_archive"; name="Forgotten Archive"; effects="elementaldimensions:forgotten_archive"},
    @{id="astral_frontier"; name="Astral Frontier"; effects="elementaldimensions:astral_frontier"},
    @{id="gloomy_caverns"; name="Gloomy Caverns"; effects="elementaldimensions:gloomy_caverns"}
)
$dimensionPath = "src\main\resources\data\elementaldimensions\dimension"
$dimensionTypePath = "src\main\resources\data\elementaldimensions\dimension_type"
# Create directories
New-Item -ItemType Directory -Force -Path $dimensionPath | Out-Null
New-Item -ItemType Directory -Force -Path $dimensionTypePath | Out-Null
Write-Output "Creating dimension files for $($dimensions.Count) dimensions..."
foreach ($dim in $dimensions) {
    # Create dimension JSON
    $dimensionJson = @{
        type = "elementaldimensions:$($dim.id)"
        generator = @{
            type = "minecraft:noise"
            biome_source = @{
                type = "minecraft:multi_noise"
                biomes = @(
                    @{
                        biome = "elementaldimensions:$($dim.id)_plains"
                        parameters = @{
                            temperature = 0.0
                            humidity = 0.0
                            continentalness = 0.0
                            erosion = 0.0
                            weirdness = 0.0
                            depth = 0.0
                            offset = 0.0
                        }
                    }
                )
            }
            settings = "minecraft:overworld"
        }
    } | ConvertTo-Json -Depth 20
    $dimPath = Join-Path $dimensionPath "$($dim.id).json"
    $dimensionJson | Set-Content -Path $dimPath -Encoding UTF8
    # Create dimension_type JSON
    $dimensionTypeJson = @{
        ultrawarm = $false
        natural = $true
        piglin_safe = $false
        respawn_anchor_works = $false
        bed_works = $false
        has_raids = $false
        has_skylight = $true
        has_ceiling = $false
        coordinate_scale = 1.0
        ambient_light = 0.0
        logical_height = 384
        effects = $dim.effects
        infiniburn = "#minecraft:infiniburn_overworld"
        min_y = -64
        height = 384
        monster_spawn_light_level = 0
        monster_spawn_block_light_limit = 0
    } | ConvertTo-Json -Depth 10
    $dimTypePath = Join-Path $dimensionTypePath "$($dim.id).json"
    $dimensionTypeJson | Set-Content -Path $dimTypePath -Encoding UTF8
    Write-Output "  Created dimension files for $($dim.name)"
}
Write-Output "`nSuccessfully created $($dimensions.Count) dimension files"
Write-Output "Successfully created $($dimensions.Count) dimension_type files"