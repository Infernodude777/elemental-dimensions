package com.elementaldimensions.entity.celestial;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;`nimport software.bernie.geckolib.animatable.processing.AnimationController;`nimport software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
public class NebulaWraithEntity extends HostileEntity implements GeoEntity {
	public NebulaWraithEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.1, false));
		this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
		this.goalSelector.add(5, new LookAroundGoal(this));
		
		this.targetSelector.add(1, new RevengeGoal(this));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
				.add(EntityAttributes.GENERIC_ARMOR, 5.0);
	}

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

