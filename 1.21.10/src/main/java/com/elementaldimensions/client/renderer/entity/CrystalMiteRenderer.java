package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.terradepths.CrystalMiteEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrystalMiteRenderer extends GeoEntityRenderer<CrystalMiteEntity> {
public CrystalMiteRenderer(EntityRendererFactory.Context context) {
super(context, new CrystalMiteModel());
}

public static class CrystalMiteModel extends GeoModel<CrystalMiteEntity> {
@Override
public Identifier getModelResource(CrystalMiteEntity entity) {
return Identifier.of("elementaldimensions", "geo/entity/crystal_mite.geo.json");
}

@Override
public Identifier getTextureResource(CrystalMiteEntity entity) {
return Identifier.of("elementaldimensions", "textures/entity/crystal_mite.png");
}

@Override
public Identifier getAnimationResource(CrystalMiteEntity entity) {
return Identifier.of("elementaldimensions", "animations/entity/crystal_mite.animation.json");
}
}
}
