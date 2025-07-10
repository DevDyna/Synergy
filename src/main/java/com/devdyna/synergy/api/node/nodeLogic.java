package com.devdyna.synergy.api.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.pipe.pipeProperties;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.init.types.zBlockTag;
// import com.devdyna.synergy.utils.LogUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public interface nodeLogic {

    /**
     * return the input block
     */
    @Nullable
    default IItemHandler getInputBlock(BlockState state, Level level, BlockPos pos) {
        return getCap(level, state.getValue(nodeType.FACING).getOpposite(),
                pos.relative(state.getValue(nodeType.FACING)));
    }

    /**
     * return the input block
     */
    @Nullable
    default Entry<Direction, BlockPos> getInputPos(BlockState state, Level level, BlockPos pos) {
        return Map.entry(state.getValue(nodeType.FACING).getOpposite(), pos.relative(state.getValue(nodeType.FACING)));
    }

    // TODO implement rerouting logic when fail
    /**
     * return the output block
     */
    @Nullable
    default IItemHandler getOutputBlock(BlockState state, Level level, BlockPos pos) {
        BlockPos variablePos = pos;
        Direction outputSide = null;
        BlockPos outputPos = null;
        BlockState actual;
        BlockState offset;
        var totalCheckSide = 6;
        List<BlockPos> validPath = new ArrayList<>();

        var flag = true;
        while (flag)
            for (Direction dir : Direction.values()) {
                actual = level.getBlockState(variablePos);
                offset = level.getBlockState(variablePos.relative(dir));

                if (actual.getValue(pipeType.D2P(dir)) == pipeProperties.OUTPUT) {
                    outputPos = variablePos;
                    outputSide = dir;
                    flag = false;
                    break;
                }

                if (offset.is(zBlockTag.CAN_CONNECT)
                        && validPath.indexOf(variablePos.relative(dir)) == -1
                        && actual.getValue(pipeType.D2P(dir)) == pipeProperties.TRUE) {

                    validPath.add(variablePos);
                    variablePos = variablePos.relative(dir);
                    totalCheckSide = 6;
                }

                totalCheckSide--;

                if (totalCheckSide < 0) {
                    flag = false;
                    break;
                }
            }

        if (totalCheckSide >= 0)
            if (outputSide == null && outputPos == null)
                return null;
            else
                return getCap(level, outputSide.getOpposite(), outputPos.relative(outputSide));
        else
            return null;
    }

    /**
     * return the output block
     */
    @Nullable
    default Entry<Direction, BlockPos> getOutputPos(BlockState state, Level level, BlockPos pos) {
        BlockPos variablePos = pos;
        Direction outputSide = null;
        BlockPos outputPos = null;
        BlockState actual;
        BlockState offset;
        var totalCheckSide = 6;
        List<BlockPos> validPath = new ArrayList<>();

        var flag = true;
        while (flag)
            for (Direction dir : Direction.values()) {
                actual = level.getBlockState(variablePos);
                offset = level.getBlockState(variablePos.relative(dir));
                // TODO re-routing when fail
                if (actual.is(zBlockTag.CAN_CONNECT) &&
                        actual.getValue(pipeType.D2P(dir)) == pipeProperties.OUTPUT) {
                    outputPos = variablePos;
                    outputSide = dir;
                    flag = false;
                    break;
                }

                if (offset.is(zBlockTag.CAN_CONNECT) && actual.is(zBlockTag.CAN_CONNECT)
                        && validPath.indexOf(variablePos.relative(dir)) == -1
                        && actual.getValue(pipeType.D2P(dir)) == pipeProperties.TRUE) {

                    validPath.add(variablePos);
                    variablePos = variablePos.relative(dir);
                    totalCheckSide = 6;
                }

                totalCheckSide--;

                if (totalCheckSide < 0) {
                    flag = false;
                    break;
                }
            }

        if (totalCheckSide >= 0)
            if (outputSide == null && outputPos == null)
                return null;
            else
                return Map.entry(outputSide.getOpposite(), outputPos.relative(outputSide));
        else
            return null;
    }

    /**
     * return the Itemcapability of a specific block
     */
    @Nullable
    default IItemHandler getCap(Level l, Direction d, BlockPos pos) {
        return l != null && l.getBlockEntity(pos) != null
                ? Capabilities.ItemHandler.BLOCK.getCapability(l, pos, l.getBlockState(pos),
                        l.getBlockEntity(pos), d.getOpposite())
                : null;
    }

    /**
     * return list of slot index with input items
     */
    @Nullable
    default ArrayList<Integer> getSlotsOfItems(IItemHandler inpuItemHandler) {
        ArrayList<Integer> items = new ArrayList<>();
        // LogUtil.info("slots: " + inpuItemHandler.getSlots());

        for (int i = 0; i < inpuItemHandler.getSlots(); i++) {
            // LogUtil.info(inpuItemHandler.extractItem(i, 1, true) + " -> " +
            // inpuItemHandler.extractItem(i, 1, true).isEmpty());
            if (!inpuItemHandler.extractItem(i, 1, true).isEmpty())
                items.add(i);
        }
        return items;
    }

    /**
     * return list of slot index with input items
     */
    @Nullable
    default List<Map.Entry<Integer, ItemStack>> getInputItems(IItemHandler inpuItemHandler) {
        List<Map.Entry<Integer, ItemStack>> items = List.of();
        for (int i = 0; inpuItemHandler.getSlots() - 1 <= i; i++) {

            if (!inpuItemHandler.getStackInSlot(i).isEmpty()) {
                items.add(Map.entry(i, inpuItemHandler.getStackInSlot(i)));
            }
        }
        return items;
    }

    // TODO convert to ItemHandlerHelper.insertItemStacked

    /**
     * move one at time of all possible input item to an output
     * <br/>
     * <br/>
     * 
     * default stack = 1
     */
    default void moveItems(IItemHandler input, IItemHandler output, int pipeRate) {

        var inputitems = getSlotsOfItems(input);

        // no valid input provided
        if (inputitems.isEmpty() || inputitems == null) {
            return;
        }

        for (int inputIndex = 0; inputIndex < input.getSlots(); inputIndex++) {

            ItemStack inItem = input.extractItem(inputIndex, pipeRate, true);

            // if input is empty
            // skip
            if (inItem.isEmpty())
                continue;

            for (int outputIndex = 0; outputIndex < output.getSlots(); outputIndex++) {

                ItemStack outItem = output.extractItem(outputIndex, getCount(output, outputIndex), true);

                // if output is full
                // skip
                if (outItem.getMaxStackSize() == outItem.getCount())
                    continue;

                // if output is empty
                // merge
                if (outItem.isEmpty()) {
                    output.insertItem(outputIndex,
                            input.extractItem(inputIndex, pipeRate,
                                    false),
                            false);
                    break; // break output due input empty and try next input slot
                }

                // if output match input
                // merge
                if (inItem.is(outItem.getItem())) {
                    output.insertItem(outputIndex,
                            input.extractItem(inputIndex,
                                    Math.min(pipeRate, outItem.getMaxStackSize() - outItem.getCount()),
                                    false),
                            false);
                    break; // break output due input empty and try next input slot
                }

                // if output dont match input
                // skip (nothing)
            }
        }
    }

    /**
     * move one at time of all possible input item to an output
     * <br/>
     * <br/>
     * 
     * default stack = 1
     */
    default void moveItems(IItemHandler input, IItemHandler output) {
        moveItems(input, output, 1);
    }

    /**
     * return the item count of a specific slot index
     */
    default int getCount(IItemHandler be, int index) {
        return be.getStackInSlot(index).getCount();
    }

    // TODO convert to ItemHandlerHelper.insertItemStacked

    /**
     * add an itemstack to the output
     * stacksize defined on itemstack
     */
    default void itemToOutput(ItemStack input, IItemHandler output) {

        for (int slot = 0; slot < output.getSlots(); slot++) {
            ItemStack outItem = output.extractItem(slot,
                    getCount(output, slot), true);

            // if output is empty
            if (output.getStackInSlot(slot).isEmpty()) {
                output.insertItem(slot,
                        input,
                        false);
                break;
            } else {
                // if output match input
                if (input.is(outItem.getItem()) && outItem.getCount() != outItem.getMaxStackSize()) {
                    output.insertItem(slot, input, false);
                    break;
                }
            }
        }

    }
}
