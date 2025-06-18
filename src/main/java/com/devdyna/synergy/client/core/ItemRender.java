package com.devdyna.synergy.client.core;

import com.devdyna.synergy.init.builder._core.renderItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public interface ItemRender {

    private int getLightLevel(Level level, BlockPos pos) {
        return LightTexture.pack(level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos));
    }

    default Vec3 BaseOffset() {
        return new Vec3(0.5f, 1.15f, 0.5f);
    }

    default Vec3 BaseScale() {
        return new Vec3(0.5f, 0.5f, 0.5f);
    }

    default void renderItem(ItemStack itemStack, Vec3 offset, Vec3 scale, BlockEntity be, float tick, PoseStack stack,
            MultiBufferSource bufferSource, int packedLight,
            int packedOverlay) {
        if (!(be instanceof renderItem))
            return;

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        stack.pushPose();
        stack.translate(offset.x, offset.y, offset.z);
        stack.scale((float) scale.x, (float) scale.y, (float) scale.z);
        stack.mulPose(Axis.YP.rotationDegrees(((renderItem) be).getAngle()));

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(be.getLevel(),
                be.getBlockPos()), OverlayTexture.NO_OVERLAY, stack, bufferSource, be.getLevel(), 0);
        stack.popPose();
    }
}
