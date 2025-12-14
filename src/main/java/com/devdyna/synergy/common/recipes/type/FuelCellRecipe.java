package com.devdyna.synergy.common.recipes.type;

import com.devdyna.synergy.common.recipes.input.MonoItemInput;
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
public class FuelCellRecipe implements Recipe<MonoItemInput> {

    protected final Ingredient input;
    protected final ItemStack output;
    protected final int duration;
    protected final double heat;
    protected final int fe;

    public FuelCellRecipe(Ingredient input,
            ItemStack output, int duration, int fe, double heat) {
        this.input = input;
        this.output = output;
        this.duration = (duration < 1 ? 1 : duration);
        this.heat = heat;
        this.fe = fe;
    }

    public boolean matches(MonoItemInput i, Level l) {
        return this.input.test(i.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.input);
        return list;
    }

    public ItemStack getResultItem(HolderLookup.Provider r) {
        return this.output;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public ItemStack getOutput(){
        return this.output;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getFe() {
        return fe;
    }

    public double getHeat() {
        return heat;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.FUEL_CELL_RECIPE.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(zBlocks.REACTOR_FUEL_CELL.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.FUEL_CELL_RECIPE.getSerializer();
    }

}
