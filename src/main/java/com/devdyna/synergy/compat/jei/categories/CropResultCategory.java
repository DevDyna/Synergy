package com.devdyna.synergy.compat.jei.categories;

import com.devdyna.synergy.api.utils.Size;
import com.devdyna.synergy.common.recipeTypes.type.CropResultRecipe;
import com.devdyna.synergy.compat.jei.categories.core.BaseRecipeCategory;
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

        builder.addInputSlot(16, 24).addIngredients(recipe.getInput());

        recipe.getOutputs().forEach(s -> {
            var index = recipe.getOutputs().indexOf(s);
            builder.addOutputSlot(65 + (index % 3 * 20), 8 + (index > 2 ? 32 : 0)).addItemStack(s);
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
        return Size.of(128, 64);
    }

    @Override
    public String setBackGround() {
        return "textures/gui/crop_result.png";
    }

}
