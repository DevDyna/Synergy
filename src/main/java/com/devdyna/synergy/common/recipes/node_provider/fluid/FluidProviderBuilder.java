package com.devdyna.synergy.common.recipes.node_provider.fluid;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.recipes.builders.BaseProviderBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings({ "null" })
public class FluidProviderBuilder extends BaseProviderBuilder<FluidProviderBuilder> {

    private FluidStack output;

    public FluidProviderBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static FluidProviderBuilder of() {
        return new FluidProviderBuilder();
    }

    public FluidProviderBuilder output(FluidStack b) {
        this.output = b;
        return this;
    }

    public FluidProviderBuilder output(Fluid b) {
        return output(x.fluid(b));
    }

    public FluidProviderBuilder output(Fluid b, int c) {
        return output(x.fluid(b, c));
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FluidProviderRecipe<>(pattern, output);
    }

    @Override
    protected FluidProviderBuilder getBuilder() {
        return this;
    }

    @Override
    public String getOutputPath() {
        return x.path(output.getFluid());
    }

    @Override
    public String getFolderPath() {
        return "fluid";
    }
}
