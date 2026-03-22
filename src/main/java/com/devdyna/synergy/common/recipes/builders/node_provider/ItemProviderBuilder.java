package com.devdyna.synergy.common.recipes.builders.node_provider;

import java.util.LinkedHashMap;

import com.devdyna.synergy.api.recipes.builders.BaseProviderBuilder;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.common.recipes.type.node_providers.ItemProviderRecipe;

import net.minecraft.advancements.Criterion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

@SuppressWarnings({ "null" })
public class ItemProviderBuilder extends BaseProviderBuilder<ItemProviderBuilder> {

    private ItemStack output;

    public ItemProviderBuilder() {
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static ItemProviderBuilder of() {
        return new ItemProviderBuilder();
    }

    public ItemProviderBuilder output(ItemStack b) {
        this.output = b;
        return this;
    }

    public ItemProviderBuilder output(Item b) {
        return output(x.item(b, 1));
    }

    public String getOutputPath() {
        return x.path(output);
    }

    public String getFolderPath() {
        return "item";
    }

    @Override
    public Recipe<?> createRecipe() {
        return new ItemProviderRecipe<>(pattern, output);
    }

    @Override
    protected ItemProviderBuilder getBuilder() {
        return this;
    }
}
