package com.devdyna.synergy;

import com.devdyna.synergy.common.dataMaps.zDataMaps;
import com.devdyna.synergy.compat.core;
import com.devdyna.synergy.init.Material;
import com.devdyna.synergy.utils.LogUtil;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Main.ID)
public class Main {

    public static final String ID = "synergy";

    public Main(IEventBus bus, ModContainer mc) {

        new LogUtil();

        Material.register(bus);
        core.registerCompat();

        GameEvents.register();

        bus.addListener(Capability::register);
        bus.addListener(zDataMaps::register);

    }

}
