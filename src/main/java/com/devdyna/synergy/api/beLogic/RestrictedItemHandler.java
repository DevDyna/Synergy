package com.devdyna.synergy.api.beLogic;

import java.util.List;

import javax.annotation.Nullable;

import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public interface RestrictedItemHandler extends ItemStorageBlock {

    abstract IItemHandler getStorageRestricted();

    default @Nullable List<Integer> getValidSlots() {
        return null;
    }
}
