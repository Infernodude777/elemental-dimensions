package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.boss.AbyssLeviathanEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AbyssLeviathanRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<AbyssLeviathanEntity, R> {
public AbyssLeviathanRenderer(EntityRendererFactory.Context context) {
super(context, new AbyssLeviathanModel());
}

public static class AbyssLeviathanModel extends GeoModel<AbyssLeviathanEntity> {
@Override
public Identifier getModelResource(AbyssLeviathanEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/abyss_leviathan.geo.json");
}

@Override
public Identifier getTextureResource(AbyssLeviathanEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/abyss_leviathan.png");
}

@Override
public Identifier getAnimationResource(AbyssLeviathanEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/abyss_leviathan.animation.json");
}
}
}
