package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBaseBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.ItemTransferBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ItemTranfer extends NodeBaseBlock {

    public ItemTranfer() {
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new ItemTransferBE(p, s);
    }

}
