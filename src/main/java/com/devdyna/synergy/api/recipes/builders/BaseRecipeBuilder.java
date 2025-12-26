package com.devdyna.synergy.api.recipes.builders;

import java.util.Map;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings("null")
public abstract class BaseRecipeBuilder implements RecipeBuilder {

    protected Map<String, Criterion<?>> criteria;

    public abstract Recipe<?> createRecipe();

    public abstract String getSuffix(String extra);

    @Override
    public void save(RecipeOutput recipeOutput) {
        save(recipeOutput, "");
    }

    public void save(RecipeOutput o, String extra) {
        this.save(o,ResourceKey.create(Registries.RECIPE,BuiltInRegistries.ITEM.getKey(getResult().asItem()).withSuffix(getSuffix(extra))));
    }



    @Override
    public void save(RecipeOutput pRecipeOutput, ResourceKey<Recipe<?>> pId) {
        if (this.criteria.isEmpty())
            throw new IllegalStateException("Missing/Null Criteria " + String.valueOf(pId));
        Advancement.Builder advancement$builder = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        pRecipeOutput.accept(pId, createRecipe(),
                advancement$builder.build(pId.identifier().withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }

}
