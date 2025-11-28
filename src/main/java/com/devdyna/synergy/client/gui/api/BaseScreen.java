package com.devdyna.synergy.client.gui.api;

import java.awt.Color;

import javax.annotation.Nullable;

import com.devdyna.synergy.utils.ColorUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public abstract class BaseScreen<T extends BaseMenu> extends AbstractContainerScreen<T> {

    public BaseScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    protected abstract ResourceLocation background();

    protected abstract @Nullable ResourceLocation arrow();

    protected abstract boolean whenAnimateArrow();

    protected abstract int getScaledArrowProgress();

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        // RenderSystem.setShader(GameRenderer::getPositionTexShader);
        // RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // RenderSystem.setShaderTexture(0, background());

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(background(), x, y, 0, 0, imageWidth, imageHeight);

        if (arrow() != null && whenAnimateArrow())
            guiGraphics.blit(arrow(), x + 73, y + 35, 0, 0, getScaledArrowProgress(), 16, 24, 16);

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

}
