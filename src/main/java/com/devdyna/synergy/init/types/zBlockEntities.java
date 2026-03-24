package com.devdyna.synergy.init.types;

import com.devdyna.synergy.zStatic;
import com.devdyna.synergy.Main;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.init.builder.automation.chopper.ChopperBE;
import com.devdyna.synergy.init.builder.automation.harvester.HarvesterBE;
import com.devdyna.synergy.init.builder.automation.resource_gen.cobble.advanced.AdvancedCobbleGenBE;
import com.devdyna.synergy.init.builder.automation.resource_gen.cobble.elite.EliteCobbleGenBE;
import com.devdyna.synergy.init.builder.automation.resource_gen.cobble.simple.SimpleCobbleGenBE;
import com.devdyna.synergy.init.builder.automation.resource_gen.water.advanced.AdvancedWaterGenBE;
import com.devdyna.synergy.init.builder.automation.resource_gen.water.elite.EliteWaterGenBE;
import com.devdyna.synergy.init.builder.automation.resource_gen.water.simple.SimpleWaterGenBE;
import com.devdyna.synergy.init.builder.automation.router.RouterBE;
import com.devdyna.synergy.init.builder.automation.solar_panel.SolarPanelBE;
import com.devdyna.synergy.init.builder.automation.sprinkler.SprinklerBE;
import com.devdyna.synergy.init.builder.automation.tank.FluidTankBE;
import com.devdyna.synergy.init.builder.laser.laser_rotor.LaserRotorBE;
import com.devdyna.synergy.init.builder.laser.machine_gun.LaserMachineBE;
import com.devdyna.synergy.init.builder.laser.sensor.LaserSensorBE;
import com.devdyna.synergy.init.builder.magic.entity_watcher.EntityWatcherBE;
import com.devdyna.synergy.init.builder.magic.logic_box.LogicBoxBE;
import com.devdyna.synergy.init.builder.magic.quern.QuernBE;
import com.devdyna.synergy.init.builder.magic.tiny_chests.ornated.OrnatedTinyChestBE;
import com.devdyna.synergy.init.builder.magic.tiny_chests.stone.StoneTinyChestBE;
import com.devdyna.synergy.init.builder.magic.tiny_chests.wooden.WoodenTinyChestBE;
import com.devdyna.synergy.init.builder.magic.urn.UrnBE;
import com.devdyna.synergy.init.builder.magic.void_box.VoidBoxBE;
import com.devdyna.synergy.init.builder.nuclear_reactor.controller.ReactorControllerBE;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBE;
import com.devdyna.synergy.init.builder.pipe_blocks.nodes.blockentities.*;
import com.devdyna.synergy.init.builder.survival.casting_table.CastingTableBE;
import com.devdyna.synergy.init.builder.survival.crushing_tub.CrushingTubBE;
import com.devdyna.synergy.init.builder.survival.drying_rack.DryingRackBE;
import com.devdyna.synergy.init.builder.survival.evaporation_basin.EvaporationBasinBE;
import com.devdyna.synergy.init.builder.survival.faucet.FaucetBE;
import com.devdyna.synergy.init.builder.survival.foundry.FoundryBE;
import com.devdyna.synergy.init.builder.survival.fuel_tank.FuelTankBE;

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

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WoodenTinyChestBE>> WOODEN_TINY_CHEST = Material
                        .createBlockEntity(zStatic.Chests.WOODEN, WoodenTinyChestBE::new,
                                        zBlocks.WOODEN_TINY_CHEST);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StoneTinyChestBE>> STONE_TINY_CHESTS = Material
                        .createBlockEntity(zStatic.Chests.STONE, StoneTinyChestBE::new,
                                        zBlocks.STONE_TINY_CHEST);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OrnatedTinyChestBE>> ORNATED_TINY_CHESTS = Material
                        .createBlockEntity(zStatic.Chests.ORNATE, OrnatedTinyChestBE::new,
                                        zBlocks.ORNATE_TINY_CHEST);

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

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LaserRotorBE>> LASER_ROTOR = Material
                        .createBlockEntity(zStatic.Lazers.rotor, LaserRotorBE::new,
                                        zBlocks.LASER_ROTOR);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<VoidBoxBE>> VOID_BOX = Material
                        .createBlockEntity(zStatic.Blocks.void_box, VoidBoxBE::new,
                                        zBlocks.VOID_BOX);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FluidTankBE>> FLUID_TANK = Material
                        .createBlockEntity(zStatic.FluidTanks.normal, FluidTankBE::new,
                                        zBlocks.SIMPLE_TANK);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FuelTankBE>> FUEL_TANK = Material
                        .createBlockEntity(zStatic.FluidTanks.fuel, FuelTankBE::new,
                                         zBlocks.FUEL_TANK);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SimpleWaterGenBE>> SIMPLE_WATER_GEN = Material
                        .createBlockEntity(zStatic.ResourceGenerators.Water.simple, SimpleWaterGenBE::new,
                                        zBlocks.SIMPLE_WATER_GEN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AdvancedWaterGenBE>> ADVANCED_WATER_GEN = Material
                        .createBlockEntity(zStatic.ResourceGenerators.Water.advanced, AdvancedWaterGenBE::new,
                                        zBlocks.ADVANCED_WATER_GEN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EliteWaterGenBE>> ELITE_WATER_GEN = Material
                        .createBlockEntity(zStatic.ResourceGenerators.Water.elite, EliteWaterGenBE::new,
                                        zBlocks.ELITE_WATER_GEN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SimpleCobbleGenBE>> SIMPLE_COBBLE_GEN = Material
                        .createBlockEntity(zStatic.ResourceGenerators.CobbleStone.simple, SimpleCobbleGenBE::new,
                                        zBlocks.SIMPLE_COBBLE_GEN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AdvancedCobbleGenBE>> ADVANCED_COBBLE_GEN = Material
                        .createBlockEntity(zStatic.ResourceGenerators.CobbleStone.advanced, AdvancedCobbleGenBE::new,
                                        zBlocks.ADVANCED_COBBLE_GEN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EliteCobbleGenBE>> ELITE_COBBLE_GEN = Material
                        .createBlockEntity(zStatic.ResourceGenerators.CobbleStone.elite, EliteCobbleGenBE::new,
                                        zBlocks.ELITE_COBBLE_GEN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrushingTubBE>> CRUSHING_TUB = Material
                        .createBlockEntity(zStatic.Blocks.crushing_tub, CrushingTubBE::new,
                                        zBlocks.CRUSHING_TUB);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EvaporationBasinBE>> EVAPORATION_BASIN = Material
                        .createBlockEntity(zStatic.Blocks.evaporation_basin, EvaporationBasinBE::new,
                                        zBlocks.EVAPORATION_BASIN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CastingTableBE>> CASTING_TABLE = Material
                        .createBlockEntity(zStatic.Blocks.casting_table, CastingTableBE::new,
                                        zBlocks.CASTING_TABLE);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DryingRackBE>> DRYING_RACK = Material
                        .createBlockEntity(zStatic.Blocks.drying_rack, DryingRackBE::new,
                                        zStatic.ALL_DRYING_RACKS.toArray(DeferredHolder[]::new));

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FoundryBE>> FOUNDRY = Material
                        .createBlockEntity(zStatic.Blocks.foundry, FoundryBE::new,
                                        zBlocks.FOUNDRY);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FaucetBE>> FAUCET = Material
                        .createBlockEntity(zStatic.Blocks.faucet, FaucetBE::new,
                                        zBlocks.FAUCET);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChopperBE>> CHOPPER = Material
                        .createBlockEntity(zStatic.Blocks.chopper, ChopperBE::new,
                                        zBlocks.CHOPPER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LogicBoxBE>> LOGIC_BOX = Material
                        .createBlockEntity(zStatic.Blocks.logic_box, LogicBoxBE::new,
                                        zBlocks.LOGIC_BOX);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RouterBE>> ROUTER = Material
                        .createBlockEntity(zStatic.Blocks.router, RouterBE::new,
                                        zBlocks.ROUTER);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EntityWatcherBE>> ENTITY_WATCHER = Material
                        .createBlockEntity(zStatic.Blocks.entity_watcher, EntityWatcherBE::new,
                                        zBlocks.ENTITY_WATCHER);

}
