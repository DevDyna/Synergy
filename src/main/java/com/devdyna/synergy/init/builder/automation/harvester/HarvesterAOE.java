package com.devdyna.synergy.init.builder.automation.harvester;

import com.devdyna.synergy.api.render.AOERender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("null")
public class HarvesterAOE<T extends HarvesterBE> implements BlockEntityRenderer<T>, AOERender {

    public HarvesterAOE(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack stack, MultiBufferSource bufferIn,
            int combinedLightsIn, int combinedOverlayIn) {

        var dir = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        var points = be.getPoints(be.getLevel(), BlockPos.ZERO, dir, true);
        var start = be.getStartPoint(points);
        var end = be.getEndPoint(points);

        renderDebugBox(be, start, end, dir, stack, bufferIn);

    }

}
