package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.skyreach.WindHawkEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WindHawkRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<WindHawkEntity, R> {
public WindHawkRenderer(EntityRendererFactory.Context context) {
super(context, new WindHawkModel());
}

public static class WindHawkModel extends GeoModel<WindHawkEntity> {
@Override
public Identifier getModelResource(WindHawkEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/wind_hawk.geo.json");
}

@Override
public Identifier getTextureResource(WindHawkEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/wind_hawk.png");
}

@Override
public Identifier getAnimationResource(WindHawkEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/wind_hawk.animation.json");
}
}
}
