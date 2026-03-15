package com.devdyna.synergy.init.builder.nuclear_reactor.controller;

import net.minecraft.util.StringRepresentable;

public enum ControllerProperties implements StringRepresentable {
    
    NOFUEL("nofuel"),
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

    public String getName() {
        return name;
    }

}
