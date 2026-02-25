package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class AlloySmelterScreen extends BaseMachineScreen<AlloySmelterMenu> {

    public AlloySmelterScreen(AlloySmelterMenu menu, Inventory playerInventory, Component title) {
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

}
