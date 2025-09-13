package com.devdyna.synergy.client.gui.fuel_cell;

import java.util.List;

import com.devdyna.synergy.client.gui.screenLocations;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class FuelCellScreen extends AbstractContainerScreen<FuelCellMenu> implements screenLocations {

    public FuelCellScreen(FuelCellMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        var textures = List.of(PLAYER_INVENTORY, MACHINE_LABEL, DARK_WINDOW, DARK_SLOTS);

        textures.forEach(t -> RenderSystem.setShaderTexture(textures.indexOf(t), t));

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        textures.forEach(t -> guiGraphics.blit(t, x, y, 0, 0, imageWidth, imageHeight));

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            guiGraphics.blit(GREEN_PROGRESS_ARROW, x + 73, y + 35, 0, 0, menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
