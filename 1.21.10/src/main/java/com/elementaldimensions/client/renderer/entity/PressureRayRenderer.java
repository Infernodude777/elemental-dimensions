package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.aquatica.PressureRayEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PressureRayRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<PressureRayEntity, R> {
public PressureRayRenderer(EntityRendererFactory.Context context) {
super(context, new PressureRayModel());
}

public static class PressureRayModel extends GeoModel<PressureRayEntity> {
@Override
public Identifier getModelResource(PressureRayEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/pressure_ray.geo.json");
}

@Override
public Identifier getTextureResource(PressureRayEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/pressure_ray.png");
}

@Override
public Identifier getAnimationResource(PressureRayEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/pressure_ray.animation.json");
}
}
}
