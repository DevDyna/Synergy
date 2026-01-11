package com.devdyna.synergy.api.recipes.builders;

import java.util.Arrays;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unchecked")
public interface CatalystItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

    abstract BUILDER consumeCatalyst();

    abstract BUILDER catalyst(Ingredient catalyst);

    default BUILDER catalyst(ItemStack... catalyst) {
        return catalyst(Ingredient.of(catalyst));
    }

    default BUILDER catalyst(DeferredHolder<Item, Item>... catalyst) {
        return catalyst((Item[]) Arrays.asList(catalyst).stream().map(DeferredHolder::get).toArray());
    }

    default BUILDER catalyst(Item... catalyst) {
        return catalyst(Ingredient.of(catalyst));
    }

    default BUILDER catalyst(ItemLike... catalyst) {
        return catalyst(Ingredient.of(catalyst));
    }

    default BUILDER catalyst(TagKey<Item> catalyst) {
        return catalyst(Ingredient.of(catalyst));
    }

}
