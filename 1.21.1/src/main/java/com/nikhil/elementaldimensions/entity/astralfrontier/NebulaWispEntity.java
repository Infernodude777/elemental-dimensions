package com.nikhil.elementaldimensions.entity.astralfrontier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class NebulaWispEntity extends AmbientEntity {

    public NebulaWispEntity(EntityType<? extends AmbientEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 2;
    }

    public static DefaultAttributeContainer.Builder createNebulaWispAttributes() {
        return AmbientEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 9.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.55)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.24);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(3, new LookAroundGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ALLAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ALLAY_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.3F;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
