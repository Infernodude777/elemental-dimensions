package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.skyreach.PetalSpriteEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PetalSpriteRenderer extends GeoEntityRenderer<PetalSpriteEntity> {
public PetalSpriteRenderer(EntityRendererFactory.Context context) {
super(context, new PetalSpriteModel());
}

public static class PetalSpriteModel extends GeoModel<PetalSpriteEntity> {
@Override
public Identifier getModelResource(PetalSpriteEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/petal_sprite.geo.json");
}

@Override
public Identifier getTextureResource(PetalSpriteEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/petal_sprite.png");
}

@Override
public Identifier getAnimationResource(PetalSpriteEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/petal_sprite.animation.json");
}
}
}
