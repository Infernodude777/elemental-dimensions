# Suppress GeckoLib deprecation warnings by adding @SuppressWarnings annotation
$projectRoot = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1"
Set-Location $projectRoot
$fixedFiles = 0
$totalChanges = 0
Write-Output "Suppressing GeckoLib deprecation warnings..."
Write-Output "============================================="
# Get all renderer files
$rendererFiles = Get-ChildItem -Path "src\main\java\com\elementaldimensions\client\renderer\entity" -Filter "*Renderer.java" -Recurse
foreach ($file in $rendererFiles) {
    $content = Get-Content $file.FullName -Raw
    $originalContent = $content
    $changed = $false
    # Add @SuppressWarnings("deprecation") before the inner model class
    if ($content -match 'public static class \w+Model extends GeoModel') {
        $content = $content -replace '([ \t]+)(public static class \w+Model extends GeoModel)', "`$1@SuppressWarnings(`"deprecation`")`r`n`$1`$2"
        $changed = $true
        $totalChanges++
    }
    if ($changed) {
        [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.UTF8Encoding]::new($false))
        $fixedFiles++
        Write-Output "Fixed: $($file.Name)"
    }
}
Write-Output "`n============================================="
Write-Output "Total files fixed: $fixedFiles"
Write-Output "Total @SuppressWarnings added: $totalChanges"
Write-Output "============================================="