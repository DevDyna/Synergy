package com.devdyna.synergy.init.builder.automation.resource_gen.cobble.elite;

import com.devdyna.synergy.api.resource_gen.BaseCobbleRGBE;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EliteCobbleGenBE extends BaseCobbleRGBE {

    public EliteCobbleGenBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.ticker = new Ticker(Common.ELITE_COBBLE_GEN_TICK_RATE.get());
    }

    public EliteCobbleGenBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ELITE_COBBLE_GEN.get(), pos, blockState);
    }

    @Override
    public int getItemAmount() {
        return Common.ELITE_COBBLE_GEN_ITEM_COUNT.get();
    }


}
