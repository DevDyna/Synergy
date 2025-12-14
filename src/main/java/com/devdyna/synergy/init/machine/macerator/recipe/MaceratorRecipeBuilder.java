package com.devdyna.synergy.init.machine.macerator.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.machine.core.BaseMachineMenu;
import com.devdyna.synergy.init.machine.core.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.init.machine.core.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;

@SuppressWarnings({ "null" })
public class MaceratorRecipeBuilder extends BaseMachineRecipeBuilder {

    private MaceratorRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static MaceratorRecipeBuilder of() {
        return new MaceratorRecipeBuilder();
    }

    @Override
    protected Recipe<?> createRecipe() {
        return new MaceratorRecipeType(ticks, energy, input, output, secondary, chance);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.MACERATOR;
    }
}
