package com.devdyna.synergy.init.builder.survival.evaporation_basin;

import java.util.Random;

import com.devdyna.synergy.api.render.helpers.FluidRenderHelper;
import com.devdyna.synergy.api.render.helpers.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;

@SuppressWarnings("null")
public class EvaporationBasinRender<T extends EvaporationBasinBE> implements BlockEntityRenderer<T> {

    private final ItemRenderer itemRenderer;

    public EvaporationBasinRender(Context ctx) {
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

        var stack = be.getStorage().getStackInSlot(0);

        if (!stack.isEmpty()) {
            Random rand = new Random(be.getBlockPos().asLong());
            SimpleItemRender.of()
                    .item(stack)
                    .rotateYN(rand.nextInt(360))
                    .rotateXP(90)
                    .move(0.5, 0.15, 0.5)
                    .scale(0.75f, 0.75f, 0.75f)
                    .build(itemRenderer, poseStack, light, overlay, buffer, be.getLevel());

        }

        var fluid = be.getFluidStorage();

        if (fluid.getFluidAmount() > 0) {
            FluidRenderHelper.of()
                    .scaleMultiplier(0.9f)
                    .offset(0.1f, 0.15f, 0.1f)
                    .textureAndColor(fluid.getFluid())
                    .amount((fluid.getPercentuage()) * 0.075f)
                    .build(poseStack, buffer, light);
        }
    }

}
