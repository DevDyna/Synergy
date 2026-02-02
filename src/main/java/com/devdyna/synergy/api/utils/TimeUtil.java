package com.devdyna.synergy.api.utils;

public class TimeUtil {

    public static long ONE_SECOND = 1000;

    public static boolean fireAt(long tick) {
        long intervalStart = tick / 2;
        return (System.currentTimeMillis() % tick) < intervalStart;
    }

        public static String getTimeValue(int duration) {
        return (duration == 1 ? "no tick delay"
                : (duration >= 20 ? (duration >= 1200 ? (duration >= 72000
                        ? duration / 72000 + " hour" + (duration > 72000 ? "s" : "")
                        : duration / 1200 + " minute" + (duration > 1200 ? "s" : "")

                )
                        : duration / 20 + " second" + (duration > 20 ? "s" : ""))
                        : duration + " tick" + (duration > 1 ? "s" : "")));
    }

}
