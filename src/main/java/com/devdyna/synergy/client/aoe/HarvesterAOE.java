package com.devdyna.synergy.client.aoe;

import com.devdyna.synergy.api.client.TypeRenders;
import com.devdyna.synergy.init.builder.harvester.HarvesterBE;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class HarvesterAOE<T extends HarvesterBE> implements BlockEntityRenderer<T>, TypeRenders<T> {

    public HarvesterAOE(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack stack, MultiBufferSource bufferIn,
            int combinedLightsIn, int combinedOverlayIn) {

        var points = be.getPoints(be.getLevel(), be.getBlockState(), BlockPos.ZERO);
        var start = be.getStartPoit(points);
        var end = be.getEndPoit(points);
        var dir = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

        renderDebugBox(be, start, end,dir, stack, bufferIn);

    }

}
