package com.devdyna.synergy.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.devdyna.synergy.init.types.zItems;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ClazzUtil {
    public static List<DeferredHolder<Item, ?>> getAllzItems() {
        List<DeferredHolder<Item, ?>> items = new ArrayList<>();

        for (Field field : zItems.class.getDeclaredFields()) {
            try {
                // Only pick DeferredHolder<Item, ?>
                if (DeferredHolder.class.isAssignableFrom(field.getType())) {
                    Object value = field.get(null); // static field → null instance
                    if (value instanceof DeferredHolder<?, ?> holder) {
                        // Ensure it's an Item DeferredHolder
                        if (holder.value() instanceof Item) {
                            @SuppressWarnings("unchecked")
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
}
