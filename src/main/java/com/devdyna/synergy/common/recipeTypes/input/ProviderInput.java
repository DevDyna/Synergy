package com.devdyna.synergy.common.recipeTypes.input;

import com.devdyna.synergy.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.state.BlockState;

public record ProviderInput(BlockState core) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        return x.item(core);
    }

    @Override
    public int size() {
        return 1;
    }

}