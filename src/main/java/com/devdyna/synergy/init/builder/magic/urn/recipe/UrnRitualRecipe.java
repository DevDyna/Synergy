package com.devdyna.synergy.init.builder.magic.urn.recipe;

import java.util.Arrays;
import java.util.List;

import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.common.recipes.input.ItemListInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

@SuppressWarnings("null")
public class UrnRitualRecipe extends BaseRecipeType<ItemListInput> {

    public static final int INPUT_COUNT = 9;
    public final List<SizedIngredient> inputs;
    public final ItemStack output;

    public UrnRitualRecipe(List<SizedIngredient> inputs,
            ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    public static UrnRitualRecipe of(List<SizedIngredient> inputs, ItemStack output) {
        return new UrnRitualRecipe(inputs, output);
    }

    public static UrnRitualRecipe of(ItemStack output, SizedIngredient... inputs) {
        return new UrnRitualRecipe(Arrays.asList(inputs), output);
    }

    @Override
    public boolean matches(ItemListInput r, Level level) {

        if (r.input().size() < inputs.size())
            return false;

        boolean[] used = new boolean[r.input().size()];

        for (var ingredient : inputs) {
            boolean found = false;

            for (int i = 0; i < r.input().size(); i++) {
                if (used[i])
                    continue;

                var stack = r.input().get(i);

                if (ingredient.test(stack) && stack.getCount() >= ingredient.count()) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }

            if (!found)
                return false;
        }

        return true;
    }

    public ItemStack assemble(ItemListInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    @Deprecated
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(inputs.stream()
                .map(SizedIngredient::getItems)
                .map(Ingredient::of).toList());
    }

    public List<SizedIngredient> getInputs() {
        return this.inputs;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<ItemListInput>> getRecipe() {
        return zRecipeTypes.URN_RITUAL_RECIPE;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.URN.get().asItem();
    }
}
