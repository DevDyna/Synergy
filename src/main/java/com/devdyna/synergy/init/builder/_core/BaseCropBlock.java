package com.devdyna.synergy.init.builder._core;

import com.devdyna.synergy.init.Material;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class BaseCropBlock extends CropBlock {

    public BaseCropBlock() {
        super(Material.bProp.mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY));
    }



}
