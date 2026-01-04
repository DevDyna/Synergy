package com.devdyna.synergy.init.builder.industrial_machines.compressor;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class CompressorScreen extends BaseMachineScreen<CompressorMenu> {

    public CompressorScreen(CompressorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl("textures/gui/container/compressor.png");
    }

    @Override
    protected @Nullable ResourceLocation arrow() {
        return x.rl("minecraft", "textures/gui/sprites/container/furnace/burn_progress.png");
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

        if (whenAnimateArrow())
            guiGraphics.blit(
                    x.rl("textures/gui/sprite/compressor_arrow.png"),
                    getGuiLeft() + 47,
                    getGuiTop() + 36,
                    1,0, 0,
                    16, 9,
                    16, 9);

        super.renderBg(guiGraphics, v, i, i1);
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

}
