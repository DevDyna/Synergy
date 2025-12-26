package com.devdyna.synergy.common.recipes.type;

import java.util.List;

import com.devdyna.synergy.common.recipes.input.MonoItemInput;
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
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class DryableBricksRecipe implements Recipe<MonoItemInput> {

    private final Ingredient input;
    private final BlockState block;
    private final ItemStack output;

    public DryableBricksRecipe(Ingredient input, BlockState block, ItemStack output) {
        this.input = input;
        this.block = block;
        this.output = output;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return output;
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<DryableBricksRecipe> getType() {
        return zRecipeTypes.DRYABLE_BRICKS.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Items.BRICK);
    }

    @Override
    public RecipeSerializer<DryableBricksRecipe> getSerializer() {
        return zRecipeTypes.DRYABLE_BRICKS.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public BlockState getBlock() {
        return block;
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
