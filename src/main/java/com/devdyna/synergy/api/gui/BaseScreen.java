package com.devdyna.synergy.api.gui;

import java.awt.Color;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.ColorUtil;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public abstract class BaseScreen<T extends BaseMenu> extends AbstractContainerScreen<T> {

    public BaseScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    protected abstract Identifier background();

    protected abstract @Nullable Identifier arrow();

    protected abstract boolean whenAnimateArrow();

    protected abstract int getScaledArrowProgress();

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int j) {

        guiGraphics.blit(background(), getGuiLeft(), getGuiTop(), 0, 0, imageWidth, imageHeight, 0,0);

        if (arrow() != null && whenAnimateArrow())
            guiGraphics.blit(arrow(), getGuiLeft() + 73, getGuiTop() + 35, 0, 0, getScaledArrowProgress(), 16, 24, 16);

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

}
