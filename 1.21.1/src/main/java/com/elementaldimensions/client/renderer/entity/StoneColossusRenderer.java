package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.boss.StoneColossusEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StoneColossusRenderer extends GeoEntityRenderer<StoneColossusEntity> {
public StoneColossusRenderer(EntityRendererFactory.Context context) {
super(context, new StoneColossusModel());
}

public static class StoneColossusModel extends GeoModel<StoneColossusEntity> {
@Override
public Identifier getModelResource(StoneColossusEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/stone_colossus.geo.json");
}

@Override
public Identifier getTextureResource(StoneColossusEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/stone_colossus.png");
}

@Override
public Identifier getAnimationResource(StoneColossusEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/stone_colossus.animation.json");
}
}
}
