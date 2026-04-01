package com.devdyna.synergy.api.utils;
/**
 * Simple ticking process
 * <br/><br/>
 * Use only inside Server-Level!
 */
public class Ticker {

    private int value;
    private int delay;
    private int base = 0;

    public Ticker(int delay) {
        this.value = base;
        this.delay = delay;
    }

    public static Ticker of(int delay) {
        return new Ticker(delay);
    }

    public void set(int value) {
        this.value = value;
    }

    public void changeDelay(int d) {
        this.delay = d;
    }

    public int get() {
        return value;
    }

    public int min() {
        return base;
    }

    public int max() {
        return delay;
    }

    public boolean commit() {
        var check = value >= delay;
        if (check)
            value = base;
        else
            value++;

        return check;
    }

    public void cancel(){
        value = base;
    }

}
