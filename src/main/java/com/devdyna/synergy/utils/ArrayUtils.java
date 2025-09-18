package com.devdyna.synergy.utils;

import java.util.*;

public class ArrayUtils {

    public static String[] concat(String[] arr1, String[] arr2) {
        List<String> list = new ArrayList<>(Arrays.asList(arr1));
        list.addAll(Arrays.asList(arr2));
        return list.toArray(new String[0]);
    }
}
