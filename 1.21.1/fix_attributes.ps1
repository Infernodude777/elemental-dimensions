# Fix EntityAttributes for Minecraft 1.21.10
$files = Get-ChildItem -Path "src\main\java" -Filter "*.java" -Recurse
foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    $modified = $false
    # Fix all EntityAttributes constants
    if ($content -match 'EntityAttributes\.GENERIC_') {
        $content = $content -replace 'EntityAttributes\.GENERIC_MAX_HEALTH', 'EntityAttributes.MAX_HEALTH'
        $content = $content -replace 'EntityAttributes\.GENERIC_ATTACK_DAMAGE', 'EntityAttributes.ATTACK_DAMAGE'
        $content = $content -replace 'EntityAttributes\.GENERIC_ARMOR', 'EntityAttributes.ARMOR'
        $content = $content -replace 'EntityAttributes\.GENERIC_KNOCKBACK_RESISTANCE', 'EntityAttributes.KNOCKBACK_RESISTANCE'
        $content = $content -replace 'EntityAttributes\.GENERIC_MOVEMENT_SPEED', 'EntityAttributes.MOVEMENT_SPEED'
        $content = $content -replace 'EntityAttributes\.GENERIC_FLYING_SPEED', 'EntityAttributes.FLYING_SPEED'
        $content = $content -replace 'EntityAttributes\.GENERIC_FOLLOW_RANGE', 'EntityAttributes.FOLLOW_RANGE'
        $modified = $true
    }
    # Remove @Override from isFireImmune()
    if ($content -match '@Override\s+public\s+boolean\s+isFireImmune') {
        $content = $content -replace '@Override(\s+)public(\s+)boolean(\s+)isFireImmune', 'public$2boolean$3isFireImmune'
        $modified = $true
    }
    # Fix world.addParticle calls (World doesn't have addParticle, need to cast)
    if ($content -match 'world\.addParticle\(ParticleTypes\.') {
        $content = $content -replace '(\s+)world\.addParticle\(', '$1((ServerWorld)world).spawnParticles('
        $content = $content -replace 'spawnParticles\(ParticleTypes\.FLAME,', 'spawnParticles(ParticleTypes.FLAME, '
        $content = $content -replace 'spawnParticles\(ParticleTypes\.PORTAL,', 'spawnParticles(ParticleTypes.PORTAL, '
        $modified = $true
    }
    # Fix getSpawnPos() -> getSpawnPoint()
    if ($content -match '\.getSpawnPos\(\)') {
        $content = $content -replace '\.getSpawnPos\(\)', '.getSpawnPoint()'
        $modified = $true
    }
    # Fix player.getPos() -> player.getPosition() or Vec3d.ofCenter(player.getBlockPos())
    if ($content -match 'player\.getPos\(\)\.add') {
        $content = $content -replace 'player\.getPos\(\)\.add\(([^)]+)\)', 'Vec3d.ofCenter(player.getBlockPos()).add($1)'
        $modified = $true
    }
    # Fix getServerWorld() -> getWorld()
    if ($content -match 'player\.getServerWorld\(\)') {
        $content = $content -replace 'player\.getServerWorld\(\)', 'player.getWorld()'
        $modified = $true
    }
    if ($modified) {
        Set-Content -Path $file.FullName -Value $content -NoNewline
        Write-Output "Fixed: $($file.FullName)"
    }
}
Write-Output "Done! Fixed all entity attribute references."