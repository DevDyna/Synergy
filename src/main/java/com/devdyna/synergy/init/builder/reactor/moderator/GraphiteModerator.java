package com.devdyna.synergy.init.builder.reactor.moderator;

public class GraphiteModerator extends ModeratorBase {

    @Override
    public float FEReducer() {
        return 0.95F;
    }

    @Override
    public float HeatReducer() {
        return 0.75F;
    }


}
