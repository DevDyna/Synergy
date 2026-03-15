package com.devdyna.synergy.init.builder.automation.resource_gen.cobble.advanced;

import com.devdyna.synergy.api.blockfactories.resource_gen.BaseCobbleRGBE;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.config.Common;
import com.devdyna.synergy.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedCobbleGenBE extends BaseCobbleRGBE {

    public AdvancedCobbleGenBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.ticker = new Ticker(Common.ADVANCED_COBBLE_GEN_TICK_RATE.get());
    }

    public AdvancedCobbleGenBE(BlockPos pos, BlockState blockState) {
        this(zBlockEntities.ADVANCED_COBBLE_GEN.get(), pos, blockState);
    }

    @Override
    public int getItemAmount() {
        return Common.ADVANCED_COBBLE_GEN_ITEM_COUNT.get();
    }

}
