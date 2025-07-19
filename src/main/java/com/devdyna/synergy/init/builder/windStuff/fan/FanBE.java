package com.devdyna.synergy.init.builder.windStuff.fan;

import com.devdyna.synergy.api.coreBE.BaseBE;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FanBE extends BaseBE {

    public FanBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FanBE(BlockPos p, BlockState s) {
        super(zBlockEntities.FAN.get(), p, s);
    }

    @Override
    public void tickServer() {

        level.setBlockAndUpdate(getBlockPos(),
                getBlockState().setValue(BlockStateProperties.ENABLED, level.hasNeighborSignal(getBlockPos())));

        if (getBlockState().getValue(BlockStateProperties.ENABLED)) {

        }

    }

}
