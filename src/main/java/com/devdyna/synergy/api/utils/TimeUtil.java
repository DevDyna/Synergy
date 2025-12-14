package com.devdyna.synergy.api.utils;

public class TimeUtil {

    public static long ONE_SECOND = 1000;

    public static boolean fireAt(long tick) {
        long intervalStart = tick / 2;
        return (System.currentTimeMillis() % tick) < intervalStart;
    }

}
