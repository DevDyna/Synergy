package com.devdyna.synergy.init.builder.industrial_machines.rock_crusher.recipe;

import java.util.*;

import com.devdyna.synergy.api.codec.recipe.ChanceOutputItem;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.machine.BaseMachineMenu;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.api.machine.recipe.BaseMachineRecipeType;
import com.devdyna.synergy.api.recipes.builders.*;
import com.devdyna.synergy.api.registers.MachineType;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zMachines;
import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "null" })
public class RockCrusherRecipeBuilder extends BaseMachineRecipeBuilder<RockCrusherRecipeBuilder>
        implements FluidAttach.Input.SizedFluid<RockCrusherRecipeBuilder> {

    private List<ChanceOutputItem> result;

    private RockCrusherRecipeBuilder() {
        this.energy = 1500;
        this.ticks = 120;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.result = new ArrayList<>(9);
    }

    public static RockCrusherRecipeBuilder of() {
        return new RockCrusherRecipeBuilder();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new RockCrusherRecipeType(ticks, energy, fluid_input, input, result);
    }

    @Override
    public MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine() {
        return zMachines.ROCK_CRUSHER;
    }

    @Override
    public RockCrusherRecipeBuilder getBuilder() {
        return this;
    }

    @Override
    public RockCrusherRecipeBuilder fluid(SizedFluidIngredient fluid) {
        this.fluid_input = fluid;
        return getBuilder();
    }

    public RockCrusherRecipeBuilder addResult(ItemStack item, float chance) {
        if (this.result.size() < 9)
            this.result.add(ChanceOutputItem.of(item, chance));
        return this;
    }

    public RockCrusherRecipeBuilder addResult(Item item, float chance) {
        return addResult(x.item(item), chance);
    }

    public RockCrusherRecipeBuilder addResult(DeferredHolder<Item, ?> item, float chance) {
        return addResult(x.item(item), chance);
    }

}
