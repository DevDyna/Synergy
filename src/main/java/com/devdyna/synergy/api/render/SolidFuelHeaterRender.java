package com.devdyna.synergy.api.render;

import java.util.Random;

import com.devdyna.synergy.api.blockfactories.heater.SolidFuelHeaterBE;
import com.devdyna.synergy.api.render.helpers.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;

@SuppressWarnings("null")
public class SolidFuelHeaterRender<T extends SolidFuelHeaterBE> implements BlockEntityRenderer<T> {

    private final ItemRenderer itemRenderer;

    public SolidFuelHeaterRender(Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light,
            int overlay) {

        var stack = be.getStorage().getStackInSlot(0);

        if (!stack.isEmpty()) {
            for (int i = 0; i < Math.min((stack.getCount() / 4) + 1, 16); i++) {
                Random rand = new Random(be.getBlockPos().asLong() + i * 31L);

                SimpleItemRender.of()
                        .item(stack)
                        .rotateYN(rand.nextInt(360))
                        .rotateXP(90)
                        .move(0.5, 0.35 + (i * 0.025f), 0.5)
                        .scale(0.75f, 0.75f, 0.75f)
                        .build(itemRenderer, poseStack, 0xF000F0, overlay, buffer, be.getLevel());
            }
        }

    }

}
