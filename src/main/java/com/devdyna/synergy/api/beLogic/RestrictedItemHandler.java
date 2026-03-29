package com.devdyna.synergy.api.beLogic;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public interface RestrictedItemHandler extends ItemStorageBlock {

    abstract IItemHandler getStorageRestricted();

    default @Nullable List<Integer> getValidSlots() {
        return null;
    }

    /**
     * insert a limited amount of stack to the specific slot<br/>
     * <br/>
     * NOTE : <code>getStorage()</code> is used as result!
     */
    default ItemStack insertLimited(ItemStack stack, int slot, int limit) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        ItemStack existing = getStorage().getStackInSlot(slot);

        int currentCount = existing.isEmpty() ? 0 : existing.getCount();

        if (currentCount >= limit)
            return stack;

        ItemStack toInsert = stack.copyWithCount(Math.min(stack.getCount(), limit - currentCount));

        int inserted = toInsert.getCount() - getStorage().insertItem(slot, toInsert, false).getCount();

        if (inserted <= 0)
            return stack;

        ItemStack result = stack.copy();
        result.shrink(inserted);

        return result;
    }
}
