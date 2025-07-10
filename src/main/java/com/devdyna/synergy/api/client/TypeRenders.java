package com.devdyna.synergy.api.client;

import com.devdyna.synergy.api.components.ModeTypes;
import com.devdyna.synergy.api.pipe.pipeProperties;
import com.devdyna.synergy.api.pipe.pipeType;
import com.devdyna.synergy.init.builder.harvester.HarvesterBE;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.EnumProperty;

@SuppressWarnings("null")
public interface TypeRenders<T> {

    /*
     * Distance of player from BE to render
     */
    default int getPlayerDistance() {
        return 16;
    }

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
                be.getBlockPos().getZ(), getPlayerDistance(), false);

        if (checkTool(ModeTypes.SHOW_TRACK, player, be.getBlockPos()))
            render.renderBatched(pipe, be.getBlockPos(), be.getLevel(), stack, color, false, be.getLevel().getRandom());
        stack.popPose();
    }

    @SuppressWarnings("deprecation")
    default void renderDebugBox(BlockEntity be, BlockPos start, BlockPos end, Direction dir, PoseStack stack,
            MultiBufferSource bufferIn) {

        var player = be.getLevel().getNearestPlayer(be.getBlockPos().getX(),
                be.getBlockPos().getY(),
                be.getBlockPos().getZ(), getPlayerDistance(), false);

        VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.lines());

        switch (dir) {
            case Direction.NORTH:
                start = HarvesterBE.move(start, Direction.SOUTH, 1);
                start = HarvesterBE.move(start, Direction.EAST, 1);
                break;
            case Direction.SOUTH:
                start = HarvesterBE.move(start, Direction.EAST, 1);
                end = HarvesterBE.move(end, Direction.SOUTH, 1);
                break;
            case Direction.EAST:
                end = HarvesterBE.move(end, Direction.SOUTH, 1);
                end = HarvesterBE.move(end, Direction.EAST, 1);
                break;
            case Direction.WEST:
                start = HarvesterBE.move(start, Direction.EAST, 1);
                end= HarvesterBE.move(end, Direction.SOUTH, 1);
                break;
            default:
                break;
        }

        if (checkTool(ModeTypes.SHOW_AOE, player, be.getBlockPos())) {
            stack.pushPose();
            LevelRenderer.renderLineBox(stack, vertexconsumer,
                    start.getX(), start.getY(), start.getZ(), end.getX(), end.getY() + 1, end.getZ(),
                    0.9F, 0.9F, 0.9F, 1.0F, 0.5F, 0.5F, 0.5F);
            stack.popPose();
        }
    }

    default boolean checkTool(String mode, Player player, BlockPos pos) {
        if (player == null)
            return false;

        var item = player.getMainHandItem();

        if (!item.is(zItems.CONFIGURATOR))
            return false;

        if (item.get(zComponents.MODE) == null || item.get(zComponents.BLOCKPOS) == null)
            return false;

        if (item.get(zComponents.MODE) != mode)
            return false;

        if (item.get(zComponents.BLOCKPOS).getX() != pos.getX()
                || item.get(zComponents.BLOCKPOS).getY() != pos.getY()
                || item.get(zComponents.BLOCKPOS).getZ() != pos.getZ())
            return false;

        return true;

    }

}
