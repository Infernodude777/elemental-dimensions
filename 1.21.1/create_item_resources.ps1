# Create item resources for all new dimension items
Add-Type -AssemblyName System.Drawing
$items = @(
    # Dreaming Depths
    @{name="dream_essence"; display="Dream Essence"; color="#D8BFD8"},
    @{name="somnacite_crystal"; display="Somnacite Crystal"; color="#E6E6FA"},
    @{name="umbral_ingot"; display="Umbral Ingot"; color="#9370DB"},
    @{name="lucid_shard"; display="Lucid Shard"; color="#B0C4DE"},
    @{name="dreamweave_fabric"; display="Dreamweave Fabric"; color="#DDA0DD"},
    @{name="mirror_of_rest"; display="Mirror of Rest"; color="#C0C0C0"},
    # Celestine Expanse
    @{name="celestine_shard"; display="Celestine Shard"; color="#87CEEB"},
    @{name="starfall_ingot"; display="Starfall Ingot"; color="#4682B4"},
    @{name="sky_paper"; display="Sky Paper"; color="#F0F8FF"},
    @{name="aether_dust"; display="Aether Dust"; color="#ADD8E6"},
    @{name="radiant_essence"; display="Radiant Essence"; color="#00BFFF"},
    @{name="celestine_key"; display="Celestine Key"; color="#FFD700"},
    # Fungal Dominion
    @{name="spore_essence"; display="Spore Essence"; color="#556B2F"},
    @{name="mycelite_ingot"; display="Mycelite Ingot"; color="#6B8E23"},
    @{name="biolume_spore"; display="Biolume Spore"; color="#9ACD32"},
    @{name="fungal_fiber"; display="Fungal Fiber"; color="#8FBC8F"},
    @{name="symbiont_sample"; display="Symbiont Sample"; color="#3CB371"},
    # Forgotten Archive
    @{name="memory_ink"; display="Memory Ink"; color="#DAA520"},
    @{name="runic_dust"; display="Runic Dust"; color="#F0E68C"},
    @{name="archive_shard"; display="Archive Shard"; color="#DEB887"},
    @{name="knowledge_essence"; display="Knowledge Essence"; color="#FFD700"},
    @{name="memory_ink_flask"; display="Memory Ink Flask"; color="#B8860B"},
    # Astral Frontier
    @{name="stardust_fragment"; display="Stardust Fragment"; color="#9370DB"},
    @{name="celestite_shard"; display="Celestite Shard"; color="#BA55D3"},
    @{name="nebular_essence"; display="Nebular Essence"; color="#8B008B"},
    @{name="astral_crystal"; display="Astral Crystal"; color="#DA70D6"},
    @{name="stellar_core"; display="Stellar Core"; color="#FF1493"},
    # Gloomy Caverns
    @{name="gloom_crystal_shard"; display="Gloom Crystal Shard"; color="#696969"},
    @{name="tear_of_despair"; display="Tear of Despair"; color="#2F4F4F"},
    @{name="echo_essence"; display="Echo Essence"; color="#708090"},
    @{name="decay_dust"; display="Decay Dust"; color="#A9A9A9"}
)
$modelsPath = "src\main\resources\assets\elementaldimensions\models\item"
$texturesPath = "src\main\resources\assets\elementaldimensions\textures\item"
# Create directories if they don't exist
New-Item -ItemType Directory -Force -Path $modelsPath | Out-Null
New-Item -ItemType Directory -Force -Path $texturesPath | Out-Null
Write-Output "Creating item models, textures, and translations for $($items.Count) items..."
$translationEntries = ""
$count = 0
foreach ($item in $items) {
    $count++
    # Create item model JSON
    $modelJson = @{
        parent = "minecraft:item/generated"
        textures = @{
            layer0 = "elementaldimensions:item/$($item.name)"
        }
    } | ConvertTo-Json -Depth 10
    $modelPath = Join-Path $modelsPath "$($item.name).json"
    $modelJson | Set-Content -Path $modelPath -Encoding UTF8
    # Create 16x16 texture
    $bitmap = New-Object System.Drawing.Bitmap(16, 16)
    $graphics = [System.Drawing.Graphics]::FromImage($bitmap)
    $brush = New-Object System.Drawing.SolidBrush([System.Drawing.ColorTranslator]::FromHtml($item.color))
    $graphics.FillRectangle($brush, 0, 0, 16, 16)
    # Add border for visibility
    $borderPen = New-Object System.Drawing.Pen([System.Drawing.Color]::Black, 1)
    $graphics.DrawRectangle($borderPen, 0, 0, 15, 15)
    $texturePath = Join-Path $texturesPath "$($item.name).png"
    $bitmap.Save($texturePath, [System.Drawing.Imaging.ImageFormat]::Png)
    $graphics.Dispose()
    $bitmap.Dispose()
    $brush.Dispose()
    $borderPen.Dispose()
    # Add translation entry
    $translationEntries += ",`n  `"item.elementaldimensions.$($item.name)`": `"$($item.display)`""
    if ($count % 5 -eq 0) {
        Write-Output "  Processed $count/$($items.Count) items..."
    }
}
Write-Output "`nSuccessfully created $count item models"
Write-Output "Successfully created $count item textures"
Write-Output "`nTranslation entries to add:"
Write-Output $translationEntries