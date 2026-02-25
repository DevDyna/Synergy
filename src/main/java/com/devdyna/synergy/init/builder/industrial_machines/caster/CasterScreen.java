package com.devdyna.synergy.init.builder.industrial_machines.caster;

import java.util.List;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.api.render.FluidGUITank;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class CasterScreen extends BaseMachineScreen<CasterMenu> {

    public CasterScreen(CasterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl("textures/gui/container/simple_dual.png");
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

        guiGraphics.blit(
                x.rl("textures/gui/container/left_label.png"),
                getGuiLeft() - 30,
                getGuiTop(),
                0, 0,
                32, 86,
                32, 86);

        super.renderBg(guiGraphics, v, i, i1);

        guiGraphics.blit(x.rl("textures/gui/container/fluid_widget.png"),
                getGuiLeft() - 22,
                getGuiTop() + 6,
                0, 0,
                18, 72,
                36, 72);

        if (getMaxFluidAmount() > 0 && getFluidAmount() > 0)
            FluidGUITank.of()
                    .setFluid(getFluid())
                    .setMaxCapacity(getMaxFluidAmount())
                    .setAmount(getFluidAmount())
                    .size(72, 16)
                    .offset(getGuiLeft() - 22, getGuiTop() + 5)
                    .render(guiGraphics);

        guiGraphics.blit(x.rl("textures/gui/container/fluid_widget.png"),
                getGuiLeft() - 22,
                getGuiTop() + 6,
                18, 0,
                18, 72,
                36, 72);

    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {

        super.render(graphics, pMouseX, pMouseY, pPartialTick);
        if (Pos.of(getGuiLeft() - 22, getGuiTop() + 6).setSize(18, 72).test(pMouseX, pMouseY)) {

            graphics.renderComponentTooltip(font,
                    List.of(
                            Component.literal(
                                    (Screen.hasShiftDown() ? getFluidAmount()
                                            : StringUtil.getFormatNoRound()
                                                    .format(getFluidAmount()))
                                            + " mB / " +
                                            (Screen.hasShiftDown() ? getMaxFluidAmount()
                                                    : StringUtil.getFormatNoRound()
                                                            .format(getMaxFluidAmount()))
                                            + " mB"),
                            Component.literal("Fluid: " + getFluid().getFluidType().getDescription().getString())),

                    pMouseX,
                    pMouseY);
        }

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX + 47, this.titleLabelY,
                defaultToolTipColor.getRGB(), false);
    }

}
