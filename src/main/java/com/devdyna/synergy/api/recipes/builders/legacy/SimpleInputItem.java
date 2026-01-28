package com.devdyna.synergy.api.recipes.builders.legacy;

import com.devdyna.synergy.api.recipes.builders.BaseRecipeBuilder;
import com.devdyna.synergy.api.recipes.builders.BuilderAttach;
import com.devdyna.synergy.api.utils.x;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface SimpleInputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

    abstract BUILDER input(Ingredient input);

    default BUILDER input(Item input) {
        return input(x.ingredient(input));
    }

    default BUILDER input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    default BUILDER input(TagKey<Item> input) {
        return input(x.ingredient(input));
    }

}
