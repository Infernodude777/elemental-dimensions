package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.firelands.PyreHowlerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PyreHowlerRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<PyreHowlerEntity, R> {
public PyreHowlerRenderer(EntityRendererFactory.Context context) {
super(context, new PyreHowlerModel());
}

public static class PyreHowlerModel extends GeoModel<PyreHowlerEntity> {
@Override
public Identifier getModelResource(PyreHowlerEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/pyre_howler.geo.json");
}

@Override
public Identifier getTextureResource(PyreHowlerEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/pyre_howler.png");
}

@Override
public Identifier getAnimationResource(PyreHowlerEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/pyre_howler.animation.json");
}
}
}
