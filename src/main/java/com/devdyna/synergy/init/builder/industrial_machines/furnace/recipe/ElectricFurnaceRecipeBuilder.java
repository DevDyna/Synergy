package com.devdyna.synergy.init.builder.industrial_machines.furnace.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machines.BaseMachineBE;
import com.devdyna.synergy.api.machines.BaseMachineBlock;
import com.devdyna.synergy.api.machines.BaseMachineMenu;
import com.devdyna.synergy.api.machines.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.machines.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;

@SuppressWarnings({ "null" })
public class ElectricFurnaceRecipeBuilder extends BaseMachineRecipeBuilder<ElectricFurnaceRecipeBuilder> {

    private ElectricFurnaceRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ElectricFurnaceRecipeBuilder of() {
        return new ElectricFurnaceRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new ElectricFurnaceRecipeType(ticks, energy, input, output);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.ELECTRIC_FURNACE;
    }

    @Override
    public ElectricFurnaceRecipeBuilder getBuilder() {
        return this;
    }

}
