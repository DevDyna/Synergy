package com.devdyna.synergy.api.node.builder;

import java.util.*;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.node.nodeLogic;
import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.pipe.pipeProperties;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.init.types.zBlockTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;

@SuppressWarnings("null")
public abstract class NodeBaseBE extends BlockEntity implements nodeLogic {

    private Set<BlockPos> failedRoutes;

    private BlockPos input;
    private BlockPos output;

    public NodeBaseBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public NodeBaseBE(BlockPos pos, BlockState blockState) {
        super(null, pos, blockState);
    }

    /**
     * Server only ticking
     * Useful for block events
     */
    public void tickServer() {
        this.failedRoutes = new HashSet<>();
        var input = getInputPos(getBlockState(), level, getBlockPos());

        if (input == null)
            return;// fail

        this.input = input;

        this.failedRoutes.add(input);

        var output = getOutputPos(level, getBlockPos());

        if (output == null)
            return;// fail

        this.output = output;

        // LogUtil.info("execute");
        execute(input, output);

        // refresh
        this.failedRoutes.removeAll(this.failedRoutes);
        this.input = null;
        this.output = null;
    }

    public BlockPos getInput() {
        return this.input;
    }

    public BlockPos getOutput() {
        return this.output;
    }

    protected abstract void execute(BlockPos input, BlockPos output);

    // protected abstract <T> boolean match(BlockCapability<T,?> block, Level level,
    // BlockPos current, BlockState state, Direction dir, BlockPos next,
    // BlockState neighbor);

    public abstract <T, R> BlockCapability<T, R> getCapType();

    public boolean match(Level level, BlockPos currentPos, BlockState currentState, Direction dir,
            BlockPos nextPos,
            BlockState nextState) {
        return getCapType().getCapability(level, nextPos,
                nextState,
                level.getBlockEntity(nextPos),
                dir) != null;
    }

    /**
     * Client only ticking
     * Useful for player events
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * 
     * Usefull for particles
     */
    public void tickBoth() {
    }

    /**
     * return the output blockpos
     */
    @Nullable
    public BlockPos getOutputPos(Level level, BlockPos start) {
        Queue<BlockPos> queue = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            if (failedRoutes.contains(current))
                continue;

            visited.add(current);

            BlockState state = level.getBlockState(current);

            for (Direction dir : Direction.values()) {
                BlockPos next = current.relative(dir);
                // LogUtil.info(dir+" -> "+state.getValue(pipeType.D2P(dir)).name());
                if (!visited.contains(next) &&
                        state.getValue(pipeType.D2P(dir)) == pipeProperties.TRUE) {
                    // check if pipe is connected and not included
                    BlockState neighbor = level.getBlockState(next);
                    // LogUtil.info("block "+neighbor.getBlock().toString());
                    if (match(level, current, state, dir, next, neighbor)) {
                        // found valid destination
                        // LogUtil.info("found");
                        return next;
                    }

                    if (neighbor.is(zBlockTag.CAN_CONNECT)) {
                        // valid pipe connection
                        // LogUtil.info("continue");
                        queue.add(next);
                    }
                }
            }
        }

        // LogUtil.info("fail");
        failedRoutes.add(start);
        return null;
    }

    /**
     * return the input blockpos
     */
    @Nullable
    public BlockPos getInputPos(BlockState state, Level level, BlockPos nodePos) {
        return nodePos.relative(state.getValue(nodeType.FACING));
    }
}
