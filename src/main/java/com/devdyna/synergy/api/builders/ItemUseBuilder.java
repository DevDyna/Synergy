package com.devdyna.synergy.api.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.recipeTypes.type.ItemUseRecipe;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("null")
public class ItemUseBuilder implements RecipeBuilder {

    private Ingredient inputItem;
    private BlockState inputState;
    private BlockState outputState;
    private final Map<String, Criterion<?>> criteria;

    public ItemUseBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ItemUseBuilder of() {
        return new ItemUseBuilder();
    }

    public ItemUseBuilder inputItem(ItemStack i) {
        this.inputItem = x.ingredient(i);
        return this;
    }

    public ItemUseBuilder inputItem(Item input) {
        return inputItem(x.item(input));
    }

    public ItemUseBuilder inputItem(DeferredHolder<Item, Item> input) {
        return inputItem(input.get());
    }

    public ItemUseBuilder inputBlock(BlockState b) {
        this.inputState = b;
        return this;
    }

    public ItemUseBuilder outputBlock(BlockState b) {
        this.outputState = b;
        return this;
    }

       public ItemUseBuilder inputBlock(Block b) {
        return inputBlock(b.defaultBlockState());
    }

    public ItemUseBuilder outputBlock(Block b) {
        return outputBlock(b.defaultBlockState());
    }

       public ItemUseBuilder inputBlock(DeferredHolder<Block, ?> b) {
        return inputBlock(b.get());
    }

    public ItemUseBuilder outputBlock(DeferredHolder<Block, ?> b) {
        return outputBlock(b.get());
    }

    public ItemUseBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Arrays.stream(this.inputItem.getItems())
                        .map(ItemStack::getItem)
                        .toArray(Item[]::new)));
    }

    public ItemUseBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public ItemUseBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return x.item(this.outputState).getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {
        this.save(recipeOutput,
                x.rl("item_use/" +
                        x.path(outputState.getBlock())
                        + "_from_" +
                        x.path(inputState.getBlock())
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
        ItemUseRecipe shapelessrecipe = new ItemUseRecipe(inputItem, inputState, outputState);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
