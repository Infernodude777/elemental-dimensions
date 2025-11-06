# Remove dropLoot methods from boss entities (they're on a single line due to formatting)
$bossFiles = @(
    "src\main\java\com\elementaldimensions\entity\boss\AbyssLeviathanEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\StoneColossusEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\WindSeraphEntity.java",
    "src\main\java\com\elementaldimensions\entity\boss\ElementalPrimarchEntity.java"
)
foreach ($file in $bossFiles) {
    if (Test-Path $file) {
        $content = Get-Content $file -Raw
        # Remove dropLoot method - it's on one line and includes dropStack calls
        # Pattern: @Override protected void dropLoot(...){...}
        $content = $content -replace '@Override protected void dropLoot\([^)]+\)\{[^}]+this\.dropStack[^}]+\}', '// TODO: Implement loot tables'
        Set-Content -Path $file -Value $content -NoNewline
        Write-Output "Removed dropLoot from: $file"
    }
}
Write-Output "Done! Removed all dropLoot methods."