package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.init.Material;

import com.devdyna.synergy.Main;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zCreativeTab {
        public static void register(IEventBus bus) {
                zCreative.register(bus);
        }

        // ---------------------------------------------------------------------------------------//
        public static final DeferredRegister<CreativeModeTab> zCreative = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, Main.ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RESOURCES = Material
                        .createCreativeTab(zStatic.CreativeTab.resources,
                                        () -> zItems.AQUAMARINE.get());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MACHINES = Material
                        .createCreativeTab(zStatic.CreativeTab.machines,
                                        () -> zMachines.ALLOY_SMELTER.item().get());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LASER_STUFF = Material
                        .createCreativeTab(zStatic.CreativeTab.laser, () -> zBlocks.LASER_MACHINE.get().asItem());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NUCLEAR_STUFF = Material
                        .createCreativeTab(zStatic.CreativeTab.nuclear,
                                        () -> zBlocks.REACTOR_CONTROLLER.get().asItem());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS = Material
                        .createCreativeTab(zStatic.CreativeTab.tools, () -> zItems.CONFIGURATOR.get());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGRICULTURE = Material
                        .createCreativeTab(zStatic.CreativeTab.agriculture, () -> zBlocks.AZALEA.get().asItem());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATIVE_BLOCKS = Material
                        .createCreativeTab(zStatic.CreativeTab.decorative, () -> zBlocks.WAXED_PLANKS.get().asItem());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AUTOMATION = Material
                        .createCreativeTab(zStatic.CreativeTab.automation, () -> zBlocks.SOLAR_PANEL.get().asItem());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LOGISTICS = Material
                        .createCreativeTab(zStatic.CreativeTab.logistics, () -> zBlocks.PIPE.get().asItem());

        // public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STORAGE = Material
        //                 .createCreativeTab(zStatic.CreativeTab.storage, () -> zBlocks.WOODEN_TINY_CHEST.get().asItem());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGIC = Material
                        .createCreativeTab(zStatic.CreativeTab.magic, () -> zBlocks.QUERN.get().asItem());

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> REDSTONE = Material
                        .createCreativeTab(zStatic.CreativeTab.redstone, () -> zBlocks.PULSE_REPEATER.get().asItem());

}
