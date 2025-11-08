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
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

/**
 * Ink Fluid - dark flowing liquid for Glyphic Archives dimension
 */
public abstract class InkFluid extends FlowableFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_INK_FLUID;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_INK_FLUID;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.INK_FLUID_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.INK_FLUID.getDefaultState();
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_INK_FLUID || fluid == ModFluids.FLOWING_INK_FLUID;
    }

    protected int getFlowSpeed(WorldView world) {
        return 3; // Medium speed
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 8; // Relatively slow
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
    }

    @Override
    protected int getMaxFlowDistance(WorldView world) {
        return 4; // Standard flow distance
    }

    @Override
    protected boolean isInfinite(net.minecraft.world.World world) {
        return true; // Infinite source fluid
    }

    public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !matchesType(fluid);
    }

    public static class Still extends InkFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends InkFluid {
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
