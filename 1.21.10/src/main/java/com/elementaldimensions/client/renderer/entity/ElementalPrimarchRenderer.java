package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.boss.ElementalPrimarchEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ElementalPrimarchRenderer extends GeoEntityRenderer<ElementalPrimarchEntity> {
public ElementalPrimarchRenderer(EntityRendererFactory.Context context) {
super(context, new ElementalPrimarchModel());
}

public static class ElementalPrimarchModel extends GeoModel<ElementalPrimarchEntity> {
@Override
public Identifier getModelResource(ElementalPrimarchEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/elemental_primarch.geo.json");
}

@Override
public Identifier getTextureResource(ElementalPrimarchEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/elemental_primarch.png");
}

@Override
public Identifier getAnimationResource(ElementalPrimarchEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/elemental_primarch.animation.json");
}
}
}
