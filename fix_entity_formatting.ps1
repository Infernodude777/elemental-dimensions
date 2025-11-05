# Fix corrupted entity file formatting
# This script fixes files where imports and code are squashed on single lines

$entityFiles = Get-ChildItem -Path "src\main\java\com\elementaldimensions\entity" -Filter "*.java" -Recurse

foreach ($file in $entityFiles) {
    Write-Host "Processing: $($file.Name)"

    $content = Get-Content $file.FullName -Raw

    # Fix import statements (add newlines after each import)
    $content = $content -replace 'import ([^;]+);import', "import `$1;`nimport"

    # Fix class declaration and constructor on same line
    $content = $content -replace '\}public class', "}`n`npublic class"
    $content = $content -replace '\{public ([A-Z]\w+Entity)\(', "{`n`tpublic `$1("

    # Fix method chaining (add newlines for readability)
    $content = $content -replace '\.add\(EntityAttributes\.GENERIC', "`n`t`t.add(EntityAttributes.GENERIC"

    # Fix method declarations squashed with code
    $content = $content -replace '\}\@Override protected void initGoals\(\)\{', "}`n`n`t@Override`n`tprotected void initGoals() {`n"
    $content = $content -replace '\}\public static DefaultAttributeContainer', "}`n`n`tpublic static DefaultAttributeContainer"
    $content = $content -replace 'createAttributes\(\)\{return', "createAttributes() {`n`t`treturn"
    $content = $content -replace ';\}\@Override public void registerControllers', ";`n`t}`n`n`t@Override`n`tpublic void registerControllers"

    # Fix goal selector additions
    $content = $content -replace 'this\.goalSelector\.add\(', "`n`t`tthis.goalSelector.add("
    $content = $content -replace 'this\.targetSelector\.add\(', "`n`t`tthis.targetSelector.add("

    # Fix closing braces
    $content = $content -replace '\}\@Override', "}`n`n`t@Override"

    # Save the fixed content
    Set-Content -Path $file.FullName -Value $content -NoNewline
}

Write-Host "Formatting fixes completed!"
