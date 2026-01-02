package com.devdyna.synergy.api.basebe.block;

import java.util.function.Function;

import com.devdyna.synergy.api.basebe.be.*;
import com.devdyna.synergy.api.beLogic.DropOnBreak;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * BlockStorage with MenuType integrated
 */
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

    @Override
    public void destroy(LevelAccessor levelaAccessor, BlockPos pos, BlockState state) {

        if (levelaAccessor.getBlockEntity(pos) instanceof DropOnBreak be) {
            be.drops();
        }

        super.destroy(levelaAccessor, pos, state);
    }

    /**
     * Event to allow to set animations or events when menu was opened
     */
    protected void onClickAction(BlockState state, Level level, BlockPos pos, Player player) {
    }

}
