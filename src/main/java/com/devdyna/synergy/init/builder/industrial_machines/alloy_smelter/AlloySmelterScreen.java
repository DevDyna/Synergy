package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class AlloySmelterScreen extends BaseMachineScreen<AlloySmelterMenu> {

    public AlloySmelterScreen(AlloySmelterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
