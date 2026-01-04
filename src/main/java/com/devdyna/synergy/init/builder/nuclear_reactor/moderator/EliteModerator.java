package com.devdyna.synergy.init.builder.nuclear_reactor.moderator;

import com.devdyna.synergy.config.Common;

public class EliteModerator extends ModeratorBase {

    @Override
    public float FEReducer() {
        return Common.ELITE_MODERATOR_FE_REDUCER.get().floatValue();
    }

    @Override
    public float HeatReducer() {
        return Common.ELITE_MODERATOR_HEAT_REDUCER.get().floatValue();
    }

}
