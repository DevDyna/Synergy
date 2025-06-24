package com.devdyna.synergy.api.node.builder;

import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.api.node.nodeLogic;

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
