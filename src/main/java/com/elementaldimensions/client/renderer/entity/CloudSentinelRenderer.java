package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.skyreach.CloudSentinelEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CloudSentinelRenderer extends GeoEntityRenderer<CloudSentinelEntity> {
public CloudSentinelRenderer(EntityRendererFactory.Context context) {
super(context, new CloudSentinelModel());
}

public static class CloudSentinelModel extends GeoModel<CloudSentinelEntity> {
@Override
public Identifier getModelResource(CloudSentinelEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/cloud_sentinel.geo.json");
}

@Override
public Identifier getTextureResource(CloudSentinelEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/cloud_sentinel.png");
}

@Override
public Identifier getAnimationResource(CloudSentinelEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/cloud_sentinel.animation.json");
}
}
}
