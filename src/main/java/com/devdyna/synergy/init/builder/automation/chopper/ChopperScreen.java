package com.devdyna.synergy.init.builder.automation.chopper;

import java.awt.Color;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.gui.BaseScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class ChopperScreen extends BaseScreen<ChopperMenu> {

    public ChopperScreen(ChopperMenu menu, Inventory playerInventory, Component title) {
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
    protected ResourceLocation background() {
        return x.rl("textures/gui/container/chopper.png");
    }

    @Override
    protected @Nullable ResourceLocation arrow() {
        return x.rl("minecraft", "textures/gui/sprites/container/furnace/lit_progress.png");
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
    protected void renderArrow(GuiGraphics guiGraphics) {
        if (arrow() != null && whenAnimateArrow())
            guiGraphics.blit(arrow(),
                    getGuiLeft() + 13,
                    getGuiTop() + 36 + (14 - getScaledArrowProgress()),
                    0, (14 - getScaledArrowProgress()),
                    14, getScaledArrowProgress(),
                    14, 14);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);

        guiGraphics.drawString(this.font, Component.literal("Status"), 55 - 5 + 3, 24,
                Color.LIGHT_GRAY.getRGB(), true);

        if (!menu.hasAxe())
            guiGraphics.drawString(this.font, Component.literal("No valid axe"), 50 - 5 - 5 + 2 - 1, 34,
                    Color.RED.getRGB(), true);
        else
            guiGraphics.drawString(this.font, Component.literal("Ready"), 51 - 3 + 5, 34,
                    Color.GREEN.getRGB(), true);

        guiGraphics.drawString(this.font, Component.literal("Range: " + menu.getRange()), 55 - 5, 45,
                Color.LIGHT_GRAY.getRGB(), true);

    }
}
