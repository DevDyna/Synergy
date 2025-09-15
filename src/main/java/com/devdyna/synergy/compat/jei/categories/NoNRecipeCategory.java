package com.devdyna.synergy.compat.jei.categories;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;

/**
 * TODO maybe i will replace directly
 */
@SuppressWarnings("null")
public abstract class NoNRecipeCategory<T> extends AbstractRecipeCategory<T> {

    public NoNRecipeCategory(IGuiHelper guiHelper,
            RecipeType<T> type, Component title, IDrawable icon, int xz, int y) {
        super(type, title, icon,
                xz, y);
    }

}