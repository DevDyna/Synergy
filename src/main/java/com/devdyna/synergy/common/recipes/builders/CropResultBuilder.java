package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.CropResultRecipe;

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
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class CropResultBuilder implements RecipeBuilder {

    private Ingredient input;
    private List<ItemStack> output;
    private final Map<String, Criterion<?>> criteria;

    public CropResultBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CropResultBuilder of() {
        return new CropResultBuilder();
    }

    public CropResultBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public CropResultBuilder input(Item input) {
        return input(x.ingredient(input));
    }

    public CropResultBuilder input(TagKey<Item> input) {
        return input(x.ingredient(input));
    }

    public CropResultBuilder input(ItemLike input) {
        return input(x.ingredient(input));
    }

    public CropResultBuilder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public CropResultBuilder output(List<ItemStack> output) {
        this.output = output;
        return this;
    }

    public CropResultBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(this.input.getItems()[0].getItem()));
    }

    public CropResultBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public CropResultBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.get(0).getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {
        this.save(recipeOutput, x.rl("jei/crop_result/"+x.path(output.getFirst())
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
        CropResultRecipe shapelessrecipe = new CropResultRecipe(input, output);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
