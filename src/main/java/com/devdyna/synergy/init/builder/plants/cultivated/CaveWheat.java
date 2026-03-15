package com.devdyna.synergy.init.builder.plants.cultivated;

import com.devdyna.synergy.api.blockfactories.plants.builder.BaseShortCropBlock;
import com.devdyna.synergy.init.types.zItems;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class CaveWheat extends BaseShortCropBlock {

    public CaveWheat() {
        super(Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY));
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

    @Override
    public int getChanceToGrow(BlockState state, ServerLevel level, BlockPos pos) {
        return super.getChanceToGrow(state, level, pos) / (level.getRawBrightness(pos, 0) < 10 ? 3 : 1);
    }

}
