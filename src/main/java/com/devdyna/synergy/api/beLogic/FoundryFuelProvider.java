package com.devdyna.synergy.api.beLogic;

import net.minecraft.world.level.Level;

public interface FoundryFuelProvider {

    abstract Level getLevel();

    /**
     * return true when conditions match and can procede
     */
    abstract boolean initConditions();

    abstract float getSpeedModifier();

    abstract void executeOnRecipeCompleted();

}
