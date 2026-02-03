package com.devdyna.synergy.init.builder.industrial_machines.compressor.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.recipes.builders.*;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

@SuppressWarnings({ "null" })
public class CompressorRecipeBuilder extends BaseMachineRecipeBuilder<CompressorRecipeBuilder>
        implements ItemAttach.Input.CatalystItem<CompressorRecipeBuilder> {

    private CompressorRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CompressorRecipeBuilder of() {
        return new CompressorRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CompressorRecipeType(ticks, energy, input, optional_input, consumeCatalyst, output);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.COMPRESSOR;
    }

    @Override
    public CompressorRecipeBuilder catalyst(SizedIngredient catalyst) {
        this.optional_input = catalyst;
        return getBuilder();
    }

    public CompressorRecipeBuilder consumeCatalyst() {
        this.consumeCatalyst = true;
        return getBuilder();
    }

    @Override
    public CompressorRecipeBuilder getBuilder() {
        return this;
    }

}
