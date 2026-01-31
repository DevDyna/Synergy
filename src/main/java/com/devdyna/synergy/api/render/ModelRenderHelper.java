package com.devdyna.synergy.api.render;

import org.joml.Quaternionf;

import com.devdyna.synergy.api.utils.x;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.data.ModelData;

public class ModelRenderHelper {

    private Quaternionf angle = null;
    private double translateX = 0;
    private double translateY = 0;
    private double translateZ = 0;
    private float scaleX = 1;
    private float scaleY = 1;
    private float scaleZ = 1;

    private ResourceLocation rl = x.mcLoc("block/stone");
    private boolean noPush = false;
    private boolean noPop = false;

    private double px = 0;
    private double py = 0;
    private double pz = 0;

    private ModelRenderHelper() {

    }

    public static ModelRenderHelper of() {
        return new ModelRenderHelper();
    }

    public ModelRenderHelper model(ResourceLocation rl) {
        this.rl = rl;
        return this;
    }

    public ModelRenderHelper model(String rl) {
        return model(x.rl(rl));
    }

    public ModelRenderHelper model(String id, String rl) {
        return model(x.rl(id, rl));
    }

    public ModelRenderHelper rotate(Quaternionf a) {
        this.angle = this.angle == null ? new Quaternionf(a) : new Quaternionf(this.angle).mul(a);
        return this;
    }

    public ModelRenderHelper rotateXP(float a) {
        return rotate(Axis.XP.rotationDegrees(a));
    }

    public ModelRenderHelper rotateXN(float a) {
        return rotate(Axis.XN.rotationDegrees(a));
    }

    public ModelRenderHelper rotateYP(float a) {
        return rotate(Axis.YP.rotationDegrees(a));
    }

    public ModelRenderHelper rotateYN(float a) {
        return rotate(Axis.YN.rotationDegrees(a));
    }

    public ModelRenderHelper rotateZP(float a) {
        return rotate(Axis.ZP.rotationDegrees(a));
    }

    public ModelRenderHelper rotateZN(float a) {
        return rotate(Axis.ZN.rotationDegrees(a));
    }

    public ModelRenderHelper move(double x, double y, double z) {
        this.translateX = x;
        this.translateY = y;
        this.translateZ = z;
        return this;
    }

    public ModelRenderHelper scale(float x, float y, float z) {
        this.scaleX = x;
        this.scaleY = y;
        this.scaleZ = z;
        return this;
    }

    public ModelRenderHelper noPush() {
        this.noPush = true;
        return this;
    }

    public ModelRenderHelper noPop() {
        this.noPop = true;
        return this;
    }

    public ModelRenderHelper pivot(double px, double py, double pz) {
        this.px = px;
        this.py = py;
        this.pz = pz;
        return this;
    }

    public void build(PoseStack p, int light, int overlay,
            MultiBufferSource buffer) {
        var mc = Minecraft.getInstance();
        build(mc.getModelManager(), mc.getBlockRenderer(), p, light, overlay, buffer);
    }

    public void build(ModelManager m, BlockRenderDispatcher r, PoseStack p, int light, int overlay,
            MultiBufferSource buffer) {

        if (!noPush)
            p.pushPose();

        p.translate(translateX, translateY, translateZ);

        if (px != 0 || py != 0 || pz != 0) {
            p.translate(px, py, pz);
        }

        if (angle != null)
            p.mulPose(angle);

        if (px != 0 || py != 0 || pz != 0) {
            p.translate(-px, -py, -pz);
        }

        p.scale(scaleX, scaleY, scaleZ);

        r.getModelRenderer().renderModel(
                p.last(),
                buffer.getBuffer(RenderType.solid()),
                null,
                m.getModel(ModelResourceLocation.standalone(rl)),
                0.0f, 0.0f, 0.0f,
                light,
                OverlayTexture.pack(light, overlay),
                ModelData.EMPTY,
                RenderType.solid());

        if (!noPop)
            p.popPose();
    }

}
