package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.common.recipes.type.CrushingTubRecipe;
import com.devdyna.synergy.compat.jei.api.JEIFluidTankHelper;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class CrushingTubCategory extends BaseRecipeCategory<CrushingTubRecipe> {

    public CrushingTubCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<CrushingTubRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.CRUSHING_TUB.getType());

    @Override
    public RecipeType<RecipeHolder<CrushingTubRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.crushing_tub;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.CRUSHING_TUB.get();
    }

    @Override
    public Size setXY() {
        return Size.of(108, 72);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/crushing_tub.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrushingTubRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 21, 29).addIngredients(recipe.getInput());

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(71, 39)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addOutputSlot(x, y));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 70+1, 46+1).addItemStack(recipe.getOutput());
    }

}
