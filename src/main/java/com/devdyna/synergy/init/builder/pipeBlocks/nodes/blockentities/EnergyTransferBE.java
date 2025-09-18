package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

@SuppressWarnings({ "null" })
public class EnergyTransferBE extends NodeBaseBE {

    public EnergyTransferBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EnergyTransferBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ENERGY_TRANSFER.get(), pos, blockState);
    }

    @Override
    protected void executeEnergy(BlockPos inputPos, IEnergyStorage input, BlockPos outputPos, IEnergyStorage output) {
        moveEnergy(input, output, 100);
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.EnergyStorage.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getOutput();
    }

}
