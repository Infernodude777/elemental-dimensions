package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.boss.WindSeraphEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WindSeraphRenderer extends GeoEntityRenderer<WindSeraphEntity> {
public WindSeraphRenderer(EntityRendererFactory.Context context) {
super(context, new WindSeraphModel());
}

public static class WindSeraphModel extends GeoModel<WindSeraphEntity> {
@Override
public Identifier getModelResource(WindSeraphEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/wind_seraph.geo.json");
}

@Override
public Identifier getTextureResource(WindSeraphEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/wind_seraph.png");
}

@Override
public Identifier getAnimationResource(WindSeraphEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/wind_seraph.animation.json");
}
}
}
