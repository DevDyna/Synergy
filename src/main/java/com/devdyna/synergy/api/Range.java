package com.devdyna.synergy.api;

public class Range {

    private int min;
    private int max;
    private BiBool included;

    /**
     * @param min
     * @param max
     * @param included <code>[minIncluded,maxIncluded]<code/>
     */
    public Range(int min, int max, BiBool included) {
        this.min = min;
        this.max = max;
        this.included = included;
    }

    public Range(int min, int max) {
        this(min, max, BiBool.of());
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public BiBool getIncluded() {
        return included;
    }

    public boolean test(int value) {
        return (included.get1() ? value >= min : value > min) && (included.get2() ? value <= max : value < max);
    }

    public static Range of(int min, int max, BiBool included) {
        return new Range(min, max, included);
    }

    public static Range of(int min, int max) {
        return new Range(min, max);
    }

}
