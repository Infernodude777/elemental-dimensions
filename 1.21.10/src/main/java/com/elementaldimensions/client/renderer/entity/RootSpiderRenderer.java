package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.terradepths.RootSpiderEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RootSpiderRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<RootSpiderEntity, R> {
public RootSpiderRenderer(EntityRendererFactory.Context context) {
super(context, new RootSpiderModel());
}

public static class RootSpiderModel extends GeoModel<RootSpiderEntity> {
@Override
public Identifier getModelResource(RootSpiderEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/root_spider.geo.json");
}

@Override
public Identifier getTextureResource(RootSpiderEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/root_spider.png");
}

@Override
public Identifier getAnimationResource(RootSpiderEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/root_spider.animation.json");
}
}
}
