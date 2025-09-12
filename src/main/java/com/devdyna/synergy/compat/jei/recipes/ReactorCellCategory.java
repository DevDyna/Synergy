package com.devdyna.synergy.compat.jei.recipes;

import static com.devdyna.synergy.Main.ID;

import org.jetbrains.annotations.Nullable;

import com.devdyna.synergy.client.gui.screenLocations;
import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;
import com.devdyna.synergy.init.types.zItems;
import com.devdyna.synergy.utils.x;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("null")
public class ReactorCellCategory implements IRecipeCategory<FuelCellRecipe> {

    private final IGuiHelper helper;
    public static final RecipeType<FuelCellRecipe> TYPE = new RecipeType<>(x.rl(ID, "reactor_reaction"), FuelCellRecipe.class);

    public ReactorCellCategory(IGuiHelper helper) {
        this.helper = helper;
    }

    @Override
    public RecipeType<FuelCellRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("reactor_reaction");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(zItems.URANIUM));
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return helper.createDrawable(screenLocations.GUI_DOUBLE_WITH_SMART_ARROW, 0, 0, 176, 85);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FuelCellRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addItemStack(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getOutput());
    }

}
