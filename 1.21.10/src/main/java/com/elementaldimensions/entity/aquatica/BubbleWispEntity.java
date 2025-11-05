package com.elementaldimensions.entity.aquatica;
import net.minecraft.entity.*;import net.minecraft.entity.ai.goal.*;import net.minecraft.entity.attribute.*;import net.minecraft.entity.passive.*;import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;`nimport software.bernie.geckolib.animatable.processing.AnimationController;`nimport software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;public class BubbleWispEntity extends PassiveEntity implements GeoEntity {public BubbleWispEntity(EntityType<?extends PassiveEntity>t,World w){super(t,w);}
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);@Override protected void initGoals(){this.goalSelector.add(1,new SwimGoal(this));this.goalSelector.add(2,new WanderAroundFarGoal(this,0.5));}public static DefaultAttributeContainer.Builder createAttributes(){return PassiveEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,6.0);}@Override public PassiveEntity createChild(net.minecraft.server.world.ServerWorld w,PassiveEntity e){return null;}
	@Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { 
controllers.add(new AnimationController<>(this, "controller", state -> state.isMoving() ? state.setAndContinue(RawAnimation.begin().thenLoop("walk")) : state.setAndContinue(RawAnimation.begin().thenLoop("idle"))));
}

		@Override
	public double getTick(Object object) {
		return this.age;
	}

@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}
}

