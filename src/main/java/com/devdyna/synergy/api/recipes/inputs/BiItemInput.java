package com.devdyna.synergy.api.recipes.inputs;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record BiItemInput(ItemStack first,ItemStack second) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        return List.of(first,second).get(i);
    }

    @Override
    public int size() {
        return 2;
    }

}