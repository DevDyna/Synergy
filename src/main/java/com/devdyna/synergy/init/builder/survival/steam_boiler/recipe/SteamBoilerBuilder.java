package com.devdyna.synergy.init.builder.survival.steam_boiler.recipe;

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
public class SteamBoilerBuilder extends BaseRecipeBuilder
        implements FluidAttach.Input.SizedFluid<SteamBoilerBuilder>,
        FluidAttach.Output.OutputFluid<SteamBoilerBuilder> {

    private SizedFluidIngredient input;
    private FluidStack output;
    private int ticks = 20;

    public SteamBoilerBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static SteamBoilerBuilder of() {
        return new SteamBoilerBuilder();
    }

    public SteamBoilerBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(output.getFluid().getBucket()));
    }

    public SteamBoilerBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.steam_boiler + "/" + x.path(output.getFluid())
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new SteamBoilerRecipe(input, ticks, output);
    }

    @Override
    public SteamBoilerBuilder getBuilder() {
        return this;
    }

    @Override
    public SteamBoilerBuilder output(FluidStack fluid) {
        this.output = fluid;
        return this;
    }

    public SteamBoilerBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public SteamBoilerBuilder fluid(SizedFluidIngredient fluid) {
        this.input = fluid;
        return this;
    }

}
