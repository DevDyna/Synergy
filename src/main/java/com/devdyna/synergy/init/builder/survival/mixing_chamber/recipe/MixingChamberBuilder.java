package com.devdyna.synergy.init.builder.survival.mixing_chamber.recipe;

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
public class MixingChamberBuilder extends BaseRecipeBuilder
        implements FluidAttach.Input.DoubleSizedFluid<MixingChamberBuilder>,
        FluidAttach.Output.OutputFluid<MixingChamberBuilder> {

    private SizedFluidIngredient a;
    private SizedFluidIngredient b;
    private FluidStack output;
    private int ticks = 20;

    public MixingChamberBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static MixingChamberBuilder of() {
        return new MixingChamberBuilder();
    }

    public MixingChamberBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(output.getFluid().getBucket()));
    }

    public MixingChamberBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.mixing_chamber + "/" + x.path(output.getFluid())
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new MixingChamberRecipe(a, b, ticks, output);
    }

    @Override
    public MixingChamberBuilder getBuilder() {
        return this;
    }

    @Override
    public MixingChamberBuilder fluids(SizedFluidIngredient a, SizedFluidIngredient b) {
        this.a = a;
        this.b = b;
        return this;
    }

    @Override
    public MixingChamberBuilder output(FluidStack fluid) {
        this.output = fluid;
        return this;
    }

    public MixingChamberBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

}
