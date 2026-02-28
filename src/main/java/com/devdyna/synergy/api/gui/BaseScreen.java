package com.devdyna.synergy.api.gui;

import java.awt.Color;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.ColorUtil;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public abstract class BaseScreen<T extends BaseMenu> extends AbstractContainerScreen<T> {

    public BaseScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    protected abstract ResourceLocation background();

    protected @Nullable ResourceLocation arrow(){
        return null;
    }

    protected boolean whenAnimateArrow(){
        return false;
    }

    protected int getScaledArrowProgress(){
        return 0;
    }

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int j) {
        guiGraphics.blit(background(), getGuiLeft(), getGuiTop(), 0, 0, imageWidth, imageHeight);
        renderArrow(guiGraphics);
    }

    protected void renderArrow(GuiGraphics guiGraphics) {
        if (arrow() != null && whenAnimateArrow())
            guiGraphics.blit(arrow(), getGuiLeft() + 73, getGuiTop() + 35, 0, 0, getScaledArrowProgress(), 16, 24, 16);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    public void drawCenteredString(GuiGraphics pGuiGraphics,Font font, Component text, int x, int y, int color,boolean dropShadow) {
      FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
       pGuiGraphics.drawString(font, formattedcharsequence, x - font.width(formattedcharsequence) / 2, y, color,dropShadow);
   }

}
