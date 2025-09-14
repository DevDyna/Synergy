package com.devdyna.synergy.init.builder.reactor.cooler;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GoldCooler extends CoolerBlockBase {

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        var water = false;
        var redstone = false;
        for (int i = 0; i < Direction.values().length; i++) {
            var block = level.getBlockState(pos.relative(Direction.values()[i])).getBlock();
            if (block instanceof WaterCooler w)
                if (w.isActive(level, pos.relative(Direction.values()[i])))
                    water = true;
            if (block instanceof RedstoneCooler red)
                if (red.isActive(level, pos.relative(Direction.values()[i])))
                    redstone = true;
        }
        return water && redstone;
    }

    @Override
    public int getActiveCooling() {
        return -120;
    }

    @Override
    public Component conditions() {
        return Component
                .translatable(Main.ID + "." + zStatic.ReactorStuff.cooler +"." + zStatic.ReactorStuff.CoolerTypes.GOLD);
    }

}
