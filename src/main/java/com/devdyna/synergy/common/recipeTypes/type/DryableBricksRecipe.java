package com.devdyna.synergy.common.recipeTypes.type;

import java.util.List;

import com.devdyna.synergy.common.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zRecipeTypes;
import com.devdyna.synergy.utils.x;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class DryableBricksRecipe implements Recipe<MonoItemInput> {

    private final ItemStack input;
    private final BlockState block;
    private final ItemStack output;

    public DryableBricksRecipe(ItemStack input, BlockState block, ItemStack output) {
        this.input = input;
        this.block = block;
        this.output = output;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return r.input().is(input.getItem());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return output;
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.DRYABLE_BRICKS.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Items.BRICK);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.DRYABLE_BRICKS.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(input)));
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return output;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public BlockState getBlock() {
        return block;
    }
}
