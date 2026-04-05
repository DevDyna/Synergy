package com.devdyna.synergy.init.builder.magic.void_box;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.render.helpers.PreFabRender;
import com.devdyna.synergy.api.render.helpers.SimpleItemRender;
import com.devdyna.synergy.init.types.zItems;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;

@SuppressWarnings("null")
public class VoidBoxRender<T extends VoidBoxBE> implements BlockEntityRenderer<T> {

        private ItemRenderer itemRenderer;

        public VoidBoxRender(BlockEntityRendererProvider.Context ctx) {
                this.itemRenderer = ctx.getItemRenderer();
        }

        @Override
        public void render(T be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                        int packedLight, int packedOverlay) {

                PreFabRender.renderChest(be, zStatic.AdditionalModel.VOID_BOX, partialTick, poseStack, bufferSource,
                                packedLight, packedOverlay);

                var angle = ((be.getLevel().getGameTime() % 360) + partialTick) * 4f;

                SimpleItemRender.of()
                                .whenOn(be.getAnimationProgress() > 0.1)
                                .move(0.5,
                                                0.5 * be.getAnimationProgress()
                                                                + (0.15 * (1 - Math.cos(Math.PI * Math.abs(angle - 180)
                                                                                / 180.0)) / 2),
                                                0.5)
                                .rotateYP(angle)
                                .scale(0.75F, 0.75F, 0.75F)
                                .item(zItems.VOID_CRYSTAL)
                                .build(itemRenderer, poseStack, packedLight, packedOverlay, bufferSource,
                                                be.getLevel());

        }

}
