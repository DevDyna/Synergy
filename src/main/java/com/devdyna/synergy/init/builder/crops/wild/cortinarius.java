package com.devdyna.synergy.init.builder.crops.wild;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.crops.BaseWildCropBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class cortinarius extends BaseWildCropBlock {

    public cortinarius() {
        super(Material.cropProp.lightLevel(b -> 1));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return super.mayPlaceOn(state, level, pos) && state.getLightBlock(level, pos) <= 5;
    }

}
