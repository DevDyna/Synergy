package com.devdyna.synergy.init.builder.nuclear_reactor.cooler;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.api.reactor.CoolerBlockBase;
import com.devdyna.synergy.config.Common;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SculkCooler extends CoolerBlockBase {

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        for (int i = 0; i < Direction.values().length; i +=2) {
            var block = level.getBlockState(pos.relative(Direction.values()[i])).getBlock();
            var off = level.getBlockState(pos.relative(Direction.values()[i].getOpposite())).getBlock();
            if (block instanceof LapisCooler b && off instanceof LapisCooler o)
                if (b.isActive(level, pos.relative(Direction.values()[i])) && o.isActive(level, pos.relative(Direction.values()[i])))
                    return true;
        }
        return false;
    }

    @Override
    public int getActiveCooling() {
        return Common.SCULK_COOLER_ACTIVE_COOLING.get();
    }

    @Override
    public int getBaseCooling() {
        return Common.SCULK_COOLER_BASE_COOLING.get();
    }

    @Override
    public Component conditions() {
        return Component
                .translatable(Main.ID + "." + zStatic.ReactorStuff.cooler +"." + zStatic.ReactorStuff.CoolerTypes.SCULK);
    }

}
