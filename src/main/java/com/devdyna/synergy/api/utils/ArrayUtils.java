package com.devdyna.synergy.api.utils;

import java.util.*;

public class ArrayUtils {

    public static <T> T[] concat(T[] a1, T[] a2) {
        List<T> list = new ArrayList<>(Arrays.asList(a1));
        list.addAll(Arrays.asList(a2));
        return list.toArray(size -> Arrays.copyOf(a1, size));
    }
}
