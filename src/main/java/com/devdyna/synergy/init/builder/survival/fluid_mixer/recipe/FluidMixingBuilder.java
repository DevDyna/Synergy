package com.devdyna.synergy.init.builder.survival.fluid_mixer.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.recipes.builders.*;

@SuppressWarnings("null")
public class FluidMixingBuilder extends BaseRecipeBuilder
        implements FluidAttach.Input.DoubleSizedFluid<FluidMixingBuilder>,
        FluidAttach.Output.OutputFluid<FluidMixingBuilder> {

    private SizedFluidIngredient a;
    private SizedFluidIngredient b;
    private FluidStack output;
    private int ticks = 20;

    public FluidMixingBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static FluidMixingBuilder of() {
        return new FluidMixingBuilder();
    }

    public FluidMixingBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(output.getFluid().getBucket()));
    }

    public FluidMixingBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.fluid_mixer + "/" + x.path(output.getFluid())
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FluidMixingRecipe(a, b, ticks, output);
    }

    @Override
    public FluidMixingBuilder getBuilder() {
        return this;
    }

    @Override
    public FluidMixingBuilder fluids(SizedFluidIngredient a, SizedFluidIngredient b) {
        this.a = a;
        this.b = b;
        return this;
    }

    @Override
    public FluidMixingBuilder output(FluidStack fluid) {
        this.output = fluid;
        return this;
    }

    public FluidMixingBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

}
