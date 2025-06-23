package com.devdyna.synergy.init.builder.pipeBlocks.nodes;

import com.devdyna.synergy.init.builder._core.BaseBE;
import com.devdyna.synergy.init.builder._core.pipes.nodeLogic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class NodeBaseBE extends BaseBE implements nodeLogic{

    public NodeBaseBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public NodeBaseBE(BlockPos pos, BlockState blockState) {
        super(null, pos, blockState);
    }

    @Override
    public void tickServer() {
    }

    public abstract int getTickDelay();

}
