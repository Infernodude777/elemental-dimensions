# Script to create GeckoLib renderers for Undead Realm entities
$rendererPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\java\com\elementaldimensions\client\renderer\entity"
if (-not (Test-Path $rendererPath)) {
    New-Item -ItemType Directory -Force -Path $rendererPath | Out-Null
}
# Entity names
$entities = @(
    "ZombieMiner", "SkeletonArcher", "Ghost", "Wraith", "Banshee",
    "NecromanticSkeleton", "PhantomKnight", "GraveyardGuardian",
    "SoulStealer", "BoneDragon", "LichKingBoss"
)
foreach ($entity in $entities) {
    $rendererContent = @"
package com.elementaldimensions.client.renderer.entity;
import com.elementaldimensions.entity.undead.${entity}Entity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
public class ${entity}Renderer extends GeoEntityRenderer<${entity}Entity> {
	public ${entity}Renderer(EntityRendererFactory.Context context) {
		super(context, new ${entity}Model());
	}
	public static class ${entity}Model extends GeoModel<${entity}Entity> {
		@Override
		public Identifier getModelResource(${entity}Entity entity) {
			return Identifier.of("elementaldimensions", "geo/entity/${entity.ToLower()}.geo.json");
		}
		@Override
		public Identifier getTextureResource(${entity}Entity entity) {
			return Identifier.of("elementaldimensions", "textures/entity/${entity.ToLower()}.png");
		}
		@Override
		public Identifier getAnimationResource(${entity}Entity entity) {
			return Identifier.of("elementaldimensions", "animations/entity/${entity.ToLower()}.animation.json");
		}
	}
}
"@
    $filePath = "$rendererPath\${entity}Renderer.java"
    $rendererContent | Out-File -FilePath $filePath -Encoding UTF8
    Write-Output "Created renderer: ${entity}Renderer"
}
Write-Output "All Undead Realm renderers created successfully!"