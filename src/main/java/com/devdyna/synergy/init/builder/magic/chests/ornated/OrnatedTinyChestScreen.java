package com.devdyna.synergy.init.builder.magic.chests.ornated;

import com.devdyna.synergy.api.gui.BaseTinyChestScreen;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("null")
public class OrnatedTinyChestScreen extends BaseTinyChestScreen<OrnatedTinyChestMenu> {

    public OrnatedTinyChestScreen(OrnatedTinyChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 220;
    }

    @Override
    protected Identifier background() {
        return x.rl("minecraft", "textures/gui/container/generic_54.png");
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {

        int xPos = (this.width - this.imageWidth) / 2;
        int yPos = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(
                background(),
                xPos, yPos,
                0, 0,
                this.imageWidth,
                this.imageHeight,
                0,
                0);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY,
                defaultToolTipColor.getRGB(), false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY + 56,
                defaultToolTipColor.getRGB(), false);
    }
}
