package com.devdyna.synergy.init.builder._core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BaseBE extends BlockEntity {

    public BaseBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    /** Server only ticking */
    public void tickServer() {
    }

    /** Client only ticking */
    public void tickClient() {
    }

    /** Client and Server ticking */
    public void tickBoth() {
    }

}
