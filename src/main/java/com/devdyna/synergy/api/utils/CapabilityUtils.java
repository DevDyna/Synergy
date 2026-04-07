package com.devdyna.synergy.api.utils;

import com.devdyna.synergy.api.basebe.be.BETank;
import com.devdyna.synergy.api.basebe.block.BlockTank;
import com.devdyna.synergy.api.beLogic.DirectionBasedItemHandler;
import com.devdyna.synergy.api.beLogic.EnergyBlock;
import com.devdyna.synergy.api.beLogic.SimpleMachineItemStorage;
import com.devdyna.synergy.api.blockfactories.machine.BaseMachineBE;
import com.devdyna.synergy.api.blockfactories.machine.FluidTankStorage;
import com.devdyna.synergy.api.beLogic.RestrictedFluidHandler;
import com.devdyna.synergy.api.beLogic.RestrictedItemHandler;
import com.devdyna.synergy.api.beLogic.SimpleFluidStorage;
import com.devdyna.synergy.init.types.zComponents;
import com.devdyna.synergy.init.types.zHandlers;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStackSimple;

@SuppressWarnings("unchecked")
public class CapabilityUtils {

    public static void registerFluidBlocks(RegisterCapabilitiesEvent e, Block... blocks) {
        e.registerBlock(
                Capabilities.FluidHandler.BLOCK,
                (level, pos, state, be, side) -> {

                    if (be instanceof RestrictedFluidHandler h)
                        return h.getFluidStorageRestricted();

                    if (be instanceof FluidTankStorage t)
                        return t.getFluidStorage();

                    if (be instanceof SimpleFluidStorage)
                        return be.getData(zHandlers.FLUID_TANK);

                    return (be != null) ? be.getData(zHandlers.FLUID_TANK) : null;
                },
                blocks);
    }

    public static void registerFluidItems(RegisterCapabilitiesEvent e, Item... items) {
        e.registerItem(
                Capabilities.FluidHandler.ITEM,
                (i, v) -> {

                    if (i.getItem() instanceof BlockItem bi && bi.getBlock() instanceof BlockTank)
                        return new FluidHandlerItemStackSimple(zComponents.FLUID_STORAGE, i,
                                BETank.DEFAULT_TANK_STORAGE);

                    return null;
                },
                items);
    }

    public static void registerEnergyBlock(RegisterCapabilitiesEvent e, Block... blocks) {
        e.registerBlock(Capabilities.EnergyStorage.BLOCK,
                (level, pos, state, be, side) -> {

                    if (be != null && be instanceof EnergyBlock s)
                        return s.getCapEnergy();
                    return null;
                },
                blocks

        );
    }

    public static void registerItemBlock(RegisterCapabilitiesEvent e, Block... blocks) {
        e.registerBlock(Capabilities.ItemHandler.BLOCK,
                (level, pos, state, be, side) -> {

                    if (be instanceof BaseMachineBE m)
                        return m.getAutomationHandler();

                    if (be instanceof DirectionBasedItemHandler m)
                        return m.getStorageRestricted(side);

                    if (be instanceof SimpleMachineItemStorage machineBE)
                        return machineBE.getAutomationItemHandler();

                    if (be instanceof RestrictedItemHandler r)
                        return r.getStorageRestricted();

                    return (be != null) ? be.getData(zHandlers.ITEM_STORAGE) : null;

                },
                blocks

        );
    }

    public static void registerBlockAll(RegisterCapabilitiesEvent e, Block... blocks) {
        registerEnergyBlock(e, blocks);
        registerFluidBlocks(e, blocks);
        registerItemBlock(e, blocks);
    }

}
