package com.devdyna.synergy.api.beLogic;

import com.devdyna.synergy.api.utils.Ticker;

public interface TimeredRecipe {
    abstract Ticker getTicker();

    abstract float getTickerSpeed();
}
