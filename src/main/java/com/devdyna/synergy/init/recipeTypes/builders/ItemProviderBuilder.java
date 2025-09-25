package com.devdyna.synergy.init.recipeTypes.builders;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.init.recipeTypes.type.ItemProviderRecipe;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class ItemProviderBuilder implements RecipeBuilder {

    private BlockState core;
    private BlockState below = Blocks.AIR.defaultBlockState();
    private BlockState left = Blocks.AIR.defaultBlockState();
    private BlockState right = Blocks.AIR.defaultBlockState();
    private ItemStack output;

    private final Map<String, Criterion<?>> criteria;

    public ItemProviderBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ItemProviderBuilder of() {
        return new ItemProviderBuilder();
    }

    public ItemProviderBuilder core(BlockState b) {
        this.core = b;
        return this;
    }

    public ItemProviderBuilder below(@Nullable BlockState b) {
        this.below = b;
        return this;
    }

    public ItemProviderBuilder left(@Nullable BlockState b) {
        this.left = b;
        return this;
    }

    public ItemProviderBuilder right(@Nullable BlockState b) {
        this.right = b;
        return this;
    }

    public ItemProviderBuilder core(Block b) {
        return core(b.defaultBlockState());
    }

    public ItemProviderBuilder below(@Nullable Block b) {
        return below(b.defaultBlockState());
    }

    public ItemProviderBuilder left(@Nullable Block b) {
        return left(b.defaultBlockState());
    }

    public ItemProviderBuilder right(@Nullable Block b) {
        return right(b.defaultBlockState());
    }

    public ItemProviderBuilder output(ItemStack b) {
        this.output = b;
        return this;
    }

    public ItemProviderBuilder output(Item b) {
        return output(x.item(b, 1));
    }

    public ItemProviderBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(x.item(core).getItem()));
    }

    public ItemProviderBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public ItemProviderBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return x.item(this.core).getItem();
    }

    public void save(RecipeOutput recipeOutput, String extra) {

        this.save(recipeOutput,
                x.rl("provider/item/" +
                        (x.path(output) == x.path(core)
                                ? x.path(core)
                                : x.path(output) + "_from_" + x.path(core))
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
        var shapelessrecipe = new ItemProviderRecipe(below, core, left, right, output);
        pRecipeOutput.accept(pId, shapelessrecipe,
                advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }
}
