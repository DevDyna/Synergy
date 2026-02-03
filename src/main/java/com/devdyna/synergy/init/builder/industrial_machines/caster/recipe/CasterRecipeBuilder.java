package com.devdyna.synergy.init.builder.industrial_machines.caster.recipe;

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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings({ "null" })
public class CasterRecipeBuilder extends BaseMachineRecipeBuilder<CasterRecipeBuilder>
        implements ItemAttach.Input.OptionalConsume<CasterRecipeBuilder>, FluidAttach.Input.SizedFluid<CasterRecipeBuilder> {

    private CasterRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CasterRecipeBuilder of() {
        return new CasterRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CasterRecipeType(ticks, energy, fluid_input, input, consumeCatalyst, output);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.CASTING_FACTORY;
    }

    @Override
    public CasterRecipeBuilder getBuilder() {
        return this;
    }

    @Override
    public CasterRecipeBuilder consumeItemInput() {
        this.consumeCatalyst = true;
        return getBuilder();
    }

    @Override
    public CasterRecipeBuilder fluid(SizedFluidIngredient fluid) {
        this.fluid_input = fluid;
        return getBuilder();
    }

}
