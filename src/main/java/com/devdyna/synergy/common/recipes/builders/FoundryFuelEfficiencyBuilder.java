package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.FoundryFuelEfficiencyRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.FluidStack;
import com.devdyna.synergy.api.recipes.builders.*;

@SuppressWarnings("null")
public class FoundryFuelEfficiencyBuilder extends BaseRecipeBuilder
        implements FluidAttach.Any.SimpleFluidAttach<FoundryFuelEfficiencyBuilder> {

    private FluidStack fluid;
    private float usageModifier;
    private float speedModifier;

    public FoundryFuelEfficiencyBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static FoundryFuelEfficiencyBuilder of() {
        return new FoundryFuelEfficiencyBuilder();
    }

    public FoundryFuelEfficiencyBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(fluid.getFluid().getBucket()));
    }

    public FoundryFuelEfficiencyBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public FoundryFuelEfficiencyBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return fluid.getFluid().getBucket();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("foundry_fuels/" + x.path(fluid.getFluid())
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FoundryFuelEfficiencyRecipe(fluid, usageModifier, speedModifier);
    }

    @Override
    public FoundryFuelEfficiencyBuilder getBuilder() {
        return this;
    }

    public FoundryFuelEfficiencyBuilder speed(float s) {
        this.speedModifier = s;
        return getBuilder();
    }

    public FoundryFuelEfficiencyBuilder usage(float u) {
        this.usageModifier = u;
        return getBuilder();
    }

    @Override
    public FoundryFuelEfficiencyBuilder fluid(FluidStack fluid) {
        this.fluid = fluid;
        return getBuilder();
    }

}
