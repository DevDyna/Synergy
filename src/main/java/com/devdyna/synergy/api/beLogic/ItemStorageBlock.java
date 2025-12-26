package com.devdyna.synergy.api.beLogic;

import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

/**
 * Simple ItemStackHandler<br/><br/>
 * Useful to create chests or single storage IO
 */
public interface ItemStorageBlock {

    ItemStacksResourceHandler getStorage();

    int MachineSlots();

}
