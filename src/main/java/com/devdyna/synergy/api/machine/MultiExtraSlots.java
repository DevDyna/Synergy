package com.devdyna.synergy.api.machine;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public interface MultiExtraSlots {


    abstract ItemStackHandler getStorage();

    public default ItemStack getSlot3() {
        return getStorage().getStackInSlot(SLOT_3);
    }

    public default ItemStack getSlot4() {
        return getStorage().getStackInSlot(SLOT_4);
    }

    public default ItemStack getSlot5() {
        return getStorage().getStackInSlot(SLOT_5);
    }

    public default ItemStack getSlot6() {
        return getStorage().getStackInSlot(SLOT_6);
    }

    public default ItemStack getSlot7() {
        return getStorage().getStackInSlot(SLOT_7);
    }

    public enum slotType {
        /**
         * Item can be consumed and required
         */
        INPUT(),
        /**
         * Item cannot be consumed but required
         */
        CATALYST(),
        /**
         * Item result and not required
         */
        OUTPUT(),
        /**
         * Slot index excluded
         */
        UNUSED();
    }

    public static final int SLOT_3 = 2;
    public static final int SLOT_4 = 7;
    public static final int SLOT_5 = 8;
    public static final int SLOT_6 = 9;
    public static final int SLOT_7 = 10;

    /**
     * SLOT_3, SLOT_4, SLOT_5, SLOT_6, SLOT_7
     */
    abstract List<slotType> getSlotTypes();

    public static final List<Integer> SlotIndexes = 
    List.of(SLOT_3, SLOT_4, SLOT_5, SLOT_6, SLOT_7);

}
