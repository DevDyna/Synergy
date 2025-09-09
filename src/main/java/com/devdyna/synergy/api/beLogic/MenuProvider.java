package com.devdyna.synergy.api.beLogic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuConstructor;

public interface MenuProvider extends MenuConstructor{
    
    Component getContainerName();

}
