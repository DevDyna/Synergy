package com.devdyna.synergy.init.builder.crops.wild;

import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder._core.crops.BaseWildCropBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("null")
public class wild_cave_wheat extends BaseWildCropBlock {

    public wild_cave_wheat() {
        super(Material.cropProp);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BlockTags.STONE_ORE_REPLACEABLES);
    }

}
