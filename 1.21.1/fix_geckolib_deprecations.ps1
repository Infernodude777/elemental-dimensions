# Fix GeckoLib deprecation warnings by removing @Override annotations
# from getModelResource, getTextureResource, and getAnimationResource methods
$projectRoot = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1"
Set-Location $projectRoot
$fixedFiles = 0
$totalChanges = 0
Write-Output "Fixing GeckoLib deprecation warnings..."
Write-Output "========================================"
# Get all renderer files
$rendererFiles = Get-ChildItem -Path "src\main\java\com\elementaldimensions\client\renderer\entity" -Filter "*Renderer.java" -Recurse
foreach ($file in $rendererFiles) {
    $content = Get-Content $file.FullName -Raw
    $originalContent = $content
    $changed = $false
    # Remove @Override from getModelResource
    if ($content -match '@Override\s+public Identifier getModelResource') {
        $content = $content -replace '(\s+)@Override(\s+public Identifier getModelResource)', '$1$2'
        $changed = $true
        $totalChanges++
    }
    # Remove @Override from getTextureResource
    if ($content -match '@Override\s+public Identifier getTextureResource') {
        $content = $content -replace '(\s+)@Override(\s+public Identifier getTextureResource)', '$1$2'
        $changed = $true
        $totalChanges++
    }
    # Remove @Override from getAnimationResource
    if ($content -match '@Override\s+public Identifier getAnimationResource') {
        $content = $content -replace '(\s+)@Override(\s+public Identifier getAnimationResource)', '$1$2'
        $changed = $true
        $totalChanges++
    }
    if ($changed) {
        [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.UTF8Encoding]::new($false))
        $fixedFiles++
        Write-Output "Fixed: $($file.Name)"
    }
}
Write-Output "`n========================================"
Write-Output "Total files fixed: $fixedFiles"
Write-Output "Total @Override annotations removed: $totalChanges"
Write-Output "========================================"