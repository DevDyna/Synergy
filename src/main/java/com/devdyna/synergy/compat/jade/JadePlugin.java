package com.devdyna.synergy.compat.jade;

import com.devdyna.synergy.api.basebe.block.TickingBlock;
import com.devdyna.synergy.api.node.builder.NodeBaseBlock;
import com.devdyna.synergy.compat.jade.provider.AOEMachines;
import com.devdyna.synergy.compat.jade.provider.CoolerProvider;
import com.devdyna.synergy.compat.jade.provider.EnergyTipProviders;
import com.devdyna.synergy.compat.jade.provider.LaserMachineGunProvider;
import com.devdyna.synergy.compat.jade.provider.LaserRotorProvider;
import com.devdyna.synergy.compat.jade.provider.MachineProgress;
import com.devdyna.synergy.compat.jade.provider.ModeratorProvider;
import com.devdyna.synergy.compat.jade.provider.NodeProvider;
import com.devdyna.synergy.compat.jade.provider.ReactorControllerProvider;
import com.devdyna.synergy.init.builder.laser.laser_rotor.LaserRotorBlock;
import com.devdyna.synergy.init.builder.laser.machine_gun.LaserMachineBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.controller.ReactorControllerBlock;
import com.devdyna.synergy.init.builder.nuclear_reactor.cooler.CoolerBlockBase;
import com.devdyna.synergy.init.builder.nuclear_reactor.moderator.ModeratorBase;
import com.devdyna.synergy.init.machine.core.BaseMachineBlock;

import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(LaserMachineGunProvider.INSTANCE, LaserMachineBlock.class);
        registration.registerBlockComponent(LaserRotorProvider.INSTANCE, LaserRotorBlock.class);
        registration.registerBlockComponent(MachineProgress.INSTANCE, BaseMachineBlock.class);
        registration.registerBlockComponent(ReactorControllerProvider.INSTANCE, ReactorControllerBlock.class);
        registration.registerBlockComponent(AOEMachines.INSTANCE, TickingBlock.class);
        registration.registerBlockComponent(NodeProvider.INSTANCE, NodeBaseBlock.class);
        registration.registerBlockComponent(EnergyTipProviders.INSTANCE, TickingBlock.class);
        registration.registerBlockComponent(CoolerProvider.INSTANCE, CoolerBlockBase.class);
        registration.registerBlockComponent(ModeratorProvider.INSTANCE, ModeratorBase.class);
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(LaserMachineGunProvider.INSTANCE, LaserMachineBlock.class);
        registration.registerBlockDataProvider(LaserRotorProvider.INSTANCE, LaserRotorBlock.class);
        registration.registerBlockDataProvider(MachineProgress.INSTANCE, BaseMachineBlock.class);
        registration.registerBlockDataProvider(ReactorControllerProvider.INSTANCE, ReactorControllerBlock.class);
        registration.registerBlockDataProvider(AOEMachines.INSTANCE, TickingBlock.class);
        registration.registerBlockDataProvider(NodeProvider.INSTANCE, NodeBaseBlock.class);
        registration.registerBlockDataProvider(EnergyTipProviders.INSTANCE, TickingBlock.class);
        registration.registerBlockDataProvider(CoolerProvider.INSTANCE, CoolerBlockBase.class);
        registration.registerBlockDataProvider(ModeratorProvider.INSTANCE, ModeratorBase.class);
    }
}
