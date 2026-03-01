package com.devdyna.synergy.api.node;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@SuppressWarnings("null")
public interface ItemNodeType {
    abstract ItemStack getItemStack();

    default void moveItems(IItemHandler input, IItemHandler output, int maxCount) {

        if (input == null || output == null)
            return;

        for (int inSlot = 0; inSlot < input.getSlots(); inSlot++) {
            ItemStack inStack = input.getStackInSlot(inSlot);
            if (inStack.isEmpty())
                continue;

            ItemStack extracted = input.extractItem(inSlot, Math.min(inStack.getCount(), maxCount), true);
            if (extracted.isEmpty())
                continue;

            var copyExtracted = extracted.copy();

            for (int outSlot = 0; outSlot < output.getSlots() && !copyExtracted.isEmpty(); outSlot++) {
                var insered = output.insertItem(outSlot, copyExtracted, true);

                if (insered.isEmpty()) {
                    output.insertItem(outSlot, input.extractItem(inSlot, copyExtracted.getCount(), false), false);
                    break;
                }

                if (insered.getCount() != copyExtracted.getCount()) {
                    output.insertItem(outSlot,
                            input.extractItem(inSlot, copyExtracted.getCount() - insered.getCount(), false), false);
                    copyExtracted = insered.copy();
                }
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
