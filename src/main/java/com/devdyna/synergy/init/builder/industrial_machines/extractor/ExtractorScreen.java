package com.devdyna.synergy.init.builder.industrial_machines.extractor;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.api.render.FluidGUITank;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.builder.industrial_machines.IndustrialUpgrade.UpgradeComponents.TYPE;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;

@SuppressWarnings("null")
public class ExtractorScreen extends BaseMachineScreen<ExtractorMenu> {

    public ExtractorScreen(ExtractorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl("textures/gui/container/electric_furnace.png");
    }

    @Override
    protected @Nullable ResourceLocation arrow() {
        return x.rl("minecraft", "textures/gui/sprites/container/furnace/burn_progress.png");
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
    protected int getEnergyStored() {
        return menu.getEnergyStored();
    }

    @Override
    protected int getMaxEnergy() {
        return menu.getMaxEnergy();
    }

    @Override
    protected int getRemainProgress() {
        return menu.getRemainProgress();
    }

    protected int getFluidAmount() {
        return menu.getFluidAmount();
    }

    protected Fluid getFluid() {
        return menu.getFluid();
    }

    protected int getMaxFluidAmount() {
        return menu.getMaxFluidAmount();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

        super.renderBg(guiGraphics, v, i, i1);

        guiGraphics.blit(x.rl("textures/gui/container/fluid_widget.png"),
                getGuiLeft() + 150,
                getGuiTop() + 5,
                0, 0,
                18, 72,
                36, 72);

        if (getMaxFluidAmount() > 0 && getFluidAmount() > 0)
            FluidGUITank.of()
                    .setFluid(getFluid())
                    .setMaxCapacity(getMaxFluidAmount())
                    .setAmount(getFluidAmount())
                    .size(72, 16)
                    .offset(getGuiLeft() + 151, getGuiTop() + 4)
                    .render(guiGraphics);

        guiGraphics.blit(x.rl("textures/gui/container/fluid_widget.png"),
                getGuiLeft() + 150,
                getGuiTop() + 5,
                18, 0,
                18, 72,
                36, 72);

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
    public List<TYPE> validUpgrades() {
        return List.of(TYPE.ENERGY, TYPE.SPEED, TYPE.LUCK);
    }

}
