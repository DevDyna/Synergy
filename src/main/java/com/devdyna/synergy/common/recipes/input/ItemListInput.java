package com.devdyna.synergy.common.recipes.input;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record ItemListInput(List<ItemStack> input) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        return input.get(i);
    }

    @Override
    public int size() {
        return input.size();
    }

}