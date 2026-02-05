package com.devdyna.synergy.api.render;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidRenderHelper {

    private TextureAtlasSprite texture;
    private int color = 0xFFFFFF;
    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float amount = 1000;
    private float height = 1.0f;
    private float width = 1.0f;
    private float scale = 1.0f;
    private Consumer<PoseStack> modified = p -> {
        p.scale(scale, scale, scale);
        p.translate(x, y, z);
    };

    public FluidRenderHelper() {
    }

    public static FluidRenderHelper of() {
        return new FluidRenderHelper();
    }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper texture(ResourceLocation rl) {
        return texture(Minecraft.getInstance()
                .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(rl));
    }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper texture(IClientFluidTypeExtensions xt) {
        return texture(xt.getStillTexture());
    }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper texture(FluidStack f) {
        return texture(IClientFluidTypeExtensions.of(f.getFluid()));
    }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper textureAndColor(FluidStack f) {
        color(f);
        return texture(IClientFluidTypeExtensions.of(f.getFluid()));
    }

    /**
     * define the texture of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper texture(TextureAtlasSprite sp) {
        texture = sp;
        return this;
    }

    /**
     * define the color of the render<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper color(int c) {
        color = c;
        return this;
    }

    /**
     * define the color of the render<br/>
     * <br/>
     * use the default fluid color<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper color(FluidStack f) {
        return color(IClientFluidTypeExtensions.of(f.getFluid())
                .getTintColor(f));
    }

    /**
     * define the fluid amount<br/>
     * <br/>
     * REQUIRED
     */
    public FluidRenderHelper amount(float a) {
        amount = a;
        return this;
    }

    /**
     * define the offset of the entire render<br/>
     * <br/>
     * Default = 0
     */
    public FluidRenderHelper offset(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * multiply the scale<br/>
     * <br/>
     * Default = x1.0f
     */
    @Deprecated
    public FluidRenderHelper scaleMultiplier(float h, float w) {
        this.height = h;
        this.width = w;
        return this;
    }

    /**
     * multiply the scale using the same scale factor<br/>
     * <br/>
     * Default = x1.0f
     */
    public FluidRenderHelper scaleMultiplier(float v) {
        this.scale = v;
        return this;
    }

    /**
     * multiply the scale using the same scale factor<br/>
     * <br/>
     * Default = x1.0f
     */
    public FluidRenderHelper modify(Consumer<PoseStack> m) {
        this.modified = m;
        return this;
    }

    public void build(PoseStack stack, MultiBufferSource bufferSource, int packedLight) {
        build(stack, bufferSource.getBuffer(Sheets.translucentCullBlockSheet()), packedLight);
    }

    public void build(PoseStack stack, VertexConsumer consumer, int packedLight) {

        if (this.texture == null)
            throw new NullPointerException(
                    "FluidRenderHelper/Texture: You need to set the texture sprite BEFORE build it!");

        stack.pushPose();

        modified.accept(stack);

        var pose = stack.last();

        float faceSize = 14 / 16f;
        float fluidHeight = amount * faceSize;
        float inset = 1 / 16f;

        TextureRenderUtil.renderDirectionalFace(Direction.NORTH, pose, consumer, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.SOUTH, pose, consumer, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.EAST, pose, consumer, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.WEST, pose, consumer, texture,
                inset, inset, inset,
                width * faceSize, height * fluidHeight,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.UP, pose, consumer, texture,
                inset, inset, inset + fluidHeight,
                width * faceSize, height * faceSize,
                color, packedLight);

        TextureRenderUtil.renderDirectionalFace(Direction.DOWN, pose, consumer, texture,
                inset, inset, 1 - inset,
                width * faceSize, height * faceSize,
                color, packedLight);

        stack.popPose();
    }

}
