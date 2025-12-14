package com.devdyna.synergy.api.recipes.builders;

import com.devdyna.synergy.api.utils.x;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface SimpleOutputItem<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

    abstract BUILDER output(ItemStack output);

    default BUILDER output(Item output) {
        return output(x.item(output));
    }

    default BUILDER output(ItemLike output) {
        return output(x.item(output));
    }

    default BUILDER output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    default BUILDER output(Item output, int count) {
        return output(x.item(output, count));
    }

    default BUILDER output(DeferredHolder<Item, Item> output, int count) {
        return output(output.get(), count);
    }
}
