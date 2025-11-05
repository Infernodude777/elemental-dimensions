package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.boss.FireTitanEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FireTitanRenderer extends GeoEntityRenderer<FireTitanEntity> {
public FireTitanRenderer(EntityRendererFactory.Context context) {
super(context, new FireTitanModel());
}

public static class FireTitanModel extends GeoModel<FireTitanEntity> {
@Override
public Identifier getModelResource(FireTitanEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/fire_titan.geo.json");
}

@Override
public Identifier getTextureResource(FireTitanEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/fire_titan.png");
}

@Override
public Identifier getAnimationResource(FireTitanEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/fire_titan.animation.json");
}
}
}
