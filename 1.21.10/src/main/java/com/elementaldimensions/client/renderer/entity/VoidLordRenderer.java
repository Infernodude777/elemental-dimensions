package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.boss.VoidLordEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VoidLordRenderer extends GeoEntityRenderer<VoidLordEntity> {
public VoidLordRenderer(EntityRendererFactory.Context context) {
super(context, new VoidLordModel());
}

public static class VoidLordModel extends GeoModel<VoidLordEntity> {
@Override
public Identifier getModelResource(VoidLordEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/void_lord.geo.json");
}

@Override
public Identifier getTextureResource(VoidLordEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/void_lord.png");
}

@Override
public Identifier getAnimationResource(VoidLordEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/void_lord.animation.json");
}
}
}
