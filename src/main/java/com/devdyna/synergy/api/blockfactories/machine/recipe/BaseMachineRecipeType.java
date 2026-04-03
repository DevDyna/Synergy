package com.devdyna.synergy.api.blockfactories.machine.recipe;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBlock;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineMenu;
import com.devdyna.synergy.api.codec.ChanceOutputItem;
import com.devdyna.synergy.api.registers.MachineType;
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
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings("null")
public abstract class BaseMachineRecipeType<T extends RecipeInput> implements Recipe<T> {

    public int ticks;
    public int energy;
    public SizedIngredient input;
    public SizedIngredient optional_input;
    public ItemStack output;
    @Deprecated
    public ItemStack optional_output;

    public @Nullable ChanceOutputItem optional_output_item;
    // public SizedIngredient extra_input;
    @Deprecated
    public float chance;
    public boolean consumeCatalyst;
    public SizedFluidIngredient fluid_input;
    public SizedFluidIngredient optional_fluid_input;
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

    public SizedIngredient getInputItem() {
        return input;
    }

    public ItemStack getOutputItem() {
        return output;
    }

    @Deprecated
    public ItemStack getSecondaryItem() {
        return optional_output;
    }

    public @Nullable ChanceOutputItem getSecondaryOutputItem() {
        return optional_output_item;
    }

    public SizedIngredient getCatalystItem() {
        return optional_input;
    }

    public FluidStack getFluidOutput() {
        return fluid_output;
    }

    public SizedFluidIngredient getFluidInput() {
        return fluid_input;
    }

    public SizedFluidIngredient getOptionalFluidInput() {
        return optional_fluid_input;
    }

    /**
     * 0.00 -> 1.00
     */
    @Deprecated
    public float getSecondaryItemChance() {
        return chance;
    }

    // public boolean hasCatalyst(T r) {
    // return false;
    // }

    public boolean hasSecondaryOutput() {
        return ChanceOutputItem.valid(optional_output_item);
    }

    @Deprecated
    public NonNullList<Ingredient> getIngredients() {
        var list = new ArrayList<Ingredient>();
        if (getInputItem() != null)
            list.add(getInputItem().ingredient());
        if (getCatalystItem() != null)
            list.add(getCatalystItem().ingredient());
        return NonNullList.copyOf(list);
    }

    public ItemStack getRecipeInput(T recipe) {
        return null;
    }

    public ItemStack getRecipeInput2(T recipe) {
        return null;
    };

    public SizedFluidIngredient getRecipeFluidInput(T recipe) {
        return null;
    };

    public SizedFluidIngredient getRecipeFluidInput2(T recipe) {
        return null;
    };

    public boolean matches(T r, Level l) {

        // primary item is present
        if (getRecipeInput(r) != null && getInputItem() != null) 
            if (!x.getItems(getInputItem()).isEmpty()) {

            // primary item dont match
            if (!getInputItem().test(getRecipeInput(r)))
                return false;

            // primary count dont match
            if (getInputItem().count() > getRecipeInput(r).getCount())
                return false;
        }

        // primary fluid is present
        if (getFluidInput() != null && getRecipeFluidInput(r) != null)
            if (!x.getFluids(getFluidInput()).isEmpty())
                if (!x.getFluids(getRecipeFluidInput(r))
                        .stream()
                        .anyMatch(i -> getFluidInput().test(i)))
                    return false;

        // secondary fluid is present
        if (getOptionalFluidInput() != null && getRecipeFluidInput2(r) != null)
            if (!x.getFluids(getOptionalFluidInput()).isEmpty())
                if (!x.getFluids(getRecipeFluidInput2(r))
                        .stream()
                        .anyMatch(i -> getOptionalFluidInput().test(i)))
                    return false;

        // secondary item input is present
        if (getCatalystItem() != null && getRecipeInput2(r) != null)
            if (!x.getItems(getCatalystItem()).isEmpty()) {

                // secondary item input dont match
                if (getCatalystItem().count() > getRecipeInput2(r).getCount())
                    return false;

                // secondary item input dont match
                if (!getCatalystItem().test(getRecipeInput2(r)))
                    return false;
            }

        return true;
    }

    @Deprecated
    public ItemStack assemble(T i, HolderLookup.Provider r) {
        return getOutputItem().copy();
    }

    @Deprecated
    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    @Override
    @Deprecated
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
