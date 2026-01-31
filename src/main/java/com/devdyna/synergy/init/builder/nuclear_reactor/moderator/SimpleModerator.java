package com.devdyna.synergy.init.builder.nuclear_reactor.moderator;

import com.devdyna.synergy.api.reactor.ModeratorBase;
import com.devdyna.synergy.config.Common;

public class SimpleModerator extends ModeratorBase {

    @Override
    public float FEReducer() {
        return Common.SIMPLE_MODERATOR_FE_REDUCER.get().floatValue();
    }

    @Override
    public float HeatReducer() {
        return Common.SIMPLE_MODERATOR_HEAT_REDUCER.get().floatValue();
    }

}
