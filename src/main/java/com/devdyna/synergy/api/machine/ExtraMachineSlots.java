package com.devdyna.synergy.api.machine;

import java.util.ArrayList;
import java.util.List;

import net.neoforged.neoforge.items.ItemStackHandler;

/**
 * Industrial Machine attach to allow more slots
 * <br/>
 * <br/>
 * Slot Indexs need to be 6 -> Integer.MAX_VALUE
 * <br/>
 * <br/>
 * Credits : @DevDyna
 */
public interface ExtraMachineSlots {

    abstract ItemStackHandler getStorage();

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

    abstract SlotBuilder getSlotTypes();

    // default List<Integer> getExtraSlots(){
    // return List.of();
    // }

    class SlotBuilder {

        private List<SlotType> list;

        public SlotBuilder(int slots) {
            List<SlotType> a = new ArrayList<>();
            for (int i = 0; i < slots; i++) {
                a.add(SlotType.UNUSED);
            }
            this.list = a;
        }

        public static SlotBuilder of(int slots) {
            return new SlotBuilder(slots);
        }

        /**
         * slot should be <code> slot >= 6 </code>
         */
        public SlotBuilder set(int slot, SlotType type) {
            if (slot >= 6)
                list.set(slot - 6, type);
            return this;
        }

        public SlotBuilder setAll(SlotType type, Integer... slots) {
            for (int s : slots) {
                if (s >= 6)
                    list.set(s - 6, type);
            }
            return this;
        }

        public SlotBuilder setAll(SlotType type, List<Integer> slots) {
            return setAll(type, slots.toArray(Integer[]::new));
        }

        public List<SlotType> get() {
            return list;
        }

        public SlotType get(int slot) {
            return list.get(slot);
        }
    }

}
