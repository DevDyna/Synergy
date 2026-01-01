package com.devdyna.synergy.api.beLogic;

import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

/**
 * Simple ItemStackHandler<br/>
 * <br/>
 * Useful to create chests or single storage IO
 */
public interface ItemStorageBlock {

    ItemStacksResourceHandler getStorage();

    int MachineSlots();

    /**
     * extract anything from any valid slot
     */
    default int extract(int index, Transaction tx) {
        return getStorage().extract(getStorage().getResource(index), index, tx);
    }

}
