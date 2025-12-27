package com.devdyna.synergy.init.builder.nuclear_reactor.controller;

import com.devdyna.synergy.api.render.AOERender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public class ReactorAOE<T extends ReactorControllerBE> implements BlockEntityRenderer<T>, AOERender {

    public ReactorAOE(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack stack, MultiBufferSource bufferIn,
            int combinedLightsIn, int combinedOverlayIn) {

        var dir = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

        var level = be.getLevel();
        var xz = be.radius();
        var y = be.height();

        var points = be.getPoints(level, BlockPos.ZERO, dir, true, xz, y);

        renderDebugBox(be, be.getStartPoint(points), be.getEndPoint(points), dir, stack, bufferIn);

    }

}
