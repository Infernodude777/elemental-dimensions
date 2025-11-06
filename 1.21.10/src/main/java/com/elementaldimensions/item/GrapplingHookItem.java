package com.elementaldimensions.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

/**
 * Grappling Hook - Advanced mobility item
 *
 * Features:
 * - Hooks to blocks within 32 block range
 * - Pulls player with momentum-based physics
 * - Smooth swing mechanics
 * - Cooldown system
 */
public class GrapplingHookItem extends Item {

	private static final double MAX_RANGE = 32.0;
	private static final double PULL_SPEED = 1.5;
	private static final int COOLDOWN_TICKS = 20; // 1 second

	public GrapplingHookItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (user.getItemCooldownManager().isCoolingDown(this)) {
			return TypedActionResult.fail(stack);
		}

		// Raycast to find hook point
		Vec3d start = user.getEyePos();
		Vec3d direction = user.getRotationVector();
		Vec3d end = start.add(direction.multiply(MAX_RANGE));

		BlockHitResult hitResult = world.raycast(new RaycastContext(
			start,
			end,
			RaycastContext.ShapeType.OUTLINE,
			RaycastContext.FluidHandling.NONE,
			user
		));

		if (hitResult.getType() == HitResult.Type.BLOCK) {
			BlockPos hitPos = hitResult.getBlockPos();
			Vec3d hookPoint = Vec3d.ofCenter(hitPos);

			// Calculate pull vector
			Vec3d playerPos = user.getPos();
			Vec3d pullVector = hookPoint.subtract(playerPos).normalize().multiply(PULL_SPEED);

			// Apply momentum with upward boost for swing effect
			Vec3d velocity = pullVector.add(0, 0.3, 0);
			user.setVelocity(velocity);
			user.velocityModified = true;

			// Play sound
			world.playSound(
				null,
				user.getX(),
				user.getY(),
				user.getZ(),
				SoundEvents.ITEM_CROSSBOW_SHOOT,
				SoundCategory.PLAYERS,
				1.0f,
				1.2f
			);

			// Apply cooldown
			user.getItemCooldownManager().set(this, COOLDOWN_TICKS);

			// Damage item
			if (!world.isClient()) {
				stack.damage(1, user, LivingEntity.getSlotForHand(hand));
			}

			// Statistics
			user.incrementStat(Stats.USED.getOrCreateStat(this));

			return TypedActionResult.success(stack, world.isClient());
		}

		// No valid hook point
		world.playSound(
			null,
			user.getX(),
			user.getY(),
			user.getZ(),
			SoundEvents.BLOCK_DISPENSER_FAIL,
			SoundCategory.PLAYERS,
			0.5f,
			1.0f
		);

		return TypedActionResult.fail(stack);
	}
}
