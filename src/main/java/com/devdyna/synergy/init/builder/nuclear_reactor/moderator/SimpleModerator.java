package com.devdyna.synergy.init.builder.nuclear_reactor.moderator;

public class SimpleModerator extends ModeratorBase {

    @Override
    public float FEReducer() {
        return 1.1F;
    }

    @Override
    public float HeatReducer() {
        return 1.3F;
    }

}
