package com.devdyna.synergy.init.builder;

import net.minecraft.world.level.block.Block;

public class AnchorBlock extends Block{

    public AnchorBlock(Properties properties) {
        super(properties.destroyTime(Block.INDESTRUCTIBLE));
    }
    
}
