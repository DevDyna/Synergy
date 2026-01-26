package com.devdyna.synergy.init.builder.industrial_machines.macerator;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.IndustrialUpgrade.UpgradeComponents.TYPE;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class MaceratorScreen extends BaseMachineScreen<MaceratorMenu> {

    public MaceratorScreen(MaceratorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl("textures/gui/container/macerator.png");
    }

    @Override
    protected @Nullable ResourceLocation arrow() {
        return x.rl("minecraft", "textures/gui/sprites/container/furnace/burn_progress.png");
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
