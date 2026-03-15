package com.devdyna.synergy.api.blockfactories.plants.builder;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

@SuppressWarnings("null")
public class BaseWildCropBlock extends BushBlock {

    protected BaseWildCropBlock(Properties properties) {
        super(properties.mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY));

    }

    @Override
    public MapCodec<BaseWildCropBlock> codec() {
        return simpleCodec((p) -> new BaseWildCropBlock(p));
    }

    public TagKey<Block> getSpawnFilter() {
        return null;
    }

    @Override
    protected boolean mayPlaceOn(BlockState s, BlockGetter l, BlockPos p) {
        return s.is(getSpawnFilter());
    }

    @Override
    protected boolean canSurvive(BlockState s, LevelReader l, BlockPos p) {
        return l.getBlockState(p.below()).is(getSpawnFilter());
    }

}
