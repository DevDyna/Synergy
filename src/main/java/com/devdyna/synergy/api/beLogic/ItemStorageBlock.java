package com.devdyna.synergy.api.beLogic;

import net.neoforged.neoforge.items.ItemStackHandler;

/**
 * Simple ItemStackHandler<br/>
 * <br/>
 * Useful to create chests or single storage IO
 */
public interface ItemStorageBlock {

    ItemStackHandler getStorage();

    int MachineSlots();

}
