package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.voidrealm.ShadowWraithEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShadowWraithRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<ShadowWraithEntity, R> {
public ShadowWraithRenderer(EntityRendererFactory.Context context) {
super(context, new ShadowWraithModel());
}

public static class ShadowWraithModel extends GeoModel<ShadowWraithEntity> {
@Override
public Identifier getModelResource(ShadowWraithEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/shadow_wraith.geo.json");
}

@Override
public Identifier getTextureResource(ShadowWraithEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/shadow_wraith.png");
}

@Override
public Identifier getAnimationResource(ShadowWraithEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/shadow_wraith.animation.json");
}
}
}
