package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.celestial.StarlightPhantomEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StarlightPhantomRenderer extends GeoEntityRenderer<StarlightPhantomEntity> {
public StarlightPhantomRenderer(EntityRendererFactory.Context context) {
super(context, new StarlightPhantomModel());
}

public static class StarlightPhantomModel extends GeoModel<StarlightPhantomEntity> {
@Override
public Identifier getModelResource(StarlightPhantomEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/starlight_phantom.geo.json");
}

@Override
public Identifier getTextureResource(StarlightPhantomEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/starlight_phantom.png");
}

@Override
public Identifier getAnimationResource(StarlightPhantomEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/starlight_phantom.animation.json");
}
}
}
