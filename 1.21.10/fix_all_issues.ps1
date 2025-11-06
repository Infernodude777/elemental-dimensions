# Fix all trailing whitespace and other issues in Java files
$javaFiles = Get-ChildItem -Path "src" -Filter "*.java" -Recurse
Write-Output "Fixing trailing whitespace in $($javaFiles.Count) Java files..."
$totalFixed = 0
$filesFixed = 0
foreach ($file in $javaFiles) {
    $content = Get-Content $file.FullName -Raw
    $originalContent = $content
    # Remove trailing whitespace from each line
    $lines = $content -split "`r?`n"
    $fixedLines = $lines | ForEach-Object { $_.TrimEnd() }
    $content = $fixedLines -join "`n"
    # Ensure file ends with single newline
    $content = $content.TrimEnd() + "`n"
    if ($content -ne $originalContent) {
        [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.UTF8Encoding]::new($false))
        $filesFixed++
        $linesDiff = ($originalContent -split "`r?`n").Count - ($content -split "`r?`n").Count
        $totalFixed += [Math]::Abs($linesDiff)
    }
}
Write-Output "Fixed trailing whitespace in $filesFixed files"
Write-Output "Total lines cleaned: $totalFixed"
# Fix JSON files
$jsonFiles = Get-ChildItem -Path "src\main\resources" -Filter "*.json" -Recurse
Write-Output "`nFixing JSON files: $($jsonFiles.Count) files..."
$jsonFixed = 0
foreach ($file in $jsonFiles) {
    $content = Get-Content $file.FullName -Raw
    $originalContent = $content
    # Remove trailing whitespace and ensure proper line endings
    $lines = $content -split "`r?`n"
    $fixedLines = $lines | ForEach-Object { $_.TrimEnd() }
    $content = $fixedLines -join "`n"
    $content = $content.TrimEnd() + "`n"
    if ($content -ne $originalContent) {
        [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.UTF8Encoding]::new($false))
        $jsonFixed++
    }
}
Write-Output "Fixed $jsonFixed JSON files"
Write-Output "`nAll issues fixed!"