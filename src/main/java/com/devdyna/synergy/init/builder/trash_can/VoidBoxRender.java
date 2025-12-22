package com.devdyna.synergy.init.builder.trash_can;

import com.devdyna.synergy.init.types.zBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.data.ModelData;

@SuppressWarnings("null")
public class VoidBoxRender<T extends VoidBoxBE> implements BlockEntityRenderer<T> {

    public VoidBoxRender(BlockEntityRendererProvider.Context ctx) {

    }

    @Override
    public void render(T be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {

        // TODO VERY BROKEN!!!

        poseStack.pushPose();

        var state = zBlocks.VOID_BOX_LID.get().defaultBlockState();

        float lid = 1.0F - be.getLidProgress(partialTick);
        lid = 1.0F - lid * lid * lid;

        float angle = lid * ((float) Math.PI / 2);

        Direction facing = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();

        float pivotX = 8f, pivotY = 5f, pivotZ = 8f;
        Axis axis = Axis.XP;
        switch (facing) {
            case NORTH -> {
                pivotZ = 4f;
                axis = Axis.XP;

            }
            case SOUTH -> {
                pivotZ = 12f;
                axis = Axis.XP;
                angle = -angle;
            }
            case WEST -> {
                pivotX = 4f;
                axis = Axis.ZP;
                angle = -angle;

            }
            case EAST -> {
                pivotX = 12f;
                axis = Axis.ZP;

            }
            default -> {
            }
        }

        poseStack.translate(pivotX / 16f, pivotY / 16f, pivotZ / 16f);
        poseStack.mulPose(axis.rotation(-angle));
        poseStack.translate(-pivotX / 16f, -pivotY / 16f, -pivotZ / 16f);

        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                state,
                poseStack, bufferSource, packedLight, packedOverlay,
                ModelData.EMPTY, RenderType.cutout());

        poseStack.popPose();
    }

}
