package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.celestial.NebulaWraithEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NebulaWraithRenderer extends GeoEntityRenderer<NebulaWraithEntity> {
public NebulaWraithRenderer(EntityRendererFactory.Context context) {
super(context, new NebulaWraithModel());
}

public static class NebulaWraithModel extends GeoModel<NebulaWraithEntity> {
@Override
public Identifier getModelResource(NebulaWraithEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/nebula_wraith.geo.json");
}

@Override
public Identifier getTextureResource(NebulaWraithEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/nebula_wraith.png");
}

@Override
public Identifier getAnimationResource(NebulaWraithEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/nebula_wraith.animation.json");
}
}
}
