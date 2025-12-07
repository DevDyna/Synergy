package com.devdyna.synergy.init.machine.core.recipe;

import java.util.List;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.init.machine.core.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public abstract class BaseMachineRecipeType<T extends RecipeInput> implements Recipe<T> {

    public int ticks;
    public int energy;

    public Ingredient input;

    public ItemStack output;

    public ItemStack secondary;

    public float chance;

    public int getEnergy() {
        return energy;
    }

    public int getTime() {
        return ticks;
    }

    public Ingredient getInputItem() {
        return input;
    }

    public ItemStack getOutputItem() {
        return output;
    }

    public ItemStack getSecondaryOutputItem() {
        return secondary;
    }

    /**
     * 0.00 -> 1.00
     */
    public float getSecondaryItemChance() {
        return chance;
    }

    public boolean hasSecondaryItem() {
        return false;
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(getInputItem()));
    }

    public abstract ItemStack getRecipeInput(T recipe);

    public boolean matches(T r, Level l) {
        return getInputItem().test(getRecipeInput(r));
    }

    public ItemStack assemble(T i, HolderLookup.Provider r) {
        return getOutputItem().copy();
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider a) {
        return getOutputItem();
    }

    public abstract MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<T>> getMachine();

    public RecipeType<?> getType() {
        return getMachine().recipe().getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(getMachine().block().get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return getMachine().recipe().getSerializer();
    }

}
