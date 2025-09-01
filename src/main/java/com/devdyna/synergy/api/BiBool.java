package com.devdyna.synergy.api;

public class BiBool {
    private boolean value1;
    private boolean value2;

    public BiBool(boolean value1, boolean value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public boolean get1() {
        return value1;
    }

    public boolean get2() {
        return value2;
    }

    public boolean match() {
        return value1 && value2;
    }

    public static BiBool of(boolean value1, boolean value2) {
        return new BiBool(value1, value2);
    }

    public static BiBool of() {
        return new BiBool(false, false);
    }

}
