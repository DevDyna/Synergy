package com.devdyna.synergy.api.render;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.api.render.helpers.FluidRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;

@SuppressWarnings("null")
public class FullTankRender<T extends BETank> implements BlockEntityRenderer<T> {

        public FullTankRender(Context c) {
        }

        @Override
        public void render(T be, float partialTick, PoseStack poseStack,
                        MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

                var storage = be.getFluidStorage();

                if (storage.getFluidAmount() > 0) 
                        FluidRenderHelper.of()
                                        .textureAndColor(storage.getFluid())
                                        .amount(storage.getPercentuage())
                                        .build(poseStack, bufferSource, packedLight);
                
        }
}