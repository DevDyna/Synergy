package com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities;

import com.devdyna.synergy.api.node_pipe.EnergyNodeType;
import com.devdyna.synergy.api.node_pipe.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings({ "null" })
public class EnergyTransferBE extends NodeBaseBE implements EnergyNodeType {

    public EnergyTransferBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EnergyTransferBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ENERGY_TRANSFER.get(), pos, blockState);
    }

    @Override
    protected void executeEnergy(IEnergyStorage input, IEnergyStorage output) {
        moveEnergy(input, output, Math.min(input.getEnergyStored(),getStack()));
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.EnergyStorage.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutputPos();
    }

    @Override
    public BlockPos defineInput() {
        return getInputPos();
    }

}
