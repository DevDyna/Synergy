package com.devdyna.synergy.init.builder.nuclear_reactor.cooler;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.blockfactories.reactor.CoolerBlockBase;
import com.devdyna.synergy.api.blockfactories.reactor.ModeratorBase;
import com.devdyna.synergy.config.Common;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class QuartzCooler extends CoolerBlockBase {

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        for (int i = 0; i < Direction.values().length; i++)
            if (level.getBlockState(pos.relative(Direction.values()[i])).getBlock() instanceof ModeratorBase)
                return true;

        return false;
    }

    @Override
    public int getActiveCooling() {
        return Common.QUARTZ_COOLER_ACTIVE_COOLING.get();
    }

    @Override
    public int getBaseCooling() {
        return Common.QUARTZ_COOLER_BASE_COOLING.get();
    }

    @Override
    public Component conditions() {
        return Component
                .translatable(Main.ID + "." + zStatic.ReactorStuff.cooler +"." + zStatic.ReactorStuff.CoolerTypes.QUARTZ);
    }

}
