package com.devdyna.synergy.common.recipes.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.api.zRecipe;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.common.recipes.input.ItemListInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;

@SuppressWarnings("null")
public class UrnRitualRecipe extends BaseRecipeType<ItemListInput> {

    public static final int INPUT_COUNT = 9;
    public final NonNullList<Ingredient> inputs;
    public final ItemStack output;

    public UrnRitualRecipe(List<Ingredient> inputs,
            ItemStack output) {
        this.inputs = NonNullList.copyOf(inputs);
        this.output = output;
    }

    public static UrnRitualRecipe of(NonNullList<Ingredient> inputs, ItemStack output) {
        return new UrnRitualRecipe(inputs, output);
    }

    public static UrnRitualRecipe of(ItemStack output, Ingredient... inputs) {
        return new UrnRitualRecipe(Arrays.asList(inputs), output);
    }

    public boolean matches(ItemListInput r, Level l) {
        List<ItemStack> temp = new ArrayList<>();
        if (r.size() < temp.size())
            return false;
        for (int j = 0; j < r.size(); ++j) {
            try {
                ItemStack item = r.getItem(j);
                if (!item.isEmpty()) {
                    temp.add(item);
                }
            } catch (Exception e) {

            }

        }
        return temp.size() == this.inputs.size() && RecipeMatcher.findMatches(temp,
                this.inputs) != null;
    }

    public ItemStack assemble(ItemListInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    public ItemStack getResultItem() {
        return output;
    }

   

    @Override
    public zRecipe<? extends BaseRecipeType<ItemListInput>> getRecipe() {
        return zRecipeTypes.URN_RITUAL_RECIPE;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.URN.get().asItem();
    }
}
