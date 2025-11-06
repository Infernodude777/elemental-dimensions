# Update to Official 1.21.10 Release
# Run this script when Minecraft 1.21.10 is officially released
$projectRoot = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.10"
Set-Location $projectRoot
Write-Output "========================================="
Write-Output "Updating to Minecraft 1.21.10 Official"
Write-Output "========================================="
Write-Output ""
# Prompt for new versions
Write-Output "Please enter the following version numbers:"
Write-Output "(Press Enter to use default values shown in brackets)"
Write-Output ""
$minecraftVersion = Read-Host "Minecraft version [1.21.10]"
if ([string]::IsNullOrWhiteSpace($minecraftVersion)) { $minecraftVersion = "1.21.10" }
$yarnMappings = Read-Host "Yarn mappings [1.21.10+build.1]"
if ([string]::IsNullOrWhiteSpace($yarnMappings)) { $yarnMappings = "1.21.10+build.1" }
$loaderVersion = Read-Host "Fabric Loader version [0.16.9]"
if ([string]::IsNullOrWhiteSpace($loaderVersion)) { $loaderVersion = "0.16.9" }
$fabricVersion = Read-Host "Fabric API version [0.110.0+1.21.10]"
if ([string]::IsNullOrWhiteSpace($fabricVersion)) { $fabricVersion = "0.110.0+1.21.10" }
$geckVersion = Read-Host "GeckoLib version [4.8.2]"
if ([string]::IsNullOrWhiteSpace($geckVersion)) { $geckVersion = "4.8.2" }
$clothVersion = Read-Host "Cloth Config version [15.0.140]"
if ([string]::IsNullOrWhiteSpace($clothVersion)) { $clothVersion = "15.0.140" }
Write-Output ""
Write-Output "Updating gradle.properties..."
# Read current gradle.properties
$gradleProps = Get-Content "gradle.properties" -Raw
# Update versions
$gradleProps = $gradleProps -replace 'minecraft_version=.*', "minecraft_version=$minecraftVersion"
$gradleProps = $gradleProps -replace 'yarn_mappings=.*', "yarn_mappings=$yarnMappings"
$gradleProps = $gradleProps -replace 'loader_version=.*', "loader_version=$loaderVersion"
$gradleProps = $gradleProps -replace 'fabric_version=.*', "fabric_version=$fabricVersion"
$gradleProps = $gradleProps -replace 'geckolib_version=.*', "geckolib_version=$geckVersion"
$gradleProps = $gradleProps -replace 'cloth_config_version=.*', "cloth_config_version=$clothVersion"
$gradleProps = $gradleProps -replace 'mod_version=1\.0\.0-pre1\.21\.10', 'mod_version=1.0.0'
# Update comment
$gradleProps = $gradleProps -replace '# Minecraft Properties \(Using.*\)', '# Minecraft Properties (Official 1.21.10 Release)'
$gradleProps = $gradleProps -replace '# This build is prepared.*', '# Official 1.21.10 build'
$gradleProps = $gradleProps -replace '# Dependencies \(Latest stable.*\)', '# Dependencies (Official 1.21.10)'
# Save updated gradle.properties
[System.IO.File]::WriteAllText("$projectRoot\gradle.properties", $gradleProps, [System.Text.UTF8Encoding]::new($false))
Write-Output "✓ gradle.properties updated"
# Update fabric.mod.json
Write-Output "Updating fabric.mod.json..."
$fabricMod = Get-Content "src\main\resources\fabric.mod.json" -Raw
$fabricMod = $fabricMod -replace '"minecraft": ">=1\.21\.1"', '"minecraft": "~1.21.10"'
$fabricMod = $fabricMod -replace '\[Pre-release for MC 1\.21\.10 - Currently running on 1\.21\.1\]', '[Official 1.21.10 Release]'
[System.IO.File]::WriteAllText("$projectRoot\src\main\resources\fabric.mod.json", $fabricMod, [System.Text.UTF8Encoding]::new($false))
Write-Output "✓ fabric.mod.json updated"
Write-Output ""
Write-Output "========================================="
Write-Output "Update Complete!"
Write-Output "========================================="
Write-Output ""
Write-Output "New versions:"
Write-Output "  Minecraft: $minecraftVersion"
Write-Output "  Yarn: $yarnMappings"
Write-Output "  Fabric Loader: $loaderVersion"
Write-Output "  Fabric API: $fabricVersion"
Write-Output "  GeckoLib: $geckVersion"
Write-Output "  Cloth Config: $clothVersion"
Write-Output ""
Write-Output "Next steps:"
Write-Output "  1. Run: .\gradlew clean build --no-daemon"
Write-Output "  2. Test the built JAR in-game"
Write-Output "  3. Deploy if everything works!"
Write-Output ""