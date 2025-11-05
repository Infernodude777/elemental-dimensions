package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.firelands.CinderElementalEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CinderElementalRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<CinderElementalEntity, R> {
public CinderElementalRenderer(EntityRendererFactory.Context context) {
super(context, new CinderElementalModel());
}

public static class CinderElementalModel extends GeoModel<CinderElementalEntity> {
@Override
public Identifier getModelResource(CinderElementalEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/cinder_elemental.geo.json");
}

@Override
public Identifier getTextureResource(CinderElementalEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/cinder_elemental.png");
}

@Override
public Identifier getAnimationResource(CinderElementalEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/cinder_elemental.animation.json");
}
}
}
