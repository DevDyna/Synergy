package com.devdyna.synergy.api.machine.recipe;

import java.util.ArrayList;
import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings("null")
public abstract class BaseMachineRecipeType<T extends RecipeInput> implements Recipe<T> {

    public int ticks;
    public int energy;

    public Ingredient input;

    public ItemStack output;

    public ItemStack secondary;

    public Ingredient catalyst;

    public float chance;

    public boolean consumeCatalyst;

    public SizedFluidIngredient fluid_input;

    public FluidStack fluid_output;

    public boolean consumeCatalyst() {
        return consumeCatalyst;
    }

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

    public ItemStack getSecondaryItem() {
        return secondary;
    }

    public Ingredient getCatalystItem() {
        return catalyst;
    }

    public FluidStack getFluidOutput() {
        return fluid_output;
    }

    public SizedFluidIngredient getFluidInput() {
        return fluid_input;
    }

    /**
     * 0.00 -> 1.00
     */
    public float getSecondaryItemChance() {
        return chance;
    }

    public boolean hasCatalyst() {
        return false;
    }

    public boolean hasSecondaryOutput() {
        return false;
    }

    public NonNullList<Ingredient> getIngredients() {
        var list = new ArrayList<Ingredient>();
        list.add(getInputItem());
        if (hasCatalyst() && getCatalystItem() != null)
            list.add(getCatalystItem());
        return NonNullList.copyOf(list);
    }

    public abstract ItemStack getRecipeInput(T recipe);

    public ItemStack getRecipeInput2(T recipe) {
        return null;
    };

    public SizedFluidIngredient getRecipeFluidInput(T recipe) {
        return null;
    };

    public boolean matches(T r, Level l) {

        if (getInputItem().isEmpty() || getInputItem() == null) {
            if (getFluidInput() != null && !x.getFluids(getFluidInput()).isEmpty())
                return x.getFluids(getRecipeFluidInput(r)).stream().anyMatch(i -> getFluidInput().test(i));
        } else {
            if (getFluidInput() != null && !x.getFluids(getFluidInput()).isEmpty())
                return x.getFluids(getRecipeFluidInput(r)).stream().anyMatch(i -> getFluidInput().test(i))
                        && getInputItem().test(getRecipeInput(r));
        }

        var check = getInputItem().test(getRecipeInput(r));
        if (hasCatalyst() && getCatalystItem() != null && getRecipeInput2(r) != null)
            check = check && getCatalystItem().test(getRecipeInput2(r));
        return check;
    }

    public ItemStack assemble(T i, HolderLookup.Provider r) {
        return getOutputItem().copy();
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider a) {
        return getOutputItem() == null ? ItemStack.EMPTY : getOutputItem();
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
