package com.devdyna.synergy.init.builder.decorative;

import com.devdyna.synergy.api.BlockAbilities.tooltips.simple.SafeBuilding;

import net.minecraft.world.level.block.RotatedPillarBlock;

@SuppressWarnings("null")
public class PillarDecorativeBlock extends RotatedPillarBlock implements SafeBuilding {

    public PillarDecorativeBlock(Properties properties) {
        super(properties);
    }
}
