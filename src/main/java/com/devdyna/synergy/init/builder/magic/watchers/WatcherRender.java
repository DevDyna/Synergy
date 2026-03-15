package com.devdyna.synergy.init.builder.magic.watchers;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.blockfactories.watchers.BaseWatcherBE;
import com.devdyna.synergy.api.render.ModelRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("null")
public class WatcherRender<T extends BaseWatcherBE> implements BlockEntityRenderer<T> {

    private BlockRenderDispatcher brd;

    public WatcherRender(Context c) {
        super();
        this.brd = c.getBlockRenderDispatcher();
    }

    @Override
    public void render(T be, float partialTicks, PoseStack poseStack,
            MultiBufferSource buffer, int light, int overlay) {

        var level = be.getLevel();

        if (level == null)
            return;

        var entity = be.getEntity();

        float yaw = ((level.getGameTime() % 360) + partialTicks) * 4f;
        float pitch = 0;

        if (entity != null) {
            Vec3 entityPos = entity.getEyePosition(partialTicks);
            BlockPos pos = be.getBlockPos();

            double dx = entityPos.x - (pos.getX() + 0.5);
            double dy = entityPos.y - (pos.getY() + 0.5);
            double dz = entityPos.z - (pos.getZ() + 0.5);

            yaw = -((float) (Math.atan2(dz, dx) * (180F / Math.PI)) - 90F) + 180f;
            pitch = (float) (Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180F / Math.PI));
        }
        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);

        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));

        if (entity != null)
            poseStack.mulPose(Axis.XP.rotationDegrees(pitch));

        poseStack.translate(-0.5, -0.5, -0.5);

        ModelRenderHelper.of()
                .noPop()
                .noPush()
                .pivot(0.5, 0.5, 0.5)
                .model(zStatic.AdditionalModel.WATCHER)//TODO
                .build(Minecraft.getInstance().getModelManager(), brd, poseStack, light, overlay, buffer);

        poseStack.popPose();
    }

}
