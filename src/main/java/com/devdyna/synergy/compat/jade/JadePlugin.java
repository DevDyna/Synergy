package com.devdyna.synergy.compat.jade;

import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.machine.BaseMachineBlock;
import com.devdyna.synergy.api.node.builder.NodeBaseBlock;
import com.devdyna.synergy.api.reactor.CoolerBlockBase;
import com.devdyna.synergy.api.reactor.ModeratorBase;
import com.devdyna.synergy.compat.jade.provider.AOEMachines;
import com.devdyna.synergy.compat.jade.provider.CoolerProvider;
import com.devdyna.synergy.compat.jade.provider.DryableBricksProvider;
import com.devdyna.synergy.compat.jade.provider.EnergyTipProviders;
import com.devdyna.synergy.compat.jade.provider.EnvironmentModifierProvider;
import com.devdyna.synergy.compat.jade.provider.FuelCellProgress;
import com.devdyna.synergy.compat.jade.provider.LaserMachineGunProvider;
import com.devdyna.synergy.compat.jade.provider.LaserRotorProvider;
import com.devdyna.synergy.compat.jade.provider.MachineProgress;
import com.devdyna.synergy.compat.jade.provider.ModeratorProvider;
import com.devdyna.synergy.compat.jade.provider.NodeProvider;
import com.devdyna.synergy.compat.jade.provider.ReactorControllerProvider;
import com.devdyna.synergy.compat.jade.provider.SimpleDelayProvider;
import com.devdyna.synergy.compat.jade.provider.TickProgressBlock;
import com.devdyna.synergy.init.builder.laser.laser_rotor.LaserRotorBlock;
import com.devdyna.synergy.init.builder.laser.machine_gun.LaserMachineBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.fuel_cell.FuelCellBlock;
import com.devdyna.synergy.init.builder.survival.placeable_bricks.PlaceableBrickBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration r) {
        r.registerBlockComponent(LaserMachineGunProvider.INSTANCE, LaserMachineBlock.class);
        r.registerBlockComponent(LaserRotorProvider.INSTANCE, LaserRotorBlock.class);
        r.registerBlockComponent(MachineProgress.INSTANCE, BaseMachineBlock.class);
        r.registerBlockComponent(ReactorControllerProvider.INSTANCE, ReactorControllerBlock.class);
        r.registerBlockComponent(AOEMachines.INSTANCE, TickingBlock.class);
        r.registerBlockComponent(NodeProvider.INSTANCE, NodeBaseBlock.class);
        r.registerBlockComponent(EnergyTipProviders.INSTANCE, TickingBlock.class);
        r.registerBlockComponent(CoolerProvider.INSTANCE, CoolerBlockBase.class);
        r.registerBlockComponent(ModeratorProvider.INSTANCE, ModeratorBase.class);
        r.registerBlockComponent(FuelCellProgress.INSTANCE, FuelCellBlock.class);
        r.registerBlockComponent(TickProgressBlock.INSTANCE, TickingBlock.class);
        r.registerBlockComponent(EnvironmentModifierProvider.INSTANCE, TickingBlock.class);
        r.registerBlockComponent(SimpleDelayProvider.INSTANCE, TickingBlock.class);
        r.registerBlockComponent(DryableBricksProvider.INSTANCE, PlaceableBrickBlock.class);
    }

    @Override
    public void register(IWailaCommonRegistration r) {
        r.registerBlockDataProvider(LaserMachineGunProvider.INSTANCE, LaserMachineBlock.class);
        r.registerBlockDataProvider(LaserRotorProvider.INSTANCE, LaserRotorBlock.class);
        r.registerBlockDataProvider(MachineProgress.INSTANCE, BaseMachineBlock.class);
        r.registerBlockDataProvider(ReactorControllerProvider.INSTANCE, ReactorControllerBlock.class);
        r.registerBlockDataProvider(AOEMachines.INSTANCE, TickingBlock.class);
        r.registerBlockDataProvider(NodeProvider.INSTANCE, NodeBaseBlock.class);
        r.registerBlockDataProvider(EnergyTipProviders.INSTANCE, TickingBlock.class);
        r.registerBlockDataProvider(CoolerProvider.INSTANCE, CoolerBlockBase.class);
        r.registerBlockDataProvider(ModeratorProvider.INSTANCE, ModeratorBase.class);
        r.registerBlockDataProvider(FuelCellProgress.INSTANCE, FuelCellBlock.class);
        r.registerBlockDataProvider(TickProgressBlock.INSTANCE, TickingBlock.class);
        r.registerBlockDataProvider(EnvironmentModifierProvider.INSTANCE, TickingBlock.class);
        r.registerBlockDataProvider(SimpleDelayProvider.INSTANCE, TickingBlock.class);
        r.registerBlockDataProvider(DryableBricksProvider.INSTANCE, PlaceableBrickBlock.class);
    }
}
