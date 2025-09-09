package com.devdyna.synergy.api.builders;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;

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

@SuppressWarnings({ "null" })
public class FuelCellRecipeBuilder implements RecipeBuilder {

    private final ItemStack input;
    private final ItemStack output;
    private final int duration;
    private final double heat;
    private final int fe;

    private final Map<String, Criterion<?>> criteria;

    private FuelCellRecipeBuilder(
            ItemStack input, ItemStack output, int duration, int fe, double heat) {
        this.input = input;
        this.output = output;
        this.duration = duration;
        this.heat = heat;
        this.fe = fe;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static <T> FuelCellRecipeBuilder of(
            ItemStack input, ItemStack output, int duration, int fe, double heat) {
        return new FuelCellRecipeBuilder(input, output, duration, fe, heat);
    }

    public static <T> FuelCellRecipeBuilder of(
            ItemStack input, ItemStack output, int duration, int fe, int heat) {
        return of(input, output, duration, fe, (double) heat);
    }

    public static <T> FuelCellRecipeBuilder of(
            Item input, Item output, int duration, int fe, double heat) {
        return of(new ItemStack(input), new ItemStack(output), duration, fe, heat);
    }

    public static <T> FuelCellRecipeBuilder of(
            Item input, Item output, int duration, int fe, int heat) {
        return of(new ItemStack(input), new ItemStack(output), duration, fe, (double) heat);
    }

    public static <T> FuelCellRecipeBuilder of(
            ItemStack input, Item output, int duration, int fe, double heat) {
        return of(input, new ItemStack(output), duration, fe, heat);
    }

    public static <T> FuelCellRecipeBuilder of(
            ItemStack input, Item output, int duration, int fe, int heat) {
        return of(input, new ItemStack(output), duration, fe, (double) heat);
    }

    public static <T> FuelCellRecipeBuilder of(
            Item input, ItemStack output, int duration, int fe, double heat) {
        return of(new ItemStack(input), output, duration, fe, heat);
    }

    public static <T> FuelCellRecipeBuilder of(
            Item input, ItemStack output, int duration, int fe, int heat) {
        return of(new ItemStack(input), output, duration, fe, (double) heat);
    }

    public FuelCellRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public FuelCellRecipeBuilder group(@Nullable String groupName) {
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
        FuelCellRecipe shapelessrecipe = new FuelCellRecipe(input, output, duration, fe, heat);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
