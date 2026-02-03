package com.devdyna.synergy;

import com.devdyna.synergy.api.registers.MachineType;
import static com.devdyna.synergy.api.utils.CapabilityUtils.*;
import com.devdyna.synergy.api.utils.ClazzUtil;
import com.devdyna.synergy.api.utils.x;
import com.devdyna.synergy.init.types.zBlocks;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unchecked")
public class Capability {

        public static void register(RegisterCapabilitiesEvent event) {

                registerBlockAll(event, x.toBlocks(ClazzUtil.getAllMachineTypes().toArray(MachineType[]::new)));

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
                                                zBlocks.FOUNDRY));

                registerEnergyBlock(event, x.toBlocks(
                                zBlocks.HARVESTER,
                                zBlocks.SPRINKLER,
                                zBlocks.SOLAR_PANEL,
                                zBlocks.REACTOR_CONTROLLER,
                                zBlocks.LASER_MACHINE,
                                zBlocks.LASER_ROTOR));

                registerFluidBlocks(event, x.toBlocks(
                                zBlocks.SIMPLE_TANK,
                                zBlocks.FUEL_TANK,
                                zBlocks.SIMPLE_WATER_GEN,
                                zBlocks.ADVANCED_WATER_GEN,
                                zBlocks.ELITE_WATER_GEN,
                                zBlocks.CRUSHING_TUB,
                                zBlocks.EVAPORATION_BASIN,
                                zBlocks.FOUNDRY));

                registerFluidItems(event, x.toItems(
                                zBlocks.SIMPLE_TANK,
                                zBlocks.FUEL_TANK));

        }

}
