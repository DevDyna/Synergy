package com.devdyna.synergy;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.api.basebe.be.MachineBE;
import com.devdyna.synergy.api.basebe.block.BlockTank;
import com.devdyna.synergy.api.beLogic.*;
import com.devdyna.synergy.api.machine.BaseMachineBE;
import com.devdyna.synergy.api.machine.FluidTankStorage;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zHandlers;
import com.devdyna.synergy.init.types.zMachines;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.neoforged.neoforge.registries.DeferredHolder;

public class Capability {

        public static void register(RegisterCapabilitiesEvent event) {

                // if it will return null , probably was a MY mistake!
                // :p

                event.registerBlock(Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be,
                                                side) -> (be != null ? be.getData(zHandlers.ENERGY_STORAGE) : null),
                                zBlocks.HARVESTER.get(),
                                zBlocks.SPRINKLER.get(),
                                zBlocks.SOLAR_PANEL.get(),
                                zBlocks.REACTOR_CONTROLLER.get(),
                                zBlocks.LASER_MACHINE.get(),
                                zBlocks.LASER_ROTOR.get()

                );

                event.registerBlock(Capabilities.FluidHandler.BLOCK,
                                (level, pos, state, be,
                                                side) -> {
                                        if (be instanceof SimpleFluidStorage)
                                                return be.getData(zHandlers.FLUID_TANK);

                                        return null;
                                },
                                zBlocks.FLUID_TANK.get(),
                                zBlocks.SIMPLE_WATER_GEN.get(),
                                zBlocks.ADVANCED_WATER_GEN.get(),
                                zBlocks.ELITE_WATER_GEN.get(),
                                zBlocks.CRUSHING_TUB.get(),
                                zBlocks.EVAPORATION_BASIN.get());

                event.registerItem(Capabilities.FluidHandler.ITEM, (i, v) -> {

                        if (i.getItem() instanceof BlockItem bi && bi.getBlock() instanceof BlockTank)
                                return new FluidHandlerItemStackSimple(zComponents.FLUID_STORAGE, i,
                                                BETank.DEFAULT_TANK_STORAGE);

                        return null;
                },
                                zBlocks.FLUID_TANK.get().asItem());

                event.registerBlock(Capabilities.ItemHandler.BLOCK,
                                (level, pos, state, be,
                                                side) -> {

                                        if (be instanceof MachineBE machineBE)
                                                return machineBE.getAutomatioHandler();

                                        return (be != null)
                                                        ? be.getData(zHandlers.ITEM_STORAGE)
                                                        : null;
                                },
                                zBlocks.REACTOR_FUEL_CELL.get(),
                                zBlocks.URN.get(),
                                zBlocks.WOODEN_TINY_CHEST.get(),
                                zBlocks.ORNATE_TINY_CHEST.get(),
                                zBlocks.STONE_TINY_CHEST.get(),
                                zBlocks.QUERN.get(),
                                zBlocks.VOID_BOX.get(),
                                zBlocks.SIMPLE_COBBLE_GEN.get(),
                                zBlocks.ADVANCED_COBBLE_GEN.get(),
                                zBlocks.ELITE_COBBLE_GEN.get(),
                                zBlocks.CRUSHING_TUB.get(),
                                zBlocks.EVAPORATION_BASIN.get());

                event.registerBlock(Capabilities.ItemHandler.BLOCK,
                                (level, pos, state, be,
                                                side) -> {

                                        if (be instanceof MachineBE machineBE)
                                                return machineBE.getAutomatioHandler();

                                        return (be != null)
                                                        ? be.getData(zHandlers.ITEM_STORAGE)
                                                        : null;
                                },
                                zStatic.ALL_DRYING_RACKS.stream().map(DeferredHolder::get).toArray(Block[]::new));

                event.registerBlock(
                                Capabilities.ItemHandler.BLOCK,
                                (level, pos, state, be, side) -> ((be instanceof BaseMachineBE m)
                                                ? m.getAutomationHandler()
                                                : null),
                                ClazzUtil.getAllMachineTypes().stream().map(b -> b.block().get())
                                                .toArray(Block[]::new));

                event.registerBlock(
                                Capabilities.EnergyStorage.BLOCK,
                                (level, pos, state, be, side) -> ((be instanceof EnergyBlock m)
                                                ? m.getCapEnergy()
                                                : null),
                                ClazzUtil.getAllMachineTypes().stream().map(b -> b.block().get())
                                                .toArray(Block[]::new));

                event.registerBlock(
                                Capabilities.FluidHandler.BLOCK,
                                (level, pos, state, be, side) -> ((be instanceof FluidTankStorage t)
                                                ? t.getFluidStorage()
                                                : null),
                                zMachines.EXTRACTOR.block().get(), zMachines.CASTING_FACTORY.block().get(),
                                zMachines.MELTER.block().get());

        }

}
