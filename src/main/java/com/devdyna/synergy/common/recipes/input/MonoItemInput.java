package com.devdyna.synergy.common.recipes.input;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record MonoItemInput(ItemStack input) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }

}