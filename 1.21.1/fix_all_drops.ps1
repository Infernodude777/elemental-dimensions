# Fix all entity dropLoot methods by removing them (use loot tables instead)

$entityFiles = @(
    "src\main\java\com\elementaldimensions\entity\boss\AbyssLeviathanEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\ElementalPrimarchEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\StoneColossusEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\WindSeraphEntity.java",
    "src\main\java\com\elementaldimensions\entity\firelands\FlameSpriteEntity.java",
    "src\main\java\com\elementaldimensions\entity\firelands\MagmaBruteEntity.java"
)

foreach ($filePath in $entityFiles) {
    if (Test-Path $filePath) {
        $content = Get-Content $filePath -Raw
        
        # Remove dropLoot method override (matches @Override...} pattern)
        $content = $content -replace '(?s)@Override\s+protected void dropLoot\([^}]+\{[^}]+\}\s+', ''
        
        # Also handle non-@Override cases
        $content = $content -replace '(?s)protected void dropLoot\(DamageSource[^}]+\{[^}]+\}\s+', ''
        
        Set-Content -Path $filePath -Value $content -NoNewline
        Write-Host "Fixed: $filePath"
    }
}

Write-Host "Done! All dropLoot methods removed. Use loot tables instead."
