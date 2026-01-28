package com.devdyna.synergy.init.builder.industrial_machines.alloy_smelter.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.recipes.builders.DoubleInputItem;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

@SuppressWarnings({ "null" })
public class AlloySmelterRecipeBuilder extends BaseMachineRecipeBuilder<AlloySmelterRecipeBuilder>
        implements DoubleInputItem<AlloySmelterRecipeBuilder> {

    private AlloySmelterRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static AlloySmelterRecipeBuilder of() {
        return new AlloySmelterRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new AlloySmelterRecipeType(ticks, energy, input, extra_input, output);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.ALLOY_SMELTER;
    }

    @Override
    public AlloySmelterRecipeBuilder getBuilder() {
        return this;
    }

    @Override
    public AlloySmelterRecipeBuilder inputs(SizedIngredient right, SizedIngredient left) {
        this.input = right;
        this.extra_input = left;
        return getBuilder();
    }

}
