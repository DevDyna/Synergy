package com.devdyna.synergy.init.builder.automation.resource_gen.cobble.simple;

import com.devdyna.synergy.api.resource_gen.BaseCobbleRGBE;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleCobbleGenBE extends BaseCobbleRGBE {

    public SimpleCobbleGenBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.ticker = new Ticker(Common.SIMPLE_COBBLE_GEN_TICK_RATE.get());
    }

    public SimpleCobbleGenBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.SIMPLE_COBBLE_GEN.get(), pos, blockState);
    }

    @Override
    public int getItemAmount() {
        return Common.SIMPLE_COBBLE_GEN_ITEM_COUNT.get();
    }

}
