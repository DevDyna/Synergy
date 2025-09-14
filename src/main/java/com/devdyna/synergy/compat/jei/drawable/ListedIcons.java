package com.devdyna.synergy.compat.jei.drawable;

import java.util.Arrays;
import java.util.List;

import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * 
 * Doesn't work inside getIcon()
 *
 */
@SuppressWarnings("null")
public class ListedIcons implements IDrawable {
    private Item[] items;
    private List<IDrawable> icons;
    private ITickTimer timer;
    private int delay;
    private boolean reverse;

    public ListedIcons() {
        this.items = new Item[] { Items.STONE, Items.COBBLESTONE }; // dummy items
        this.delay = 40;// dummy delay
        this.reverse = false;
    }

    public static ListedIcons of() {
        return new ListedIcons();
    }

    /**
     * Require to be used BEFORE <code>setup(IGuiHelper)</code>
     */
    public ListedIcons setIcons(Item... items) {
        this.items = items;
        return this;
    }

    /**
     * Require to be used BEFORE <code>setup(IGuiHelper)</code>
     * 
     * @param delay ticks delay between a new selection
     */
    public ListedIcons setDelay(int ticks) {
        this.delay = ticks;
        return this;
    }

    /**
     * Require to be used BEFORE <code>setup(IGuiHelper)</code> <br/>
     * <br/>
     * Reverse list order
     */
    public ListedIcons setReverse() {
        this.reverse = true;
        return this;
    }

    public ListedIcons setup(IGuiHelper guiHelper) {
        this.icons = Arrays.stream(this.items).map(guiHelper::createDrawableItemLike).toList();
        this.timer = guiHelper.createTickTimer(this.delay, this.icons.size() - 1, this.reverse);
        return this;
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        this.icons.get(this.timer.getValue()).draw(guiGraphics, xOffset, yOffset);
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
