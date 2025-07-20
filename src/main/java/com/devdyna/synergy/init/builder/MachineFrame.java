package com.devdyna.synergy.init.builder;

import java.util.List;

import com.devdyna.synergy.Main;
import com.mojang.serialization.MapCodec;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class MachineFrame extends DirectionalBlock {

    public MachineFrame(Properties p) {
        super(p);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {

        return this.defaultBlockState()
                .setValue(BlockStateProperties.FACING,
                        (c.getPlayer().isCrouching() ? c.getClickedFace().getOpposite() : c.getClickedFace()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.FACING);
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return simpleCodec((p) -> new MachineFrame(p));
    }

        @Override
    public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
            TooltipFlag f) {
        t.add(Component.translatable(Main.ID + ".safe_building"));
    }

}
