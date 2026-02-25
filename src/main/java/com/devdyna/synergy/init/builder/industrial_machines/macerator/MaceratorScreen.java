package com.devdyna.synergy.init.builder.industrial_machines.macerator;

import java.util.List;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents.UpgradeType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class MaceratorScreen extends BaseMachineScreen<MaceratorMenu> {

    public MaceratorScreen(MaceratorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    public List<UpgradeType> validUpgrades() {
        return List.of(UpgradeType.ENERGY_CAPACITY,UpgradeType.ENERGY_EFFICIENCY, UpgradeType.SPEED, UpgradeType.LUCK);
    }

}
