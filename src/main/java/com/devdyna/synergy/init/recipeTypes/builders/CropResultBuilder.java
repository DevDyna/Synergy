package com.devdyna.synergy.init.recipeTypes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.recipeTypes.type.CropResultRecipe;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class CropResultBuilder implements RecipeBuilder {

    private ItemStack input;
    private List<Ingredient> output;
    private final Map<String, Criterion<?>> criteria;

    public CropResultBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CropResultBuilder of() {
        return new CropResultBuilder();
    }

    public CropResultBuilder input(ItemStack input) {
        this.input = input;
        return this;
    }

    public CropResultBuilder input(Item input) {
        return input(x.item(input));
    }

    public CropResultBuilder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public CropResultBuilder output(List<Ingredient> output) {
        this.output = output;
        return this;
    }

    public CropResultBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(this.input.getItem()));
    }

    public CropResultBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public CropResultBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.get(0).getItems()[0].getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {
        this.save(recipeOutput, x.rl("jei/crop_result/" + x.path(input.getItem())
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
