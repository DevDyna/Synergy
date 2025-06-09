package com.devdyna.synergy.init.builder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Extractor extends Block {

    public Extractor() {
        super(BlockBehaviour.Properties.of().sound(SoundType.METAL));
    }

}
