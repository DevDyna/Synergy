package com.devdyna.synergy.api;

public class BiBool {
    private Boolean value1;
    private Boolean value2;

    public BiBool(Boolean value1, Boolean value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Boolean get1() {
        return value1;
    }

    public Boolean get2() {
        return value2;
    }

    public Boolean match() {
        return value1 && value2;
    }

    public static BiBool of(Boolean value1, Boolean value2) {
        return new BiBool(value1, value2);
    }

    public static BiBool of() {
        return new BiBool(false, false);
    }

}
