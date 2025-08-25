package com.devdyna.synergy.init.builder.reactor.cooler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class IronCooler extends CoolerBlockBase{

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        return true;
    }

    @Override
    public int getActiveCooling() {
        return 10;
    }

    @Override
    public int getBaseCooling() {
        return 0;
    }
    
}
