package com.devdyna.synergy.init.builder.survival.drying_rack;

import com.devdyna.synergy.api.render.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;

@SuppressWarnings("null")
public class DryingRackRender<T extends DryingRackBE> implements BlockEntityRenderer<T> {

    private final ItemRenderer itemRenderer;

    public DryingRackRender(Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(
            T be,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int light,
            int overlay) {

        var stack = be.getStorage().getStackInSlot(0);

        if (!stack.isEmpty())
            SimpleItemRender.of()
                    .item(stack)
                    .rotateYN(DryingRackBlock.checkDir(be.getBlockState()) ? 0 : 90)
                    .move(0.5, -0.425, 0.5)
                    .scale(1.75f, 1.75f, 1.75f)
                    .build(itemRenderer, poseStack, light, overlay, buffer, be.getLevel());

    }

}
