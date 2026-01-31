package com.devdyna.synergy.init.builder.magic.crushing_tub;

import java.util.Random;

import com.devdyna.synergy.api.render.FluidRenderHelper;
import com.devdyna.synergy.api.render.SimpleItemRender;
import com.devdyna.synergy.api.utils.Ticker;
import com.devdyna.synergy.init.types.zItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class CrushingTubRender<T extends CrushingTubBE> implements BlockEntityRenderer<T> {

    private final ItemRenderer itemRenderer;
    private final Ticker timer = Ticker.of(360);

    public CrushingTubRender(Context ctx) {
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

        if (!stack.isEmpty()) {

            timer.commit();

            if (stack.is(Tags.Items.MUSIC_DISCS) || stack.is(zItems.CAKE_STICK))
                SimpleItemRender.of()
                        .move(0.5,
                                0.5 + (0.15 * (1 - Math.cos(Math.PI * Math.abs(timer.get() - 180) / 180.0)) / 2),
                                0.5)
                        .rotateYP(timer.get())
                        .scale(1.5f, 1.5F, 1.5F)
                        .item(stack)
                        .build(itemRenderer, poseStack, light, overlay, buffer, be.getLevel());
            else
                for (int i = 0; i < Math.min((stack.getCount() / 4) + 1, 16); i++) {
                    Random rand = new Random(be.getBlockPos().asLong() + i * 31L);

                    SimpleItemRender.of()
                            .item(stack)
                            .rotateYN(rand.nextInt(360))
                            .rotateXP(90)
                            .move(0.5, 0.15 + (i * 0.025f), 0.5)
                            .scale(0.75f, 0.75f, 0.75f)
                            .build(itemRenderer, poseStack, light, overlay, buffer, be.getLevel());
                }
        }

        var fluid = be.getFluidStorage();

        if (fluid.getFluidAmount() > 0) {
            FluidRenderHelper.of()
                    .textureAndColor(fluid.getFluid())
                    .offset(0f, 0.15f, 0f)
                    .amount(fluid.getPercentuage() * 0.15f)
                    .build(poseStack, buffer, light);
        }
    }

}
