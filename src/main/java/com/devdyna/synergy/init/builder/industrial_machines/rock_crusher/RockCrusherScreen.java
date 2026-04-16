package com.devdyna.synergy.init.builder.industrial_machines.rock_crusher;

import java.util.List;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineScreen;
import com.devdyna.synergy.api.utils.ArrayUtils;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents.UpgradeType;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class RockCrusherScreen extends BaseMachineScreen<RockCrusherMenu> {

        public RockCrusherScreen(RockCrusherMenu menu, Inventory playerInventory, Component title) {
                super(menu, playerInventory, title);
        }

        @Override
        public List<UpgradeType> validUpgrades() {
                return ArrayUtils.concat(DEFAULT_UPGRADES, UpgradeType.LUCK, UpgradeType.FLUID);
        }

        @Override
        protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

                renderLeftLabel(guiGraphics);

                super.renderBg(guiGraphics, v, i, i1);

                renderFluidTank(guiGraphics, -22, +6);

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
