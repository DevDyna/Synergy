package com.devdyna.synergy.api.recipes.builders;

import org.checkerframework.framework.qual.Unused;

import com.devdyna.synergy.api.utils.x;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface DoubleInputItem<BUILDER extends BaseRecipeBuilder> extends SimpleInputItem<BUILDER> {

    abstract BUILDER inputs(Ingredient right, Ingredient left);

    /**
     * USE <code>input(Ingredient right, Ingredient left)</code> intend of this
     * 
     * @deprecated
     */
    @Deprecated(forRemoval = false, since = "''USE input(Ingredient right, Ingredient left)''")
    default BUILDER input(Ingredient input) {
        return null;
    }

    /**
     * USE <code>input(Ingredient right, Ingredient left)</code> intend of this
     * 
     * @deprecated
     */
    @Deprecated(forRemoval = false, since = "''USE input(Ingredient right, Ingredient left)''")
    default BUILDER catalyst(Ingredient input) {
        return null;
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
}
