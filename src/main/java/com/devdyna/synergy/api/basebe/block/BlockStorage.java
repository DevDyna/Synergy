package com.devdyna.synergy.api.basebe.block;

import java.util.function.Function;

import com.devdyna.synergy.api.basebe.be.*;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("null")
public abstract class BlockStorage extends BlockMenu {

    public BlockStorage(Properties p) {
        super(p);
    }

    protected abstract Function<Properties, Block> getFactory();

    @Override
    protected MapCodec<Block> codec() {
        return simpleCodec(getFactory());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {

        if (level.getBlockEntity(pos) instanceof BEStorage be) {
            onClickAction(state, level, pos, player);
            player.openMenu(new SimpleMenuProvider(be, be.getContainerName()), pos);
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    // TODO try destroy(LevelAccessor level, BlockPos pos, BlockState state)
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (state.getBlock() != newState.getBlock())
            if (level.getBlockEntity(pos) instanceof BEStorage be) {

                be.drops();

                level.updateNeighbourForOutputSignal(pos, this);
            }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    /**
     * Event to allow to set animations or events when menu was opened
     */
    protected abstract void onClickAction(BlockState state, Level level, BlockPos pos, Player player);

}
