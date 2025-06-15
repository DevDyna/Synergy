package com.devdyna.synergy.utils;

import net.minecraft.world.item.Item;

public class ArrayUtils {

    public static Item[] concatMixed(Item basic, Item... dynamic) {
        Item[] combined = new Item[1 + dynamic.length];
        combined[0] = basic;
        System.arraycopy(dynamic, 0, combined, 1, dynamic.length);
        return combined;
    }
}
