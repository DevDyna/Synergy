package com.devdyna.synergy.api.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.ItemStack;

public class IOUtils {
    /**
     * unify all dropped items
     */
    public static ArrayList<ItemStack> unifyDrops(List<ItemStack> items) {
        ArrayList<ItemStack> newItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {

            var check = false;
            int index = -1;
            for (ItemStack itemStack : newItems) {
                if (itemStack.getItem() == items.get(i).getItem()) {
                    if (itemStack.getCount() >= 64)
                        continue;
                    index = newItems.indexOf(itemStack);
                    check = true;
                    break;
                }
            }

            if (check) {

                newItems.set(index,
                        new ItemStack(newItems.get(index).getItem(),
                                newItems.get(index).getCount() + 1));

            } else {

                newItems.add(items.get(i));

            }

        }
        return newItems;
    }
}
