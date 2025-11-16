package com.devdyna.synergy.api.machine.core.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

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
public class builder implements RecipeBuilder {

    private Ingredient input;
    private int tick;
    private int energy;
    private ItemStack output;

    private final Map<String, Criterion<?>> criteria;

    private builder() {
        this.tick = 60;
        this.energy = 1000;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static builder of() {
        return new builder();
    }

    public builder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public builder input(Item input) {
        return input(x.ingredient(input));
    }

    public builder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public builder input(TagKey<Item> input) {
        return input(x.ingredient(input));
    }

    public builder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public builder delay(int tick) {
        this.tick = tick;
        return this;
    }

    public builder energy(int energy) {
        this.energy = energy;
        return this;
    }

    public builder output(Item output) {
        return output(x.item(output));
    }

    public builder output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    public builder output(Item output, int count) {
        return output(x.item(output, count));
    }

    public builder output(DeferredHolder<Item, Item> output, int count) {
        return output(output.get(), count);
    }

    public builder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Arrays.stream(this.input.getItems())
                        .map(ItemStack::getItem)
                        .toArray(Item[]::new)));
    }

    public builder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public builder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {
        this.save(recipeOutput, x.rl("machine/" + x.path(output.getItem())
                + extra));
    }

    @Override
    public void save(RecipeOutput recipeOutput) {
        save(recipeOutput, "");
    }

    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        if (this.criteria.isEmpty())
            throw new IllegalStateException("Missing/Null Criteria " + String.valueOf(pId));
        Advancement.Builder advancement$builder = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        recipetype shapelessrecipe = new recipetype(input, output, tick, energy);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
