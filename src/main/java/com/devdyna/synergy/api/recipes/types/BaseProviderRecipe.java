package com.devdyna.synergy.api.recipes.types;

import java.util.List;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.ProviderInput;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public abstract class BaseProviderRecipe<T, RECIPE extends Recipe<ProviderInput>> implements Recipe<ProviderInput> {

    protected final BlockState core;
    protected final BlockState below;
    protected final BlockState left;
    protected final BlockState right;
    protected final T output;

    public BaseProviderRecipe(BlockState core, @Nullable BlockState below, @Nullable BlockState left,
            @Nullable BlockState right, T output) {
        this.core = core;
        this.below = below;
        this.left = left;
        this.right = right;
        this.output = output;
    }

    public boolean matches(ProviderInput r, Level l) {
        return this.core.is(x.block(r.core()));
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public abstract zRecipe<?> getRecipe();

    public RecipeType<RECIPE> getType() {
        return (RecipeType<RECIPE>) getRecipe().getType();
    }

    @Override
    public RecipeSerializer<RECIPE> getSerializer() {
        return (RecipeSerializer<RECIPE>) getRecipe().getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(x.item(core))));
    }

    public BlockState getCore() {
        return core;
    }

    @Nullable
    public BlockState getBelow() {
        return below;
    }

    @Nullable
    public BlockState getLeft() {
        return left;
    }

    @Nullable
    public BlockState getRight() {
        return right;
    }

    public T getOutput() {
        return output;
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
