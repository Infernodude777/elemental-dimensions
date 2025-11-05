package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.aquatica.BubbleWispEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BubbleWispRenderer extends GeoEntityRenderer<BubbleWispEntity> {
public BubbleWispRenderer(EntityRendererFactory.Context context) {
super(context, new BubbleWispModel());
}

public static class BubbleWispModel extends GeoModel<BubbleWispEntity> {
@Override
public Identifier getModelResource(BubbleWispEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/bubble_wisp.geo.json");
}

@Override
public Identifier getTextureResource(BubbleWispEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/bubble_wisp.png");
}

@Override
public Identifier getAnimationResource(BubbleWispEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/bubble_wisp.animation.json");
}
}
}
