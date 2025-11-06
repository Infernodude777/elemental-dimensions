# Add @SuppressWarnings("deprecation") to all GeoModel classes
$projectRoot = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1"
Set-Location $projectRoot
$fixedFiles = 0
Write-Output "Adding @SuppressWarnings to all entity renderers..."
Write-Output "===================================================="
# Get all renderer files
$rendererFiles = Get-ChildItem -Path "src\main\java\com\elementaldimensions\client\renderer\entity" -Filter "*Renderer.java" -Recurse
foreach ($file in $rendererFiles) {
    $lines = Get-Content $file.FullName
    $newLines = @()
    $modified = $false
    for ($i = 0; $i -lt $lines.Count; $i++) {
        $line = $lines[$i]
        # Check if this line contains a Model class declaration
        if ($line -match '^\s*public static class \w+Model extends GeoModel') {
            # Check if the previous line already has @SuppressWarnings
            if ($i -gt 0 -and $newLines[$newLines.Count - 1] -notmatch '@SuppressWarnings') {
                # Get the indentation from the current line
                if ($line -match '^(\s*)') {
                    $indent = $matches[1]
                    # Add @SuppressWarnings with the same indentation
                    $newLines += "$indent@SuppressWarnings(`"deprecation`")"
                    $modified = $true
                }
            }
        }
        $newLines += $line
    }
    if ($modified) {
        $newLines | Set-Content $file.FullName -Encoding UTF8
        $fixedFiles++
        Write-Output "Fixed: $($file.Name)"
    }
}
Write-Output "`n===================================================="
Write-Output "Total files fixed: $fixedFiles"
Write-Output "===================================================="