package com.devdyna.synergy.api.BlockAbilities.tooltips.multi_simple;

import java.util.List;

import com.devdyna.synergy.api.BlockAbilities.tooltips.base.MultiSimpleTips;


public interface NodeType extends MultiSimpleTips {
    @Override
    default List<String> keys() {
        return List.of("extend");
    }
}
