package com.devdyna.synergy.api.builders;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

import com.devdyna.synergy.init.recipeTypes.type.UrnRitualRecipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "null" })
public class UrnRitualRecipeBuilder implements RecipeBuilder {

    private final ItemStack input;
    private final ItemStack output;

    private final Map<String, Criterion<?>> criteria;

    private UrnRitualRecipeBuilder(
            ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static <T> UrnRitualRecipeBuilder of(
            ItemStack input, ItemStack output) {
        return new UrnRitualRecipeBuilder(input, output);
    }

    public static <T> UrnRitualRecipeBuilder of(
            Item input, int inputCount, Item output, int outputCount) {
        return new UrnRitualRecipeBuilder(new ItemStack(input, inputCount), new ItemStack(output, outputCount));
    }

    public static <T> UrnRitualRecipeBuilder of(
            Item input, int inputCount, ItemStack output) {
        return new UrnRitualRecipeBuilder(new ItemStack(input, inputCount), output);
    }

    public static <T> UrnRitualRecipeBuilder of(
            ItemStack input, Item output, int outputCount) {
        return new UrnRitualRecipeBuilder(input, new ItemStack(output, outputCount));
    }

    public static <T> UrnRitualRecipeBuilder of(
            DeferredHolder<Item, Item> input, int inputCount, DeferredHolder<Item, Item> output, int outputCount) {
        return new UrnRitualRecipeBuilder(new ItemStack(input, inputCount), new ItemStack(output, outputCount));
    }

    public static <T> UrnRitualRecipeBuilder of(
            DeferredHolder<Item, Item> input, int inputCount, ItemStack output) {
        return new UrnRitualRecipeBuilder(new ItemStack(input, inputCount), output);
    }

    public static <T> UrnRitualRecipeBuilder of(
            ItemStack input, DeferredHolder<Item, Item> output, int outputCount) {
        return new UrnRitualRecipeBuilder(input, new ItemStack(output, outputCount));
    }

        public static <T> UrnRitualRecipeBuilder of(
            DeferredHolder<Item, Item> input, int inputCount, Item output, int outputCount) {
        return new UrnRitualRecipeBuilder(new ItemStack(input, inputCount), new ItemStack(output, outputCount));
    }

        public static <T> UrnRitualRecipeBuilder of(
            Item input, int inputCount, DeferredHolder<Item, Item> output, int outputCount) {
        return new UrnRitualRecipeBuilder(new ItemStack(input, inputCount), new ItemStack(output, outputCount));
    }

    public UrnRitualRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public UrnRitualRecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        if (this.criteria.isEmpty())
            throw new IllegalStateException("Missing/Null Criteria " + String.valueOf(pId));
        Advancement.Builder advancement$builder = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        UrnRitualRecipe shapelessrecipe = new UrnRitualRecipe(input, output);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
