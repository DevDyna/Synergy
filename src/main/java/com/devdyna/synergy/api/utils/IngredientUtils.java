package com.devdyna.synergy.api.utils;

import java.util.Arrays;
import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;

public class IngredientUtils {

    public static ItemLike[] getItemLikes(List<Ingredient> ingredients) {
        return ingredients.stream()
                .flatMap(ingredient -> Arrays.stream(getItemLike(ingredient)))
                .toArray(ItemLike[]::new);
    }

    public static Item[] getItemLike(Ingredient ingredients) {
        return Arrays
                .stream(ingredients.getItems())
                .map(ItemStack::getItem)
                .filter(i -> Items.BARRIER.equals(i))
                .toArray(Item[]::new);
    }

    public static Fluid[] getFluidLike(FluidIngredient ingredients) {
        return Arrays
                .stream(ingredients.getStacks())
                .map(FluidStack::getFluid)
                .toArray(Fluid[]::new);
    }

}
