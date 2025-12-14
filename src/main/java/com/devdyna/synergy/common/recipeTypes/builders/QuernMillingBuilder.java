package com.devdyna.synergy.common.recipeTypes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.IngredientUtils;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipeTypes.type.QuernMillingRecipe;

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
public class QuernMillingBuilder implements RecipeBuilder {

    private Ingredient input;
    private int tick;
    private ItemStack output;

    private final Map<String, Criterion<?>> criteria;

    private QuernMillingBuilder() {
        this.tick = 60;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static QuernMillingBuilder of() {
        return new QuernMillingBuilder();
    }

    public QuernMillingBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public QuernMillingBuilder input(Item input) {
        return input(x.ingredient(input));
    }

    public QuernMillingBuilder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public QuernMillingBuilder input(TagKey<Item> input) {
        return input(x.ingredient(input));
    }

    public QuernMillingBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public QuernMillingBuilder delay(int tick) {
        this.tick = tick;
        return this;
    }

    public QuernMillingBuilder output(Item output) {
        return output(x.item(output));
    }

    public QuernMillingBuilder output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    public QuernMillingBuilder output(Item output, int count) {
        return output(x.item(output, count));
    }

    public QuernMillingBuilder output(DeferredHolder<Item, Item> output, int count) {
        return output(output.get(), count);
    }

    public QuernMillingBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(IngredientUtils.getItemLike(input)));
    }

    public QuernMillingBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public QuernMillingBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {
        this.save(recipeOutput, x.rl("quern/" + x.path(output.getItem())
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
        QuernMillingRecipe shapelessrecipe = new QuernMillingRecipe(input, output, tick);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
