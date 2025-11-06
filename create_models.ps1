# Script to create all GeckoLib geometry and animation JSON files
$entities = @(
    @{Name="ember_serpent"; DisplayName="Ember Serpent"; Type="serpent"},
    @{Name="pyre_howler"; DisplayName="Pyre Howler"; Type="quadruped"},
    @{Name="magma_brute"; DisplayName="Magma Brute"; Type="biped"},
    @{Name="cinder_elemental"; DisplayName="Cinder Elemental"; Type="biped"},
    @{Name="abyss_crab"; DisplayName="Abyss Crab"; Type="crab"},
    @{Name="tide_siren"; DisplayName="Tide Siren"; Type="biped"},
    @{Name="pressure_ray"; DisplayName="Pressure Ray"; Type="ray"},
    @{Name="bubble_wisp"; DisplayName="Bubble Wisp"; Type="wisp"},
    @{Name="coral_guardian"; DisplayName="Coral Guardian"; Type="biped"},
    @{Name="stone_golem"; DisplayName="Stone Golem"; Type="golem"},
    @{Name="moss_beast"; DisplayName="Moss Beast"; Type="quadruped"},
    @{Name="crystal_mite"; DisplayName="Crystal Mite"; Type="spider"},
    @{Name="root_spider"; DisplayName="Root Spider"; Type="spider"},
    @{Name="cave_lurker"; DisplayName="Cave Lurker"; Type="biped"},
    @{Name="wind_hawk"; DisplayName="Wind Hawk"; Type="bird"},
    @{Name="cloud_sentinel"; DisplayName="Cloud Sentinel"; Type="golem"},
    @{Name="thunder_hawk"; DisplayName="Thunder Hawk"; Type="bird"},
    @{Name="sky_wisp"; DisplayName="Sky Wisp"; Type="wisp"},
    @{Name="petal_sprite"; DisplayName="Petal Sprite"; Type="wisp"},
    @{Name="celestial_enderman"; DisplayName="Celestial Enderman"; Type="enderman"},
    @{Name="starlight_phantom"; DisplayName="Starlight Phantom"; Type="phantom"},
    @{Name="void_shulker"; DisplayName="Void Shulker"; Type="shulker"},
    @{Name="nebula_wraith"; DisplayName="Nebula Wraith"; Type="wraith"},
    @{Name="shadow_wraith"; DisplayName="Shadow Wraith"; Type="wraith"},
    @{Name="void_stalker"; DisplayName="Void Stalker"; Type="biped"},
    @{Name="fire_titan"; DisplayName="Fire Titan"; Type="boss_biped"},
    @{Name="abyss_leviathan"; DisplayName="Abyss Leviathan"; Type="boss_serpent"},
    @{Name="stone_colossus"; DisplayName="Stone Colossus"; Type="boss_golem"},
    @{Name="wind_seraph"; DisplayName="Wind Seraph"; Type="boss_flying"},
    @{Name="elemental_primarch"; DisplayName="Elemental Primarch"; Type="boss_biped"},
    @{Name="void_lord"; DisplayName="Void Lord"; Type="boss_biped"}
)
# Create directories
New-Item -ItemType Directory -Force -Path "src\main\resources\assets\elementaldimensions\geo\entity" | Out-Null
New-Item -ItemType Directory -Force -Path "src\main\resources\assets\elementaldimensions\animations\entity" | Out-Null
foreach ($entity in $entities) {
    $name = $entity.Name
    $displayName = $entity.DisplayName
    $type = $entity.Type
    # Determine bone structure and dimensions based on type
    $bones = switch ($type) {
        "biped" { @("body", "head", "arm_right", "arm_left", "leg_right", "leg_left") }
        "quadruped" { @("body", "head", "leg_front_right", "leg_front_left", "leg_back_right", "leg_back_left") }
        "serpent" { @("head", "body", "body2", "body3", "body4", "tail") }
        "spider" { @("body", "head", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6", "leg7", "leg8") }
        "bird" { @("body", "head", "wing_right", "wing_left", "leg_right", "leg_left") }
        "wisp" { @("core", "aura1", "aura2", "aura3") }
        "wraith" { @("body", "head", "arm_right", "arm_left", "tail") }
        "golem" { @("body", "head", "arm_right", "arm_left", "leg_right", "leg_left") }
        "crab" { @("body", "claw_right", "claw_left", "leg1", "leg2", "leg3", "leg4") }
        "ray" { @("body", "wing_right", "wing_left", "tail") }
        "enderman" { @("body", "head", "arm_right", "arm_left", "leg_right", "leg_left") }
        "phantom" { @("body", "head", "wing_right", "wing_left") }
        "shulker" { @("base", "lid", "head") }
        "boss_biped" { @("body", "head", "arm_right", "arm_left", "leg_right", "leg_left") }
        "boss_serpent" { @("head", "body", "body2", "body3", "body4", "body5", "body6", "tail") }
        "boss_golem" { @("body", "head", "arm_right", "arm_left", "leg_right", "leg_left") }
        "boss_flying" { @("body", "head", "wing_right", "wing_left") }
        default { @("body", "head") }
    }
    # Create geometry JSON
    $geoJson = @{
        format_version = "1.12.0"
        "minecraft:geometry" = @(
            @{
                description = @{
                    identifier = "geometry.$name"
                    texture_width = 64
                    texture_height = 64
                }
                bones = @()
            }
        )
    } | ConvertTo-Json -Depth 10
    # Create simple bone structure
    $bonesArray = @()
    foreach ($bone in $bones) {
        $bonesArray += @{
            name = $bone
            pivot = @(0, 0, 0)
            cubes = @(
                @{
                    origin = @(-2, 0, -2)
                    size = @(4, 4, 4)
                    uv = @(0, 0)
                }
            )
        }
    }
    $geoContent = @"
{
  "format_version": "1.12.0",
  "minecraft:geometry": [
    {
      "description": {
        "identifier": "geometry.$name",
        "texture_width": 64,
        "texture_height": 64
      },
      "bones": [
$($bonesArray | ForEach-Object {
    @"
        {
          "name": "$($_.name)",
          "pivot": [0, 0, 0],
          "cubes": [
            {
              "origin": [-2, 0, -2],
              "size": [4, 4, 4],
              "uv": [0, 0]
            }
          ]
        }
"@
} | Join-String -Separator ",`n")
      ]
    }
  ]
}
"@
    $geoPath = "src\main\resources\assets\elementaldimensions\geo\entity\$name.geo.json"
    $geoContent | Out-File -FilePath $geoPath -Encoding UTF8
    # Create animation JSON
    $animContent = @"
{
  "format_version": "1.8.0",
  "animations": {
    "animation.$name.idle": {
      "loop": true,
      "animation_length": 2.0,
      "bones": {
        "$($bones[0])": {
          "rotation": {
            "0.0": [0, 0, 0],
            "1.0": [0, 5, 0],
            "2.0": [0, 0, 0]
          }
        }
      }
    },
    "animation.$name.walk": {
      "loop": true,
      "animation_length": 1.0,
      "bones": {
        "$($bones[0])": {
          "rotation": {
            "0.0": [0, 0, 0],
            "0.5": [5, 0, 0],
            "1.0": [0, 0, 0]
          }
        }
      }
    }
  }
}
"@
    $animPath = "src\main\resources\assets\elementaldimensions\animations\entity\$name.animation.json"
    $animContent | Out-File -FilePath $animPath -Encoding UTF8
    Write-Output "Created geometry and animation for $displayName"
}
Write-Output "`nAll geometry and animation files created successfully!"