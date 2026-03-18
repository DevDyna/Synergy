package com.devdyna.synergy.api.recipes.builders.api;

import java.util.Map;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings("null")
public abstract class BaseRecipeBuilder {

    protected Map<String, Criterion<?>> criteria;

    public abstract Recipe<?> createRecipe();

    public abstract ResourceLocation getSuffix(String extra);

    public void save(RecipeOutput recipeOutput) {
        save(recipeOutput, "");
    }

    public void save(RecipeOutput o, String extra) {
        this.save(o, getSuffix(extra));
    }

    public void save(RecipeOutput c, ResourceLocation pId) {
        if (this.criteria.isEmpty())
            throw new IllegalStateException("Recipe unobtainable : " + String.valueOf(pId));
        Advancement.Builder adv = c.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(adv::addCriterion);
        c.accept(pId, createRecipe(),
                adv.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }

}
