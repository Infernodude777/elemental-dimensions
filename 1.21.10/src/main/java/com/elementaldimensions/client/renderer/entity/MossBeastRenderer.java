package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.terradepths.MossBeastEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MossBeastRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<MossBeastEntity, R> {
public MossBeastRenderer(EntityRendererFactory.Context context) {
super(context, new MossBeastModel());
}

public static class MossBeastModel extends GeoModel<MossBeastEntity> {
@Override
public Identifier getModelResource(MossBeastEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/moss_beast.geo.json");
}

@Override
public Identifier getTextureResource(MossBeastEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/moss_beast.png");
}

@Override
public Identifier getAnimationResource(MossBeastEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/moss_beast.animation.json");
}
}
}
