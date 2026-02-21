package com.devdyna.synergy.init.builder.magic.logic_box;

import java.util.Random;

import com.devdyna.synergy.api.render.SimpleItemRender;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.render.PreFabRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;

@SuppressWarnings("null")
public class LogicBoxRender<T extends LogicBoxBE> implements BlockEntityRenderer<T> {

        private ItemRenderer itemRenderer;
        private int timer;

        public LogicBoxRender(BlockEntityRendererProvider.Context ctx) {
                this.itemRenderer = ctx.getItemRenderer();
                this.timer = new Random().nextInt(360);
        }

        @Override
        public void render(T be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                        int packedLight, int packedOverlay) {

                var condition = be.getAnimationProgress() > 0.1;

                PreFabRender.renderChest(be,
                                (be.isInverted() ? zStatic.AdditionalModel.LOGIC_BOX_OFF
                                                : zStatic.AdditionalModel.LOGIC_BOX_ON),
                                partialTick, poseStack, bufferSource,
                                packedLight, packedOverlay);

                if (condition) {
                        timer++;

                        if (timer > 360)
                                timer = 0;
                }

                if (!be.getFilterSlot().isEmpty())
                        SimpleItemRender.of()
                                        .whenOn(condition)
                                        .move(0.5,
                                                        0.5 * be.getAnimationProgress()
                                                                        + (0.15 * (1 - Math.cos(
                                                                                        Math.PI * Math.abs(timer - 180)
                                                                                                        / 180.0))
                                                                                        / 2),
                                                        0.5)
                                        .rotateYP(timer)
                                        .scale(0.75F, 0.75F, 0.75F)
                                        .item(be.getFilterSlot())
                                        .build(itemRenderer, poseStack, packedLight, packedOverlay, bufferSource,
                                                        be.getLevel());

        }

}
