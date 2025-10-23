package com.devdyna.synergy.compat.jei.categories.core;

import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public abstract class BaseRecipeCategory {
    protected IGuiHelper helper;
    protected final Font font = Minecraft.getInstance().font;

    public BaseRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
    }

}
