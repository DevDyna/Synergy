package com.devdyna.synergy.common.recipes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.DryableBricksRecipe;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class DryableBricksBuilder implements RecipeBuilder {

    private ItemStack input;
    private BlockState block;
    private ItemStack output;
    private final Map<String, Criterion<?>> criteria;

    public DryableBricksBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static DryableBricksBuilder of() {
        return new DryableBricksBuilder();
    }

    public DryableBricksBuilder input(ItemStack input) {
        this.input = input;
        return this;
    }

    public DryableBricksBuilder block(BlockState block) {
        this.block = block;
        return this;
    }

    public DryableBricksBuilder block(Block block) {
        return block(block.defaultBlockState());
    }

    public DryableBricksBuilder input(Item input) {
        return input(x.item(input));
    }

    public DryableBricksBuilder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public DryableBricksBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public DryableBricksBuilder output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    public DryableBricksBuilder output(Item output) {
        return output(x.item(output));
    }

    public DryableBricksBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(this.input.getItem()));
    }

    public DryableBricksBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public DryableBricksBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {
        this.save(recipeOutput, x.rl("dryable_bricks/" + x.path(output)
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
        DryableBricksRecipe shapelessrecipe = new DryableBricksRecipe(input, block, output);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
