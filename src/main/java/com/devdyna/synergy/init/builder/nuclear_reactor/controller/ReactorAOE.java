package com.devdyna.synergy.init.builder.nuclear_reactor.controller;

import com.devdyna.synergy.api.render.AOERender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public class ReactorAOE<T extends ReactorControllerBE> implements BlockEntityRenderer<T>, AOERender {

    public ReactorAOE(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack stack, MultiBufferSource bufferIn,
            int combinedLightsIn, int combinedOverlayIn) {

        renderAOE(be,
                be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING),
                stack, bufferIn);
    }

}
