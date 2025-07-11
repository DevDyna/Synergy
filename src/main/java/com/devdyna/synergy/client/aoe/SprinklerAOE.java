package com.devdyna.synergy.client.aoe;

import com.devdyna.synergy.api.client.TypeRenders;
import com.devdyna.synergy.init.builder.sprinkler.SprinklerBE;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;

public class SprinklerAOE<T extends SprinklerBE> implements BlockEntityRenderer<T>, TypeRenders<T> {

    public SprinklerAOE(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack stack, MultiBufferSource bufferIn,
            int combinedLightsIn, int combinedOverlayIn) {

        var radius = SprinklerBE.getRadius();

        renderDebugBox(be, new BlockPos(-radius, 0, -radius), new BlockPos(radius+1, 2, radius+1), null, stack, bufferIn);

    }

}
