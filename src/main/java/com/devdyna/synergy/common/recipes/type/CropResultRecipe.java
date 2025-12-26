package com.devdyna.synergy.common.recipes.type;

import java.util.List;

import com.devdyna.synergy.common.recipes.input.ItemListInput;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class CropResultRecipe implements Recipe<ItemListInput> {

    public static final int INPUT_COUNT = 6;
    public final Ingredient input;
    public final List<ItemStack> outputs;
    // TODO add percentuages as float values

    public CropResultRecipe(Ingredient input,
            List<ItemStack> outputs) {
        this.input = input;
        this.outputs = outputs;
    }

    public boolean matches(ItemListInput r, Level l) {
        return true;
    }

    public ItemStack assemble(ItemListInput i, HolderLookup.Provider r) {
        return this.outputs.get(0);
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<CropResultRecipe> getType() {
        return zRecipeTypes.CROP_RESULT.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Items.MELON_SEEDS);
    }

    @Override
    public RecipeSerializer<CropResultRecipe> getSerializer() {
        return zRecipeTypes.CROP_RESULT.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(input));
    }

    public Ingredient getInput() {
        return input;
    }

    public List<ItemStack> getOutputs() {
        return outputs;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_BUILDING_BLOCKS;
    }
}
