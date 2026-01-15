package com.devdyna.synergy.init.builder.automation.tank;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FluidTankBE extends BETank {

    public FluidTankBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public FluidTankBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.FLUID_TANK.get(), pos, blockState);
    }

}
