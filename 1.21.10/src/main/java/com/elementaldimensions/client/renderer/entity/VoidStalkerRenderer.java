package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.voidrealm.VoidStalkerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VoidStalkerRenderer extends GeoEntityRenderer<VoidStalkerEntity> {
public VoidStalkerRenderer(EntityRendererFactory.Context context) {
super(context, new VoidStalkerModel());
}

public static class VoidStalkerModel extends GeoModel<VoidStalkerEntity> {
@Override
public Identifier getModelResource(VoidStalkerEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/void_stalker.geo.json");
}

@Override
public Identifier getTextureResource(VoidStalkerEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/void_stalker.png");
}

@Override
public Identifier getAnimationResource(VoidStalkerEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/void_stalker.animation.json");
}
}
}
