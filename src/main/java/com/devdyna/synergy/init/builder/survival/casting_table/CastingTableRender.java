package com.devdyna.synergy.init.builder.survival.casting_table;

import com.devdyna.synergy.api.render.helpers.FluidRenderHelper;
import com.devdyna.synergy.api.render.helpers.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("null")
public class CastingTableRender<T extends CastingTableBE> implements BlockEntityRenderer<T> {

    private final ItemRenderer itemRenderer;

    public CastingTableRender(Context ctx) {
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

        var mold = be.getStorage().getStackInSlot(CastingTableBE.MOLD_SLOT);
        var ingot = be.getStorage().getStackInSlot(CastingTableBE.OUTPUT_SLOT);
        var dir = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

        var rotation = (int) dir.toYRot();

        Vec3 pos = new Vec3(
                0.5 + dir.getStepX() * 0.25,
                0.9,
                0.5 + dir.getStepZ() * 0.25);

        if (!mold.isEmpty()) {
            SimpleItemRender.of()
                    .item(mold)
                    .rotateYN(rotation)
                    .rotateXN(90)
                    .move(pos.x, pos.y, pos.z)
                    .scale(2, 2, 2)
                    .build(itemRenderer, poseStack, light, overlay, buffer, be.getLevel());

        }
        if (!ingot.isEmpty()) {
            SimpleItemRender.of()
                    .item(ingot)
                    .rotateYN(rotation)
                    .rotateXN(90)
                    .move(pos.x, pos.y + 0.05, pos.z)
                    .scale(2, 2, 2)
                    .build(itemRenderer, poseStack, light, overlay, buffer, be.getLevel());

        }

        var fluid = be.getFluidStorage();

        if (fluid.getFluidAmount() > 0) {

            FluidRenderHelper.of()
                    .modify(p -> {
                        p.translate(0f, 14f / 16f, 0f);
                        p.scale(1f, 2f / 16f, 1f);
                    })
                    .textureAndColor(fluid.getFluid())
                    .amount(Mth.clamp(fluid.getPercentuage(), 0.0f, 1.0f))
                    .build(poseStack, buffer, light);

        }
    }

}
