package com.elementaldimensions.entity.aquatica;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
public class CoralGuardianEntity extends HostileEntity implements GeoAnimatable {
	public CoralGuardianEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.setAir(300);
	}
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.1, false));
		this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.9));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
		this.goalSelector.add(5, new LookAroundGoal(this));
		
		this.targetSelector.add(1, new RevengeGoal(this));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 22.0)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.25)
				.add(EntityAttributes.ATTACK_DAMAGE, 6.0)
				.add(EntityAttributes.FOLLOW_RANGE, 28.0)
				.add(EntityAttributes.ARMOR, 4.0);
	}

	@Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { 
controllers.add(new AnimationController<>(this, "controller", 0, state -> {
return state.setAndContinue(RawAnimation.begin().thenLoop("idle"));
}));
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

