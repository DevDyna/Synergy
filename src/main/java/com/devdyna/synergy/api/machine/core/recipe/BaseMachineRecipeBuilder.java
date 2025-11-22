package com.devdyna.synergy.api.machine.core.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.Map;

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
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings({ "unused", "null" })
public abstract class BaseMachineRecipeBuilder implements RecipeBuilder {

    protected Map<String, Criterion<?>> criteria;

    protected abstract Recipe<?> createRecipe();

    @Override
    public void save(RecipeOutput o, ResourceLocation i) {
        if (this.criteria.isEmpty())
            throw new IllegalStateException("Missing/Null Criteria " + String.valueOf(i));
        Advancement.Builder adv = o.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(i))
                .rewards(AdvancementRewards.Builder.recipe(i))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(adv::addCriterion);
        o.accept(i, createRecipe(),
                adv.build(i.withPrefix("recipes/" +
                        RecipeCategory.MISC.getFolderName() + "/")));
    }
}
