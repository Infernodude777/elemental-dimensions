package com.elementaldimensions.entity.boss;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
public class FireTitanEntity extends HostileEntity implements GeoAnimatable {
	private final ServerBossBar bossBar;
	private int phase = 0;
	
	public FireTitanEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.bossBar = new ServerBossBar(Text.literal("Fire Titan"), BossBar.Color.RED, BossBar.Style.NOTCHED_10);
		this.experiencePoints = 500;
	}
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	public boolean isFireImmune() {
		return true;
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new MeleeAttackGoal(this, 0.8, false));
		this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 32.0f));
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 600.0)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.25)
				.add(EntityAttributes.ATTACK_DAMAGE, 15.0)
				.add(EntityAttributes.ARMOR, 15.0)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
				.add(EntityAttributes.FOLLOW_RANGE, 64.0);
	}
	
	@Override
	public void tick() {
		super.tick();
		if (!this.getEntityWorld().isClient()) {
			this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
			updatePhase();
		}
	}
	
	private void updatePhase() {
		float healthPercent = this.getHealth() / this.getMaxHealth();
		if (healthPercent < 0.33f && phase < 2) {
			phase = 2; // Lava swarm phase
		} else if (healthPercent < 0.66f && phase < 1) {
			phase = 1; // Pillar slam phase
		}
	}

@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.addPlayer(player);
	}
	
	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.removePlayer(player);
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

