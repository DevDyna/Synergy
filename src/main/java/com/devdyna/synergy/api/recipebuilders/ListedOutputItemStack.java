package com.devdyna.synergy.api.recipebuilders;

import java.util.List;

import com.devdyna.synergy.api.utils.x;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unchecked")
public interface ListedOutputItemStack<BUILDER extends BaseRecipeBuilder> extends BaseItemAttach<BUILDER> {

    abstract BUILDER output(List<ItemStack> output);

    default BUILDER output(ItemStack output) {
        return output(List.of(output));
    }

    default BUILDER output(ItemStack... output) {
        return output(List.of(output));
    }

    default BUILDER output(Item output) {
        return output(x.item(output));
    }

    default BUILDER output(Item... output) {
        return output(List.of(output).stream().map(i -> x.item(i)).toList());
    }

    default BUILDER output(DeferredHolder<Item, Item> output) {
        return output(output.get());
    }

    default BUILDER output(ItemLike output) {
        return output(x.item(output));
    }

    default BUILDER output(DeferredHolder<Item, Item>... output) {
        return output(List.of(output).stream().map(DeferredHolder::get).map(ItemStack::new).toList());
    }

    default BUILDER output(ItemLike... output) {
        return output(List.of(output).stream().map(ItemStack::new).toList());
    }
}
