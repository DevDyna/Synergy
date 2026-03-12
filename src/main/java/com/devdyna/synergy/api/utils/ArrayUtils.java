package com.devdyna.synergy.api.utils;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class ArrayUtils {

    public static <T> T[] concat(T[] a1, T[] a2) {
        List<T> list = new ArrayList<>(Arrays.asList(a1));
        list.addAll(Arrays.asList(a2));
        return list.toArray(size -> Arrays.copyOf(a1, size));
    }

    public static <T> List<T> concat(List<T> a1, T... a2) {
        return Stream.concat(Stream.of(a2), a1.stream()).toList();
    }

    public static <T> List<T> concat(Stream<T> a1, T... a2) {
        return Stream.concat(Stream.of(a2), a1).toList();
    }

    /**
     * @param l initial array
     * @param m map to convert things
     * @param b builder used to create the array ex. Integer[]::new
     */
    public static <T, R> R[] map(T[] l, Function<? super T, ? extends R> m, IntFunction<R[]> b) {
        return Arrays.asList(l).stream().map(m).toArray(b::apply);
    }

}
