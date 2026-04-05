package com.devdyna.synergy.api.render.helpers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;

public class TextureRenderUtil {

        /**
         * @param light -> check net.minecraft.client.renderer.LightTexture
         */
        public static void renderDirectionalFace(Direction face, PoseStack.Pose pose, VertexConsumer consumer,
                        TextureAtlasSprite texture, float x, float y, float z, float w, float h, int color, int light) {
                switch (face) {
                        case DOWN -> renderFace(pose, consumer, texture, color, light,
                                        x, x + w,
                                        1.0f - z, 1.0f - z,
                                        y, y,
                                        y + h, y + h,
                                        x, x + w,
                                        y, y + h,
                                        0, -1, 0);
                        case UP -> renderFace(pose, consumer, texture, color, light,
                                        x, x + w,
                                        z, z,
                                        y + h, y + h,
                                        y, y,
                                        x, x + w, y,
                                        y + h,
                                        0, 1, 0);
                        case NORTH -> renderFace(pose, consumer, texture, color, light,
                                        x, x + w,
                                        y + h, y,
                                        z, z,
                                        z, z,
                                        x, x + w, y,
                                        y + h,
                                        0, 0, -1);
                        case SOUTH -> renderFace(pose, consumer, texture, color, light,
                                        x, x + w,
                                        y, y + h,
                                        1.0f - z, 1.0f - z,
                                        1.0f - z, 1.0f - z,
                                        x + w, x,
                                        y + h, y,
                                        0, 0, 1);
                        case EAST -> renderFace(pose, consumer, texture, color, light,
                                        1.0f - z, 1.0f - z, y + h, y, x,
                                        x + w, x + w, x,
                                        x, x + w,
                                        y, y + h,
                                        1, 0, 0);
                        case WEST -> renderFace(pose, consumer, texture, color, light,
                                        z, z,
                                        y, y + h,
                                        x, x + w,
                                        x + w, x,
                                        x + w, x,
                                        y + h, y,
                                        -1, 0, 0);
                }
        }

        public static void renderFace(PoseStack.Pose pose, VertexConsumer consumer, TextureAtlasSprite texture,
                        int color, int light,
                        float x0, float x1,
                        float y0, float y1,
                        float z0, float z1,
                        float z2, float z3,
                        float u0, float u1,
                        float v0, float v1,
                        float normalX, float normalY, float normalZ) {

                consumer.addVertex(pose, x0, y0, z0)
                                .setColor(color)
                                .setUv(texture.getU(u0 * texture.contents().width() / 16f),
                                                texture.getV(v0 * texture.contents().height() / 16f))
                                .setOverlay(OverlayTexture.NO_OVERLAY)
                                .setLight(light)
                                .setNormal(pose, normalX, normalY, normalZ);
                consumer.addVertex(pose, x1, y0, z1)
                                .setColor(color)
                                .setUv(texture.getU(u1 * texture.contents().width() / 16f),
                                                texture.getV(v0 * texture.contents().height() / 16f))
                                .setOverlay(OverlayTexture.NO_OVERLAY)
                                .setLight(light)
                                .setNormal(pose, normalX, normalY, normalZ);
                consumer.addVertex(pose, x1, y1, z2)
                                .setColor(color)
                                .setUv(texture.getU(u1 * texture.contents().width() / 16f),
                                                texture.getV(v1 * texture.contents().height() / 16f))
                                .setOverlay(OverlayTexture.NO_OVERLAY)
                                .setLight(light)
                                .setNormal(pose, normalX, normalY, normalZ);
                consumer.addVertex(pose, x0, y1, z3)
                                .setColor(color)
                                .setUv(texture.getU(u0 * texture.contents().width() / 16f),
                                                texture.getV(v1 * texture.contents().height() / 16f))
                                .setOverlay(OverlayTexture.NO_OVERLAY)
                                .setLight(light)
                                .setNormal(pose, normalX, normalY, normalZ);
        }

}