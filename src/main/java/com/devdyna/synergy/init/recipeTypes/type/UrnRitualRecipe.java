package com.devdyna.synergy.init.recipeTypes.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.init.recipeTypes.input.ItemListInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;

@SuppressWarnings("null")
public class UrnRitualRecipe implements Recipe<ItemListInput> {

    public static final int INPUT_COUNT = 9;
    public final List<ItemStack> inputs;
    public final ItemStack output;

    public UrnRitualRecipe(List<ItemStack> inputs,
            ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    public static UrnRitualRecipe of(List<ItemStack> inputs, ItemStack output) {
        return new UrnRitualRecipe(inputs, output);
    }

        public static UrnRitualRecipe of(ItemStack output,ItemStack... inputs ) {
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
                List.copyOf(this.inputs.stream().map(e -> Ingredient.of(e)).toList())) != null;
    }

    public ItemStack assemble(ItemListInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public boolean canCraftInDimensions(int xz, int y) {
        return false;
    }

    public RecipeType<?> getType() {
        return zRecipeTypes.URN_RITUAL_RECIPE.getType();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(zBlocks.URN.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return zRecipeTypes.URN_RITUAL_RECIPE.getSerializer();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(this.inputs.stream().map(e -> Ingredient.of(e)).toList());
    }

    public List<ItemStack> getInputItemStacks() {
        return inputs;
    }

    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }
}
