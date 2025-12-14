package com.devdyna.synergy.common.recipeTypes.builders.base;

import com.devdyna.synergy.utils.x;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface ListedIngredientInput<BUILDER extends BaseRecipeBuilder> extends BaseItemAttach<BUILDER> {

    abstract BUILDER add(Ingredient input);

    default BUILDER add(Item input) {
        return add(x.ingredient(input));
    }

    default BUILDER add(DeferredHolder<Item, Item> input) {
        return add(input.get());
    }

    default BUILDER add(TagKey<Item> input) {
        return add(x.ingredient(input));
    }

    default BUILDER add(ItemLike input) {
        return add(x.ingredient(input));
    }
}
