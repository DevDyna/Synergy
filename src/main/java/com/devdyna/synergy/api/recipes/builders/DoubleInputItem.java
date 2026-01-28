package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.utils.x;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface DoubleInputItem<BUILDER extends BaseRecipeBuilder> extends InputItem<BUILDER> {

    abstract BUILDER inputs(SizedIngredient right, SizedIngredient left);

    default BUILDER inputs(Ingredient right, Ingredient left) {
        return inputs(x.itemSized(right), x.itemSized(left));
    }
    default BUILDER inputs(Item right, Item left) {
        return inputs(x.ingredient(right), x.ingredient(left));
    }

    default BUILDER inputs(DeferredHolder<Item, Item> right, DeferredHolder<Item, Item> left) {
        return inputs(right.get(), left.get());
    }

    default BUILDER inputs(TagKey<Item> right, TagKey<Item> left) {
        return inputs(x.ingredient(right), x.ingredient(left));
    }

    default BUILDER inputs(TagKey<Item> right, Item left) {
        return inputs(x.ingredient(right), x.ingredient(left));
    }

    default BUILDER inputs(Item right, TagKey<Item> left) {
        return inputs(x.ingredient(right), x.ingredient(left));
    }

}
