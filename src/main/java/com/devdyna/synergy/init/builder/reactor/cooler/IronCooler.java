package com.devdyna.synergy.init.builder.reactor.cooler;

import com.devdyna.synergy.Main;
import com.devdyna.synergy.zStatic;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class IronCooler extends CoolerBlockBase {

    @Override
    public boolean activeWhen(BlockState state, Level level, BlockPos pos) {
        return true;
    }

    @Override
    public int getActiveCooling() {
        return 10;
    }

    @Override
    public Component conditions() {
        return Component.translatable(Main.ID + "." + zStatic.ReactorStuff.cooler + ".desc");
    }

}
