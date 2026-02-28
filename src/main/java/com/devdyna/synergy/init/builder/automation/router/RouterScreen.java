package com.devdyna.synergy.init.builder.automation.router;

import com.devdyna.synergy.api.gui.BaseScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class RouterScreen extends BaseScreen<RouterMenu> {

        public RouterScreen(RouterMenu menu, Inventory playerInventory, Component title) {
                super(menu, playerInventory, title);
        }

        @Override
        protected ResourceLocation background() {
                return x.rl("textures/gui/container/router.png");
        }

        @Override
        protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
                drawCenteredString(guiGraphics, font, Component.literal("North"), 30, 10,
                                defaultToolTipColor.brighter().brighter().getRGB(), true);
                drawCenteredString(guiGraphics, font, Component.literal("South"), 30, 45,
                                defaultToolTipColor.brighter().brighter().getRGB(), true);
                drawCenteredString(guiGraphics, font, Component.literal("East"), 30 + 58, 10,
                                defaultToolTipColor.brighter().brighter().getRGB(), true);
                drawCenteredString(guiGraphics, font, Component.literal("West"), 30 + 58, 45,
                                defaultToolTipColor.brighter().brighter().getRGB(), true);
                drawCenteredString(guiGraphics, font, Component.literal("Up"), 30 + 58 + 58, 10,
                                defaultToolTipColor.brighter().brighter().getRGB(), true);
                drawCenteredString(guiGraphics, font, Component.literal("Down"), 30 + 58 + 58, 45,
                                defaultToolTipColor.brighter().brighter().getRGB(), true);
        }

}
