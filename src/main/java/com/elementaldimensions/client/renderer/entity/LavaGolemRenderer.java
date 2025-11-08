package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.ElementalDimensions;
import com.elementaldimensions.entity.firelands.LavaGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LavaGolemRenderer extends GeoEntityRenderer<LavaGolemEntity> {

	public LavaGolemRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new DefaultedEntityGeoModel<>(
			Identifier.of(ElementalDimensions.MOD_ID, "lava_golem")));
	}
}
