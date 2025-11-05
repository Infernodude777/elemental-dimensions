package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.skyreach.ThunderHawkEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ThunderHawkRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<ThunderHawkEntity, R> {
public ThunderHawkRenderer(EntityRendererFactory.Context context) {
super(context, new ThunderHawkModel());
}

public static class ThunderHawkModel extends GeoModel<ThunderHawkEntity> {
@Override
public Identifier getModelResource(ThunderHawkEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/thunder_hawk.geo.json");
}

@Override
public Identifier getTextureResource(ThunderHawkEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/thunder_hawk.png");
}

@Override
public Identifier getAnimationResource(ThunderHawkEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/thunder_hawk.animation.json");
}
}
}
