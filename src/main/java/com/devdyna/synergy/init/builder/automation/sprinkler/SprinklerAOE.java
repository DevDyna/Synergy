package com.devdyna.synergy.init.builder.automation.sprinkler;

import com.devdyna.synergy.api.render.AOERender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;

@SuppressWarnings("null")
public class SprinklerAOE<T extends SprinklerBE> implements BlockEntityRenderer<T>, AOERender {

    public SprinklerAOE(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack stack, MultiBufferSource bufferIn,
            int combinedLightsIn, int combinedOverlayIn) {

        renderAOE(be, null, stack, bufferIn);

    }

}
