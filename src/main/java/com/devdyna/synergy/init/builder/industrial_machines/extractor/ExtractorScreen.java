package com.devdyna.synergy.init.builder.industrial_machines.extractor;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.machine.BaseMachineScreen;
import com.devdyna.synergy.api.utils.Pos;
import com.devdyna.synergy.api.utils.StringUtil;
import com.devdyna.synergy.api.utils.x;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;

@SuppressWarnings("null")
public class ExtractorScreen extends BaseMachineScreen<ExtractorMenu> {

    public ExtractorScreen(ExtractorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected ResourceLocation background() {
        return x.rl("textures/gui/container/extractor.png");
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

        // guiGraphics.blit(
        // x.rl("textures/gui/container/fluid_tank.png"),
        // getGuiLeft() - 30,
        // getGuiTop(),
        // 0, 0,
        // 32, 86,
        // 32, 86);

        super.renderBg(guiGraphics, v, i, i1);

        guiGraphics.blit(x.rl("textures/gui/container/fluid_widget.png"),
                getGuiLeft() + 150,
                getGuiTop() + 5,
                0, 0,
                18, 72,
                36, 72);

        if (getMaxFluidAmount() > 0 && getFluidAmount() > 0) {

            int tankHeight = 72;
            int tankWidth = 16;
            int fluidHeight = ((getFluidAmount() * tankHeight) / getMaxFluidAmount()) - 2;

            var still = IClientFluidTypeExtensions.of(getFluid()).getStillTexture();

            if (still != null) {
                TextureAtlasSprite sprite = Minecraft.getInstance()
                        .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                        .apply(still);

                int x = getGuiLeft() + 150 + 1;
                int yBottom = getGuiTop() + 5 + tankHeight - 1;

                // Tint color
                int color = IClientFluidTypeExtensions.of(getFluid()).getTintColor();
                float r = ((color >> 16) & 0xFF) / 255f;
                float g = ((color >> 8) & 0xFF) / 255f;
                float b = (color & 0xFF) / 255f;
                guiGraphics.setColor(r, g, b, 1f);

                int remaining = fluidHeight;
                int y = yBottom;

                while (remaining > 0) {
                    int renderHeight = Math.min(16, remaining);
                    y -= renderHeight;

                    // z = 0 ensures the fluid is on the same layer as normal GUI
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderColor(r, g, b, 1f);
                    RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);

                    guiGraphics.blit(
                            x,
                            y,
                            0,
                            tankWidth,
                            renderHeight,
                            sprite);

                    remaining -= renderHeight;
                }

                // Reset color
                guiGraphics.setColor(1f, 1f, 1f, 1f);
            }
        }

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

}
