package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.firelands.MagmaBruteEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MagmaBruteRenderer extends GeoEntityRenderer<MagmaBruteEntity> {
public MagmaBruteRenderer(EntityRendererFactory.Context context) {
super(context, new MagmaBruteModel());
}

public static class MagmaBruteModel extends GeoModel<MagmaBruteEntity> {
@Override
public Identifier getModelResource(MagmaBruteEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/magma_brute.geo.json");
}

@Override
public Identifier getTextureResource(MagmaBruteEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/magma_brute.png");
}

@Override
public Identifier getAnimationResource(MagmaBruteEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/magma_brute.animation.json");
}
}
}
