# Entity list with display names and identifiers
$entities = @(
    @{Name="Ember Serpent"; Id="ember_serpent"},
    @{Name="Pyre Howler"; Id="pyre_howler"},
    @{Name="Magma Brute"; Id="magma_brute"},
    @{Name="Cinder Elemental"; Id="cinder_elemental"},
    @{Name="Abyss Crab"; Id="abyss_crab"},
    @{Name="Tide Siren"; Id="tide_siren"},
    @{Name="Pressure Ray"; Id="pressure_ray"},
    @{Name="Bubble Wisp"; Id="bubble_wisp"},
    @{Name="Coral Guardian"; Id="coral_guardian"},
    @{Name="Stone Golem"; Id="stone_golem"},
    @{Name="Moss Beast"; Id="moss_beast"},
    @{Name="Crystal Mite"; Id="crystal_mite"},
    @{Name="Root Spider"; Id="root_spider"},
    @{Name="Cave Lurker"; Id="cave_lurker"},
    @{Name="Wind Hawk"; Id="wind_hawk"},
    @{Name="Cloud Sentinel"; Id="cloud_sentinel"},
    @{Name="Thunder Hawk"; Id="thunder_hawk"},
    @{Name="Sky Wisp"; Id="sky_wisp"},
    @{Name="Petal Sprite"; Id="petal_sprite"},
    @{Name="Celestial Enderman"; Id="celestial_enderman"},
    @{Name="Starlight Phantom"; Id="starlight_phantom"},
    @{Name="Void Shulker"; Id="void_shulker"},
    @{Name="Nebula Wraith"; Id="nebula_wraith"},
    @{Name="Shadow Wraith"; Id="shadow_wraith"},
    @{Name="Void Stalker"; Id="void_stalker"},
    @{Name="Fire Titan"; Id="fire_titan"},
    @{Name="Abyss Leviathan"; Id="abyss_leviathan"},
    @{Name="Stone Colossus"; Id="stone_colossus"},
    @{Name="Wind Seraph"; Id="wind_seraph"},
    @{Name="Elemental Primarch"; Id="elemental_primarch"},
    @{Name="Void Lord"; Id="void_lord"}
)

# Read template files
$geoTemplate = Get-Content "src\main\resources\assets\elementaldimensions\geo\entity\flame_sprite.geo.json" -Raw
$animTemplate = Get-Content "src\main\resources\assets\elementaldimensions\animations\entity\flame_sprite.animation.json" -Raw

# Create files for each entity
foreach ($entity in $entities) {
    # Replace flame_sprite with entity id in templates
    $geoContent = $geoTemplate -replace 'flame_sprite', $entity.Id
    $animContent = $animTemplate -replace 'flame_sprite', $entity.Id
    
    # Write geometry file
    $geoPath = "src\main\resources\assets\elementaldimensions\geo\entity\$($entity.Id).geo.json"
    $geoContent | Set-Content -Path $geoPath -NoNewline
    
    # Write animation file
    $animPath = "src\main\resources\assets\elementaldimensions\animations\entity\$($entity.Id).animation.json"
    $animContent | Set-Content -Path $animPath -NoNewline
    
    Write-Host "Created resources for $($entity.Name)"
}

Write-Host "`nAll geometry and animation files created successfully!"
Write-Host "Total: $($entities.Count) entities"
