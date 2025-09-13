package com.devdyna.synergy.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unchecked")
public class ClazzUtil {

    /**
     * Dont use on LootTableProviders
     */
    public static List<DeferredHolder<Item, ?>> getAllzItems() {
        List<DeferredHolder<Item, ?>> items = new ArrayList<>();

        for (Field field : zItems.class.getDeclaredFields()) {
            try {
                if (DeferredHolder.class.isAssignableFrom(field.getType())) {
                    Object value = field.get(null);
                    if (value instanceof DeferredHolder<?, ?> holder) {
                        if (holder.value() instanceof Item) {
                            DeferredHolder<Item, ?> itemHolder = (DeferredHolder<Item, ?>) holder;
                            items.add(itemHolder);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    /**
     * Dont use on LootTableProviders
     */
    public static List<DeferredHolder<Block, ?>> getAllzBlocks() {
        List<DeferredHolder<Block, ?>> blocks = new ArrayList<>();

        for (Field field : zItems.class.getDeclaredFields()) {
            try {
                if (DeferredHolder.class.isAssignableFrom(field.getType())) {
                    Object value = field.get(null);
                    if (value instanceof DeferredHolder<?, ?> holder) {
                        if (holder.value() instanceof Block) {
                            DeferredHolder<Block, ?> blockHolder = (DeferredHolder<Block, ?>) holder;
                            blocks.add(blockHolder);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return blocks;
    }

}
