package com.devdyna.synergy.init.builder.crops.mushroom;

import com.devdyna.synergy.init.builder._core.crops.BaseCropMushroom;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class blue_cup extends BaseCropMushroom {

    public blue_cup() {
        super(Properties.of());
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.BLUE_CUP_SPORE.get();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(Blocks.END_STONE) ? true : super.canSurvive(state, level, pos);
    }

}
