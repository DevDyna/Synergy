package com.devdyna.synergy.init.builder.survival.faucet;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

@SuppressWarnings("null")
public class FaucetRender<T extends FaucetBE> implements BlockEntityRenderer<T> {

    public FaucetRender(Context ctx) {
    }

    @Override
    public void render(
            T be,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int light,
            int overlay) {

        var level = be.getLevel();

        if (level == null)
            return;

        if (!be.getBlockState().getValue(BlockStateProperties.ENABLED))
            return;

        Direction dir = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

        IFluidHandler handler = Capabilities.FluidHandler.BLOCK.getCapability(
                level,
                be.getBlockPos().relative(dir),
                level.getBlockState(be.getBlockPos().relative(dir)),
                level.getBlockEntity(be.getBlockPos().relative(dir)),
                dir.getOpposite());

        if (handler == null)
            return;

        var fluid = handler.getFluidInTank(0);
        if (fluid.isEmpty())
            return;

        // TODO WIP
    }
}
