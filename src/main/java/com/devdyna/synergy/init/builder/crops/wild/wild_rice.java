package com.devdyna.synergy.init.builder.crops.wild;

import com.devdyna.synergy.init.builder._core.crops.BaseWildCropBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class wild_rice extends BaseWildCropBlock {

    public wild_rice() {
        super(Properties.of());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(Blocks.WATER) ? true : super.canSurvive(state, level, pos);
    }

}
