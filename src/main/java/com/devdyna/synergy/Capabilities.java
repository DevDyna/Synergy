package com.devdyna.synergy;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zHandlers;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class Capabilities {

    public static void register(RegisterCapabilitiesEvent event) {

        // TODO if it will return null , probably was a MY mistake! 
        //:p

        event.registerBlock(net.neoforged.neoforge.capabilities.Capabilities.EnergyStorage.BLOCK,
                (level, pos, state, be, side) -> be.getData(zHandlers.ENERGY_STORAGE),
                zBlocks.HARVESTER.get(), zBlocks.SPRINKLER.get(),zBlocks.SOLAR_PANEL.get());
    }

}
