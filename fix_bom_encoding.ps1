# Script to fix UTF-8 BOM encoding issues in Java files
$entityPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\java\com\elementaldimensions\entity\undead"
$rendererPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\java\com\elementaldimensions\client\renderer\entity"
$entityFiles = Get-ChildItem -Path $entityPath -Filter "*.java"
$filesToFix = @()
$filesToFix += $entityFiles | Where-Object { $_.Name -notlike "ZombieMinerEntity.java" -and $_.Name -notlike "LichKingBossEntity.java" }
$filesToFix += Get-ChildItem -Path $rendererPath -Filter "*Renderer.java" | Where-Object {
    $_.Name -like "ZombieMiner*" -or
    $_.Name -like "SkeletonArcher*" -or
    $_.Name -like "Ghost*" -or
    $_.Name -like "Wraith*" -or
    $_.Name -like "Banshee*" -or
    $_.Name -like "NecromanticSkeleton*" -or
    $_.Name -like "PhantomKnight*" -or
    $_.Name -like "GraveyardGuardian*" -or
    $_.Name -like "SoulStealer*" -or
    $_.Name -like "BoneDragon*" -or
    $_.Name -like "LichKingBoss*"
}
foreach ($file in $filesToFix) {
    try {
        # Read the file content as bytes
        $content = Get-Content -Path $file.FullName -Raw
        # Write back without BOM using ASCII encoding then UTF-8
        $utf8NoBomEncoding = New-Object System.Text.UTF8Encoding $False
        [System.IO.File]::WriteAllText($file.FullName, $content, $utf8NoBomEncoding)
        Write-Output "Fixed encoding for: $($file.Name)"
    }
    catch {
        Write-Warning "Failed to fix $($file.Name): $_"
    }
}
Write-Output "`nAll files fixed!"
