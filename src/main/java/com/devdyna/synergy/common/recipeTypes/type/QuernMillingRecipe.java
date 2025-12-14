package com.devdyna.synergy.common.recipeTypes.type;

import java.util.List;

import com.devdyna.synergy.common.recipeTypes.input.MonoItemInput;
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
public class QuernMillingRecipe implements Recipe<MonoItemInput> {

    // public static final int INPUT_COUNT = 1;
    private final Ingredient input;
    private final int time;
    private final ItemStack output;

    public QuernMillingRecipe(Ingredient input,
            ItemStack output, int time) {
        this.input = input;
        this.time = time;
        this.output = output;
    }

    public static QuernMillingRecipe of(Ingredient input, ItemStack output, int time) {
        return new QuernMillingRecipe(input, output, time);
    }

    public boolean matches(MonoItemInput r, Level l) {
        return this.input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.QUERN_MILLING.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(zBlocks.QUERN.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.QUERN_MILLING.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTime() {
        return time;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }
}
