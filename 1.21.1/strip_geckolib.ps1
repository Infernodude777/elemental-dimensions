$files = Get-ChildItem -Path "src\main\java\com\elementaldimensions\entity" -Recurse -Filter "*.java" | Where-Object { $_.Name -ne "ModEntities.java" }

foreach ($file in $files) {
    Write-Host "Processing: $($file.Name)"
    
    $content = Get-Content $file.FullName -Raw
    
    # Remove GeckoLib imports
    $content = $content -replace 'import software\.bernie\.geckolib\..*?;[\r\n]+', ''
    
    # Remove "implements GeoEntity" from class declaration
    $content = $content -replace '\s+implements\s+GeoEntity\s*\{', ' {'
    $content = $content -replace '\s+implements\s+GeoEntity', ''
    
    # Remove cache field
    $content = $content -replace 'private\s+final\s+AnimatableInstanceCache\s+cache\s*=\s*GeckoLibUtil\.createInstanceCache\(this\);[\r\n\s]*', ''
    
    # Remove registerControllers method (full method with body)
    $content = $content -replace '@Override[\r\n\s]+public\s+void\s+registerControllers\(AnimatableManager\.ControllerRegistrar.*?\}\s*', ''
    
    # Remove predicate method (animation state method)
    $content = $content -replace 'private\s+PlayState\s+predicate\(AnimationState<.*?>\s+.*?\)[\r\n\s]*\{[^}]*\}[\r\n\s]*', ''
    
    # Remove getAnimatableInstanceCache method
    $content = $content -replace '@Override[\r\n\s]+public\s+AnimatableInstanceCache\s+getAnimatableInstanceCache\(\)[\r\n\s]*\{[^}]*\}[\r\n\s]*', ''
    
    # Clean up any double blank lines
    $content = $content -replace '(\r?\n\s*){3,}', "`r`n`r`n"
    
    Set-Content -Path $file.FullName -Value $content -NoNewline
}

Write-Host "Done processing all entity files"
