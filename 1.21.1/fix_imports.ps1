# Fix missing imports and method signatures

# Fix EmberSandBlock particle spawning
$emberSand = "src\main\java\com\elementaldimensions\block\EmberSandBlock.java"
$content = Get-Content $emberSand -Raw

# Fix spawnParticles signature - it needs count and velocity params
$content = $content -replace 'spawnParticles\(ParticleTypes\.FLAME, ([^,]+), ([^,]+), ([^,]+), ([^,]+), ([^,]+), ([^)]+)\)', 'spawnParticles(ParticleTypes.FLAME, $1, $2, $3, 1, $4, $5, $6, 0.0)'

# Ensure ServerWorld import
if ($content -notmatch 'import net\.minecraft\.server\.world\.ServerWorld;') {
    $content = $content -replace '(package com\.elementaldimensions\.block;)', "`$1`r`nimport net.minecraft.server.world.ServerWorld;"
}

Set-Content -Path $emberSand -Value $content -NoNewline
Write-Host "Fixed EmberSandBlock"

# Fix RiftBlock particle spawning  
$riftBlock = "src\main\java\com\elementaldimensions\block\RiftBlock.java"
$content = Get-Content $riftBlock -Raw

$content = $content -replace 'spawnParticles\(ParticleTypes\.PORTAL, ([^,]+), ([^,]+), ([^,]+), ([^,]+), ([^,]+), ([^)]+)\)', 'spawnParticles(ParticleTypes.PORTAL, $1, $2, $3, 1, $4, $5, $6, 0.0)'

if ($content -notmatch 'import net\.minecraft\.server\.world\.ServerWorld;') {
    $content = $content -replace '(package com\.elementaldimensions\.block;)', "`$1`r`nimport net.minecraft.server.world.ServerWorld;"
}

Set-Content -Path $riftBlock -Value $content -NoNewline
Write-Host "Fixed RiftBlock"

# Fix ModCommands
$commands = "src\main\java\com\elementaldimensions\command\ModCommands.java"
$content = Get-Content $commands -Raw

# Add Vec3d import
if ($content -notmatch 'import net\.minecraft\.util\.math\.Vec3d;') {
    $content = $content -replace '(package com\.elementaldimensions\.command;)', "`$1`r`nimport net.minecraft.util.math.Vec3d;"
}

# Fix getSpawnPoint return type (it returns BlockPos)
$content = $content -replace 'BlockPos spawnPos = targetWorld\.getSpawnPoint\(\);', 'BlockPos spawnPos = targetWorld.getSpawnPos();'

# Fix entity spawn - spawn method signature changed
$content = $content -replace 'entityType\.spawn\(player\.getWorld\(\), spawnPos, SpawnReason\.COMMAND\);', 'entityType.spawn(player.getServerWorld(), spawnPos, SpawnReason.COMMAND);'

Set-Content -Path $commands -Value $content -NoNewline
Write-Host "Fixed ModCommands"

Write-Host "Done!"
