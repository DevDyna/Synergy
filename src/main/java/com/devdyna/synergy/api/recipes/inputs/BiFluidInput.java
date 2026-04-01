package com.devdyna.synergy.api.recipes.inputs;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;

public record BiFluidInput(FluidStack first,FluidStack second) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        return first.getFluidType().getBucket(first);
    }

    @Override
    public int size() {
        return 2;
    }

}