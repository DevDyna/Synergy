package com.devdyna.synergy.api.beLogic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuConstructor;
import net.neoforged.neoforge.items.ItemStackHandler;

public interface ItemStorageBlock extends MenuConstructor{

    Component getContainerName();

    ItemStackHandler getStorage();

    int MachineSlots();

}
