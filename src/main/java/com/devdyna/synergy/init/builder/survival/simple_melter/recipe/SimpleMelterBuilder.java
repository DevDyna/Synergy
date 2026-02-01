package com.devdyna.synergy.init.builder.survival.simple_melter.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.*;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.SimpleFluidAttach;
import com.devdyna.synergy.api.recipes.builders.legacy.SimpleInputItem;
import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.FluidStack;

@SuppressWarnings({ "null" })
public class SimpleMelterBuilder extends BaseRecipeBuilder
        implements SimpleInputItem<SimpleMelterBuilder>,
        SimpleFluidAttach<SimpleMelterBuilder> {

    private Ingredient input;
    private int ticks = 100;
    private FluidStack fluid;

    private SimpleMelterBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static SimpleMelterBuilder of() {
        return new SimpleMelterBuilder();
    }

    public SimpleMelterBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public SimpleMelterBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.fluid.getFluid().getBucket();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new SimpleMelterRecipe(input, ticks, fluid);
    }

    @Override
    public SimpleMelterBuilder getBuilder() {
        return this;
    }

    @Override
    public SimpleMelterBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public SimpleMelterBuilder fluid(FluidStack fluid) {
        this.fluid = fluid;
        return this;
    }

    @Override
    public SimpleMelterBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("simple_melter/" + x.path(this.fluid.getFluid()) + extra);
    }

}
