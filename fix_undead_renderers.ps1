# Script to fix renderers and create proper resource names
$rendererPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\java\com\elementaldimensions\client\renderer\entity"
# Entity names mapped to their snake_case resource names
$entities = @{
    "ZombieMiner" = "zombie_miner"
    "SkeletonArcher" = "skeleton_archer"
    "Ghost" = "ghost"
    "Wraith" = "wraith"
    "Banshee" = "banshee"
    "NecromanticSkeleton" = "necromantic_skeleton"
    "PhantomKnight" = "phantom_knight"
    "GraveyardGuardian" = "graveyard_guardian"
    "SoulStealer" = "soul_stealer"
    "BoneDragon" = "bone_dragon"
    "LichKingBoss" = "lich_king"
}
foreach ($entity in $entities.GetEnumerator()) {
    $className = $entity.Key
    $resourceName = $entity.Value
    $rendererContent = @"
package com.elementaldimensions.client.renderer.entity;
import com.elementaldimensions.entity.undead.${className}Entity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
public class ${className}Renderer extends GeoEntityRenderer<${className}Entity> {
	public ${className}Renderer(EntityRendererFactory.Context context) {
		super(context, new ${className}Model());
	}
	public static class ${className}Model extends GeoModel<${className}Entity> {
		@Override
		public Identifier getModelResource(${className}Entity entity) {
			return Identifier.of("elementaldimensions", "geo/entity/$resourceName.geo.json");
		}
		@Override
		public Identifier getTextureResource(${className}Entity entity) {
			return Identifier.of("elementaldimensions", "textures/entity/$resourceName.png");
		}
		@Override
		public Identifier getAnimationResource(${className}Entity entity) {
			return Identifier.of("elementaldimensions", "animations/entity/$resourceName.animation.json");
		}
	}
}
"@
    $filePath = "$rendererPath\${className}Renderer.java"
    $rendererContent | Out-File -FilePath $filePath -Encoding UTF8 -Force
    Write-Output "Fixed renderer: ${className}Renderer"
}
Write-Output "All Undead Realm renderers fixed successfully!"