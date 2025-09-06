package com.devdyna.synergy;

import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zHandlers;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class Capabilities {

    public static void register(RegisterCapabilitiesEvent event) {

        // if it will return null , probably was a MY mistake!
        // :p

        event.registerBlock(net.neoforged.neoforge.capabilities.Capabilities.EnergyStorage.BLOCK,
                (level, pos, state, be, side) -> be.getData(zHandlers.ENERGY_STORAGE),
                zBlocks.HARVESTER.get(), zBlocks.SPRINKLER.get(), zBlocks.SOLAR_PANEL.get(),
                zBlocks.REACTOR_CONTROLLER.get());

        event.registerBlock(net.neoforged.neoforge.capabilities.Capabilities.ItemHandler.BLOCK,
                (level, pos, state, be, side) -> be.getData(zHandlers.ITEM_STORAGE),
                zBlocks.REACTOR_FUEL_CELL.get(), zBlocks.WOODEN_TINY_CHEST.get(), zBlocks.ORNATE_TINY_CHEST.get(), zBlocks.STONE_TINY_CHEST.get());

    }

}
