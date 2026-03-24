package com.devdyna.synergy.common.recipes.resource_info;

import static com.devdyna.synergy.Main.ID;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Nullable;

import com.devdyna.synergy.api.recipes.builders.ItemAttach;
import com.devdyna.synergy.api.recipes.builders.api.BaseRecipeBuilder;
import com.devdyna.synergy.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings("null")
public class ResourceInfoBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.NoItemCount<ResourceInfoBuilder>, ItemAttach.Output.ListedOutputItemStack<ResourceInfoBuilder> {

    private Ingredient input;
    private List<ItemStack> output;

    public ResourceInfoBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ResourceInfoBuilder of() {
        return new ResourceInfoBuilder();
    }

    public ResourceInfoBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public ResourceInfoBuilder output(List<ItemStack> output) {
        this.output = output;
        return this;
    }

    public ResourceInfoBuilder unlockedBy() {
        return unlockedBy(ID, InventoryChangeTrigger.TriggerInstance
                .hasItems(this.input.getItems()[0].getItem()));
    }

    public ResourceInfoBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public ResourceInfoBuilder group(@Nullable String groupName) {
        return this;
    }

    public Item getResult() {
        return this.output.get(0).getItem();
    }

    @Override
    public ResourceLocation getSuffix(String extra) {
        return x.rl("resource_info/" + x.id(input)
                + extra);
    }

    @Override
    public Recipe<?> createRecipe() {
        return new ResourceInfoRecipe(input, output);
    }

    @Override
    public ResourceInfoBuilder getBuilder() {
        return this;
    }

}
