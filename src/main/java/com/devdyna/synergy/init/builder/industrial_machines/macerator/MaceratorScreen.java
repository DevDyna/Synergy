package com.devdyna.synergy.init.builder.industrial_machines.macerator;

import java.util.List;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents.TYPE;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class MaceratorScreen extends BaseMachineScreen<MaceratorMenu> {

    public MaceratorScreen(MaceratorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected boolean whenAnimateArrow() {
        return menu.isCrafting();
    }

    @Override
    protected int getScaledArrowProgress() {
        return menu.getScaledArrowProgress();
    }

    @Override
    protected int getEnergyStored() {
        return menu.getEnergyStored();
    }

    @Override
    protected int getMaxEnergy() {
        return menu.getMaxEnergy();
    }

    @Override
    protected int getRemainProgress() {
        return menu.getRemainProgress();
    }

    @Override
    public List<TYPE> validUpgrades() {
        return List.of(TYPE.ENERGY, TYPE.SPEED, TYPE.LUCK);
    }

}
