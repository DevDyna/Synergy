package com.devdyna.synergy.api.render;

import com.devdyna.synergy.init.builder.magic.void_box.VoidBoxBE;
import com.devdyna.synergy.init.types.zBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.data.ModelData;

public interface TinyChestOpening {

    default void renderChest(BlockEntity be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
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

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                zBlocks.VOID_BOX_LID.get().defaultBlockState(),
                poseStack, bufferSource, packedLight, packedOverlay,
                ModelData.EMPTY, RenderType.cutout());

        poseStack.popPose();
    }
}
