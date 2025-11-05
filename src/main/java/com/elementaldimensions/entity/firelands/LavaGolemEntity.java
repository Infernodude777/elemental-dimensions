package com.elementaldimensions.entity.firelands;

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
 * Lava Golem - Powerful firelands tank
 * Slow but heavily armored fire entity
 */
public class LavaGolemEntity extends HostileEntity implements GeoAnimatable {
	
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	public LavaGolemEntity(EntityType<? extends HostileEntity> t, World w) {
		super(t, w);
		this.setFireTicks(-1); // Immune to fire
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 0.8, false));
		this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
		this.goalSelector.add(5, new LookAroundGoal(this));
		
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0)
			.add(EntityAttributes.GENERIC_ARMOR, 8.0)
			.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.6)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.18);
	}
	
	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controller", 0, state -> {
			if (state.isMoving()) {
				return state.setAndContinue(RawAnimation.begin().thenLoop("walk"));
			}
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
	
	@Override
	public boolean isFireImmune() {
		return true;
	}
}
