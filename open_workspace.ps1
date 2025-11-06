# Open correct VS Code workspace
# Choose which version to work on

Write-Output "Which version do you want to work on?"
Write-Output "1. Minecraft 1.21.1 (Production)"
Write-Output "2. Minecraft 1.21.10 (Pre-Release)"
Write-Output ""

$choice = Read-Host "Enter 1 or 2"

switch ($choice) {
    "1" {
        Write-Output "Opening 1.21.1 in VS Code..."
        code "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1"
    }
    "2" {
        Write-Output "Opening 1.21.10 in VS Code..."
        code "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.10"
    }
    default {
        Write-Output "Invalid choice. Opening 1.21.1 by default..."
        code "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1"
    }
}

Write-Output ""
Write-Output "This will eliminate all 'not on classpath' warnings!"
Write-Output "Each version is a separate project - open them separately."
