package com.devdyna.synergy.api.node;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings("null")
public interface ItemNodeType {
    abstract ItemStack getItemStack();

    default void moveItems(IItemHandler input, IItemHandler output, int maxCount) {

        if (input == null || output == null || maxCount <= 0)
            return;

        int remaining = maxCount;

        for (int inSlot = 0; inSlot < input.getSlots() && remaining > 0; inSlot++) {

            ItemStack inStack = input.getStackInSlot(inSlot);
            if (inStack.isEmpty())
                continue;

            ItemStack simulatedExtract = input.extractItem(inSlot, remaining, true);
            if (simulatedExtract.isEmpty())
                continue;

            ItemStack toInsert = simulatedExtract.copy();

            for (int outSlot = 0; outSlot < output.getSlots() && !toInsert.isEmpty(); outSlot++) {

                ItemStack remainder = output.insertItem(outSlot, toInsert, true);

                int accepted = toInsert.getCount() - remainder.getCount();
                if (accepted <= 0)
                    continue;

                output.insertItem(outSlot, input.extractItem(inSlot, accepted, false), false);

                remaining -= accepted;

                toInsert = remainder;
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
