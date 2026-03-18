package com.devdyna.synergy.init.builder.survival.evaporation_basin.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.*;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.recipes.builders.*;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

@SuppressWarnings({ "null" })
public class EvaporatingBasinBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<EvaporatingBasinBuilder>,
        FluidAttach.Input.SizedFluid<EvaporatingBasinBuilder> {

    private SizedFluidIngredient fluid;
    private int ticks = 100;
    private ItemStack output;

    private EvaporatingBasinBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static EvaporatingBasinBuilder of() {
        return new EvaporatingBasinBuilder();
    }

    public EvaporatingBasinBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(x.getFluids(this.fluid).getFirst().getFluid().getBucket()));
    }

    public EvaporatingBasinBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new EvaporationBasinRecipe(fluid, ticks, output);
    }

    @Override
    public EvaporatingBasinBuilder getBuilder() {
        return this;
    }

    @Override
    public EvaporatingBasinBuilder fluid(SizedFluidIngredient fluid) {
        this.fluid = fluid;
        return this;
    }

    @Override
    public EvaporatingBasinBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public EvaporatingBasinBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl(zStatic.Blocks.evaporation_basin + "/" + x.path(this.output.getItem()) + extra);
    }

}
