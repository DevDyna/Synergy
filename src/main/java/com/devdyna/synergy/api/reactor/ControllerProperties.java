package com.devdyna.synergy.api.reactor;

import net.minecraft.util.StringRepresentable;

public enum ControllerProperties implements StringRepresentable {
    
    NOCELLS("nocells"),
    WAITING("waiting"),
    OVERHEATED("overheated"),
    PRODUCTION("production");

    private final String name;

    ControllerProperties(String string) {
        this.name = string;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }

}
