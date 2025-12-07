package com.devdyna.synergy.init.machine.macerator.recipe;

import static com.devdyna.synergy.Main.ID;

import java.util.Arrays;
import java.util.LinkedHashMap;
import javax.annotation.Nullable;

import com.devdyna.synergy.init.machine.core.recipe.BaseMachineRecipeBuilder;
import com.devdyna.synergy.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings({ "null" })
public class MaceratorRecipeBuilder extends BaseMachineRecipeBuilder {

    private Ingredient input;
    private int tick;
    private int energy;
    private ItemStack output;
    private ItemStack secondary = null;

    private MaceratorRecipeBuilder() {
        this.tick = 60;
        this.energy = 1000;
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static MaceratorRecipeBuilder of() {
        return new MaceratorRecipeBuilder();
    }

    public MaceratorRecipeBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public MaceratorRecipeBuilder input(Item input) {
        return input(x.ingredient(input));
    }

    public MaceratorRecipeBuilder input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    public MaceratorRecipeBuilder input(TagKey<Item> input) {
        return input(x.ingredient(input));
    }

    public MaceratorRecipeBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    public MaceratorRecipeBuilder secondary(ItemStack secondary) {
        this.secondary = secondary;
        return this;
    }

    public MaceratorRecipeBuilder delay(int tick) {
        this.tick = tick;
        return this;
    }

    public MaceratorRecipeBuilder energy(int energy) {
        this.energy = energy;
        return this;
    }

    public MaceratorRecipeBuilder output(Item output) {
        return output(x.item(output));
    }

    public MaceratorRecipeBuilder output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    public MaceratorRecipeBuilder output(Item output, int count) {
        return output(x.item(output, count));
    }

    public MaceratorRecipeBuilder output(DeferredHolder<Item, Item> output, int count) {
        return output(output.get(), count);
    }

      public MaceratorRecipeBuilder secondary(Item secondary) {
        return secondary(x.item(secondary));
    }

        public MaceratorRecipeBuilder secondary(DeferredHolder<Item, Item> secondary) {
        return secondary(secondary.get());
    }

    public MaceratorRecipeBuilder secondary(Item secondary, int count) {
        return secondary(x.item(secondary, count));
    }

    public MaceratorRecipeBuilder secondary(DeferredHolder<Item, Item> secondary, int count) {
        return secondary(secondary.get(), count);
    }

    public MaceratorRecipeBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(Arrays.stream(this.input.getItems())
                        .map(ItemStack::getItem)
                        .toArray(Item[]::new)));
    }

    public MaceratorRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public MaceratorRecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.getItem();
    }

    public void save(RecipeOutput o, String extra) {
        this.save(o, x.rl("macerator/" + x.path(output.getItem())
                + extra));
    }

    @Override
    public void save(RecipeOutput o) {
        save(o, "");
    }

    @Override
    protected Recipe<?> createRecipe() {
        return new MaceratorRecipeType(input, output, secondary, tick, energy);
    }
}
