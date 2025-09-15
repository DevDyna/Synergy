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

@SuppressWarnings({ "null", "unchecked" })
public class EnergyTransferBE extends NodeBaseBE {

    public EnergyTransferBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EnergyTransferBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ENERGY_TRANSFER.get(), pos, blockState);
    }

    @Override
    protected void execute(BlockPos input, BlockPos output) {
        var inState = level.getBlockState(input);
        var outState = level.getBlockState(output);
        var inBE = level.getBlockEntity(input);
        var outBE = level.getBlockEntity(output);
        if (inBE == null || outBE == null)
            return;
        var inCap = getCapType().getCapability(level, input, inState, inBE, null);
        var outCap = getCapType().getCapability(level, output, outState, outBE, null);
        if (inCap == null || outCap == null)
            return;

        provideFE((IEnergyStorage) inCap, (IEnergyStorage) outCap);
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.EnergyStorage.BLOCK;
    }

}
