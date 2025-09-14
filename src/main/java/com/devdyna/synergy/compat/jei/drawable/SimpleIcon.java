package com.devdyna.synergy.compat.jei.drawable;

import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;

@SuppressWarnings("null")
public class SimpleIcon implements IDrawable {
    private final IDrawable items;

    public SimpleIcon(IDrawable items) {
        this.items = items;
    }

    public static SimpleIcon of(IGuiHelper guiHelper, Item item) {
        return new SimpleIcon(guiHelper.createDrawableItemStack(x.item(item)));
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        items.draw(guiGraphics, xOffset, yOffset);
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

}
