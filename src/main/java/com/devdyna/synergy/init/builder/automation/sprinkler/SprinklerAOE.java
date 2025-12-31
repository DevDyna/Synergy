package com.devdyna.synergy.init.builder.automation.sprinkler;

import com.devdyna.synergy.api.beLogic.SimpleAOE;
import com.devdyna.synergy.api.render.TypeRenders;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;

@SuppressWarnings("null")
public class SprinklerAOE<T extends SprinklerBE> implements BlockEntityRenderer<T>, TypeRenders<T> {

    public SprinklerAOE(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack stack, MultiBufferSource bufferIn,
            int combinedLightsIn, int combinedOverlayIn) {
        int radius = 0;
        if (be instanceof SimpleAOE aoe)
            radius = aoe.radius();

        renderDebugBox(be, new BlockPos(-radius, 0, -radius), new BlockPos(radius + 1, 2, radius + 1), null, stack,
                bufferIn);

    }

}
