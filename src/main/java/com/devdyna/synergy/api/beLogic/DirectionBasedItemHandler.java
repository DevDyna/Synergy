package com.devdyna.synergy.api.beLogic;

import java.util.List;

import net.minecraft.core.Direction;
import net.neoforged.neoforge.items.IItemHandler;

@SuppressWarnings("null")
public interface DirectionBasedItemHandler extends ItemStorageBlock{

    abstract IItemHandler getStorageRestricted(Direction dir);

    abstract List<Integer> getValidSlots();
}
