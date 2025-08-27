package com.devdyna.synergy.client.gui.reactor_port;

import java.util.Set;

import com.devdyna.synergy.client.gui.screenLocations;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class portScreen extends AbstractContainerScreen<portGUI> implements screenLocations {

    public portScreen(portGUI menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        var textures = Set.of(PLAYER_INVENTORY, MACHINE_SINGLE_SLOT);//TODO change machine single slot

        textures.forEach(t -> RenderSystem.setShaderTexture(0, t));

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        textures.forEach(t -> guiGraphics.blit(t, x, y, 0, 0, imageWidth, imageHeight));
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

}
