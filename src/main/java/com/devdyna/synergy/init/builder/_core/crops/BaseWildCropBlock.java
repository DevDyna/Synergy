package com.devdyna.synergy.init.builder._core.crops;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.IShearable;

@SuppressWarnings("null")
public class BaseWildCropBlock extends BushBlock implements IShearable {

    protected BaseWildCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<BaseWildCropBlock> codec() {
        return simpleCodec((p) -> new BaseWildCropBlock(p));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT);//TODO BLOCKTAGS
    }

}
