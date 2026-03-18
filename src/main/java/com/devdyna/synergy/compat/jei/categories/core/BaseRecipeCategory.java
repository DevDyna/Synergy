package com.devdyna.synergy.compat.jei.categories.core;

import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.TimeUtil;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

@SuppressWarnings({ "unchecked", "null" })
public abstract class BaseRecipeCategory<T extends Recipe<?>> extends BaseCategory<RecipeHolder<T>> {

    public BaseRecipeCategory(IGuiHelper h) {
        super(h);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<T> recipe, IFocusGroup focuses) {
        setRecipe(builder, recipe.value(), focuses);
    }

    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
    }

    @Override
    public void draw(RecipeHolder<T> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        if (enableTimerRender())
            renderTickDelay(recipe.value(), guiGraphics);

        draw(recipe.value(), recipeSlotsView, guiGraphics, mouseX, mouseY);

    }

    public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, RecipeHolder<T> recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {
        getTooltip(tooltip, recipe.value(), recipeSlotsView, mouseX, mouseY);
        super.getTooltip(tooltip, recipe, recipeSlotsView, mouseX, mouseY);
    }

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
                Component.literal(TimeUtil.getTimeValue(tickValue(recipe), shortTicks())),
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