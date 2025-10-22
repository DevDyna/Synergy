package com.devdyna.synergy.compat.jei.categories.core;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public abstract class BaseRecipeCategory<T> implements IRecipeCategory<T> {
    protected IGuiHelper helper;
    protected final Font font = Minecraft.getInstance().font;

    public BaseRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

}
