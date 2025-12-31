package com.devdyna.synergy.init.machine.alloy_smelter.recipe;

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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;

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
        return new AlloySmelterRecipeType(ticks, energy, input, catalyst, output);
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
    public AlloySmelterRecipeBuilder inputs(Ingredient right, Ingredient left) {
        this.input = right;
        this.catalyst = left;
        return getBuilder();
    }

}
