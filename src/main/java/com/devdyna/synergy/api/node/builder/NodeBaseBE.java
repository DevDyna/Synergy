package com.devdyna.synergy.api.node.builder;

import java.util.*;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.node.nodeType;
import com.devdyna.synergy.api.pipe.pipeProperties;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.init.types.zBlockTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public abstract class NodeBaseBE extends BlockEntity {

    public NodeBaseBE(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public NodeBaseBE(BlockPos pos, BlockState blockState) {
        super(null, pos, blockState);
    }

    private Set<BlockPos> failedRoutes;
    private BlockPos input;
    private BlockPos output;

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
        var inCap = getCapType().getCapability(level, input, inState, inBE, null);
        var outCap = getCapType().getCapability(level, output, outState, outBE, null);
        var capType = getCapType();
        if (capType == Capabilities.ItemHandler.BLOCK) {
            executeItem((IItemHandler) inCap, (IItemHandler) outCap);
        } else if (capType == Capabilities.EnergyStorage.BLOCK) {
            executeEnergy((IEnergyStorage) inCap, (IEnergyStorage) outCap);
        } else if (capType == Capabilities.FluidHandler.BLOCK) {
            executeFluid((IFluidHandler) inCap, (IFluidHandler) outCap);
        } else {
            // TODO meka compats
        }
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

    public boolean allowInputNull() {
        return false;
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

    protected void executeItem(IItemHandler input, IItemHandler output) {
    }

    protected void executeEnergy(IEnergyStorage input, IEnergyStorage output) {
    }

    protected void executeFluid(IFluidHandler input, IFluidHandler output) {
    }

    public abstract BlockCapability<?, Direction> getCapType();

    public boolean match(Level level, BlockPos currentPos, BlockState currentState, Direction dir, BlockPos nextPos,
            BlockState nextState) {
        var capType = getCapType();
        var blockEntity = level.getBlockEntity(nextPos);
        if (capType == Capabilities.ItemHandler.BLOCK) {
            var itemHandler = capType.getCapability(level, nextPos, nextState, blockEntity, dir);
            if (itemHandler instanceof IItemHandler handler) {
                // Check if any slot can accept an item (simulate insert)
                for (int slot = 0; slot < handler.getSlots(); slot++) {
                    if (handler.insertItem(slot, new ItemStack(Items.STONE, 1), true).isEmpty()) {
                        return true;
                    }
                }
            }
        } else if (capType == Capabilities.EnergyStorage.BLOCK) {
            var energy = capType.getCapability(level, nextPos, nextState, blockEntity, dir);
            if (energy instanceof IEnergyStorage storage) {
                // Check if it can receive at least 1 energy
                if (storage.receiveEnergy(1, true) > 0) {
                    return true;
                }
            }
        } else if (capType == Capabilities.FluidHandler.BLOCK) {
            var fluid = capType.getCapability(level, nextPos, nextState, blockEntity, dir);
            if (fluid instanceof IFluidHandler handler) {
                // Try to simulate inserting 1 bucket of water
                if (handler.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.SIMULATE) > 0) {
                    return true;
                }
            }
        } else {
            // TODO: meka compat

        }
        return false;
    }

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

    /**
     * return the output blockpos
     */
    @Nullable
    private BlockPos getOutputPos(Level level, BlockPos start) {
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

                if (!visited.contains(next) &&
                        state.getValue(pipeType.D2P(dir)) == pipeProperties.TRUE) {
                    // check if pipe is connected and not included
                    BlockState neighbor = level.getBlockState(next);

                    if (match(level, current, state, dir, next, neighbor)) {
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
        return nodePos.relative(state.getValue(nodeType.FACING));
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

    public abstract BlockPos defineOutput();

    public void moveItems(IItemHandler input, IItemHandler output, int maxCount) {
        int remaining = maxCount;

        if (input == null || output == null)
            return;

        for (int inSlot = 0; inSlot < input.getSlots() && remaining > 0; inSlot++) {
            ItemStack inStack = input.getStackInSlot(inSlot);
            if (inStack.isEmpty())
                continue;

            int extractAmount = Math.min(inStack.getCount(), remaining);
            ItemStack extracted = input.extractItem(inSlot, extractAmount, false);
            if (extracted.isEmpty())
                continue;

            ItemStack leftover = extracted.copy();

            // Attempt to insert into all output slots
            for (int outSlot = 0; outSlot < output.getSlots() && !leftover.isEmpty(); outSlot++) {
                leftover = output.insertItem(outSlot, leftover, false);
            }

            // Update remaining based on successfully inserted items
            int inserted = extracted.getCount() - leftover.getCount();
            remaining -= inserted;

            // If anything couldn't be inserted, return it to input
            if (!leftover.isEmpty()) {
                input.insertItem(inSlot, leftover, false);
            }
        }
    }

    protected void moveFluids(IFluidHandler input, IFluidHandler output, int maxMilliBuckets) {

        int remaining = maxMilliBuckets;

        for (int tank = 0; tank < input.getTanks() && remaining > 0; tank++) {
            FluidStack stack = input.getFluidInTank(tank);
            if (stack.isEmpty())
                continue;

            int transfer = Math.min(stack.getAmount(), remaining);
            FluidStack toMove = stack.copy();
            toMove.setAmount(transfer);

            // Actually drain from input
            FluidStack drained = input.drain(transfer, IFluidHandler.FluidAction.EXECUTE);
            if (drained.isEmpty())
                continue;

            int filled = output.fill(drained, IFluidHandler.FluidAction.EXECUTE);
            remaining -= filled;

            if (filled < drained.getAmount()) {
                // Return the leftover back to input
                drained.setAmount(drained.getAmount() - filled);
                input.fill(drained, IFluidHandler.FluidAction.EXECUTE);

            }
        }
    }

    // Only water can increase inside tanks from other mods!?
    public static FluidStack insertFluidStacked(IFluidHandler handler, FluidStack stack, boolean simulate) {
        if (handler == null || stack.isEmpty()) {
            return stack;
        }

        FluidStack remaining = stack.copy();
        IFluidHandler.FluidAction action = simulate ? IFluidHandler.FluidAction.SIMULATE
                : IFluidHandler.FluidAction.EXECUTE;

        int tankCount = handler.getTanks();
        for (int i = 0; i < tankCount && !remaining.isEmpty(); i++) {

            FluidStack toFill = remaining.copy();
            int filled = handler.fill(toFill, action);

            if (filled > 0)
                remaining.shrink(filled);

        }

        return remaining;
    }

    // TODO meka compat
    protected void moveEnergy(IEnergyStorage input, IEnergyStorage output, int maxEnergy) {

        int remaining = maxEnergy;

        // Try to extract energy from input
        int extracted = input.extractEnergy(remaining, false);
        if (extracted <= 0)
            return;

        // Try to insert into output
        int accepted = output.receiveEnergy(extracted, false);
        int leftover = extracted - accepted;

        // If some energy couldn't be accepted, put it back into input
        if (leftover > 0) {
            input.receiveEnergy(leftover, false);
        }
    }

}
