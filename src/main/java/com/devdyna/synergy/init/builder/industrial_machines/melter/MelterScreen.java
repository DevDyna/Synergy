package com.devdyna.synergy.init.builder.industrial_machines.melter;

import java.util.List;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineScreen;
import com.devdyna.synergy.api.render.helpers.FluidGUITank;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class MelterScreen extends BaseMachineScreen<MelterMenu> {

        public MelterScreen(MelterMenu menu, Inventory playerInventory, Component title) {
                super(menu, playerInventory, title);
        }

        @Override
        protected ResourceLocation background() {
                return x.rl("textures/gui/container/only_input.png");
        }

        @Override
        protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

                super.renderBg(guiGraphics, v, i, i1);

                renderFluidTank(guiGraphics, 150, 5);

        }

        @Override
        public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {

                super.render(graphics, pMouseX, pMouseY, pPartialTick);
                if (Pos.of(getGuiLeft() + 150, getGuiTop() + 5).setSize(18, 72).test(pMouseX, pMouseY)) {

                        graphics.renderComponentTooltip(font,
                                        List.of(
                                                        Component.literal(
                                                                        (Screen.hasShiftDown() ? getFluidAmount()
                                                                                        : StringUtil.getFormatNoRound()
                                                                                                        .format(getFluidAmount()))
                                                                                        + " mB / " +
                                                                                        (Screen.hasShiftDown()
                                                                                                        ? getMaxFluidAmount()
                                                                                                        : StringUtil.getFormatNoRound()
                                                                                                                        .format(getMaxFluidAmount()))
                                                                                        + " mB"),
                                                        Component.literal("Fluid: " + getFluid().getFluidType()
                                                                        .getDescription().getString())),

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
