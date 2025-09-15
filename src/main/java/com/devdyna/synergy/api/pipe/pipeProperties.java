package com.devdyna.synergy.api.pipe;

import net.minecraft.util.StringRepresentable;

public enum pipeProperties implements StringRepresentable {
    
    TRUE("true"),
    FALSE("false"),
    // OUTPUT("output"),
    // DISABLED("disabled"),
    NODE("node");

    private final String name;

    pipeProperties(String string) {
        this.name = string;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }

}
