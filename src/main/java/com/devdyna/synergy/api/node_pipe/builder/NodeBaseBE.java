package com.devdyna.synergy.api.node_pipe.builder;

import java.util.*;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.beLogic.DirectionBasedItemHandler;
import com.devdyna.synergy.api.beLogic.RestrictedItemHandler;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.node_pipe.FluidNodeType;
import com.devdyna.synergy.api.node_pipe.ItemNodeType;
import com.devdyna.synergy.init.builder.pipe_blocks.NodePipeBlock;
import com.devdyna.synergy.init.types.zBlockTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public abstract class NodeBaseBE extends BlockEntity {

    public NodeBaseBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    private Set<BlockPos> failedRoutes;
    private BlockPos input;
    private BlockPos output;
    private Object inCap;
    private Object outCap;

    private Direction outputDir;
    // private BlockEntity inBE;
    // private BlockEntity outBE;

    /**
     * Client only ticking
     * <br/>
     * <br/>
     * Useful for player events
     */
    public void tickClient() {
    }

    /**
     * Client and Server ticking
     * <br/>
     * <br/>
     * Usefull for particles
     */
    public void tickBoth() {
    }

    public Direction getInputDirection() {
        return getNodeDirection();
    }

    public Direction getNodeDirection() {
        return getBlockState().getValue(NodeBaseBlock.FACING).getOpposite();
    }

    public Direction getOutputDirection() {
        return getDirectionFromPath();
    }

    public Direction getDirectionFromPath() {
        return this.outputDir;
    }

    /**
     * Server only ticking
     * <br/>
     * <br/>
     * Useful for block events
     */
    public void tickServer() {

        this.failedRoutes = new HashSet<>();
        var input = getInputPos(getBlockState(), level, getBlockPos());

        if (input == null)
            return;

        this.input = input;
        this.failedRoutes.add(input);
        var output = getOutputPos(level, getBlockPos());
        if (output == null)
            return;
        this.output = output;
        var inState = level.getBlockState(input);
        var outState = level.getBlockState(output);
        var inBE = level.getBlockEntity(input);
        var outBE = level.getBlockEntity(output);
        var capType = getCapType();
        this.inCap = capType.getCapability(level, input, inState, inBE, getInputDirection());
        this.outCap = capType.getCapability(level, output, outState, outBE, getOutputDirection());
        if (capType == Capabilities.ItemHandler.BLOCK) {
            executeItem((IItemHandler) inCap, (IItemHandler) outCap);
        } else if (capType == Capabilities.EnergyStorage.BLOCK) {
            executeEnergy((IEnergyStorage) inCap, (IEnergyStorage) outCap);
        } else if (capType == Capabilities.FluidHandler.BLOCK) {
            executeFluid((IFluidHandler) inCap, (IFluidHandler) outCap);
        } else {
            // add other capabilities
        }

        setChanged();
        level.sendBlockUpdated(getInputPos(), inState, inState, 3);
        level.sendBlockUpdated(getOutputPos(), outState, outState, 3);
        if (outBE != null)
            outBE.setChanged();

        // refresh
        this.failedRoutes.removeAll(this.failedRoutes);
        this.input = null;
        this.output = null;
    }

    public BlockPos getInputPos() {
        return this.input;
    }

    public BlockPos getOutputPos() {
        return this.output;
    }

    public Object getInputCap() {
        return this.inCap;
    }

    public Object getOutputCap() {
        return this.outCap;
    }

    public BlockEntity getNodeBE() {
        return level.getBlockEntity(getBlockPos());
    }

    /**
     * Exclude this position to be used and cancel the event <br/>
     * <br/>
     * Useful when containers are full
     */
    public void excludePos(BlockPos pos) {
        if (pos != null)
            this.failedRoutes.add(pos);
    }

    /**
     * Exclude this position to be used and cancel the event <br/>
     * <br/>
     * Useful when containers are full
     */
    public void excludePos() {
        excludePos(defineOutput());
    }

    protected void executeItem(@Nullable IItemHandler input, @Nullable IItemHandler output) {
    }

    protected void executeEnergy(@Nullable IEnergyStorage input, @Nullable IEnergyStorage output) {
    }

    protected void executeFluid(@Nullable IFluidHandler input, @Nullable IFluidHandler output) {
    }

    public abstract BlockCapability<?, Direction> getCapType();

    public boolean match(Level level, BlockPos currentPos, BlockState currentState, Direction dir, BlockPos nextPos,
            BlockState nextState) {
        var capType = getCapType();
        var blockEntity = level.getBlockEntity(nextPos);
        if (capType == Capabilities.ItemHandler.BLOCK) {
            var itemHandler = capType.getCapability(level, nextPos, nextState, blockEntity, dir);
            if (itemHandler instanceof IItemHandler handler) {
                if (getNodeBE() instanceof ItemNodeType it) {

                    if (blockEntity instanceof RestrictedItemHandler r) {
                        if (r.getValidSlots() != null)
                            for (Integer validSlots : r.getValidSlots()) {
                                if (r.getStorageRestricted().isItemValid(validSlots, it.getItemStack()))
                                    return true;
                            }
                    }

                    if (blockEntity instanceof DirectionBasedItemHandler directional) {
                        for (Integer validSlots : directional.getValidSlots()) {
                            if (directional.getStorageRestricted(dir).isItemValid(validSlots, it.getItemStack()))
                                return true;
                        }
                    }

                    if (blockEntity instanceof BaseMachineBE machineBE) {
                        for (Integer slot : machineBE.getInputSlotIndex()) {
                            var insered = machineBE
                                    .insertItem(slot, it.getItemStack(), true);
                            if (insered.isEmpty() || insered.getCount() < it.getItemStack().getCount())
                                return true;
                        }
                    } else

                    if (blockEntity instanceof WorldlyContainer container) {
                        for (int index = 0; index < container.getContainerSize(); index++) {
                            if (container.canPlaceItemThroughFace(index, it.getItemStack(), dir)) {
                                return true;
                            }
                        }
                    } else

                        for (int slot = 0; slot < handler.getSlots(); slot++) {
                            if (handler.getStackInSlot(slot).isEmpty()
                                    || handler.isItemValid(slot, it.getItemStack())) {
                                return true;
                            }
                        }
                }
            }
        } else if (capType == Capabilities.EnergyStorage.BLOCK) {
            var energy = capType.getCapability(level, nextPos, nextState, blockEntity, dir);
            if (energy instanceof IEnergyStorage storage) {
                if (storage.receiveEnergy(1, true) > 0) {
                    return true;
                }
            }
        } else if (capType == Capabilities.FluidHandler.BLOCK) {
            var fluid = capType.getCapability(level, nextPos, nextState, blockEntity, dir);
            if (fluid instanceof IFluidHandler handler) {
                if (getNodeBE() instanceof FluidNodeType ft) {
                    for (int tank = 0; tank < handler.getTanks(); tank++) {
                        if (handler.getFluidInTank(tank).isEmpty() || handler.isFluidValid(tank, ft.getFluidStack())) {
                            return true;
                        }
                    }
                }
            }
        } else {
            // add other capabilities
        }
        return false;
    }

    /**
     * return the output blockpos
     */
    @Nullable
    private BlockPos getOutputPos(Level level, BlockPos start) {
        Queue<BlockPos> queue = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();
        // remove node from new possible connection
        queue.add(start);

        this.outputDir = getNodeDirection().getOpposite();

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            if (failedRoutes.contains(current))
                continue;
            visited.add(current);
            BlockState state = level.getBlockState(current);
            for (Direction dir : Direction.values()) {
                BlockPos next = current.relative(dir);

                if (!visited.contains(next) &&
                        state.getValue(NodePipeBlock.PROPERTY_BY_DIRECTION.get(dir))) {
                    // check if pipe is connected and not included
                    BlockState neighbor = level.getBlockState(next);

                    if (match(level, current, state, dir, next, neighbor)) {
                        this.outputDir = dir;
                        return next;
                    }

                    if (neighbor.is(zBlockTag.CAN_CONNECT)) {
                        queue.add(next);
                    }
                }
            }
        }
        failedRoutes.add(start);
        return null;
    }

    /**
     * return the input blockpos
     */
    @Nullable
    private BlockPos getInputPos(BlockState state, Level level, BlockPos nodePos) {
        return nodePos.relative(state.getValue(NodeBaseBlock.FACING));
    }

    /**
     * check if the blockstate at specific input <code>BlockPos pos</code> <br/>
     * <br/>
     * is the same of input
     * <code>BlockState state</code>
     * 
     */
    protected boolean check(BlockPos pos, BlockState state) {

        var cond = level.getBlockState(pos).is(state.getBlock());

        if (!state.getFluidState().isEmpty())
            cond &= state.getFluidState().isSource();

        return cond;
    }

    public static boolean check(Level level, BlockPos pos, BlockState state) {

        var cond = level.getBlockState(pos).is(state.getBlock());

        if (!state.getFluidState().isEmpty())
            cond &= state.getFluidState().isSource();

        return cond;
    }

    public abstract BlockPos defineOutput();

    public abstract BlockPos defineInput();

}
