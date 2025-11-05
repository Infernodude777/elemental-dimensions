package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.celestial.VoidShulkerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VoidShulkerRenderer extends GeoEntityRenderer<VoidShulkerEntity> {
public VoidShulkerRenderer(EntityRendererFactory.Context context) {
super(context, new VoidShulkerModel());
}

public static class VoidShulkerModel extends GeoModel<VoidShulkerEntity> {
@Override
public Identifier getModelResource(VoidShulkerEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/void_shulker.geo.json");
}

@Override
public Identifier getTextureResource(VoidShulkerEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/void_shulker.png");
}

@Override
public Identifier getAnimationResource(VoidShulkerEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/void_shulker.animation.json");
}
}
}
