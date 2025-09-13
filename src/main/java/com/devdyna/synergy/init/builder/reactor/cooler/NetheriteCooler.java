package com.devdyna.synergy.init.builder.reactor.cooler;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class NetheriteCooler extends CoolerBlockBase {

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        var s = false;
        var gold = false;
        for (int i = 0; i < Direction.values().length; i++) {
            var block = level.getBlockState(pos.relative(Direction.values()[i])).getBlock();
            if (block instanceof SculkCooler r)
                if (r.isActive(level, pos.relative(Direction.values()[i])))
                    s = true;
            if (block instanceof GoldCooler g)
                if (g.isActive(level, pos.relative(Direction.values()[i])))
                    gold = true;
        }
        return s && gold;
    }

    @Override
    public int getActiveCooling() {
        return 140;
    }

    @Override
    public Component conditions() {
        return Component.translatable(Main.ID + "." + zStatic.ReactorStuff.cooler+"." + zStatic.ReactorStuff.CoolerTypes.NETHERITE);
    }

}
