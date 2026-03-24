package com.devdyna.synergy.api.recipes.inputs;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;

public record ItemFluidInput(FluidStack input,ItemStack item) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        return input.getFluidType().getBucket(input);
    }

    @Override
    public int size() {
        return 2;
    }

}