package com.devdyna.synergy.init.machine.core.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.MachineType;
import com.devdyna.synergy.init.machine.core.BaseMachineBE;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;
import com.devdyna.synergy.init.machine.core.BaseMachineMenu;
import com.devdyna.synergy.init.machine.macerator.recipe.MaceratorRecipeBuilder;
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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "unused", "null" })
public abstract class BaseMachineRecipeBuilder implements RecipeBuilder {

    protected abstract Recipe<?> createRecipe();

    public abstract MachineType<? extends BaseMachineBlock, ? extends BaseMachineBE, ? extends BaseMachineMenu, ? extends BaseMachineRecipeType<? extends RecipeInput>> getMachine();

    protected Map<String, Criterion<?>> criteria;

    protected int ticks = 60;
    protected int energy = 10;

    protected Ingredient input;

    protected ItemStack output;

    protected ItemStack secondary = ItemStack.EMPTY;

    protected float chance = 1f;

    public BaseMachineRecipeBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public BaseMachineRecipeBuilder input(Item input) {
        return input(x.ingredient(input));
    }

    public BaseMachineRecipeBuilder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public BaseMachineRecipeBuilder input(TagKey<Item> input) {
        return input(x.ingredient(input));
    }

    public BaseMachineRecipeBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public BaseMachineRecipeBuilder secondary(ItemStack secondary, float chance) {
        this.secondary = secondary;
        this.chance = chance;
        return this;
    }

    /**
     * default chance of success -> 100%
     */
    public BaseMachineRecipeBuilder secondary(ItemStack secondary) {
        this.secondary = secondary;
        return this;
    }

    /**
     * default value -> 60t
     */
    public BaseMachineRecipeBuilder delay(int ticks) {
        this.ticks = ticks;
        return this;
    }

    /**
     * default value -> 1kfe | 1000fe
     */
    public BaseMachineRecipeBuilder energy(int energy) {
        this.energy = energy;
        return this;
    }

    /**
     * secondary recipe output chance to success
     */
    public BaseMachineRecipeBuilder chance(float chance) {
        this.chance = chance;
        return this;
    }

    public BaseMachineRecipeBuilder output(Item output) {
        return output(x.item(output));
    }

    public BaseMachineRecipeBuilder output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    public BaseMachineRecipeBuilder output(Item output, int count) {
        return output(x.item(output, count));
    }

    public BaseMachineRecipeBuilder output(DeferredHolder<Item, Item> output, int count) {
        return output(output.get(), count);
    }

    public BaseMachineRecipeBuilder secondary(DeferredHolder<Item, Item> secondary) {
        return secondary(secondary.get());
    }

    public BaseMachineRecipeBuilder secondary(Item secondary) {
        return secondary(x.item(secondary));
    }

    public BaseMachineRecipeBuilder secondary(Item secondary, int count) {
        return secondary(x.item(secondary, count));
    }

    public BaseMachineRecipeBuilder secondary(DeferredHolder<Item, Item> secondary, int count) {
        return secondary(secondary.get(), count);
    }

    public BaseMachineRecipeBuilder secondary(Item secondary, float chance) {
        return secondary(secondary, chance);
    }

    public BaseMachineRecipeBuilder secondary(DeferredHolder<Item, Item> secondary, float chance) {
        return secondary(secondary.get(), chance);
    }

    public BaseMachineRecipeBuilder secondary(Item secondary, int count, float chance) {
        return secondary(x.item(secondary, count), chance);
    }

    public BaseMachineRecipeBuilder secondary(DeferredHolder<Item, Item> secondary, int count, float chance) {
        return secondary(secondary.get(), count, chance);
    }

    public BaseMachineRecipeBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Arrays.stream(this.input.getItems())
                        .map(ItemStack::getItem)
                        .toArray(Item[]::new)));
    }

    public BaseMachineRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public BaseMachineRecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput o, String extra) {
        this.save(o, x.rl(getMachine().id() + "/" + x.path(output.getItem())
                + extra));
    }

    @Override
    public void save(RecipeOutput o) {
        save(o, "");
    }

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
