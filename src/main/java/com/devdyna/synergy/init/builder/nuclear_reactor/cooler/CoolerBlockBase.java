package com.devdyna.synergy.init.builder.nuclear_reactor.cooler;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.BlockAbilities.tooltips.complex.ICooler;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public abstract class CoolerBlockBase extends Block implements ICooler {

    public CoolerBlockBase() {
        super(Properties.of().strength(1.0f).destroyTime(1.0f).sound(SoundType.CHAIN).mapColor(MapColor.METAL));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(BlockStateProperties.ENABLED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState().setValue(BlockStateProperties.ENABLED, activeWhen(defaultBlockState(), c.getLevel(),
                c.getClickedPos()));
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader levelrReader, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, levelrReader, pos, neighbor);
        if (levelrReader instanceof Level level)
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.ENABLED, activeWhen(state, level, pos)));
    }

    /**
     * Override to set OFF-ON cooling condition
     */
    public abstract boolean activeWhen(BlockState state, Level level, BlockPos pos);

    /**
     * Override to set ON cooling <br/>
     * <br/>
     * <strong>NOTE:</strong> This should return a negative value <i>else will
     * increase heat value</i>!
     */
    public abstract int getActiveCooling();

    // /**
    // * Override to set OFF cooling
    // */
    public int getBaseCooling() {
        return 0;
    }

    public abstract Component conditions();

    public boolean isActive(Level level, BlockPos pos) {
        return level.getBlockState(pos).getValue(BlockStateProperties.ENABLED);
    }

}
