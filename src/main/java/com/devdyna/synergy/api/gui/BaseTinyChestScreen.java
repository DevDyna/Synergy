package com.devdyna.synergy.api.gui;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public abstract class BaseTinyChestScreen<T extends BaseTinyChestMenu> extends BaseScreen<T> {

    public BaseTinyChestScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBg(guiGraphics, pPartialTick, pMouseX, pMouseY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected @Nullable ResourceLocation arrow() {
        return null;
    }

    @Override
    protected boolean whenAnimateArrow() {
        return false;
    }

    @Override
    protected int getScaledArrowProgress() {
        return 0;
    }

}
