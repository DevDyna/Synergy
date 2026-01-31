package com.devdyna.synergy.init.builder.magic.quern.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class QuernMillingRecipe extends BaseRecipeType<MonoItemInput> {

    private final Ingredient input;
    private final int time;
    private final ItemStack output;

    public QuernMillingRecipe(Ingredient input,
            ItemStack output, int time) {
        this.input = input;
        this.time = time;
        this.output = output;
    }

    public static QuernMillingRecipe of(Ingredient input, ItemStack output, int time) {
        return new QuernMillingRecipe(input, output, time);
    }

    public boolean matches(MonoItemInput r, Level l) {
        return this.input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTime() {
        return time;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<MonoItemInput>> getRecipe() {
        return zRecipeTypes.QUERN_MILLING;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.QUERN.get().asItem();
    }
}
