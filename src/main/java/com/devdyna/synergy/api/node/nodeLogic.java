package com.devdyna.synergy.api.node;

import java.util.*;

import javax.annotation.Nullable;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public interface nodeLogic {

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

    default void provideFE(IEnergyStorage input, IEnergyStorage output, int rate) {

        if (!input.canExtract() || !output.canReceive() || output.getMaxEnergyStored() == output.getEnergyStored())
            return;

        output.receiveEnergy(Math.min(output.getMaxEnergyStored() - output.getEnergyStored(),
                Math.min(input.getEnergyStored(), input.extractEnergy(rate, false))), false);

    }

    default void provideFE(IEnergyStorage input, IEnergyStorage output) {
        provideFE(input, output, 128);
    }

}
