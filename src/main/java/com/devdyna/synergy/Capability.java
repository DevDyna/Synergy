package com.devdyna.synergy;

import static com.devdyna.synergy.api.utils.CapabilityUtils.*;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;
import com.devdyna.synergy.init.types.zMachines;

import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unchecked")
public class Capability {

        public static void register(RegisterCapabilitiesEvent event) {

                registerItemBlock(event, x.toBlocks(ClazzUtil.getAllMachineTypes()));
                registerEnergyBlock(event, x.toBlocks(ClazzUtil.getAllMachineTypes()));
                registerFluidBlocks(event, x.toBlocks(
                                zMachines.CASTING_FACTORY,
                                zMachines.EXTRACTOR,
                                zMachines.ROCK_CRUSHER,
                                zMachines.MELTER));

                registerItemBlock(event, x.toBlocks(zStatic.ALL_DRYING_RACKS.toArray(DeferredHolder[]::new)));

                registerItemBlock(event,
                                x.toBlocks(
                                                zBlocks.REACTOR_FUEL_CELL,
                                                zBlocks.URN,
                                                zBlocks.WOODEN_TINY_CHEST,
                                                zBlocks.ORNATE_TINY_CHEST,
                                                zBlocks.STONE_TINY_CHEST,
                                                zBlocks.QUERN,
                                                zBlocks.VOID_BOX,
                                                zBlocks.SIMPLE_COBBLE_GEN,
                                                zBlocks.ADVANCED_COBBLE_GEN,
                                                zBlocks.ELITE_COBBLE_GEN,
                                                zBlocks.CRUSHING_TUB,
                                                zBlocks.EVAPORATION_BASIN,
                                                zBlocks.FOUNDRY,
                                                zBlocks.CASTING_TABLE,
                                                zBlocks.CHOPPER,
                                                zBlocks.LOGIC_BOX,
                                                zBlocks.ROUTER,

                                                zBlocks.ITEM_PROVIDER,
                                                zBlocks.ITEM_RETRIEVAL,
                                                zBlocks.ITEM_TRANSFER,

                                                zBlocks.ENERGY_RETRIEVAL,
                                                zBlocks.ENERGY_TRANSFER,

                                                zBlocks.FLUID_PROVIDER,
                                                zBlocks.FLUID_RETRIEVAL,
                                                zBlocks.FLUID_TRANSFER,

                                                zBlocks.BRICKED_HEATER,
                                                zBlocks.METALLIC_HEATER

                                ));

                registerEnergyBlock(event, x.toBlocks(
                                zBlocks.HARVESTER,
                                zBlocks.SPRINKLER,
                                zBlocks.SOLAR_PANEL,
                                zBlocks.REACTOR_CONTROLLER,
                                zBlocks.LASER_MACHINE,
                                zBlocks.LASER_ROTOR,
                                zBlocks.CHOPPER

                ));

                registerFluidBlocks(event, x.toBlocks(
                                zBlocks.SIMPLE_TANK,
                                zBlocks.FUEL_TANK,
                                zBlocks.SIMPLE_WATER_GEN,
                                zBlocks.ADVANCED_WATER_GEN,
                                zBlocks.ELITE_WATER_GEN,
                                zBlocks.CRUSHING_TUB,
                                zBlocks.EVAPORATION_BASIN,
                                zBlocks.FOUNDRY,
                                zBlocks.CASTING_TABLE,
                                zBlocks.MIXING_CHAMBER,
                                zBlocks.STEAM_BOILER

                ));

                registerFluidItems(event, x.toItems(
                                zBlocks.SIMPLE_TANK,
                                zBlocks.FUEL_TANK

                ));

        }

}
