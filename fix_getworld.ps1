# Fix getWorld() calls to getEntityWorld() and isClient to isClient() in all boss entities

$bossFiles = @(
    "src\main\java\com\elementaldimensions\entity\boss\AbyssLeviathanEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\StoneColossusEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\WindSeraphEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\ElementalPrimarchEntity.java"
)

foreach ($file in $bossFiles) {
    if (Test-Path $file) {
        $content = Get-Content $file -Raw
        
        # Fix getWorld() to getEntityWorld()
        $content = $content -replace 'this\.getWorld\(\)', 'this.getEntityWorld()'
        
        # Fix isClient to isClient()
        $content = $content -replace '\.isClient([^(])', '.isClient()$1'
        
        Set-Content -Path $file -Value $content -NoNewline
        Write-Host "Fixed: $file"
    }
}

Write-Host "Done! Fixed getWorld() calls in all boss entities."
