# Comprehensive fix script for MC 1.21.10 API changes

$files = Get-ChildItem -Path "src" -Filter "*.java" -Recurse

foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    $modified = $false
    
    # Fix EntityType.Builder.build() - requires a RegistryKey parameter in 1.21.10
    if ($content -match '\.build\(\);') {
        $content = $content -replace '\.build\(\);', '.build(EntityType.createSimpleId(name));'
        $modified = $true
    }
    
    # Fix dropLoot method signature - changed in 1.21.10
    if ($content -match 'dropLoot\(DamageSource,\s*boolean\)') {
        $content = $content -replace '@Override\s+protected void dropLoot\(DamageSource source, boolean causedByPlayer\)', 'protected void dropLoot(ServerWorld world, DamageSource source, boolean causedByPlayer)'
        $content = $content -replace 'super\.dropLoot\(source,\s*causedByPlayer\)', 'super.dropLoot(this.getWorld() instanceof ServerWorld serverWorld ? serverWorld : null, source, causedByPlayer)'
        $modified = $true
    }
    
    # Fix dropStack method signature
    if ($content -match 'dropStack\(ItemStack') {
        $content = $content -replace 'this\.dropStack\(new ItemStack', 'this.dropStack(this.getWorld() instanceof ServerWorld serverWorld ? serverWorld : null, new ItemStack'
        $modified = $true
    }
    
    # Fix FallingBlock.getColor - needs implementation in 1.21.10
    if ($content -match 'class EmberSandBlock extends FallingBlock') {
        if ($content -notmatch 'getColor') {
            $content = $content -replace '(randomDisplayTick.*?\n\s+})', "`$1`n`n    @Override`n    public int getColor(BlockState state, BlockView world, BlockPos pos) {`n        return -1; // Default sand color`n    }"
            $modified = $true
        }
    }
    
    # Fix FallingBlock instantiation - now abstract, need concrete implementation
    $content = $content -replace 'new FallingBlock\(', 'new Block('
    if ($content -match 'new Block\(AbstractBlock\.Settings') {
        $modified = $true
    }
    
    # Fix FoodComponent package - changed to FoodComponents in 1.21.10
    $content = $content -replace 'import net\.minecraft\.item\.FoodComponent;', 'import net.minecraft.component.type.FoodComponent;'
    $content = $content -replace 'FoodComponent\.Builder', 'new FoodComponent.Builder()'
    $content = $content -replace '\.food\(new FoodComponent\.Builder\(\)\.', '.component(DataComponentTypes.FOOD, new FoodComponent.Builder().'
    if ($content -match 'component\(DataComponentTypes') {
        $modified = $true
        # Add DataComponentTypes import if needed
        if ($content -notmatch 'import net\.minecraft\.component\.DataComponentTypes') {
            $content = $content -replace '(package .*?;)', "`$1`nimport net.minecraft.component.DataComponentTypes;"
        }
    }
    
    # Fix World.isClient - changed to public getter
    $content = $content -replace 'world\.isClient', 'world.isClient()'
    if ($content -match 'isClient\(\)') {
        $modified = $true
    }
    
    # Fix armor material - API changes
    $content = $content -replace 'ArmorMaterial\.EMPTY', 'ArmorMaterials.LEATHER'
    $content = $content -replace 'ArmorMaterial\.Builder', 'new ArmorMaterial.Builder()'
    if ($content -match 'ArmorMaterials') {
        $modified = $true
    }
    
    # Fix ModCommands issues
    if ($file.Name -eq 'ModCommands.java') {
        # Already fixed TeleportTarget, just need to ensure imports
        if ($content -notmatch 'import net\.minecraft\.util\.math\.Vec3d') {
            $content = $content -replace '(package .*?;)', "`$1`nimport net.minecraft.util.math.Vec3d;"
            $modified = $true
        }
    }
    
    # Fix particle spawning symbols
    $content = $content -replace 'ParticleTypes\.DEFAULT_', 'ParticleTypes.'
    
    # Fix DimensionalCompassItem
    if ($file.Name -eq 'DimensionalCompassItem.java') {
        $content = $content -replace 'world\.isClient', 'world.isClient()'
        $content = $content -replace 'player\.getWorld\(\)\.getDimension', 'player.getWorld().getDimensionEntry'
        $modified = $true
    }
    
    if ($modified) {
        Set-Content -Path $file.FullName -Value $content -NoNewline
        Write-Host "Fixed: $($file.FullName)"
    }
}

Write-Host "Done! Fixed all API compatibility issues."
