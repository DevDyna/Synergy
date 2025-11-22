package com.devdyna.synergy.api.machine.core;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.coreBE.block.BlockMenu;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public abstract class BaseMachineBlock extends BlockMenu {

    public static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    public BaseMachineBlock(Properties p) {
        super(p
                .strength(1.0f)
                .destroyTime(1.0f)
                .sound(SoundType.METAL)
                .mapColor(MapColor.METAL));
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState().setValue(FACING, c.getHorizontalDirection().getOpposite()).setValue(ENABLED, false);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(FACING, ENABLED);
    }

}
