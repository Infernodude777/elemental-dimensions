package com.elementaldimensions.entity.aquatica;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * Deep Anglerfish - Aggressive aquatic predator
 * Fast swimmer with high attack damage
 */
public class DeepAnglerfishEntity extends HostileEntity implements GeoAnimatable {

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public DeepAnglerfishEntity(EntityType<? extends HostileEntity> t, World w) {
		super(t, w);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2, false));
		this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.8));

		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 9.0)
			.add(EntityAttributes.GENERIC_ARMOR, 2.0)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 0, state -> {
			return state.setAndContinue(RawAnimation.begin().thenLoop("swim"));
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
