package com.devdyna.synergy.init.builder.industrial_machines.furnace;

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
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX + 47, this.titleLabelY,
                defaultToolTipColor.getRGB(), false);
    }

}
