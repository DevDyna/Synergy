package com.devdyna.synergy.init.builder.automation.resource_gen.water.simple;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.Common;
import com.devdyna.synergy.api.blockfactories.resource_gen.BaseResourceGenBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class SimpleWaterGenBlock extends BaseResourceGenBlock {

    public SimpleWaterGenBlock(Properties properties) {
        super(properties);
    }

    public SimpleWaterGenBlock() {
        this(Properties.of());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SimpleWaterGenBE(pos, state);
    }

    @Override
    public void appendHoverText(ItemStack s, TooltipContext c, List<Component> t, TooltipFlag f) {
        t.add(water(Common.SIMPLE_WATER_GEN_FLUID_AMOUNT, Common.SIMPLE_WATER_GEN_TICK_RATE));
    }

}
