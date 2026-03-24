package com.devdyna.synergy.init.builder.survival.crushing_tub.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.inputs.MonoItemInput;
import com.devdyna.synergy.api.recipes.types.BaseRecipeType;
import com.devdyna.synergy.api.registers.RecipeRegister;
import com.devdyna.synergy.api.utils.x;
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
public class CrushingTubRecipe extends BaseRecipeType<MonoItemInput> {

    private final Ingredient input;
    private final FluidStack fluid;
    private final ItemStack output;

    public CrushingTubRecipe(Ingredient input,
            ItemStack output, FluidStack fluid) {
        this.input = input;
        this.fluid = fluid;
        this.output = output;
    }

    public static CrushingTubRecipe of(Ingredient input, ItemStack output, FluidStack fluid) {
        return new CrushingTubRecipe(input, output, fluid);
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

    public FluidStack getFluid() {
        return fluid;
    }

    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider r) {
        return this.output != null
                ? this.output
                : x.item(this.fluid.getFluid().getBucket()).copy();
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<MonoItemInput>> getRecipe() {
        return zRecipeTypes.CRUSHING_TUB;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.CRUSHING_TUB.get().asItem();
    }
}
