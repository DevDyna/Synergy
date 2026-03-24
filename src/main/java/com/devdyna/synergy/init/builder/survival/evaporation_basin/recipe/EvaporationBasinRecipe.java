package com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe;

import java.util.List;

import com.devdyna.synergy.api.recipes.inputs.FluidInput;
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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings("null")
public class EvaporationBasinRecipe extends BaseRecipeType<FluidInput> {

    private final SizedFluidIngredient fluid;
    private final int ticks;
    private final ItemStack output;

    public EvaporationBasinRecipe(SizedFluidIngredient fluid, int ticks, ItemStack output) {
        this.fluid = fluid;
        this.ticks = ticks;
        this.output = output;
    }

    public static EvaporationBasinRecipe of(SizedFluidIngredient fluid, int ticks, ItemStack output) {
        return new EvaporationBasinRecipe(fluid,ticks,output);
    }

    public boolean matches(FluidInput r, Level l) {
        return this.fluid.test(r.input());
    }

    public ItemStack assemble(FluidInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.ingredient(x.getFluids(this.fluid).getFirst().getFluid().getBucket())));
    }

    public SizedFluidIngredient getFluid() {
        return fluid;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTicks() {
        return ticks;
    }


    @Override
    @Deprecated
    public ItemStack getResultItem(HolderLookup.Provider registryAccess) {
        return this.output;
    }

    @Override
    public RecipeRegister<? extends BaseRecipeType<FluidInput>> getRecipe() {
        return zRecipeTypes.EVAPORATING_BASIN;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.EVAPORATION_BASIN.get().asItem();
    }
}
