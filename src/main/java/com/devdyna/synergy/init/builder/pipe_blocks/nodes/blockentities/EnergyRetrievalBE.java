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

@SuppressWarnings("null")
public class EnergyRetrievalBE extends NodeBaseBE implements EnergyNodeType {

    public EnergyRetrievalBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EnergyRetrievalBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ENERGY_RETRIEVAL.get(), pos, blockState);
    }

    @Override
    protected void executeEnergy(IEnergyStorage input, IEnergyStorage output) {
        moveEnergy(output, input, Math.min(output.getEnergyStored(),getStack()));
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.EnergyStorage.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getInputPos();
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
