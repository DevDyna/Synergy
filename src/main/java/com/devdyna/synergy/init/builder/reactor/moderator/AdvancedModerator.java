package com.devdyna.synergy.init.builder.reactor.moderator;

public class AdvancedModerator extends ModeratorBase {

    @Override
    public float FEReducer() {
        return 1.65F;
    }

    @Override
    public float HeatReducer() {
        return 2.0F;
    }

}
