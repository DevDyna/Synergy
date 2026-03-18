package com.devdyna.synergy.compat.jei.categories.core;

import static com.devdyna.synergy.Main.ID;

import java.awt.Color;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.api.utils.ColorUtil;
import com.devdyna.synergy.api.utils.Image;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.TimeUtil;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings({ "unchecked", "null" })
public abstract class BaseCategory<T> implements IRecipeCategory<T> {

    protected IGuiHelper helper;

    public final Font font = Minecraft.getInstance().font;

    public BaseCategory(IGuiHelper h) {
        this.helper = h;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
    }

    protected final Color defaultToolTipColor = ColorUtil.color(64, 64, 64);

    public abstract String getTitleKey();

    public abstract ItemLike getIconItem();

    /**
     * Set Size of all category
     * <br/>
     * <br/>
     * If the background image doesn't fit , you need to override
     * <code>background(GuiGraphics)</code>
     */
    public abstract Size setXY();

    public abstract String setBackGround();

    @Override
    public Component getTitle() {
        return Component.translatable(ID + ".jei." + getTitleKey());
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableItemLike(getIconItem());
    }

    @Override
    public int getWidth() {
        return setXY().getX();
    }

    @Override
    public int getHeight() {
        return setXY().getY();
    }

    public void background(GuiGraphics graphics) {
        Image.of()
                .rl(this.setBackGround())
                .size(this.getWidth(), this.getHeight())
                .render(helper, graphics);
    }

    @Override
    public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        background(guiGraphics);
        if (enableTimerRender())
            renderTickDelay(recipe, guiGraphics);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, T recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {
    }

    /**
     * Default : false
     */
    public boolean enableTimerRender() {
        return false;
    }

    /**
     * Default : true
     */
    public boolean shortTicks() {
        return true;
    }

    /**
     * This method is already used by default!
     */
    public void renderTickDelay(T recipe, GuiGraphics guiGraphics) {
        guiGraphics.drawString(font,
                Component.literal(TimeUtil.getTimeValue(tickValue(recipe),shortTicks())),
                tickPos().getX(), tickPos().getY(), tickColor());
    }

    /**
     * Default : 0
     */
    public int tickValue(T recipe) {
        return 0;
    }

    /**
     * Default : 21 | 14
     */
    public Size tickPos() {
        return Size.of(21, 14);
    }

    /**
     * Default : 0xA0A0A0
     */
    public int tickColor() {
        return 0xA0A0A0;
    }

}