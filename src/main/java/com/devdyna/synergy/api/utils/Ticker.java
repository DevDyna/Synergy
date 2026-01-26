package com.devdyna.synergy.api.utils;

public class Ticker {

    private int value;
    private int delay;

    public Ticker(int delay) {
        this.value = 0;
        this.delay = delay;
    }

    public boolean commit() {
        var check = value >= delay;
        if (check)
            value = 0;
        else
            value++;

        return check;
    }

}
