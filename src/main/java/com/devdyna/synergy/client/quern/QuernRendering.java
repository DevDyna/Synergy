package com.devdyna.synergy.client.quern;

import com.devdyna.synergy.init.builder.quern.QuernBE;
import com.devdyna.synergy.init.types.zBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

@SuppressWarnings({ "null", "deprecation" })
public class QuernRendering<T extends QuernBE> implements BlockEntityRenderer<T> {

    public QuernRendering(Context c) {
        super();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack poseStack,
            MultiBufferSource buffer, int light, int overlay) {

        float rotation = be.getRotation(partialTicks);

        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        poseStack.translate(-0.5, -0.5, -0.5);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                zBlocks.QUERN_MOVING.get().defaultBlockState(),
                poseStack,
                buffer,
                light,
                overlay);

        poseStack.popPose();
    }

}
