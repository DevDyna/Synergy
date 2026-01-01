package com.devdyna.synergy.api.BlockAbilities.tooltips.multi_simple;

import java.util.List;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.utils.ArrayUtils;

public interface NodeTransfer extends NodeType {

    @Override
    default List<String> keys() {
        return ArrayUtils.concat(NodeType.super.keys(), zStatic.PipeStuff.nodes.type_transfer);
    }

}
