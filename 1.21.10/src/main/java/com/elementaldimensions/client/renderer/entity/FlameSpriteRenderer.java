package com.elementaldimensions.client.renderer.entity;

import com.elementaldimensions.entity.firelands.FlameSpriteEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FlameSpriteRenderer<R extends net.minecraft.client.render.entity.state.EntityRenderState & software.bernie.geckolib.renderer.GeoRenderState> extends GeoEntityRenderer<FlameSpriteEntity, R> {
	public FlameSpriteRenderer(EntityRendererFactory.Context context) {
		super(context, new FlameSpriteModel());
	}
	
	public static class FlameSpriteModel extends GeoModel<FlameSpriteEntity> {
		@Override
		public Identifier getModelResource(FlameSpriteEntity entity) {
			return Identifier.of("elementaldimensions", "geo/entity/flame_sprite.geo.json");
		}
		
		@Override
		public Identifier getTextureResource(FlameSpriteEntity entity) {
			return Identifier.of("elementaldimensions", "textures/entity/flame_sprite.png");
		}
		
		@Override
		public Identifier getAnimationResource(FlameSpriteEntity entity) {
			return Identifier.of("elementaldimensions", "animations/entity/flame_sprite.animation.json");
		}
	}
}
