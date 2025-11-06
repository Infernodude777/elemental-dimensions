# Fix EntityType.Builder.build() calls for MC 1.21.10
$file = "src\main\java\com\elementaldimensions\entity\ModEntities.java"
$content = Get-Content $file -Raw
# In MC 1.21.10, EntityType.Builder.build() requires a null parameter (or RegistryKey)
# Replace .build()); with .build(null));
$content = $content -replace '\.build\(\)\);', '.build(null));'
Set-Content -Path $file -Value $content -NoNewline
Write-Output "Fixed EntityType.Builder.build() calls in ModEntities.java"