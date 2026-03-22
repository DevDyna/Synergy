package com.devdyna.synergy.compat.jei.categories.core;

import com.devdyna.synergy.api.recipes.types.BaseProviderRecipe;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings({ "null" })
public abstract class BaseProviderCategory<T extends BaseProviderRecipe<J>, J>
        extends BaseCategory<T> {

    public BaseProviderCategory(IGuiHelper h) {
        super(h);
        this.helper = h;
    }

    protected abstract String getProviderType();

    public String getTitleKey() {
        return "provider." + getProviderType();
    }

    public Size setXY() {
        return Size.of(52, 88);
    }

    public String setBackGround() {
        return "textures/gui/jei/provider.png";
    }

    protected abstract void defineOutput(IRecipeLayoutBuilder builder, BaseProviderRecipe<J> recipe,
            IFocusGroup focuses);

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {

        defineOutput(builder, recipe, focuses);

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 33 - 15, 45 - 8)
                .addIngredients(x.ingredient(zBlocks.FLUID_PROVIDER.get()));

        try {
            var core = recipe.getPattern().core();
            if (core.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(33 - 15, 62 - 8).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(33 - 15, 62 - 8).addItemStack(x.item(core));
        } catch (Exception e) {
        }

        try {
            var below = recipe.getPattern().below();
            if (!below.isAir() && below != null) {
                if (below.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(33 - 15, 79 - 8).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(33 - 15, 79 - 8).addItemStack(x.item(below));
            }
        } catch (Exception e) {
        }

        try {
            var right = recipe.getPattern().right();
            if (!right.isAir() && right != null) {
                if (right.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(50 - 15, 62 - 8).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(50 - 15, 62 - 8).addItemStack(x.item(right));
            }
        } catch (Exception e) {
        }

        try {
            var left = recipe.getPattern().left();
            if (!left.isAir() && left != null) {
                if (left.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(16 - 15, 62 - 8).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(16 - 15, 62 - 8).addItemStack(x.item(left));
            }
        } catch (Exception e) {
        }

    }

}
