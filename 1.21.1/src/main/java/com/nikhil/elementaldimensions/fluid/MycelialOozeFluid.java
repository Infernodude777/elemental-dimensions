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
 * Mycelial Ooze - slow, sticky fungal liquid for Mycelial Nexus dimension
 */
public abstract class MycelialOozeFluid extends FlowableFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_MYCELIAL_OOZE;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_MYCELIAL_OOZE;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.MYCELIAL_OOZE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.MYCELIAL_OOZE.getDefaultState();
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_MYCELIAL_OOZE || fluid == ModFluids.FLOWING_MYCELIAL_OOZE;
    }

    protected int getFlowSpeed(WorldView world) {
        return 2; // Very slow (like honey/lava)
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 2; // Loses level quickly
    }

    @Override
    public int getTickRate(WorldView world) {
        return 20; // Very slow flow
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
        return 2; // Very short flow distance
    }

    @Override
    protected boolean isInfinite(World world) {
        return false; // Finite fluid (no infinite sources)
    }

    public boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !matchesType(fluid);
    }

    public static class Still extends MycelialOozeFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends MycelialOozeFluid {
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
