package com.devdyna.synergy.init.builder.void_box;

import java.util.Random;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.data.ModelData;

@SuppressWarnings("null")
public class VoidBoxRender<T extends VoidBoxBE> implements BlockEntityRenderer<T> {

    private ItemRenderer itemRenderer;
    private int timer;

    public VoidBoxRender(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
        this.timer = new Random().nextInt(360);
    }

    @Override
    public void render(T be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {

        poseStack.pushPose();

        Direction facing = be.getBlockState()
                .getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();

        poseStack.translate(0.5, 0.5, 0.5);

        switch (facing) {
            case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(0));// 180
            case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90));// 90
            case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(90));// -90
            case NORTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180));// 0
            default -> {
            }
        }

        poseStack.translate(-0.5, -0.5, -0.5);

        float lid = 1.0F - be.getLidProgress(partialTick);
        lid = 1.0F - lid * lid * lid;
        float angle = lid * ((float) Math.PI / 2);

        float pivotX = 8f;
        float pivotY = 5f;
        float pivotZ = 12f;

        poseStack.translate(pivotX / 16f, pivotY / 16f, pivotZ / 16f);
        poseStack.mulPose(Axis.XP.rotation(angle));
        poseStack.translate(-pivotX / 16f, -pivotY / 16f, -pivotZ / 16f);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                zBlocks.VOID_BOX_LID.get().defaultBlockState(),
                poseStack, bufferSource, packedLight, packedOverlay,
                ModelData.EMPTY, RenderType.cutout());

        poseStack.popPose();

        timer++;

        if (timer > 360)

            timer = 0;

        if (be.getAnimationProgress() > 0.1) {
            poseStack.pushPose();

            poseStack.translate(0.5, 0.5 * be.getAnimationProgress(), 0.5);
            poseStack.mulPose(Axis.YP.rotationDegrees(timer));

            poseStack.scale(0.75F, 0.75F, 0.75F);

            itemRenderer.renderStatic(x.item(zItems.VOID_CRYSTAL),
                    ItemDisplayContext.GROUND, packedLight, packedOverlay, poseStack, bufferSource, be.getLevel(),
                    be.getLevel().random.nextInt());

            poseStack.popPose();
        }

    }

}
