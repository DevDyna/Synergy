package com.devdyna.synergy.client.core;

import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.ItemTransferBE;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;

@SuppressWarnings("null")
public class ItemTransferDebugRender<T extends ItemTransferBE> implements BlockEntityRenderer<T> , TypeRenders<T> {

        public ItemTransferDebugRender(Context context) {
                super();
        }

        @Override
        public void render(T be, float tick, PoseStack stack, MultiBufferSource bufferSource, int packedLight,
                        int packedOverlay) {

                var input = be.getInputPos(be.getBlockState(), be.getLevel(), be.getBlockPos());
                var output = be.getOutputPos(be.getBlockState(), be.getLevel(), be.getBlockPos());

                var x = be.getBlockPos().getX();
                var y = be.getBlockPos().getY();
                var z = be.getBlockPos().getZ();

                if (input != null) {
                        var xi = input.getValue().relative(input.getKey()).getX();
                        var yi = input.getValue().relative(input.getKey()).getY();
                        var zi = input.getValue().relative(input.getKey()).getZ();

                        createPipeRender(xi - x, yi - y, zi - z, stack, be, bufferSource, packedLight, packedOverlay);
                }

                if (output != null) {
                        var xo = output.getValue().relative(output.getKey()).getX();
                        var yo = output.getValue().relative(output.getKey()).getY();
                        var zo = output.getValue().relative(output.getKey()).getZ();
                        createPipeRender(xo - x, yo - y, zo - z, stack, be, bufferSource, packedLight, packedOverlay);

                }

        }


}
