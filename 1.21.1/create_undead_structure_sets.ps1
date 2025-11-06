# Create structure sets for Undead Realm
$structures = @(
    @{name="crypt"; spacing=24; separation=12; salt=111111},
    @{name="tomb"; spacing=20; separation=10; salt=222222},
    @{name="graveyard"; spacing=28; separation=14; salt=333333},
    @{name="bone_castle"; spacing=40; separation=20; salt=444444},
    @{name="mausoleum"; spacing=26; separation=13; salt=555555},
    @{name="haunted_house"; spacing=22; separation=11; salt=666666},
    @{name="necromancer_tower"; spacing=35; separation=17; salt=777777},
    @{name="soul_prison"; spacing=30; separation=15; salt=888888},
    @{name="phantom_fortress"; spacing=42; separation=21; salt=999999},
    @{name="lich_citadel"; spacing=50; separation=25; salt=101010},
    @{name="cursed_well"; spacing=16; separation=8; salt=121212},
    @{name="bone_garden"; spacing=20; separation=10; salt=131313},
    @{name="spirit_shrine"; spacing=22; separation=11; salt=141414},
    @{name="undead_catacombs"; spacing=38; separation=19; salt=151515},
    @{name="wraith_monument"; spacing=28; separation=14; salt=161616}
)
$basePath = "src/main/resources/data/elementaldimensions/worldgen/structure_set"
foreach ($struct in $structures) {
    $content = @"
{
  "structures": [
    {
      "structure": "elementaldimensions:undead_$($struct.name)",
      "weight": 1
    }
  ],
  "placement": {
    "type": "minecraft:random_spread",
    "spacing": $($struct.spacing),
    "separation": $($struct.separation),
    "salt": $($struct.salt)
  }
}
"@
    $filePath = Join-Path $basePath "undead_$($struct.name).json"
    [System.IO.File]::WriteAllText($filePath, $content, [System.Text.UTF8Encoding]::new($false))
    Write-Output "Created structure set for $($struct.name)"
}
Write-Output "All 15 Undead Realm structure sets created!"