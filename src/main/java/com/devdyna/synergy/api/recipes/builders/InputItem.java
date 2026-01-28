package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.utils.x;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface InputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

    abstract BUILDER input(SizedIngredient input);


    default BUILDER input(Item input) {
        return input(x.itemSized(input));
    }

    default BUILDER input(DeferredHolder<Item, Item> input) {
        return input(input.get());
    }

    default BUILDER input(TagKey<Item> input) {
        return input(x.itemSized(input));
    }

    default BUILDER input(Item input,int c) {
        return input(x.itemSized(input,c));
    }

    default BUILDER input(DeferredHolder<Item, Item> input,int c) {
        return input(input.get(),c);
    }

    default BUILDER input(TagKey<Item> input,int c) {
        return input(x.itemSized(input,c));
    }

}
