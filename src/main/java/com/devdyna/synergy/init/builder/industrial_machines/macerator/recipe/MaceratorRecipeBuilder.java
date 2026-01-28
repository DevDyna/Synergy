package com.devdyna.synergy.init.builder.industrial_machines.macerator.recipe;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.recipes.builders.SecondaryOutputItem;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;

@SuppressWarnings({ "null" })
public class MaceratorRecipeBuilder extends BaseMachineRecipeBuilder<MaceratorRecipeBuilder>
        implements SecondaryOutputItem<MaceratorRecipeBuilder> {

    private MaceratorRecipeBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static MaceratorRecipeBuilder of() {
        return new MaceratorRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new MaceratorRecipeType(ticks, energy, input, output, optional_output, chance);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.MACERATOR;
    }

    public MaceratorRecipeBuilder secondary(ItemStack secondary, float chance) {
        this.optional_output = secondary;
        this.chance = chance;
        return getBuilder();
    }

    @Override
    public MaceratorRecipeBuilder getBuilder() {
        return this;
    }

}
