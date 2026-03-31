package com.devdyna.synergy.api.beLogic;

public interface EnvironmentModifier {
    abstract float getSpeedModifier();

    abstract boolean isRequired();

    default String failDescKey(){
        return "";
    }
}
