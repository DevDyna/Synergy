package com.devdyna.synergy.api.beLogic;

import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public interface Connectable {

    abstract Map<Direction, BooleanProperty> PropByDir();

    abstract Boolean whenConnect(@Nullable Level level, BlockPos basePos, BlockPos neighborPos, BlockState baseState,
            BlockState neighborState);

    default Boolean updateWhen(@Nullable Level level, BlockPos basePos, BlockPos neighborPos, BlockState baseState,
            BlockState neighborState) {
        return whenConnect(level, basePos, neighborPos, baseState, neighborState);
    }

    /**
     * use on
     * <code>onPlace(BlockState s, Level l, BlockPos p, BlockState os, boolean m)</code>
     */
    default void updateOnPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            BlockState newState = state;
            for (Direction face : PropByDir().keySet()) {
                BlockPos neighborPos = pos.relative(face);
                BlockState neighborState = level.getBlockState(neighborPos);

                if (whenConnect(level, pos, neighborPos, oldState, neighborState)) {

                    if (updateWhen(level, pos, neighborPos, oldState, neighborState))
                        level.setBlock(neighborPos,
                                neighborState.setValue(PropByDir().get(face.getOpposite()), true),
                                Block.UPDATE_ALL);

                    newState = newState.setValue(PropByDir().get(face), true);
                }

                // newState = update(level, newState, face, neighborPos, neighborState);

            }
            level.setBlock(pos, newState, Block.UPDATE_ALL);
        }

    }

    /**
     * use on
     * <code>destroy(LevelAccessor level, BlockPos pos, BlockState state)</code>
     */
    default void updateOnDestroy(LevelAccessor l, BlockPos p, BlockState s) {
        for (Direction face : PropByDir().keySet()) {
            var offset = l.getBlockState(p.relative(face));
            if (whenConnect((Level) l, p, p.relative(face), s, offset)) {
                if (updateWhen((Level) l, p, p.relative(face), s, offset))
                    l.setBlock(p.relative(face),
                            offset.setValue(PropByDir().get(face.getOpposite()),
                                    false),
                            Block.UPDATE_ALL);
            }
        }

    }

    /**
     * DONT FORGET TO super.neighborChanged!
     * 
     * <br/>
     * <br/>
     * use on
     * <code>neighborChanged(BlockState s, Level l, BlockPos p, Block nb, BlockPos np, boolean f)</code>
     * 
     * 
     * 
     */
    default void updateOnNeighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
            BlockPos neighborPos, boolean movedByPiston) {

        var cache = state;

        for (Direction face : PropByDir().keySet()) {

            var offset = level.getBlockState(pos.relative(face));
            if (whenConnect(level, pos, pos.relative(face), state, offset)) {

                if (updateWhen(level, pos, pos.relative(face), state, offset))
                    level.setBlockAndUpdate(pos.relative(face),
                            offset.setValue(PropByDir().get(face.getOpposite()),
                                    true));

                state = state.setValue(PropByDir().get(face), true);

            } else
                state = state.setValue(PropByDir().get(face), false);

        }

        if (state != cache)
            level.setBlockAndUpdate(pos, state);

    }

}
