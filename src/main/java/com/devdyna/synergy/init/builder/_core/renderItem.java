package com.devdyna.synergy.init.builder._core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class renderItem extends BaseBE {

    public renderItem(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static float angle;

    public float getAngle() {
        angle += 0.5f;
        return angle >= 360 ? angle = 0 : angle;
    }
}
