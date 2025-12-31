package com.devdyna.synergy.api.machine;

import com.devdyna.synergy.api.gui.BaseScreen;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public abstract class BaseMachineScreen<T extends BaseMachineMenu> extends BaseScreen<T> {

    public BaseMachineScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    protected abstract int getEnergyStored();

    protected abstract int getMaxEnergy();

    protected abstract int getRemainProgress();

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        
        guiGraphics.blit(
                x.rl("textures/gui/container/upgrade_slots.png"),
                getGuiLeft() +172,
                getGuiTop(),
                0, 0,
                32, 86,
                32, 86);

        super.renderBg(guiGraphics, v, i, i1);

        if (getRemainProgress() > 0)
            guiGraphics.drawString(font, Component.literal((1 + getRemainProgress()) + " ticks"),
                    getGuiLeft() + 68,
                    getGuiTop() + 70,
                    defaultToolTipColor.getRGB(), false);

        


        guiGraphics.blit(
                x.rl("textures/gui/container/energy.png"),
                getGuiLeft() + 8,
                getGuiTop() + 5,
                0, 0,
                18, 72,
                36, 72);

        if (getMaxEnergy() > 0 && getEnergyStored() > 0) {

            int slice = (getEnergyStored() * 72) / getMaxEnergy();

            guiGraphics.blit(
                    x.rl("textures/gui/container/energy.png"),
                    getGuiLeft() + 8,
                    getGuiTop() + 5 + (72 - slice),
                    18, 72 - slice,
                    18, slice,
                    36, 72);
        }

    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {

        super.render(graphics, pMouseX, pMouseY, pPartialTick);
        if (Pos.of(getGuiLeft() + 8, getGuiTop() + 5).setSize(18, 72).test(pMouseX, pMouseY)) {

            graphics.renderTooltip(font,

                    Component.literal(
                            (Screen.hasShiftDown() ? getEnergyStored()
                                    : StringUtil.getFormatNoRound()
                                            .format(getEnergyStored()))
                                    + " FE / " +
                                    (Screen.hasShiftDown() ? getMaxEnergy()
                                            : StringUtil.getFormatNoRound()
                                                    .format(getMaxEnergy()))
                                    + " FE"),

                    pMouseX,
                    pMouseY);
        }

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX + 57, this.titleLabelY,
                defaultToolTipColor.getRGB(), false);

    }

}
