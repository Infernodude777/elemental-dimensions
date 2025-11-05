package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.terradepths.StoneGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StoneGolemRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<StoneGolemEntity, R> {
public StoneGolemRenderer(EntityRendererFactory.Context context) {
super(context, new StoneGolemModel());
}

public static class StoneGolemModel extends GeoModel<StoneGolemEntity> {
@Override
public Identifier getModelResource(StoneGolemEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/stone_golem.geo.json");
}

@Override
public Identifier getTextureResource(StoneGolemEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/stone_golem.png");
}

@Override
public Identifier getAnimationResource(StoneGolemEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/stone_golem.animation.json");
}
}
}
