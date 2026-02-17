package com.devdyna.synergy.init.builder.automation.chopper;

import java.awt.Color;
import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.gui.BaseScreen;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class ChopperScreen extends BaseScreen<ChopperMenu> {

    public ChopperScreen(ChopperMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
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
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

        if (menu.handleEnergy())
            guiGraphics.blit(x.rl("textures/gui/container/left_label.png"),
                    getGuiLeft() - 30, getGuiTop(),
                    0, 0,
                    32, 86,
                    32, 86);

        if (menu.handleEnergy()) {
            guiGraphics.blit(
                    x.rl("textures/gui/container/energy.png"),
                    getGuiLeft() - 22,
                    getGuiTop() + 6,
                    0, 0,
                    18, 72,
                    36, 72);
        }

        super.renderBg(guiGraphics, v, i, i1);

        if (menu.getMaxEnergy() > 0 && menu.getEnergy() > 0 && menu.handleEnergy()) {

            int slice = (menu.getEnergy() * 72) / menu.getMaxEnergy();

            guiGraphics.blit(
                    x.rl("textures/gui/container/energy.png"),
                    getGuiLeft() - 22,
                    getGuiTop() + 6 + (72 - slice),
                    18, 72 - slice,
                    18, slice,
                    36, 72);
        }
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

        guiGraphics.drawString(this.font, Component.literal("Range: " + menu.getRange()), 55 - 5, 45,
                Color.LIGHT_GRAY.getRGB(), true);

        if (!menu.hasAxe()) {
            guiGraphics.drawString(this.font, Component.literal("No valid axe"), 50 - 5 - 5 + 2 - 1, 34,
                    Color.RED.getRGB(), true);
            return;
        }

        if (!menu.isCrafting()) {
            guiGraphics.drawString(this.font, Component.literal("Missing Fuel"), 50 - 5 - 5 + 2 - 1, 34,
                    Color.RED.getRGB(), true);
            return;
        }

        guiGraphics.drawString(this.font, Component.literal("Ready"), 51 - 3 + 5, 34,
                Color.GREEN.getRGB(), true);

    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int xmouse, int ymouse) {
        super.renderTooltip(guiGraphics, xmouse, ymouse);
        // if (Pos.of(getGuiLeft() + 12, getGuiTop() + 35).setSize(18, 16).test(xmouse, ymouse))
        //     guiGraphics.renderTooltip(font,
        //             Component.literal(
        //                     "Burntime: " + (menu.getProgress() <= 0 ? "Empty" : (menu.getProgress() + " ticks left"))),
        //             xmouse, ymouse);

        if (menu.handleEnergy())
            if (Pos.of(getGuiLeft() - 22, getGuiTop() + 6).setSize(18, 72).test(xmouse, ymouse)) {

                guiGraphics.renderComponentTooltip(font,
                        List.of(
                                Component.literal(
                                        (Screen.hasShiftDown() ? menu.getEnergy()
                                                : StringUtil.getFormatNoRound()
                                                        .format(menu.getEnergy()))
                                                + " FE / " +
                                                (Screen.hasShiftDown() ? menu.getMaxEnergy()
                                                        : StringUtil.getFormatNoRound()
                                                                .format(menu.getMaxEnergy()))
                                                + " FE")),

                        xmouse,
                        ymouse);
            }
    }
}
