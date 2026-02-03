package com.devdyna.synergy.init.builder.industrial_machines.melter.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;
import com.devdyna.synergy.api.recipes.builders.*;


@SuppressWarnings({ "null" })
public class MelterRecipeBuilder extends BaseMachineRecipeBuilder<MelterRecipeBuilder>
        implements FluidAttach.Any.SimpleFluidAttach<MelterRecipeBuilder> {

    private MelterRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.energy = BaseMachineBE.DEFAULT_FE_COST*10;
        this.ticks = BaseMachineBE.DEFAULT_TICK_DURATION*4;
    }

    public static MelterRecipeBuilder of() {
        return new MelterRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new MelterRecipeType(ticks, energy, input, fluid_output);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.MELTER;
    }

    @Override
    public MelterRecipeBuilder getBuilder() {
        return this;
    }

    @Override
    public MelterRecipeBuilder fluid(FluidStack fluid) {
        this.fluid_output = fluid;
        return getBuilder();
    }


}
