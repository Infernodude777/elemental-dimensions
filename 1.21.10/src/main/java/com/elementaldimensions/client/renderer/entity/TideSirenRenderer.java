package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.aquatica.TideSirenEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TideSirenRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<TideSirenEntity, R> {
public TideSirenRenderer(EntityRendererFactory.Context context) {
super(context, new TideSirenModel());
}

public static class TideSirenModel extends GeoModel<TideSirenEntity> {
@Override
public Identifier getModelResource(TideSirenEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/tide_siren.geo.json");
}

@Override
public Identifier getTextureResource(TideSirenEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/tide_siren.png");
}

@Override
public Identifier getAnimationResource(TideSirenEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/tide_siren.animation.json");
}
}
}
