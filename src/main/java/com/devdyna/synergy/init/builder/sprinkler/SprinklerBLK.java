package com.devdyna.synergy.init.builder.sprinkler;

import javax.annotation.Nullable;

import com.devdyna.synergy.api.coreBE.BaseBlockBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("null")
public class SprinklerBLK extends BaseBlockBE {

    public SprinklerBLK() {
        super(Properties.of().forceSolidOn().destroyTime(1.0f).sound(SoundType.TUFF_BRICKS).mapColor(MapColor.TERRACOTTA_BROWN));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new SprinklerBE(p, s);
    }

}
