package com.devdyna.synergy.init.builder.magic.quern;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.render.helpers.ModelRenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import com.mojang.blaze3d.vertex.PoseStack;

@SuppressWarnings({ "null" })
public class QuernRendering<T extends QuernBE> implements BlockEntityRenderer<T> {

    private BlockRenderDispatcher brd;

    public QuernRendering(Context c) {
        super();
        this.brd = c.getBlockRenderDispatcher();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack poseStack,
            MultiBufferSource buffer, int light, int overlay) {

        float rotation = be.getRotation(partialTicks);

        ModelRenderHelper.of()
                .pivot(0.5, 0.5, 0.5)
                .rotateYP(rotation)
                .model(zStatic.AdditionalModel.QUERN)
                .build(Minecraft.getInstance().getModelManager(), brd, poseStack, light, overlay, buffer);

    }

}
