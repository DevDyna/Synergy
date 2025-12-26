package com.devdyna.synergy;

import com.devdyna.synergy.api.beLogic.*;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.init.machine.core.*;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@SuppressWarnings("null")
public class Capability {

        public static void register(RegisterCapabilitiesEvent event) {

                // if it will return null , probably was a MY mistake!
                // :p

                event.registerBlock(Capabilities.Energy.BLOCK,
                                (level, pos, state, be,
                                                side) -> (be != null ? be.getData(zHandlers.ENERGY_STORAGE) : null),
                                zBlocks.HARVESTER.get(),
                                zBlocks.SPRINKLER.get(),
                                zBlocks.SOLAR_PANEL.get(),
                                zBlocks.REACTOR_CONTROLLER.get(),
                                zBlocks.LASER_MACHINE.get(),
                                zBlocks.LASER_ROTOR.get()

                );

                event.registerBlock(Capabilities.Item.BLOCK,
                                (level, pos, state, be,
                                                side) -> (be != null ? be.getData(zHandlers.ITEM_STORAGE) : null),
                                zBlocks.REACTOR_FUEL_CELL.get(),
                                zBlocks.URN.get(),
                                zBlocks.WOODEN_TINY_CHEST.get(),
                                zBlocks.ORNATE_TINY_CHEST.get(),
                                zBlocks.STONE_TINY_CHEST.get(),
                                zBlocks.QUERN.get(),
                                zBlocks.VOID_BOX.get());

                event.registerBlock(
                                Capabilities.Item.BLOCK,
                                (level, pos, state, be, side) -> ((be instanceof BaseMachineBE m)
                                                ? m.getAutomationHandler()
                                                : null),
                                ClazzUtil.getAllMachineTypes().stream().map(b -> b.block().get())
                                                .toArray(Block[]::new));

                event.registerBlock(
                                Capabilities.Energy.BLOCK,
                                (level, pos, state, be, side) -> ((be instanceof EnergyBlock m)
                                                ? m.getCapEnergy()
                                                : null),
                                ClazzUtil.getAllMachineTypes().stream().map(b -> b.block().get())
                                                .toArray(Block[]::new));

        }

}
