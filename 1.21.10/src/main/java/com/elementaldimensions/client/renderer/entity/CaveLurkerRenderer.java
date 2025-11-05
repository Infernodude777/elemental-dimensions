package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.terradepths.CaveLurkerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CaveLurkerRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<CaveLurkerEntity, R> {
public CaveLurkerRenderer(EntityRendererFactory.Context context) {
super(context, new CaveLurkerModel());
}

public static class CaveLurkerModel extends GeoModel<CaveLurkerEntity> {
@Override
public Identifier getModelResource(CaveLurkerEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/cave_lurker.geo.json");
}

@Override
public Identifier getTextureResource(CaveLurkerEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/cave_lurker.png");
}

@Override
public Identifier getAnimationResource(CaveLurkerEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/cave_lurker.animation.json");
}
}
}
