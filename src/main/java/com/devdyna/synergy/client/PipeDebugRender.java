package com.devdyna.synergy.client;

import com.devdyna.synergy.init.builder._core.pipes.pipeProperties;
import com.devdyna.synergy.init.builder._core.pipes.pipeType;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.ItemTransferBE;
import com.devdyna.synergy.init.types.zBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("null")
public class PipeDebugRender<T extends ItemTransferBE> implements BlockEntityRenderer<T> {

        public PipeDebugRender(BlockEntityRendererProvider.Context context) {
                super();
        }

        @Override
        public void render(T be, float tick, PoseStack stack, MultiBufferSource bufferSource, int packedLight,
                        int packedOverlay) {

                var input = be.getInputPos(be.getBlockState(), be.getLevel(), be.getBlockPos());
                var output = be.getOutputPos(be.getBlockState(), be.getLevel(), be.getBlockPos());

                var x = be.getBlockPos().getX();
                var y = be.getBlockPos().getY();
                var z = be.getBlockPos().getZ();

                if (input != null) {
                        var xi = input.getValue().relative(input.getKey()).getX();
                        var yi = input.getValue().relative(input.getKey()).getY();
                        var zi = input.getValue().relative(input.getKey()).getZ();

                        createPipeRender(xi - x, yi - y, zi - z, stack, be, bufferSource, packedLight, packedOverlay);
                }

                if (output != null) {
                        var xo = output.getValue().relative(output.getKey()).getX();
                        var yo = output.getValue().relative(output.getKey()).getY();
                        var zo = output.getValue().relative(output.getKey()).getZ();
                        createPipeRender(xo - x, yo - y, zo - z, stack, be, bufferSource, packedLight, packedOverlay);

                }

        }

        private void createPipeRender(int x, int y, int z, PoseStack stack, T be, MultiBufferSource bufferSource,
                        int packedLight,
                        int packedOverlay) {
                var render = Minecraft.getInstance().getBlockRenderer();

                stack.pushPose();

                // stack.scale(1.25f, 1.25f, 1.25f);
                stack.translate(x, y, z);

                // pipe render
                var pipe = zBlocks.PIPE.get().defaultBlockState();

                for (EnumProperty<pipeProperties> prop : pipeType.PROPRTIES) {
                        pipe = pipe.setValue(prop, pipeProperties.FALSE);
                }

                var player = be.getLevel().getNearestPlayer(be.getBlockPos().getX(), be.getBlockPos().getY(),
                                be.getBlockPos().getZ(), 8, false);

                if (player != null && player.getMainHandItem().is(Tags.Items.TOOLS_WRENCH))
                        render.renderSingleBlock(pipe, stack, bufferSource, packedLight, packedOverlay,
                                        ModelData.EMPTY,
                                        RenderType.debugQuads());

                stack.popPose();
        }

}
