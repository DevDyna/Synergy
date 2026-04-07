package com.devdyna.synergy.api.beLogic;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.utils.Range;

@SuppressWarnings("null")
public interface HeatConsumer {

    /**
     * Get heat limit value
     */
    abstract Range getHeatLimit();

    abstract void update();

    default void processHeat() {

        if (getProvider() == null)
            return;

        var heat = getProvider().getHeat();

        var limits = getHeatLimit();

        if (limits.isBelow(heat)) {
            update();
            whenBelow(heat);
            return;
        }

        if (limits.isAbove(heat)) {
            update();
            whenAbove(heat);
            return;
        }

        whenInRange(heat);
        update();

    }

    abstract @Nullable HeatProvider getProvider();

    default void whenBelow(int heat) {

    }

    default void whenAbove(int heat) {

    }

    default void whenInRange(int heat) {

    }

}
