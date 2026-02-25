package com.devdyna.synergy.init.builder.industrial_machines.furnace;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class ElectricFurnaceScreen extends BaseMachineScreen<ElectricFurnaceMenu> {

    public ElectricFurnaceScreen(ElectricFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl("textures/gui/container/simple_dual.png");
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
