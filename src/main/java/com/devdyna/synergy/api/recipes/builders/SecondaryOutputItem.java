package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.utils.x;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface SecondaryOutputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

    abstract BUILDER secondary(ItemStack output, float chance);

    /**
     * default chance of success -> 100%
     */
    default BUILDER secondary(ItemStack secondary) {
        return secondary(secondary, 1f);
    }

    default BUILDER secondary(DeferredHolder<Item, Item> secondary) {
        return secondary(secondary.get());
    }

    default BUILDER secondary(Item secondary) {
        return secondary(x.item(secondary));
    }

    default BUILDER secondary(Item secondary, int count) {
        return secondary(x.item(secondary, count));
    }

    default BUILDER secondary(DeferredHolder<Item, Item> secondary, int count) {
        return secondary(secondary.get(), count);
    }

    default BUILDER secondary(Item secondary, float chance) {
        return secondary(secondary, chance);
    }

    default BUILDER secondary(DeferredHolder<Item, Item> secondary, float chance) {
        return secondary(secondary.get(), chance);
    }

    default BUILDER secondary(Item secondary, int count, float chance) {
        return secondary(x.item(secondary, count), chance);
    }

    default BUILDER secondary(DeferredHolder<Item, Item> secondary, int count, float chance) {
        return secondary(secondary.get(), count, chance);
    }
}
