package com.devdyna.synergy.api.render.helpers;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;

public class FluidGUITank {

    private int amount;
    private int max;
    private Fluid fluid;
    private int x;
    private int y;
    private int h;
    private int w;

    public FluidGUITank() {

    }

    public static FluidGUITank of(){
        return new FluidGUITank();
    }

    public FluidGUITank setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public FluidGUITank setMaxCapacity(int max) {
        this.max = max;
        return this;
    }

    public FluidGUITank offset(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public FluidGUITank size(int h, int w) {
        this.h = h;
        this.w = w;
        return this;
    }

    public FluidGUITank setFluid(Fluid fluid) {
        this.fluid = fluid;
        return this;
    }

    public void render(GuiGraphics guiGraphics) {

        var still = IClientFluidTypeExtensions.of(fluid).getStillTexture();

        if (still != null) {
            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                    .apply(still);

            int xTank = x ;
            int yTank = y + h;

            int color = IClientFluidTypeExtensions.of(fluid).getTintColor();
            float r = ((color >> 16) & 0xFF) / 255f;
            float g = ((color >> 8) & 0xFF) / 255f;
            float b = (color & 0xFF) / 255f;
            guiGraphics.setColor(r, g, b, 1f);

            int remaining = ((amount * h) / max) - 2;

            while (remaining > 0) {
                int renderHeight = Math.min(16, remaining);
                yTank -= renderHeight;

                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(r, g, b, 1f);
                RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);

                guiGraphics.blit(
                        xTank,
                        yTank,
                        0,
                        w,
                        renderHeight,
                        sprite);

                remaining -= renderHeight;
            }

            guiGraphics.setColor(1f, 1f, 1f, 1f);
        }
    }
}
