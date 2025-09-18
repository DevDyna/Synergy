package com.devdyna.synergy.compat.jei.categories;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.compat.jei.drawable.SimpleIcon;
import com.devdyna.synergy.init.recipeTypes.type.CropResultRecipe;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

@SuppressWarnings("null")
public class CropResultCategory implements IRecipeCategory<CropResultRecipe> {

    private IGuiHelper helper;

    public static final RecipeType<CropResultRecipe> TYPE = new RecipeType<>(
            x.rl(zRecipeTypes.CROP_RESULT.getId()),
            CropResultRecipe.class);

    public CropResultCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<CropResultRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Main.ID + ".jei.crop");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return SimpleIcon.of(helper, Items.WHEAT_SEEDS);
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.CROP_RESULT, 0, 0, 144, 55);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CropResultRecipe recipe, IFocusGroup focuses) {

        builder.addInputSlot(12, 17).addItemStack(recipe.getInput());

        recipe.getOutputs().forEach(s -> {
            var index = recipe.getOutputs().indexOf(s);
            builder.addOutputSlot(53 + (index % 3 * 22), 6 + (index > 2 ? 22 : 0)).addIngredients(s);
        });

    }

}
