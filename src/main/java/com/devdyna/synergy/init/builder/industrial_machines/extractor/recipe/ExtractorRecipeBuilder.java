package com.devdyna.synergy.init.builder.industrial_machines.extractor.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;

import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBlock;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineMenu;
import com.devdyna.synergy.api.blockfactories.machine.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.blockfactories.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.recipes.builders.*;

@SuppressWarnings({ "null" })
public class ExtractorRecipeBuilder extends BaseMachineRecipeBuilder<ExtractorRecipeBuilder>
        implements FluidAttach.Any.SimpleFluidAttach<ExtractorRecipeBuilder> , ItemAttach.Output.SecondaryOutputItem<ExtractorRecipeBuilder> {

    private ExtractorRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ExtractorRecipeBuilder of() {
        return new ExtractorRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new ExtractorRecipeType(ticks, energy, input, optional_output, fluid_output, chance);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.EXTRACTOR;
    }

    @Override
    public ExtractorRecipeBuilder getBuilder() {
        return this;
    }

    @Override
    public ExtractorRecipeBuilder fluid(FluidStack fluid) {
        this.fluid_output = fluid;
        return getBuilder();
    }

    @Override
    public ExtractorRecipeBuilder secondary(ItemStack secondary, float chance) {
        this.optional_output = secondary;
        this.chance = chance;
        return getBuilder();
    }

}
