package com.devdyna.synergy.api.render;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.magic.void_box.VoidBoxBE;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class PreFabRender {

    public static void renderChest(BlockEntity be, float partialTick, PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);

        switch (be.getBlockState()
                .getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite()) {
            case Direction.SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(0));// 180
            case Direction.WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90));// 90
            case Direction.EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(90));// -90
            case Direction.NORTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180));// 0
            default -> {
            }
        }

        poseStack.translate(-0.5, -0.5, -0.5);

        // 8/16 | 5/16 | 12/16
        poseStack.translate(0.5f, 0.3125f, 0.75f);
        poseStack.mulPose(
                Axis.XP.rotation((float) ((1.0F - Math.pow((1.0F - ((VoidBoxBE) be)
                        .getLidProgress(partialTick)), 3))
                        * ((float) Math.PI / 2))));
        poseStack.translate(-0.5f, -0.3125f, -0.75f);

        ModelRenderHelper.of()
                .model(zStatic.AdditionalModel.VOID_BOX)
                .build(poseStack, packedLight, packedOverlay, bufferSource);

        poseStack.popPose();
    }
}
