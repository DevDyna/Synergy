package com.devdyna.synergy.init.builder;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

@SuppressWarnings("null")
public class MachineFrame extends DirectionalBlock {

    public MachineFrame(Properties p) {
        super(p);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {

        return defaultBlockState()
                .setValue(FACING,
                        (c.getPlayer().isCrouching() ? c.getClickedFace().getOpposite() : c.getClickedFace()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(FACING);
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return simpleCodec((p) -> this);
    }

    // @Override
    // public void appendHoverText(ItemStack i, TooltipContext c, List<Component> t,
    // TooltipFlag f) {
    // t.add(Component.translatable(Main.ID + ".safe_building"));
    // }

}
