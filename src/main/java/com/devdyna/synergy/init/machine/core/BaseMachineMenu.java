package com.devdyna.synergy.init.machine.core;

import com.devdyna.synergy.client.gui.api.BaseMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * not yet functional <br/><br/> "just an empty abstract class"
 */
public abstract class BaseMachineMenu extends BaseMenu {

    protected BaseMachineMenu(MenuType<?> menuType, int containerId, BlockEntity blockEntity) {
        super(menuType, containerId, blockEntity);
    }

}
