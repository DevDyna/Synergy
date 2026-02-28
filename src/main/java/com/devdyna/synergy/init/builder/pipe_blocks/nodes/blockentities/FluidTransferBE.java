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
public class FluidTransferBE extends NodeBaseBE implements FluidNodeType {

    public FluidTransferBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FluidTransferBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.FLUID_TRANSFER.get(), pos, blockState);
    }

    @Override
    protected void executeFluid(IFluidHandler input, IFluidHandler output) {
        moveFluids(input, output, 1);
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.FluidHandler.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutputPos();
    }

    @Override
    public FluidStack getFluidStack() {
        return getFirstFluid((IFluidHandler) getInputCap());
    }

    @Override
    public BlockPos defineInput() {
        return getInputPos();
    }

}
