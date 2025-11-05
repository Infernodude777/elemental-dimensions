package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.aquatica.AbyssCrabEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AbyssCrabRenderer extends GeoEntityRenderer<AbyssCrabEntity> {
public AbyssCrabRenderer(EntityRendererFactory.Context context) {
super(context, new AbyssCrabModel());
}

public static class AbyssCrabModel extends GeoModel<AbyssCrabEntity> {
@Override
public Identifier getModelResource(AbyssCrabEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/abyss_crab.geo.json");
}

@Override
public Identifier getTextureResource(AbyssCrabEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/abyss_crab.png");
}

@Override
public Identifier getAnimationResource(AbyssCrabEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/abyss_crab.animation.json");
}
}
}
