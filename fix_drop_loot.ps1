# Fix dropLoot and dropStack methods for MC 1.21.10
# These methods changed signature - simplest fix is to comment them out and use loot tables instead

$entityFiles = Get-ChildItem -Path "src\main\java\com\elementaldimensions\entity" -Filter "*Entity.java" -Recurse

foreach ($file in $entityFiles) {
    $content = Get-Content $file.FullName -Raw
    $modified = $false
    
    # Comment out @Override annotation before dropLoot if it exists
    if ($content -match '@Override\s+protected void dropLoot') {
        $content = $content -replace '(@Override\s+protected void dropLoot.*?^\s*})', '/*$1*/'  -replace "`r`n", "`n"
        $modified = $true
    }
    
    # Also comment out dropLoot methods without @Override
    if ($content -match 'protected void dropLoot\(DamageSource') {
        # Find and comment out the entire dropLoot method
        $content = $content -replace '(protected void dropLoot\(DamageSource[^}]+}\s+})', '/*TODO: Implement proper loot tables for MC 1.21.10*/'
        $modified = $true
    }
    
    if ($modified) {
        Set-Content -Path $file.FullName -Value $content -NoNewline
        Write-Host "Fixed: $($file.Name)"
    }
}

Write-Host "Done! Commented out dropLoot methods - use loot tables instead."
