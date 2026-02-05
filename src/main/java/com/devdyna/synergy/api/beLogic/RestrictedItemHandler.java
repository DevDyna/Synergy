package com.devdyna.synergy.api.beLogic;

import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public interface RestrictedItemHandler extends ItemStorageBlock{

    abstract IItemHandler getStorageRestricted();
}
