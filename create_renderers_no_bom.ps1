# Entity list with full renderer details
$entities = @(
    @{Name="EmberSerpent"; Package="firelands"; Entity="EmberSerpentEntity"},
    @{Name="PyreHowler"; Package="firelands"; Entity="PyreHowlerEntity"},
    @{Name="MagmaBrute"; Package="firelands"; Entity="MagmaBruteEntity"},
    @{Name="CinderElemental"; Package="firelands"; Entity="CinderElementalEntity"},
    @{Name="AbyssCrab"; Package="aquatica"; Entity="AbyssCrabEntity"},
    @{Name="TideSiren"; Package="aquatica"; Entity="TideSirenEntity"},
    @{Name="PressureRay"; Package="aquatica"; Entity="PressureRayEntity"},
    @{Name="BubbleWisp"; Package="aquatica"; Entity="BubbleWispEntity"},
    @{Name="CoralGuardian"; Package="aquatica"; Entity="CoralGuardianEntity"},
    @{Name="StoneGolem"; Package="terradepths"; Entity="StoneGolemEntity"},
    @{Name="MossBeast"; Package="terradepths"; Entity="MossBeastEntity"},
    @{Name="CrystalMite"; Package="terradepths"; Entity="CrystalMiteEntity"},
    @{Name="RootSpider"; Package="terradepths"; Entity="RootSpiderEntity"},
    @{Name="CaveLurker"; Package="terradepths"; Entity="CaveLurkerEntity"},
    @{Name="WindHawk"; Package="skyreach"; Entity="WindHawkEntity"},
    @{Name="CloudSentinel"; Package="skyreach"; Entity="CloudSentinelEntity"},
    @{Name="ThunderHawk"; Package="skyreach"; Entity="ThunderHawkEntity"},
    @{Name="SkyWisp"; Package="skyreach"; Entity="SkyWispEntity"},
    @{Name="PetalSprite"; Package="skyreach"; Entity="PetalSpriteEntity"},
    @{Name="CelestialEnderman"; Package="celestial"; Entity="CelestialEndermanEntity"},
    @{Name="StarlightPhantom"; Package="celestial"; Entity="StarlightPhantomEntity"},
    @{Name="VoidShulker"; Package="celestial"; Entity="VoidShulkerEntity"},
    @{Name="NebulaWraith"; Package="celestial"; Entity="NebulaWraithEntity"},
    @{Name="ShadowWraith"; Package="voidrealm"; Entity="ShadowWraithEntity"},
    @{Name="VoidStalker"; Package="voidrealm"; Entity="VoidStalkerEntity"},
    @{Name="FireTitan"; Package="boss"; Entity="FireTitanEntity"},
    @{Name="AbyssLeviathan"; Package="boss"; Entity="AbyssLeviathanEntity"},
    @{Name="StoneColossus"; Package="boss"; Entity="StoneColossusEntity"},
    @{Name="WindSeraph"; Package="boss"; Entity="WindSeraphEntity"},
    @{Name="ElementalPrimarch"; Package="boss"; Entity="ElementalPrimarchEntity"},
    @{Name="VoidLord"; Package="boss"; Entity="VoidLordEntity"}
)
foreach ($entity in $entities) {
    $name = $entity.Name
    $package = $entity.Package
    $entityClass = $entity.Entity
    $snake_case = ($name -creplace '([A-Z])', '_$1').ToLower().TrimStart('_')
    $content = @"
package com.elementaldimensions.client.renderer.entity;
import com.elementaldimensions.entity.${package}.${entityClass};
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
public class ${name}Renderer extends GeoEntityRenderer<${entityClass}> {
    public ${name}Renderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ${name}Model());
    }
    public static class ${name}Model extends GeoModel<${entityClass}> {
        @Override
        public Identifier getModelResource(${entityClass} animatable) {
            return Identifier.of("elementaldimensions", "geo/entity/${snake_case}.geo.json");
        }
        @Override
        public Identifier getTextureResource(${entityClass} animatable) {
            return Identifier.of("elementaldimensions", "textures/entity/${snake_case}.png");
        }
        @Override
        public Identifier getAnimationResource(${entityClass} animatable) {
            return Identifier.of("elementaldimensions", "animations/entity/${snake_case}.animation.json");
        }
    }
}
"@
    $filePath = "src\main\java\com\elementaldimensions\client\renderer\entity\${name}Renderer.java"
    [System.IO.File]::WriteAllText($filePath, $content, (New-Object System.Text.UTF8Encoding $False))
    Write-Output "Created ${name}Renderer.java"
}
Write-Output "`nAll renderer classes created successfully without BOM!"