package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.firelands.EmberSerpentEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EmberSerpentRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<EmberSerpentEntity, R> {
public EmberSerpentRenderer(EntityRendererFactory.Context context) {
super(context, new EmberSerpentModel());
}

public static class EmberSerpentModel extends GeoModel<EmberSerpentEntity> {
@Override
public Identifier getModelResource(EmberSerpentEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/ember_serpent.geo.json");
}

@Override
public Identifier getTextureResource(EmberSerpentEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/ember_serpent.png");
}

@Override
public Identifier getAnimationResource(EmberSerpentEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/ember_serpent.animation.json");
}
}
}
