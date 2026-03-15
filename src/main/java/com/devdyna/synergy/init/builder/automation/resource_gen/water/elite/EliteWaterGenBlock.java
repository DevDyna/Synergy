package com.devdyna.synergy.init.builder.automation.resource_gen.water.elite;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.resource_gen.BaseResourceGenBlock;
import com.devdyna.synergy.config.Common;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class EliteWaterGenBlock extends BaseResourceGenBlock {

    public EliteWaterGenBlock(Properties properties) {
        super(properties);
    }

    public EliteWaterGenBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EliteWaterGenBE(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack s, TooltipContext c, List<Component> t, TooltipFlag f) {
        t.add(water(Common.ELITE_WATER_GEN_FLUID_AMOUNT, Common.ELITE_WATER_GEN_TICK_RATE));
    }

}
