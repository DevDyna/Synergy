package com.devdyna.synergy.init.recipeTypes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

import com.devdyna.synergy.init.recipeTypes.type.UrnRitualRecipe;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "null" })
public class UrnRitualBuilder implements RecipeBuilder {

    private List<Ingredient> input;
    private ItemStack output;

    private final Map<String, Criterion<?>> criteria;

    private UrnRitualBuilder() {
        this.input = new ArrayList<>();
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static UrnRitualBuilder of() {
        return new UrnRitualBuilder();
    }

    public UrnRitualBuilder add(Ingredient input) {
        this.input.add(input);
        return this;
    }

    public UrnRitualBuilder add(Item input) {
        return add(x.ingredient(input));
    }

    public UrnRitualBuilder add(DeferredHolder<Item, Item> input) {
        return add(input.get());
    }

    public UrnRitualBuilder add(TagKey<Item> input) {
        return add(x.ingredient(input));
    }

    public UrnRitualBuilder add(ItemLike input) {
        return add(x.ingredient(input));
    }

    public UrnRitualBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public UrnRitualBuilder output(Item output) {
        return output(x.item(output));
    }

    public UrnRitualBuilder output(ItemLike output) {
        return output(x.item(output));
    }

    public UrnRitualBuilder output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    public UrnRitualBuilder output(Item output, int count) {
        return output(x.item(output, count));
    }

    public UrnRitualBuilder output(DeferredHolder<Item, Item> output, int count) {
        return output(output.get(), count);
    }

    public UrnRitualBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(this.input.stream()
                        .flatMap(i -> Arrays.stream(i.getItems())
                                .map(ItemStack::getItem))
                        .toArray(Item[]::new)));
    }

    public UrnRitualBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public UrnRitualBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {
        this.save(recipeOutput, x.rl("urn_ritual/" + x.path(output.getItem())
                + extra));
    }

    @Override
    public void save(RecipeOutput recipeOutput) {
        save(recipeOutput, "");
    }

    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        if (this.criteria.isEmpty())
            throw new IllegalStateException("Missing/Null Criteria " + String.valueOf(pId));
        Advancement.Builder advancement = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        UrnRitualRecipe shapelessrecipe = new UrnRitualRecipe(input, output);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }

    

}
