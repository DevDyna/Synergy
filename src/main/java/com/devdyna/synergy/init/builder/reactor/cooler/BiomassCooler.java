package com.devdyna.synergy.init.builder.reactor.cooler;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.builder.reactor.cell.FuelCellBlock;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BiomassCooler extends CoolerBlockBase {

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        var controller = false;
        var cell = false;
        for (int i = 0; i < Direction.values().length; i++) {
            var block = level.getBlockState(pos.relative(Direction.values()[i])).getBlock();
            if (block instanceof ReactorControllerBlock)
                controller = true;
            if (block instanceof FuelCellBlock)
                cell = true;
        }
        return controller && cell;
    }

    @Override
    public int getActiveCooling() {
        return 10;
    }

    @Override
    public Component conditions() {
        return Component
                .translatable(Main.ID + "." + zStatic.ReactorStuff.cooler +"." + zStatic.ReactorStuff.CoolerTypes.BIOMASS);
    }

}
