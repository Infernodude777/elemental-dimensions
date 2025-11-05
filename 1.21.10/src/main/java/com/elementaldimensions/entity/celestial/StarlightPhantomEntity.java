package com.elementaldimensions.entity.celestial;import net.minecraft.entity.*;import net.minecraft.entity.ai.goal.*;import net.minecraft.entity.attribute.*;import net.minecraft.entity.mob.*;import net.minecraft.entity.player.PlayerEntity;import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;`nimport software.bernie.geckolib.animatable.processing.AnimationController;`nimport software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;public class StarlightPhantomEntity extends HostileEntity implements GeoEntity {public StarlightPhantomEntity(EntityType<?extends HostileEntity>t,World w){super(t,w);}
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);@Override protected void initGoals(){this.goalSelector.add(1,new MeleeAttackGoal(this,1.1,false));this.targetSelector.add(1,new ActiveTargetGoal<>(this,PlayerEntity.class,true));}public static DefaultAttributeContainer.Builder createAttributes(){return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH,25.0).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.4);}
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

