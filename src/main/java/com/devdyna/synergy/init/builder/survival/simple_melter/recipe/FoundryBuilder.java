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
public class FoundryBuilder extends BaseRecipeBuilder
        implements SimpleInputItem<FoundryBuilder>,
        SimpleFluidAttach<FoundryBuilder> {

    private Ingredient input;
    private int ticks = 100;
    private FluidStack fluid;

    private FoundryBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static FoundryBuilder of() {
        return new FoundryBuilder();
    }

    public FoundryBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public FoundryBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public Item getResult() {
        return this.fluid.getFluid().getBucket();
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FoundryRecipe(input, ticks, fluid);
    }

    @Override
    public FoundryBuilder getBuilder() {
        return this;
    }

    @Override
    public FoundryBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public FoundryBuilder fluid(FluidStack fluid) {
        this.fluid = fluid;
        return this;
    }

    @Override
    public FoundryBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("simple_melter/" + x.path(this.fluid.getFluid()) + extra);
    }

}
