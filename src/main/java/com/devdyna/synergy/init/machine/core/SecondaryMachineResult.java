package com.devdyna.synergy.init.machine.core;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public interface SecondaryMachineResult {
    
    public static final int OUTPUT_SECONDARY_SLOT = 2;

    abstract ItemStackHandler getStorage();

    public default ItemStack getSecondary() {
        return getStorage().getStackInSlot(OUTPUT_SECONDARY_SLOT);
    }

}
