package com.devdyna.synergy.api.beLogic;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

/**
 * Simple ItemStackHandler<br/>
 * <br/>
 * Useful to create chests or single storage IO
 */
public interface ItemStorageBlock extends DropOnBreak {

    ItemStacksResourceHandler getStorage();

    int MachineSlots();

    /**
     * extract anything
     * 
     * @return extracted amount
     */
    default int extract(int index, Transaction tx) {
        return getStorage().extract(getStorage().getResource(index), index, tx);
    }

    /**
     * extract anything
     * 
     * @return extracted amount
     */
    default int extract(int index, int amount, Transaction tx) {
        var stack = getStackInSlot(index);
        stack.setCount(amount);
        return getStorage().extract(ItemResource.of(stack), index, tx);
    }

    /**
     * extract anything
     * 
     * @return extracted amount
     */
    default int extract(int index) {
        try (var tx = Transaction.openRoot()) {
            if (canExtract(index)) {
                extract(index, tx);
                tx.commit();
            }
            return getStackInSlot(index).getCount();
        }
    }

    /**
     * extract anything
     * 
     * @return extracted amount
     */
    default int extract(int index, int amount) {
        try (var tx = Transaction.openRoot()) {
            if (canExtract(index, amount)) {
                extract(index, amount, tx);
                tx.commit();
            }
            return getStackInSlot(index).getCount();
        }
    }

    default ItemStack getStackInSlot(int i) {
        return getStorage().getResource(i).toStack(getStorage().getCapacityAsInt(i, getStorage().getResource(i)));
    }

    default ItemStack[] getItems() {
        return getStorage().copyToList().toArray(ItemStack[]::new);
    }

    /**
     * @return insered amount
     */
    default int insert(int index, ItemStack stack) {
        try (var tx = Transaction.openRoot()) {
            if (canInsert(index, stack)) {
                tx.commit();
                return getStorage().insert(index, ItemResource.of(stack), stack.getCount(), tx);
            }
        }
        return 0;
    }

    default boolean canInsert(int index, ItemStack stack) {
        return isEmpty(index) || (getStackInSlot(index).is(stack.getItem())
                && difference(index, stack) == 0);
    }

    /**
     * @return return the remain when slot full
     */
    default int difference(int index, ItemStack stack) {
        var count = getStackInSlot(index).getCount() + stack.getCount();
        if (count > getStackInSlot(index).getMaxStackSize()) {
            return count - getStackInSlot(index).getMaxStackSize();
        } else
            return 0;
    }

    default boolean isEmpty(int index) {
        return getStackInSlot(index).isEmpty();
    }

    default boolean canExtract(int index) {
        return !isEmpty(index) && getStackInSlot(index).getCount() > 0;
    }

    default boolean canExtract(int index, int amount) {
        return !isEmpty(index) && getStackInSlot(index).getCount() >= amount;
    }

    default boolean canExtract(int index, ItemStack stack) {
        return canExtract(index) && getStackInSlot(index).is(stack.getItem());
    }

    /**
     * exchange index slot stack to storage slot stack
     */
    default void swap(int index, ItemStack stack) {
        var temp = stack.copy();
        stack = getStackInSlot(index);
        insert(index, temp);
    }

}
