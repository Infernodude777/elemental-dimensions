package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.skyreach.SkyWispEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkyWispRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<SkyWispEntity, R> {
public SkyWispRenderer(EntityRendererFactory.Context context) {
super(context, new SkyWispModel());
}

public static class SkyWispModel extends GeoModel<SkyWispEntity> {
@Override
public Identifier getModelResource(SkyWispEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/sky_wisp.geo.json");
}

@Override
public Identifier getTextureResource(SkyWispEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/sky_wisp.png");
}

@Override
public Identifier getAnimationResource(SkyWispEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/sky_wisp.animation.json");
}
}
}
