# Script to create all GeckoLib entity renderers
$entities = @(
    @{Name="MagmaBrute"; Package="firelands"; SnakeName="magma_brute"},
    @{Name="CinderElemental"; Package="firelands"; SnakeName="cinder_elemental"},
    @{Name="AbyssCrab"; Package="aquatica"; SnakeName="abyss_crab"},
    @{Name="TideSiren"; Package="aquatica"; SnakeName="tide_siren"},
    @{Name="PressureRay"; Package="aquatica"; SnakeName="pressure_ray"},
    @{Name="BubbleWisp"; Package="aquatica"; SnakeName="bubble_wisp"},
    @{Name="CoralGuardian"; Package="aquatica"; SnakeName="coral_guardian"},
    @{Name="StoneGolem"; Package="terradepths"; SnakeName="stone_golem"},
    @{Name="MossBeast"; Package="terradepths"; SnakeName="moss_beast"},
    @{Name="CrystalMite"; Package="terradepths"; SnakeName="crystal_mite"},
    @{Name="RootSpider"; Package="terradepths"; SnakeName="root_spider"},
    @{Name="CaveLurker"; Package="terradepths"; SnakeName="cave_lurker"},
    @{Name="WindHawk"; Package="skyreach"; SnakeName="wind_hawk"},
    @{Name="CloudSentinel"; Package="skyreach"; SnakeName="cloud_sentinel"},
    @{Name="ThunderHawk"; Package="skyreach"; SnakeName="thunder_hawk"},
    @{Name="SkyWisp"; Package="skyreach"; SnakeName="sky_wisp"},
    @{Name="PetalSprite"; Package="skyreach"; SnakeName="petal_sprite"},
    @{Name="CelestialEnderman"; Package="celestial"; SnakeName="celestial_enderman"},
    @{Name="StarlightPhantom"; Package="celestial"; SnakeName="starlight_phantom"},
    @{Name="VoidShulker"; Package="celestial"; SnakeName="void_shulker"},
    @{Name="NebulaWraith"; Package="celestial"; SnakeName="nebula_wraith"},
    @{Name="ShadowWraith"; Package="voidrealm"; SnakeName="shadow_wraith"},
    @{Name="VoidStalker"; Package="voidrealm"; SnakeName="void_stalker"},
    @{Name="FireTitan"; Package="boss"; SnakeName="fire_titan"},
    @{Name="AbyssLeviathan"; Package="boss"; SnakeName="abyss_leviathan"},
    @{Name="StoneColossus"; Package="boss"; SnakeName="stone_colossus"},
    @{Name="WindSeraph"; Package="boss"; SnakeName="wind_seraph"},
    @{Name="ElementalPrimarch"; Package="boss"; SnakeName="elemental_primarch"},
    @{Name="VoidLord"; Package="boss"; SnakeName="void_lord"}
)
$rendererDir = "src\main\java\com\elementaldimensions\client\renderer\entity"
foreach ($entity in $entities) {
    $name = $entity.Name
    $package = $entity.Package
    $snakeName = $entity.SnakeName
    $content = @"
package com.elementaldimensions.client.renderer.entity;
import com.elementaldimensions.entity.$package.${name}Entity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
public class ${name}Renderer extends GeoEntityRenderer<${name}Entity> {
	public ${name}Renderer(EntityRendererFactory.Context context) {
		super(context, new ${name}Model());
	}
	public static class ${name}Model extends GeoModel<${name}Entity> {
		@Override
		public Identifier getModelResource(${name}Entity entity) {
			return Identifier.of("elementaldimensions", "geo/entity/$snakeName.geo.json");
		}
		@Override
		public Identifier getTextureResource(${name}Entity entity) {
			return Identifier.of("elementaldimensions", "textures/entity/$snakeName.png");
		}
		@Override
		public Identifier getAnimationResource(${name}Entity entity) {
			return Identifier.of("elementaldimensions", "animations/entity/$snakeName.animation.json");
		}
	}
}
"@
    $filePath = "$rendererDir\${name}Renderer.java"
    $content | Out-File -FilePath $filePath -Encoding UTF8
    Write-Output "Created $filePath"
}
Write-Output "`nAll renderer classes created successfully!"