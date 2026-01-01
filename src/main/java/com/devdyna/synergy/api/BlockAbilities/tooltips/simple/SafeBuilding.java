package com.devdyna.synergy.api.BlockAbilities.tooltips.simple;

import com.devdyna.synergy.api.BlockAbilities.tooltips.base.SimpleToolTip;

public interface SafeBuilding extends SimpleToolTip {
    @Override
    default String key(){
        return "safe_building";
    }
}
