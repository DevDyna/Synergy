package com.devdyna.synergy.common.recipes.foundry_fuel;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.FluidStack;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.recipes.builders.*;

@SuppressWarnings("null")
public class FoundryFuelBuilder extends BaseRecipeBuilder
        implements FluidAttach.Any.SimpleFluidAttach<FoundryFuelBuilder> {

    private FluidStack fluid;
    private float usageModifier;
    private float speedModifier;

    public FoundryFuelBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static FoundryFuelBuilder of() {
        return new FoundryFuelBuilder();
    }

    public FoundryFuelBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(fluid.getFluid().getBucket()));
    }

    public FoundryFuelBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public FoundryFuelBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return fluid.getFluid().getBucket();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.foundry+"_fuels/" + x.path(fluid.getFluid())
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FoundryFuelEfficiencyRecipe(fluid, usageModifier, speedModifier);
    }

    @Override
    public FoundryFuelBuilder getBuilder() {
        return this;
    }

    public FoundryFuelBuilder speed(float s) {
        this.speedModifier = s;
        return getBuilder();
    }

    public FoundryFuelBuilder usage(float u) {
        this.usageModifier = u;
        return getBuilder();
    }

    @Override
    public FoundryFuelBuilder fluid(FluidStack fluid) {
        this.fluid = fluid;
        return getBuilder();
    }

}
