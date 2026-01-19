package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.common.recipes.type.VoidBoxInfusionRecipe;
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
public class VoidBoxInfusionCategory extends BaseRecipeCategory<VoidBoxInfusionRecipe> {

    public VoidBoxInfusionCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final RecipeType<RecipeHolder<VoidBoxInfusionRecipe>> TYPE = RecipeType
            .createFromVanilla(zRecipeTypes.VOID_BOX_INFUSION.getType());

    @Override
    public RecipeType<RecipeHolder<VoidBoxInfusionRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTitleKey() {
        return zStatic.Blocks.void_box;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.VOID_BOX.get();
    }

    @Override
    public Size setXY() {
        return Size.of(75, 20);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/simple.png";
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, VoidBoxInfusionRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 2).addItemStack(recipe.getOutput());
    }

}
