package com.devdyna.synergy.compat.jei.categories.core;

import com.devdyna.synergy.api.Size;
import com.devdyna.synergy.api.node.BaseProviderRecipe;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.world.level.block.LiquidBlock;

@SuppressWarnings({ "null" })
public abstract class BaseProviderCategory<T extends BaseProviderRecipe<J>, J>
        extends BaseRecipeCategory<BaseProviderRecipe<J>> {

    public BaseProviderCategory(IGuiHelper helper) {
        super(helper);
    }

    protected abstract String getProviderType();

    @Override
    public String getTitleKey() {
        return "provider." + getProviderType();
    }

    @Override
    public Size setXY() {
        return Size.of(80, 100);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/provider.png";
    }

    protected abstract void defineOutput(IRecipeLayoutBuilder builder, BaseProviderRecipe<J> recipe,
            IFocusGroup focuses);

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BaseProviderRecipe<J> recipe, IFocusGroup focuses) {

        defineOutput(builder, recipe, focuses);

        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 33, 45)
                .addIngredients(x.ingredient(zBlocks.FLUID_PROVIDER.get()));

        try {
            var core = recipe.getCore();
            if (core.getBlock() instanceof LiquidBlock fluid)
                builder.addInputSlot(33, 62).addFluidStack(fluid.fluid);
            else
                builder.addInputSlot(33, 62).addItemStack(x.item(core));
        } catch (Exception e) {
        }

        try {
            var below = recipe.getBelow();
            if (!below.isAir()) {
                if (below.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(33, 79).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(33, 79).addItemStack(x.item(below));
            }
        } catch (Exception e) {
        }

        try {
            var right = recipe.getRight();
            if (!right.isAir()) {
                if (right.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(50, 62).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(50, 62).addItemStack(x.item(right));
            }
        } catch (Exception e) {
        }

        try {
            var left = recipe.getLeft();
            if (!left.isAir()) {
                if (left.getBlock() instanceof LiquidBlock fluid)
                    builder.addInputSlot(16, 62).addFluidStack(fluid.fluid);
                else
                    builder.addInputSlot(16, 62).addItemStack(x.item(left));
            }
        } catch (Exception e) {
        }

    }

}
