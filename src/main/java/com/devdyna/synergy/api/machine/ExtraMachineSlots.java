package com.devdyna.synergy.api.machine;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public interface ExtraMachineSlots {

    abstract ItemStackHandler getStorage();

    public default ItemStack getExtraSlot1() {
        return getStorage().getStackInSlot(EXTRA_SLOT_1);
    }

    public default ItemStack getExtraSlot2() {
        return getStorage().getStackInSlot(EXTRA_SLOT_2);
    }

    public default ItemStack getExtraSlot3() {
        return getStorage().getStackInSlot(EXTRA_SLOT_3);
    }

    public default ItemStack getExtraSlot4() {
        return getStorage().getStackInSlot(EXTRA_SLOT_4);
    }

    public default ItemStack getExtraSlot5() {
        return getStorage().getStackInSlot(EXTRA_SLOT_5);
    }

    public enum SlotType {
        /**
         * Item can be consumed and required
         */
        INPUT(),
        // /**
        // * Item cannot be consumed but required
        // */
        // CATALYST(),
        /**
         * Item result and not required
         */
        OUTPUT(),
        /**
         * Slot index excluded
         */
        UNUSED();
    }

    public static final int EXTRA_SLOT_1 = 6;
    public static final int EXTRA_SLOT_2 = 7;
    public static final int EXTRA_SLOT_3 = 8;
    public static final int EXTRA_SLOT_4 = 9;
    public static final int EXTRA_SLOT_5 = 10;

    /**
     * SLOT_2, SLOT_3, SLOT_4, SLOT_5, SLOT_6
     */
    abstract SlotBuilder getSlotTypes();

    public List<Integer> INDEX_SLOTS = List.of(EXTRA_SLOT_1, EXTRA_SLOT_2, EXTRA_SLOT_3, EXTRA_SLOT_4,
            EXTRA_SLOT_5);

    class SlotBuilder {

        private List<SlotType> list;

        public SlotBuilder() {
            List<SlotType> a = new ArrayList<>();
            for (int i = 0; i < INDEX_SLOTS.size(); i++) {
                a.add(SlotType.UNUSED);
            }
            this.list = a;
        }

        public static SlotBuilder of() {
            return new SlotBuilder();
        }

        /**
         * slot should be EXTRA_SLOT_X
         */
        public SlotBuilder set(int slot, SlotType type) {
            list.set(slot - EXTRA_SLOT_1, type);
            return this;
        }

        public List<SlotType> get() {
            return list;
        }
    }

}
