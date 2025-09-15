package com.devdyna.synergy.api.client;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("null")
public interface TypeRenders<T> {

    /*
     * Distance of player from BE to render
     */
    default int getPlayerDistance() {
        return 16;
    }

    /**
     * @param xyz block position to traslate NOT BLOCKPOS
     */
    // default void createPipeRender(int x, int y, int z, PoseStack stack,
    // BlockEntity be, MultiBufferSource bufferSource,
    // int packedLight,
    // int packedOverlay) {
    // var render = Minecraft.getInstance().getBlockRenderer();
    // stack.pushPose();
    // stack.translate(x, y, z);
    // // pipe render
    // var pipe = zBlocks.PIPE.get().defaultBlockState();
    // for (EnumProperty<pipeProperties> prop : pipeType.PROPRTIES) {
    // pipe = pipe.setValue(prop, pipeProperties.FALSE);
    // }
    // // wip , only make it white atm
    // var color = bufferSource.getBuffer(RenderType.DEBUG_QUADS).setColor(1.0f,
    // 0.0f, 0.0f, 1.0f);
    // var player = be.getLevel().getNearestPlayer(be.getBlockPos().getX(),
    // be.getBlockPos().getY(),
    // be.getBlockPos().getZ(), getPlayerDistance(), false);
    // if (checkTool(ModeTypes.SHOW_TRACK, player, be.getBlockPos()))
    // render.renderBatched(pipe, be.getBlockPos(), be.getLevel(), stack, color,
    // false, be.getLevel().getRandom());
    // stack.popPose();
    // }

    default void renderDebugBox(BlockEntity be, BlockPos start, BlockPos end, @Nullable Direction dir, PoseStack stack,
            MultiBufferSource bufferIn) {

        var player = be.getLevel().getNearestPlayer(be.getBlockPos().getX(),
                be.getBlockPos().getY(),
                be.getBlockPos().getZ(), getPlayerDistance(), false);

        VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.lines());

        if (dir != null)
            switch (dir) {
                case Direction.NORTH:
                    start = start.relative(Direction.SOUTH, 1);
                    end = end.relative(Direction.EAST, 1);
                    break;
                case Direction.SOUTH:
                    // start = start.relative(Direction.EAST, 2);
                    end = end.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1);
                    break;
                case Direction.EAST:
                    end = end.relative(Direction.SOUTH, 1);
                    end = end.relative(Direction.EAST, 1);
                    break;
                case Direction.WEST:
                    start = start.relative(Direction.EAST, 1);
                    end = end.relative(Direction.SOUTH, 1);
                    break;
                default:
                    break;
            }

        if (checkTool(player, be.getBlockPos())) {
            stack.pushPose();
            LevelRenderer.renderLineBox(stack, vertexconsumer,
                    start.getX(), start.getY(), start.getZ(), end.getX(), end.getY() + 1, end.getZ(),
                    0.9F, 0.9F, 0.9F, 1.0F, 0.5F, 0.5F, 0.5F);
            stack.popPose();
        }
    }

    default boolean checkTool(Player player, BlockPos pos) {
        if (player == null)
            return false;

        var mainH = player.getMainHandItem();
        var offH = player.getOffhandItem();
        ItemStack item;

        if (mainH != null && mainH.is(zItems.CONFIGURATOR)) {
            item = mainH;
        } else {
            if (offH != null && offH.is(zItems.CONFIGURATOR)) {
                item = offH;
            } else {
                return false;
            }
        }

        if (!item.is(zItems.CONFIGURATOR))
            return false;

        if (item.get(zComponents.GLOBAL_POS) == null)
            return false;

        if (item.get(zComponents.GLOBAL_POS).dimension() != player.level().dimension())
            return false;

        if (item.get(zComponents.GLOBAL_POS).pos().getX() != pos.getX()
                || item.get(zComponents.GLOBAL_POS).pos().getY() != pos.getY()
                || item.get(zComponents.GLOBAL_POS).pos().getZ() != pos.getZ())
            return false;
        return true;
    }

}
