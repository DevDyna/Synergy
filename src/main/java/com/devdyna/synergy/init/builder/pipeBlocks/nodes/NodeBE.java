package com.devdyna.synergy.init.builder.pipeBlocks.nodes;

import com.devdyna.synergy.init.builder._core.BaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class NodeBE extends BaseBE {

    public NodeBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

        public NodeBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.PIPE_NODE.get(), pos, blockState);
    }

    @Override
    public void tickServer() {
        
    }
    
}
