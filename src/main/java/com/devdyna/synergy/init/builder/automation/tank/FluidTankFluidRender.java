package com.devdyna.synergy.init.builder.automation.tank;

import com.devdyna.synergy.api.render.FluidRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;

@SuppressWarnings("null")
public class FluidTankFluidRender<T extends FluidTankBE> implements BlockEntityRenderer<T> {

        public FluidTankFluidRender(Context c) {
        }

        @Override
        public void render(T be, float partialTick, PoseStack poseStack,
                        MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

                var storage = be.getFluidStorage();

                if (storage.getFluidAmount() > 0) {

                        FluidRenderHelper.of()
                                        .textureAndColor(storage.getFluid())
                                        .amount(storage.getPercentuage())
                                        .build(poseStack, bufferSource, packedLight);
                }
        }
}