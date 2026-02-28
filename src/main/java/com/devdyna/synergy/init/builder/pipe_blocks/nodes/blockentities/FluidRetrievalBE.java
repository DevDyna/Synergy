package com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities;

import com.devdyna.synergy.api.node.FluidNodeType;
import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

@SuppressWarnings({ "null" })
public class FluidRetrievalBE extends NodeBaseBE implements FluidNodeType {

    public FluidRetrievalBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FluidRetrievalBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.FLUID_RETRIEVAL.get(), pos, blockState);
    }

    @Override
    protected void executeFluid(IFluidHandler input, IFluidHandler output) {
        moveFluids(output, input, 1);
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.FluidHandler.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getInputPos();
    }

    @Override
    public FluidStack getFluidStack() {
        return getFirstFluid((IFluidHandler) getOutputCap());
    }

    @Override
    public BlockPos defineInput() {
        return getOutputPos();
    }

    @Override
    public Direction getInputDirection() {
        return getDirectionFromPath();
    }

    @Override
    public Direction getOutputDirection() {
        return getNodeDirection().getOpposite();
    }

}
