package com.devdyna.synergy.api.BlockAbilities.tooltips.simple;

import com.devdyna.synergy.api.BlockAbilities.tooltips.base.SimpleToolTip;

public interface NoItemForm extends SimpleToolTip {
    @Override
    default String key() {
        return "disabled";
    }
}
