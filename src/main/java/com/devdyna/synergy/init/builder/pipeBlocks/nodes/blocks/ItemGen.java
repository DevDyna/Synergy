package com.devdyna.synergy.init.builder.pipeBlocks.nodes.blocks;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.builder.pipeBlocks.nodes.NodeBaseBlock;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.ItemGenBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ItemGen extends NodeBaseBlock {

    public ItemGen() {
    }
    
    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new ItemGenBE(p, s);
    }

}
