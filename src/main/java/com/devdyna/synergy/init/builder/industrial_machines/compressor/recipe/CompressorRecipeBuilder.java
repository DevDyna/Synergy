package com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.recipes.builders.CatalystItem;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;

@SuppressWarnings({ "null" })
public class CompressorRecipeBuilder extends BaseMachineRecipeBuilder<CompressorRecipeBuilder> implements CatalystItem<CompressorRecipeBuilder> {

    private CompressorRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CompressorRecipeBuilder of() {
        return new CompressorRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CompressorRecipeType(ticks,energy,input,catalyst,output);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.COMPRESSOR;
    }

    @Override
    public CompressorRecipeBuilder catalyst(Ingredient catalyst) {
        this.catalyst = catalyst;
        return getBuilder();
    }

    @Override
    public CompressorRecipeBuilder getBuilder() {
        return this;
    }

}
