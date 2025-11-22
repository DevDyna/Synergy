package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.api.Size;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
import com.devdyna.synergy.init.recipeTypes.type.CropResultRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("null")
public class CropResultCategory extends BaseRecipeCategory<CropResultRecipe> {

    public static final RecipeType<CropResultRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.CROP_RESULT.getId()),
            CropResultRecipe.class);

    public CropResultCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<CropResultRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CropResultRecipe recipe, IFocusGroup focuses) {

        builder.addInputSlot(12, 17).addIngredients(recipe.getInput());

        recipe.getOutputs().forEach(s -> {
            var index = recipe.getOutputs().indexOf(s);
            builder.addOutputSlot(53 + (index % 3 * 22), 6 + (index > 2 ? 22 : 0)).addItemStack(s);
        });

    }

    @Override
    public String getTitleKey() {
        return "crop";
    }

    @Override
    public ItemLike getIconItem() {
        return Items.WHEAT_SEEDS;
    }

    @Override
    public Size setXY() {
        return Size.of(144, 55);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/jei/crop_result.png";
    }

}
