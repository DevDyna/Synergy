package com.devdyna.synergy.init.builder._core.crops;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

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

}
