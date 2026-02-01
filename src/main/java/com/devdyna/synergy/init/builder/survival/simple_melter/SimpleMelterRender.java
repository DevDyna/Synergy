package com.devdyna.synergy.init.builder.survival.simple_melter;

import java.util.Random;

import com.devdyna.synergy.api.render.FluidRenderHelper;
import com.devdyna.synergy.api.render.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.Mth;

@SuppressWarnings("null")
public class SimpleMelterRender<T extends SimpleMelterBE> implements BlockEntityRenderer<T> {

    private final ItemRenderer itemRenderer;

    public SimpleMelterRender(Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(
            T be,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int light,
            int overlay) {
        // brute-force light due occlusion
        var forceLight = 0xF000F0;
        var stack = be.getStorage().getStackInSlot(0);

        if (!stack.isEmpty()) {
            for (int i = 0; i < Math.min((stack.getCount() / 4) + 1, 16); i++) {
                Random rand = new Random(be.getBlockPos().asLong() + i * 31L);

                SimpleItemRender.of()
                        .item(stack)
                        .rotateYN(rand.nextInt(360))
                        .rotateXP(90)
                        .move(0.5, 0.5 + (i * 0.025f), 0.5)
                        .scale(0.75f, 0.75f, 0.75f)
                        .build(itemRenderer, poseStack, forceLight, overlay, buffer, be.getLevel());
            }
        }

        var fluid = be.getFluidStorage();

        if (fluid.getFluidAmount() > 0)
            FluidRenderHelper.of()
                    .textureAndColor(fluid.getFluid())
                    .offset(0f, 0.45f, 0f)
                    .amount(Mth.clamp(fluid.getPercentuage(), 0.0f, 1.0f) * 0.5f)
                    .build(poseStack, buffer, forceLight);

    }

}
