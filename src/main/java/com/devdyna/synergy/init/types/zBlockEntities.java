package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.harvester.HarvesterBE;
import com.devdyna.synergy.init.builder.laser.sensor.LaserSensorBE;
import com.devdyna.synergy.init.builder.laser.transmitter.LaserMachineBE;
import com.devdyna.synergy.init.builder.pipeBlocks.nodes.blockentities.*;
import com.devdyna.synergy.init.builder.quern.QuernBE;
import com.devdyna.synergy.init.builder.reactor.cell.FuelCellBE;
import com.devdyna.synergy.init.builder.reactor.controller.ReactorControllerBE;
import com.devdyna.synergy.init.builder.solar_panel.SolarPanelBE;
import com.devdyna.synergy.init.builder.sprinkler.SprinklerBE;
import com.devdyna.synergy.init.builder.tinychests.TinyChestBE;
import com.devdyna.synergy.init.builder.urn.UrnBE;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings({ "unchecked", "null" })
public class zBlockEntities {
        public static void register(IEventBus bus) {
                zBE.register(bus);
        }
        // ---------------------------------------------------------------------------------------//

        public static final DeferredRegister<BlockEntityType<?>> zBE = DeferredRegister
                        .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.ID);
        // ---------------------------------------------------------------------------------------//

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SprinklerBE>> SPRINKLER = Material
                        .createBlockEntity(zStatic.Blocks.sprinkler, SprinklerBE::new, zBlocks.SPRINKLER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HarvesterBE>> HARVESTER = Material
                        .createBlockEntity(zStatic.Blocks.harvester, HarvesterBE::new, zBlocks.HARVESTER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SolarPanelBE>> SOLAR_PANEL = Material
                        .createBlockEntity(zStatic.Blocks.solar_panel, SolarPanelBE::new, zBlocks.SOLAR_PANEL);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemTransferBE>> ITEM_TRANSFER = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Transfer.Item, ItemTransferBE::new,
                                        zBlocks.ITEM_TRANSFER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemProviderBE>> ITEM_PROVIDER = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Provider.Item, ItemProviderBE::new,
                                        zBlocks.ITEM_PROVIDER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ItemRetrievalBE>> ITEM_RETRIEVAL = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Retrieval.Item, ItemRetrievalBE::new,
                                        zBlocks.ITEM_RETRIEVAL);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnergyTransferBE>> ENERGY_TRANSFER = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Transfer.Energy, EnergyTransferBE::new,
                                        zBlocks.ENERGY_TRANSFER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnergyRetrievalBE>> ENERGY_RETRIEVAL = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Retrieval.Energy, EnergyRetrievalBE::new,
                                        zBlocks.ENERGY_RETRIEVAL);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FluidTransferBE>> FLUID_TRANSFER = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Transfer.Fluid, FluidTransferBE::new,
                                        zBlocks.FLUID_TRANSFER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FluidProviderBE>> FLUID_PROVIDER = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Provider.Fluid, FluidProviderBE::new,
                                        zBlocks.FLUID_PROVIDER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FluidRetrievalBE>> FLUID_RETRIEVAL = Material
                        .createBlockEntity(zStatic.PipeStuff.nodes.Retrieval.Fluid, FluidRetrievalBE::new,
                                        zBlocks.FLUID_RETRIEVAL);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ReactorControllerBE>> REACTOR_CONTROLLER = Material
                        .createBlockEntity(zStatic.ReactorStuff.controller, ReactorControllerBE::new,
                                        zBlocks.REACTOR_CONTROLLER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TinyChestBE>> WOODEN_TINY_CHEST = Material
                        .createBlockEntity(zStatic.Blocks.tiny_wooden_chest, TinyChestBE::new,
                                        zBlocks.WOODEN_TINY_CHEST, zBlocks.STONE_TINY_CHEST, zBlocks.ORNATE_TINY_CHEST);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FuelCellBE>> FUEL_CELL = Material
                        .createBlockEntity(zStatic.ReactorStuff.fuel_cell, FuelCellBE::new,
                                        zBlocks.REACTOR_FUEL_CELL);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UrnBE>> URN = Material
                        .createBlockEntity(zStatic.Blocks.urn, UrnBE::new,
                                        zBlocks.URN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<QuernBE>> QUERN = Material
                        .createBlockEntity(zStatic.Blocks.quern, QuernBE::new,
                                        zBlocks.QUERN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LaserMachineBE>> LASER_MACHINE = Material
                        .createBlockEntity(zStatic.Lazers.machine_gun, LaserMachineBE::new,
                                        zBlocks.LASER_MACHINE);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LaserSensorBE>> LASER_SENSOR = Material
                        .createBlockEntity(zStatic.Lazers.sensor, LaserSensorBE::new,
                                        zBlocks.LASER_SENSOR);

}
