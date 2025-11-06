# Script to create Undead Realm entity classes
$entityPath = "c:\Users\Nikhil\Desktop\elemental_dimensions\1.21.1\src\main\java\com\elementaldimensions\entity\undead"
if (-not (Test-Path $entityPath)) {
    New-Item -ItemType Directory -Force -Path $entityPath | Out-Null
}
# Entity definitions: Name, Health, Speed, Damage, Armor
$entities = @(
    @{Name="SkeletonArcherEntity"; Health=15.0; Speed=0.25; Damage=2.5; Armor=0.0},
    @{Name="GhostEntity"; Health=12.0; Speed=0.3; Damage=3.0; Armor=0.0},
    @{Name="WraithEntity"; Health=18.0; Speed=0.28; Damage=4.0; Armor=1.0},
    @{Name="BansheeEntity"; Health=16.0; Speed=0.26; Damage=3.5; Armor=0.0},
    @{Name="NecromanticSkeleton"; Health=22.0; Speed=0.24; Damage=5.0; Armor=3.0},
    @{Name="PhantomKnightEntity"; Health=25.0; Speed=0.27; Damage=6.0; Armor=4.0},
    @{Name="GraveyardGuardianEntity"; Health=30.0; Speed=0.22; Damage=7.0; Armor=5.0},
    @{Name="SoulStealerEntity"; Health=20.0; Speed=0.29; Damage=4.5; Armor=2.0},
    @{Name="BoneDragonEntity"; Health=50.0; Speed=0.31; Damage=10.0; Armor=8.0}
)
foreach ($entity in $entities) {
    $className = $entity.Name
    $health = $entity.Health
    $speed = $entity.Speed
    $damage = $entity.Damage
    $armor = $entity.Armor
    $content = @"
package com.elementaldimensions.entity.undead;
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
import software.bernie.geckolib.util.GeckoLibUtil;
public class $className extends HostileEntity implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public $className(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }
    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, $health)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, $speed)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, $damage)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_ARMOR, $armor);
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { }
    @Override
    public double getTick(Object object) {
        return this.age;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
"@
    $filePath = "$entityPath\$className.java"
    $content | Out-File -FilePath $filePath -Encoding UTF8
    Write-Output "Created entity: $className"
}
Write-Output "All Undead Realm entities created successfully!"