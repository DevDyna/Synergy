package com.devdyna.synergy.compat.jade;

import com.devdyna.synergy.compat.jade.provider.LaserMachineGunProvider;
import com.devdyna.synergy.init.builder.laser.machine_gun.LaserMachineBlock;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(LaserMachineGunProvider.INSTANCE, LaserMachineBlock.class);
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(LaserMachineGunProvider.INSTANCE, LaserMachineBlock.class);
    }
}
