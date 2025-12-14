package com.devdyna.synergy.init.builder.nuclear_reactor.cooler;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneCooler extends CoolerBlockBase {

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        for (int i = 0; i < Direction.values().length; i++) {
            var block = level.getBlockState(pos.relative(Direction.values()[i])).getBlock();
            if (block instanceof FuelCellBlock)
                return true;
        }
        return false;
    }

    @Override
    public int getActiveCooling() {
        return -90;
    }

    @Override
    public Component conditions() {
        return Component
                .translatable(Main.ID + "." + zStatic.ReactorStuff.cooler +"." + zStatic.ReactorStuff.CoolerTypes.REDSTONE);
    }

}
