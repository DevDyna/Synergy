package com.devdyna.synergy.api.beLogic;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;


/**
 * <b>STANDALONE BASE BE HANDLER</b>
 * <br/>
 * <br/>
 * Base BE HANDLER to filter multiple slots on automation handling
 * <br/>
 * <br/>
 * This Base BE HANDLER is inspired from
 * <code>com.devdyna.synergy.api.machine.BaseMachineBE</code> to be used to
 * create simple-complex machines
 * <br/>
 * <br/>
 * |-----------------------------------------------------------------|<br/>
 * <br/>
 * <br/>
 * credit: @DevDyna
 */
@SuppressWarnings("null")
public interface SimpleMachineItemStorage {

    ItemStackHandler getStorage();

    abstract List<Integer> getInputSlotIndex();

    abstract List<Integer> getOutputSlotIndex();

    default IItemHandler getAutomationItemHandler() {
        return new IItemHandler() {

            @Override
            public int getSlots() {
                return getStorage().getSlots();
            }

            @Override
            public ItemStack getStackInSlot(int slot) {
                return getStorage().getStackInSlot(slot);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                if (getInputSlotIndex().contains(slot))
                    return getStorage().insertItem(slot, stack, simulate);
                return stack;
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (getOutputSlotIndex().contains(slot))
                    return getStorage().extractItem(slot, amount, simulate);
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return getStorage().getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return getInputSlotIndex().contains(slot);
            }

        };
    }

    default boolean isCustomHander(){
        return false;
    }
}
