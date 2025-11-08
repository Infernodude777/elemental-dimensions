package com.nikhil.elementaldimensions.item;

import com.nikhil.elementaldimensions.block.portal.DimensionalPortalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * Item that ignites dimensional portals when used on the frame
 */
public class PortalIgniterItem extends Item {

    private final Block portalBlock;
    private final Block frameBlock;

    public PortalIgniterItem(Block portalBlock, Block frameBlock, Settings settings) {
        super(settings);
        this.portalBlock = portalBlock;
        this.frameBlock = frameBlock;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos clickedPos = context.getBlockPos();
        BlockState clickedState = world.getBlockState(clickedPos);
        PlayerEntity player = context.getPlayer();

        // Check if clicked on frame block
        if (!clickedState.isOf(frameBlock)) {
            return ActionResult.PASS;
        }

        // Try to find and create portal
        if (tryCreatePortal(world, clickedPos)) {
            // Play success sound
            world.playSound(player, clickedPos, SoundEvents.BLOCK_PORTAL_TRIGGER,
                SoundCategory.BLOCKS, 1.0F, 1.0F);

            // Damage item (1 use per portal)
            if (player != null && !player.isCreative()) {
                context.getStack().decrement(1);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    private boolean tryCreatePortal(World world, BlockPos clickedPos) {
        if (!(portalBlock instanceof DimensionalPortalBlock portal)) {
            return false;
        }

        int width = portal.getPortalWidth();
        int height = portal.getPortalHeight();

        // Search for potential portal frames in all directions
        for (Direction.Axis axis : new Direction.Axis[]{Direction.Axis.X, Direction.Axis.Z}) {
            for (int dx = -width; dx <= 1; dx++) {
                for (int dy = -height; dy <= 1; dy++) {
                    BlockPos bottomLeft = axis == Direction.Axis.X
                        ? clickedPos.add(dx, dy, 0)
                        : clickedPos.add(0, dy, dx);

                    if (portal.isValidFrame(world, bottomLeft)) {
                        return portal.tryCreatePortal(world, bottomLeft);
                    }
                }
            }
        }

        return false;
    }
}
