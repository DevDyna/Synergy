package com.devdyna.synergy.api.render.helpers;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.beLogic.AreaOfEffect;
import com.devdyna.synergy.api.beLogic.AreaOfEffect.AreaType;
import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public interface AOERender {

    default int getPlayerDistance() {
        return 16;
    }

    default void renderAOE(BlockEntity be,
                           @Nullable Direction dir,
                           PoseStack stack,
                           MultiBufferSource buffer) {

        if (!(be instanceof AreaOfEffect aoe))
            return;

        if (be.getLevel() == null)
            return;

        var player = be.getLevel().getNearestPlayer(
                be.getBlockPos().getX(),
                be.getBlockPos().getY(),
                be.getBlockPos().getZ(),
                getPlayerDistance(),
                false);

        if (!checkTool(player, be.getBlockPos()))
            return;

        stack.pushPose();

        int width = aoe.getWidth();
        int height = aoe.getHeight();

        if (dir == null && aoe.getAreaType().is(AreaType.MIDDLE)) {
            renderMiddleAOE(width, height, stack, buffer);
        }

        if (dir != null && aoe.getAreaType().is(AreaType.SIDE)) {
            renderSideAOE(width, height, dir, stack, buffer);
        }

        stack.popPose();
    }

    /*
     * CENTERED AOE
     * Rendered relative to BE position (0,0,0)
     */
    default void renderMiddleAOE(int width,
                                 int height,
                                 PoseStack stack,
                                 MultiBufferSource buffer) {

        int half = width / 2;

        double minX = -half;
        double maxX = half + 1;

        double minZ = -half;
        double maxZ = half + 1;

        double minY = 0;
        double maxY = height;

        LevelRenderer.renderLineBox(
                stack,
                buffer.getBuffer(RenderType.lines()),
                minX, minY, minZ,
                maxX, maxY, maxZ,
                0.9F, 0.9F, 0.9F, 1.0F,
                0.5F, 0.5F, 0.5F
        );
    }

    /*
     * DIRECTIONAL AOE
     * Rendered relative to BE position (0,0,0)
     */
    default void renderSideAOE(int width,
                               int height,
                               Direction face,
                               PoseStack stack,
                               MultiBufferSource buffer) {

        int half = width / 2;

        double minX = face.getStepX();
        double maxX = face.getStepX();
        double minY = face.getStepY();
        double maxY = height + face.getStepY();
        double minZ = face.getStepZ();
        double maxZ = face.getStepZ();

        switch (face) {

            case NORTH -> {
                minX += -half;
                maxX += half + 1;
                minZ += -width + 1;
                maxZ += 1;
            }

            case SOUTH -> {
                minX += -half;
                maxX += half + 1;
                // minZ += 0;
                maxZ += width;
            }

            case WEST -> {
                minX += -width + 1;
                maxX += 1;
                minZ += -half;
                maxZ += half + 1;
            }

            case EAST -> {
                // minX += 0;
                maxX += width;
                minZ += -half;
                maxZ += half + 1;
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + face);

            // case UP -> {
            //     minX += -half;
            //     maxX += half + 1;
            //     minZ += -half;
            //     maxZ += half + 1;
            //     minY += 0;
            //     maxY += height;
            // }

            // case DOWN -> {
            //     minX += -half;
            //     maxX += half + 1;
            //     minZ += -half;
            //     maxZ += half + 1;
            //     minY += -height + 1;
            //     maxY += 1;
            // }
        }

        LevelRenderer.renderLineBox(
                stack,
                buffer.getBuffer(RenderType.lines()),
                minX, minY, minZ,
                maxX, maxY, maxZ,
                0.9F, 0.9F, 0.9F, 1.0F,
                0.5F, 0.5F, 0.5F
        );
    }

    default boolean checkTool(Player player, BlockPos pos) {
        if (player == null)
            return false;

        var mainH = player.getMainHandItem();
        var offH = player.getOffhandItem();
        ItemStack item;

        if (mainH != null && mainH.is(zItems.CONFIGURATOR)) {
            item = mainH;
        } else if (offH != null && offH.is(zItems.CONFIGURATOR)) {
            item = offH;
        } else {
            return false;
        }

        if (item.get(zComponents.GLOBAL_POS) == null)
            return false;

        if (item.get(zComponents.GLOBAL_POS).dimension() != player.level().dimension())
            return false;

        return item.get(zComponents.GLOBAL_POS).pos().equals(pos);
    }
}
