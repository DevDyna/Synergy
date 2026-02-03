package com.devdyna.synergy.init.builder.survival.simple_melter.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.input.MonoItemInput;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings("null")
public class FoundryRecipe extends BaseRecipeType<MonoItemInput> {

    private final Ingredient input;
    private final int ticks;
    private final FluidStack fluid;

    public FoundryRecipe(Ingredient input, int ticks, FluidStack fluid) {
        this.fluid = fluid;
        this.ticks = ticks;
        this.input = input;
    }

    public static FoundryRecipe of(Ingredient input, int ticks, FluidStack fluid) {
        return new FoundryRecipe(input,ticks,fluid);
    }

    public boolean matches(MonoItemInput r, Level l) {
        return this.input.test(r.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return x.item(this.fluid.getFluid().getBucket()).copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public FluidStack getFluid() {
        return fluid;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getTicks() {
        return ticks;
    }


    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return x.item(this.fluid.getFluid().getBucket()).copy();
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<MonoItemInput>> getRecipe() {
        return zRecipeTypes.FOUNDRY;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.FOUNDRY.get().asItem();
    }
}
