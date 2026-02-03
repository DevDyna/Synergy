package com.devdyna.synergy.api.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class StringUtil {
    public static String getModName(String traslationName) {
        String[] parts = traslationName.split("\\.");
        if (parts.length >= 2) {
            return parts[1];
        } else {
            return null;
        }
    }

    // example string -> String
    public static String nameCapitalized(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static NumberFormat getFormat(){
        NumberFormat format = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
        return format;
    }

    public static NumberFormat getFormatNoRound(){
        NumberFormat format = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        format.setMaximumFractionDigits(1);
        format.setRoundingMode(RoundingMode.DOWN);
        return format;
    }

    public static float cut(float v,float p){
       return Math.round(v * p) / p;
    }
    public static float cut(float v){
       return cut(v,100f);
    }

}
