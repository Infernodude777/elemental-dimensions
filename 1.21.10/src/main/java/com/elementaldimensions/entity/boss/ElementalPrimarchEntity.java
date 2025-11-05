package com.elementaldimensions.entity.boss;

import com.elementaldimensions.config.ModConfig;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.boss.*;
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
public class ElementalPrimarchEntity extends HostileEntity implements GeoAnimatable {
    private final ServerBossBar bossBar;
    private int phase = 0;

    public ElementalPrimarchEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = new ServerBossBar(Text.literal("Elemental Primarch"), BossBar.Color.PURPLE, BossBar.Style.NOTCHED_20);
        this.experiencePoints = 1000;
    }
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public boolean isFireImmune() {
        return true;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 0.8, false));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1200.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 25.0)
                .add(EntityAttributes.GENERIC_ARMOR, 25.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 80.0);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getEntityWorld().isClient()) {
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            
            float healthPercent = this.getHealth() / this.getMaxHealth();
            if (healthPercent < 0.2f && phase < 4) {
                phase = 4;
            } else if (healthPercent < 0.4f && phase < 3) {
                phase = 3;
            } else if (healthPercent < 0.6f && phase < 2) {
                phase = 2;
            } else if (healthPercent < 0.8f && phase < 1) {
                phase = 1;
            }

            if (ModConfig.get().bossScalesWithPlayers) {
                int nearbyPlayers = (int) this.getEntityWorld().getPlayers().stream()
                        .filter(player -> player.squaredDistanceTo(this) < 4096)
                        .count();
                if (nearbyPlayers > 1) {
                    this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                            .setBaseValue(1200.0 + nearbyPlayers * 200);
                }
            }
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

