package com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.recipe;

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
public class FuelCellRecipe extends BaseRecipeType<MonoItemInput> {

    protected final Ingredient input;
    protected final ItemStack output;
    protected final int duration;
    protected final double heat;
    protected final int fe;

    public FuelCellRecipe(Ingredient input,
            ItemStack output, int duration, int fe, double heat) {
        this.input = input;
        this.output = output;
        this.duration = (duration < 1 ? 1 : duration);
        this.heat = heat;
        this.fe = fe;
    }

    public boolean matches(MonoItemInput i, Level l) {
        return this.input.test(i.input());
    }

    public ItemStack assemble(MonoItemInput i, HolderLookup.Provider r) {
        return this.output.copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.input);
        return list;
    }
@Deprecated
    public ItemStack getResultItem(HolderLookup.Provider r) {
        return this.output;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public ItemStack getOutput(){
        return this.output;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getFe() {
        return fe;
    }

    public double getHeat() {
        return heat;
    }




    @Override
    public RecipeRegister<? extends BaseRecipeType<MonoItemInput>> getRecipe() {
        return zRecipeTypes.FUEL_CELL_RECIPE;
    }

    @Override
    public Item getToastIcon() {
       return zBlocks.REACTOR_FUEL_CELL.get().asItem();
    }

}
