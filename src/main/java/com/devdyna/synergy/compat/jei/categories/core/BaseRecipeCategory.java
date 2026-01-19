package com.devdyna.synergy.compat.jei.categories.core;

import static com.devdyna.synergy.Main.ID;

import java.awt.Color;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.api.utils.ColorUtil;
import com.devdyna.synergy.api.utils.Image;
import com.devdyna.synergy.api.utils.Size;
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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings({ "unchecked", "null" })
public abstract class BaseRecipeCategory<T extends Recipe<?>> implements IRecipeCategory<RecipeHolder<T>> {

    protected IGuiHelper helper;

    public final Font font = Minecraft.getInstance().font;

    public BaseRecipeCategory(IGuiHelper h) {
        this.helper = h;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<T> recipe, IFocusGroup focuses) {
        setRecipe(builder, recipe.value(), focuses);
    }

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
    public void draw(RecipeHolder<T> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        draw(recipe.value(), recipeSlotsView, guiGraphics, mouseX, mouseY);
    }

    public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        background(guiGraphics);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, RecipeHolder<T> recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {
        getTooltip(tooltip, recipe.value(), recipeSlotsView, mouseX, mouseY);
    }

    public void getTooltip(ITooltipBuilder tooltip, T recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {

    }

}