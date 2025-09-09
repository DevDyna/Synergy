package com.devdyna.synergy.init.recipeTypes.type;

import com.devdyna.synergy.init.recipeTypes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class UrnRitualRecipe implements Recipe<MonoItemInput> {

    protected final ItemStack input;
    protected final ItemStack output;

    public UrnRitualRecipe(ItemStack input,
            ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public boolean matches(MonoItemInput i, Level l) {
        return this.input.is(i.input().getItem());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public ItemStack getInput() {
        return this.input;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.URN_RITUAL_RECIPE.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(zBlocks.URN.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.URN_RITUAL_RECIPE.getSerializer();
    }

    // NoOp
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(Ingredient.of(this.input));
        return list;
    }

    public ItemStack getResultItem(HolderLookup.Provider r) {
        return ItemStack.EMPTY;
    }
}
