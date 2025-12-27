package com.devdyna.synergy.api.render;

import org.joml.Quaternionf;

import com.devdyna.synergy.api.utils.x;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;

public class SimpleItemRender {

    private boolean whenOn = true;

    private Quaternionf angle = Axis.XP.rotationDegrees(0);
    private double translateX = 0.5;
    private double translateY = 0.5;
    private double translateZ = 0.5;
    private float scaleX = 1;
    private float scaleY = 1;
    private float scaleZ = 1;

    private Item item = Items.STONE;

    private SimpleItemRender() {

    }

    public static SimpleItemRender of() {
        return new SimpleItemRender();
    }

    public SimpleItemRender whenOn(boolean whenOn) {
        this.whenOn = whenOn;
        return this;
    }

    public SimpleItemRender rotate(Quaternionf angle) {
        this.angle = angle;
        return this;
    }

    public SimpleItemRender rotateXP(int angle) {
        return rotate(Axis.XP.rotationDegrees(angle));
    }

    public SimpleItemRender rotateXN(int angle) {
        return rotate(Axis.XN.rotationDegrees(angle));
    }

    public SimpleItemRender rotateYP(int angle) {
        return rotate(Axis.YP.rotationDegrees(angle));
    }

    public SimpleItemRender rotateYN(int angle) {
        return rotate(Axis.YN.rotationDegrees(angle));
    }

    public SimpleItemRender rotateZP(int angle) {
        return rotate(Axis.ZP.rotationDegrees(angle));
    }

    public SimpleItemRender rotateZN(int angle) {
        return rotate(Axis.ZN.rotationDegrees(angle));
    }

    public SimpleItemRender move(double x, double y, double z) {
        this.translateX = x;
        this.translateY = y;
        this.translateZ = z;
        return this;
    }

    public SimpleItemRender scale(float x, float y, float z) {
        this.scaleX = x;
        this.scaleY = y;
        this.scaleZ = z;
        return this;
    }

    public SimpleItemRender item(ItemStack i) {
        return item(i.getItem());
    }

    public SimpleItemRender item(Item i) {
        this.item = i;
        return this;
    }

    public SimpleItemRender item(DeferredHolder<Item, Item> i) {
        return item(i.get());
    }

    public void build(ItemRenderer r, PoseStack p, int light, int overlay, MultiBufferSource buffer, Level level) {

        if (whenOn) {
            p.pushPose();
            p.translate(translateX, translateY, translateZ);
            p.mulPose(angle);
            p.scale(scaleX, scaleY, scaleZ);
            r.renderStatic(x.item(item),
                    ItemDisplayContext.GROUND, light, overlay, p, buffer, level,
                    level.random.nextInt());
            p.popPose();
        }
    }

}
