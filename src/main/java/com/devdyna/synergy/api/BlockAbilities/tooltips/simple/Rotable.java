package com.devdyna.synergy.api.BlockAbilities.tooltips.simple;

import com.devdyna.synergy.api.BlockAbilities.tooltips.base.SimpleToolTip;

public interface Rotable extends SimpleToolTip {
    @Override
    default String key() {
        return "rotate_by_click";
    }
}
