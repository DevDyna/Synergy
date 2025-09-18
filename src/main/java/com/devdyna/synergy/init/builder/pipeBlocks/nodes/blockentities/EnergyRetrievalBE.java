package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities;

import com.devdyna.synergy.api.node.builder.NodeBaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;

@SuppressWarnings({ "null" })
public class EnergyRetrievalBE extends NodeBaseBE {

    public EnergyRetrievalBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public EnergyRetrievalBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.ENERGY_RETRIEVAL.get(), pos, blockState);
    }

    @Override
    public BlockCapability<?, Direction> getCapType() {
        return Capabilities.EnergyStorage.BLOCK;
    }

    @Override
    public BlockPos defineOutput() {
        return getInput();
    }

}
