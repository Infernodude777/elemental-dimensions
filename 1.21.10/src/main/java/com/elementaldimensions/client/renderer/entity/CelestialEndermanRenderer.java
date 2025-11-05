package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.celestial.CelestialEndermanEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CelestialEndermanRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<CelestialEndermanEntity, R> {
public CelestialEndermanRenderer(EntityRendererFactory.Context context) {
super(context, new CelestialEndermanModel());
}

public static class CelestialEndermanModel extends GeoModel<CelestialEndermanEntity> {
@Override
public Identifier getModelResource(CelestialEndermanEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/celestial_enderman.geo.json");
}

@Override
public Identifier getTextureResource(CelestialEndermanEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/celestial_enderman.png");
}

@Override
public Identifier getAnimationResource(CelestialEndermanEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/celestial_enderman.animation.json");
}
}
}
