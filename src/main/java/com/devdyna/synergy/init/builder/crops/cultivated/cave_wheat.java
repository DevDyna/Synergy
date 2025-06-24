package com.devdyna.synergy.init.builder.crops.cultivated;

import com.devdyna.synergy.api.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class cave_wheat extends BaseShortCropBlock {

    public cave_wheat() {
        super(Properties.of());
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.CAVE_WHEAT_SEEDS.get();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BlockTags.BASE_STONE_OVERWORLD)
                || level.getBlockState(pos.below()).is(BlockTags.DIRT);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BlockTags.BASE_STONE_OVERWORLD) ? true
                : super.mayPlaceOn(state, level, pos);
    }

}
