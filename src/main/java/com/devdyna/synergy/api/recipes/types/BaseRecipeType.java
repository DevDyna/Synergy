package com.devdyna.synergy.api.recipes.types;

import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings("null")
public abstract class BaseRecipeType<RECIPE_INPUT extends RecipeInput>
        implements Recipe<RECIPE_INPUT> {

    @Override
    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    @Override
    @Deprecated
    public abstract ItemStack getResultItem(Provider provider);

    @Override
    public RecipeSerializer<? extends BaseRecipeType<RECIPE_INPUT>> getSerializer() {
        return getRecipe().getSerializer();
    }

    @Override
    public RecipeType<? extends BaseRecipeType<RECIPE_INPUT>> getType() {
        return getRecipe().getType();
    }

    public abstract zRecipe<? extends BaseRecipeType<RECIPE_INPUT>> getRecipe();

    public abstract Item getToastIcon();

    public ItemStack getToastSymbol() {
        return x.item(getToastIcon());
    }

}
