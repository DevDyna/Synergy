package com.devdyna.synergy.api.node;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings("null")
public interface ItemNodeType {
    abstract ItemStack getItemStack();

    abstract BlockEntity getInputBE();

    abstract BlockEntity getOutputBE();

    default void moveItems(IItemHandler input, IItemHandler output, int maxCount) {

        if (input == null || output == null || getInputBE() == null || getOutputBE() == null)
            return;

        int remaining = maxCount;

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

    default ItemStack getFirstItem(IItemHandler handler) {
        if (handler != null)
            for (int i = 0; i < handler.getSlots(); i++) {
                if (!handler.getStackInSlot(i).isEmpty())
                    return handler.getStackInSlot(i).copy();
            }
        return ItemStack.EMPTY;
    }

    default ItemStack insertItemStacked(IItemHandler handler, ItemStack stack, boolean simOn) {
        return ItemHandlerHelper.insertItemStacked(handler, stack, simOn);
    }

}
