package com.devdyna.synergy.init.builder.magic.void_box;

import java.util.Random;

import com.devdyna.synergy.api.render.SimpleItemRender;
import com.devdyna.synergy.api.render.TinyChestOpening;
import com.devdyna.synergy.init.types.zItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;

@SuppressWarnings("null")
public class VoidBoxRender<T extends VoidBoxBE> implements BlockEntityRenderer<T>, TinyChestOpening {

    private ItemRenderer itemRenderer;
    private int timer;

    public VoidBoxRender(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
        this.timer = new Random().nextInt(360);
    }

    @Override
    public void render(T be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {

        renderChest(be, partialTick, poseStack, bufferSource, packedLight, packedOverlay);

        timer++;

        if (timer > 360)
            timer = 0;

        SimpleItemRender.of()
                .whenOn(be.getAnimationProgress() > 0.1)
                .move(0.5, 0.5 * be.getAnimationProgress(), 0.5)
                .rotateYP(timer)
                .scale(0.75F, 0.75F, 0.75F)
                .item(zItems.VOID_CRYSTAL)
                .build(itemRenderer, poseStack, packedLight, packedOverlay, bufferSource, be.getLevel());

    }

}
