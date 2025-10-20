package com.devdyna.synergy.api.beLogic;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public interface ItemStorageWithOutput extends IItemHandler {

    ItemStackHandler getStorage();

    int MachineSlots();

    List<Integer> getInputSlotIndex();

    List<Integer> getOutputSlotIndex();

    @Override
    default ItemStack insertItem(int index, ItemStack itemStack, boolean simulate) {
        if (getOutputSlotIndex().contains(index))
            return itemStack;

        return getStorage().insertItem(index, itemStack, simulate);

    }

}
