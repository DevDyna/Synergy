package com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.gui.BaseScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class FuelCellScreen extends BaseScreen<FuelCellMenu> {

    public FuelCellScreen(FuelCellMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        super.renderBg(guiGraphics, v, i, i1);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected Identifier background() {
        return x.rl("textures/gui/container/fuel_cell.png");
    }

    @Override
    protected @Nullable Identifier arrow() {
        return x.rl("textures/gui/green_progress_arrow.png");
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
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY,
                defaultToolTipColor.darker().darker().getRGB(), false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY,
                defaultToolTipColor.darker().darker().getRGB(), false);
    }
}
