package com.devdyna.synergy.api.beLogic;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

@SuppressWarnings("null")
public interface MachineItemAutomation extends ItemStorageBlock {

    int getMachineSlots();

    List<Integer> getInputSlotIndex();

    List<Integer> getOutputSlotIndex();

    /**
     * return the item count insered
     */
    default int insertItem(int slot, ItemStack itemStack) {

        try (var tx = Transaction.openRoot()) {
            if (getOutputSlotIndex().contains(slot))
                return 0;

            var count = getStorage().insert(slot, ItemResource.of(itemStack), itemStack.getCount(), tx);
            tx.commit();
            return count;
        }

    }

    /**
     * return the item count extracted
     */
    default int extractItem(int slot, int amount) {
        try (var tx = Transaction.openRoot()) {
            if (getInputSlotIndex().contains(slot))
                return 0;

            var count = extract(slot,tx);
            tx.commit();
            return count;
        }
    }

    // @Override
    // default ItemStack getStackInSlot(int slot) {
    //     if (getStorage().getSlots() >= slot)
    //         return getStorage().extractItem(slot, getSlotLimit(slot), true);

    //     return ItemStack.EMPTY;
    // }

    // @Override
    // default int getSlotLimit(int slot) {
    //     return getStorage().getStackInSlot(slot).getMaxStackSize();
    // }

}
