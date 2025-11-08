package com.nikhil.elementaldimensions.fluid;

import com.nikhil.elementaldimensions.registry.ModBlocks;
import com.nikhil.elementaldimensions.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

/**
 * Lucid Water - flowing fluid for Dreaming Depths dimension
 */
public abstract class LucidWaterFluid extends FlowableFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_LUCID_WATER;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_LUCID_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.LUCID_WATER_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.LUCID_WATER.getDefaultState();
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_LUCID_WATER || fluid == ModFluids.FLOWING_LUCID_WATER;
    }

    protected int getFlowSpeed(WorldView world) {
        return 4; // Same as water
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1; // Standard water-like flow
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5; // Same as water
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        // No special block breaking behavior
    }

    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 4; // Same as water
    }

    @Override
    protected boolean isInfinite(World world) {
        return true; // Infinite like water
    }

    public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !matchesType(fluid);
    }

    // Still variant
    public static class Still extends LucidWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8; // Maximum level (source block)
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }

    // Flowing variant
    public static class Flowing extends LucidWaterFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }
}
