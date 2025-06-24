package com.devdyna.synergy.api.client;

import com.devdyna.synergy.api.pipe.pipeProperties;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zItemTag;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("null")
public interface TypeRenders<T> {

    @SuppressWarnings("unused")
    private int getLightLevel(Level level, BlockPos pos) {
        return LightTexture.pack(level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos));
    }

    default Vec3 BaseOffset() {
        return new Vec3(0.5f, 1.15f, 0.5f);
    }

    default Vec3 BaseScale() {
        return new Vec3(0.5f, 0.5f, 0.5f);
    }

    // default void renderItem(ItemStack itemStack, Vec3 offset, Vec3 scale, BlockEntity be, float tick, PoseStack stack,
    //         MultiBufferSource bufferSource, int packedLight,
    //         int packedOverlay) {
    //     if (!(be instanceof renderItem))
    //         return;

    //     ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
    //     stack.pushPose();
    //     stack.translate(offset.x, offset.y, offset.z);
    //     stack.scale((float) scale.x, (float) scale.y, (float) scale.z);
    //     stack.mulPose(Axis.YP.rotationDegrees(((renderItem) be).getAngle()));

    //     itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(be.getLevel(),
    //             be.getBlockPos()), OverlayTexture.NO_OVERLAY, stack, bufferSource, be.getLevel(), 0);
    //     stack.popPose();
    // }

    default void createPipeRender(int x, int y, int z, PoseStack stack, BlockEntity be, MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay) {
        var render = Minecraft.getInstance().getBlockRenderer();

        stack.pushPose();

        stack.translate(x, y, z);

        // pipe render
        var pipe = zBlocks.PIPE.get().defaultBlockState();

        for (EnumProperty<pipeProperties> prop : pipeType.PROPRTIES) {
            pipe = pipe.setValue(prop, pipeProperties.FALSE);
        }
        // wip , only make it white atm
        var color = bufferSource.getBuffer(RenderType.DEBUG_QUADS).setColor(1.0f, 0.0f, 0.0f, 1.0f);

        var player = be.getLevel().getNearestPlayer(be.getBlockPos().getX(),
                be.getBlockPos().getY(),
                be.getBlockPos().getZ(), 8, false);

        if (player != null && ((LivingEntity) player).getMainHandItem().is(zItemTag.VISUALIZER))
            render.renderBatched(pipe, be.getBlockPos(), be.getLevel(), stack, color, false, be.getLevel().getRandom());
        stack.popPose();
    }

}
