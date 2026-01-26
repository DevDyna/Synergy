package com.devdyna.synergy.init.builder.automation.resource_gen.water.advanced;

import com.devdyna.synergy.api.resource_gen.BaseWaterRGBE;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedWaterGenBE extends BaseWaterRGBE {

    public AdvancedWaterGenBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.ticker = new Ticker(Common.ADVANCED_WATER_GEN_TICK_RATE.get());
    }

    public AdvancedWaterGenBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ADVANCED_WATER_GEN.get(), pos, blockState);
    }

    @Override
    public int getFluidAmount() {
        return Common.ADVANCED_WATER_GEN_FLUID_AMOUNT.get();
    }

    @Override
    public int getFluidCapacity() {
        return Common.ADVANCED_WATER_GEN_CAPACITY.get();
    }

}
