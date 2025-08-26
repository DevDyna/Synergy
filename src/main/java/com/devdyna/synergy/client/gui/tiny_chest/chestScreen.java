package com.devdyna.synergy.client.gui.tiny_chest;

import java.util.Set;

import com.devdyna.synergy.client.gui.screenLocations;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class chestScreen extends AbstractContainerScreen<chestGUI> implements screenLocations {

    public chestScreen(chestGUI menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        var textures = Set.of(PLAYER_INVENTORY, MACHINE_SINGLE_SLOT);

        textures.forEach(t -> RenderSystem.setShaderTexture(0, t));

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        textures.forEach(t -> guiGraphics.blit(t, x, y, 0, 0, imageWidth, imageHeight));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

}
