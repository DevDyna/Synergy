package com.devdyna.synergy.utils;

public class StringUtil {
    public static String getModName(String traslationName) {
        String[] parts = traslationName.split("\\.");
        if (parts.length >= 2) {
            return parts[1];
        } else {
            return null;
        }
    }

    //example string -> String
    public static String nameCapitalized(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
