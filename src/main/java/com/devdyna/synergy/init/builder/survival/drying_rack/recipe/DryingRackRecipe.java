package com.devdyna.synergy.init.builder.survival.drying_rack.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.inputs.MonoItemInput;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

@SuppressWarnings("null")
public class DryingRackRecipe extends BaseRecipeType<MonoItemInput> {

    private final Ingredient input;
    private final int ticks;
    private final ItemStack output;

    public DryingRackRecipe(Ingredient input,int ticks, ItemStack output) {
        this.input = input;
        this.output = output;
        this.ticks = ticks;
    }

    public boolean matches(MonoItemInput r, Level l) {
        return input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output;
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(input));
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return output;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTicks() {
        return ticks;
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<MonoItemInput>> getRecipe() {
        return zRecipeTypes.DRYING_RACK;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.VOID_BOX.get().asItem();
    }

}
