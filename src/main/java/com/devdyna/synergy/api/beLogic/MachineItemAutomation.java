package com.devdyna.synergy.api.beLogic;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.ItemStackHandler;

@SuppressWarnings("null")
public interface MachineItemAutomation extends IItemHandlerModifiable {

    ItemStackHandler getStorage();

    int getMachineSlots();

    List<Integer> getInputSlotIndex();

    List<Integer> getOutputSlotIndex();

    @Override
    default int getSlots() {
        return getMachineSlots();
    }

    @Override
    default ItemStack insertItem(int slot, ItemStack itemStack, boolean simOn) {
        if (getOutputSlotIndex().contains(slot))
            return itemStack;

        return getStorage().insertItem(slot, itemStack, simOn);
    }

    @Override
    default ItemStack extractItem(int slot, int amount, boolean simOn) {
        if (getInputSlotIndex().contains(slot))
            return ItemStack.EMPTY;

        return getStorage().extractItem(slot, amount, simOn);
    }

    @Override
    default ItemStack getStackInSlot(int slot) {
        if (getStorage().getSlots() >= slot)
            return getStorage().extractItem(slot, getSlotLimit(slot), true);

        return ItemStack.EMPTY;
    }

    @Override
    default int getSlotLimit(int slot) {
        return getStorage().getStackInSlot(slot).getMaxStackSize();
    }

}
