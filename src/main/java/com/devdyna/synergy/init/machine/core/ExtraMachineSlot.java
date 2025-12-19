package com.devdyna.synergy.init.machine.core;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public interface ExtraMachineSlot {

    /**
     * Specify the Slot type<br/><br/>
     * 
     * <code>INPUT</code> -> Item can be consumed and required<br/><br/>
     * 
     *
     * <code>CATALYST</code> -> Item cannot be consumed but required<br/><br/>
     * 
     * 
     * <code>OUTPUT</code> -> Item result and not required<br/><br/>
     * 
     */
    public enum TYPE{
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
        OUTPUT();
    }

    abstract TYPE getSlotType();
    
    public static final int EXTRA_SLOT = 2;

    abstract ItemStackHandler getStorage();

    public default ItemStack getExtraSlot() {
        return getStorage().getStackInSlot(EXTRA_SLOT);
    }

}
