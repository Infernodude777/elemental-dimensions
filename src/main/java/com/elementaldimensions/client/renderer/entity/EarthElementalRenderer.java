package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.ElementalDimensions;
import com.elementaldimensions.entity.terradepths.EarthElementalEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EarthElementalRenderer extends GeoEntityRenderer<EarthElementalEntity> {

	public EarthElementalRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new DefaultedEntityGeoModel<>(
			Identifier.of(ElementalDimensions.MOD_ID, "earth_elemental")));
	}
}
