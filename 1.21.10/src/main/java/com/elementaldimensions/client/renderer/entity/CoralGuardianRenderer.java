package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.aquatica.CoralGuardianEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CoralGuardianRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<CoralGuardianEntity, R> {
public CoralGuardianRenderer(EntityRendererFactory.Context context) {
super(context, new CoralGuardianModel());
}

public static class CoralGuardianModel extends GeoModel<CoralGuardianEntity> {
@Override
public Identifier getModelResource(CoralGuardianEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/coral_guardian.geo.json");
}

@Override
public Identifier getTextureResource(CoralGuardianEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/coral_guardian.png");
}

@Override
public Identifier getAnimationResource(CoralGuardianEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/coral_guardian.animation.json");
}
}
}
