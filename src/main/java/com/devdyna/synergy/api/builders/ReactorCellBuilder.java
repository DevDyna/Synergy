package com.devdyna.synergy.api.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

import com.devdyna.synergy.init.recipeTypes.type.FuelCellRecipe;
import com.devdyna.synergy.utils.x;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "null" })
public class ReactorCellBuilder implements RecipeBuilder {

    private Ingredient input;
    private ItemStack output;
    private int duration;
    private double heat;
    private int fe;

    private final Map<String, Criterion<?>> criteria;

    private ReactorCellBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ReactorCellBuilder of() {
        return new ReactorCellBuilder();
    }

    public ReactorCellBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public ReactorCellBuilder input(Item input) {
        return input(x.ingredient(input));
    }

    public ReactorCellBuilder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public ReactorCellBuilder input(TagKey<Item> input) {
        return input(x.ingredient(input));
    }

    public ReactorCellBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public ReactorCellBuilder output(Item output) {
        return output(x.item(output));
    }

    public ReactorCellBuilder output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    public ReactorCellBuilder output(Item output, int count) {
        return output(x.item(output, count));
    }

    public ReactorCellBuilder output(DeferredHolder<Item, Item> output, int count) {
        return output(output.get(), count);
    }

    public ReactorCellBuilder energy(int fe) {
        this.fe = fe;
        return this;
    }

    public ReactorCellBuilder duration(int ticks) {
        this.duration = ticks;
        return this;
    }

    public ReactorCellBuilder heat(double heat) {
        this.heat = heat;
        return this;
    }

    public ReactorCellBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(this.input.getItems()[0].getItem()));
    }

    public ReactorCellBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public ReactorCellBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput recipeOutput, Boolean applyInput) {
        this.save(recipeOutput, x.rl("reactor_reaction/" + x.path(output.getItem())
                + (applyInput ? "_from_" + x.path(input.getItems()[0].getItem()) : "")));
    }

    @Override
    public void save(RecipeOutput recipeOutput) {
        save(recipeOutput, false);
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
