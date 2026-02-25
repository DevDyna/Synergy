package com.devdyna.synergy.api.utils;

import java.util.*;
import java.util.stream.Stream;

public class ArrayUtils {

    public static <T> T[] concat(T[] a1, T[] a2) {
        List<T> list = new ArrayList<>(Arrays.asList(a1));
        list.addAll(Arrays.asList(a2));
        return list.toArray(size -> Arrays.copyOf(a1, size));
    }

    public static <T> List<T> concat(T a1, List<T> a2) {
        return Stream.concat(Stream.of(a1), a2.stream()).toList();
    }

    public static <T> List<T> concat(T a1, Stream<T> a2) {
        return Stream.concat(Stream.of(a1), a2).toList();
    }

    public static <T> List<T> concat(List<T> a1, T a2) {
        return concat(a2, a1);
    }

    public static <T> List<T> concat(Stream<T> a1, T a2) {
        return concat(a2, a1);
    }

}
